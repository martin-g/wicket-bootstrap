package de.agilecoders.wicket.markup.html.bootstrap.form;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.references.BootstrapDatepickerJsReference;
import de.agilecoders.wicket.markup.html.references.BootstrapDatepickerReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Date;

/**
 * A TextField that is mapped to a <code>java.util.Date</code> object.
 * <p/>
 * If no date pattern is explicitly specified, the default <code>DateFormat.SHORT</code> pattern for
 * the current locale will be used.
 *
 * @author miha
 * @version 1.0
 */
public class DateTextField extends org.apache.wicket.extensions.markup.html.form.DateTextField {

    /**
     * holds all week days in a specific sort order.
     */
    public enum Day {
        Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
    }

    private final IModel<Day> day = Model.of(Day.Sunday);

    /**
     * Construct.
     *
     * @param markupId The id of the text field
     */
    public DateTextField(final String markupId) {
        super(markupId);
    }

    /**
     * Construct.
     *
     * @param markupId The id of the text field
     * @param model    The date model
     */
    public DateTextField(final String markupId, final IModel<Date> model) {
        super(markupId, model);
    }

    /**
     * Construct.
     *
     * @param markupId   The id of the text field
     * @param model      The date model
     * @param dateFormat The format of the date
     */
    public DateTextField(final String markupId, final IModel<Date> model, final String dateFormat) {
        super(markupId, model, dateFormat);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setOutputMarkupId(true);
        add(new AssertTagNameBehavior("input"));
    }

    /**
     * sets the week start day
     *
     * @param day The day to start the week
     * @return this component's instance
     */
    public DateTextField setWeekStart(final Day day) {
        this.day.setObject(day);

        return this;
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(BootstrapDatepickerReference.INSTANCE));
        response.render(JavaScriptHeaderItem.forReference(BootstrapDatepickerJsReference.INSTANCE));
        response.render(OnDomReadyHeaderItem.forScript(createScript()));
    }

    /**
     * creates the initializer script.
     *
     * @return initializer script
     */
    protected CharSequence createScript() {
        return "$('#" + getMarkupId() + "').datepicker({"
               + "    weekStart: " + getWeekStart() + ","
               + "    format: '" + getTextFormat() + "'"
               + "})";
    }

    /**
     * @return the week start as int.
     */
    private int getWeekStart() {
        return day.getObject() != null ? day.getObject().ordinal() : Day.Sunday.ordinal();
    }
}
