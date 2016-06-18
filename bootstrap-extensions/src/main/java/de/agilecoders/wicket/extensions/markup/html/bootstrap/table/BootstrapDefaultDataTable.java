package de.agilecoders.wicket.extensions.markup.html.bootstrap.table;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;

import de.agilecoders.wicket.core.markup.html.bootstrap.table.TableBehavior;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.table.toolbars.BootstrapHeadersToolbar;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.table.toolbars.BootstrapNavigationToolbar;

/**
 * An implementation of the DataTable that aims to solve the 90% usecase by adding navigation,
 * headers, an no-records-found toolbars to a standard {@link DataTable} stylized with Bootstrap.
 * <p>
 * The {@link BootstrapNavigationToolbar} and the {@link BootstrapHeadersToolbar} are added as top toolbars, while the
 * {@link NoRecordsToolbar} toolbar is added as a bottom toolbar.
 * 
 * @see DataTable
 * @see BootstrapNavigationToolbar
 * @see BootstrapHeadersToolbar
 * @see NoRecordsToolbar
 * 
 * @author Eric Hamel <eric.hamel@me.com>
 *
 * @param <T> the model object type
 * @param <S> the type of the sorting parameter
 */
public class BootstrapDefaultDataTable<T,S> extends DataTable<T, S> {
	
	private final TableBehavior tableBehavior;

	/**
	 * Construct.
	 * 
	 * @param id the component id
	 * @param columns list of columns
	 * @param dataProvider data provider
	 * @param rowsPerPage number of rows per page
	 */
	public BootstrapDefaultDataTable(String id, List<? extends IColumn<T,S>> columns, ISortableDataProvider<T,S> dataProvider, long rowsPerPage) {
		super(id, columns, dataProvider, rowsPerPage);

		add(tableBehavior = new TableBehavior());
		
		addTopToolbar(new BootstrapNavigationToolbar(this));
		addTopToolbar(new BootstrapHeadersToolbar<S>(this, dataProvider));
		addBottomToolbar(new NoRecordsToolbar(this));
	}

    /**
     * adds the "striped" style to table
     *
     * @return this instance for chaining
     */
    public BootstrapDefaultDataTable striped() {
    	tableBehavior.striped();
    	
        return this;
    }

    /**
     * adds the "condensed" style to table
     *
     * @return this instance for chaining
     */
    public BootstrapDefaultDataTable condensed() {
    	tableBehavior.condensed();
    	
        return this;
    }

    /**
     * adds the "bordered" style to table
     *
     * @return this instance for chaining
     */
    public BootstrapDefaultDataTable bordered() {
    	tableBehavior.bordered();
    	
        return this;
    }

    /**
     * adds the "hover" flag to table
     *
     * @return this instance for chaining
     */
    public BootstrapDefaultDataTable hover() {
    	tableBehavior.hover();
    	
        return this;
    }
}
