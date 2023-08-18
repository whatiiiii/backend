package hj.backend.service;

import hj.backend.domain.FileUp;
import hj.backend.repository.FileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor //자동으로 생성자 만들어줌
public class FileServiceImpl implements FileService {

    @org.springframework.beans.factory.annotation.Value("${file.dir}")
    private String fileDir;
    private final FileRepository fileRepository;
    
    //(1) 파일 업로드 
    @Override
    public long saveFile(MultipartFile mf) throws IOException {
        if(mf.isEmpty()){
            return -1;
        }
        String origName = mf.getOriginalFilename(); // 원래 파일 이름 추출
        String uuid = UUID.randomUUID().toString(); // 파일 이름으로 쓸 uuid 생성
        String extension = origName.substring(origName.lastIndexOf(".")); //확장자추출(ex: .png)
        String savedName = uuid + extension; //uuid 와 확장자 결합
        String savedPath = fileDir+savedName; //파일 불러올 때 사용할 파일 경로(물리적 경로)

        FileUp fileUp = FileUp.builder()
                .orgnm(origName)
                .savednm(savedName)
                .savedpath(savedPath)
                .build();
        mf.transferTo(new File(savedPath)); //실제로 로컬에 uuid 파일명으로 저장
        FileUp savedFile = fileRepository.save(fileUp); //오라클(DB)에 파일 insert
        return savedFile.getId();
    }

    //(2) 파일 다운로드
    @Override
    public List<FileUp> getFileUpAll(){
        List<FileUp> fileUps = fileRepository.findAll();
        return fileUps;
    }
    @Override
    public FileUp getFileUp(long file_id) {
        FileUp fileUp = fileRepository.findById(file_id).orElse(null);
        return fileUp;
    }
    //(3) 파일 삭제
    @Transactional //아래 둘 중 하나라도 안되면 rollback 하겠다 !~  중요함 !!
    @Override
    public void remove(long id){
        FileUp fileup = fileRepository.findById(id).orElse(null); //만약 db에서 file이 없다면 null
        String savedpath = fileup.getSavedpath();
        File f = new File(savedpath);
       // f.delete();
        if(f.exists()) { //(1) 파일이 존재하면 store 폴더에서 삭제해라
            f.delete();
        }
        fileRepository.deleteById(id); //(2)DB에서 삭제

    }
}
