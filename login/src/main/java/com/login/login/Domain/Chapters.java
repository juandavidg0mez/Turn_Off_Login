package com.login.login.Domain;

import java.util.List;



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
public class Chapters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    Audit audit = new Audit();

    @Column(columnDefinition = "VARCHAR(50)")
    private String chaper_number;

    @Column(columnDefinition = "VARCHAR(50)")
    private String chaper_title;

    @ManyToOne
    @JoinColumn(name = "surveys")
    private Surveys surveys;

    @OneToMany(mappedBy = "chapters", cascade = CascadeType.ALL)
    private List<Question> question;

}
