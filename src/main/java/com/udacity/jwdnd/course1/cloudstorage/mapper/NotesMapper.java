package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {
    //create note model

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")  //noteId of model
    Integer insert(Note note);

    @Select("SELECT * FROM notes WHERE userid = #{userid}")
    List<Note> getNotes(Integer userId);

    @Update("UPDATE notes SET notetitle = #{noteTitle}, notedescription = #{noteDescription}, userid = #{userid} WHERE noteid = #{noteId}")
    void update(Note note);

    @Delete("DELETE FROM notes WHERE noteid = #{noteId}")
    void delete(Integer noteId);
}
