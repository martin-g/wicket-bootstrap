package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for FontAwesomeIconType
 */
public class OpenWebIconTypeTest extends Assertions {

    @Test
    public void cssClassName() {
        assertEquals("icon-apml", OpenWebIconType.apml.cssClassName(), "OpenWebIcon should use 'icon-NAME");
    }
}
