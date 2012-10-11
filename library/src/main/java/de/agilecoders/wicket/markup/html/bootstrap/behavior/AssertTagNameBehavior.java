package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import com.google.common.collect.Sets;
import de.agilecoders.wicket.util.Components;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Set;

/**
 * The {@code AssertTagNameBehavior} asserts that one of a list of given tag
 * names to be present. If you add this behavior there is no need to override
 * {@link Component#onComponentTag(org.apache.wicket.markup.ComponentTag)}.
 * <p/>
 * To assert a {@code div} and a {@code span} tag just add an
 * {@code AssertTagNameBehavior} with both tag names.
 * <p/>
 * <pre>
 *     add(new AssertTagNameBehavior("div", "span"));
 * </pre>
 *
 * @author miha
 * @version 1.0
 */
public class AssertTagNameBehavior extends Behavior {

    private final IModel<Set<? extends String>> tagNames;

    /**
     * Constructor.
     *
     * @param tagNames a list of html tag names
     */
    public AssertTagNameBehavior(String... tagNames) {
        this(Sets.newHashSet(tagNames));
    }

    /**
     * Constructor.
     *
     * @param tagNames a set of html tag names
     */
    public AssertTagNameBehavior(Set<String> tagNames) {
        this(Model.<String>ofSet(tagNames));
    }

    /**
     * Constructor.
     *
     * @param tagNames a model that contains a set of html tag names
     */
    public AssertTagNameBehavior(IModel<Set<? extends String>> tagNames) {
        super();

        this.tagNames = tagNames;
    }

    @Override
    public void detach(Component component) {
        super.detach(component);

        tagNames.detach();
    }

    /**
     * Assert the html tag names to be present.
     * <p/>
     * {@inheritDoc}
     */
    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, tagNames.getObject());
    }

}
