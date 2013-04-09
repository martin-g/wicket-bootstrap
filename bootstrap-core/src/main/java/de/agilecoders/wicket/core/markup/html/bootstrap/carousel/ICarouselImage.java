package de.agilecoders.wicket.core.markup.html.bootstrap.carousel;

import org.apache.wicket.util.io.IClusterable;

/**
 * Represents a image for a {@link Carousel} component
 *
 * @author miha
 */
public interface ICarouselImage extends IClusterable {

    /**
     * @return the url to the image
     */
    public String url();

    /**
     * @return the description of the image
     */
    public String description();

    /**
     * @return header of description for image
     */
    public String header();

}
