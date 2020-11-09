package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.col.SpanType;
import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.FeedbackMessages;
import org.apache.wicket.markup.ComponentTag;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * An {@link InputBehavior} controls the size of an input tag.
 *
 * @author miha
 */
public class InputBehavior extends BootstrapBaseBehavior {

    /**
     * Holder class for all possible input element height sizes
     */
    public enum Size implements ICssClassNameProvider {
        Small("sm"), Medium("md"), Large("lg");

        private final String cssName;

        Size(String cssName) {
            this.cssName = cssName;
        }

        @Override
        public String cssClassName() {
            return this == Medium ? "" : "form-control-" + cssName;
        }

    }

    /**
     * Specifies the height of the input field
     */
    private ICssClassNameProvider heightSize;

    /**
     * Specifies the width of the input field by
     * surrounding it with a &lt;div class="col-xx-yy"&gt;
     */
    private SpanType columnSize;

    /**
     * Construct. Uses {@link Size#Medium} as default size.
     */
    public InputBehavior() {
        this(null, null);
    }

    /**
     * Construct.
     *
     * @param columnSize the column size of input tag.
     */
    public InputBehavior(final SpanType columnSize) {
        this(null, columnSize);
    }

    /**
     * Construct.
     *
     * @param heightSize the height size of input tag.
     */
    public InputBehavior(final Size heightSize) {
        this(heightSize, null);
    }

    public InputBehavior(final Size heightSize, final SpanType columnSize) {
        this.heightSize = heightSize;
        this.columnSize = columnSize;
    }

    /**
     * sets the size of input tag
     *
     * @param spanType the column size to use
     * @return this instance for chaining
     */
    public InputBehavior size(final SpanType spanType) {
        this.columnSize = spanType;
        return this;
    }

    /**
     * sets the size of input tag
     *
     * @param heightSize the height size to use
     * @return this instance for chaining
     */
    public InputBehavior size(final Size heightSize) {
        this.heightSize = heightSize;
        return this;
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        if (heightSize != null ) {
            Attributes.addClass(tag, heightSize.cssClassName());
        }

        Attributes.addClass(tag, "form-control");

        final FeedbackMessages messages = component.getFeedbackMessages();
        if (!messages.isEmpty()) {
            Attributes.addClass(tag, toClassName(getWorstMessage(messages)));
        }
    }

    @Override
    public void beforeRender(Component component) {
        super.beforeRender(component);

        if (columnSize != null ) {
            component.getResponse().write("<div class=\"" + columnSize.cssClassName() + "\">");
        }
    }

    @Override
    public void afterRender(Component component) {
        super.afterRender(component);

        if (columnSize != null ) {
            component.getResponse().write("</div>");
        }
    }

    /**
     * ordered list of all feedback message types.
     */
    private static final List<Integer> messageTypes = Arrays.asList(
            FeedbackMessage.FATAL, FeedbackMessage.ERROR, FeedbackMessage.WARNING, FeedbackMessage.SUCCESS,
            FeedbackMessage.INFO, FeedbackMessage.DEBUG, FeedbackMessage.UNDEFINED
    );

    /**
     * @return new function that transforms a {@link FeedbackMessage} to a css class name
     */
    protected Function<FeedbackMessage, String> newFeedbackMessageToCssClassNameTransformer() {
        return new FeedbackMessageToCssClassNameTransformer();
    }

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
                case FeedbackMessage.ERROR:
                case FeedbackMessage.WARNING: return "is-invalid";
                case FeedbackMessage.INFO:
                case FeedbackMessage.SUCCESS: return "is-valid";
                default: return "";
            }
        }
    }
}
