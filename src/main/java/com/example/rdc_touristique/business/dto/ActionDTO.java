package com.example.rdc_touristique.business.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class ActionDTO {

    private int id;
    private LocalDateTime date;
    private String className;
    private int idClasse;
    private String action;
    private String description;
}
