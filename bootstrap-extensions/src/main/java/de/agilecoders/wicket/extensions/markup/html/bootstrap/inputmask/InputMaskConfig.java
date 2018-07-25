package de.agilecoders.wicket.extensions.markup.html.bootstrap.inputmask;

import com.fasterxml.jackson.annotation.JsonValue;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.Key;

/**
 * @author Jan Ferko
 */
public class InputMaskConfig extends AbstractConfig {

    /**
     * Mask for the input.
     */
    private static final IKey<String> MASK = new Key<>("mask");

    /**
     * The mask placeholder. Default: "_".
     */
    private static final IKey<String> PLACEHOLDER = new Key<>("placeholder", "_");

    /**
     * Regular expression to use as a mask.
     */
    private static final IKey<String> REGEX = new Key<>("regex");

    /**
     * Mask repeat function. Repeat the mask definition x-times.
     */
    private static final IKey<Integer> REPEAT = new Key<>("repeat");

    /**
     * Toggle to allocate as much possible or the opposite. Default: false.
     */
    private static final IKey<Boolean> GREEDY = new Key<>("greedy", false);

    /**
     * Automatically unmask the value when retrieved.
     * Default: false.
     */
    private static final IKey<Boolean> AUTO_UNMASK = new Key<>("autoUnmask", false);

    /**
     * Remove the mask before submitting the form.
     * Default: false.
     */
    private static final IKey<Boolean> REMOVE_MASK_ON_SUBMIT = new Key<>("removeMaskOnSubmit", false);

    /**
     * Remove the empty mask on blur or when not empty removes the optional trailing part.
     * Default: true
     */
    private static final IKey<Boolean> CLEAR_MASK_ON_LOST_FOCUS = new Key<>("clearMaskOnLostFocus", true);

    /**
     * Toggle to insert or overwrite input.
     * Default: true.
     * This option can be altered by pressing the Insert key.
     */
    private static final IKey<Boolean> INSERT_MODE = new Key<>("insertMode", true);

    /**
     * Clear the incomplete input on blur.
     */
    private static final IKey<Boolean> CLEAR_INCOMPLETE = new Key<>("clearIncomplete");

    /**
     * Shows the mask when the input gets focus.
     * Default: true.
     */
    private static final IKey<Boolean> SHOW_MASK_ON_FOCUS = new Key<>("showMaskOnFocus", true);

    /**
     * Shows the mask when hovering the mouse.
     * Default: true.
     */
    private static final IKey<Boolean> SHOW_MASK_ON_HOVER = new Key<>("showMaskOnHover", true);

    /**
     * Numeric input direction. Keeps the caret at the end.
     */
    private static final IKey<Boolean> NUMERIC_INPUT = new Key<>("numericInput");

    /**
     * Align the input to the right.
     *
     * By setting the rightAlign you can specify to right align an inputmask.
     * This is only applied in combination op the numericInput option or the dir-attribute.
     * Default: true.
     */
    private static final IKey<Boolean> RIGHT_ALIGN = new Key<>("rightAlign");

    /**
     * Make escape behave like undo. (ctrl-Z)
     * Pressing escape reverts the value to the value before focus.
     * Default: true.
     */
    private static final IKey<Boolean> UNDO_ON_ESCAPE = new Key<>("undoOnEscape", true);

    /**
     * When enabled the caret position is set after the latest valid position on TAB.
     * Default: true.
     */
    private static final IKey<Boolean> POSITION_CARET_ON_TAB = new Key<>("positionCaretOnTab", true);

    /**
     * Allows for tabbing through the different parts of the masked field.
     * Default: false.
     */
    private static final IKey<Boolean> TAB_THROUGH = new Key<>("tabThrough", false);

    /**
     * Return nothing when the user hasn't entered anything.
     * Default: true.
     */
    private static final IKey<Boolean> NULLABLE = new Key<>("nullable", true);

    /**
     * Positioning of the caret on click.
     */
    private static final IKey<CaretPosition> POSITION_CARET_ON_CLICK = new Key<>("positionCaretOnClick", CaretPosition.LVP);

    /**
     * Enumeration of possible caret position.
     */
    public enum CaretPosition {
        NONE("none"),
        /**
         * Last Valid position (default).
         */
        LVP("lvp"),
        /**
         * Position caret to radixpoint on initial click.
         */
        RADIX_FOCUS("radixFocus"),
        /**
         * Select the whole input.
         */
        SELECT("select"),
        /**
         * Ignore the click and continue the mask.
         */
        IGNORE("ignore");

        /**
         * Value of position.
         */
        private final String value;

        CaretPosition(String value) {
            this.value = value;
        }

        /**
         * @return unique value of element.
         */
        @JsonValue
        public String getValue() {
            return value;
        }
    }

    /**
     * Set mask for the input.
     *
     * @param mask the mask for the input.
     * @return this config instance
     */
    public InputMaskConfig mask(String mask) {
        put(MASK, mask);
        return this;
    }

    /**
     * Sets mask placeholder.
     *
     * @param placeholder The mask placeholder.
     * @return this config instance
     */
    public InputMaskConfig placeholder(String placeholder) {
        put(PLACEHOLDER, placeholder);
        return this;
    }

    /**
     * Sets regular expression to use as a mask.
     * When using shorthands be aware that you need to double escape them.
     *
     * @param regex The regular expression to use as a mask.
     * @return this config instance
     */
    public InputMaskConfig regex(String regex) {
        put(REGEX, regex);
        return this;
    }

