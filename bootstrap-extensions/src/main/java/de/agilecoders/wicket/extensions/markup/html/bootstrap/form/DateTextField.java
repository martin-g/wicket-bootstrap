package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.util.Attributes;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.references.BootstrapDatepickerJsReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.references.BootstrapDatepickerLangJsReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.references.BootstrapDatepickerReference;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.json.JsonFunction;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.Strings;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * A TextField that is mapped to a <code>java.util.Date</code> object.
 * <p/>
 * If no date pattern is explicitly specified, the default <code>DateFormat.SHORT</code> pattern for
 * the current locale will be used.
 *
 * @author miha
 */
public class DateTextField extends org.apache.wicket.extensions.markup.html.form.DateTextField {
    private static final long serialVersionUID = 3499287675713818823L;

    private static final String EVENT_PARAM = "datePickerEvent";
    private static final String DATE = "date";

    /**
     * Enumeration for date picker client side events.
     */
    public enum Event {
        show, // Fired when the date picker is displayed.
        hide, // Fired when the date picker is hidden.
        clearDate, // Fired when the date is cleared, normally when the “clear” button (enabled with the clearBtn option) is pressed.
        changeDate, // Fired when the date is changed.
        changeMonth, // Fired when the view month is changed from year view.
        changeYear, // Fired when the view year is changed from decade view.
        changeDecade, // Fired when the view decade is changed from century view.
        changeCentury, // Fired when the view century is changed from millennium view.
    }

    /**
     * Abstract base class for events.
     */
    public abstract class AbstractEventHandler implements Serializable {

        protected abstract CharSequence getBody();

        protected String getFunction() {
            StringBuilder sb = new StringBuilder("function(e) {\n");
            sb.append(getBody());
            sb.append("\n}");
            return sb.toString();
        }
    }

    /**
     * Ajax event handler
     */
    public interface IAjaxEventHandler extends Serializable {

        void onAjaxEvent(AjaxRequestTarget target, Date date, Event event);

    }

    private abstract class AbstractAjaxEvent extends AbstractEventHandler {

        private final Event event;
        private final IAjaxEventHandler handler;
        private final boolean updateModel;

        public AbstractAjaxEvent(Event event, IAjaxEventHandler handler, boolean updateModel) {
            this.event = event;
            this.handler = handler;
            this.updateModel = updateModel;
        }

        protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
            attributes.getExtraParameters().put(EVENT_PARAM, event.name());
            attributes.getExtraParameters().put(DATE, new JsonFunction("e.format()"));
        }

