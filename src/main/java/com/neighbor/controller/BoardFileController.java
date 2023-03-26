package com.neighbor.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/board-files/*")
@RequiredArgsConstructor
@Slf4j
public class BoardFileController {


    
//    파일 업로드 하면 Ajax로 들어옴
    @PostMapping("uploadD")
    @ResponseBody
    public List<String> uploadD (@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException{
        List<String> uuids = new ArrayList<>();
        String path = "C:/upload/" + getPath();
        log.info("path는 " + path);
        File file = new File(path);
        if(!file.exists()) {file.mkdirs();}
        for(int i=0; i < multipartFiles.size(); i++){
            uuids.add(UUID.randomUUID().toString());
            multipartFiles.get(i).transferTo(new File(path, uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));
            FileOutputStream out = new FileOutputStream(new File(path, "t_" + uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));
            Thumbnailator.createThumbnail(multipartFiles.get(i).getInputStream(), out, 100, 100);
            out.close();
        }
        return uuids;
    }


    //    파일 업로드 하면 Ajax로 들어옴
    @PostMapping("uploadM")
    @ResponseBody
    public List<String> uploadP (@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException{
        List<String> uuids = new ArrayList<>();
        String path = "C:/upload/" + getPath();
        log.info("path는 " + path);
        File file = new File(path);
        if(!file.exists()) {file.mkdirs();}
        for(int i=0; i < multipartFiles.size(); i++){
            uuids.add(UUID.randomUUID().toString());
            multipartFiles.get(i).transferTo(new File(path, uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));
            FileOutputStream out = new FileOutputStream(new File(path, "m_" + uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));
            Thumbnailator.createThumbnail(multipartFiles.get(i).getInputStream(), out, 100, 100);
            out.close();
        }
        return uuids;
    }

//  파일 불러오기
    @GetMapping("display")
    @ResponseBody
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload", fileName));
    }




    //    현재 날짜 경로 구하기
    private String getPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
