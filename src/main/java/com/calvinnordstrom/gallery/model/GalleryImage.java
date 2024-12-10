package com.calvinnordstrom.gallery.model;

import com.calvinnordstrom.gallery.Application;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GalleryImage {
    private final BufferedImage image;
    private final String date;
    private final String location;
    private final int id;

    public GalleryImage(BufferedImage image, String date, String location, int id) {
        this.image = image;
        this.date = date;
        this.location = location;
        this.id = id;
    }

    @JsonIgnore
    public BufferedImage getImage() {
        return image;
    }

    @JsonIgnore
    public byte[] getBytes() {
        byte[] bytes = new byte[0];
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            bytes = baos.toByteArray();
        } catch (IOException e) {
            Application.LOGGER.warn("Unable to create required ImageOutputStream");
        }
        return bytes;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getId() {
        return id;
    }
}
