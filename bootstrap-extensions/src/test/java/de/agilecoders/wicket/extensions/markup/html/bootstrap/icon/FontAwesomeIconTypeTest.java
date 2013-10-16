package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for FontAwesomeIconType
 */
public class FontAwesomeIconTypeTest extends Assert {

    @Test
    public void cssClassName() {
        assertEquals("FontAwesome should use 'icon icon-NAME", "icon icon-adjust", FontAwesomeIconType.adjust.cssClassName());
    }
}
