package com.example.rdc_touristique.rest_api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.ImageModelDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.business.service.ImageModelService;
import com.example.rdc_touristique.data_access.entity.ImageModel;
import com.example.rdc_touristique.data_access.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "image")
public class ImageUploadController extends AbstratCrudController<ImageModelDTO, Integer>{
    public ImageUploadController(CrudService<ImageModelDTO, Integer> service) {
        super(service);
    }

    @PostMapping("/upload")
    public BodyBuilder uplaodImage(@RequestParam("imageFile") List<MultipartFile> file,@RequestParam("superid") int superid) throws IOException {
        return ((ImageModelService)service).uploadImage(file, superid);
    }


    @GetMapping(path = { "/get/{imageName}" })
    public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
        return ((ImageModelService)service).getImage(imageName);
    }

    @GetMapping(path = "getall")
    public List<ImageModel> getAllbySuperid(int id) throws IOException {
        return ((ImageModelService)service).getAllBySuperid(id);
    }

}
