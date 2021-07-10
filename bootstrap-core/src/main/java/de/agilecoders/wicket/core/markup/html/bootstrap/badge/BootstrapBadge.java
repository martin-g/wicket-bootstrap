package de.agilecoders.wicket.core.markup.html.bootstrap.badge;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior;

/**
 * Badges are our small count and labeling components.
 * https://getbootstrap.com/docs/4.1/components/badge/
 *
 * <p>
 * <pre>
 *     	<span class="badge">Default</span>
 *     	<span class="badge bg-primary">Primary</span>
 *     	<span class="badge bg-success">Success</span>
 *     	<span class="badge bg-warning">Warning</span>
 *     	<span class="badge bg-info">Info</span>
 *     	<span class="badge bg-danger">Danger</span>
 * </pre>
 * @author Jan Ferko
 */
public class BootstrapBadge extends Label {
    private static final long serialVersionUID = 1L;
    private BadgeBehavior badgeBehavior;

    public BootstrapBadge(String id, BackgroundColorBehavior.Color color) {
        this(id, null, color);
    }

    public BootstrapBadge(String id, Serializable label, BackgroundColorBehavior.Color color) {
        this(id, Model.of(label), color);
    }

    public BootstrapBadge(String id, IModel<?> model, BackgroundColorBehavior.Color color) {
        super(id, model);
        add(this.badgeBehavior = new BadgeBehavior(color));
    }

    public BootstrapBadge setType(BackgroundColorBehavior.Color color) {
        badgeBehavior.color(color);

        return this;
    }

    public BootstrapBadge setPill(boolean isPill) {
        badgeBehavior.pill(isPill);

        return this;
    }
}
