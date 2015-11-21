package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import org.junit.Assert;
import org.junit.Test;

import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.on;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.FontAwesomeGraphic.align_center;

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
        FontAwesomeIconType fontAwesomeIcon = on(align_center).fw().build();
        assertEquals("FontAwesome should use 'fa fa-NAME fa-fw", "fa fa-align-center fa-fw",
                     fontAwesomeIcon.cssClassName());
    }
}
