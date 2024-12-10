package com.calvinnordstrom.gallery.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GalleryConfig {
    @Bean
    public GalleryRegistry galleryRegistry() {
        return new GalleryRegistry();
    }

    @Bean
    public GalleryModel galleryModel(GalleryRegistry galleryRegistry) {
        return new GalleryModel(galleryRegistry);
    }
}
