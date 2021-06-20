package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;

/**
 * Datetime-picker with calendar icon.
 *
 * @author Alexey Volkov
 * @since 01.02.2015
 */
public abstract class AbstractDateTimePickerWithIcon<T> extends FormComponentPanel<T> {

    private static final long serialVersionUID = 1L;

    private DatetimePickerConfig config;
    private FormComponent<T> dateInput;

    /**
     * Construct.
     *
     * @param markupId markup id
     * @param config   DateTimePicker config
     */
    public AbstractDateTimePickerWithIcon(String markupId, DatetimePickerConfig config) {
        this(markupId, null, config);
    }

    /**
     * Construct.
     *
     * @param markupId markup id
     * @param model    model
     * @param config   DateTimePicker config
     */
    public AbstractDateTimePickerWithIcon(String markupId, IModel<T> model, DatetimePickerConfig config) {
        super(markupId, model);
        setRenderBodyOnly(true);
        this.config = config;
    }

	private FormComponent<T> getDateInput() {
        if (dateInput == null) {
            dateInput = newInput("date", config.getFormat());
            dateInput.setModel(getModel());
            dateInput.add(new DatetimePickerBehavior(config));
        }
        return dateInput;
    }

    @Override
    public void convertInput() {
        setConvertedInput(getDateInput().getConvertedInput());
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Component input = getDateInput();
        if (config.getMaskInput()) {
            input.add(config.newMaskBehavior());
        }
        Component iconContainer = newIconContainer("iconContainer")
                .add(newIcon("icon"))
                .add(
                    new AttributeAppender("data-target", "#" + input.getMarkupId())
                );


        add(new WebMarkupContainer("dateWrapper")
                .add(input, iconContainer)
        );
    }

    /**
     * use specified config
     *
     * @param config config to use
     * @return current instance
     */
    public AbstractDateTimePickerWithIcon<T> with(DatetimePickerConfig config) {
        this.config = config;
        return this;
    }

    /**
     * get current config for tweaking
     *
     * @return current config
     */
    public DatetimePickerConfig getConfig() {
        return config;
    }

    /**
     * @param wicketId   wicket id
     * @param dateFormat datetime format
     * @return new input text field
     */
    abstract protected FormComponent<T> newInput(String wicketId, String dateFormat);

    /**
     * Creates new container for icon.
     *
     * @param wicketId wicket component id
     * @return container for icon component
     */
    protected MarkupContainer newIconContainer(String wicketId) {
        return new WebMarkupContainer(wicketId);
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
        return FontAwesomeIconType.calendar_alt_r;
    }

    @Override
    public FormComponent<T> setLabel(IModel<String> labelModel) {
        getDateInput().setLabel(labelModel);
        return super.setLabel(labelModel);
    }
}
