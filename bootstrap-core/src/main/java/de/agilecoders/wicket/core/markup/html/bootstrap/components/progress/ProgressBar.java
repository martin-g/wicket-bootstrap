package de.agilecoders.wicket.core.markup.html.bootstrap.components.progress;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Args;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;

/**
 * TODO: document
 *
 * @author miha
 */
public class ProgressBar extends GenericPanel<Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(ProgressBar.class);

    private static final int MIN = 0;
    private static final int MAX = 100;

    public enum Type implements ICssClassNameProvider {
        DEFAULT, INFO, SUCCESS, WARNING, DANGER;

        public String cssClassName() {
            return equals(DEFAULT) ? "" : "progress-bar-" + name().toLowerCase();
        }
    }

    private final RepeatingView stacks;

    private boolean active = false;

    private boolean striped = false;

    public ProgressBar(String id) {
        this(id, Model.of(MIN));
    }

    public ProgressBar(String id, IModel<Integer> model) {
        super(id, model);

        stacks = new RepeatingView("stacks");
        add(stacks);
    }

    public ProgressBar addStacks(Stack... _stacks) {
        Args.notNull(_stacks, "_stacks");

        for (Stack stack : _stacks) {
            stacks.add(stack);
        }

        return this;
    }

    protected final Component indicator() {
        return get("bar");
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
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        if (!"div".equalsIgnoreCase(tag.getName())) {
            LOG.warn("you've added a progress bar component to a non 'div' tag: {}", tag.getName());

            tag.setName("div");
        }

        Attributes.addClass(tag, "progress");

        if (active()) {
            Attributes.addClass(tag, "active");
        }

        if (striped()) {
            Attributes.addClass(tag, "progress-striped");
        }

    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(JavaScriptHeaderItem.forReference(new UploadProgressBarJavaScriptReference()));
    }

    public class Stack extends GenericPanel<Integer> {

        private final Label srOnly;

        private Type type = Type.DEFAULT;

        private boolean labeled = false;

        public Stack(IModel<Integer> model) {
            super(stacks.newChildId(), model);

            srOnly = new Label("srOnly", new AbstractReadOnlyModel<String>() {
                @Override
                public String getObject() {
                    return String.format("%s%%", Stack.this.getModelObject());
                }
            });
            add(srOnly);
        }

        public Type type() {
            return type;
        }

        public Stack type(Type type) {
            this.type = type;
            return this;
        }

        public boolean labeled() {
            return labeled;
        }

        public Stack labeled(boolean labeled) {
            this.labeled = labeled;
            return this;
        }

        @Override
        protected void onConfigure() {
            super.onConfigure();

            if (labeled()) {
                srOnly.setRenderBodyOnly(true);
            }
        }

        @Override
        protected void onComponentTag(ComponentTag tag) {
            super.onComponentTag(tag);

            Integer value = getModelObject();
            Attributes.set(tag, "style", String.format("width: %s%%", value));
            Attributes.set(tag, "aria-valuenow", String.valueOf(value));
            Attributes.set(tag, "aria-valuemin", String.valueOf(MIN));
            Attributes.set(tag, "aria-valuemax", String.valueOf(MAX));

            if (!Type.DEFAULT.equals(type)) {
                Attributes.addClass(tag, type().cssClassName());
            }
        }
    }
}
