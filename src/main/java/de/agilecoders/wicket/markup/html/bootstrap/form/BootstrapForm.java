package de.agilecoders.wicket.markup.html.bootstrap.form;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class BootstrapForm<T> extends org.apache.wicket.markup.html.form.Form<T> {

    private Type type;

    /**
     * TODO document
     */
    public enum Type implements CssClassNameProvider {
        Vertical("form-vertical"), // Stacked, left-aligned labels over controls
        Inline("form-inline"), // Left-aligned label and inline-block controls for compact style
        Search("form-search"), // Extra-rounded text input for a typical search aesthetic
        Horizontal("form-horizontal"); // (default) Float left, right-aligned labels on same line as controls

        private final String cssClassName;

        Type(String cssClassName) {
            this.cssClassName = cssClassName;
        }

        @Override
        public String cssClassName() {
            return cssClassName;
        }

        @Override
        public CssClassNameAppender newCssClassNameAppender() {
            return new CssClassNameAppender(this);
        }
    }

    /**
     * Constructs a from with no validation
     *
     * @param componentId See Component
     */
    public BootstrapForm(String componentId) {
        super(componentId);

        commonInit();
    }

    /**
     * @see org.apache.wicket.Component#Component(String, IModel)
     * @param componentId See Component
     * @param model See Component
     */
    public BootstrapForm(String componentId, IModel<T> model) {
        super(componentId, model);

        commonInit();
    }

    /**
     * Common code executed by constructors.
     */
    private void commonInit() {
        type = Type.Horizontal;
    }
    
    public BootstrapForm<T> type(Type type) {
        this.type = type;

        return this;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        
        add(type.newCssClassNameAppender());
    }
}
