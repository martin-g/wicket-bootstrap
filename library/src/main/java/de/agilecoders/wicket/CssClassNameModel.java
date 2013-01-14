package de.agilecoders.wicket;

import de.agilecoders.wicket.util.CssClassNames;
import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * Simple css class name model that holds a set of css class names.
 *
 * @author miha
 */
public class CssClassNameModel extends AbstractReadOnlyModel<String> {

    private transient final CssClassNames.Builder builder;

    /**
     * Construct.
     */
    public CssClassNameModel() {
        this.builder = CssClassNames.newBuilder();
    }

    @Override
    public String getObject() {
        return builder.asString();
    }

    /**
     * @return css class name builder
     */
    public final CssClassNames.Builder builder() {
        return builder;
    }
}
