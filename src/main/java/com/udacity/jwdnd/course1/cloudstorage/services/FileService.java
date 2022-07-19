package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {

    private final FileMapper fileMapper;
    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public boolean isFilenameAvailable(String fileName, Integer userId) {

        if (fileMapper.getFileByName(fileName, userId) != null) {
            return false;
        }
        return true;

    }

    public Integer insert(MultipartFile multipartFile, Integer userId1) throws IOException {

        String file_Name = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        System.out.println(file_Name +" ******************");

        //model file instance
        Integer fileId=null;
        System.out.println(fileId +" ******************");

        String fileName=file_Name;
        System.out.println(fileName +" ******************");

        String contentType=multipartFile.getContentType();
        System.out.println(contentType +" ******************");

        String fileSize="" + multipartFile.getSize();
        System.out.println(fileSize +" ******************");

        // owner of file
        Integer userId=userId1;
        System.out.println(userId +" ******************");

        byte[] fileData=multipartFile.getBytes();
        System.out.println(fileData +" ******************");

        File f= new File(fileId,fileName,contentType,fileSize,userId,fileData);

        System.out.println(f +" ******************");

        Integer insert = fileMapper.insert(f);
        return insert;
    }



    public List<File> getFiles(Integer userid) {

        return fileMapper.getFiles(userid);
    }
    public File getFile(Integer fileId) {

        return fileMapper.getFile(fileId);
    }



    public void delete(Integer fileId) {

        fileMapper.delete(fileId);
    }
}
