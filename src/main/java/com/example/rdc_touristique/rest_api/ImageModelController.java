package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.ImageModelDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.data_access.entity.ImageModel;
import com.example.rdc_touristique.data_access.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@RestController
@RequestMapping("image")
public class ImageModelController extends AbstratCrudController<ImageModelDTO, Integer>{

    @Autowired
    private Mapper<ImageModelDTO, ImageModel> imageModelMapper;
    @Autowired
    private ImageRepository imageRepository;

    public ImageModelController(CrudService<ImageModelDTO, Integer> service) {
        super(service);
        }

    @PostMapping("/upppp")
    public String save(@RequestParam("file") MultipartFile file) throws IllegalStateException,IOException {

        String baseDir = "C:\\Users\\tshis\\OneDrive\\Bureau\\TFE\\rdc_touristique\\image";
        file.transferTo(new File(baseDir + "myfile.jpg"));
        return "ok";
    }


//    @PostMapping("/save")
//    public String getAllByUser(
//            @ModelAttribute(name = "ImageModel") ImageModelDTO toDTO,
//            RedirectAttributes re,
//            @RequestParam("img") MultipartFile multipartFile) throws IOException {
//
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//        toDTO.setImage(fileName);
//        ImageModel saveImage = imageRepository.save(imageModelMapper.toEntity(toDTO));
//        String uploadDir =  "./image/imageModel/" + saveImage.getId();
//        System.out.println(saveImage);
//        Path uploadPath = Paths.get(uploadDir);
//
//        if (!Files.exists(uploadPath)){
//            Files.createDirectories(uploadPath);
//        }
//
//        try (InputStream inputStream = multipartFile.getInputStream()){
//            Path filePath = uploadPath.resolve(fileName);
//            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//        }catch (IOException e){
//            throw new IOException("could not save uploaded file: " +fileName);
//        }
//
//        re.addFlashAttribute("message", "the toDTO has been saved successfully.");
//
//
//        return "redirect:/toDTO";
//    }
}
