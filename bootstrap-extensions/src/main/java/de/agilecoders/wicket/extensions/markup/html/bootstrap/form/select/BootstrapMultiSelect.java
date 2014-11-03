package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.model.IModel;

import java.util.Collection;
import java.util.List;

/**
 * Bootstrap multiple select choice.
 * Integrates <a href="http://silviomoreto.github.io/bootstrap-select">bootstrap select picker</a>
 *
 * @author Alexey Volkov
 * @since 02.11.14
 */
public class BootstrapMultiSelect<T> extends ListMultipleChoice<T> {

    private static final long serialVersionUID = 3096515143628484587L;

    private BootstrapSelectConfig config = new BootstrapSelectConfig().withMultiple(true);

    /**
     * @param id component id
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String)
     */
    public BootstrapMultiSelect(String id) {
        super(id);
    }

    /**
     * @param id      component id
     * @param choices list of choices
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, java.util.List)
     */
    public BootstrapMultiSelect(String id, List<? extends T> choices) {
        super(id, choices);
    }

    /**
     * Creates a multiple choice list with a maximum number of visible rows.
     *
     * @param id      component id
     * @param choices list of choices
     * @param maxRows the maximum number of visible rows.
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, java.util.List)
     */
    public BootstrapMultiSelect(String id, List<? extends T> choices, int maxRows) {
        super(id, choices, maxRows);
    }

    /**
     * @param id       component id
     * @param choices  list of choices
     * @param renderer renderer
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String,
     * java.util.List, org.apache.wicket.markup.html.form.IChoiceRenderer)
     */
    public BootstrapMultiSelect(String id, List<? extends T> choices,
            IChoiceRenderer<? super T> renderer) {
        super(id, choices, renderer);
    }

    /**
     * @param id      component id
     * @param object  model
     * @param choices list of choices
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, org.apache.wicket.model.IModel, java.util.List)
     */
    public BootstrapMultiSelect(String id, IModel<? extends Collection<T>> object,
            List<? extends T> choices) {
        super(id, object, choices);
    }

    /**
     * @param id       component id
     * @param object   model
     * @param choices  list of choices
     * @param renderer renderer
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, org.apache.wicket.model.IModel,
     * java.util.List, org.apache.wicket.markup.html.form.IChoiceRenderer)
     */
    public BootstrapMultiSelect(String id, IModel<? extends Collection<T>> object, List<? extends T> choices,
            IChoiceRenderer<? super T> renderer) {
        super(id, object, choices, renderer);
    }

    /**
     * @param id      component id
     * @param choices list of choices
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, org.apache.wicket.model.IModel)
     */
    public BootstrapMultiSelect(String id, IModel<? extends List<? extends T>> choices) {
        super(id, choices);
    }

    /**
     * @param id      component id
     * @param model   model
     * @param choices list of choices
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, org.apache.wicket.model.IModel, org.apache.wicket.model.IModel)
     */
    public BootstrapMultiSelect(String id, IModel<? extends Collection<T>> model,
            IModel<? extends List<? extends T>> choices) {
        super(id, model, choices);
    }

    /**
     * @param id       component id
     * @param choices  list of choices
     * @param renderer renderer
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String,
     * org.apache.wicket.model.IModel, org.apache.wicket.markup.html.form.IChoiceRenderer)
     */
    public BootstrapMultiSelect(String id, IModel<? extends List<? extends T>> choices,
            IChoiceRenderer<? super T> renderer) {
        super(id, choices, renderer);
    }

    /**
     * @param id       component id
     * @param model    model model
     * @param choices  list of choices
     * @param renderer renderer
     * @see org.apache.wicket.markup.html.form.AbstractChoice#AbstractChoice(String, org.apache.wicket.model.IModel,
     * org.apache.wicket.model.IModel, org.apache.wicket.markup.html.form.IChoiceRenderer)
     */
    public BootstrapMultiSelect(String id, IModel<? extends Collection<T>> model,
            IModel<? extends List<? extends T>> choices, IChoiceRenderer<? super T> renderer) {
        super(id, model, choices, renderer);
    }

    /**
     * @return current config
     */
    public BootstrapSelectConfig config() {
        return config;
    }

    /**
     * @param config config to use
     * @return current config
     */
    public BootstrapMultiSelect<T> with(BootstrapSelectConfig config) {
        if (config != null) {
            this.config = config;
        }
        this.config.withMultiple(true);
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(newBootstrapSelectBehavior(config));
    }

    /**
     * create new behavior by specified config
     *
     * @param config config
     * @return new instance of select behavior
     */
    protected BootstrapSelectBehavior newBootstrapSelectBehavior(BootstrapSelectConfig config) {
        return new BootstrapSelectBehavior(config);
    }

}
