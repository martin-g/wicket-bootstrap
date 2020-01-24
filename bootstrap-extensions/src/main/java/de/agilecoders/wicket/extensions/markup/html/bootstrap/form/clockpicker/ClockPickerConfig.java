package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.clockpicker;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import de.agilecoders.wicket.jquery.Key;

public class ClockPickerConfig extends AbstractConfig {
    private static final long serialVersionUID = 1L;
    private static final IKey<String> DoneText = new Key<>("donetext");
    private static final IKey<Boolean> AutoClose = new Key<>("autoclose");
    private static final IKey<String> Placement = new Key<>("placement");
    private static final IKey<String> Align = new Key<>("align");

    public ClockPickerConfig() {
        put(DoneText, "Ok");
        put(AutoClose, false);
        put(Placement, Direction.bottom.value());
        put(Align, Direction.top.value());
    }

    /**
     * Done button text
     * @param doneText
     * @return
     */
    public ClockPickerConfig withDoneText(String doneText) {
        put(DoneText, doneText);
        return this;
    }

    /**
     * If clock should autoclose after minute pick - removes done button
     * @param autoClose
     * @return
     */
    public ClockPickerConfig withAutoClose(Boolean autoClose) {
        put(AutoClose, autoClose);
        return this;
    }

    /**
     * Placement of the popover clock
     * @param direction
     * @return
     */
    public ClockPickerConfig withPlacement(Direction direction) {
        put(Placement, direction.value());
        return this;
    }

    /**
     * Placement of the arrow on the popover clock
     * @param direction
     * @return
     */
    public ClockPickerConfig withAlign(Direction direction) {
        put(Align, direction.value());
        return this;
    }


    public enum Direction {
        top, bottom, right, left;

        public String value() {
            return name();
        }
    }
}
