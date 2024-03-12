package com.example.Seminar6HomeWork6.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Класс заметки
 */
@Data
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id заметки

    @Column(nullable = false)
    private String heading; // заголовок заметки

    @Column(nullable = false)
    private String content; // содержимое заметки

    @Column()
    private LocalDateTime createDate; // дата создания (автоматически устанавливается при создании заметки)

}
