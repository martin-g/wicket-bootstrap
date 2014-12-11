package de.agilecoders.wicket.core.markup.html.bootstrap.html;

import de.agilecoders.wicket.core.util.Components;
import de.agilecoders.wicket.jquery.util.Generics2;
import org.apache.wicket.IGenericComponent;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.List;

import static de.agilecoders.wicket.jquery.util.Strings2.nullToEmpty;

/**
 * A simple meta tag component.
 *
 * <p>The value of the <em>content</em> attribute is stored as model object for this component</p>
 *
 * @author miha
 */
public class MetaTag extends WebMarkupContainer implements IGenericComponent<String> {
    // @see http://www.w3schools.com/tags/att_meta_http_equiv.asp
    private static final List<String> HTTP_EQUIV_NAMES = Generics2.newArrayList(
            "content-type", "expires", "refresh", "pragma", "cache-control",
            "content-language", "set-cookie", "PICS-Label", "content-script-type",
            "content-style-type", "last-modified", "date", "location",
            "window-target"
    );

    private static final String ATTRIBUTE_NAME_DEFAULT = "name";
    private static final String ATTRIBUTE_NAME_HTTPEQUIV = "http-equiv";
    private static final String ATTRIBUTE_NAME_PROPERTY = "property";
    private static final String ATTRIBUTE_NAME_CONTENT = "content";

    /**
     * All possible meta tag types
     */
    public enum Type {
        Detect(""), Default(ATTRIBUTE_NAME_DEFAULT), HttpEquiv(ATTRIBUTE_NAME_HTTPEQUIV), Property(ATTRIBUTE_NAME_PROPERTY);

        private final String nameAttribute;

        private Type(String nameAttribute) {
            this.nameAttribute = nameAttribute;
        }

        @Override
        public String toString() {
            return nameAttribute;
        }
    }

    private final IModel<String> name;
    private Type type;

    /**
     * Construct.
     *
     * @param id   the wicket markup id
     * @param name the name of this meta tag
     * @deprecated please use constructor with content
     */
    @Deprecated
    public MetaTag(final String id, final String name) {
        this(id, Model.of(name));
    }

    /**
     * Construct.
     *
     * @param id   the wicket markup id
     * @param name the name of this meta tag
     * @deprecated please use constructor with content
     */
    @Deprecated
    public MetaTag(String id, IModel<String> name) {
        this(id, name, Model.of(""));
    }

    /**
     * Construct.
     *
     * @param id      the wicket markup id
     * @param name    the name of this meta tag
     * @param content the content of this meta tag
     */
    public MetaTag(String id, String name, String content) {
        this(id, Model.of(name), Model.of(content));
    }

    /**
     * Construct.
     *
     * @param id      the wicket markup id
     * @param name    the name of this meta tag
     * @param content the content of this meta tag
     */
    public MetaTag(String id, IModel<String> name, IModel<String> content) {
        super(id, content);

        this.name = name;
        this.type = Type.Detect;
    }

    /**
     * detects the type of this meta tag according to its name. Some special
     * meta-tags uses "http-equiv" instead of "name" as attribute key for their
     * name.
     *
     * @param name the name of this meta tag
     * @return {@link Type#HttpEquiv} for all names that are listed in {@link #HTTP_EQUIV_NAMES} else {@link Type#Default}
     */
    private Type detect(String name) {
        if (HTTP_EQUIV_NAMES.contains(nullToEmpty(name).toLowerCase())) {
            return Type.HttpEquiv;
        } else if (Type.Property.nameAttribute.equalsIgnoreCase(name)) {
            return Type.Property;
        }

        return Type.Default;
    }

    /**
     * sets the type of meta tag. This is useful if you've a special meta-tag that
     * is not listed in {@link #HTTP_EQUIV_NAMES} but it should use "http-equiv".
     *
     * @param type the type to use
     * @return this instance for chaining.
     */
    public MetaTag type(Type type) {
        this.type = type;
        return this;
    }

    /**
     * @return the type of this meta tag.
     */
    public final Type type() {
        if (Type.Detect.equals(type)) {
            type = detect(name());
        }

        return type;
    }

    /**
     * @return the name of this meta tag
     */
    public String name() {
        return name.getObject();
    }

    /**
     * @return the content of this meta tag
     */
    public String content() {
        return getModelObject();
    }

    /**
     * sets the content of meta tag
     *
     * @param content content as string
     * @return this instance for chaining
     * @deprecated please use constructor instead.
     */
    @Deprecated
    public MetaTag content(String content) {
        this.setModelObject(content);
        return this;
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Components.assertTag(this, tag, "meta");

        String nameAttribute = type().nameAttribute;

        tag.put(nameAttribute, name());
        tag.put(ATTRIBUTE_NAME_CONTENT, content());
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
}
