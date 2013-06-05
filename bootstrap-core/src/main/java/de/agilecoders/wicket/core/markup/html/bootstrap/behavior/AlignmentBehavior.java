package de.agilecoders.wicket.core.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;

/**
 * Alignment behavior to position components
 *
 * @author bcousin
 */
public class AlignmentBehavior extends BootstrapBaseBehavior {
    public enum Alignment {
        RIGHT("pull-right"),
        LEFT("pull-left"),
        NONE("");
        private String className;

        Alignment(final String className) {
            this.className = className;
        }
    }

    private IModel<Alignment> alignement;

    public AlignmentBehavior(final IModel<Alignment> alignment) {
        this.alignement = alignment;
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);
        Attributes.removeClass(tag, Alignment.RIGHT.className, Alignment.LEFT.className);
        switch (alignement.getObject()) {
            case RIGHT:
            case LEFT:
                Attributes.addClass(tag, alignement.getObject().className);
        }
    }
}
