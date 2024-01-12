package de.agilecoders.wicket.core.markup.html.bootstrap.badge;

import java.io.Serializable;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior.BackgroundColor;

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
 *
 * @author Jan Ferko
 */
public class BootstrapBadge extends Label {

    private static final long serialVersionUID = 1L;
    private BadgeBehavior badgeBehavior;

    public BootstrapBadge(String id, BackgroundColor backgroundColor) {
        this(id, null, backgroundColor);
    }

    public BootstrapBadge(String id, Serializable label, BackgroundColor backgroundColor) {
        this(id, Model.of(label), backgroundColor);
    }

    public BootstrapBadge(String id, IModel<?> model, BackgroundColor backgroundColor) {
        super(id, model);
        add(this.badgeBehavior = new BadgeBehavior(backgroundColor));
    }

    public BootstrapBadge setType(BackgroundColor backgroundColor) {
        badgeBehavior.color(backgroundColor);
        return this;
    }

    public BootstrapBadge setPill(boolean isPill) {
        badgeBehavior.pill(isPill);
        return this;
    }
}
