package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

/**
 * <h1>Component visitor.</h1>
 * <p>adds {@link FeedbackMessageBehavior} to all form components.</p>
 *
 * @author Alexey Volkov
 * @author Osipov Anton
 * @since 15.09.2014
 */
class FeedbackMessageBehaviorVisitor implements IVisitor<FormComponent<?>, Void> {

    private final FeedbackMessageBehavior feedbackBehavior;

    /**
     * @param attribute name of tag attribute
     */
    FeedbackMessageBehaviorVisitor(String attribute) {
        this.feedbackBehavior = new FeedbackMessageBehavior(attribute);
    }

    @Override
    public void component(FormComponent<?> component, IVisit<Void> visit) {
        component.add(feedbackBehavior);
    }

    /**
     * Add visitor instance to all children form components.<br>
     *
     * @param parentComponent parent component
     * @param attribute       name of tag attribute
     */
    static void add(MarkupContainer parentComponent, String attribute) {
        parentComponent.internalInitialize();
        parentComponent.visitChildren(FormComponent.class, new FeedbackMessageBehaviorVisitor(attribute));
    }
}
