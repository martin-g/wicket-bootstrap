package de.agilecoders.wicket.extensions.markup.html.bootstrap.button;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.AbstractConfig;
import org.apache.wicket.util.time.Duration;

/**
 * {@link DropDownAutoOpen} configuration
 *
 * @author miha
 */
public class DropDownAutoOpenConfig extends AbstractConfig {

    /**
     * menu open delay
     */
    private static final IKey<Long> Delay = newKey("delay", 500L);

    /**
     * whether to close other menus or not
     */
    private static final IKey<Boolean> InstantlyCloseOthers = newKey("instantlyCloseOthers", true);

    /**
     * Construct.
     */
    public DropDownAutoOpenConfig() {
        super();
    }

    /**
     * open delay
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public DropDownAutoOpenConfig withDelay(final Duration value) {
        put(Delay, value.getMilliseconds());
        return this;
    }

    /**
     * whether to close other menus or not
     *
     * @param value mandatory parameter
     * @return this instance for chaining.
     */
    public DropDownAutoOpenConfig instantlyCloseOthers(final boolean value) {
        put(InstantlyCloseOthers, value);
        return this;
    }

}
