package de.agilecoders.wicket.core.markup.html.bootstrap.html;

import de.agilecoders.wicket.core.util.Components;
import de.agilecoders.wicket.jquery.Generics2;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.List;

import static de.agilecoders.wicket.jquery.Strings2.nullToEmpty;

/**
 * A simple meta tag component.
 *
 * @author miha
 */
public class MetaTag extends WebMarkupContainer {
    // @see http://www.w3schools.com/tags/att_meta_http_equiv.asp
    private static final List<String> HTTP_EQUIV_NAMES = Generics2.newArrayList(
            "content-type", "expires", "refresh", "pragma", "cache-control",
            "content-language", "set-cookie", "PICS-Label", "content-script-type",
            "content-style-type", "last-modified", "date", "location",
            "window-target"
    );

    private static final String ATTRIBUTE_NAME_DEFAULT = "name";
    private static final String ATTRIBUTE_NAME_HTTPEQUIV = "http-equiv";
    private static final String ATTRIBUTE_NAME_CONTENT = "content";

    /**
     * All possible meta tag types
     */
    public enum Type {
        Detect, Default, HttpEquiv
    }

    private final IModel<String> name;
    private final IModel<String> content;
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
        this(id, Model.<String>of(name));
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
        this(id, name, Model.<String>of(""));
    }

    /**
     * Construct.
     *
     * @param id      the wicket markup id
     * @param name    the name of this meta tag
     * @param content the content of this meta tag
     */
    public MetaTag(String id, String name, String content) {
        this(id, Model.<String>of(name), Model.<String>of(content));
    }

    /**
     * Construct.
     *
     * @param id      the wicket markup id
     * @param name    the name of this meta tag
     * @param content the content of this meta tag
     */
    public MetaTag(String id, IModel<String> name, IModel<String> content) {
        super(id);

        this.name = name;
        this.content = content;
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
    public Type type() {
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
        return content.getObject();
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
        this.content.setObject(content);
        return this;
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Components.assertTag(this, tag, "meta");

        final String nameAttribute = Type.Default.equals(type()) ? ATTRIBUTE_NAME_DEFAULT : ATTRIBUTE_NAME_HTTPEQUIV;

        tag.put(nameAttribute, name());
        tag.put(ATTRIBUTE_NAME_CONTENT, content());
    }
}
