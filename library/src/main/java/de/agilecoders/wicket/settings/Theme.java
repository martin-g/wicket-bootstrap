package de.agilecoders.wicket.settings;

import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import com.google.common.collect.Lists;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Theme implements ITheme {

    private String name;
    private List<ResourceReference> resourceReferences;

    public Theme(final String name, final ResourceReference... resourceReferences) {
        this.name = name;
        this.resourceReferences = Lists.newArrayList(resourceReferences);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        for (ResourceReference resourceReference : resourceReferences) {
            if (resourceReference instanceof CssResourceReference) {
                response.render(CssHeaderItem.forReference(resourceReference));
            } else if (resourceReference instanceof JavaScriptResourceReference) {
                response.render(JavaScriptHeaderItem.forReference(resourceReference));
            }
        }
    }
}
