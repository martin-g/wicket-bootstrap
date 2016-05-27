package de.agilecoders.wicket.core.markup.html.bootstrap.panel;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameModifier;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;

/**
 * A {@link PanelType} defines the background color of a Panel Heading/Panel Title and the color of the Panel border.
 * 
 * documentation: http://getbootstrap.com/components/#panels-alternatives
 * 
 * @author Eric Hamel <eric.hamel@me.com>
 *
 */
public enum PanelType implements ICssClassNameProvider, ICssClassNameModifier {
	Default,
	Primary,
	Success,
	Info,
	Warning,
	Danger;

    @Override
    public String cssClassName() {
        return "panel-" + name().toLowerCase();
    }

    @Override
    public CssClassNameAppender newCssClassNameModifier() {
        return new CssClassNameAppender(cssClassName());
    }

}
