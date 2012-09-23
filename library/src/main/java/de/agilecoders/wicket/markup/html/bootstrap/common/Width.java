package de.agilecoders.wicket.markup.html.bootstrap.common;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import org.apache.wicket.Component;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public enum Width implements CssClassNameProvider {
    SPAN1,
    SPAN2,
    SPAN3,
    SPAN4,
    SPAN5,
    SPAN6,
    SPAN7,
    SPAN8,
    SPAN9,
    SPAN10,
    SPAN11,
    SPAN12;

    public void addTo(Component component) {
        component.add(newCssClassNameModifier());
    }
    
    @Override
    public String cssClassName() {
        return name().toLowerCase();
    }

    @Override
    public CssClassNameAppender newCssClassNameModifier() {
        return new CssClassNameAppender(this);
    }
}
