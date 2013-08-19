package de.agilecoders.wicket.core.markup.html.bootstrap.form;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

/**
 * InputBorder that adds both prepending and appending addons
 */
public class AroundInputBorder extends InputBorder {

    /**
     * Constructor with two icons.
     *
     * @param id              The component id
     * @param beforeIconType  The icon to use for the prepending addon
     * @param afterIconType   The icon to use for the appending addon
     */
    public AroundInputBorder(String id, IconType beforeIconType, IconType afterIconType) {
        super(id, beforeIconType);

        addComponent(new Icon("after", afterIconType));
    }

    /**
     * Constructor with two labels.
     *
     * @param id              The component id
     * @param beforeLabel     The label to use for the prepending addon
     * @param afterLabel      The label to use for the appending addon
     */
    public AroundInputBorder(String id, IModel<String> beforeLabel, IModel<String> afterLabel) {
        super(id, beforeLabel);

        addComponent(new Label("after", afterLabel));
    }

    /**
     * Constructor with two components.
     *
     * @param id               The component id
     * @param beforeComponent  The component to use for the prepending addon
     * @param afterComponent   The component to use for the appending addon
     */
    public AroundInputBorder(String id, Component beforeComponent, Component afterComponent) {
        super(id, beforeComponent);

        Args.isTrue("after".equals(afterComponent.getId()), "The id of the 'after' component must be 'after'");

        addComponent(afterComponent);
    }

    /**
     * Constructor with prepending icon and appending label.
     *
     * @param id              The component id
     * @param beforeIconType  The icon to use for the prepending addon
     * @param afterLabel      The label to use for the appending addon
     */
    public AroundInputBorder(String id, IconType beforeIconType, IModel<String> afterLabel) {
        super(id, beforeIconType);

        addComponent(new Label("after", afterLabel));
    }

    /**
     * Constructor with prepending icon and appending component.
     *
     * @param id               The component id
     * @param beforeIconType   The icon to use for the prepending addon
     * @param afterComponent   The component to use for the appending addon
     */
    public AroundInputBorder(String id, IconType beforeIconType, Component afterComponent) {
        super(id, beforeIconType);

        Args.isTrue("after".equals(afterComponent.getId()), "The id of the 'after' component must be 'after'");

        addComponent(afterComponent);
    }

    /**
     * Constructor with prepending component and appending icon.
     *
     * @param id                The component id
     * @param afterIconType     The icon to use for the appending addon
     * @param beforeComponent   The component to use for the appending addon
     */
    public AroundInputBorder(String id, Component beforeComponent, IconType afterIconType) {
        super(id, beforeComponent);

        addComponent(new Icon("after", afterIconType));
    }

    /**
     * Constructor with prepending label and appending component.
     *
     * @param id              The component id
     * @param beforeLabel     The label to use for the prepending addon
     * @param afterComponent  The component to use for the appending addon
     */
    public AroundInputBorder(String id, IModel<String> beforeLabel, Component afterComponent) {
        super(id, beforeLabel);

        Args.isTrue("after".equals(afterComponent.getId()), "The id of the 'after' component must be 'after'");

        addComponent(afterComponent);
    }

    /**
     * Constructor with prepending component and appending label.
     *
     * @param id               The component id
     * @param afterLabel       The label to use for the prepending addon
     * @param beforeComponent  The component to use for the appending addon
     */
    public AroundInputBorder(String id, Component beforeComponent, IModel<String> afterLabel) {
        super(id, beforeComponent);

        addComponent(new Label("after", afterLabel));
    }

    @Override
    protected String id() {
        return "before";
    }
}
