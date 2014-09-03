package de.agilecoders.wicket.extensions.markup.html.bootstrap.xeditable;

import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipConfig.IPlacement;
import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * The options for {@link de.agilecoders.wicket.extensions.markup.html.bootstrap.xeditable.XEditableBehavior}
 *
 * Please note: Not all options are implemented. If more options are needed then
 * the application can extend this class and add them. Or send a Pull Request to
 * the project.
 *
 * @link http://vitalets.github.io/x-editable/docs.html
 * @author <a href="mailto:christian.schroeter@1und1.de">Christian Schröter</a>
 */
public class XEditableOptions extends AbstractConfig {

    private static final IKey<String> DefaultValue = newKey("defaultValue", null);

    private static final IKey<String> Type = newKey("type", "text");

    private static final IKey<String> Placement = newKey("placement", "top");

    private static final IKey<String> Mode = newKey("mode", "popup");

    private static final IKey<Boolean> Display = newKey("display", null);

    private static final IKey<String> EmptyText = newKey("emptytext", "empty");

    private static final IKey<String> Highlight = newKey("highlight", "#FFFF80");

    private static final IKey<String> Url = newKey("url", null);

    private static final IKey<String> Send = newKey("send", "auto");

    private static final IKey<Boolean> Disabled = newKey("disabled", false);

    private static final IKey<Boolean> SaveNoChanges = newKey("savenochange", false);

    /**
     * @param value that will be displayed in input if original field value is empty
     * @return this instance for chaining.
     */
    public XEditableOptions withDefaultValue(final String value) {
        put(DefaultValue, value);
        return this;
    }

    /**
     * @param value can be text|textarea|select|date|checklist and more
     * @return this instance for chaining.
     */
    public XEditableOptions withType(final String value) {
        put(Type, value);
        return this;
    }

    /**
     * Can be top|right|bottom|left. Not used for inline container.
     *
     * @param value placement of container relative to element.
     * @return this instance for chaining.
     */
    public XEditableOptions withPlacement(final IPlacement value) {
        put(Placement, value.value());
        return this;
    }

    /**
     * @param value can be popup or inline
     * @return this instance for chaining.
     */
    public XEditableOptions withMode(final String value) {
        put(Mode, value);
        return this;
    }

    /**
     * @param value if false, no displaying methods will be called, element's text will never change.
     * @return this instance for chaining.
     */
    public XEditableOptions showValue(final Boolean value) {
        put(Display, value);
        return this;
    }

    /**
     * @param value text shown when element is empty.
     * @return this instance for chaining.
     */
    public XEditableOptions withEmptyText(final String value) {
        put(EmptyText, value);
        return this;
    }

    /**
     * @param value color used to highlight element after update.
     * @return this instance for chaining.
     */
    public XEditableOptions withHighlight(final String value) {
        put(Highlight, value);
        return this;
    }

    /**
     * @param value url for submit
     * @return this instance for chaining.
     */
    public XEditableOptions withUrl(final String value) {
        put(Url, value);
        return this;
    }

    /**
     * Strategy for sending data on server.
     *
     * @param value can be auto|always|never.
     * @return this instance for chaining.
     */
    public XEditableOptions send(final String value) {
        put(Send, value);
        return this;
    }

    /**
     * @param value whether or not editable is disabled
     * @return this instance for chaining.
     */
    public XEditableOptions isDisabled(final Boolean value) {
        put(Disabled, value);
        return this;
    }

    /**
     * @param value whether to save or cancel value when it was not changed but form was submitted
     * @return this instance for chaining.
     */
    public XEditableOptions saveNoChanges(final Boolean value) {
        put(SaveNoChanges, value);
        return this;
    }

}
