package com.login.login.Domain;


import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Surveys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    @NotNull(message = "Falta Descripcion para guardar")
    @Column(columnDefinition = "TEXT")
    private String description;

    
    @NotEmpty
    @NotNull(message = "Ten encuenta que para para guardar La encuenta debe tener un nombre")
    @Column(columnDefinition = "TEXT")
    private String name;

    @Embedded
    Audit audit = new Audit();

    @OneToMany(mappedBy = "surveys", cascade = CascadeType.ALL)
    private List<Chapters> chapters;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Categories categories;


}
