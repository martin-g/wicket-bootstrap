package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.rating;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeSettings;
import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import org.apache.wicket.Application;

/**
 * @author daniel.jipa
 */
public class RatingConfig extends AbstractConfig {

    private static final long serialVersionUID = 1L;

    private static final IKey<Integer> start = newKey("start", 0);

    private static final IKey<Integer> stop = newKey("stop", 5);

    private static final IKey<Integer> step = newKey("step", 1);

    private static final IKey<Integer> fractions = newKey("fractions", 1);

    private static final IKey<String> filled = newKey("filled", null);

    private static final IKey<String> empty = newKey("empty", null);

    private static final IKey<String> filledSelected = newKey("filledSelected", null);

    public RatingConfig() {
        put(filled, getCssClassNameFor(FontAwesomeSettings.IconKey.FILLED));
        put(empty, getCssClassNameFor(FontAwesomeSettings.IconKey.EMPTY));
    }

    private String getCssClassNameFor(FontAwesomeSettings.IconKey iconKey) {
        return FontAwesomeSettings
            .get(Application.get())
            .getIconType(iconKey)
            .cssClassName();
    }

    public RatingConfig withStart(Integer value) {
        put(start, value);
        return this;
    }

    public RatingConfig withStop(Integer value) {
        put(stop, value);
        return this;
    }

    public RatingConfig withStep(Integer value) {
        put(step, value);
        return this;
    }

    public RatingConfig withFractions(Integer value) {
        put(fractions, value);
        return this;
    }

    public RatingConfig withFilled(String value) {
        put(filled, value);
        return this;
    }

    public RatingConfig withEmpty(String value) {
        put(empty, value);
        return this;
    }

    public RatingConfig withFilledSelected(String value) {
        put(filledSelected, value);
        return this;
    }

}
