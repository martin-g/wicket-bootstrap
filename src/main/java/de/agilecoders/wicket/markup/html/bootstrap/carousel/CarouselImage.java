package de.agilecoders.wicket.markup.html.bootstrap.carousel;

import com.google.common.base.Optional;

import javax.annotation.concurrent.Immutable;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
@Immutable
public class CarouselImage {

    private final String header;
    private final String description;
    private final String url;

    public CarouselImage(String url) {
        this(url, null, null);
    }

    public CarouselImage(String url, String header) {
        this(url, header, null);
    }

    public CarouselImage(String url, String header, String description) {
        this.header = header;
        this.description = description;
        this.url = url;
    }

    public String url() {
        return url;
    }

    public Optional<String> description() {
        return Optional.fromNullable(description);
    }

    public Optional<String> header() {
        return Optional.fromNullable(header);
    }
}
