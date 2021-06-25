package de.agilecoders.wicket.extensions.markup.html.bootstrap.tour;

import java.io.IOException;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.util.Json;


public class TourOptions extends AbstractConfig {
    private static final long serialVersionUID = 1L;

    /**
     * This option is used to build the name of the storage item where the tour state is stored.
     * You can initialize several tours with different names in the same page and application.
     */
    private static final IKey<IModel<String>> Name = newKey("name", Model.of("tour"));

    /**
     * Appends the step popover to a specific element.
     */
    private static final IKey<IModel<String>> Container = newKey("container", Model.of("body"));

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

    private static final IKey<BackdropPadding> BackdropPadding = newKey("backdropPadding", new BackdropPadding(0));

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
     * TODO waiting http://bootstraptour.com/api/ to be updated
     */
    private static final IKey<Integer> Delay = newKey("delay", 0);

    /**
     * Specify a default base path prepended to the  path option of every single step.
     * Very useful if you need to reuse the same tour on different environments or sub-projects.
     */
    private static final IKey<IModel<String>> BasePath = newKey("basePath", Model.of(""));

    /**
     * String or function that returns a string of the HTML template for the popovers.
     */
    private static final IKey<IModel<String>> Template = newKey("template", Model.of(
        "<div class='popover tour'>" +
        "  <div class='popover-arrow'></div>" +
        "  <h3 class='popover-header'></h3>" +
        "  <div class='popover-body'></div>" +
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
     * Show a dark backdrop behind the popover and its element, highlighting the current step.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TourOptions withBackdrop(boolean value) {
        put(Backdrop, value);
        return this;
    }

    /**
     * Add padding to the backdrop element that highlights the step element.
     It can be a number or a object containing optional top, right, bottom and left numbers.
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public TourOptions withBackdropPadding(BackdropPadding value) {
        put(BackdropPadding, value);
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

    public TourOptions withDelay(Integer value) {
        put(Delay, value);
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

    @JsonSerialize(using = BackdropPaddingSerializer.class)
    public static class BackdropPadding {
        private final int top, right, bottom, left;

        public BackdropPadding(int value) {
            this.top = value;
            this.right = value;
            this.bottom = value;
            this.left = value;
        }

        public BackdropPadding(int top, int right, int bottom, int left) {
            this.top = top;
            this.right = right;
            this.bottom = bottom;
            this.left = left;
        }
    }


    /**
     * Serializer for BackdropPadding
     */
    private static class BackdropPaddingSerializer extends JsonSerializer<BackdropPadding> {

        @Override
        public void serialize(BackdropPadding value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            if ((value.top == value.right) && (value.right == value.bottom) && (value.bottom == value.left)) {
                jgen.writeNumber(value.top);
            } else {
                jgen.writeStartObject();
                jgen.writeNumberField("top",    value.top);
                jgen.writeNumberField("right",  value.right);
                jgen.writeNumberField("bottom", value.bottom);
                jgen.writeNumberField("left",   value.left);
                jgen.writeEndObject();
            }
        }
    }
}
