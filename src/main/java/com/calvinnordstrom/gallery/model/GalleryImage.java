package com.calvinnordstrom.gallery.model;

import com.calvinnordstrom.gallery.Application;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Represents an image with some contextual metadata about its date and
 * location.
 * <p>
 * The image is constructed with an id so that it can be distinguished from
 * other images. See {@link GalleryRegistry} for construction implementation.
 *
 * @param image the image
 * @param date the date of the image
 * @param location the location of the image
 * @param id the id of the image
 */
public record GalleryImage(BufferedImage image, String date, String location, int id) {
    /**
     * Returns the {@link BufferedImage} of this object. This is overridden
     * from the {@link GalleryImage} record because this information should not
     * be returned with the rest of the fields. To get the pixel data of the
     * image, see {@link #getBytes()}.
     *
     * @return the {@link BufferedImage}
     */
    @Override
    @JsonIgnore
    public BufferedImage image() {
        return image;
    }

    /**
     * Converts the pixel data of the {@link BufferedImage} into a byte array.
     *
     * @return the pixel data in a byte array
     */
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
}
