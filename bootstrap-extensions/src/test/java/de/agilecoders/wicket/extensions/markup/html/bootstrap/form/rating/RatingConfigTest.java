package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.rating;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.WicketApplicationTest;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5CssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeSettings;
import org.apache.wicket.Application;
import org.junit.jupiter.api.Test;

class RatingConfigTest extends WicketApplicationTest {

    @Test
    void empty() {
        RatingConfig config = new RatingConfig();
        assertThat(config.toJsonString(),
            is(equalTo("{\"filled\":\"fa-solid fa-star\",\"empty\":\"fa-regular fa-star\"}")));
    }

    @Test
    void withFA5_empty() {
        FontAwesomeSettings
            .get(Application.get())
            .setCssResourceReference(FontAwesome5CssReference.instance());
        RatingConfig config = new RatingConfig();
        assertThat(config.toJsonString(), is(equalTo("{\"filled\":\"fas fa-star\",\"empty\":\"far fa-star\"}")));
    }

    @Test
    void initval() {
    	RatingConfig config = new RatingConfig();
        config.withFractions(3);
        config.withStart(3);
        config.withStop(7);
        assertThat(config.toJsonString(), is(equalTo(
            "{\"filled\":\"fa-solid fa-star\",\"empty\":\"fa-regular fa-star\",\"fractions\":3,\"start\":3,\"stop\":7}")));
    }

    @Test
    void withFA5_initval() {
        FontAwesomeSettings
            .get(Application.get())
            .setCssResourceReference(FontAwesome5CssReference.instance());
        RatingConfig config = new RatingConfig();
        config.withFractions(3);
        config.withStart(3);
        config.withStop(7);
        assertThat(config.toJsonString(), is(equalTo(
            "{\"filled\":\"fas fa-star\",\"empty\":\"far fa-star\",\"fractions\":3,\"start\":3,\"stop\":7}")));
    }

}
