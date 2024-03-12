package com.example.Seminar6HomeWork6.services;

import com.example.Seminar6HomeWork6.models.Note;
import com.example.Seminar6HomeWork6.repositories.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;

    /**
     * Метод получения списка всех задач
     * @return - возвращает список заметок
     */
    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Метод получения заметки по id
     * @param id - id заметки
     * @return - возвращает заметку
     */
    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(null);
    }

    /**
     * Метод изменения заметки по id
     * @param note - id заметки
     * @return - обновленная заметка
     */
    @Override
    public Note updateNote(Long id, Note note) {
        Note noteById = getNoteById(id);
        noteById.setHeading(note.getHeading());
        noteById.setContent(note.getContent());
        return noteRepository.save(noteById);
    }

    /**
     * Метод сохранения заметки
     * @param note - новая заметка
     * @return - возвращает новую сохраненную заметку
     */
    @Override
    public Note createNote(Note note) {
        note.setCreateDate(LocalDateTime.now());
        return noteRepository.save(note);
    }

    /**
     * Метод удаления заметки по id
     * @param id - id заметки
     * @return
     */
    @Override
//    @TrackUserAction
    public void deleteNote(Long id) {
        Note noteById = getNoteById(id);
        noteRepository.delete(noteById);
    }
}
