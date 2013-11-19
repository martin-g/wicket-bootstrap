package de.agilecoders.wicket.extensions.markup.html.bootstrap.tour;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.util.Json;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;


public class TourOptions extends AbstractConfig {

    /**
     * This option is used to build the name of the storage item where the tour state is stored.
     * You can initialize several tours with different names in the same page and application.
     */
    private static final IKey<IModel> Name = newKey("name", (IModel) Model.of("tour"));

    /**
     * Appends the step popover to a specific element.
     */
    private static final IKey<IModel> Container = newKey("body", (IModel) Model.of("body"));

    /**
     * This option set the left and right arrow navigation.
     */
    private static final IKey<Boolean> Keyboard = newKey("keyboard", Boolean.TRUE);

    /**
     * The storage system you want to use. could be the objects window.localStorage, window.sessionStorage of your own object.
     */
    private static final IKey<Json.RawValue> Storage = newKey("storage", new Json.RawValue("window.localStorage"));

    /**
     * Set this option to true to have some useful informations printed in the console.
     */
    private static final IKey<Boolean> DEBUG = newKey("debug", Boolean.FALSE);

    /**
     * The storage system you want to use. could be the objects window.localStorage, window.sessionStorage of your own object.
     */
    private static final IKey<Boolean> Backdrop = newKey("backdrop", Boolean.FALSE);

    /**
     * Set a custom function to execute as redirect function. The default redirect relies on the traditional document.location.href
     */
    private static final IKey<Boolean> Redirect = newKey("redirect", Boolean.TRUE);

    /**
     * Allow to show the step regardless whether its element is not set, is not present in the page or is hidden.
     * The step is fixed positioned in the middle of the page.
     */
    private static final IKey<Boolean> Orphan = newKey("orphan", Boolean.FALSE);

    /**
     * Specify a default base path prepended to the  path option of every single step.
     * Very useful if you need to reuse the same tour on different environments or sub-projects.
     */
    private static final IKey<IModel> BasePath = newKey("basePath", (IModel) Model.of(""));

    /**
     * String or function that returns a string of the HTML template for the popovers.
     */
    private static final IKey<IModel> Template = newKey("template", (IModel) Model.of(
        "<div class='popover tour'>" +
        "  <div class='arrow'></div>" +
        "  <h3 class='popover-title'></h3>" +
        "  <div class='popover-content'></div>" +
        "  <nav class='popover-navigation'>" +
        "    <div class='btn-group'>" +
        "      <button class='btn btn-default' data-role='prev'>« Prev</button>" +
        "      <button class='btn btn-default' data-role='next'>Next »</button>" +
        "     </div>" +
        "     <button class='btn btn-default' data-role='end'>End tour</button>" +
        "  </nav>" +
        "</div>"));


    /**
     * This option is used to build the name of the storage item where the tour state is stored.
     * You can initialize several tours with different names in the same page and application.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TourOptions withName(IModel<String> value) {
        put(Name, wrap(value));
        return this;
    }

    /**
     * Appends the step popover to a specific element.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TourOptions withContainer(IModel<String> value) {
        put(Container, wrap(value));
        return this;
    }

    /**
     * This option set the left and right arrow navigation.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TourOptions withKeyboard(boolean value) {
        put(Keyboard, value);
        return this;
    }

    /**
     * The storage system you want to use. could be the objects window.localStorage, window.sessionStorage of your own object.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TourOptions withStorage(String value) {
        put(Storage, new Json.RawValue("window.localStorage"));
        return this;
    }

    /**
     * Set this option to true to have some useful informations printed in the console.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TourOptions withDebug(boolean value) {
        put(DEBUG, value);
        return this;
    }

    /**
     * The storage system you want to use. could be the objects window.localStorage, window.sessionStorage of your own object.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TourOptions withBackdrop(boolean value) {
        put(Backdrop, value);
        return this;
    }

    /**
     * Set a custom function to execute as redirect function. The default redirect relies on the traditional document.location.href
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TourOptions withRedirect(boolean value) {
        put(Redirect, value);
        return this;
    }

    /**
     * Allow to show the step regardless whether its element is not set, is not present in the page or is hidden.
     * The step is fixed positioned in the middle of the page.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TourOptions withOrphan(boolean value) {
        put(Orphan, value);
        return this;
    }

    /**
     * Specify a default base path prepended to the  path option of every single step.
     * Very useful if you need to reuse the same tour on different environments or sub-projects.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TourOptions withBasepath(IModel<String> value) {
        put(BasePath, value);
        return this;
    }

    /**
     * String or function that returns a string of the HTML template for the popovers.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TourOptions withTemplate(IModel<String> value) {
        put(Template, wrap(value));
        return this;
    }

}
