package com.example.rdc_touristique.rest_api;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.ImageModelDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.business.service.ImageModelService;
import com.example.rdc_touristique.data_access.entity.ImageBien;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "image")
public class ImageUploadController extends AbstratCrudController<ImageModelDTO, Integer>{
    public ImageUploadController(CrudService<ImageModelDTO, Integer> service) {
        super(service);
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public BodyBuilder uplaodImage(@RequestParam("imageFile") List<MultipartFile> file, @RequestParam("bien")int bienDTO) throws IOException{
        return ((ImageModelService)service).uploadImage(file, bienDTO);
    }

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ImageBien> allImageByBienid(@RequestBody BienDTO bien){
        return ((ImageModelService)service).getImage(bien);
    }

}
