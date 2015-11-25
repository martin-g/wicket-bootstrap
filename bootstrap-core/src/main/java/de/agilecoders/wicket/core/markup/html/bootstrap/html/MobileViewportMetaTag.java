package de.agilecoders.wicket.core.markup.html.bootstrap.html;

import de.agilecoders.wicket.core.util.Components;
import org.apache.wicket.IGenericComponent;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * A special {@link MetaTag} that adds a TODO
 *
 * @author David Beer
 */
public class MobileViewportMetaTag extends WebMarkupContainer implements IGenericComponent<String> {

    private final IModel<String> name;
    private String width;
    private String initialScale;
    private String maximumScale;
    private String userScalable;

    /**
     * Default Constructor
     *
     * @param id the markup id
     */
    public MobileViewportMetaTag(final String id) {
        super(id, Model.of(""));
        this.name = Model.of("viewport");
    }

    /**
     * @return the name of this meta tag
     */
    public String name() {
        return name.getObject();
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = "width=" + width;
        if(content() != null && content().length() > 1) {
            this.setModelObject(this.getModelObject().concat(", " + this.width));
        } else {
            this.setModelObject(this.width);
        }
    }

    public String getInitialScale() {
        return initialScale;
    }

    public void setInitialScale(String value) {
        this.initialScale = value;
        if (content() != null && content().length() > 1) {
            this.setModelObject(this.getModelObject().concat(", " + this.initialScale));
        } else {
            this.setModelObject(this.initialScale);
        }
    }

    public String getMaximumScale() {
        return maximumScale;
    }

    public void setMaximumScale(String maximumScale) {
        this.maximumScale = maximumScale;
        if (content() != null && content().length() > 1) {
            this.setModelObject(this.getModelObject().concat(", " + this.maximumScale));
        } else {
            this.setModelObject(this.maximumScale);
        }
    }

    public String getUserScalable() {
        return userScalable;
    }

    public void setUserScalable(String userScalable) {
        this.userScalable = userScalable;
        if (content() != null && content().length() > 1) {
            this.setModelObject(this.content().concat(", " + this.userScalable));
        } else {
            this.setModelObject(this.userScalable);
        }
    }

    /**
     * @return the content of this meta tag
     */
    public String content() {
        return getModelObject();
    }

    @Override
    public IModel<String> getModel() {
        return (IModel<String>) getDefaultModel();
    }

    @Override
    public void setModel(IModel<String> model) {
        setDefaultModel(model);
    }

    @Override
    public void setModelObject(String object) {
        setDefaultModelObject(object);
    }

    @Override
    public String getModelObject() {
        return getDefaultModelObjectAsString();
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Components.assertTag(this, tag, "meta");

        tag.put("name", name());
        tag.put("content", content());
    }


}
