package com.calvinnordstrom.gallery.model;

import com.calvinnordstrom.gallery.Application;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GalleryRegistry {
    private final List<GalleryImage> images = new ArrayList<>();
    public final GalleryImage IMG_2878 = register("IMG_2878.PNG", "2017-04-24", "Rio Celeste, Costa Rica");
    public final GalleryImage IMG_4302 = register("IMG_4302.JPG", "2020-06-05", "Wild River Wilderness, New Hampshire");
    public final GalleryImage IMG_5624 = register("IMG_5624.JPG", "2023-01-01", "Beaver Creek, Colorado");
    public final GalleryImage IMG_6635 = register("IMG_6635.JPG", "2024-02-19", "Park City, Utah");
    public final GalleryImage IMG_7021 = register("IMG_7021.JPG", "2024-08-27", "Lost Creek Campground, Colorado");

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