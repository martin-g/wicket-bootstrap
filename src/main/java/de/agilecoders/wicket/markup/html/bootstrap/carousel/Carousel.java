package de.agilecoders.wicket.markup.html.bootstrap.carousel;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
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
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Carousel extends Panel {

    private Duration interval = Duration.seconds(5);
    private final IModel<List<CarouselImage>> model;

    public Carousel(String id, IModel<List<CarouselImage>> model) {
        super(id, model);
        this.model = model;

        setOutputMarkupId(true);

        add(new CssClassNameAppender("carousel", "slide"));
        add(new BootstrapResourcesBehavior());

        add(newImageList("images"),
            newNavigationButton("prev"),
            newNavigationButton("next"));
    }

    private Component newNavigationButton(String wicketId) {
        Label button = new Label(wicketId);
        button.add(new AttributeModifier("href", "#" + getMarkupId(true)));
        button.setEscapeModelStrings(false);

        if("prev".equals(wicketId)) {
            button.setDefaultModel(createPrevLabel());
        } else {
            button.setDefaultModel(createNextLabel());
        }

        return button;
    }

    protected IModel<String> createPrevLabel() {
        return Model.of("&lsaquo;");
    }

    protected IModel<String> createNextLabel() {
        return Model.of("&rsaquo;");
    }

    private Component newImageList(String wicketId) {
        return new ListView<CarouselImage>(wicketId, model) {

            private boolean renderedActive = false;

            @Override
            protected void populateItem(ListItem<CarouselImage> item) {
                CarouselImage carouselImage = item.getModelObject();

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

                if(!renderedActive) {
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
                                                       + "  interval: "+ interval().getMilliseconds() +"\n"
                                                       + "})"));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Components.assertTag(this, tag, "div");
    }

    public Duration interval() {
        return interval;
    }

    public Carousel interval(Duration interval) {
        this.interval = interval;
        return this;
    }
}


/*

*/