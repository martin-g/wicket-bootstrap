package de.agilecoders.wicket.markup.html.bootstrap.button;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.util.CssClassNames;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * This {@link CssClassNameAppender} adds all button styles.
 *
 * @author miha
 * @version 1.0
 */
public class ButtonCssClassAppender extends CssClassNameAppender {
    private static final String DISABLED_CLASSNAME = "btn-disabled";
    private static final String DEFAULT_CLASSNAME = "btn";

    private final IModel<ButtonType> type;
    private final IModel<ButtonSize> size;
    private final IModel<Boolean> block;

    /**
     * Constructor.
     *
     * @param type  The button type.
     * @param size  The button size.
     * @param block Whether to display this button as block element.
     */
    public ButtonCssClassAppender(IModel<ButtonType> type, IModel<ButtonSize> size, IModel<Boolean> block) {
        super("");

        this.type = type;
        this.size = size;
        this.block = block;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind(Component component) {
        super.bind(component);

        getClassNameModel().setObject(classNames(component.isEnabled()));
    }

    /**
     * @param enabled the enabled state of the assigned component.
     * @return a list of all css class names.
     */
    private String classNames(final boolean enabled) {
        return CssClassNames.parse(DEFAULT_CLASSNAME).add(
                type.getObject().cssClassName(),
                size.getObject().cssClassName(),
                (block.getObject() ? "btn-block" : ""),
                (enabled ? "" : DISABLED_CLASSNAME)).asString();
    }

    /**
     * @return a casted instance of the assigned model.
     */
    @SuppressWarnings("unchecked")
    private IModel<String> getClassNameModel() {
        return (IModel<String>) getReplaceModel();
    }
}
