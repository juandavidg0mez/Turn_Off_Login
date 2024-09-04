package com.login.login.Domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class SubQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    Audit audit = new Audit();

    @Column(columnDefinition = "TEXT")
    private String subquestiontext;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    @OneToMany(mappedBy = "subQuestion")
    private List<Option_Question> option_Questions;

}
