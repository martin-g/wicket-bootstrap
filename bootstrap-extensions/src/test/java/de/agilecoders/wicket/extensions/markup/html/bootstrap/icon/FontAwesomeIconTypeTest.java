package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for FontAwesomeIconType
 */
public class FontAwesomeIconTypeTest extends Assert {

    @Test
    public void cssClassName() {
        assertEquals("FontAwesome should use 'fa fa-NAME", "fa fa-adjust", FontAwesomeIconType.adjust.cssClassName());
    }

    @Test
    public void cssClassNameFixedWidth() {
        assertEquals("FontAwesome should use 'fa fa-NAME fa-fw", "fa fa-align-center fa-fw", FontAwesomeIconType.align_center.fixedWidth().cssClassName());
    }
}
