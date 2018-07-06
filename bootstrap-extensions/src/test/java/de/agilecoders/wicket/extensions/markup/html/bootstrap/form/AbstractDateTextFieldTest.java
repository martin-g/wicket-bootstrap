package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.WicketApplicationTest;
import org.junit.Test;

public abstract class AbstractDateTextFieldTest<C extends AbstractDateTextFieldConfig, D>
    extends WicketApplicationTest {

    /**
     * @return a new default configuration with no specific properties set.
     */
    abstract C newDefaultConfig();

    /**
     * @return a date of type {@code D} representing {@literal now}
     */
    abstract D getNow();


    @Test
    public void assertDefaultLocalDateTextFieldConfig_hasLanguageEnglish() {
        assertThat(newDefaultConfig().getLanguage(), is(equalTo("en")));
    }

    @Test
    public void assertDefaultLocalDateTextFieldConfig_hasAmericanDateFormat() {
        assertThat(newDefaultConfig().getFormat(), is(equalTo("MM/dd/yyyy")));
    }
}
