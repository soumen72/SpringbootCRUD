package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    //@Insert("INSERT INTO files (filename, contenttype, filesize, userid, filedata) VALUES (#{filename}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insert(File file);

    //create file in model, need the schema (schema ,model)
    @Select("SELECT * FROM files WHERE fileId = #{fileId}")
    File getFile(Integer fileId); //used in HomeController

    @Update("UPDATE files SET filename = #{fileName}, contenttype = #{contentType}, filesize = #{fileSize}, userid = #{userId} WHERE fileId = #{fileId}")
    void update(File file);

    @Delete("DELETE FROM files WHERE fileId = #{fileId}")
    void delete(Integer fileId);


    @Select("SELECT * FROM files WHERE filename = #{fileName} and userid = #{userId}")
    File getFileByName(String fileName, Integer userId);

    @Select("SELECT * FROM files WHERE userid = #{userId}")
    List<File> getFiles(Integer userId);





}
