package de.agilecoders.wicket.markup.html.bootstrap.carousel;

import com.google.common.base.Optional;

/**
 * Default implementation of {@link ICarouselImage}.
 *
 * @author miha
 */
public class CarouselImage implements ICarouselImage {

    private final String header;
    private final String description;
    private final String url;

    /**
     * Construct.
     *
     * @param url image url
     */
    public CarouselImage(String url) {
        this(url, null, null);
    }

    /**
     * Construct.
     *
     * @param url    image url
     * @param header header text
     */
    public CarouselImage(String url, String header) {
        this(url, header, null);
    }

    /**
     * Construct.
     *
     * @param url         image url
     * @param header      header text
     * @param description description text
     */
    public CarouselImage(String url, String header, String description) {
        this.header = header;
        this.description = description;
        this.url = url;
    }

    @Override
    public String url() {
        return url;
    }

    @Override
    public Optional<String> description() {
        return Optional.fromNullable(description);
    }

    @Override
    public Optional<String> header() {
        return Optional.fromNullable(header);
    }
}
