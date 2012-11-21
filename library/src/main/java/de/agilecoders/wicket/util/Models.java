package de.agilecoders.wicket.util;

import com.google.common.base.Strings;
import org.apache.wicket.model.IModel;

/**
 * helper class for {@link org.apache.wicket.model.IModel} handling.
 *
 * @author miha
 * @version 1.0
 */
public final class Models {

    private Models() {
        throw new UnsupportedOperationException();
    }

    /**
     * checks if given model is not null and string object is also not null or empty.
     *
     * @param model The model to check
     * @return true, if model is null or empty
     */
    public static boolean isNullOrEmpty(IModel<String> model) {
        return model == null || Strings.isNullOrEmpty(model.getObject());
    }


}
