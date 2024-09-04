package com.login.login.Domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    Audit audit = new Audit();

    @Column(columnDefinition = "VARCHAR(10)")
    private String question_number;

    @Column(columnDefinition = "VARCHAR(10)")
    private String response_type;

    @Column(columnDefinition = "TEXT")
    private String commet_question;

    @Column(columnDefinition = "TEXT")
    private String question_text;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    @JsonIgnore
    private Chapters chapters;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<SubQuestion> subQuestion;

}
