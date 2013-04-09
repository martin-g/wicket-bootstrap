package de.agilecoders.wicket.core.markup.html.bootstrap.components;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameModifier;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Components;
import de.agilecoders.wicket.core.util.Generics2;

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
    private static final int MIN = 0;
    private static final int MAX = 100;


    private Component indicator;

    public enum Type implements ICssClassNameProvider {
        DEFAULT, INFO, SUCCESS, WARNING, DANGER;

        public String cssClassName() {
            return equals(DEFAULT) ? "" : "progress-" + name().toLowerCase();
        }

        public CssClassNameAppender newCssClassNameModifier() {
            return new CssClassNameAppender(cssClassName());
        }
    }

    private boolean active = false;
    private Type type = Type.DEFAULT;
    private boolean striped = false;

    public ProgressBar(String id) {
        super(id, Model.of(MIN));

        commonInit();
    }

    public ProgressBar(String id, IModel<Integer> model) {
        super(id, model);

        commonInit();
    }

    private void commonInit() {
        indicator = newIndicator("indicator");

        add(indicator);
    }

    protected final Component indicator() {
        return indicator;
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

    public final boolean complete() {
        return value() == MAX;
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
        return Math.max(Math.min((Integer) getDefaultModelObject(), MAX), MIN);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        add(new CssClassNameModifier(cssClassNames()));
    }

    private List<String> cssClassNames() {
        List<String> classNames = Generics2.newArrayList(
                "progress",
                type().cssClassName()
        );

        if (active()) {
            classNames.add("active");
        }

        if (striped()) {
            classNames.add("progress-striped");
        }

        return classNames;
    }


    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Components.assertTag(this, tag, "div");
    }
}
