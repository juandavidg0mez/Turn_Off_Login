package com.login.login.Domain.DTO;

import com.login.login.Domain.Audit;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private String question_number;
    private String response_type;
    private String commet_question;
    private String question_text;
    private Long chapter_id;
    @Embedded
    Audit audit = new Audit();
}
