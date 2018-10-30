package de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Args;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.AlignmentBehavior.Alignment;
import de.agilecoders.wicket.core.util.Attributes;

/**
* Behavior for alignment of the DropDownButton and SplitButton to align 
* the DropDown menu to the right.
*
* @author helmut
**/

public class DropDownAlignmentBehavior extends BootstrapBaseBehavior {

	 /**
     * Alignment for the dropdown of a DropDownButton
     */
    public enum Alignment implements ICssClassNameProvider {
        RIGHT("dropdown-menu-right"),
        NONE("");
        private String className;

        /**
         * Construct.
         *
         * @param className the css class name
         */
        Alignment(final String className) {
            this.className = className;
        }

        @Override
        public String cssClassName() {
            return className;
        }
    }

    private final IModel<Alignment> alignment;

    /**
     * Construct.
     *
     * @param alignment the alignment to use
     */
    public DropDownAlignmentBehavior(final IModel<Alignment> alignment) {
        this.alignment = Args.notNull(alignment, "alignment");
    }

    /**
     * Construct.
     *
     * @param alignment the alignment to use
     */
    public DropDownAlignmentBehavior(final Alignment alignment) {
        this(Model.of(Args.notNull(alignment, "alignment")));
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        // remove existing alignment class names to allow switching alignment during ajax updates
        Attributes.removeClass(tag, Alignment.RIGHT.cssClassName());

        Alignment value = alignment.getObject();
        switch (value) {
            case RIGHT:
            	Attributes.addClass(tag, value.cssClassName());
            default:
                break;
        }
    }

}
