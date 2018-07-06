package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.wicket.model.Model;
import org.junit.Test;

/**
 * @author Urs Joss
 */
public class LocalDateTextFieldTest extends
    AbstractDateTextFieldTest<LocalDate, org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField, LocalDateTime, LocalDateTextFieldConfig, LocalDateTextField> {

    private final LocalDate now = LocalDate.now();

    @Override
    LocalDate getNow() {
        return now;
    }

    @Override
    LocalDateTextFieldConfig newDefaultConfig() {
        return new LocalDateTextFieldConfig();
    }

    LocalDateTextField newTextField() {
        return new LocalDateTextField("tf");
    }

    @Override
    LocalDateTime getStartDate() {
        return LocalDateTime.parse("2018-06-30T01:02:03.000");
    }

    @Override
    LocalDateTime getEndDate() {
        return LocalDateTime.parse("2018-07-13T04:05:06.000");
    }

    @Test(expected = NullPointerException.class)
    public void constructing_withIdAndDateModelAndNullConfig_throwsNPE() {
        // TODO should ideally throw IllegalArgumentException from Args.notNull
        new LocalDateTextField("tf", Model.of(getNow()), (LocalDateTextFieldConfig) null);
    }

    @Test(expected = NullPointerException.class)
    public void constructing_withIdAndNullConfig_throwsNPE() {
        // TODO should ideally throw IllegalArgumentException from Args.notNull
        new LocalDateTextField("tf", (LocalDateTextFieldConfig) null);
    }

    @Test
    public void constructing_withIdAndDatePattern_knowsIdAndDatePattern() {
        LocalDateTextField f = new LocalDateTextField("tf", "dd.MM.yyyy");

        assertThat(f.getId(), is(equalTo("tf")));
        assertThat(f.getModelObject(), is(equalTo(null)));

        LocalDateTextFieldConfig config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo("dd.MM.yyyy")));
        assertThat(config.getLanguage(), is(equalTo(newDefaultConfig().getLanguage())));
    }

    @Test
    public void constructing_withIdAndDateModel_knowsIdAndDateModel() {
        LocalDateTextField f = new LocalDateTextField("tf", Model.of(getNow()));

        assertThat(f.getId(), is(equalTo("tf")));
        assertThat(f.getModelObject(), is(equalTo(getNow())));

        hasDefaultModel(f);
    }

    @Test
    public void constructing_withIdAndDateModelAndDatePattern_knowsIdAndDateModelAndDatePattern() {
        LocalDateTextField f = new LocalDateTextField("tf", Model.of(getNow()), "dd.MM.yyyy");

        assertThat(f.getId(), is(equalTo("tf")));
        assertThat(f.getModelObject(), is(equalTo(getNow())));

        LocalDateTextFieldConfig config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo("dd.MM.yyyy")));
        assertThat(config.getLanguage(), is(equalTo(newDefaultConfig().getLanguage())));
    }

    @Test
    public void constructing_withIdAndDateModelAndConfig_knowsIdAndModelAndConfig() {
        LocalDateTextFieldConfig cfg = new LocalDateTextFieldConfig().withFormat("dd.MM.yyyy");

        LocalDateTextField f = new LocalDateTextField("tf", Model.of(getNow()), cfg);

        assertThat(f.getId(), is(equalTo("tf")));
        assertThat(f.getModelObject(), is(equalTo(getNow())));

        LocalDateTextFieldConfig config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo("dd.MM.yyyy")));
        assertThat(config, is(equalTo(cfg)));
    }

}
