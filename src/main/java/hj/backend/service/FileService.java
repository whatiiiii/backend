package hj.backend.service;

import hj.backend.domain.FileUp;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {
    //(1)파일 업로드
    long saveFile(MultipartFile mf) throws IOException;
    //(2)파일 다운로드
    FileUp getFileUp(long file_id);
    List<FileUp> getFileUpAll();
    //(3)파일 삭제
    void remove(long id);
}
