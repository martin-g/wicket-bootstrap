package de.agilecoders.wicket.markup.html.bootstrap.components;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ProgressBar extends Panel {

    public enum Type implements CssClassNameProvider {
        DEFAULT, INFO, SUCCESS, WARNING, DANGER;

        public String cssClassName() {
            return equals(DEFAULT) ? "" : "progress-" + name().toLowerCase();
        }

        public CssClassNameAppender newCssClassNameAppender() {
            return new CssClassNameAppender(cssClassName());
        }
    }

    private boolean active = false;
    private Type type = Type.DEFAULT;
    private boolean striped = false;

    public ProgressBar(String id) {
        super(id, Model.of(0));

        commonInit();
    }

    public ProgressBar(String id, IModel<Integer> model) {
        super(id, model);

        commonInit();
    }

    private void commonInit() {
        Component indicator = newIndicator("indicator");

        add(indicator);
    }

    public boolean striped() {
        return striped;
    }

    public ProgressBar striped(boolean value) {
        striped = value;
        return this;
    }

    public boolean active() {
        return active;
    }

    public ProgressBar active(boolean value) {
        active = value;
        return this;
    }

    public Type type() {
        return type;
    }

    public ProgressBar type(Type type) {
        this.type = type;
        return this;
    }

    private Component newIndicator(final String markupId) {
        return new WebMarkupContainer(markupId) {
            @Override
            protected void onConfigure() {
                super.onConfigure();

                add(new AttributeModifier("style", createStyleValue()));
            }
        };
    }

    private IModel<String> createStyleValue() {
        return Model.of("width: " + value() + "%");
    }

    public ProgressBar value(IModel<Integer> value) {
        setDefaultModel(value);
        return this;
    }

    public ProgressBar value(Integer value) {
        setDefaultModelObject(value);
        return this;
    }

    public Integer value() {
        return Math.max(Math.min((Integer) getDefaultModelObject(), 100), 0);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        List<String> classNames = Lists.newArrayList(
                "progress",
                type().cssClassName()
        );

        if (active()) {
            classNames.add("active");
        }

        if(striped()) {
            classNames.add("progress-striped");
        }

        add(new CssClassNameAppender(classNames));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Components.assertTag(this, tag, "div");
    }
}
