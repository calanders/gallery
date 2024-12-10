package com.calvinnordstrom.gallery;

import com.calvinnordstrom.gallery.model.GalleryImage;
import com.calvinnordstrom.gallery.model.GalleryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GalleryController {
    private final GalleryModel model;

    @Autowired
    public GalleryController(GalleryModel model) {
        this.model = model;
    }

    @GetMapping("/images")
    public List<GalleryImage> getImages() {
        return model.getImages();
    }

    @GetMapping("/imageByDate")
    public GalleryImage getImageByDate(@RequestParam String date) {
        return model.getImageByDate(date);
    }

    @GetMapping("/imagesByDate")
    public List<GalleryImage> getImagesByDate(@RequestParam String date) {
        return model.getImagesByDate(date);
    }

    @GetMapping("/imageByLocation")
    public GalleryImage getImageByLocation(@RequestParam String location) {
        return model.getImageByLocation(location);
    }

    @GetMapping("/imagesByLocation")
    public List<GalleryImage> getImagesByLocation(@RequestParam String location) {
        return model.getImagesByLocation(location);
    }

    @GetMapping("/imageById")
    public GalleryImage getImageById(@RequestParam int id) {
        return model.getImageById(id);
    }

//    @GetMapping("/bytesByDate")
//    public ResponseEntity<byte[]> getBytesByDate(@RequestParam String date) {
//        byte[] bytes = model.getImageByDate(date).getBytes();
//        return getImageResponseEntity(bytes);
//    }
//
//    @GetMapping("/bytesByLocation")
//    public ResponseEntity<byte[]> getBytesByLocation(@RequestParam String location) {
//        byte[] bytes = model.getImageByLocation(location).getBytes();
//        return getImageResponseEntity(bytes);
//    }

    @GetMapping("/bytesById")
    public ResponseEntity<byte[]> getBytesById(@RequestParam int id) {
        byte[] bytes = model.getImageById(id).getBytes();
        return getImageResponseEntity(bytes);
    }

    private static ResponseEntity<byte[]> getImageResponseEntity(byte[] bytes) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
