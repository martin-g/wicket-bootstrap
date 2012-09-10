package de.agilecoders.wicket.markup.html.bootstrap.form;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessages;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IFormModelUpdateListener;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.iterator.ComponentHierarchyIterator;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.util.Components;

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
    private final Model<String> stateClassName;

    /**
     * Construct.
     *
     * @param id    the wicket component id
     * @param label the label
     */
    public ControlGroup(final String id, final IModel<String> label) {
        this(id, label, Model.of(""));
    }

    /**
     * Construct.
     *
     * @param id the wicket component id
     */
    public ControlGroup(final String id, final IModel<String> label, final IModel<String> help) {
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

    public ControlGroup label(final IModel<String> label) {
        this.label.setDefaultModel(label);
        return this;
    }

    public ControlGroup help(final IModel<String> help) {
        this.help.setDefaultModel(help);
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final List<FormComponent<?>> formComponents = findFormComponents();
        for (final FormComponent<?> fc : formComponents) {
            fc.setOutputMarkupId(true);
            label.add(new AttributeModifier("for", fc.getMarkupId()));
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        Components.show(help, label, error);

        stateClassName.setObject("");
        error.setDefaultModelObject("");

        final List<FormComponent<?>> formComponents = findFormComponents();
        for (final FormComponent<?> fc : formComponents) {
        	final FeedbackMessages messages = fc.getFeedbackMessages();
            if (!messages.isEmpty()) {
                final FeedbackMessage worstMessage = getWorstMessage(messages);
                worstMessage.markRendered();

                stateClassName.setObject(toClassName(worstMessage));
                error.setDefaultModelObject(worstMessage.getMessage());

                break; // render worst message of first found child component with feedback message
            }
        }

        Components.hideIfModelIsEmpty(help);
        Components.hideIfModelIsEmpty(label);
        Components.hideIfModelIsEmpty(error);
    }

    private List<FormComponent<?>> findFormComponents() {
        final ComponentHierarchyIterator it = getBodyContainer().visitChildren(FormComponent.class);

        final List<FormComponent<?>> components = Lists.newArrayList();
        while (it.hasNext()) {
        	components.add((FormComponent<?>) it.next());
        }

        return components;
    }

    private FeedbackMessage getWorstMessage(final FeedbackMessages messages) {
    	FeedbackMessage ret;
    	ret = messages.first(FeedbackMessage.FATAL);
    	if (ret != null) {
    		return ret;
    	}
    	ret = messages.first(FeedbackMessage.ERROR);
    	if (ret != null) {
    		return ret;
    	}
    	ret = messages.first(FeedbackMessage.WARNING);
    	if (ret != null) {
    		return ret;
    	}
    	ret = messages.first(FeedbackMessage.SUCCESS);
    	if (ret != null) {
    		return ret;
    	}
    	ret = messages.first(FeedbackMessage.INFO);
    	if (ret != null) {
    		return ret;
    	}
    	ret = messages.first(FeedbackMessage.DEBUG);
    	if (ret != null) {
    		return ret;
    	}
    	ret = messages.first(FeedbackMessage.UNDEFINED);
    	if (ret != null) {
    		return ret;
    	}
    	return messages.first();
    }

    
    private String toClassName(final FeedbackMessage message) {
    	if (message.isLevel(FeedbackMessage.ERROR)) {
    		return "error";
    	}
    	if (message.isLevel(FeedbackMessage.WARNING)) {
    		return "warning";
    	}
    	if (message.isLevel(FeedbackMessage.SUCCESS)) {
    		return "success";
    	}
    	if (message.isLevel(FeedbackMessage.INFO)) {
    		return "info";
    	}
    	return "";
    }
    
    @Override
    public void updateModel() {
        final List<FormComponent<?>> formComponents = findFormComponents();
        for (final FormComponent<?> formComponent : formComponents) {
            formComponent.updateModel();
        }
    }
}