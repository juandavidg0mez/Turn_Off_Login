package com.login.login.Domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Option_Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    Audit audit = new Audit();

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    @ManyToOne
    @JoinColumn(name = "sub_question_id")
    @JsonIgnore
    private SubQuestion subQuestion;

    @ManyToOne
    @JoinColumn(name = "option_id")
    @JsonIgnore
    private Option option;

}
