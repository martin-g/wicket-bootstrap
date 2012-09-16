package de.agilecoders.wicket.markup.html.bootstrap.form;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.references.BootstrapDatepickerJsReference;
import de.agilecoders.wicket.markup.html.references.BootstrapDatepickerReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class DateTextField extends TextField<String> {

    public enum Day {
        Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
    }

    private Day day = Day.Sunday;
    private String dateFormat = "mm/dd/yyyy";

    public DateTextField(String id) {
        super(id);
    }

    public DateTextField(String id, IModel<String> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setOutputMarkupId(true);
        add(new AssertTagNameBehavior("input"));
    }

    public DateTextField weekStart(final Day day) {
        this.day = day;
        return this;
    }

    public DateTextField dateFormat(final String dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(BootstrapDatepickerReference.INSTANCE));
        response.render(JavaScriptHeaderItem.forReference(BootstrapDatepickerJsReference.INSTANCE));
        response.render(OnDomReadyHeaderItem.forScript(createScript()));
    }

    protected CharSequence createScript() {
        return "$('#" + getMarkupId() + "').datepicker({"
               + "    weekStart: " + day.ordinal() + ","
               + "    format: '" + dateFormat + "'"
               + "})";
    }
}
