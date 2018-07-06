package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.WicketApplicationTest;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

/**
 * @author Urs Joss
 */
public class LocalDateTextFieldTest extends WicketApplicationTest {

    private final LocalDateTextFieldConfig defaultConfig = new LocalDateTextFieldConfig();

    private final LocalDate now = LocalDate.now();

    private final LocalDateTextField.IAjaxEventHandler handler = new LocalDateTextField.IAjaxEventHandler() {

        @Override
        public void onAjaxEvent(final AjaxRequestTarget target, final LocalDate date,
            final LocalDateTextField.Event event) {
        }
    };

    @Test
    public void assertDefaultLocalDateTextFieldConfig_hasLanguageEnglish() {
        assertThat(defaultConfig.getLanguage(), is(equalTo("en")));
    }

    @Test
    public void assertDefaultLocalDateTextFieldConfig_hasAmericanDateFormat() {
        assertThat(defaultConfig.getFormat(), is(equalTo("MM/dd/yyyy")));
    }

    @Test
    public void constructing_withIdOnly_knowsIdAndDefaultConfig() {
        LocalDateTextField f = new LocalDateTextField("id");

        assertThat(f.getId(), is(equalTo("id")));
        assertThat(f.getModelObject(), is(equalTo(null)));

        hasDefaultModel(f);
    }

