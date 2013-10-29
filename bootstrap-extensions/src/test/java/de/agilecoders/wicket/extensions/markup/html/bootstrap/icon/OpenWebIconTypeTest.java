package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for FontAwesomeIconType
 */
public class OpenWebIconTypeTest extends Assert {

	@Test
	public void cssClassName() {
		assertEquals("OpenWebIcon should use 'icon-NAME", "icon-apml", OpenWebIconType.apml.cssClassName());
	}
}
