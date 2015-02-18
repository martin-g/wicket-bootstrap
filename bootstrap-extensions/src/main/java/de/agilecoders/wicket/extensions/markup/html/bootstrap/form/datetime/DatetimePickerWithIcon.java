package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import java.util.Date;

/**
 * Datetime-picker with calendar icon.
 *
 * @author Alexey Volkov
 * @since 01.02.2015
 */
public class DatetimePickerWithIcon extends Panel {

    private static final long serialVersionUID = 1L;

    private DatetimePickerConfig config;

    /**
     * Construct.
     *
     * @param markupId markup id
     * @param config   DateTimePicker config
     */
    public DatetimePickerWithIcon(String markupId, DatetimePickerConfig config) {
        this(markupId, null, config);
    }

    /**
     * Construct.
     *
     * @param markupId markup id
     * @param model    model
     * @param config   DateTimePicker config
     */
    public DatetimePickerWithIcon(String markupId, IModel<Date> model, DatetimePickerConfig config) {
        super(markupId, model);
        setDefaultModel(model);
        setRenderBodyOnly(true);
        this.config = config;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new WebMarkupContainer("dateWrapper")
                .add(
                    newInput("date", config.getFormat()),
                    newIcon("icon")
                )
                .add(
                    new DatetimePickerBehavior(config)
                )
        );
    }

    /**
     * use specified config
     *
     * @param config config to use
     * @return current instance
     */
    public DatetimePickerWithIcon with(DatetimePickerConfig config) {
        this.config = config;
        return this;
    }

    /**
     * @param wicketId   wicket id
     * @param dateFormat datetime format
     * @return new input text field
     */
    protected DateTextField newInput(String wicketId, String dateFormat) {
        DateTextField field = new DateTextField(wicketId, (IModel<Date>) getDefaultModel(), dateFormat);
        if (config.getMaskInput()) {
            field.add(config.newMaskBehavior());
        }
        return field;
    }

    /**
     * @param wicketId wicket id
     * @return icon component
     */
    protected Component newIcon(String wicketId) {
        return new Icon(wicketId, newIconType());
    }

    /**
     * @return icon type
     */
    protected IconType newIconType() {
        return GlyphIconType.calendar;
    }
}
