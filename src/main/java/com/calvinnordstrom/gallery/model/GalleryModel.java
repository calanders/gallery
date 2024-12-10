package com.calvinnordstrom.gallery.model;

import java.util.ArrayList;
import java.util.List;

public class GalleryModel {
    private final List<GalleryImage> images;

    public GalleryModel(GalleryRegistry galleryRegistry) {
        this.images = galleryRegistry.getImages();
    }

    public List<GalleryImage> getImages() {
        return images;
    }

    public List<GalleryImage> getImagesByDate(String date) {
        String[] splitDates = date.split("-");
        String year = splitDates[0];
        String month = splitDates[1];
        String day = splitDates[2];

        List<GalleryImage> list = new ArrayList<>();
        for (GalleryImage image : images) {
            String[] splitImageDates = image.getDate().split("-");
            String imageYear = splitImageDates[0];
            String imageMonth = splitImageDates[1];
            String imageDay = splitImageDates[2];

            boolean matches = (year.equals("0000") || year.equals(imageYear))
                    && (month.equals("00") || month.equals(imageMonth))
                    && (day.equals("00") || day.equals(imageDay));

            if (matches) list.add(image);
        }
        return list;

//        List<GalleryImage> list = new ArrayList<>();
//        for (GalleryImage image : images) {
//            if (image.getDate().contains(date)) list.add(image);
//        }
//        return list;
    }

    public GalleryImage getImageByDate(String date) {
        for (GalleryImage image : images) {
            if (image.getDate().equals(date)) return image;
        }
        return null;
    }

    public List<GalleryImage> getImagesByLocation(String location) {
        List<GalleryImage> list = new ArrayList<>();
        for (GalleryImage image : images) {
            String location1 = image.getLocation().toLowerCase();
            String location2 = location.toLowerCase();
            if (location1.contains(location2)) list.add(image);
        }
        return list;
    }

    public GalleryImage getImageByLocation(String location) {
        for (GalleryImage image : images) {
            if (image.getLocation().equals(location)) return image;
        }
        return null;
    }

    public GalleryImage getImageById(int id) {
        for (GalleryImage image : images) {
            if (image.getId() == id) return image;
        }
        return null;
    }
}

