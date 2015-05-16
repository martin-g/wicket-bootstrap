package de.agilecoders.wicket.extensions.markup.html.bootstrap.contextmenu;

import com.google.common.base.Optional;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.util.References;

import de.agilecoders.wicket.jquery.util.Strings2;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * A bootstrap styled context menu.
 *
 * @author miha
 */
public abstract class ContextMenu<T> extends GenericPanel<T> {

    private Optional<Component> assignedComponent = Optional.absent();

    /**
     * Construct.
     *
     * @param markupId The component id
     */
    protected ContextMenu(final String markupId) {
        this(markupId, null);
    }

    /**
     * Construct.
     *
     * @param markupId The component id
     */
    public ContextMenu(final String markupId, final IModel<T> model) {
        super(markupId, model);

        setOutputMarkupPlaceholderTag(true);

        BootstrapResourcesBehavior.addTo(this);

        add(new CssClassNameAppender("context-menu"));

        add(createContent("content"));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        if (isAssigned()) {
            References.renderWithFilter(response, ContextMenuJavaScriptReference.instance());

            response.render($(assignedComponent.get()).chain("contextmenu").asDomReadyScript());
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        setVisible(isAssigned());
    }

    /**
     * @return true if a component was assigned to this context menu
     */
    public final boolean isAssigned() {
        return assignedComponent.isPresent();
    }

    /**
     * assign component to this context menu
     *
     * @param component The component to assign
     * @return this instance for chaining
     */
    public ContextMenu assignTo(final Component component) {
        assignedComponent = Optional.fromNullable(component);

        component.setOutputMarkupId(true);
        component.add(new AttributeModifier("data-toggle", "context"));
        component.add(new AttributeModifier("data-target", "#" + Strings2.getMarkupId(this)));

        return this;
    }

    /**
     * creates a new content container
     *
     * @param markupId The markup id
     * @return new content container
     */
    protected abstract Component createContent(final String markupId);
}
