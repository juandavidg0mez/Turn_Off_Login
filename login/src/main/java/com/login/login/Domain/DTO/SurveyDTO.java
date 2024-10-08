package com.login.login.Domain.DTO;



import com.login.login.Domain.Audit;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SurveyDTO {
    private String name;
    private String description;
    private Long  categories_id;
    @Embedded
    Audit audit = new Audit();
}
