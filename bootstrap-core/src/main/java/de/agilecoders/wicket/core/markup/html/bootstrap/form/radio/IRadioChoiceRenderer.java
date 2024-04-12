package de.agilecoders.wicket.core.markup.html.bootstrap.form.radio;

import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;

/**
 * Allows to configure radio choice component
 *
 * @param <T>
 */
public interface IRadioChoiceRenderer<T> extends IDetachable {

    IModel<T> modelOf(T option);

    /**
     * @deprecated Please use {@link #labelOf(Object)}
     */
    @Deprecated(forRemoval = true)
    default IModel<String> lableOf(T option) {
        return null;
    }

    default IModel<String> labelOf(T option) {
        return lableOf(option);
    };

    String getButtonClass(T option);
}
