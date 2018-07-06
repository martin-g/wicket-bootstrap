package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import static org.hamcrest.Matchers.*;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.joda.time.DateTime;
import org.junit.Test;

/**
 * @author Urs Joss
 */
public class DateTextFieldTest extends AbstractDateTextFieldTest<DateTextFieldConfig, Date> {

    @Override
    DateTextFieldConfig newDefaultConfig() {
        return new DateTextFieldConfig();
    }

    @Override
    Date getNow() {
        return new Date();
    }

    private final DateTextField.IAjaxEventHandler handler = new DateTextField.IAjaxEventHandler() {

        @Override
        public void onAjaxEvent(final AjaxRequestTarget target, final Date date, final DateTextField.Event event) {
        }
    };

    @Test
    public void constructing_withIdOnly_knowsIdAndDefaultConfig() {
        DateTextField f = new DateTextField("tf");

        assertThat(f.getId(), is(equalTo("tf")));
        assertThat(f.getModelObject(), is(equalTo(null)));

        hasDefaultModel(f);
    }

    private void hasDefaultModel(final DateTextField f) {
        DateTextFieldConfig config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo(newDefaultConfig().getFormat())));
        assertThat(config.getLanguage(), is(equalTo(newDefaultConfig().getLanguage())));
    }

    @Test
    public void constuctingt_withIdAndDatePattern_knowsIdAndDatePattern() {
        DateTextField f = new DateTextField("tf", "dd.MM.yyyy");

        assertThat(f.getId(), is(equalTo("tf")));
        assertThat(f.getModelObject(), is(equalTo(null)));

        DateTextFieldConfig config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo("dd.MM.yyyy")));
        assertThat(config.getLanguage(), is(equalTo(newDefaultConfig().getLanguage())));
    }

    @Test
    public void canConstruct_withIdAndDateModel_knowsIdAndDateModel() {
        DateTextField f = new DateTextField("tf", Model.of(getNow()));

        assertThat(f.getId(), is(equalTo("tf")));
        assertThat(f.getModelObject(), is(equalTo(getNow())));

        hasDefaultModel(f);
    }

    @Test
    public void constuctingt_withIdAndDateModelAndDatePattern_knowsIdAndDateModelAndDatePattern() {
        DateTextField f = new DateTextField("tf", Model.of(getNow()), "dd.MM.yyyy");

        assertThat(f.getId(), is(equalTo("tf")));
        assertThat(f.getModelObject(), is(equalTo(getNow())));

        DateTextFieldConfig config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo("dd.MM.yyyy")));
        assertThat(config.getLanguage(), is(equalTo(newDefaultConfig().getLanguage())));
    }

    @Test
    public void constuctingt_withIdAndDateModelAndConfig_knowsIdAndModelAndConfig() {
        DateTextFieldConfig cfg = new DateTextFieldConfig().withFormat("dd.MM.yyyy");

        DateTextField f = new DateTextField("tf", Model.of(getNow()), cfg);

        assertThat(f.getId(), is(equalTo("tf")));
        assertThat(f
            .getModelObject()
            .toString(), is(equalTo(getNow().toString())));

        DateTextFieldConfig config = f.getConfig();
        assertThat(config.getFormat(), is(equalTo("dd.MM.yyyy")));
        assertThat(config, is(equalTo(cfg)));
    }

    @Test(expected = NullPointerException.class)
    public void constructing_withIdAndDateModelAndNullConfig_throwsNPE() {
        // TODO should ideally throw IllegalArgumentException from Args.notNull
        new DateTextField("tf", Model.of(getNow()), (DateTextFieldConfig) null);
    }

    @Test(expected = NullPointerException.class)
    public void constructing_withIdAndNullConfig_throwsNPE() {
        // TODO should ideally throw IllegalArgumentException from Args.notNull
        new DateTextField("tf", (DateTextFieldConfig) null);
    }

    @Test
    public void canReplaceConfig() {
        DateTextField f = new DateTextField("tf");
        assertThat(f
            .getConfig()
            .getFormat(), is(equalTo("MM/dd/yyyy")));

        f.with(new DateTextFieldConfig().withFormat("dd.MM.yyyy"));

        assertThat(f
            .getConfig()
            .getFormat(), is(equalTo("dd.MM.yyyy")));
    }

    @Test
    public void cannotReplaceConfig_withNullConfig() {
        DateTextField f = new DateTextField("tf");
        f.with(new DateTextFieldConfig().withFormat("dd.MM.yyyy"));
        f.with(null);
        assertThat(f
            .getConfig()
            .getFormat(), is(equalTo("dd.MM.yyyy")));
    }

    @Test
    public void onInitialize_setsOutputMarkupId() {
        DateTextField f = new DateTextField("tf");
        assertThat(f.getOutputMarkupId(), is(false));
        f.onInitialize();
        assertThat(f.getOutputMarkupId(), is(true));
    }

    @Test
    public void gettingDestroyScript() {
        DateTextField f = new DateTextField("tf");
        assertThat(f.getDestroyScript(), is(equalTo("$('#tf1').datepicker('destroy');")));
    }

    @Test
    public void startingComponent_withoutInputTagAttached_throwsMarkupException() {
        DateTextField f = new DateTextField("tf");
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
    public void startingComponent_withFieldAssignedToInput_succeedsAndHasTypeText() {
        DateTextField f = new DateTextField("myId");
        tester().startComponentInPage(f, Markup.of("<input wicket:id='myId'></input>"));

        String responseTxt = tester()
            .getLastResponse()
            .getDocument();
        assertThat(TagTester.createTagByAttribute(responseTxt, "type", "text"), is(notNullValue()));
        tester().assertNoErrorMessage();
    }

    @Test
    public void creatingScript_withDefaultConfig() {
        CharSequence script = new DateTextField("ldtf").createScript(newDefaultConfig());
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
        DateTextFieldConfig config = new DateTextFieldConfig()
            .withFormat("dd.MM.yyyy")
            .withLanguage("de")
            .withStartDate(DateTime.parse("2018-06-30T01:02:03.000"))
            .withMinViewMode(DateTextFieldConfig.View.Day)
            .withEndDate(DateTime.parse("2018-07-13T04:05:06.000"))
            .withView(DateTextFieldConfig.View.Day)
            .withWeekStart(DateTextFieldConfig.Day.Monday)
            .allowKeyboardNavigation(false)
            .highlightToday(true)
            .showTodayButton(DateTextFieldConfig.TodayButton.LINKED)
            .forceParse(false)
            .clearButton(true)
            .calendarWeeks(true)
            .autoClose(true)
            .withMulti(true);
        CharSequence script = new DateTextField("ldtf").createScript(config);
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
        DateTextFieldConfig config = new DateTextFieldConfig()
            .withFormat("yyyy-MM-dd")
            .withLanguage("fr")
            .withStartDate(DateTime.parse("2018-06-30T01:02:03.000"))
            .withMinViewMode(DateTextFieldConfig.View.Month)
            .withEndDate(DateTime.parse("2018-07-13T04:05:06.000"))
            .withView(DateTextFieldConfig.View.Decade)
            .withWeekStart(DateTextFieldConfig.Day.Tuesday)
            .allowKeyboardNavigation(true)
            .highlightToday(false)
            .showTodayButton(DateTextFieldConfig.TodayButton.FALSE)
            .forceParse(true)
            .clearButton(false)
            .calendarWeeks(false)
            .autoClose(false)
            .withMulti(false);
        CharSequence script = new DateTextField("ldtf").createScript(config);
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
        DateTextFieldConfig config = new DateTextFieldConfig()
            .withLanguage("es")
            .withStartDate(DateTime.parse("2018-06-30T01:02:03.000"))
            .showTodayButton(DateTextFieldConfig.TodayButton.TRUE)
            .withEndDate(DateTime.parse("2018-07-13T04:05:06.000"));
        CharSequence script = new DateTextField("ldtf").createScript(config);
        assertThat(script, is(equalTo(expectedScript)));
    }

    @Test
    public void creatingScript_withClearedFormat_usesUnprocessedDates() {
        // @formatter:off
        final String expectedScript =
            "$('#ldtf1').datepicker({"
                + "\"language\":\"es\","
                + "\"format\":\"\","
                + "\"startDate\":\"2018-06-30T01:02:03.000+02:00\","
                + "\"endDate\":\"2018-07-13T04:05:06.000+02:00\""
            + "});";
        // @formatter:on
        DateTextFieldConfig config = new DateTextFieldConfig()
            .withLanguage("es")
            .withFormat(null)
            .withStartDate(DateTime.parse("2018-06-30T01:02:03.000"))
            .withEndDate(DateTime.parse("2018-07-13T04:05:06.000"));
        CharSequence script = new DateTextField("ldtf").createScript(config);
        assertThat(script, is(equalTo(expectedScript)));
    }

    @Test
    public void addingEvent_addsToScript() {
        DateTextField.AbstractEventHandler handler = new DateTextField.AbstractEventHandler() {

            @Override
            protected CharSequence getBody() {
                return "eventBody";
            }
        };
        DateTextField f = new DateTextField("ldtf").addEvent(DateTextField.Event.clearDate, handler);
        String script = f
            .createScript(newDefaultConfig())
            .toString();
        assertThat(script.replaceAll("\\n", ""),
            is(equalTo("$('#ldtf1').datepicker().on('clearDate',function(e) {eventBody});")));
    }

    @Test
    public void canAddAndRemoveEvent() {
        DateTextField.AbstractEventHandler handler1 = new DateTextField.AbstractEventHandler() {

            @Override
            protected CharSequence getBody() {
                return "eventBody1";
            }
        };
        DateTextField.AbstractEventHandler handler2 = new DateTextField.AbstractEventHandler() {

            @Override
            protected CharSequence getBody() {
                return "eventBody2";
            }
        };
        DateTextField f = new DateTextField("ldtf")
            .addEvent(DateTextField.Event.clearDate, handler1)
            .addEvent(DateTextField.Event.changeYear, handler2);
        String script = f
            .createScript(newDefaultConfig())
            .toString();
        assertThat(script.replaceAll("\\n", ""), startsWith("$('#ldtf1').datepicker()"));
        assertThat(script.replaceAll("\\n", ""), containsString(".on('clearDate',function(e) {eventBody1})"));
        assertThat(script.replaceAll("\\n", ""), containsString(".on('changeYear',function(e) {eventBody2})"));
        f.removeEvent(DateTextField.Event.clearDate);
        script = f
            .createScript(newDefaultConfig())
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

        DateTextField f = new DateTextField("ldtf").addAjaxEvent(DateTextField.Event.clearDate, handler);

        tester().startComponentInPage(f, Markup.of("<input wicket:id='ldtf'></input>"));

        String script = f
            .createScript(newDefaultConfig())
            .toString();

        assertThat(script, is(equalTo(expectedScript)));
    }

    @Test
    public void addingAjaxEvent1() {
        DateTextField f = new DateTextField("ldtf").addAjaxEvent(DateTextField.Event.clearDate, handler);
        tester().startComponentInPage(f, Markup.of("<input wicket:id='ldtf'></input>"));
        tester().executeAjaxEvent("ldtf", "clearDate");
        // TODO need to find something to assert the ajax event
    }

}
