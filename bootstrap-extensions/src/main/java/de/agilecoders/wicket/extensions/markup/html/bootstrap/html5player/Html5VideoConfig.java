package de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player;


import de.agilecoders.wicket.jquery.AbstractConfig;

/**
 * {@link Html5Player} configuration
 *
 * @author miha
 */
public class Html5VideoConfig extends AbstractConfig {

    /**
     * enable auto play
     */
    private static final IKey<Boolean> AutoPlay = newKey("autoPlay", false);

    /**
     * enable auto hide control bar
     */
    private static final IKey<Boolean> AutoHideControlBar = newKey("autoHide", true);

    /**
     * enable full screen button
     */
    private static final IKey<Boolean> ShowFullscreenButton = newKey("fullscreenMedia", true);

    /**
     * show & set volume value 1..10
     */
    private static final IKey<Integer> Volume = newKey("volumeMedia", 5);

    /**
     * show video timer
     */
    private static final IKey<Boolean> ShowTimer = newKey("timerMedia", true);

    /**
     * show progress bar
     */
    private static final IKey<Boolean> ShowProgressBar = newKey("progressMedia", true);

    /**
     * show pause/play button
     */
    private static final IKey<Boolean> ShowPlayPauseButton = newKey("playMedia", true);

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
        put(Volume, Math.min(Math.max(0, value), 10));
        return this;
    }

    /**
     * enable auto play
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public Html5VideoConfig autoPlay(final boolean value) {
        put(AutoPlay, value);
        return this;
    }

    /**
     * enable auto hide control bar
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public Html5VideoConfig autoHideControlBar(final boolean value) {
        put(AutoHideControlBar, value);
        return this;
    }

    /**
     * enable full screen button
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public Html5VideoConfig showFullscreenButton(final boolean value) {
        put(ShowFullscreenButton, value);
        return this;
    }

    /**
     * show video timer
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public Html5VideoConfig showTimer(final boolean value) {
        put(ShowTimer, value);
        return this;
    }

    /**
     * show progress bar
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public Html5VideoConfig showProgressBar(final boolean value) {
        put(ShowProgressBar, value);
        return this;
    }

    /**
     * show pause/play button
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public Html5VideoConfig showPlayPauseButton(final boolean value) {
        put(ShowPlayPauseButton, value);
        return this;
    }

}
