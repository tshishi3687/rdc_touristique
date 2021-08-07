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
    public BodyBuilder uplaodImage(@RequestParam("imageFile") List<MultipartFile> file, @RequestParam("bien")int bienDTO) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return ((ImageModelService)service).uploadImage(file, bienDTO);
    }


//    @GetMapping(path = { "/get/{imageName}" })
//    public ImageBien getImage(@PathVariable("imageName") String imageName) throws IOException {
//        return ((ImageModelService)service).getImage(imageName);
//    }

//    @GetMapping(path = "getall")
//    public List<ImageModel> getAllbySuperid(int id) throws IOException {
//        return ((ImageModelService)service).getAllBySuperid(id);
//    }

}
