package com.calvinnordstrom.gallery.model;

import com.calvinnordstrom.gallery.Application;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for initializing and storing all
 * {@link GalleryImage} objects. When the bean of this class is created,
 * instances of each {@link GalleryImage} is created and will persist in its
 * {@link List<GalleryImage>} for the lifetime of the server.
 */
public class GalleryRegistry {
    private final List<GalleryImage> images = new ArrayList<>();
    private final GalleryImage IMG_2878 = register("IMG_2878.PNG", "2017-04-24", "Rio Celeste, Costa Rica");
    private final GalleryImage IMG_4302 = register("IMG_4302.JPG", "2020-06-05", "Wild River Wilderness, New Hampshire");
    private final GalleryImage IMG_5624 = register("IMG_5624.JPG", "2023-01-01", "Beaver Creek, Colorado");
    private final GalleryImage IMG_6635 = register("IMG_6635.JPG", "2024-02-19", "Park City, Utah");
    private final GalleryImage IMG_7021 = register("IMG_7021.JPG", "2024-08-27", "Lost Creek Campground, Colorado");

    /**
     * Retrieves the {@link List<GalleryImage>} containing all
     * {@link GalleryImage} objects stored on this server.
     *
     * @return the {@link List<GalleryImage>} of {@link GalleryImage} objects
     */
    public List<GalleryImage> getImages() {
        return images;
    }

    private GalleryImage register(String fileName, String date, String location) {
        String root = "src/main/resources/static/images/";
        File file = new File(root + fileName);

        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            Application.LOGGER.warn("Unable to create required ImageInputStream");
        }

        GalleryImage galleryImage = new GalleryImage(image, date, location, images.size());
        images.add(galleryImage);
        return galleryImage;
    }
}
