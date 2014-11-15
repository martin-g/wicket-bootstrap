package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.apache.wicket.util.visit.Visits;

/**
 * <h1>Component visitor.</h1>
 * <p>adds to all FormComponents component {@link FeedbackMessageBehavior}.</p>
 *
 * @author Alexey Volkov
 * @author Osipov Anton
 */
public class FeedbackMessageBehaviorVisitor implements IVisitor<FormComponent<?>, Void> {

    private final FeedbackMessageBehavior feedbackBehavior;

    /**
     * @param attribute name of tag attribute
     */
    public FeedbackMessageBehaviorVisitor(String attribute) {
        this.feedbackBehavior = new FeedbackMessageBehavior(attribute);
    }

    @Override
    public void component(FormComponent<?> component, IVisit<Void> visit) {
        component.add(feedbackBehavior);
    }

    /**
     * Add visitor instance to all children's.<br>
     *
     * @param parentComponent parent component
     * @param attribute       name of tag attribute
     */
    public static void add(MarkupContainer parentComponent, String attribute) {
        parentComponent.visitChildren(FormComponent.class, new FeedbackMessageBehaviorVisitor(attribute));
    }

}
