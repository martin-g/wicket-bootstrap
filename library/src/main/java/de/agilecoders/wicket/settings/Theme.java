package de.agilecoders.wicket.settings;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.less.LessResourceReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Theme {

    private String name;
    private List<ResourceReference> resourceReferences;

    public Theme(final String name, final ResourceReference... resourceReferences) {
        this.name = name;
        this.resourceReferences = Lists.newArrayList(resourceReferences);
    }

    public String name() {
        return name;
    }

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
