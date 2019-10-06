package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.WicketApplicationTest;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.markup.html.form.AbstractTextComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

/**
 * Abstract base class for the DateTextField tests.
 *
 * @param <T>
 *     the type of date ({@code java.util.Date} or {@code java.time.LocalDate})
 * @param <P>
 *     the type of the parent wicket {@code TextField} working with date of type {@code T}
 * @param <I>
 *     the input date type for the configuration options withBeginDate or withEndDate
 * @param <C>
 *     the configuration - the concrete implementation of the AbstractDateTextFieldConfig
 * @param <F>
 *     the concrete implementation type of this abstract class
 */
abstract class AbstractDateTextFieldTest<T, P extends TextField<T> & AbstractTextComponent.ITextFormatProvider, I, C extends AbstractDateTextFieldConfig<C, I>, F extends AbstractDateTextField<T, P, I, C, F>>
    extends WicketApplicationTest {

    private final AbstractDateTextField.IParentAjaxEventHandler handler = (AbstractDateTextField.IParentAjaxEventHandler) (target, date, event) -> {
    };

    /**
     * @return a new default configuration with no specific properties set.
     */
    abstract C newDefaultConfig();

    /**
     * @return a date of type {@code D} representing {@literal now}
     */
    abstract T getNow();

    @Test
    void assertDefaultLocalDateTextFieldConfig_hasLanguageEnglish() {
        assertThat(newDefaultConfig().getLanguage(), is(equalTo("en")));
    }

    @Test
    void assertDefaultLocalDateTextFieldConfig_hasAmericanDateFormat() {
        assertThat(newDefaultConfig().getFormat(), is(equalTo("MM/dd/yyyy")));
    }

    @Test
    void constructing_withIdOnly_knowsIdAndDefaultConfig() {
        F f = newTextField();

        assertThat(f.getId(), is(equalTo("tf")));
        assertThat(f.getModelObject(), is(equalTo(null)));

        hasDefaultModel(f);
    }

    abstract F newTextField();

    void hasDefaultModel(final F f) {
        C config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo(newDefaultConfig().getFormat())));
        assertThat(config.getLanguage(), is(equalTo(newDefaultConfig().getLanguage())));
    }

    @Test
    void canReplaceConfig() {
        F f = newTextField();
        assertThat(f
            .getConfig()
            .getFormat(), is(equalTo("MM/dd/yyyy")));

        f.with(newDefaultConfig().withFormat("dd.MM.yyyy"));

        assertThat(f
            .getConfig()
            .getFormat(), is(equalTo("dd.MM.yyyy")));
    }

    @Test
    void cannotReplaceConfig_withNullConfig() {
        F f = newTextField();
        f.with(newDefaultConfig().withFormat("dd.MM.yyyy"));
        f.with(null);
        assertThat(f
            .getConfig()
            .getFormat(), is(equalTo("dd.MM.yyyy")));
    }

    @Test
    void onInitialize_setsOutputMarkupId() {
        F f = newTextField();
        assertThat(f.getOutputMarkupId(), is(false));
        f.onInitialize();
        assertThat(f.getOutputMarkupId(), is(true));
    }

    @Test
    void gettingDestroyScript() {
        F f = newTextField();
        assertThat(f.getDestroyScript(), is(equalTo("$('#tf1').datepicker('destroy');")));
    }

    @Test
    void startingComponent_withoutInputTagAttached_throwsMarkupException() {
        F f = newTextField();
        try {
            tester().startComponentInPage(f);
            fail("should have thrown exception");
        } catch (Exception ex) {
            assertThat(ex.getClass(), is(equalTo(MarkupException.class)));
            assertThat(ex.getMessage(), is(equalTo(
                "Component [tf] (path = [0:tf]) must be applied to a tag of type [input], not:  '<span wicket:id=\"tf\">' (line 0, column 0)")));
        }
    }

    @Test
    void startingComponent_withFieldAssignedToInput_succeedsAndHasTypeText() {
        LocalDateTextField f = new LocalDateTextField("myId");
        tester().startComponentInPage(f, Markup.of("<input wicket:id='myId'></input>"));

        String responseTxt = tester()
            .getLastResponse()
            .getDocument();
        assertThat(TagTester.createTagByAttribute(responseTxt, "type", "text"), is(notNullValue()));
        tester().assertNoErrorMessage();
    }

    @Test
    void creatingScript_withDefaultConfig() {
        CharSequence script = newTextField().createScript(newDefaultConfig());
        assertThat(script, is(equalTo("$('#tf1').datepicker();")));
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    void creatingScript_withExplicitConfig_withNonDefaultValues() {
        // @formatter:off
        final String expectedScript =
            "$('#tf1').datepicker({"
                + "\"format\":\"dd.mm.yyyy\","
                + "\"language\":\"de\","
                + "\"startDate\":\"30.06.2018\","
                + "\"minViewMode\":4,"
                + "\"endDate\":\"13.07.2018\","
                + "\"startView\":3,"
                + "\"weekStart\":1,"
                + "\"keyboardNavigation\":false,"
                + "\"todayHighlight\":true,"
                + "\"todayBtn\":\"linked\","
                + "\"forceParse\":false,"
                + "\"clearBtn\":true,"
                + "\"calendarWeeks\":true,"
                + "\"autoclose\":true,"
                + "\"multidate\":true"
            + "});";
        // @formatter:on
        C config = newDefaultConfig()
            .withFormat("dd.MM.yyyy")
            .withLanguage("de")
            .withStartDate(getStartDate())
            .withMinViewMode(LocalDateTextFieldConfig.View.Day)
            .withEndDate(getEndDate())
            .withView(LocalDateTextFieldConfig.View.Day)
            .withWeekStart(LocalDateTextFieldConfig.Day.Monday)
            .allowKeyboardNavigation(false)
            .highlightToday(true)
            .showTodayButton(LocalDateTextFieldConfig.TodayButton.LINKED)
            .forceParse(false)
            .clearButton(true)
            .calendarWeeks(true)
            .autoClose(true)
            .withMulti(true);
        CharSequence script = newTextField().createScript(config);
        assertThat(script, is(equalTo(expectedScript)));
    }

    abstract I getStartDate();

    abstract I getEndDate();

    @Test
    void creatingScript_withExplicitConfig_skipsDefaultValuesExplicitlySet() {
        // @formatter:off
        final String expectedScript =
            "$('#tf1').datepicker({"
                + "\"format\":\"yyyy-mm-dd\","
                + "\"language\":\"fr\","
                + "\"startDate\":\"2018-06-30\","
                + "\"minViewMode\":1,"
                + "\"endDate\":\"2018-07-13\","
                + "\"startView\":2,"
                + "\"weekStart\":2"
            + "});";
        // @formatter:on
        C config = newDefaultConfig()
            .withFormat("yyyy-MM-dd")
            .withLanguage("fr")
            .withStartDate(getStartDate())
            .withMinViewMode(LocalDateTextFieldConfig.View.Month)
            .withEndDate(getEndDate())
            .withView(LocalDateTextFieldConfig.View.Decade)
            .withWeekStart(LocalDateTextFieldConfig.Day.Tuesday)
            .allowKeyboardNavigation(true)
            .highlightToday(false)
            .showTodayButton(LocalDateTextFieldConfig.TodayButton.FALSE)
            .forceParse(true)
            .clearButton(false)
            .calendarWeeks(false)
            .autoClose(false)
            .withMulti(false);
        CharSequence script = newTextField().createScript(config);
        assertThat(script, is(equalTo(expectedScript)));
    }

    @Test
    void creatingScript_withExplicitConfigWithoutExplicitFormat_usesUsFormat() {
        // @formatter:off
        final String expectedScript =
            "$('#tf1').datepicker({"
                + "\"language\":\"es\","
                + "\"startDate\":\"06/30/2018\","
                + "\"todayBtn\":true,"
                + "\"endDate\":\"07/13/2018\""
            + "});";
        // @formatter:on
        C config = newDefaultConfig()
            .withLanguage("es")
            .withStartDate(getStartDate())
            .showTodayButton(LocalDateTextFieldConfig.TodayButton.TRUE)
            .withEndDate(getEndDate());
        CharSequence script = newTextField().createScript(config);
        assertThat(script, is(equalTo(expectedScript)));
    }

    @Test
    void addingEvent_addsToScript() {
        LocalDateTextField.AbstractEventHandler handler = new LocalDateTextField.AbstractEventHandler() {

            @Override
            protected CharSequence getBody() {
                return "eventBody";
            }
        };
        F f = newTextField().addEvent(LocalDateTextField.Event.clearDate, handler);
        String script = f
            .createScript(newDefaultConfig())
            .toString();
        assertThat(script.replaceAll("\\n", ""),
            is(equalTo("$('#tf1').datepicker().on('clearDate',function(e) {eventBody});")));
    }

    @Test
    void canAddAndRemoveEvent() {
        LocalDateTextField.AbstractEventHandler handler1 = new LocalDateTextField.AbstractEventHandler() {

            @Override
            protected CharSequence getBody() {
                return "eventBody1";
            }
        };
        LocalDateTextField.AbstractEventHandler handler2 = new LocalDateTextField.AbstractEventHandler() {

            @Override
            protected CharSequence getBody() {
                return "eventBody2";
            }
        };
        F f = newTextField()
            .addEvent(LocalDateTextField.Event.clearDate, handler1)
            .addEvent(LocalDateTextField.Event.changeYear, handler2);
        String script = f
            .createScript(newDefaultConfig())
            .toString();
        assertThat(script.replaceAll("\\n", ""), startsWith("$('#tf1').datepicker()"));
        assertThat(script.replaceAll("\\n", ""), containsString(".on('clearDate',function(e) {eventBody1})"));
        assertThat(script.replaceAll("\\n", ""), containsString(".on('changeYear',function(e) {eventBody2})"));
        f.removeEvent(LocalDateTextField.Event.clearDate);
        script = f
            .createScript(newDefaultConfig())
            .toString();
        assertThat(script.replaceAll("\\n", ""),
            is(equalTo("$('#tf1').datepicker().on('changeYear',function(e) {eventBody2});")));

    }

    @Test
    void addingAjaxEvent_addsToScript() {
        // @formatter:off
        final String expectedScript =
            "$('#tf1').datepicker()"
                + ".on('clearDate',function(e) {\n"
                    + "Wicket.Ajax.ajax({"
                        + "\"u\":\"./wicket/page?0-1.0-tf\","
                        + "\"c\":\"tf1\","
                        + "\"ep\":[{\"name\":\"date\",\"value\":e.format()},{\"name\":\"datePickerEvent\",\"value\":\"clearDate\"}]"
                    + "});\n"
                + "});";
        // @formatter:on

        F f = newTextField().addAjaxEvent(LocalDateTextField.Event.clearDate, handler);

        tester().startComponentInPage(f, Markup.of("<input wicket:id='tf'></input>"));

        String script = f
            .createScript(newDefaultConfig())
            .toString();

        assertThat(script, is(equalTo(expectedScript)));
    }

    @Test
    void addingAjaxEvent1() {
        F f = newTextField().addAjaxEvent(LocalDateTextField.Event.clearDate, handler);
        tester().startComponentInPage(f, Markup.of("<input wicket:id='tf'></input>"));
        tester().executeAjaxEvent("tf", "clearDate");
        // TODO need to find something to assert the ajax event
    }
}
