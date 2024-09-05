package com.login.login.Domain.DTO;

import com.login.login.Domain.Audit;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDTO {
    private String chaper_number;
    private String chaper_title;
    private Long surveid;
    @Embedded
    Audit audit = new Audit();



}
