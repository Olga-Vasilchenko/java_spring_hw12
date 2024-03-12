package com.example.Seminar6HomeWork6.repositories;

import com.example.Seminar6HomeWork6.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с базой данных
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    /**
     * Метод получения списка заметок по статусу
     * @param id - id заметки
     * @return - возвращает заметку
     */
//    Optional<Note> findById(Long id);

}
