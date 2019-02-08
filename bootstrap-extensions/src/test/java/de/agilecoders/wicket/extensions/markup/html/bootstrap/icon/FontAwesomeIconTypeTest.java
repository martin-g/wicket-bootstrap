package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import org.junit.jupiter.api.Test;

import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.on;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.FontAwesomeGraphic.align_center;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for FontAwesomeIconType
 */
public class FontAwesomeIconTypeTest {

    @Test
    public void cssClassName() {
        assertEquals("fa fa-adjust", FontAwesomeIconType.adjust.cssClassName(), "FontAwesome should use 'fa fa-NAME");
    }

    @Test
    public void cssClassNameFixedWidth() {
        FontAwesomeIconType fontAwesomeIcon = on(align_center).fw().build();
        assertEquals("fa fa-align-center fa-fw", fontAwesomeIcon.cssClassName(), "FontAwesome should use 'fa fa-NAME fa-fw");
    }
}
