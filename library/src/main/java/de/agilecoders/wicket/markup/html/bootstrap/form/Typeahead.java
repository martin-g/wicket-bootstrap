package de.agilecoders.wicket.markup.html.bootstrap.form;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.layout.SpanType;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

import static de.agilecoders.wicket.util.JQuery.$;

/**
 * A basic, easily extended plugin for quickly creating elegant typeaheads with any {@link TextField}.
 *
 * @author miha
 */
public class Typeahead<T> extends TextField<T> {

    private final TypeaheadConfig config;
    private final InputBehavior inputBehavior;

    /**
     * Construct.
     *
     * @param markupId   The component's id
     * @param dataSource The data source for all available options
     */
    public Typeahead(final String markupId, final IDataSource<T> dataSource) {
        this(markupId, null, dataSource, new TypeaheadConfig());
    }

    /**
     * Construct.
     *
     * @param markupId   The component's id
     * @param dataSource The data source for all available options
     * @param config     The typeahead configuration
     */
    public Typeahead(final String markupId, final IDataSource<T> dataSource, final TypeaheadConfig config) {
        this(markupId, null, dataSource, config);
    }

    /**
     * Construct.
     *
     * @param markupId   The component's id
     * @param model      The textfield value
     * @param dataSource The data source for all available options
     */
    public Typeahead(final String markupId, final IModel<T> model, final IDataSource<T> dataSource) {
        this(markupId, model, dataSource, new TypeaheadConfig());
    }

    /**
     * Construct.
     *
     * @param markupId   The component's id
     * @param model      The textfield value
     * @param dataSource The data source for all available options
     * @param config     The typeahead configuration
     */
    public Typeahead(final String markupId, final IModel<T> model, final IDataSource<T> dataSource, final TypeaheadConfig config) {
        super(markupId, model);

        this.config = config.withDataSource(dataSource);

        add(inputBehavior = new InputBehavior());
        add(new AttributeModifier("data-provide", "typeahead"));

        BootstrapResourcesBehavior.addTo(this);
    }

    /**
     * sets the size of textfield
     *
     * @param size mandatory parameter
     * @return this instance for chaining
     */
    public Typeahead<T> size(final SpanType size) {
        this.inputBehavior.size(size);
        return this;
    }

    /**
     * sets the size of textfield
     *
     * @param size mandatory parameter
     * @return this instance for chaining
     */
    public Typeahead<T> size(final InputBehavior.Size size) {
        this.inputBehavior.size(size);
        return this;
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        response.render(OnDomReadyHeaderItem.forScript($(this).chain("typeahead", config).get()));
    }

}