    /**
     * Repeat the mask definition x-times.
     *
     * @param repeat the number of times mask is repeated.
     * @return this config instance
     */
    public InputMaskConfig repeat(int repeat) {
        put(REPEAT, repeat);
        return this;
    }

    /**
     * Toggle to allocate as much possible or the opposite.
     *
     * @param greedy Toggle to allocate as much possible or the opposite.
     * @return this config instance
     */
    public InputMaskConfig greedy(boolean greedy) {
        put(GREEDY, greedy);
        return this;
    }

    /**
     * Automatically unmask the value when retrieved.
     *
     * @param autoUnmask Toggle automatic unmasking when value is retrieved.
     * @return this config instance
     */
    public InputMaskConfig autoUnmask(boolean autoUnmask) {
        put(AUTO_UNMASK, autoUnmask);
        return this;
    }

    /**
     * Remove the mask before submitting the form.
     *
     * @param removeMaskOnSubmit Toggle mask removing before form submitting
     * @return this config instace
     */
    public InputMaskConfig removeMaskOnSubmit(boolean removeMaskOnSubmit) {
        put(REMOVE_MASK_ON_SUBMIT, removeMaskOnSubmit);
        return this;
    }

    /**
     * Remove the empty mask on blur or when not empty removes the optional trailing part.
     *
     * @param clearMaskOnFocus Toggle to empty mask on blur or optional trailing part if mask is empty
     * @return this config instance
     */
    public InputMaskConfig clearMarkOnLostFocus(boolean clearMaskOnFocus) {
        put(CLEAR_MASK_ON_LOST_FOCUS, clearMaskOnFocus);
        return this;
    }

    /**
     * Toggle to insert or overwrite input.
     * This option can be altered by pressing the Insert key.
     *
     * @param insertMode Toggle to insert or overwrite input
     * @return this config instance
     */
    public InputMaskConfig insertMode(boolean insertMode) {
        put(INSERT_MODE, insertMode);
        return this;
    }

    /**
     * Clear the incomplete input on blur.
     *
     * @param clearIncomplete Toggle to clear the incomplete input on blur.
     * @return this config instance
     */
    public InputMaskConfig clearIncomplete(boolean clearIncomplete) {
        put(CLEAR_INCOMPLETE, clearIncomplete);
        return this;
    }

    /**
     * Shows the mask when the input gets focus.
     *
     * @param showMaskOnFocus Toggle to show the mask when inpus gets focus.
     * @return this config instance
     */
    public InputMaskConfig showMaskOnFocus(boolean showMaskOnFocus) {
        put(SHOW_MASK_ON_FOCUS, showMaskOnFocus);
        return this;
    }

    /**
     * Shows the mask when hovering the mouse.
     *
     * @param showMaskOnHover Toggle to show the mask when hovering the mouse.
     * @return this config instance
     */
    public InputMaskConfig showMaskOnHover(boolean showMaskOnHover) {
        put(SHOW_MASK_ON_HOVER, showMaskOnHover);
        return this;
    }

    /**
     * Numeric input direction. Keeps the caret at the end.
     *
     * @param numericInput Toggle to keep the caret at the end
     * @return this config instance
     */
    public InputMaskConfig numericInput(boolean numericInput) {
        put(NUMERIC_INPUT, numericInput);
        return this;
    }

    /**
     * Align the input to the right.
     * This is only applied in combination op the numericInput option or the dir-attribute.
     *
     * @param rightAlign Toggle right input alignment
     * @return this config instance
     */
    public InputMaskConfig rightAlign(boolean rightAlign) {
        put(RIGHT_ALIGN, rightAlign);
        return this;
    }

    /**
     * Make escape behave like undo. (ctrl-Z)
     * Pressing escape reverts the value to the value before focus.
     *
     * @param undoOnEscape Toggle to make escape behave like undo
     * @return this config instance
     */
    public InputMaskConfig undoOnEscape(boolean undoOnEscape) {
        put(UNDO_ON_ESCAPE, undoOnEscape);
        return this;
    }

    /**
     * When enabled the caret position is set after the latest valid position on TAB.
     *
     * @param positionCaretOnTab Enable to set caret position after the latest valid position on TAB
     * @return this config instance
     */
    public InputMaskConfig positionCaretOnTab(boolean positionCaretOnTab) {
        put(POSITION_CARET_ON_TAB, positionCaretOnTab);
        return this;
    }

    /**
     * Allows for tabbing through the different parts of the masked field.
     *
     * @param tabThrough Toggle to allow for tabbing through the different parts of the masked field.
     * @return this config instance
     */
    public InputMaskConfig tabThrough(boolean tabThrough) {
        put(TAB_THROUGH, tabThrough);
        return this;
    }

    /**
     * Return nothing when the user hasn't entered anything.
     *
     * @param nullable  Toggle to return nothing if user hasn't entered anything
     * @return this config instance
     */
    public InputMaskConfig nullable(boolean nullable) {
        put(NULLABLE, nullable);
        return this;
    }

    /**
     * Positioning of the caret on click.
     *
     * @param position position of caret on click
     * @return this config instance
     */
    public InputMaskConfig positionCaretOnClick(CaretPosition position) {
        put(POSITION_CARET_ON_CLICK, position);
        return this;
    }
}
