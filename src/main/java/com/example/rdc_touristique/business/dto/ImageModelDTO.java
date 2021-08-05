package com.example.rdc_touristique.business.dto;

import lombok.*;
import org.hibernate.type.ObjectType;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImageModelDTO implements IdentifiedDTO<Integer> {
    private Integer id;
    private byte[] img_byte;
    private String type;
    private String Name;
    private String FileName;
    private int size;
}
