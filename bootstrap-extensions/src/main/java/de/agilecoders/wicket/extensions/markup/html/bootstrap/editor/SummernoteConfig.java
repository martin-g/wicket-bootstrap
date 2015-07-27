package de.agilecoders.wicket.extensions.markup.html.bootstrap.editor;

import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

public class SummernoteConfig extends AbstractConfig {

    private static final long serialVersionUID = 1L;

    private static final IKey<Boolean> AirMode = newKey("airMode", null);
    private static final IKey<Integer> MaxHeight = newKey("maxHeight", null);
    private static final IKey<Integer> MinHeight = newKey("minHeight", null);
    private static final IKey<Integer> Height = newKey("height", null);
    private static final IKey<Boolean> Force = newKey("force", null);

    public SummernoteConfig() {
    }

    /**
     * @param airmode
     *            if air mode should be used or normal
     * @return current instance
     */
    public SummernoteConfig withAirMode(boolean airMode) {
	put(AirMode, airMode);
	return this;
    }

    /**
     * @param maxHeight
     *            the max height of the editor
     * @return current instance
     */
    public SummernoteConfig withMaxHeight(Integer maxHeight) {
	put(MaxHeight, maxHeight);
	return this;
    }

    /**
     * @param minHeight
     *            the min height of the editor
     * @return current instance
     */
    public SummernoteConfig withMinHeight(Integer minHeight) {
	put(MinHeight, minHeight);
	return this;
    }

    /**
     * @param height
     *            the height of the editor
     * @return current instance
     */
    public SummernoteConfig withHeight(Integer height) {
	put(Height, height);
	return this;
    }

    /**
     * @param force
     *            if the editor should gain the focus
     * @return current instance
     */
    public SummernoteConfig force(boolean force) {
	put(Force, force);
	return this;
    }
}
