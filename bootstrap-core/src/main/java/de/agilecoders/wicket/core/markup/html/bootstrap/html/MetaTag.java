package de.agilecoders.wicket.core.markup.html.bootstrap.html;

import de.agilecoders.wicket.core.util.Components;
import de.agilecoders.wicket.core.util.Generics2;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.List;

import static de.agilecoders.wicket.core.util.Strings2.nullToEmpty;

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

    public enum Type {
        Detect, Default, HttpEquiv
    }

    private final IModel<String> name;
    private final IModel<String> content;
    private Type type;

    public MetaTag(String id, String name) {
        this(id, Model.<String>of(name));
    }

    public MetaTag(String id, IModel<String> name) {
        this(id, name, Model.<String>of(""));
    }

    public MetaTag(String id, String name, String content) {
        this(id, Model.<String>of(name), Model.<String>of(content));
    }

    public MetaTag(String id, IModel<String> name, IModel<String> content) {
        super(id);

        this.name = name;
        this.content = content;
        this.type = Type.Detect;
    }

    private Type detect(String name) {
        if (HTTP_EQUIV_NAMES.contains(nullToEmpty(name).toLowerCase())) {
            return Type.HttpEquiv;
        }

        return Type.Default;
    }

    public Type type() {
        return type;
    }

    public MetaTag type(Type type) {
        this.type = type;
        return this;
    }

    public String name() {
        return name.getObject();
    }

    public String content() {
        return content.getObject();
    }

    public MetaTag content(String content) {
        this.content.setObject(content);
        return this;
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Components.assertTag(this, tag, "meta");
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        String nameAttribute = ATTRIBUTE_NAME_DEFAULT;

        if (Type.Detect.equals(type)) {
            nameAttribute = Type.Default.equals(detect(name())) ? ATTRIBUTE_NAME_DEFAULT : ATTRIBUTE_NAME_HTTPEQUIV;
        } else if (Type.HttpEquiv.equals(type())) {
            nameAttribute = ATTRIBUTE_NAME_HTTPEQUIV;
        }

        add(new AttributeModifier(nameAttribute, name()));
        add(new AttributeModifier(ATTRIBUTE_NAME_CONTENT, content()));
    }
}
