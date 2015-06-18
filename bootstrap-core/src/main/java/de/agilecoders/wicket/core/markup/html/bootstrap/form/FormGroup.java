package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import com.google.common.base.Function;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
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
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Simple form control group that is able to show a label, help text and feedback
 * message additional to wrapped form element.
 *
 * @author miha
 */
public class FormGroup extends Border {

    /**
     * Holder class for all possible form group sizes
     */
    public enum Size implements ICssClassNameProvider {
        Small("sm"), Large("lg");

        private final String cssName;

        Size(String cssName) {
            this.cssName = cssName;
        }

        @Override
        public String cssClassName() {
            return "form-group-" + cssName;
        }

    }

    private Component label;
    private Component help;
    private Component feedback;
    private String stateClassName;
    private boolean useFormComponentLabel = true;
    private Size size;
    private final IModel<String> labelModel;
    private final IModel<String> helpModel;


    /**
     * Construct.
     *
     * @param id the wicket component id
     */
    public FormGroup(final String id) {
        this(id, Model.of(""), Model.of(""));
    }

    /**
     * Construct.
     *
     * @param id    the wicket component id
     * @param label the label
     */
    public FormGroup(final String id, final IModel<String> label) {
        this(id, label, Model.of(""));
    }

    /**
     * Construct.
     *
     * @param id the wicket component id
     */
    public FormGroup(final String id, final IModel<String> label, final IModel<String> help) {
        super(id, Model.of(""));

        this.labelModel = label;
        this.helpModel = help;
        this.stateClassName = "";
    }

    /**
     * @param value whether to use form components label as control group label or not
     * @return this instance for chaining
     */
    public FormGroup useFormComponentLabel(boolean value) {
        this.useFormComponentLabel = value;
        return this;
    }

    /**
     * sets the size of form-group
     *
     * @param size the size to use
     * @return this instance for chaining
     */
    public FormGroup size(final Size size) {
        this.size = size;
        return this;
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
        Attributes.addClass(tag, "form-group", stateClassName);
        if (size != null) {
            Attributes.addClass(tag, size.cssClassName());
        }
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        this.label = newLabel("label", labelModel);
        this.help = newHelpLabel("help", helpModel);
        this.feedback = newFeedbackMessageContainer("error");
        addToBorder(this.label, this.help, this.feedback);


        final List<FormComponent<?>> formComponents = findFormComponents();
        final int size = formComponents.size();

        if (size > 0) {
            addOutputMarkupId(formComponents);

            final FormComponent<?> formComponent = formComponents.get(size - 1);
            label.add(new AttributeModifier("for", formComponent.getMarkupId()));

            if (useFormComponentLabel) {
                label.setDefaultModel(new LoadableDetachableModel<String>() {
                    @Override
                    protected String load() {
                        if (formComponent.getLabel() != null && !Strings.isEmpty(formComponent.getLabel().getObject())) {
                            return formComponent.getLabel().getObject();
                        } else {
                            String text = formComponent.getDefaultLabel("wicket:unknown");
                            if (!"wicket:unknown".equals(text) && !Strings.isEmpty(text)) {
                                return text;
                            } else {
                                return labelModel.getObject();
                            }
                        }
                    }
                });
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
        stateClassName = "";
        feedback.setDefaultModelObject("");

        final List<FormComponent<?>> formComponents = findFormComponents();
        for (final FormComponent<?> fc : formComponents) {
            final FeedbackMessages messages = fc.getFeedbackMessages();

            if (!messages.isEmpty()) {
                final FeedbackMessage worstMessage = getWorstMessage(messages);
                worstMessage.markRendered();

                stateClassName = toClassName(worstMessage);
                feedback.setDefaultModelObject(worstMessage.getMessage());

                break; // render worst message of first found child component with feedback message
            }
        }

        Components.hideIfModelIsEmpty(help);
        Components.hideIfModelIsEmpty(label);
        Components.hideIfModelIsEmpty(feedback);
    }

    /**
     * @return all form components that are assigned to this {@link FormGroup}
     */
    List<FormComponent<?>> findFormComponents() {
        final List<FormComponent<?>> components = new ArrayList<>();
        getBodyContainer().visitChildren(FormComponent.class, new IVisitor<FormComponent, Void>() {
            @Override
            public void component(FormComponent formComponent, IVisit<Void> visit)
            {
                components.add(formComponent);
            }
        });

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
         return newFeedbackMessageToCssClassNameTransformer().apply(message);
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
                case FeedbackMessage.ERROR: return "has-error";
                case FeedbackMessage.WARNING: return "has-warning";
                case FeedbackMessage.INFO:
                case FeedbackMessage.SUCCESS: return "has-success";
                default: return "";
            }
        }
    }
}
