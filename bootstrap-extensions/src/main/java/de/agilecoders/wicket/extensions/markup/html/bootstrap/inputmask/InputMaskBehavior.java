package de.agilecoders.wicket.extensions.markup.html.bootstrap.inputmask;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapJavascriptBehavior;
import de.agilecoders.wicket.core.util.References;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * Behavior that adds mask to input components.
 *
 * @author Jan Ferko
 */
public class InputMaskBehavior extends BootstrapJavascriptBehavior {

    /**
     * The input mask configuration.
     */
    private final InputMaskConfig config;

    /**
     * Constructs new behavior for given mask.
     *
     * @param mask the mask used in component
     */
    public InputMaskBehavior(String mask) {
        this(new InputMaskConfig().mask(mask));
    }

    /**
     * Constructs new behavior for given configuration.
     *
     * @param config the input mask configuration
     */
    public InputMaskBehavior(InputMaskConfig config) {
        this.config = config;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse headerResponse) {
        super.renderHead(component, headerResponse);
        References.renderWithFilter(headerResponse, JavaScriptHeaderItem.forReference(InputMaskJavascriptReference.getInstance()));
        headerResponse.render(
                OnDomReadyHeaderItem.forScript($(component).chain("inputmask", config).build())
        );
    }

    /**
     * Set mask for the input.
     *
     * @param mask the mask for the input.
     * @return this instance
     */
    public InputMaskBehavior mask(String mask) {
        config.mask(mask);
        return this;
    }

    /**
     * Sets mask placeholder.
     *
     * @param placeholder The mask placeholder.
     * @return this instance
     */
    public InputMaskBehavior placeholder(String placeholder) {
        config.placeholder(placeholder);
        return this;
    }

    /**
     * Sets regular expression to use as a mask.
     * When using shorthands be aware that you need to double escape them.
     *
     * @param regex The regular expression to use as a mask.
     * @return this config instance
     */
    public InputMaskBehavior regex(String regex) {
        config.regex(regex);
        return this;
    }

    /**
     * Repeat the mask definition x-times.
     *
     * @param repeat the number of times mask is repeated.
     * @return this config instance
     */
    public InputMaskBehavior repeat(int repeat) {
        config.repeat(repeat);
        return this;
    }

    /**
     * Toggle to allocate as much possible or the opposite.
     *
     * @param greedy Toggle to allocate as much possible or the opposite.
     * @return this instance
     */
    public InputMaskBehavior greedy(boolean greedy) {
        config.greedy(greedy);
        return this;
    }

    /**
     * Automatically unmask the value when retrieved.
     *
     * @param autoUnmask Toggle automatic unmasking when value is retrieved.
     * @return this instance
     */
    public InputMaskBehavior autoUnmask(boolean autoUnmask) {
        config.autoUnmask(autoUnmask);
        return this;
    }

    /**
     * Remove the mask before submitting the form.
     *
     * @param removeMaskOnSubmit Toggle mask removing before form submitting
     * @return this instace
     */
    public InputMaskBehavior removeMaskOnSubmit(boolean removeMaskOnSubmit) {
        config.removeMaskOnSubmit(removeMaskOnSubmit);
        return this;
    }

    /**
     * Remove the empty mask on blur or when not empty removes the optional trailing part.
     *
     * @param clearMaskOnFocus Toggle to empty mask on blur or optional trailing part if mask is empty
     * @return this instance
     */
    public InputMaskBehavior clearMarkOnLostFocus(boolean clearMaskOnFocus) {
        config.clearMarkOnLostFocus(clearMaskOnFocus);
        return this;
    }

    /**
     * Toggle to insert or overwrite input.
     * This option can be altered by pressing the Insert key.
     *
     * @param insertMode Toggle to insert or overwrite input
     * @return this instance
     */
    public InputMaskBehavior insertMode(boolean insertMode) {
        config.insertMode(insertMode);
        return this;
    }

    /**
     * Clear the incomplete input on blur.
     *
     * @param clearIncomplete Toggle to clear the incomplete input on blur.
     * @return this instance
     */
    public InputMaskConfig clearIncomplete(boolean clearIncomplete) {
        return config.clearIncomplete(clearIncomplete);
    }

    /**
     * Shows the mask when the input gets focus.
     *
     * @param showMaskOnFocus Toggle to show the mask when inpus gets focus.
     * @return this instance
     */
    public InputMaskConfig showMaskOnFocus(boolean showMaskOnFocus) {
        return config.showMaskOnFocus(showMaskOnFocus);
    }

    /**
     * Shows the mask when hovering the mouse.
     *
     * @param showMaskOnHover Toggle to show the mask when hovering the mouse.
     * @return this instance
     */
    public InputMaskConfig showMaskOnHover(boolean showMaskOnHover) {
        return config.showMaskOnHover(showMaskOnHover);
    }

    /**
     * Numeric input direction. Keeps the caret at the end.
     *
     * @param numericInput Toggle to keep the caret at the end
     * @return this instance
     */
    public InputMaskConfig numericInput(boolean numericInput) {
        return config.numericInput(numericInput);
    }

    /**
     * Align the input to the right.
     * This is only applied in combination op the numericInput option or the dir-attribute.
     *
     * @param rightAlign Toggle right input alignment
     * @return this instance
     */
    public InputMaskConfig rightAlign(boolean rightAlign) {
        return config.rightAlign(rightAlign);
    }

    /**
     * Make escape behave like undo. (ctrl-Z)
     * Pressing escape reverts the value to the value before focus.
     *
     * @param undoOnEscape Toggle to make escape behave like undo
     * @return this instance
     */
    public InputMaskConfig undoOnEscape(boolean undoOnEscape) {
        return config.undoOnEscape(undoOnEscape);
    }

    /**
     * When enabled the caret position is set after the latest valid position on TAB.
     *
     * @param positionCaretOnTab Enable to set caret position after the latest valid position on TAB
     * @return this instance
     */
    public InputMaskConfig positionCaretOnTab(boolean positionCaretOnTab) {
        return config.positionCaretOnTab(positionCaretOnTab);
    }

    /**
     * Allows for tabbing through the different parts of the masked field.
     *
     * @param tabThrough Toggle to allow for tabbing through the different parts of the masked field.
     * @return this instance
     */
    public InputMaskConfig tabThrough(boolean tabThrough) {
        return config.tabThrough(tabThrough);
    }

    /**
     * Return nothing when the user hasn't entered anything.
     *
     * @param nullable  Toggle to return nothing if user hasn't entered anything
     * @return this instance
     */
    public InputMaskConfig nullable(boolean nullable) {
        return config.nullable(nullable);
    }

    /**
     * Positioning of the caret on click.
     *
     * @param position position of caret on click
     * @return this instance
     */
    public InputMaskConfig positionCaretOnClick(InputMaskConfig.CaretPosition position) {
        return config.positionCaretOnClick(position);
    }
}
