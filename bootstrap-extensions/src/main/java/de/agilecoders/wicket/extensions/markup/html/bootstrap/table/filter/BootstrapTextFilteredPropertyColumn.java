package de.agilecoders.wicket.extensions.markup.html.bootstrap.table.filter;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.TextFilter;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.TextFilteredPropertyColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * A bootstrap version of {@link TextFilteredPropertyColumn}.
 *
 * @author drummer
 */
public class BootstrapTextFilteredPropertyColumn<T, F, S> extends TextFilteredPropertyColumn<T, F, S> {

    private String filterPropertyExpression;

    public BootstrapTextFilteredPropertyColumn(final IModel<String> displayModel, final S sortProperty, final String propertyExpression,
                                               final String filterPropertyExpression) {
        super(displayModel, sortProperty, propertyExpression);
        this.filterPropertyExpression = filterPropertyExpression;
    }

    public BootstrapTextFilteredPropertyColumn(final IModel<String> displayModel, final String propertyExpression,
                                               final String filterPropertyExpression) {
        super(displayModel, propertyExpression);
        this.filterPropertyExpression = filterPropertyExpression;
    }

    @Override
    public Component getFilter(final String componentId, final FilterForm<?> form) {
        final TextFilter textFilter = (TextFilter) super.getFilter(componentId, form);
        textFilter.getFilter().add(new AttributeAppender("class", "form-control").setSeparator(" "));
        return textFilter;
    }

    @Override
    protected IModel<F> getFilterModel(final FilterForm<?> form) {
        return new PropertyModel(form.getDefaultModel(), filterPropertyExpression);
    }
}
