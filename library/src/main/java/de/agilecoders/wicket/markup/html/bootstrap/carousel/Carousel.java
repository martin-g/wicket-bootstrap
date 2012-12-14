package de.agilecoders.wicket.markup.html.bootstrap.carousel;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;

import java.util.List;

/**
 * A generic plugin and component for cycling through images (with description and header text)
 * like a carousel.
 *
 * @author miha
 */
public class Carousel extends Panel {

    private Duration interval = Duration.seconds(5);
    private final IModel<List<? extends ICarouselImage>> model;

    /**
     * Construct.
     *
     * @param markupId the component id
     * @param model    the list of images
     */
    public Carousel(final String markupId, final IModel<List<? extends ICarouselImage>> model) {
        super(markupId, model);
        this.model = model;

        setOutputMarkupId(true);

        add(new CssClassNameAppender("carousel", "slide"));
        add(new BootstrapResourcesBehavior());
        add(new AssertTagNameBehavior("div"));

        add(newImageList("images"),
            newNavigationButton("prev"),
            newNavigationButton("next"));
    }

    /**
     * creates a new navigation button
     *
     * @param markupId the component id
     * @return new navigation button
     */
    private Component newNavigationButton(final String markupId) {
        Label button = new Label(markupId);
        button.add(new AttributeModifier("href", "#" + getMarkupId(true)));
        button.setEscapeModelStrings(false);

        if ("prev".equals(markupId)) {
            button.setDefaultModel(createPrevLabel());
        } else {
            button.setDefaultModel(createNextLabel());
        }

        return button;
    }

    /**
     * @return label of previous button
     */
    protected IModel<String> createPrevLabel() {
        return Model.of("&lsaquo;");
    }

    /**
     * @return label of next button
     */
    protected IModel<String> createNextLabel() {
        return Model.of("&rsaquo;");
    }

    /**
     * creates a new {@link ICarouselImage} list view
     *
     * @param wicketId The component id
     * @return new list view.
     */
    private Component newImageList(String wicketId) {
        return new ListView<ICarouselImage>(wicketId, model) {

            private boolean renderedActive = false;

            @Override
            protected void populateItem(ListItem<ICarouselImage> item) {
                ICarouselImage carouselImage = item.getModelObject();

                Label image = new Label("image");
                image.add(new AttributeModifier("src", carouselImage.url()));

                Label header = new Label("header");
                if (carouselImage.header().isPresent()) {
                    header.setDefaultModel(Model.of(carouselImage.header().get()));
                } else {
                    header.setVisible(false);
                }

                Label description = new Label("description");
                if (carouselImage.description().isPresent()) {
                    description.setDefaultModel(Model.of(carouselImage.description().get()));
                } else {
                    description.setVisible(false);
                }

                TransparentWebMarkupContainer caption = new TransparentWebMarkupContainer("caption");
                caption.setVisible(header.isVisible() || description.isVisible());

                if (!renderedActive) {
                    renderedActive = true;
                    item.add(new CssClassNameAppender("active"));
                }

                item.add(image, header, description, caption);
            }
        };
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(OnDomReadyHeaderItem.forScript("$('#" + getMarkupId(true) + "').carousel({\n"
                                                       + "  interval: " + getInterval().getMilliseconds() + "\n"
                                                       + "})"));
    }

    /**
     * @return current interval as {@link Duration}
     */
    public final Duration getInterval() {
        return interval;
    }

    /**
     * The amount of time to delay between automatically cycling an item.
     * If Duration.NONE or value is 0, carousel will not automatically cycle.
     *
     * @param interval The duration
     * @return this instance for chaining
     */
    public final Carousel setInterval(final Duration interval) {
        this.interval = interval;

        return this;
    }
}