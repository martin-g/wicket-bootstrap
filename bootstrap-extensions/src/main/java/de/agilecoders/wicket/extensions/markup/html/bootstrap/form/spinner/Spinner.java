package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.spinner;

import static de.agilecoders.wicket.jquery.JQuery.$;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.agilecoders.wicket.core.util.Attributes;

/**
 * A mobile and touch friendly input spinner component for Bootstrap 3. It supports the mousewheel and the up/down keys.
 * Integrates <a href="http://www.virtuosoft.eu/code/bootstrap-touchspin/">Bootstrap TouchSpin</a> plugin.
 *
 * @author daniel.jipa
 */
public class Spinner<T extends Number> extends TextField<T>{

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(Spinner.class);

    private static final CssResourceReference SPINNER_CSS = new CssResourceReference(Spinner.class,
            "css/touchspin.min.css");
    private static final JavaScriptResourceReference SPINNER_JS = new JavaScriptResourceReference(Spinner.class,
            "js/touchspin.min.js");

    private final SpinnerConfig config;

    public Spinner(String id) {
        this(id, null, new SpinnerConfig());
    }

    public Spinner(String id, SpinnerConfig config) {
        this(id, null, config);
    }

    public Spinner(String id, final IModel<T> model) {
        this(id, model, new SpinnerConfig());
    }

    public Spinner(String id, final IModel<T> model, SpinnerConfig config) {
        super(id, model);
        this.config = config;
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        checkComponentTag(tag, "input");
        Attributes.set(tag, "type", "text");
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(SPINNER_CSS));
        response.render(JavaScriptHeaderItem.forReference(SPINNER_JS));
        response.render(OnDomReadyHeaderItem.forScript(createScript(getConfig())));
    }

    @Override
    protected void onInitialize() {
        if (wantStartNotification()) {
            registerStartListener();
        }
        if (wantStartUpNotification()) {
            registerStartUpListener();
        }
        if (wantStartDownNotification()) {
            registerStartDownListener();
        }
        if (wantStopNotification()) {
            registerStopListener();
        }
        if (wantStopUpNotification()) {
            registerStopUpListener();
        }
        if (wantStopDownNotification()) {
            registerStopDownListener();
        }
        if (wantMinNotification()) {
            registerMinListener();
        }
        if (wantMaxNotification()) {
            registerMaxListener();
        }
        super.onInitialize();
    }

    private void registerStartListener() {
        add(new AjaxEventBehavior("touchspin.on.startspin"){
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                LOG.debug("Start spin");
                onStartSpin(target);
            }
        });
    }

    protected boolean wantStartNotification() {
        return false;
    }

    private void registerStartUpListener() {
        add(new AjaxEventBehavior("touchspin.on.startupspin"){
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                LOG.debug("Start up spin");
                onStartUpSpin(target);
            }
        });
    }

    protected boolean wantStartUpNotification() {
        return false;
    }

    private void registerStartDownListener() {
        add(new AjaxEventBehavior("touchspin.on.startdownspin"){
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                LOG.debug("Start down spin");
                onStartDownSpin(target);
            }
        });
    }

    protected boolean wantStartDownNotification() {
        return false;
    }

    private void registerStopListener() {
        add(new AjaxEventBehavior("touchspin.on.stopspin"){
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                LOG.debug("Stop spin");
                onStopSpin(target);
            }
        });
    }

    protected boolean wantStopNotification() {
        return false;
    }

    private void registerStopUpListener() {
        add(new AjaxEventBehavior("touchspin.on.stopupspin"){
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                LOG.debug("Stop up spin");
                onStopUpSpin(target);
            }
        });
    }

    protected boolean wantStopUpNotification() {
        return false;
    }

    private void registerStopDownListener() {
        add(new AjaxEventBehavior("touchspin.on.stopdownspin"){
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                LOG.debug("Stop down spin");
                onStopDownSpin(target);
            }
        });
    }

    protected boolean wantStopDownNotification() {
        return false;
    }

    private void registerMinListener() {
        add(new AjaxEventBehavior("touchspin.on.min"){
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                LOG.debug("Reached minimum value");
                onMin(target);
            }
        });
    }

    protected boolean wantMinNotification() {
        return false;
    }

    private void registerMaxListener() {
        add(new AjaxEventBehavior("touchspin.on.max"){
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                LOG.debug("Reached maximum value");
                onMax(target);
            }
        });
    }

    protected boolean wantMaxNotification() {
        return false;
    }

    protected void onMax(AjaxRequestTarget target) {

    }

    protected void onMin(AjaxRequestTarget target) {

    }

    protected void onStopDownSpin(AjaxRequestTarget target) {

    }

    protected void onStopUpSpin(AjaxRequestTarget target) {

    }

    protected void onStopSpin(AjaxRequestTarget target) {

    }

    protected void onStartDownSpin(AjaxRequestTarget target) {

    }

    protected void onStartSpin(AjaxRequestTarget target) {

    }

    protected void onStartUpSpin(AjaxRequestTarget target) {

    }

    protected CharSequence createScript(final SpinnerConfig config) {
        return $(this).chain("TouchSpin", config).get();
    }

    public SpinnerConfig getConfig() {
        return config;
    }
}
