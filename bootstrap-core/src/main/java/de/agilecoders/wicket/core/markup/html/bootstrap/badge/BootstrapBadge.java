package de.agilecoders.wicket.core.markup.html.bootstrap.badge;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Badges are our small count and labeling components.
 * https://getbootstrap.com/docs/4.1/components/badge/
 *
 * <p>
 * <pre>
 *     	<span class="badge">Default</span>
 *     	<span class="badge badge-primary">Primary</span>
 *     	<span class="badge badge-success">Success</span>
 *     	<span class="badge badge-warning">Warning</span>
 *     	<span class="badge badge-info">Info</span>
 *     	<span class="badge badge-danger">Danger</span>
 * </pre>
 * @author Jan Ferko
 */
public class BootstrapBadge extends Label {
    private BadgeBehavior badgeBehavior;

    public BootstrapBadge(String id, BadgeBehavior.Type type) {
        this(id, null, type);
    }

    public BootstrapBadge(String id, Serializable label, BadgeBehavior.Type type) {
        this(id, Model.of(label), type);
    }

    public BootstrapBadge(String id, IModel<?> model, BadgeBehavior.Type type) {
        super(id, model);
        add(this.badgeBehavior = new BadgeBehavior(type));
    }

    public BootstrapBadge setType(BadgeBehavior.Type type) {
        badgeBehavior.type(type);

        return this;
    }

    public BootstrapBadge setPill(boolean isPill) {
        badgeBehavior.pill(isPill);

        return this;
    }
}
