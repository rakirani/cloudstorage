package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

@Service
public class FileService {

    private FilesMapper fileMapper;

    public FileService(FilesMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating FileService bean");
    }

    public Files findFile(Integer fileid) {
        return fileMapper.getFile(fileid);
    }


    public List<Files> getFilesForUser(int userid){
        return fileMapper.getAllFiles(userid);
    }


    public void addFile(MultipartFile multipartFile, Integer userId) throws IOException {
        Files files = new Files();
        files.setUserid(userId);
        files.setFilename(multipartFile.getOriginalFilename());
        files.setContenttype((multipartFile.getContentType()));
        files.setFilesize("" + multipartFile.getSize());
        files.setFiledata(multipartFile.getBytes());
        fileMapper.insertFile(files);
    }

    public void deleteFile(Integer fileid){
        fileMapper.deleteFile(fileid);
        System.out.println(fileid);
    }
}
