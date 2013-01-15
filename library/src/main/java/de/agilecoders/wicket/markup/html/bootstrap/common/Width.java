package de.agilecoders.wicket.markup.html.bootstrap.common;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameModifier;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.ICssClassNameProvider;
import org.apache.wicket.Component;

/**
 * Defines all possible layout dimensions.
 *
 * @author miha
 */
public enum Width implements ICssClassNameProvider, ICssClassNameModifier {
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

    /**
     * adds width css class name modifier to given component
     *
     * @param component to append the css class
     */
    public void addTo(final Component component) {
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
