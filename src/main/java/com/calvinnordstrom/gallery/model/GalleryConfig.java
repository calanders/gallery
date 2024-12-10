package com.calvinnordstrom.gallery.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Represents the configuration class of this Java Spring application. All
 * required beans are constructed in this class.
 */
@Configuration
public class GalleryConfig {
    /**
     * Constructs the {@link GalleryRegistry} bean.
     *
     * @return the {@link GalleryRegistry}
     */
    @Bean
    public GalleryRegistry galleryRegistry() {
        return new GalleryRegistry();
    }

    /**
     * Constructs the {@link GalleryModel} bean from the specified
     * {@link GalleryRegistry} object.
     *
     * @param galleryRegistry the {@link GalleryRegistry}
     * @return the {@link GalleryModel}
     */
    @Bean
    public GalleryModel galleryModel(GalleryRegistry galleryRegistry) {
        return new GalleryModel(galleryRegistry);
    }
}
