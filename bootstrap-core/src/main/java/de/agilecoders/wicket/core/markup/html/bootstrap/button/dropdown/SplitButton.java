package de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

/**
 * Use any button to trigger a dropdown menu by placing it within a .btn-group and providing the proper menu markup.
 *
 * @author miha
 */
public abstract class SplitButton extends DropDownButton {
    private static final long serialVersionUID = 1L;
    private AbstractLink btn;
    private WebMarkupContainer caret;

    public SplitButton(final String markupId, final IModel<String> model) {
        super(markupId, model);
    }

    public SplitButton(final String markupId, final IModel<String> model, final IModel<IconType> iconTypeModel) {
        super(markupId, model, iconTypeModel);
    }

    /**
     * a {@link SplitButton} uses "btn-group" instead of "dropdown" as base css class.
     *
     * @return css class name.
     */
    @Override
    protected String createCssClassName() {
        return "btn-group";
    }

    /**
     * creates a new caret button that opens the split button dropdown menu
     *
     * @param markupId the caret's markup id
     * @return new caret component
     */
    protected WebMarkupContainer newCaret(final String markupId) {
        return new WebMarkupContainer(markupId);
    }

    /**
     * creates a new base button
     *
     * @param markupId      the base button markup id
     * @param labelModel    the label of the base button
     * @param iconTypeModel the type of the icon
     * @return new base button
     */
    protected abstract AbstractLink newBaseButton(final String markupId, final IModel<String> labelModel, final IModel<IconType> iconTypeModel);

    @Override
    protected final WebMarkupContainer newButton(String markupId, IModel<String> labelModel, IModel<IconType> iconTypeModel) {
        Args.isTrue(btn == null, "btn was already set before.");

        btn = newBaseButton("button", labelModel, iconTypeModel);
        btn.setOutputMarkupId(true);

        caret = newCaret("caret");
        add(caret);

        return btn;
    }

    @Override
    protected void addIconToBaseButton(final Icon icon) {
        // do nothing
    }

    @Override
    protected void addButtonBehavior(final ButtonBehavior buttonBehavior) {
        btn.add(buttonBehavior);
        caret.add(buttonBehavior);
    }
}
