package de.agilecoders.wicket.core.markup.html.bootstrap.components.progress;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.UploadProgressBarJavaScriptReference;
import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ResourceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: document
 *
 * @author miha
 */
public class UploadProgressBar extends org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar {
    private static final Logger LOG = LoggerFactory.getLogger(UploadProgressBar.class);

    private static final int MIN = 0;
    private static final int MAX = 100;

    public enum Type implements ICssClassNameProvider {
        DEFAULT, INFO, SUCCESS, WARNING, DANGER;

        public String cssClassName() {
            return equals(DEFAULT) ? "" : "progress-bar-" + name().toLowerCase();
        }
    }

    private boolean active = false;
    private Type type = Type.DEFAULT;
    private boolean striped = false;

    public UploadProgressBar(String id) {
        this(id, Model.of(MIN));
    }

    public UploadProgressBar(String id, IModel<Integer> model) {
        this(id, new Form<Void>("dummy"), null, model);
    }

    public UploadProgressBar(String id, Form<?> form, FileUploadField fileUploadField, IModel<Integer> model) {
        super(id, form, fileUploadField);

        setRenderBodyOnly(false);

        setDefaultModel(model);
    }

    @Override
    protected ResourceReference getCss() {
        return null;
    }

    protected final Component indicator() {
        return get("bar");
    }

    public boolean striped() {
        return striped;
    }

    public UploadProgressBar striped(boolean value) {
        striped = value;
        return this;
    }

    public boolean active() {
        return active;
    }

    public UploadProgressBar active(boolean value) {
        active = value;
        return this;
    }

    public final boolean complete() {
        return value() == MAX;
    }

    public Type type() {
        return type;
    }

    public UploadProgressBar type(Type type) {
        this.type = type;
        return this;
    }

    @Override
    protected MarkupContainer newBarComponent(String id) {
        return new WebMarkupContainer(id) {
            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);

                if (!Type.DEFAULT.equals(type)) {
                    Attributes.addClass(tag, type().cssClassName());
                }

                tag.put("style", createStyleValue().getObject());
            }
        };
    }

    @Override
    protected MarkupContainer newStatusComponent(String id) {
        MarkupContainer status = super.newStatusComponent(id);
        status.setVisible(false);
        return status;
    }

    private IModel<String> createStyleValue() {
        return Model.of("width: " + value() + "%");
    }

    public UploadProgressBar value(IModel<Integer> value) {
        setDefaultModel(value);
        return this;
    }

    public UploadProgressBar value(Integer value) {
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
}
