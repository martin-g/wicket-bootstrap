package de.agilecoders.wicket.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.markup.html.bootstrap.common.Invertible;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Simple {@link org.apache.wicket.ajax.markup.html.AjaxLink} implementation that adds ability to
 * add an {@link Icon}.
 *
 * @author miha
 */
public abstract class NavbarAjaxLink<T> extends org.apache.wicket.ajax.markup.html.AjaxLink<T> implements Invertible<NavbarAjaxLink<T>> {
    private final Icon icon;
    private final Label label;
    private final IModel<IconType> iconTypeModel;

    /**
     * Construct. Navbar ajax link with default component id for navbar components ("component") and
     * an empty label.
     */
    public NavbarAjaxLink() {
        this(Model.of(""));
    }

    /**
     * Construct. Navbar ajax link with default component id for navbar components ("component") and
     * a specific label.
     *
     * @param label Label of link
     */
    public NavbarAjaxLink(final IModel<String> label) {
        this(Navbar.COMPONENT_ID, label);
    }

    /**
     * Construct.
     *
     * @param markupId component markup id
     * @param label    label of link
     */
    public NavbarAjaxLink(final String markupId, IModel<String> label) {
        super(markupId);

        this.iconTypeModel = Model.of(IconType.NULL);
        this.icon = new Icon("icon", iconTypeModel);

        this.label = new Label("label", label);
        this.label.setRenderBodyOnly(true);

        add(this.icon, this.label);
    }

    /**
     * setter for link label
     *
     * @param label label as model
     * @return this instance
     */
    public NavbarAjaxLink<T> setLabel(IModel<String> label) {
        this.label.setDefaultModel(label);

        return this;
    }

    /**
     * setter for icon type
     *
     * @param iconType icon type
     * @return this instance
     */
    public NavbarAjaxLink<T> setIconType(final IconType iconType) {
        this.iconTypeModel.setObject(iconType);

        return this;
    }

    @Override
    public NavbarAjaxLink<T> setInverted(boolean inverted) {
        icon.setInverted(inverted);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(true);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        if (!"a".equalsIgnoreCase(tag.getName()) && !"button".equalsIgnoreCase(tag.getName())) {
            tag.setName("a");
        }

        super.onComponentTag(tag);
    }

}
