package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import org.junit.jupiter.api.Test;

import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5IconTypeBuilder.FontAwesome5Brand._500px;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5IconTypeBuilder.FontAwesome5Regular.envelope_open;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5IconTypeBuilder.FontAwesome5Solid.address_book;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5IconTypeBuilder.FontAwesome5Solid.align_center;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5IconTypeBuilder.on;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link FontAwesome5IconType}
 */
class FontAwesome5IconTypeTest {

    @Test
    void cssClassName() {
        assertEquals("fas fa-adjust", FontAwesome5IconType.adjust_s.cssClassName(), "FontAwesome should use 'fas fa-NAME");
    }

    @Test
    void cssClassNameFixedWidthAlias() {
        FontAwesome5IconType fontAwesomeIcon = on(align_center)
            .fw()
            .build();
        assertEquals("fas fa-align-center fa-fw", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fas fa-NAME fa-fw");
    }

    @Test
    void cssClassNameFixedWidth() {
        FontAwesome5IconType fontAwesomeIcon = on(align_center)
            .fixedWidth()
            .build();
        assertEquals("fas fa-align-center fa-fw", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fas fa-NAME fa-fw");
    }

    @Test
    void cssClassSpin() {
        FontAwesome5IconType fontAwesomeIcon = on(align_center)
            .spin()
            .build();
        assertEquals("fas fa-align-center fa-spin", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fas fa-NAME fa-spin");
    }

    @Test
    void cssClassRotate() {
        FontAwesome5IconType fontAwesomeIcon = on(align_center)
            .rotate(FontAwesome5IconTypeBuilder.Rotation.rotate_90)
            .build();
        assertEquals("fas fa-align-center fa-rotate-90", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fas fa-NAME fa-ROTATE");
    }

    @Test
    void cssClassSize() {
        FontAwesome5IconType fontAwesomeIcon = on(align_center)
            .size(FontAwesome5IconTypeBuilder.Size.two)
            .build();
        assertEquals("fas fa-align-center fa-2x", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fas fa-NAME fa-SIZE");
    }

    @Test
    void allAttributes() {
        FontAwesome5IconType fontAwesomeIcon = on(address_book)
            .spin()
            .fw()
            .rotate(FontAwesome5IconTypeBuilder.Rotation.flip_horizontal)
            .size(FontAwesome5IconTypeBuilder.Size.large)
            .build();
        assertEquals("fas fa-address-book fa-spin fa-fw fa-flip-horizontal fa-lg", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fas fa-NAME fa-spin fa-fw fa-ROTATE fa-SIZE");
    }


    @Test
    void withRegularStyle_allAttributes() {
        FontAwesome5IconType fontAwesomeIcon = on(envelope_open)
            .spin()
            .fw()
            .rotate(FontAwesome5IconTypeBuilder.Rotation.flip_horizontal)
            .size(FontAwesome5IconTypeBuilder.Size.large)
            .build();
        assertEquals("far fa-envelope-open fa-spin fa-fw fa-flip-horizontal fa-lg", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'far fa-NAME fa-spin fa-fw fa-ROTATE fa-SIZE");
    }

    @Test
    void withBrandIcon_allAttributes() {
        FontAwesome5IconType fontAwesomeIcon = on(_500px)
            .spin()
            .fw()
            .rotate(FontAwesome5IconTypeBuilder.Rotation.flip_horizontal)
            .size(FontAwesome5IconTypeBuilder.Size.large)
            .build();
        assertEquals("fab fa-500px fa-spin fa-fw fa-flip-horizontal fa-lg", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fab fab-NAME fa-spin fa-fw fa-ROTATE fa-SIZE");
    }
}
