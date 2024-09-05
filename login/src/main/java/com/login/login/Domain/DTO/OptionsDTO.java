package com.login.login.Domain.DTO;

import com.login.login.Domain.Audit;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionsDTO {
    private Long question_id;
    private Long SubQuestion_id;
    private String TextOptions;
    @Embedded
    Audit audit = new Audit();
}
