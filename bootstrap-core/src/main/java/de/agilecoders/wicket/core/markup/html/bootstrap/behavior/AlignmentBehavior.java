package de.agilecoders.wicket.core.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;

/**
 * User: bcousin
 * add boostrap class for alignment
 */
public class AlignmentBehavior extends BootstrapBaseBehavior {
    public enum Alignement {
        RIGHT("pull-right"),
        LEFT("pull-left"),
        NONE("");
        private String className;

        Alignement(final String className) {
            this.className = className;
        }
    }

    private IModel<Alignement> alignement;

    public AlignmentBehavior(final IModel<Alignement> alignment) {
        this.alignement = alignment;
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);
        Attributes.addClass(tag, alignement.getObject().className);
    }
}
