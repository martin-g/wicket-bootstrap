package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.rating;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class RatingConfigTest {

    @Test
    void empty() {
        RatingConfig config = new RatingConfig();
        assertThat(config.toJsonString(), is(equalTo("{\"filled\":\"fa fa-star\",\"empty\":\"fa fa-star-o\"}")));
    }

    @Test
    void initval() {
    	RatingConfig config = new RatingConfig();
        config.withFractions(3);
        config.withStart(3);
        config.withStop(7);
        assertThat(config.toJsonString(), is(equalTo("{\"filled\":\"fa fa-star\",\"empty\":\"fa fa-star-o\",\"fractions\":3,\"start\":3,\"stop\":7}")));
    }
}
