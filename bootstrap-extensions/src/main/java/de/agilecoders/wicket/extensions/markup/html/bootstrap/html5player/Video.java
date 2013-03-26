package de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Default implementation of {@link de.agilecoders.wicket.core.markup.html.bootstrap.extensions.html5player.Html5Player.IVideo}
 *
 * @author miha
 */
public class Video implements Html5Player.IVideo {

    private final IModel<String> url;
    private final IModel<String> type;

    /**
     * Construct.
     *
     * @param url The video url as string
     */
    public Video(final String url) {
        this(url, ""); // TODO add detection
    }

    /**
     * Construct.
     *
     * @param url       The video url as string
     * @param mediaType The media type of video
     */
    public Video(final String url, final String mediaType) {
        this(Model.of(url), Model.of(mediaType));
    }

    /**
     * Construct.
     *
     * @param url       The video url as string
     * @param mediaType The media type of video
     */
    public Video(final IModel<String> url, final IModel<String> mediaType) {
        this.url = url;
        this.type = mediaType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrl() {
        return url.getObject();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMediaType() {
        return type.getObject();
    }
}
