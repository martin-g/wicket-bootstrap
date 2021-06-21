package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.rating;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class RatingConfigTest {

    @Test
    void empty() {
        RatingConfig config = new RatingConfig();
        assertThat(config.toJsonString(), is(equalTo("{\"filled\":\"fas fa-star\",\"empty\":\"far fa-star\"}")));
    }

    @Test
    void initval() {
    	RatingConfig config = new RatingConfig();
        config.withFractions(3);
        config.withStart(3);
        config.withStop(7);
        assertThat(config.toJsonString(), is(equalTo("{\"filled\":\"fas fa-star\",\"empty\":\"far fa-star\",\"fractions\":3,\"start\":3,\"stop\":7}")));
    }
}
