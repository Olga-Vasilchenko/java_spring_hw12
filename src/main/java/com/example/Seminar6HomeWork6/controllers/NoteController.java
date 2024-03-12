package com.example.Seminar6HomeWork6.controllers;

import com.example.Seminar6HomeWork6.models.Note;
import com.example.Seminar6HomeWork6.services.NoteService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс - контроллер, используется для обработки запросов
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    private final Counter addNoteCounter = Metrics.counter("add_note_count");

    private final NoteService noteService;

    /**
     * Метод обработки Get-запроса без параметров по адресу - localhost:8080/notes
     * @return - список всех заметок
     */
    @GetMapping()
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    /**
     * Метод обработки Post-запроса (создание новой заметки) с добавлением параметров заметки по адресу - localhost:8080/notes
     * @param note - новая заметка, передается через body
     * @return - новая заметка
     */
    @PostMapping()
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        addNoteCounter.increment();
        note.setCreateDate(LocalDateTime.now());
        return new ResponseEntity<>(noteService.createNote(note), HttpStatus.CREATED);
    }

    /**
     * Получение заметки по ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(@PathVariable("id") Long id) {
        Note noteById;
        try {
            noteById = noteService.getNoteById(id);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
        return new ResponseEntity<>(noteById, HttpStatus.OK);
    }

    /**
     * Редактирование заметки по id
     * @param note
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@RequestBody Note note, @PathVariable("id") Long id) {
        note.setId(id);
        return new ResponseEntity<>(noteService.updateNote(id, note), HttpStatus.OK);
    }

    /**
     * Метод обработки Delete-запроса на удаление заметки по id по адресу - localhost:8080/notes/{id}
     *
     * @param id - id заметки
     * @return - Void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable ("id") Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
