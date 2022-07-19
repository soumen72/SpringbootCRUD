package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.Arrays;

public class File {
    //
    private Integer fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    // guy whose file
    //
    private Integer userId;


    private byte[] fileData;

    //
    public File(Integer fileId, String fileName, String contentType, String fileSize, Integer userId, byte[] fileData) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }


    //
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userid) {
        this.userId = userId;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }


//    @Override
//    public String toString() {
//        return "File{" +
//                "fileId=" + fileId +
//                ", fileName='" + fileName + '\'' +
//                ", contentType='" + contentType + '\'' +
//                ", fileSize='" + fileSize + '\'' +
//                ", userId=" + userId +
//                ", fileData=" + Arrays.toString(fileData) +
//                '}';
//    }
}
