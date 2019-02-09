package de.agilecoders.wicket.samples.components.base;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;
import org.apache.wicket.model.Model;

import java.util.List;

/**
 * State select
 *
 * @author Alexey Volkov
 * @since 08.11.14
 */
public class StateSelect extends BootstrapSelect<String> {

    /**
     * states
     */
    public static final List<String> DATA = Lists.newArrayList(
        "Alabama", "Alaska", "Arizona", "Arkansas",
        "California", "Colorado", "Connecticut",
        "Delaware", "Florida", "Georgia", "Hawaii",
        "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
        "Kentucky", "Louisiana", "Maine", "Maryland",
        "Massachusetts", "Michigan", "Minnesota",
        "Mississippi", "Missouri", "Montana", "Nebraska",
        "Nevada", "New Hampshire", "New Jersey",
        "New Mexico", "New York", "North Dakota",
        "North Carolina", "Ohio", "Oklahoma", "Oregon",
        "Pennsylvania", "Rhode Island", "South Carolina",
        "South Dakota", "Tennessee", "Texas", "Utah",
        "Vermont", "Virginia", "Washington",
        "West Virginia", "Wisconsin", "Wyoming"
    );

    private static final long serialVersionUID = -6140212749311855260L;

    /**
     * Constructor.
     *
     * @param id See Component
     */
    public StateSelect(String id) {
        super(id, Model.<>of(), DATA);
    }
}
