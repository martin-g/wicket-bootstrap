package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Classes;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class BooleanRadioChoiceRenderer extends DefaultRadioChoiceRenderer<Boolean> {

    /**
     * component used to resolve i18n resources for this renderer.
     */
    private final Component resourceSource;


    public BooleanRadioChoiceRenderer(Buttons.Type type) {
        this(type, null);
    }

    public BooleanRadioChoiceRenderer(Buttons.Type type, Component resourceSource) {
        super(type, null);
        this.resourceSource = resourceSource;
    }

    public IModel<String> lableOf(Boolean option) {
        return Model.of(getDisplayValue(option).toString());
    }

    /** {@inheritDoc} */
    public final CharSequence getDisplayValue(Boolean option)
    {
        final String value;

        String key = resourceKey(option);

        if (resourceSource != null)
        {
            value = resourceSource.getString(key);
        }
        else
        {
            value = Application.get().getResourceSettings().getLocalizer().getString(key, null);
        }

        return postProcess(value);
    }

    protected CharSequence postProcess(String value)
    {
        return value;
    }

    /**
     * Translates the {@code object} into resource key that will be used to lookup the value shown
     * to the user
     *
     * @param object
     * @return resource key
     */
    protected String resourceKey(Boolean object)
    {
        return Classes.simpleName(Boolean.class) + '.' + object.booleanValue();
    }
}
