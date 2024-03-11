package com.example.Seminar6HomeWork6.services;

import com.example.Seminar6HomeWork6.models.Note;

import java.util.List;


/**
 * Интерфейс сервиса заметок
 */

public interface NoteService {
    List<Note> getAllNotes();
    Note getNoteById(Long id);
    Note updateNote(Note note);
    Note createNote(Note note);
    void deleteNote(Long id);
}
