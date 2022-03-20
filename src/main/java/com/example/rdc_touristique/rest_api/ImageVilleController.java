package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.ImageVilleDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.business.service.ImageVilleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "image_ville")
public class ImageVilleController extends AbstratCrudController<ImageVilleDTO, Integer> {
    public ImageVilleController(CrudService<ImageVilleDTO, Integer> service){super(service);}

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public void uplaodImage(@RequestParam("imageFile") List<MultipartFile> file, @RequestParam("province")int province) throws IOException {
        ((ImageVilleService)service).uploadImage(file, province);
    }
}
