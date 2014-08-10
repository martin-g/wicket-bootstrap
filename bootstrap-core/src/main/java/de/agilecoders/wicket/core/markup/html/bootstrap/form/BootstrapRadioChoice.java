package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.IOnChangeListener;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.WildcardListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.AppendingStringBuffer;
import org.apache.wicket.util.string.Strings;
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

    public BootstrapRadioChoice(String id, List<? extends T> choices) {
        this(id, choices, new ChoiceRenderer<T>());
    }

    public BootstrapRadioChoice(String id, List<? extends T> choices, IChoiceRenderer<? super T> renderer) {
        this(id, null, choices, renderer);
    }

    public BootstrapRadioChoice(String id, IModel<T> model, List<? extends T> choices) {
        this(id, model, choices, new ChoiceRenderer<T>());
    }

    public BootstrapRadioChoice(String id, IModel<T> model, List<? extends T> choices, IChoiceRenderer<? super T> renderer) {
        this(id, model, new WildcardListModel<T>(choices), renderer);
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

        setPrefix("<div class=\"radio\">");
        setSuffix("</div>");
    }

    public boolean isInline() {
        return inline;
    }

    public BootstrapRadioChoice<T> setInline(boolean inline) {
        this.inline = inline;
        return this;
    }

    @Override
    protected IValueMap getAdditionalAttributesForLabel(int index, T choice) {
        IValueMap attrs = null;
        if (isInline()) {
            attrs = new ValueMap();
            attrs.put("class", "radio-inline");
        }
        return attrs;
    }


    /**
     * Generates and appends html for a single choice into the provided buffer
     *
     * @param buffer
     *            Appending string buffer that will have the generated html appended
     * @param choice
     *            Choice object
     * @param index
     *            The index of this option
     * @param selected
     *            The currently selected string value
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void appendOptionHtml(final AppendingStringBuffer buffer, final T choice, int index,
                                    final String selected)
    {
        Object displayValue = getChoiceRenderer().getDisplayValue(choice);
        Class<?> objectClass = (displayValue == null ? null : displayValue.getClass());

        // Get label for choice
        String label = "";

        if (objectClass != null && objectClass != String.class)
        {
            @SuppressWarnings("rawtypes")
            final IConverter converter = getConverter(objectClass);
            label = converter.convertToString(displayValue, getLocale());
        }
        else if (displayValue != null)
        {
            label = displayValue.toString();
        }

        // If there is a display value for the choice, then we know that the
        // choice is automatic in some way. If label is /null/ then we know
        // that the choice is a manually created radio tag at some random
        // location in the page markup!
        if (label != null)
        {
            String id = getChoiceRenderer().getIdValue(choice, index);
            final String idAttr = getMarkupId() + "-" + id;

            // Append option suffix
            buffer.append(getPrefix(index, choice));

            boolean enabled = isEnabledInHierarchy() && !isDisabled(choice, index, selected);

            buffer.append("<label for=\"")
                    .append(idAttr)
                    .append('"');

            // Allows user to add attributes to the <label..> tag
            IValueMap labelAttrs = getAdditionalAttributesForLabel(index, choice);
            if (labelAttrs != null)
            {
                for (Map.Entry<String, Object> attr : labelAttrs.entrySet())
                {
                    buffer.append(' ')
                            .append(attr.getKey())
                            .append("=\"")
                            .append(attr.getValue())
                            .append('"');
                }
            }
            buffer.append('>');

            // Add radio tag
            buffer.append("<input name=\"")
                    .append(getInputName())
                    .append('"')
                    .append(" type=\"radio\"")
                    .append((isSelected(choice, index, selected) ? " checked=\"checked\"" : ""))
                    .append((enabled ? "" : " disabled=\"disabled\""))
                    .append(" value=\"")
                    .append(id)
                    .append("\" id=\"")
                    .append(idAttr)
                    .append('"');

            // Should a roundtrip be made (have onSelectionChanged called)
            // when the option is clicked?
            if (wantOnSelectionChangedNotifications())
            {
                CharSequence url = urlFor(IOnChangeListener.INTERFACE, new PageParameters());

                Form<?> form = findParent(Form.class);
                if (form != null)
                {
                    buffer.append(" onclick=\"")
                            .append(form.getJsForInterfaceUrl(url))
                            .append(";\"");
                }
                else
                {
                    // NOTE: do not encode the url as that would give
                    // invalid JavaScript
                    buffer.append(" onclick=\"window.location.href='")
                            .append(url)
                            .append((url.toString().indexOf('?') > -1 ? '&' : '?') + getInputName())
                            .append('=')
                            .append(id)
                            .append("';\"");
                }
            }

            // Allows user to add attributes to the <input..> tag
            {
                IValueMap attrs = getAdditionalAttributes(index, choice);
                if (attrs != null)
                {
                    for (Map.Entry<String, Object> attr : attrs.entrySet())
                    {
                        buffer.append(' ')
                                .append(attr.getKey())
                                .append("=\"")
                                .append(attr.getValue())
                                .append('"');
                    }
                }
            }

            String componentPathAttributeName = getApplication().getDebugSettings().getComponentPathAttributeName();
            if (!Strings.isEmpty(componentPathAttributeName))
            {
                CharSequence path = getPageRelativePath();
                path = Strings.replaceAll(path, "_", "__");
                path = Strings.replaceAll(path, ":", "_");
                buffer.append(' ')
                        .append(componentPathAttributeName)
                        .append("=\"")
                        .append(path)
                        .append("_input_")
                        .append(index)
                        .append('"');
            }

            buffer.append("/>");

            // Add label for radio button
            String display = label;
            if (localizeDisplayValues())
            {
                display = getLocalizer().getString(label, this, label);
            }

            CharSequence escaped = display;
            if (getEscapeModelStrings())
            {
                escaped = Strings.escapeMarkup(display);
            }

            buffer.append(escaped).append("</label>");

            // Append option suffix
            buffer.append(getSuffix(index, choice));
        }
    }
}
