package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.util.CssClassNames;
import org.apache.wicket.Component;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * A Label is a highlighted text with rounded corners.
 *
 * @author miha
 * @version 1.0
 */
public class LabelBehavior extends AssertTagNameBehavior {
    private final IModel<LabelType> type;
    private final CssClassNameAppender cssClassNameAppender;

    /**
     * Construct.
     */
    public LabelBehavior() {
        this(LabelType.Default);
    }

    /**
     * Construct.
     *
     * @param type The type of the label
     */
    public LabelBehavior(final LabelType type) {
        this(Model.of(type));
    }

    /**
     * Construct.
     *
     * @param type The type of the label as model
     */
    public LabelBehavior(final IModel<LabelType> type) {
        super("span");

        this.type = type;
        this.cssClassNameAppender = new CssClassNameAppender(new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                return CssClassNames.parse(className()).add(getType().cssClassName(className())).asString();
            }
        });
    }

    /**
     * @return current label type
     */
    public final LabelType getType() {
        return type.getObject();
    }

    /**
     * sets the label type
     *
     * @param type the label type
     * @return this instance
     */
    public final LabelBehavior setType(final LabelType type) {
        this.type.setObject(type);

        return this;
    }

    /**
     * @return the css class name as string
     */
    protected String className() {
        return "label";
    }

    @Override
    public void bind(final Component component) {
        super.bind(component);

        BootstrapBaseBehavior.addTo(component);
        component.add(cssClassNameAppender);
    }

    @Override
    public void unbind(final Component component) {
        super.unbind(component);

        BootstrapBaseBehavior.removeFrom(component);
        component.remove(cssClassNameAppender);
    }
}
