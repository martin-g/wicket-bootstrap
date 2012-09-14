package de.agilecoders.wicket.markup.html.bootstrap.button;

import com.google.common.base.Predicate;
import de.agilecoders.wicket.util.Behaviors;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class AssertValidButtonPredicate implements Predicate<AbstractLink> {
    private static final Logger LOG = LoggerFactory.getLogger(AssertValidButtonPredicate.class);

    private final String buttonMarkupId;

    public AssertValidButtonPredicate(final String buttonMarkupId) {
        this.buttonMarkupId = buttonMarkupId;
    }

    @Override
    public boolean apply(AbstractLink button) {
        if (button == null) {
            throw new IllegalArgumentException("invalid button object");
        }

        if (!buttonMarkupId.equals(button.getId())) {
            throw new IllegalArgumentException("button id must be equal to '" + buttonMarkupId + "'");
        }

        if (!Behaviors.contains(button, ButtonBehavior.class)) {
            LOG.debug("button {}#{} has no ButtonBehavior. This will lead to an invalid style! ", button.getPath(), button.getId());

            button.add(new ButtonBehavior(ButtonType.Menu, ButtonSize.Medium));
        }

        return true;
    }
}