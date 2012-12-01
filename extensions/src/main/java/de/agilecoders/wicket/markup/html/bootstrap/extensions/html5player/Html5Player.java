package de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.common.AbstractConfig;
import de.agilecoders.wicket.util.JQuery;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.List;

import static de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player.Html5Player.Html5PlayerJqueryScript.videoUI;
import static de.agilecoders.wicket.util.JQuery.$;

/**
 * A customizable HTML5 video player plugin for jQuery based on bootstrap UI
 * <p/>
 * {@see http://html5-ninja.com/item/Bootstrap-video-player-jQuery-plugin}
 *
 * @author miha
 */
public class Html5Player extends Panel {

    private final IModel<Integer> width;
    private final IModel<Integer> height;
    private final IModel<List<? extends IVideo>> resources;
    private final Html5VideoConfig config;
    private final WebMarkupContainer container;
    private final IModel<String> errorMessage;

    /**
     * Construct.
     *
     * @param markupId The components id
     * @param model    The list of video resources
     */
    public Html5Player(final String markupId, final IModel<List<? extends IVideo>> model) {
        this(markupId, model, new Html5VideoConfig());
    }

    /**
     * Construct.
     *
     * @param markupId The components id
     * @param model    The list of video resources
     * @param config   The javascript configuration
     */
    public Html5Player(final String markupId, final IModel<List<? extends IVideo>> model, final Html5VideoConfig config) {
        super(markupId, model);

        resources = model;
        this.config = config;

        width = Model.of(370);
        height = Model.of(215);
        errorMessage = Model.of("Your browser does not support the video tag.");

        add(new CssClassNameAppender("videoUiWrapper", "thumbnail"));
        add(new AssertTagNameBehavior("div", "span"));
        add(container = newVideoTag("video"));
        container.add(newVideoList("videos"));
        container.add(new Label("message", errorMessage).setRenderBodyOnly(true));
    }

    /**
     * sets the width of the video
     *
     * @param width The width in pixel
     * @return this instance for chaining
     */
    public Html5Player setWidth(final Integer width) {
        this.width.setObject(width);
        return this;
    }

    /**
     * sets the height of the video
     *
     * @param height The height in pixel
     * @return this instance for chaining
     */
    public Html5Player setHeight(final Integer height) {
        this.height.setObject(height);
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
        return new ListView<IVideo>(markupId, resources) {

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
    private WebMarkupContainer newVideoTag(final String markupId) {
        final WebMarkupContainer container = new WebMarkupContainer(markupId);
        container.add(new AttributeModifier("width", width));
        container.add(new AttributeModifier("height", height));
        return container;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        response.render(JavaScriptReferenceHeaderItem.forReference(Html5PlayerJavaScriptReference.instance()));
        response.render(CssReferenceHeaderItem.forReference(Html5PlayerCssReference.instance()));

        response.render(OnDomReadyHeaderItem.forScript($(container).chain(videoUI(config)).get()));
    }

    /**
     * Interface for all video resources.
     */
    public static interface IVideo {

        /**
         * @return the url to the video (relative or absolute)
         */
        String getUrl();

        /**
         * @return the media type of given video url. e.g. video/ogg
         */
        String getMediaType();
    }

    /**
     * Abstraction of videoUI jquery method.
     */
    public static final class Html5PlayerJqueryScript extends JQuery.AbstractFunction {

        /**
         * helper method.
         *
         * @param config The javascript configuration
         * @return new {@link Html5PlayerJqueryScript} instance
         */
        public static Html5PlayerJqueryScript videoUI(final AbstractConfig config) {
            return new Html5PlayerJqueryScript(config);
        }

        /**
         * Construct.
         *
         * @param config The javascript configuration
         */
        public Html5PlayerJqueryScript(final AbstractConfig config) {
            super("videoUI");

            if (!config.isEmpty()) {
                addParameter(config.toJsonString());
            }
        }
    }
}
