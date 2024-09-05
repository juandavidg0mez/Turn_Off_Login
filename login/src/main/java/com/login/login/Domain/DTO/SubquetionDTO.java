package com.login.login.Domain.DTO;

import com.login.login.Domain.Audit;


import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubquetionDTO {
    private String subquestiontext;
    private Long question_id;
    @Embedded
    Audit audit = new Audit();
}
