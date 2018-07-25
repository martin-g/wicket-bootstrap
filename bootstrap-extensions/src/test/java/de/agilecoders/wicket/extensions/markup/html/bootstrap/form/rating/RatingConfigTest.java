package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.rating;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;

public class RatingConfigTest extends Assert{

    @Test
    public void empty() {
        RatingConfig config = new RatingConfig();
        assertThat(config.toJsonString(), is(equalTo("{\"filled\":\"fa fa-star\",\"empty\":\"fa fa-star-o\"}")));
    }

    @Test
    public void initval() {
    	RatingConfig config = new RatingConfig();
        config.withFractions(3);
        config.withStart(3);
        config.withStop(7);
        assertThat(config.toJsonString(), is(equalTo("{\"filled\":\"fa fa-star\",\"empty\":\"fa fa-star-o\",\"fractions\":3,\"start\":3,\"stop\":7}")));
    }
}
