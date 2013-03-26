package de.agilecoders.wicket.core.markup.html.bootstrap.components;

import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

/**
 * Thumbnails are great for grids of photos or videos, image search results, retail
 * products, portfolios, and much more. They can be links or static content.
 * <p/>
 * By default, Bootstrap's thumbnails are designed to showcase linked images with minimal required markup.
 * With a bit of extra markup, it's possible to add any kind of HTML content like headings, paragraphs,
 * or buttons into thumbnails.
 *
 * @author miha
 */
public class ThumbnailBorder extends Border {

    private Component title;
    private Component image;

    /**
     * Construct.
     *
     * @param id the component id
     */
    public ThumbnailBorder(String id) {
        this(id, Model.of(""));
    }

    /**
     * Construct.
     *
     * @param id    the component id
     * @param model the default image url
     */
    public ThumbnailBorder(String id, IModel<String> model) {
        super(id, model);

        add(title = new Label("title", Model.of("")),
            image = new Label("image").add(new AttributeModifier("url", getDefaultModel())));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "div");
        Attributes.addClass(tag, "thumbnail");
    }

    /**
     * sets the thumbnail title
     *
     * @param title new thumbnail title
     * @return this instance for chaining
     */
    public ThumbnailBorder setTitle(IModel<String> title) {
        this.title.setDefaultModel(title);
        return this;
    }

    /**
     * sets the thumbnail title
     *
     * @param title new thumbnail title
     * @return this instance for chaining
     */
    public ThumbnailBorder setTitle(String title) {
        this.title.setDefaultModelObject(title);
        return this;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        Components.hideIfModelIsEmpty(title);
        image.setVisible(!Strings.isEmpty(getDefaultModelObjectAsString()));
    }
}