        protected void onAjaxEvent(AjaxRequestTarget target) {
            String dateStr = RequestCycle.get().getRequest().getRequestParameters().getParameterValue(DATE).toString(null);
            Date date = !Strings.isEmpty(dateStr)?
                DateTextField.this.getConverter(Date.class).convertToObject(dateStr, RequestCycle.get().getRequest().getLocale()): null;
            if(updateModel) {
                DateTextField.this.setDefaultModelObject(date);
            }
            handler.onAjaxEvent(target, date, event);
        }
    }

    private class DatePickerAbstractDefaultAjaxBehavior extends AbstractDefaultAjaxBehavior {

        private final AbstractAjaxEvent abstractAjaxEvent;


        public DatePickerAbstractDefaultAjaxBehavior(Event event, IAjaxEventHandler handler, boolean updateModel) {
            Args.notNull(event, "event");
            Args.notNull(handler, "handler");
            abstractAjaxEvent = createNew(event, handler, updateModel);
        }

        protected AbstractAjaxEvent createNew(Event event, IAjaxEventHandler handler, boolean updateModel) {
            return new AbstractAjaxEvent(event, handler, updateModel) {
                @Override
                protected CharSequence getBody() {
                    return DatePickerAbstractDefaultAjaxBehavior.this.getCallbackScript().toString();
                }
            };
        }

        @Override
        protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
            abstractAjaxEvent.updateAjaxAttributes(attributes);
        }

        @Override
        protected void respond(AjaxRequestTarget target) {
            abstractAjaxEvent.onAjaxEvent(target);
        }
    }

    private final Map<Event, AbstractEventHandler> eventMap = new HashMap<Event, AbstractEventHandler>();

    private final DateTextFieldConfig config;

    /**
     * Construct.
     *
     * @param markupId The id of the text field
     */
    public DateTextField(final String markupId) {
        this(markupId, new DateTextFieldConfig());
    }

    /**
     * Construct.
     *
     * @param markupId    The id of the text field
     * @param datePattern The format of the date
     */
    public DateTextField(final String markupId, final String datePattern) {
        this(markupId, new DateTextFieldConfig().withFormat(datePattern));
    }

    /**
     * Construct.
     *
     * @param markupId The id of the text field
     * @param model    The date model
     */
    public DateTextField(final String markupId, final IModel<Date> model) {
        this(markupId, model, new DateTextFieldConfig());
    }

    /**
     * Construct.
     *
     * @param markupId   The id of the text field
     * @param model      The date model
     * @param dateFormat The format of the date
     */
    public DateTextField(final String markupId, final IModel<Date> model, final String dateFormat) {
        this(markupId, model, new DateTextFieldConfig().withFormat(dateFormat));
    }

    /**
     * Construct.
     *
     * @param markupId The id of the text field
     * @param model    The date model
     * @param config   The configuration of this field
     */
    public DateTextField(final String markupId, final IModel<Date> model, final DateTextFieldConfig config) {
        super(markupId, model, config.getFormat());

        this.config = config;
    }

    /**
     * Construct.
     *
     * @param markupId The id of the text field
     * @param config   The configuration of this field
     */
    public DateTextField(final String markupId, final DateTextFieldConfig config) {
        super(markupId, config.getFormat());

        this.config = config;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setOutputMarkupId(true);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        checkComponentTag(tag, "input");
        Attributes.set(tag, "type", "text");

        super.onComponentTag(tag);
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(BootstrapDatepickerReference.INSTANCE));

        if (!config.isDefaultLanguageSet()) {
            response.render(JavaScriptHeaderItem.forReference(new BootstrapDatepickerLangJsReference(config.getLanguage())));
        } else {
            response.render(JavaScriptHeaderItem.forReference(BootstrapDatepickerJsReference.INSTANCE));
        }

        response.render(OnDomReadyHeaderItem.forScript(createScript(config)));
    }

    /**
     * creates the initializer script.
     *
     * @return initializer script
     */
    protected CharSequence createScript(final DateTextFieldConfig config) {
        CharSequence script = $(this).chain("datepicker", config).get();
        if(eventMap.isEmpty()) {
            return script;
        }
        // remove trailing ;
        StringBuilder sb = new StringBuilder(script.subSequence(0, script.length()-1));
        for(Event type: eventMap.keySet()) {
            sb.append(".on('").append(type).append("',") .append(eventMap.get(type).getFunction()).append(')');
        }
        sb.append(';');
        return sb;
    }

    /**
     * @return the config
     */
    protected final DateTextFieldConfig getConfig() {
        return config;
    }


    /**
     *  Allows to register/add an event handler.
     * @param type The event type
     * @param handler he event type
     * @return this
     */
    public DateTextField addEvent(Event type, AbstractEventHandler handler) {
        eventMap.put(type, handler);
        return this;
    }

    /**
     * Adds and ajax based event. Model object is not updated.
     *
     * @param type the event type
     * @param evenHandler The event handler
     */
    public DateTextField addAjaxEvent(Event type, IAjaxEventHandler evenHandler) {
        addAjaxEvent(type, evenHandler, false);
        return this;
    }

    /**
     *  Adds and ajax based event
     *
     * @param type the event type
     * @param evenHandler The event handler
     * @param updateModel if model object should be updated when even is fired
     */
    public DateTextField addAjaxEvent(Event type, IAjaxEventHandler evenHandler, boolean updateModel) {
        DatePickerAbstractDefaultAjaxBehavior datePickerAbstractDefaultAjaxBehavior = new DatePickerAbstractDefaultAjaxBehavior(type, evenHandler, updateModel);
        add(datePickerAbstractDefaultAjaxBehavior);
        addEvent(type, datePickerAbstractDefaultAjaxBehavior.abstractAjaxEvent);
        return this;
    }

    /**
     *  Allows to remove an event handler
     * @param type The event type
     * @return this
     */
    public DateTextField removeEvent(Event type) {
        eventMap.remove(type);
        return this;
    }

    public String getDestroyScript() {
        return "$('#" + getMarkupId()+"').datepicker('destroy');";
    }
}
