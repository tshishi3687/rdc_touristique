package com.example.rdc_touristique.business.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class ModifPass {
    private String codeActive;
    private String newPass;
    private String verifPass;
}
