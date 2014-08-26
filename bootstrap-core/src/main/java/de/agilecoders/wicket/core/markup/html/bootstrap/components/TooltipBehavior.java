package de.agilecoders.wicket.core.markup.html.bootstrap.components;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapJavascriptBehavior;
import de.agilecoders.wicket.jquery.Config;
import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IComponentAssignedModel;
import org.apache.wicket.model.IModel;

import static com.google.common.base.Preconditions.checkNotNull;
import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * Tooltips are an updated version of Jquery.tipsy, which don't rely on images,
 * use CSS3 for animations, and data-attributes for local title storage.
 *
 * @author miha
 */
public class TooltipBehavior extends BootstrapJavascriptBehavior {

    private final IModel<String> label;
    private final TooltipConfig config;
    private IModel<String> labelResolved;

    /**
     * Construct.
     *
     * @param label The tooltip text
     */
    public TooltipBehavior(final IModel<String> label) {
        this(label, new TooltipConfig());
    }

    /**
     * Construct.
     *
     * @param label  The tooltip text
     * @param config configuration of tooltip
     */
    public TooltipBehavior(final IModel<String> label, final TooltipConfig config) {
        this.label = label;
        this.config = config;
    }

    @Override
    public void detach(Component component) {
        super.detach(component);

        label.detach();
        labelResolved.detach();
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        tag.put("rel", createRelAttribute());
        tag.put("title", labelResolved.getObject());
    }

    @Override
    public void bind(final Component component) {
        super.bind(component);
        labelResolved=wrapOnAssignment(label, component);
        component.setOutputMarkupId(true);
    }
    private IModel<String> wrapOnAssignment(IModel<String> label, Component component) {
        if (label != null && label instanceof IComponentAssignedModel) {
            IComponentAssignedModel<String> cam = (IComponentAssignedModel<String>) label;
            return cam.wrapOnAssignment(checkNotNull(component));
        }
        return label;
    }
    /**
     * @return the value of the "rel" attribute
     */
    protected String createRelAttribute() {
        return "tooltip";
    }

    @Override
    public void renderHead(final Component component, final IHeaderResponse headerResponse) {
        super.renderHead(component, headerResponse);

        headerResponse.render(OnDomReadyHeaderItem.forScript(createInitializerScript(component, config)));
    }

    /**
     * creates an initializer script
     *
     * @param component The component where this behavior is assigned to.
     * @param config    The current configuration
     * @return new initializer script
     */
    protected CharSequence createInitializerScript(final Component component, final Config config) {
        return $(component).chain("tooltip", config).get();
    }

}
