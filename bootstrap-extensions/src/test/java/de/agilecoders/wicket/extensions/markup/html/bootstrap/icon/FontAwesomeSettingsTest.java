package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.startsWith;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.WicketApplicationTest;
import org.apache.wicket.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FontAwesomeSettingsTest extends WicketApplicationTest {
    FontAwesomeSettings settings = null;

    @Nested
    class withFontAwesome5configured {

        @BeforeEach
        void setUp() {
            FontAwesomeSettings
                .get(Application.get())
                .setCssResourceReference(FontAwesome5CssReference.instance());
            settings = FontAwesomeSettings.get(Application.get());
        }

        @Test
        void cssClass_startsWith_far_or_fas() {
            for (final FontAwesomeSettings.IconKey key : FontAwesomeSettings.IconKey.values()) {
                assertThat("IconType with key " + key + " should be a FontAwesome5 icon", settings
                    .getIconType(key)
                    .cssClassName(), anyOf(startsWith("far "), startsWith("fas ")));

            }
        }
    }

    @Nested
    class withFontAwesome6configured {

        @BeforeEach
        void setUp() {
            FontAwesomeSettings
                .get(Application.get())
                .setCssResourceReference(FontAwesome6CssReference.instance());
            settings = FontAwesomeSettings.get(Application.get());
        }

        @Test
        void cssClass_startsWith_fa_regular_or_fa_solid() {
            for (final FontAwesomeSettings.IconKey key : FontAwesomeSettings.IconKey.values()) {
                assertThat("IconType with key " + key + " should be a FontAwesome5 icon", settings
                    .getIconType(key)
                    .cssClassName(), anyOf(startsWith("fa-regular "), startsWith("fa-solid ")));

            }
        }
    }
}
