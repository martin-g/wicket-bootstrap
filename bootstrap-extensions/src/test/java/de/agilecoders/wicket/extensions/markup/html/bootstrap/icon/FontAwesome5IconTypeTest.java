package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconTypeBuilder.on;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconTypeBuilder.FontAwesome6Brand._500px;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconTypeBuilder.FontAwesome6Regular.envelope_open;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconTypeBuilder.FontAwesome6Solid.address_book;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconTypeBuilder.FontAwesome6Solid.align_center;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link FontAwesome6IconType}
 */
class FontAwesome5IconTypeTest {

    @Test
    void cssClassName() {
        assertEquals("fa-solid fa-circle-half-stroke", FontAwesome6IconType.circle_half_stroke_s.cssClassName(), "FontAwesome should use 'fa-solid fa-NAME");
    }

    @Test
    void cssClassNameFixedWidthAlias() {
        FontAwesome6IconType fontAwesomeIcon = on(align_center)
            .fw()
            .build();
        assertEquals("fa-solid fa-align-center fa-fw", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fa-solid fa-NAME fa-fw");
    }

    @Test
    void cssClassNameFixedWidth() {
        FontAwesome6IconType fontAwesomeIcon = on(align_center)
            .fixedWidth()
            .build();
        assertEquals("fa-solid fa-align-center fa-fw", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fa-solid fa-NAME fa-fw");
    }

    @Test
    void cssClassSpin() {
        FontAwesome6IconType fontAwesomeIcon = on(align_center)
            .spin()
            .build();
        assertEquals("fa-solid fa-align-center fa-spin", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fa-solid fa-NAME fa-spin");
    }

    @Test
    void cssClassRotate() {
        FontAwesome6IconType fontAwesomeIcon = on(align_center)
            .rotate(FontAwesome6IconTypeBuilder.Rotation.rotate_90)
            .build();
        assertEquals("fa-solid fa-align-center fa-rotate-90", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fa-solid fa-NAME fa-ROTATE");
    }

    @Test
    void cssClassSize() {
        FontAwesome6IconType fontAwesomeIcon = on(align_center)
            .size(FontAwesome6IconTypeBuilder.Size.two)
            .build();
        assertEquals("fa-solid fa-align-center fa-2x", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fa-solid fa-NAME fa-SIZE");
    }

    @Test
    void allAttributes() {
        FontAwesome6IconType fontAwesomeIcon = on(address_book)
            .spin()
            .fw()
            .rotate(FontAwesome6IconTypeBuilder.Rotation.flip_horizontal)
            .size(FontAwesome6IconTypeBuilder.Size.large)
            .build();
        assertEquals("fa-solid fa-address-book fa-spin fa-fw fa-flip-horizontal fa-lg", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fa-solid fa-NAME fa-spin fa-fw fa-ROTATE fa-SIZE");
    }


    @Test
    void withRegularStyle_allAttributes() {
        FontAwesome6IconType fontAwesomeIcon = on(envelope_open)
            .spin()
            .fw()
            .rotate(FontAwesome6IconTypeBuilder.Rotation.flip_horizontal)
            .size(FontAwesome6IconTypeBuilder.Size.large)
            .build();
        assertEquals("fa-regular fa-envelope-open fa-spin fa-fw fa-flip-horizontal fa-lg", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fa-regular fa-NAME fa-spin fa-fw fa-ROTATE fa-SIZE");
    }

    @Test
    void withBrandIcon_allAttributes() {
        FontAwesome6IconType fontAwesomeIcon = on(_500px)
            .spin()
            .fw()
            .rotate(FontAwesome6IconTypeBuilder.Rotation.flip_horizontal)
            .size(FontAwesome6IconTypeBuilder.Size.large)
            .build();
        assertEquals("fa-brands fa-500px fa-spin fa-fw fa-flip-horizontal fa-lg", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fa-brands fab-NAME fa-spin fa-fw fa-ROTATE fa-SIZE");
    }
}
