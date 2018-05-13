package de.agilecoders.wicket.extensions.markup.html.bootstrap.table.filter;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.ChoiceFilteredPropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.util.List;

/**
 * A bootstrap version of {@link ChoiceFilteredPropertyColumn}.
 *
 * @author drummer
 */
public class BootstrapChoiceFilteredPropertyColumn<T, Y, S> extends ChoiceFilteredPropertyColumn<T, Y, S> {

    private String filterPropertyExpression;

    public BootstrapChoiceFilteredPropertyColumn(final IModel<String> displayModel, final S sortProperty, final String propertyExpression,
                                                 final IModel<? extends List<? extends Y>> filterChoices, final String filterPropertyExpression) {
        super(displayModel, sortProperty, propertyExpression, filterChoices);
        this.filterPropertyExpression = filterPropertyExpression;
    }

    public BootstrapChoiceFilteredPropertyColumn(final IModel<String> displayModel, final String propertyExpression,
                                                 final IModel<? extends List<? extends Y>> filterChoices, final String filterPropertyExpression) {
        super(displayModel, propertyExpression, filterChoices);
        this.filterPropertyExpression = filterPropertyExpression;
    }

    @Override
    public Component getFilter(final String componentId, final FilterForm<?> form) {

        final BootstrapSelectFilter<Y> filter =
            new BootstrapSelectFilter<>(componentId, getFilterModel(form), form, getFilterChoices(), enableAutoSubmit());
        final IChoiceRenderer<Y> renderer = getChoiceRenderer();
        if (renderer != null) {
            filter.getChoice().setChoiceRenderer(renderer);
        }
        return filter;
    }

    @Override
    protected IModel<Y> getFilterModel(final FilterForm<?> form) {
        return new PropertyModel<>(form.getDefaultModel(), filterPropertyExpression);
    }
}
