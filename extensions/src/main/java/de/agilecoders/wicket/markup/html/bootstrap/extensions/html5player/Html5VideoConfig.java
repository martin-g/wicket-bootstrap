package de.agilecoders.wicket.markup.html.bootstrap.extensions.html5player;

import com.google.common.base.Preconditions;
import de.agilecoders.wicket.markup.html.bootstrap.common.AbstractConfig;
import org.apache.wicket.util.lang.Objects;

/**
 * {@link Html5Player} configuration
 *
 * @author miha
 */
public class Html5VideoConfig extends AbstractConfig {

    /**
     * Holds all html5 video player configuration keys.
     */
    private enum Key implements IKey {

        /**
         * enable auto play
         */
        AutoPlay("autoPlay", Boolean.class, false),

        /**
         * enable auto hide control bar
         */
        AutoHideControlBar("autoHide", Boolean.class, true),

        /**
         * enable full screen button
         */
        ShowFullscreenButton("fullscreenMedia", Boolean.class, true),

        /**
         * show & set volume value 1..10
         */
        Volume("volumeMedia", Integer.class, 5),

        /**
         * show video timer
         */
        ShowTimer("timerMedia", Boolean.class, true),

        /**
         * show progress bar
         */
        ShowProgressBar("progressMedia", Boolean.class, true),

        /**
         * show pause/play button
         */
        ShowPlayPauseButton("playMedia", Boolean.class, true);

        private final String key;
        private final Class type;
        private final Object defaultValue;

        /**
         * Construct.
         *
         * @param key          string representation of this key
         * @param type         The object type
         * @param defaultValue The default value
         */
        private Key(final String key, final Class type, final Object defaultValue) {
            this.key = key;
            this.type = type;
            this.defaultValue = defaultValue;
        }

        @Override
        public String key() {
            return key;
        }

        @Override
        public void assertCorrectType(final Object value) {
            Preconditions.checkArgument(type.isInstance(value));
        }

        @Override
        public boolean isDefaultValue(final Object value) {
            return Objects.equal(value, defaultValue);
        }

        @Override
        public Object getDefaultValue() {
            return defaultValue;
        }
    }

    /**
     * Construct.
     */
    public Html5VideoConfig() {
        super();
    }

    /**
     * show & set volume value 1..10
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public Html5VideoConfig withVolume(final int value) {
        put(Key.Volume, Math.min(Math.max(0, value), 10));
        return this;
    }

    /**
     * enable auto play
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public Html5VideoConfig autoPlay(final boolean value) {
        put(Key.AutoPlay, value);
        return this;
    }

    /**
     * enable auto hide control bar
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public Html5VideoConfig autoHideControlBar(final boolean value) {
        put(Key.AutoHideControlBar, value);
        return this;
    }

    /**
     * enable full screen button
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public Html5VideoConfig showFullscreenButton(final boolean value) {
        put(Key.ShowFullscreenButton, value);
        return this;
    }

    /**
     * show video timer
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public Html5VideoConfig showTimer(final boolean value) {
        put(Key.ShowTimer, value);
        return this;
    }

    /**
     * show progress bar
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public Html5VideoConfig showProgressBar(final boolean value) {
        put(Key.ShowProgressBar, value);
        return this;
    }

    /**
     * show pause/play button
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public Html5VideoConfig showPlayPauseButton(final boolean value) {
        put(Key.ShowPlayPauseButton, value);
        return this;
    }

}
