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

/**
 * This class unctions as a controller for intercepting HTTP requests from
 * clients. All methods needed for the client to retrieve all necessary
 * information about image filtering and retrieval are available here.
 */
@RestController
@RequestMapping("/api")
public class GalleryController {
    private final GalleryModel model;

    /**
     * Constructs a new {@link GalleryController} with the specified
     * {@link GalleryModel} object.
     *
     * @param model the {@link GalleryModel} this controller will reference
     */
    @Autowired
    public GalleryController(GalleryModel model) {
        this.model = model;
    }

    /**
     * Retrieves the {@link List<GalleryImage>} of all {@link GalleryImage}
     * objects stored in the {@link GalleryModel}.
     *
     * @return the {@link List<GalleryImage>} of {@link GalleryImage} objects
     */
    @GetMapping("/images")
    public List<GalleryImage> getImages() {
        return model.getImages();
    }

    /**
     * Retrieves the {@link GalleryImage} that matches the specified date.
     *
     * @return the {@link GalleryImage} object
     */
    @GetMapping("/imageByDate")
    public GalleryImage getImageByDate(@RequestParam String date) {
        return model.getImageByDate(date);
    }

    /**
     * Retrieves the {@link List<GalleryImage>} of all {@link GalleryImage}
     * objects stored in the {@link GalleryModel} that match the specified
     * date.
     *
     * @return the {@link List<GalleryImage>} of {@link GalleryImage} objects
     */
    @GetMapping("/imagesByDate")
    public List<GalleryImage> getImagesByDate(@RequestParam String date) {
        return model.getImagesByDate(date);
    }

    /**
     * Retrieves the {@link GalleryImage} that matches the specified location.
     *
     * @return the {@link GalleryImage} object
     */
    @GetMapping("/imageByLocation")
    public GalleryImage getImageByLocation(@RequestParam String location) {
        return model.getImageByLocation(location);
    }

    /**
     * Retrieves the {@link List<GalleryImage>} of all {@link GalleryImage}
     * objects stored in the {@link GalleryModel} that match the specified
     * location.
     *
     * @return the {@link List<GalleryImage>} of {@link GalleryImage} objects
     */
    @GetMapping("/imagesByLocation")
    public List<GalleryImage> getImagesByLocation(@RequestParam String location) {
        return model.getImagesByLocation(location);
    }

    /**
     * Retrieves the {@link GalleryImage} that matches the specified id.
     *
     * @return the {@link GalleryImage} object
     */
    @GetMapping("/imageById")
    public GalleryImage getImageById(@RequestParam int id) {
        return model.getImageById(id);
    }

    /**
     * Retrieves the {@link ResponseEntity<byte>} that contains the byte array
     * of the {@link GalleryImage} that matches the specified id.
     *
     * @return the {@link ResponseEntity<byte>} object
     */
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
