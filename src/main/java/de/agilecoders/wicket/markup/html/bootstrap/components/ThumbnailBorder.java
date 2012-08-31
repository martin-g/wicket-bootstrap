package de.agilecoders.wicket.markup.html.bootstrap.components;

import com.google.common.base.Strings;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Thumbnails are great for grids of photos or videos, image search results, retail
 * products, portfolios, and much more. They can be links or static content.
 * <p/>
 * By default, Bootstrap's thumbnails are designed to showcase linked images with minimal required markup.
 * With a bit of extra markup, it's possible to add any kind of HTML content like headings, paragraphs,
 * or buttons into thumbnails.
 *
 * @author miha
 * @version 1.0
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

        add(new CssClassNameAppender("thumbnail"));
        add(new AssertTagNameBehavior("div"));

        add(title = new Label("title", Model.of("")),
            image = new Label("image").add(new AttributeModifier("url", getDefaultModel())));
    }

    public ThumbnailBorder setImageUrl(IModel<String> url) {
        setDefaultModelObject(url.getObject());
        return this;
    }

    public ThumbnailBorder setImageUrl(String url) {
        return setImageUrl(Model.of(url));
    }

    public ThumbnailBorder setTitle(IModel<String> title) {
        this.title.setDefaultModel(title);
        return this;
    }

    public ThumbnailBorder setTitle(String title) {
        return setTitle(Model.of(title));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        Components.hideIfModelIsEmpty(title);
        image.setVisible(!Strings.isNullOrEmpty(getDefaultModelObjectAsString()));
    }
}

