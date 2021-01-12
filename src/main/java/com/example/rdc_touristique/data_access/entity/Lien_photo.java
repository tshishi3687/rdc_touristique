package com.example.rdc_touristique.data_access.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Entity

@NoArgsConstructor
@AllArgsConstructor
public class Lien_photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Column(name = "img")
    @Lob
    @Getter
    @Setter
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "province")
    @Getter
    @Setter
    private Province province;

    @ManyToOne
    @JoinColumn(name = "ville")
    @Getter
    @Setter
    private Ville ville;

    @ManyToOne
    @JoinColumn(name = "bien")
    @Getter
    @Setter
    private Bien bien;



//    public void setImage(BufferedImage bufferedImage) throws IOException{
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(bufferedImage, mime_type.split("image/")[1], baos);
//        baos.flush();
//        this.image = baos.toByteArray();
//        baos.close();
//    }
//
//    public BufferedImage getBufferedImage() throws IOException {
//        return ImageIO.read(new ByteArrayInputStream(image));
//    }
//
//    transient public static final Map acceptedMimeType = new HashMap();
//    static{
//        acceptedMimeType.put("jpg", "image/jpeg");
//        acceptedMimeType.put("gif", "image/gif");
//        acceptedMimeType.put("png", "image/png");
//        acceptedMimeType.put("tif", "image/tiff");
//        acceptedMimeType.put("svg", "image/svg+xml");
//        acceptedMimeType.put("ico", "image/vnd.microsoft.icon");
//    }
}
