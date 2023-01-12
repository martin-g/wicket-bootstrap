package de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.util.Attributes;

/**
* Behavior for alignment of the DropDownButton and SplitButton to align
* the DropDown menu to the right.
*
* @author helmut
**/

public class DropDownAlignmentBehavior extends BootstrapBaseBehavior {
    private static final long serialVersionUID = 1L;
    private static final String[] POSSIBLE_CLASSES = Stream.of(Size.values()).flatMap(size ->
            Stream.of(Alignment.left(size), Alignment.right(size))
        ).map(Alignment::cssClassName).toArray(String[]::new);

    /**
     * Size for Alignment for the dropdown of a DropDownButton
     */
    public enum Size implements ICssClassNameProvider {
        SMALL("sm"),
        MEDIUM("md"),
        LARGE("lg"),
        XLARGE("xl"),
        XXLARGE("xxl"),
        NONE("");
        private final String className;

        /**
         * Construct.
         *
         * @param className the css class name
         */
        Size(final String className) {
            this.className = className;
        }

        @Override
        public String cssClassName() {
            return className;
        }
    }

    /**
     * Alignment for the dropdown of a DropDownButton
     */
    public static class Alignment implements ICssClassNameProvider {
        private static final String CLASS_RIGHT = "end";
        private static final String CLASS_LEFT = "start";
        public static final Alignment RIGHT = right();
        public static final Alignment NONE = new Alignment("");

        private final String className;
        private final Size size;

        /**
         * Construct.
         *
         * @param className the css class name
         */
        private Alignment(final String className) {
            this(className, Size.NONE);
        }

        /**
         * Construct.
         *
         * @param className the css class name
         * @param size the size
         */
        private Alignment(final String className, final Size size) {
            Args.isTrue(Strings.isEmpty(className) || CLASS_LEFT.equals(className) || CLASS_RIGHT.equals(className), "className");
            this.className = className;
            this.size = size;
        }

        public static Alignment left() {
            return left(Size.NONE);
        }

        public static Alignment left(final Size size) {
            return new Alignment(CLASS_LEFT, size);
        }

        public static Alignment right() {
            return right(Size.NONE);
        }

        public static Alignment right(final Size size) {
            return new Alignment(CLASS_RIGHT, size);
        }

        @Override
        public String cssClassName() {
            if (Strings.isEmpty(className)) {
                return className;
            }
            final String sizeCss = size.cssClassName() + (Strings.isEmpty(size.cssClassName()) ? "" : "-");
            return "dropdown-menu-" + sizeCss + className;
        }
    }

    private final IModel<Collection<Alignment>> alignment;

    /**
     * Construct.
     *
     * @param alignment the alignment to use
     */
    public DropDownAlignmentBehavior(final IModel<Collection<Alignment>> alignment) {
        this.alignment = Args.notNull(alignment, "alignment");
    }

    /**
     * Construct.
     *
     * @param alignment the alignment to use
     */
    public DropDownAlignmentBehavior(final Alignment... alignment) {
        this(Model.of(List.of(Args.notNull(alignment, "alignment"))));
    }

    @Override
    public void onComponentTag(final Component component, final ComponentTag tag) {
        super.onComponentTag(component, tag);

        // remove existing alignment class names to allow switching alignment during ajax updates
        Attributes.removeClass(tag, POSSIBLE_CLASSES);

        alignment.getObject().forEach(align -> Attributes.addClass(tag, align.cssClassName()));
    }

    public DropDownAlignmentBehavior setAlignment(Alignment... alignment) {
        this.alignment.setObject(List.of(alignment));
        return this;
    }

    @Override
    public void detach(Component component) {
        super.detach(component);
        alignment.detach();
    }
}
