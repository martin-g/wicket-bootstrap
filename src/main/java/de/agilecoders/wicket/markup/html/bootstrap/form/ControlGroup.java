package de.agilecoders.wicket.markup.html.bootstrap.form;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.feedback.FeedbackMessages;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IFormModelUpdateListener;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.iterator.ComponentHierarchyIterator;

import java.io.Serializable;
import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ControlGroup extends Border implements IFormModelUpdateListener {

    private final Label label;
    private final Label help;
    private final Label error;
    private CssClassNameAppender stateClassNameAppender;
    private Model<String> stateClassName;

    /**
     * Construct.
     *
     * @param id    the wicket component id
     * @param label the label
     */
    public ControlGroup(String id, IModel<String> label) {
        this(id, label, Model.of(""));
    }

    /**
     * Construct.
     *
     * @param id the wicket component id
     */
    public ControlGroup(String id, IModel<String> label, IModel<String> help) {
        super(id, Model.of(""));

        this.label = new Label("label", label);
        this.help = new Label("help", help);
        this.error = new Label("error", Model.<Serializable>of(""));

        stateClassName = Model.of("");

        add(new AssertTagNameBehavior("div"));
        add(new CssClassNameAppender("control-group"));
        add(new CssClassNameAppender(stateClassName));
        addToBorder(this.label, this.help, this.error);
    }

    public ControlGroup label(IModel<String> label) {
        this.label.setDefaultModel(label);
        return this;
    }

    public ControlGroup help(IModel<String> help) {
        this.help.setDefaultModel(help);
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        List<FormComponent<?>> formComponents = findFormComponents();
        for (FormComponent fc : formComponents) {
            fc.setOutputMarkupId(true);
            label.add(new AttributeModifier("for", fc.getMarkupId()));
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        Components.show(help, label, error);

        List<FormComponent<?>> formComponents = findFormComponents();
        for (FormComponent fc : formComponents) {
            FeedbackMessages messages = fc.getFeedbackMessages();
            if (!messages.isEmpty()) {
                stateClassName.setObject(toClassName(messages.first().getLevelAsString()));

                error.setDefaultModelObject(messages.first().getMessage());
                messages.first().markRendered();

                break;
            } else {
                stateClassName.setObject("");
                error.setDefaultModelObject("");
            }
        }

        if (formComponents.isEmpty()) {
            stateClassName.setObject("");
            error.setDefaultModelObject("");
        }

        Components.hideIfModelIsEmpty(help);
        Components.hideIfModelIsEmpty(label);
        Components.hideIfModelIsEmpty(error);
    }

    private List<FormComponent<?>> findFormComponents() {
        ComponentHierarchyIterator it = getBodyContainer().visitChildren();

        List<FormComponent<?>> components = Lists.newArrayList();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof FormComponent) {
                components.add((FormComponent) next);
            }
        }

        return components;
    }

    private String toClassName(final String level) {
        final String className;

        if (level.equalsIgnoreCase("ERROR") || level.equalsIgnoreCase("FATAL")) {
            className = "error";
        } else if (level.equalsIgnoreCase("WARNING")) {
            className = "warning";
        } else if (level.equalsIgnoreCase("SUCCESS")) {
            className = "success";
        } else {
            className = "";
        }

        return className;
    }

    @Override
    public void updateModel() {
        List<FormComponent<?>> formComponents = findFormComponents();
        for (FormComponent<?> formComponent : formComponents) {
            formComponent.updateModel();
        }
    }
}