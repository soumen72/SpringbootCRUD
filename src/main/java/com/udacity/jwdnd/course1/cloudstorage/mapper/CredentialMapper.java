package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CredentialMapper {
    //create model for this  (schema , model)


    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{URL}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId") //keyProperty = "credentialId" model
    Integer insert(Credential credential);


    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credential> getCredentials(Integer userId);

    @Update("UPDATE CREDENTIALS SET url = #{URL}, username = #{username}, key = #{key}, password = #{password}, userid = #{userid} WHERE credentialId = #{credentialId}")
    void update(Credential credential);

//    @Update("UPDATE CREDENTIALS SET url = #{URL}, username = #{userName}, password = #{password} WHERE credentialid = #{credentialsId}")
//    Integer update(Integer credentialId, String url, String userName, String password);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void delete(Integer credentialId);


}
