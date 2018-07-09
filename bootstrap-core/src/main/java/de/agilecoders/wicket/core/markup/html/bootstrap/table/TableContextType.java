package de.agilecoders.wicket.core.markup.html.bootstrap.table;

import org.apache.wicket.AttributeModifier;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameModifier;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;

/**
 *
 * A {@link TableContextType} defines the type of contextual class for a table which changes the background color.
 *
 * documentation: http://getbootstrap.com/css/#tables-contextual-classes
 *
 * @author Eric Hamel <eric.hamel@me.com>
 *
 */
public enum TableContextType implements ICssClassNameProvider, ICssClassNameModifier {
	Active,
	Primary,
	Secondary,
	Info,
	Success,
	Warning,
	Danger,
	Light,
	Dark;

	@Override
	public AttributeModifier newCssClassNameModifier() {
		return new CssClassNameAppender(cssClassName());
	}

	@Override
	public String cssClassName() {
		return String.format("table-%s", name().toLowerCase());
	}

}
