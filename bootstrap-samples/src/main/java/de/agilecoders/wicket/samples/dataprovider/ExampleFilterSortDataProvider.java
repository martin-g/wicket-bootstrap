package de.agilecoders.wicket.samples.dataprovider;

import de.agilecoders.wicket.samples.dataprovider.ExampleFilterSortDataProvider.ExampleFilter;
import de.agilecoders.wicket.samples.dataprovider.ExampleFilterSortDataProvider.ExampleFilterSortRowItem;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A dataprovider to show filtering and sorting. Note that this implementation is not optimal for production use.
 *
 * @author drummer on 23.04.2018.
 */
public class ExampleFilterSortDataProvider extends SortableDataProvider<ExampleFilterSortRowItem, String> implements IFilterStateLocator<ExampleFilter> {

    private static final List<String> possibleLastNames = Arrays.asList("Meier", "Meyer", "Maier", "Müller", "Schulze", "Schmidt");
    private static final List<String> possibleFirstNames = Arrays.asList("Horst", "Klaus", "Gert", "Frank", "Hermann", "Sven", "Stefan");

    private List<ExampleFilterSortRowItem> allRowItems;
    private List<ExampleFilterSortRowItem> filteredRowItems;

    private ExampleFilter filterState = new ExampleFilter();

    public ExampleFilterSortDataProvider() {

        allRowItems = createTestData();
        filteredRowItems = allRowItems;
    }

    @Override
    public ExampleFilter getFilterState() {
        return filterState;
    }

    @Override
    public void setFilterState(ExampleFilter exampleFilter) {
        this.filterState = exampleFilter;
    }

    @Override
    public Iterator<? extends ExampleFilterSortRowItem> iterator(long first, long count) {

        int from = (int) first;
        int to = (int) (first + count);
        if (to > filteredRowItems.size()) {
            to = filteredRowItems.size();
        }

        SortParam<String> sortParam = getSort();
        if (sortParam != null) {
            filteredRowItems.sort(getComparator(sortParam));
        }

        return filteredRowItems.subList(from, to).iterator();
    }

    @Override
    public long size() {
        doFilter(); // do filtering in size method because this is called before the iterator method.
        return filteredRowItems == null ? 0 : filteredRowItems.size();
    }

    @Override
    public IModel<ExampleFilterSortRowItem> model(ExampleFilterSortRowItem object) {
        return Model.of(object);
    }

    private Comparator<ExampleFilterSortRowItem> getComparator(final SortParam<String> sortParam) {
        Comparator<ExampleFilterSortRowItem> comparator;
        if (ExampleFilterSortRowItem.SORTPARAM_FIRST_NAME.equals(sortParam.getProperty())) {
            comparator = Comparator.nullsFirst(Comparator.comparing(item -> item.firstName));
        } else if (ExampleFilterSortRowItem.SORTPARAM_LAST_NAME.equals(sortParam.getProperty())) {
            comparator = Comparator.nullsFirst(Comparator.comparing(item -> item.lastName));
        } else if (ExampleFilterSortRowItem.SORTPARAM_GENDER.equals(sortParam.getProperty())) {
            comparator = Comparator.nullsFirst(Comparator.comparing(item -> item.gender));
        } else {
            throw new IllegalStateException("The sort param " + sortParam.getProperty() + " is not yet supprted");
        }
        if (!sortParam.isAscending()) {
            comparator = comparator.reversed();
        }

        return comparator;
    }

    private void doFilter() {
        filteredRowItems = allRowItems;
        if (filterState != null) {
            if (checkPropNotEmpty(filterState.firstName)) {
                filteredRowItems = filteredRowItems.stream()
                    .filter(f -> f.firstName != null)
                    .filter(f -> f.firstName.contains(filterState.firstName))
                    .collect(Collectors.toList());
            }
            if (checkPropNotEmpty(filterState.lastName)) {
                filteredRowItems = filteredRowItems.stream()
                    .filter(f -> f.lastName != null)
                    .filter(f -> f.lastName.contains(filterState.lastName))
                    .collect(Collectors.toList());
            }
            if (filterState.genderType != null) {
                filteredRowItems = filteredRowItems.stream()
                    .filter(f -> f.gender != null)
                    .filter(f -> f.gender == filterState.genderType)
                    .collect(Collectors.toList());
            }
        }
    }

    private List<ExampleFilterSortRowItem> createTestData() {

        final List<ExampleFilterSortRowItem> rowItems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ExampleFilterSortRowItem rowItem = new ExampleFilterSortRowItem();
            rowItem.firstName = getRandomFirstName();
            rowItem.lastName = getRandomLastName();
            // we only have two genders here because of ease in testData generation.
            rowItem.gender = i % 2 == 0 ? GenderType.MALE : GenderType.FEMALE;

            rowItems.add(rowItem);
        }
        return rowItems;
    }

    private String getRandomLastName() {
        final Random random = new Random();
        final int index = random.nextInt(possibleLastNames.size());
        return possibleLastNames.get(index);
    }

    private String getRandomFirstName() {
        final Random random = new Random();
        final int index = random.nextInt(possibleFirstNames.size());
        return possibleFirstNames.get(index);
    }

    private boolean checkPropNotEmpty(final String propertyValue) {
        return (propertyValue != null && !propertyValue.isEmpty());
    }

    /**
     * Lets assume that the row items holds informations about a person. The gender of that person is represented by an enumeration.
     */
    public static class ExampleFilterSortRowItem implements Serializable {

        public static final String PROPERTY_FIRST_NAME = "firstName";
        public static final String PROPERTY_LAST_NAME = "lastName";
        public static final String PROPERTY_GENDER = "gender";

        public static final String SORTPARAM_FIRST_NAME = "firstNameSortParam";
        public static final String SORTPARAM_LAST_NAME = "lastNameSortParam";
        public static final String SORTPARAM_GENDER = "genderSortParam";

        public String firstName;
        public String lastName;
        public GenderType gender;

        @Override
        public final String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    public static class ExampleFilter implements Serializable {

        // the constants muss have the same value as the referenced property is named.
        public static final String FILTER_FIRST_NAME = "firstName";
        public static final String FILTER_LAST_NAME = "lastName";
        public static final String FILTER_GENDER_TYPE = "genderType";

        public String firstName;
        public String lastName;
        public GenderType genderType;

        @Override
        public final String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    public enum GenderType {

        MALE("male"),
        FEMALE("female");

        private final String displayValue;

        GenderType(final String displayValue) {
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }
    }
}
