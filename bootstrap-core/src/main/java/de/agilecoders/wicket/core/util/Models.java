package de.agilecoders.wicket.core.util;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;

/**
 * # Description
 *
 * helper class for {@link org.apache.wicket.model.IModel} handling.
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public final class Models {

    /**
     * Construct.
     */
    private Models() {
        throw new UnsupportedOperationException();
    }

    /**
     * # Description
     *
     * checks if given model is not null and string object is also not null or empty.
     *
     * # Usage
     *
     * ```java
     * Models.isNullOrEmpty(null); // = true
     * Models.isNullOrEmpty(Model.of(null)); // = true
     * Models.isNullOrEmpty(Model.of("")); // = true
     * Models.isNullOrEmpty(Model.of("value")); // = false
     * ```
     *
     * @param model The model to check
     * @return true, if model is null or empty
     */
    public static boolean isNullOrEmpty(IModel<String> model) {
        return model == null || Strings.isEmpty(model.getObject());
    }

}
