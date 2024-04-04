package de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;
import de.agilecoders.wicket.core.util.References;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.io.IClusterable;

import java.util.List;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * A customizable HTML5 video player plugin for jQuery based on bootstrap UI
 * <p/>
 * {@see http://html5-ninja.com/item/Bootstrap-video-player-jQuery-plugin}
 *
 * @author miha
 */
public class Html5Player extends Panel {
    private static final long serialVersionUID = 1L;
    private final IModel<List<IVideo>> resources;
    private final Html5VideoConfig config;
    private final WebMarkupContainer container;
    private final IModel<String> errorMessage;

    private int width;
    private int height;

    /**
     * Construct.
     *
     * @param markupId The components id
     * @param model    The list of video resources
     */
    public Html5Player(final String markupId, final IModel<List<IVideo>> model) {
        this(markupId, model, new Html5VideoConfig());
    }

    /**
     * Construct.
     *
     * @param markupId The components id
     * @param model    The list of video resources
     * @param config   The javascript configuration
     */
    public Html5Player(final String markupId, final IModel<List<IVideo>> model, final Html5VideoConfig config) {
        super(markupId, model);

        resources = model;
        this.config = config;

        width = 370;
        height = 215;
        errorMessage = Model.of("Your browser does not support the video tag.");

        add(container = newVideoTag("video"));
        container.add(newVideoList("videos"));
        container.add(new Label("message", errorMessage).setRenderBodyOnly(true));
    }


    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Components.assertTag(this, tag, "div", "span");
        Attributes.addClass(tag, "videoUiWrapper", "img-thumbnail");
    }

    /**
     * sets the width of the video
     *
     * @param width The width in pixel
     * @return this instance for chaining
     */
    public Html5Player setWidth(final int width) {
        this.width = width;
        return this;
    }

    /**
     * sets the height of the video
     *
     * @param height The height in pixel
     * @return this instance for chaining
     */
    public Html5Player setHeight(final int height) {
        this.height = height;
        return this;
    }

    /**
     * sets the error message which is shown if video tag is not supported by browser
     *
     * @param message The error message
     * @return this instance for chaining
     */
    public Html5Player setErrorMessage(final String message) {
        this.errorMessage.setObject(message);
        return this;
    }

    /**
     * creates a new video resources list
     *
     * @param markupId The component' id
     * @return new list view
     */
    private Component newVideoList(String markupId) {
        return new ListView<>(markupId, resources) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onInitialize() {
                super.onInitialize();

                setRenderBodyOnly(true);
            }

            @Override
            protected void populateItem(ListItem<IVideo> item) {
                final IVideo video = item.getModelObject();

                item.add(new WebMarkupContainer("element")
                                 .add(new AttributeModifier("src", video.getUrl()))
                                 .add(new AttributeModifier("type", video.getMediaType())));
            }
        };
    }

    /**
     * creates a video tag container
     *
     * @param markupId The component' id
     * @return new container
     */
    private WebMarkupContainer newVideoTag(String markupId) {
        return new WebMarkupContainer(markupId) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);

                tag.put("width", width);
                tag.put("height", height);
            }
        };
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(Html5PlayerCssReference.instance()));
        References.renderWithFilter(Bootstrap.getSettings(), response,
                                    JavaScriptHeaderItem.forReference(Html5PlayerJavaScriptReference.instance()));

        response.render($(container).chain("videoUI", config).asDomReadyScript());
    }

    /**
     * Interface for all video resources.
     */
    public static interface IVideo extends IClusterable {

        /**
         * @return the url to the video (relative or absolute)
         */
        String getUrl();

        /**
         * @return the media type of given video url. e.g. video/ogg
         */
        String getMediaType();
    }

}
