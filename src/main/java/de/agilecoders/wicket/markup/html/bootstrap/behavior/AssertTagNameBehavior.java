package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import com.google.common.collect.Sets;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;

import java.util.Set;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class AssertTagNameBehavior extends BootstrapBaseBehavior {

    private final Set<String> tagNames;

    public AssertTagNameBehavior(String... tagNames) {
        this(Sets.newHashSet(tagNames));
    }

    public AssertTagNameBehavior(Set<String> tagNames) {
        super();

        this.tagNames = tagNames;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, tagNames);
    }

}
