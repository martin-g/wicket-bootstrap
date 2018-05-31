package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.util.value.IValueMap;
import org.apache.wicket.util.value.ValueMap;

/**
 * A specialization of a RadioChoice with Bootstrap styling
 *
 * @param <T>
 *     The model object type
 * @see <a href="http://getbootstrap.com/css/#forms">Bootstrap forms</a>
 */
public class BootstrapRadioChoice<T> extends RadioChoice<T> {

    private boolean inline = false;

    public BootstrapRadioChoice(String id) {
        this(id, new ArrayList<T>());
    }

    public BootstrapRadioChoice(String id, List<T> choices) {
        this(id, choices, new ChoiceRenderer<T>());
    }

    public BootstrapRadioChoice(String id, List<T> choices, IChoiceRenderer<? super T> renderer) {
        this(id, null, choices, renderer);
    }

    public BootstrapRadioChoice(String id, IModel<T> model, List<T> choices) {
        this(id, model, choices, new ChoiceRenderer<T>());
    }

    public BootstrapRadioChoice(String id, IModel<T> model, List<T> choices, IChoiceRenderer<? super T> renderer) {
        this(id, model, new ListModel<>(choices), renderer);
    }

    public BootstrapRadioChoice(String id, IModel<? extends List<? extends T>> choices) {
        this(id, choices, new ChoiceRenderer<T>());
    }

    public BootstrapRadioChoice(String id, IModel<T> model, IModel<? extends List<? extends T>> choices) {
        this(id, model, choices, new ChoiceRenderer<T>());
    }

    public BootstrapRadioChoice(String id, IModel<? extends List<? extends T>> choices, IChoiceRenderer<? super T> renderer) {
        this(id, null, choices, renderer);
    }

    public BootstrapRadioChoice(String id, IModel<T> model, IModel<? extends List<? extends T>> choices, IChoiceRenderer<? super T> renderer) {
        super(id, model, choices, renderer);

        setInline(false);
        setLabelPosition(LabelPosition.AFTER);
    }

    public boolean isInline() {
        return inline;
    }

    public BootstrapRadioChoice<T> setInline(boolean inline) {
        String cssClass = "form-check";
        if (inline) {
            cssClass += " form-check-inline";
        }
        setPrefix("<div class=\"" + cssClass + "\">");
        setSuffix("</div>");

        this.inline = inline;
        return this;
    }

    @Override
    protected IValueMap getAdditionalAttributesForLabel(int index, T choice) {
        IValueMap attrs = new ValueMap();
        attrs.put("class", "form-check-label");
        return attrs;
    }

    @Override
    protected IValueMap getAdditionalAttributes(int index, T choice) {
        IValueMap attrs = new ValueMap();
        attrs.put("class", "form-check-input");
        return attrs;
    }
}
