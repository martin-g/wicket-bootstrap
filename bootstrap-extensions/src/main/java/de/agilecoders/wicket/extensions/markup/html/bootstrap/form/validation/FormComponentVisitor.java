package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.validation;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

/**
 * <h1>Component visitor.</h1>
 * <p>add component {@link FeedbackMessagePopupBehavior}.</p>
 *
 * @author Alexey Volkov
 * @author Osipov Anton
 */
public class FormComponentVisitor implements IVisitor<Component, String> {

    /**
     * Add visitor instance to all children's.<br>
     *
     * @param parentComponent parent component
     * @param attribute name of tag attribute
     */
    public static void add(MarkupContainer parentComponent, String attribute) {
        parentComponent.visitChildren(new FormComponentVisitor(attribute));
    }

    private FeedbackMessagePopupBehavior popup;

    /**
     * @param attribute name of tag attribute
     */
    public FormComponentVisitor(String attribute) {
        this.popup = new FeedbackMessagePopupBehavior(attribute);
    }

    @Override
    public void component(Component component, IVisit<String> visit) {
        if (component instanceof FormComponent<?>) {
            component.add(popup);
        }
    }
}
