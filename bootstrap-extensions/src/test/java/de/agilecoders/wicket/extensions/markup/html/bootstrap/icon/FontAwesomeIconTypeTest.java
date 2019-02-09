package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.FontAwesomeGraphic.address_book;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.FontAwesomeGraphic.align_center;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.on;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for FontAwesomeIconType
 */
class FontAwesomeIconTypeTest {

    @Test
    void cssClassName() {
        assertEquals("fa fa-adjust", FontAwesomeIconType.adjust.cssClassName(), "FontAwesome should use 'fa fa-NAME");
    }

    @Test
    void cssClassNameFixedWidthAlias() {
        FontAwesomeIconType fontAwesomeIcon = on(align_center)
            .fw()
            .build();
        assertEquals("fa fa-align-center fa-fw", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fa fa-NAME fa-fw");
    }

    @Test
    void cssClassNameFixedWidth() {
        FontAwesomeIconType fontAwesomeIcon = on(align_center)
            .fixedWidth()
            .build();
        assertEquals("fa fa-align-center fa-fw", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fa fa-NAME fa-fw");
    }

    @Test
    void cssClassSpin() {
        FontAwesomeIconType fontAwesomeIcon = on(align_center)
            .spin()
            .build();
        assertEquals("fa fa-align-center fa-spin", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fa fa-NAME fa-spin");
    }

    @Test
    void cssClassRotate() {
        FontAwesomeIconType fontAwesomeIcon = on(align_center)
            .rotate(FontAwesomeIconTypeBuilder.Rotation.rotate_90)
            .build();
        assertEquals("fa fa-align-center fa-rotate-90", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fa fa-NAME fa-ROTATE");
    }

    @Test
    void cssClassSize() {
        FontAwesomeIconType fontAwesomeIcon = on(align_center)
            .size(FontAwesomeIconTypeBuilder.Size.two)
            .build();
        assertEquals("fa fa-align-center fa-2x", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fa fa-NAME fa-SIZE");
    }

    @Test
    void allAttributes() {
        FontAwesomeIconType fontAwesomeIcon = on(address_book)
            .spin()
            .fw()
            .rotate(FontAwesomeIconTypeBuilder.Rotation.flip_horizontal)
            .size(FontAwesomeIconTypeBuilder.Size.large)
            .build();
        assertEquals("fa fa-address-book fa-spin fa-fw fa-flip-horizontal fa-lg", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fa fa-NAME fa-spin fa-fw fa-ROTATE fa-SIZE");
    }
}
