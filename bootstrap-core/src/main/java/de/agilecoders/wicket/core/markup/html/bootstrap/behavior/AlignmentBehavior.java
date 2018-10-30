package de.agilecoders.wicket.core.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.core.util.Attributes;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Args;

/**
 * #### Description
 *
 * Alignment behavior to position components.
 *
 * #### Usage
 *
 * the following code snippet sets a container to be "right" positioned.
 *
 * ```java
 * WebMarkupContainer container = new WebMarkupContainer("id");
 * container.add(new AlignmentBehavior(Alignment.RIGHT));
 * add(container);
 * ```
 *
 * ```html
 * <div wicket:id="id">Content</div>
 * ```
 *
 * It is also possible to use a model that contains the {@link de.agilecoders.wicket.core.markup.html.bootstrap.behavior.AlignmentBehavior.Alignment}
 * instead of using {@link de.agilecoders.wicket.core.markup.html.bootstrap.behavior.AlignmentBehavior.Alignment} directly.
 *
 * @author bcousin
 */
public class AlignmentBehavior extends BootstrapBaseBehavior {

    /**
     * TODO miha: add documentation.
     */
    public enum Alignment implements ICssClassNameProvider {
        RIGHT("pull-right"),
        LEFT("pull-left"),
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
    public AlignmentBehavior(final IModel<Alignment> alignment) {
        this.alignment = Args.notNull(alignment, "alignment");
    }

    /**
     * Construct.
     *
     * @param alignment the alignment to use
     */
    public AlignmentBehavior(final Alignment alignment) {
        this(Model.of(Args.notNull(alignment, "alignment")));
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        // remove existing alignment class names to allow switching alignment during ajax updates
        Attributes.removeClass(tag, Alignment.RIGHT.cssClassName(), Alignment.LEFT.cssClassName());

        Alignment value = alignment.getObject();
        switch (value) {
            case RIGHT:
            case LEFT:
                Attributes.addClass(tag, value.cssClassName());
            default:
                break;
        }
    }
}
