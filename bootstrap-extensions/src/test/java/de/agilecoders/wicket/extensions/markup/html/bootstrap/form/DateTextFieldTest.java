package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Date;

import org.apache.wicket.model.Model;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

/**
 * @author Urs Joss
 */
class DateTextFieldTest extends
    AbstractDateTextFieldTest<Date, org.apache.wicket.extensions.markup.html.form.DateTextField, DateTime, DateTextFieldConfig, DateTextField> {

    private final Date now = new Date();

    @Override
    Date getNow() {
        return now;
    }

    @Override
    DateTextFieldConfig newDefaultConfig() {
        return new DateTextFieldConfig();
    }

    DateTextField newTextField() {
        return new DateTextField("tf");
    }

    @Override
    DateTime getStartDate() {
        return DateTime.parse("2018-06-30T01:02:03.000");
    }

    @Override
    DateTime getEndDate() {
        return DateTime.parse("2018-07-13T04:05:06.000");
    }

    @Test
    void constructing_withIdAndDateModelAndNullConfig_throwsNPE() {
        assertThrows(IllegalArgumentException.class, () -> new DateTextField("tf", Model.of(getNow()), (DateTextFieldConfig) null));
    }

    @Test
    void constructing_withIdAndNullConfig_throwsNPE() {
        assertThrows(IllegalArgumentException.class, () -> new DateTextField("tf", (DateTextFieldConfig) null));
    }

    @Test
    void constructing_withIdAndDatePattern_knowsIdAndDatePattern() {
        DateTextField f = new DateTextField("tf", "dd.MM.yyyy");

        assertThat(f.getId(), is(equalTo("tf")));
        assertThat(f.getModelObject(), is(equalTo(null)));

        DateTextFieldConfig config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo("dd.MM.yyyy")));
        assertThat(config.getLanguage(), is(equalTo(newDefaultConfig().getLanguage())));
    }

    @Test
    void constructing_withIdAndDateModel_knowsIdAndDateModel() {
        DateTextField f = new DateTextField("tf", Model.of(getNow()));

        assertThat(f.getId(), is(equalTo("tf")));
        assertThat(f.getModelObject(), is(equalTo(getNow())));

        hasDefaultModel(f);
    }

    @Test
    void constructing_withIdAndDateModelAndDatePattern_knowsIdAndDateModelAndDatePattern() {
        DateTextField f = new DateTextField("tf", Model.of(getNow()), "dd.MM.yyyy");

        assertThat(f.getId(), is(equalTo("tf")));
        assertThat(f
            .getModelObject()
            .toString(), is(equalTo(getNow().toString())));

        DateTextFieldConfig config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo("dd.MM.yyyy")));
        assertThat(config.getLanguage(), is(equalTo(newDefaultConfig().getLanguage())));
    }

    @Test
    void constructing_withIdAndDateModelAndConfig_knowsIdAndModelAndConfig() {
        DateTextFieldConfig cfg = new DateTextFieldConfig().withFormat("dd.MM.yyyy");

        DateTextField f = new DateTextField("tf", Model.of(getNow()), cfg);

        assertThat(f.getId(), is(equalTo("tf")));
        assertThat(f.getModelObject(), is(equalTo(getNow())));

        DateTextFieldConfig config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo("dd.MM.yyyy")));
        assertThat(config, is(equalTo(cfg)));
    }

}
