package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    //create note mapper

    private final NotesMapper notesMapper;
    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public Integer insert(Note note) {

        Integer noteId=null;

        String noteTitle=note.getNoteTitle();
        System.out.println(noteTitle+" **************");

        String noteDescription=note.getNoteDescription();

        Integer userid=note.getUserId();

        Note n = new Note(noteId,noteTitle,noteDescription,userid);

        Integer insert = notesMapper.insert(n);
        return insert;
    }

    public List<Note> getNotes(Integer userID) {

        return notesMapper.getNotes(userID);
    }



    public void update(Note note) {
        notesMapper.update(note);
    }

    public void delete(Integer noteId) {
        notesMapper.delete(noteId);
    }



}
