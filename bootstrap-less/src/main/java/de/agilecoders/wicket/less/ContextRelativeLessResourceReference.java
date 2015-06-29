package de.agilecoders.wicket.less;

import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.request.resource.ContextRelativeResourceReference;
import org.apache.wicket.util.resource.ResourceUtils;

/**
 * A resource reference to a LESS resource located at the servlet context
 */
public class ContextRelativeLessResourceReference extends ContextRelativeResourceReference {

    public static final String CONTEXT_RELATIVE_LESS_REFERENCE_VARIATION = "wicketcrlrrv";

    public ContextRelativeLessResourceReference(String name) {
        this(name, ResourceUtils.MIN_POSTFIX_DEFAULT, true);
    }

    public ContextRelativeLessResourceReference(String name, boolean minifyIt) {
        this(name, ResourceUtils.MIN_POSTFIX_DEFAULT, minifyIt);
    }

    public ContextRelativeLessResourceReference(String name, String minPostfix) {
        this(name, minPostfix, true);
    }

    public ContextRelativeLessResourceReference(String name, String minPostfix, boolean minifyIt) {
        super(name, minPostfix, minifyIt);
    }

    @Override
    protected ContextRelativeResource buildContextRelativeResource(String name, String minPostfix) {
        String minifiedName = name;

        if (canBeMinified())
        {
            minifiedName = ResourceUtils.getMinifiedName(name, minPostfix);
        }
        return new ContextRelativeLessResource(minifiedName);
    }

    @Override
    public String getVariation() {
        return CONTEXT_RELATIVE_LESS_REFERENCE_VARIATION;
    }
}
