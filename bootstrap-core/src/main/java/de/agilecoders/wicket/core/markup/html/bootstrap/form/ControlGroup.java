package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import com.google.common.base.Function;
import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.core.util.Components;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessages;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IFormModelUpdateListener;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.iterator.ComponentHierarchyIterator;
import org.apache.wicket.util.string.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Simple form control group that is able to show a label, help text and feedback
 * message additional to wrapped form element.
 *
 * @author miha
 */
public class ControlGroup extends Border implements IFormModelUpdateListener {

    private final Component label;
    private final Component help;
    private final Component feedback;
    private final Model<String> stateClassName;
    private final Function<FeedbackMessage, String> feedbackMessageToCssClassName;


    /**
     * Construct.
     *
     * @param id the wicket component id
     */
    public ControlGroup(final String id) {
        this(id, Model.of(""), Model.of(""));
    }

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

        this.label = newLabel("label", label);
        this.help = newHelpLabel("help", help);
        this.feedback = newFeedbackMessageContainer("error");
        this.feedbackMessageToCssClassName = newFeedbackMessageToCssClassNameTransformer();

        stateClassName = Model.of("");

        addToBorder(this.label, this.help, this.feedback);
    }

    /**
     * @return new function that transforms a {@link FeedbackMessage} to a css class name
     */
    protected Function<FeedbackMessage, String> newFeedbackMessageToCssClassNameTransformer() {
        return new FeedbackMessageToCssClassNameTransformer();
    }

    /**
     * creates a new label
     *
     * @param id the component id
     * @param model the content model for this component
     * @return new label
     */
    protected Component newLabel(final String id, final IModel<String> model) {
        return new Label(id, model);
    }

    /**
     * creates a new container for a feedback message
     *
     * @param id the component id
     * @return new feedback message container
     */
    protected Component newFeedbackMessageContainer(final String id) {
        return new Label(id, new Model<String>());
    }

    /**
     * creates a new help label that contains a help message for the form field. This field
     * will be set to invisible if there is no content.
     *
     * @param id    the component id
     * @param model the content model for this component
     * @return new help label
     */
    protected Component newHelpLabel(final String id, final IModel<String> model) {
        return new Label(id, model);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "div");
        Attributes.addClass(tag, "control-group", stateClassName.getObject());
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final List<FormComponent<?>> formComponents = findFormComponents();
        final int size = formComponents.size();

        addOutputMarkupId(formComponents);

        if (size > 0) {
            FormComponent<?> formComponent = formComponents.get(size - 1);
            label.add(new AttributeModifier("for", formComponent.getMarkupId()));

            if (formComponent.getLabel() != null && !Strings.isEmpty(formComponent.getLabel().getObject())) {
                label.setDefaultModel(formComponent.getLabel());
            }
        }
    }

    /**
     * sets the output markup id flag to true for all given formComponents. This is necessary to
     * reference them in a "for" attribute on client side.
     *
     * @param formComponents the form components to add the output markup id
     */
    protected void addOutputMarkupId(List<FormComponent<?>> formComponents) {
        for (final FormComponent<?> fc : formComponents) {
            fc.setOutputMarkupId(true);
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        // set all components visible
        Components.show(help, label, feedback);

        // clear feedback message and current state
        stateClassName.setObject("");
        feedback.setDefaultModelObject("");

        final List<FormComponent<?>> formComponents = findFormComponents();
        for (final FormComponent<?> fc : formComponents) {
            final FeedbackMessages messages = fc.getFeedbackMessages();

            if (!messages.isEmpty()) {
                final FeedbackMessage worstMessage = getWorstMessage(messages);
                worstMessage.markRendered();

                stateClassName.setObject(toClassName(worstMessage));
                feedback.setDefaultModelObject(worstMessage.getMessage());

                break; // render worst message of first found child component with feedback message
            }
        }

        Components.hideIfModelIsEmpty(help);
        Components.hideIfModelIsEmpty(label);
        Components.hideIfModelIsEmpty(feedback);
    }

    /**
     * @return all form components that are assigned to this {@link ControlGroup}
     */
    private List<FormComponent<?>> findFormComponents() {
        final ComponentHierarchyIterator it = getBodyContainer().visitChildren(FormComponent.class);

        final List<FormComponent<?>> components = new ArrayList<FormComponent<?>>();
        while (it.hasNext()) {
            components.add((FormComponent<?>) it.next());
        }

        return components;
    }

    /**
     * ordered list of all feedback message types.
     */
    private static final List<Integer> messageTypes = Arrays.asList(
            FeedbackMessage.FATAL, FeedbackMessage.ERROR, FeedbackMessage.WARNING, FeedbackMessage.SUCCESS,
            FeedbackMessage.INFO, FeedbackMessage.DEBUG, FeedbackMessage.UNDEFINED
    );

    /**
     * returns the worst message that is available.
     *
     * @param messages all current feedback messages
     * @return worst possible message or null
     */
    private FeedbackMessage getWorstMessage(final FeedbackMessages messages) {
        for (final Integer messageType : messageTypes) {
            final FeedbackMessage ret = messages.first(messageType);

            if (ret != null) {
                return ret;
            }
        }

        return messages.first();
    }

    /**
     * transforms a given feedback message to its css class name representation.
     *
     * @param message the feedback message to use for css class name detection
     * @return the css class name representation of given message
     */
    private String toClassName(final FeedbackMessage message) {
         return feedbackMessageToCssClassName.apply(message);
    }

    @Override
    public void updateModel() {
        final List<FormComponent<?>> formComponents = findFormComponents();

        for (final FormComponent<?> formComponent : formComponents) {
            if (formComponent.isEnabled()) {
                formComponent.updateModel();
            }
        }
    }

    /**
     * Transforms a {@link FeedbackMessage} to a css class name.
     */
    public static class FeedbackMessageToCssClassNameTransformer implements Function<FeedbackMessage, String> {
        @Override
        public String apply(final FeedbackMessage message) {
            if (message == null) {
                return "";
            }

            switch (message.getLevel()) {
                case FeedbackMessage.FATAL:
                case FeedbackMessage.ERROR: return "error";
                case FeedbackMessage.WARNING: return "warning";
                case FeedbackMessage.SUCCESS: return "success";
                case FeedbackMessage.INFO: return "info";
                default: return "";
            }
        }
    }
}