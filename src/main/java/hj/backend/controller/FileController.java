package hj.backend.controller;

import hj.backend.domain.FileUp;
import hj.backend.service.FileService;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequestMapping("file")
@RequiredArgsConstructor
@Controller
public class FileController {

    private final FileService fileService;
    //(1) 파일 업로드
    @GetMapping("upload.do")
    public String form(){
        return "file/form";
    }

    @PostMapping("upload.do")
    public String uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("files") List<MultipartFile> files)
        throws IOException {
            //long fileSize = file.getSize();
            fileService.saveFile(file); //단일파일
            for(MultipartFile mfile:files){
                fileService.saveFile(mfile);
            }
            return "redirect:list.do";
    }

    //(2) 파일 다운로드
    @GetMapping("list.do")
    public String list(Model model){
        List<FileUp> fileUps = fileService.getFileUpAll();
        model.addAttribute("fileUps", fileUps);
        return "file/list";
    }
    //첨부파일 다운로드
    @GetMapping("/attach/{file_id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long file_id) throws MalformedURLException {
        FileUp fileup = fileService.getFileUp(file_id);
        UrlResource resource = new UrlResource("file:" + fileup.getSavedpath());
        String encodedFileName = org.springframework.web.util.UriUtils.encode(fileup.getOrgnm(), StandardCharsets.UTF_8);

        // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
        // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource);
    }
    //이미지 출력
    @GetMapping("/images/{file_id}")
    @ResponseBody
    public org.springframework.core.io.Resource downloadImage(@PathVariable("file_id") Long file_id, Model model)
            throws IOException{
        FileUp fileup = fileService.getFileUp(file_id);
        return new UrlResource("file:" + fileup.getSavedpath());
    }

    void pln(String str){
        System.out.println(str);
    }


}
