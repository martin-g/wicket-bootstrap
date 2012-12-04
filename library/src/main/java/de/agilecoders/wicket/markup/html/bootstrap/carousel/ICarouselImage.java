package de.agilecoders.wicket.markup.html.bootstrap.carousel;

import com.google.common.base.Optional;

/**
 * Represents a image for a {@link Carousel} component
 *
 * @author miha
 */
public interface ICarouselImage {

    /**
     * @return the url to the image
     */
    public String url();

    /**
     * @return the description of the image
     */
    public Optional<String> description();

    /**
     * @return header of description for image
     */
    public Optional<String> header();

}
