package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkboxx;

import java.util.Locale;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.StringValue;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * An extension of {@link org.apache.wicket.markup.html.form.CheckBox} that allows to have three states:
 * <ul>
 *     <li>checked - model object value is {@link java.lang.Boolean#TRUE}</li>
 *     <li>unchecked - model object value is {@link java.lang.Boolean#FALSE}</li>
 *     <li>undefined - model object value is {@code null}</li>
 * </ul>
 *
 * Integrates <a href="https://github.com/kartik-v/bootstrap-checkbox-x">Checkbox X</a>
 */
public class CheckBoxX extends CheckBox {

    private final CheckBoxXConfig config = new CheckBoxXConfig();

    /**
     * Constructor.
     *
     * Should be used when this component has a parent with {@link org.apache.wicket.model.CompoundPropertyModel}
     *
     * @param id The component id
     */
    public CheckBoxX(String id) {
        this(id, null);
    }

    /**
     * Constructor.
     *
     * @param id The component id
     * @param model The model to keep the selection
     */
    public CheckBoxX(String id, IModel<Boolean> model) {
        super(id, model);

        // clicks on a wrapping <label> should not fire change events because this leads to double submits
        config.withEnclosedLabel(true);

        setOutputMarkupId(true);
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);

        // set the value attribute. It is used by the JavaScript widget
        final String value = CheckBoxXConverter.INSTANCE.convertToString(getModelObject(), getLocale());
        tag.put("value", value);
    }

    @Override
    public void convertInput() {
        // a normal form submit or another ajax submit behavior. the value is already set by an earlier click (with Ajax)
        setConvertedInput(getModelObject());
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new AjaxEventBehavior("change") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                WebRequest request = getWebRequest();
                IRequestParameters requestParameters = request.getRequestParameters();
                StringValue value = requestParameters.getParameterValue(getParameterName());
                if (!value.isNull()) {
                    Boolean convertedInput = CheckBoxXConverter.INSTANCE.convertToObject(value.toString(), getLocale());
                    setModelObject(convertedInput);
                    onChange(convertedInput, target);
                }
            }

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);

                attributes.setMethod(AjaxRequestAttributes.Method.POST);
                attributes.getDynamicExtraParameters().add("return {'"+getParameterName()+"': Wicket.$(attrs.c).value}");
            }
        });
    }

    private String getParameterName() {
        return "checkboxx::"+getInputName();
    }

    /**
     * A callback method called when the value has changed due to a click on the widget
     *
     * @param value The new value. {@link java.lang.Boolean#TRUE} means <em>checked</em>,
     *              {@link java.lang.Boolean#FALSE} means <em>unchecked</em> and {@code null}
     *              means <em>undefined</em>
     * @param target The Ajax request handler
     */
    protected void onChange(Boolean value, AjaxRequestTarget target) {
    }

    /**
     * @return The configuration for the widget.
     */
    public CheckBoxXConfig getConfig() {
        return config;
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(new CssResourceReference(CheckBoxX.class, "css/checkbox-x.css")));
        response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(CheckBoxX.class, "js/checkbox-x.js")));

        response.render(OnDomReadyHeaderItem.forScript($(this).chain("checkboxX", getConfig()).get()));
    }

    /**
     * Converter specific for CheckBox-X.
     * @see <a href="https://github.com/kartik-v/bootstrap-checkbox-x#documentation">The official documentaion</a>
     */
    private static class CheckBoxXConverter implements IConverter<Boolean>
    {
        private static final IConverter<Boolean> INSTANCE = new CheckBoxXConverter();

        @Override
        public Boolean convertToObject(String value, Locale locale)
        {
            if ("1".equals(value))
            {
                return Boolean.TRUE;
            }
            else if ("0".equals(value))
            {
                return Boolean.FALSE;
            } else {
                return null;
            }
        }

        @Override
        public String convertToString(Boolean value, Locale locale)
        {
            String s;
            if (value == null) {
                s = "";
            } else if (Boolean.TRUE.equals(value)) {
                s = "1";
            } else {
                s = "0";
            }
            return s;
        }
    }
}
