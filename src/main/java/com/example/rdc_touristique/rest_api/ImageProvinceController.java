package com.example.rdc_touristique.rest_api;


import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.ImageProvinceDTO;
import com.example.rdc_touristique.business.dto.ProvinceDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.business.service.ImageModelService;
import com.example.rdc_touristique.business.service.ImageProvinceService;
import com.example.rdc_touristique.data_access.entity.ImageBien;
import com.example.rdc_touristique.data_access.entity.ImageProvince;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "image_province")
public class ImageProvinceController extends AbstratCrudController<ImageProvinceDTO, Integer>{
    public ImageProvinceController(CrudService<ImageProvinceDTO, Integer> service){super(service);}

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public void uplaodImage(@RequestParam("imageFile") List<MultipartFile> file, @RequestParam("province")int province) throws IOException {
         ((ImageProvinceService)service).uploadImage(file, province);
    }

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ImageProvince> getAllll(){
        return ((ImageProvinceService)service).getAll();
    }

}
