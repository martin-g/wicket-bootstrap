package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.on;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.FontAwesomeBrand._500px;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.FontAwesomeRegular.envelope_open;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.FontAwesomeSolid.address_book;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.FontAwesomeSolid.align_center;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link FontAwesomeIconType}
 */
class FontAwesomeIconTypeTest {

    @Test
    void cssClassName() {
        assertEquals("fas fa-adjust", FontAwesomeIconType.adjust_s.cssClassName(), "FontAwesome should use 'fas fa-NAME");
    }

    @Test
    void cssClassNameFixedWidthAlias() {
        FontAwesomeIconType fontAwesomeIcon = on(align_center)
            .fw()
            .build();
        assertEquals("fas fa-align-center fa-fw", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fas fa-NAME fa-fw");
    }

    @Test
    void cssClassNameFixedWidth() {
        FontAwesomeIconType fontAwesomeIcon = on(align_center)
            .fixedWidth()
            .build();
        assertEquals("fas fa-align-center fa-fw", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fas fa-NAME fa-fw");
    }

    @Test
    void cssClassSpin() {
        FontAwesomeIconType fontAwesomeIcon = on(align_center)
            .spin()
            .build();
        assertEquals("fas fa-align-center fa-spin", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fas fa-NAME fa-spin");
    }

    @Test
    void cssClassRotate() {
        FontAwesomeIconType fontAwesomeIcon = on(align_center)
            .rotate(FontAwesomeIconTypeBuilder.Rotation.rotate_90)
            .build();
        assertEquals("fas fa-align-center fa-rotate-90", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fas fa-NAME fa-ROTATE");
    }

    @Test
    void cssClassSize() {
        FontAwesomeIconType fontAwesomeIcon = on(align_center)
            .size(FontAwesomeIconTypeBuilder.Size.two)
            .build();
        assertEquals("fas fa-align-center fa-2x", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fas fa-NAME fa-SIZE");
    }

    @Test
    void allAttributes() {
        FontAwesomeIconType fontAwesomeIcon = on(address_book)
            .spin()
            .fw()
            .rotate(FontAwesomeIconTypeBuilder.Rotation.flip_horizontal)
            .size(FontAwesomeIconTypeBuilder.Size.large)
            .build();
        assertEquals("fas fa-address-book fa-spin fa-fw fa-flip-horizontal fa-lg", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fas fa-NAME fa-spin fa-fw fa-ROTATE fa-SIZE");
    }


    @Test
    void withRegularStyle_allAttributes() {
        FontAwesomeIconType fontAwesomeIcon = on(envelope_open)
            .spin()
            .fw()
            .rotate(FontAwesomeIconTypeBuilder.Rotation.flip_horizontal)
            .size(FontAwesomeIconTypeBuilder.Size.large)
            .build();
        assertEquals("far fa-envelope-open fa-spin fa-fw fa-flip-horizontal fa-lg", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'far fa-NAME fa-spin fa-fw fa-ROTATE fa-SIZE");
    }

    @Test
    void withBrandIcon_allAttributes() {
        FontAwesomeIconType fontAwesomeIcon = on(_500px)
            .spin()
            .fw()
            .rotate(FontAwesomeIconTypeBuilder.Rotation.flip_horizontal)
            .size(FontAwesomeIconTypeBuilder.Size.large)
            .build();
        assertEquals("fab fa-500px fa-spin fa-fw fa-flip-horizontal fa-lg", fontAwesomeIcon.cssClassName(),
            "FontAwesome should use 'fab fab-NAME fa-spin fa-fw fa-ROTATE fa-SIZE");
    }
}
