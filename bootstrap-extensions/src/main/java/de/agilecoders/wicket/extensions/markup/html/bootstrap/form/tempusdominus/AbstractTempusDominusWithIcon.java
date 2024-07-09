package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeSettings;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.model.IModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;

/**
 * Tempus-dominus with calendar icon.
 *
 */
public abstract class AbstractTempusDominusWithIcon<T> extends FormComponentPanel<T> {
    private static final long serialVersionUID = 1L;

    private TempusDominusConfig config;
    private FormComponent<T> dateInput;

    /**
     * Construct.
     *
     * @param markupId markup id
     * @param config   DateTimePicker config
     */
    public AbstractTempusDominusWithIcon(String markupId, TempusDominusConfig config) {
        this(markupId, null, config);
    }

    /**
     * Construct.
     *
     * @param markupId markup id
     * @param model    model
     * @param config   DateTimePicker config
     */
    public AbstractTempusDominusWithIcon(String markupId, IModel<T> model, TempusDominusConfig config) {
        super(markupId, model);
        this.config = config;
    }

    private FormComponent<T> getDateInput() {
        if (dateInput == null) {
            dateInput = newInput("date", config.getJavaFormat());
            dateInput.setModel(getModel());
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
        setOutputMarkupId(true);
        FormComponent<T> input = getDateInput();
        setType(input.getType());
        final String mainId = "#" + getMarkupId();
        input.add(AttributeAppender.append("data-td-target", mainId));
        Component iconContainer = newIconContainer("iconContainer")
                .add(newIcon("icon"))
                .add(AttributeAppender.append("data-td-target", mainId)
                    , AttributeAppender.append("data-td-toggle", "datetimepicker"));
        add(AttributeAppender.append("class", "input-group date")
            , AttributeAppender.append("data-td-target-input", "nearest")
            , AttributeAppender.append("data-td-target-toggle", "nearest"));

        add(input, iconContainer);
        add(new TempusDominusBehavior(config));
    }

    /**
     * use specified config
     *
     * @param config config to use
     * @return current instance
     */
    public AbstractTempusDominusWithIcon<T> with(TempusDominusConfig config) {
        this.config = config;
        return this;
    }

    /**
     * get current config for tweaking
     *
     * @return current config
     */
    public TempusDominusConfig getConfig() {
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
        return FontAwesomeSettings.get(Application.get()).getIconType(FontAwesomeSettings.IconKey.CALENDAR);
    }

    @Override
    public FormComponent<T> setLabel(IModel<String> labelModel) {
        getDateInput().setLabel(labelModel);
        return super.setLabel(labelModel);
    }
}
