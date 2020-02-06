package de.agilecoders.wicket.samples.panels;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig;

/**
 * @author Alexey Volkov
 * @since 01.02.15
 */
public abstract class AbstractDatetimePickerPanel<T> extends Panel {
    private static final long serialVersionUID = -3234694041469981384L;

    private T date;

    /**
     * @param id     wicket id
     * @param config config
     */
    public AbstractDatetimePickerPanel(String id, DatetimePickerConfig config) {
        super(id);

        add(
            newInput("input", PropertyModel.<T>of(this, "date"), config),
            newInputWithIcon("with-icon", PropertyModel.<T>of(this, "date"), config)
            );
    }

    protected abstract Component newInput(String id, IModel<T> model, DatetimePickerConfig config);
    protected abstract Component newInputWithIcon(String id, IModel<T> model, DatetimePickerConfig config);

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
