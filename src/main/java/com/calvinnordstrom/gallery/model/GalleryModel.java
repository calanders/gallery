package com.calvinnordstrom.gallery.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods for basic operations on a
 * {@link List<GalleryImage>} of {@link GalleryImage} objects provided by the
 * specified {@link GalleryRegistry}.
 */
public class GalleryModel {
    private final List<GalleryImage> images;

    /**
     * Constructs a {@link GalleryModel} with the specified
     * {@link GalleryRegistry}.
     *
     * @param galleryRegistry the {@link GalleryRegistry}
     */
    public GalleryModel(GalleryRegistry galleryRegistry) {
        this.images = galleryRegistry.getImages();
    }

    /**
     * Retrieves the {@link List<GalleryImage>} of {@link GalleryImage}.
     *
     * @return the {@link List<GalleryImage>} of {@link GalleryImage}
     */
    public List<GalleryImage> getImages() {
        return images;
    }

    /**
     * Retrieves the {@link List<GalleryImage>} of {@link GalleryImage} sorted
     * by the specified date string.
     *
     * @return the {@link List<GalleryImage>} of {@link GalleryImage}
     */
    public List<GalleryImage> getImagesByDate(String date) {
        String[] splitDates = date.split("-");
        String year = splitDates[0];
        String month = splitDates[1];
        String day = splitDates[2];

        List<GalleryImage> list = new ArrayList<>();
        for (GalleryImage image : images) {
            String[] splitImageDates = image.date().split("-");
            String imageYear = splitImageDates[0];
            String imageMonth = splitImageDates[1];
            String imageDay = splitImageDates[2];

            boolean matches = (year.equals("0000") || year.equals(imageYear))
                    && (month.equals("00") || month.equals(imageMonth))
                    && (day.equals("00") || day.equals(imageDay));

            if (matches) list.add(image);
        }
        return list;
    }

    /**
     * Retrieves the {@link GalleryImage} that matches the specified date
     * string.
     *
     * @return the {@link GalleryImage}
     */
    public GalleryImage getImageByDate(String date) {
        for (GalleryImage image : images) {
            if (image.date().equals(date)) return image;
        }
        return null;
    }

    /**
     * Retrieves the {@link List<GalleryImage>} of {@link GalleryImage} sorted
     * by the location string
     *
     * @return the {@link List<GalleryImage>} of {@link GalleryImage}
     */
    public List<GalleryImage> getImagesByLocation(String location) {
        List<GalleryImage> list = new ArrayList<>();
        for (GalleryImage image : images) {
            String location1 = image.location().toLowerCase();
            String location2 = location.toLowerCase();
            if (location1.contains(location2)) list.add(image);
        }
        return list;
    }

    /**
     * Retrieves the {@link GalleryImage} that matches the specified location
     * string.
     *
     * @return the {@link GalleryImage}
     */
    public GalleryImage getImageByLocation(String location) {
        for (GalleryImage image : images) {
            if (image.location().equals(location)) return image;
        }
        return null;
    }

    /**
     * Retrieves the {@link GalleryImage} that matches the id integer.
     *
     * @return the {@link GalleryImage}
     */
    public GalleryImage getImageById(int id) {
        for (GalleryImage image : images) {
            if (image.id() == id) return image;
        }
        return null;
    }
}