    private void hasDefaultModel(final LocalDateTextField f) {
        LocalDateTextFieldConfig config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo(defaultConfig.getFormat())));
        assertThat(config.getLanguage(), is(equalTo(defaultConfig.getLanguage())));
    }

    @Test
    public void constuctingt_withIdAndDatePattern_knowsIdAndDatePattern() {
        LocalDateTextField f = new LocalDateTextField("id", "dd.MM.yyyy");

        assertThat(f.getId(), is(equalTo("id")));
        assertThat(f.getModelObject(), is(equalTo(null)));

        LocalDateTextFieldConfig config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo("dd.MM.yyyy")));
        assertThat(config.getLanguage(), is(equalTo(defaultConfig.getLanguage())));
    }

    @Test
    public void canConstruct_withIdAndDateModel_knowsIdAndDateModel() {
        LocalDateTextField f = new LocalDateTextField("id", Model.of(now));

        assertThat(f.getId(), is(equalTo("id")));
        assertThat(f.getModelObject(), is(equalTo(now)));

        hasDefaultModel(f);
    }

    @Test
    public void constuctingt_withIdAndDateModelAndDatePattern_knowsIdAndDateModelAndDatePattern() {
        LocalDateTextField f = new LocalDateTextField("id", Model.of(now), "dd.MM.yyyy");

        assertThat(f.getId(), is(equalTo("id")));
        assertThat(f.getModelObject(), is(equalTo(now)));

        LocalDateTextFieldConfig config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo("dd.MM.yyyy")));
        assertThat(config.getLanguage(), is(equalTo(defaultConfig.getLanguage())));
    }

    @Test
    public void constuctingt_withIdAndDateModelAndConfig_knowsIdAndModelAndConfig() {
        LocalDateTextFieldConfig cfg = new LocalDateTextFieldConfig().withFormat("dd.MM.yyyy");

        LocalDateTextField f = new LocalDateTextField("id", Model.of(now), cfg);

        assertThat(f.getId(), is(equalTo("id")));
        assertThat(f.getModelObject(), is(equalTo(now)));

        LocalDateTextFieldConfig config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo("dd.MM.yyyy")));
        assertThat(config, is(equalTo(cfg)));
    }

    @Test(expected = NullPointerException.class)
    public void constructing_withIdAndDateModelAndNullConfig_throwsNPE() {
        // TODO should ideally throw IllegalArgumentException from Args.notNull
        new LocalDateTextField("id", Model.of(now), (LocalDateTextFieldConfig) null);
    }

    @Test(expected = NullPointerException.class)
    public void constructing_withIdAndNullConfig_throwsNPE() {
        // TODO should ideally throw IllegalArgumentException from Args.notNull
        new LocalDateTextField("id", (LocalDateTextFieldConfig) null);
    }

    @Test
    public void canReplaceConfig() {
        LocalDateTextField f = new LocalDateTextField("id");
        assertThat(f
            .getConfig()
            .getFormat(), is(equalTo("MM/dd/yyyy")));

        f.with(new LocalDateTextFieldConfig().withFormat("dd.MM.yyyy"));

        assertThat(f
            .getConfig()
            .getFormat(), is(equalTo("dd.MM.yyyy")));
    }

    @Test
    public void cannotReplaceConfig_withNullConfig() {
        LocalDateTextField f = new LocalDateTextField("id");
        f.with(new LocalDateTextFieldConfig().withFormat("dd.MM.yyyy"));
        f.with(null);
        assertThat(f
            .getConfig()
            .getFormat(), is(equalTo("dd.MM.yyyy")));
    }

    @Test
    public void onInitialize_setsOutputMarkupId() {
        LocalDateTextField f = new LocalDateTextField("id");
        assertThat(f.getOutputMarkupId(), is(false));
        f.onInitialize();
        assertThat(f.getOutputMarkupId(), is(true));
    }

    @Test
    public void gettingDestroyScript() {
        LocalDateTextField f = new LocalDateTextField("id");
        assertThat(f.getDestroyScript(), is(equalTo("$('#id1').datepicker('destroy');")));
    }

    @Test
    public void startingComponent_withoutInputTagAttached_throwsMarkupException() {
        LocalDateTextField f = new LocalDateTextField("id");
        try {
            tester().startComponentInPage(f);
            fail("should have thrown exception");
        } catch (Exception ex) {
            assertThat(ex.getClass(), is(equalTo(MarkupException.class)));
            assertThat(ex.getMessage(), is(equalTo(
                "Component [id] (path = [0:id]) must be applied to a tag of type [input], not:  '<span wicket:id=\"id\">' (line 0, column 0)")));
        }
    }

    @Test
    public void startingComponent_withFieldAssignedToInput_succeedsAndHasTypeText() {
        LocalDateTextField f = new LocalDateTextField("myId");
        tester().startComponentInPage(f, Markup.of("<input wicket:id='myId'></input>"));

        String responseTxt = tester()
            .getLastResponse()
            .getDocument();
        assertThat(TagTester.createTagByAttribute(responseTxt, "type", "text"), is(notNullValue()));
        tester().assertNoErrorMessage();
    }

    @Test
    public void creatingScript_withDefaultConfig() {
        CharSequence script = new LocalDateTextField("ldtf").createScript(defaultConfig);
        assertThat(script, is(equalTo("$('#ldtf1').datepicker();")));
    }

    @Test
    public void creatingScript_withExplicitConfig_withNonDefaultValues() {
        // @formatter:off
        final String expectedScript =
            "$('#ldtf1').datepicker({"
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
        LocalDateTextFieldConfig config = new LocalDateTextFieldConfig()
            .withFormat("dd.MM.yyyy")
            .withLanguage("de")
            .withStartDate(LocalDateTime.parse("2018-06-30T01:02:03.000"))
            .withMinViewMode(LocalDateTextFieldConfig.View.Day)
            .withEndDate(LocalDateTime.parse("2018-07-13T04:05:06.000"))
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
        CharSequence script = new LocalDateTextField("ldtf").createScript(config);
        assertThat(script, is(equalTo(expectedScript)));
    }

    @Test
    public void creatingScript_withExplicitConfig_skipsDefaultValuesExcplicitlySet() {
        // @formatter:off
        final String expectedScript =
            "$('#ldtf1').datepicker({"
                + "\"format\":\"yyyy-mm-dd\","
                + "\"language\":\"fr\","
                + "\"startDate\":\"2018-06-30\","
                + "\"minViewMode\":1,"
                + "\"endDate\":\"2018-07-13\","
                + "\"startView\":2,"
                + "\"weekStart\":2"
            + "});";
        // @formatter:on
        LocalDateTextFieldConfig config = new LocalDateTextFieldConfig()
            .withFormat("yyyy-MM-dd")
            .withLanguage("fr")
            .withStartDate(LocalDateTime.parse("2018-06-30T01:02:03.000"))
            .withMinViewMode(LocalDateTextFieldConfig.View.Month)
            .withEndDate(LocalDateTime.parse("2018-07-13T04:05:06.000"))
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
        CharSequence script = new LocalDateTextField("ldtf").createScript(config);
        assertThat(script, is(equalTo(expectedScript)));
    }

    @Test
    public void creatingScript_withExplicitConfigWithoutExplicitFormat_usesUsFormat() {
        // @formatter:off
        final String expectedScript =
            "$('#ldtf1').datepicker({"
                + "\"language\":\"es\","
                + "\"startDate\":\"06/30/2018\","
                + "\"todayBtn\":true,"
                + "\"endDate\":\"07/13/2018\""
            + "});";
        // @formatter:on
        LocalDateTextFieldConfig config = new LocalDateTextFieldConfig()
            .withLanguage("es")
            .withStartDate(LocalDateTime.parse("2018-06-30T01:02:03.000"))
            .showTodayButton(LocalDateTextFieldConfig.TodayButton.TRUE)
            .withEndDate(LocalDateTime.parse("2018-07-13T04:05:06.000"));
        CharSequence script = new LocalDateTextField("ldtf").createScript(config);
        assertThat(script, is(equalTo(expectedScript)));
    }

    @Test
    public void creatingScript_withClearedFormat_usesUnprocessedDates() {
        // @formatter:off
        final String expectedScript =
            "$('#ldtf1').datepicker({"
                + "\"language\":\"es\","
                + "\"format\":\"\","
                + "\"startDate\":\"2018-06-30T01:02:03\","
                + "\"endDate\":\"2018-07-13T04:05:06\""
            + "});";
        // @formatter:on
        LocalDateTextFieldConfig config = new LocalDateTextFieldConfig()
            .withLanguage("es")
            .withFormat(null)
            .withStartDate(LocalDateTime.parse("2018-06-30T01:02:03.000"))
            .withEndDate(LocalDateTime.parse("2018-07-13T04:05:06.000"));
        CharSequence script = new LocalDateTextField("ldtf").createScript(config);
        assertThat(script, is(equalTo(expectedScript)));
    }

    @Test
    public void addingEvent_addsToScript() {
        LocalDateTextField.AbstractEventHandler handler = new LocalDateTextField.AbstractEventHandler() {

            @Override
            protected CharSequence getBody() {
                return "eventBody";
            }
        };
        LocalDateTextField f = new LocalDateTextField("ldtf").addEvent(LocalDateTextField.Event.clearDate, handler);
        String script = f
            .createScript(defaultConfig)
            .toString();
        assertThat(script.replaceAll("\\n", ""),
            is(equalTo("$('#ldtf1').datepicker().on('clearDate',function(e) {eventBody});")));
    }

    @Test
    public void canAddAndRemoveEvent() {
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
        LocalDateTextField f = new LocalDateTextField("ldtf")
            .addEvent(LocalDateTextField.Event.clearDate, handler1)
            .addEvent(LocalDateTextField.Event.changeYear, handler2);
        String script = f
            .createScript(defaultConfig)
            .toString();
        assertThat(script.replaceAll("\\n", ""), startsWith("$('#ldtf1').datepicker()"));
        assertThat(script.replaceAll("\\n", ""), containsString(".on('clearDate',function(e) {eventBody1})"));
        assertThat(script.replaceAll("\\n", ""), containsString(".on('changeYear',function(e) {eventBody2})"));
        f.removeEvent(LocalDateTextField.Event.clearDate);
        script = f
            .createScript(defaultConfig)
            .toString();
        assertThat(script.replaceAll("\\n", ""),
            is(equalTo("$('#ldtf1').datepicker().on('changeYear',function(e) {eventBody2});")));

    }

    @Test
    public void addingAjaxEvent_addsToScript() {
        // @formatter:off
        final String expectedScript =
            "$('#ldtf1').datepicker()"
                + ".on('clearDate',function(e) {\n"
                    + "Wicket.Ajax.ajax({"
                        + "\"u\":\"./wicket/page?0-1.0-ldtf\","
                        + "\"c\":\"ldtf1\","
                        + "\"ep\":[{\"name\":\"date\",\"value\":e.format()},{\"name\":\"datePickerEvent\",\"value\":\"clearDate\"}]"
                    + "});\n"
                + "});";
        // @formatter:on

        LocalDateTextField f = new LocalDateTextField("ldtf").addAjaxEvent(LocalDateTextField.Event.clearDate, handler);

        tester().startComponentInPage(f, Markup.of("<input wicket:id='ldtf'></input>"));

        String script = f
            .createScript(defaultConfig)
            .toString();

        assertThat(script, is(equalTo(expectedScript)));
    }

    @Test
    public void addingAjaxEvent1() {
        LocalDateTextField f = new LocalDateTextField("ldtf").addAjaxEvent(LocalDateTextField.Event.clearDate, handler);
        tester().startComponentInPage(f, Markup.of("<input wicket:id='ldtf'></input>"));
        tester().executeAjaxEvent("ldtf", "clearDate");
    }

}
