package de.agilecoders.wicket.extensions.markup.html.bootstrap.table;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.DataGridView;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByLink;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationIncrementLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.WicketApplicationTest;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.table.toolbars.BootstrapHeadersToolbar;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.table.toolbars.BootstrapNavigationToolbar;
import org.junit.jupiter.api.Test;

public class BootstrapDefaultDataTableTest extends WicketApplicationTest {

    private static final String MARKUP = "<html><head></head><body><table class='dataview' wicket:id='table'/></body></html>";

    private final List<IColumn<DemoType, String>> columns = new ArrayList<>();
    private final ISortableDataProvider<DemoType, String> provider = new DemoProvider();
    private BootstrapDefaultDataTable<DemoType, String> table;

    @Override
    protected void onBefore() {
        columns.add(new PropertyColumn<>(Model.of("id"), "id", "id"));
        columns.add(new PropertyColumn<>(Model.of("name"), "name", "name"));
    }

    @Test
    public void canStartComponentInPage() {
        startComponentWithRowsPerPage(10);
        tester().assertComponent("table", BootstrapDefaultDataTable.class);
        tester().assertComponent("table:caption", Label.class);
    }

    private void startComponentWithRowsPerPage(int rowsPerPage) {
        table = new BootstrapDefaultDataTable<>("table", columns, provider, rowsPerPage);
        tester().startComponentInPage(table, Markup.of(MARKUP));
    }

    @Test
    public void canShowModelValues() {
        startComponentWithRowsPerPage(10);
        assertTableBody("table:body");
    }

    private void assertTableBody(final String path) {
        tester().assertComponent(path, WebMarkupContainer.class);
        tester().assertComponent(path + ":rows", DataGridView.class);

        String r1 = path + ":rows:1";
        tester().assertComponent(r1, Item.class);
        String r1c = r1 + ":cells";
        tester().assertComponent(r1c, RepeatingView.class);
        tester().assertComponent(r1c + ":1", Item.class);
        tester().assertComponent(r1c + ":1:cell", Label.class);
        tester().assertComponent(r1c + ":2", Item.class);
        tester().assertComponent(r1c + ":2:cell", Label.class);

        String valuePath = ":cells:2:cell";
        tester().assertModelValue(r1 + valuePath, "foo");
        String r2 = path + ":rows:2";
        tester().assertComponent(r2, Item.class);
        tester().assertModelValue(r2 + valuePath, "bar");
        String r3 = path + ":rows:3";
        tester().assertComponent(r3, Item.class);
        tester().assertModelValue(r3 + valuePath, "baz");
    }

    @Test
    public void hasInvisibleBottomToolbar() {
        startComponentWithRowsPerPage(10);
        tester().assertInvisible("table:bottomToolbars");
    }

    @Test
    public void hasVisibleHeaderTopToolbar() {
        startComponentWithRowsPerPage(10);
        tester().assertComponent("table:topToolbars", WebMarkupContainer.class);
        tester().debugComponentTrees();
        assertHeadersToolbar("table:topToolbars:toolbars");
    }

    private void assertHeadersToolbar(final String path) {
        tester().assertComponent(path, RepeatingView.class);
        tester().assertComponent(path + ":2", BootstrapHeadersToolbar.class);
        String hp = path + ":2:headers";
        tester().assertComponent(hp, RefreshingView.class);
        tester().assertComponent(hp + ":1", Item.class);
        tester().assertComponent(hp + ":1:header", WebMarkupContainer.class);
        tester().assertComponent(hp + ":1:header:orderByLink", OrderByLink.class);
        tester().assertModelValue(hp + ":1:header:orderByLink:header_body:label", "id");
        tester().assertModelValue(hp + ":2:header:orderByLink:header_body:label", "name");
    }

    @Test
    public void assertInvisibleNavigationToolbars_withFewerRowsThanRowsPerPage() {
        int rowsPerPage = 10;
        assertTrue(provider.size() <= rowsPerPage);

        startComponentWithRowsPerPage(rowsPerPage);
        assertNavigationToolbarInvisible("table:topToolbars:toolbars:1");
    }

    private void assertNavigationToolbarInvisible(String path) {
        tester().assertInvisible(path);
        String np = path + ":span:navigator";
        tester().assertInvisible(np);
        tester().assertInvisible(np + ":navigation");
        tester().assertInvisible(np + ":first");
        tester().assertInvisible(np + ":prev");
        tester().assertInvisible(np + ":next");
        tester().assertInvisible(np + ":last");
        tester().assertInvisible(np + ":firstItem");
        tester().assertInvisible(np + ":prevItem");
        tester().assertInvisible(np + ":nextItem");
        tester().assertInvisible(np + ":lastItem");
    }

    @Test
    public void assertVisibleNavigationToolbars_withMoreRowsThanRowsPerPage() {
        int rowsPerPage = 2;
        assertTrue(provider.size() > rowsPerPage);
        startComponentWithRowsPerPage(rowsPerPage);
        assertNavigationToolbarVisible("table:topToolbars:toolbars:1");
    }

    private void assertNavigationToolbarVisible(String path) {
        tester().assertComponent(path, BootstrapNavigationToolbar.class);
        tester().assertComponent(path + ":span", WebMarkupContainer.class);
        String np = path + ":span:navigator";
        tester().assertComponent(np, BootstrapPagingNavigator.class);
        tester().assertComponent(np + ":navigation", PagingNavigation.class);
        tester().assertComponent(np + ":first", PagingNavigationLink.class);
        tester().assertComponent(np + ":prev", PagingNavigationIncrementLink.class);
        tester().assertComponent(np + ":next", PagingNavigationIncrementLink.class);
        tester().assertComponent(np + ":last", PagingNavigationLink.class);
        tester().assertComponent(np + ":firstItem", TransparentWebMarkupContainer.class);
        tester().assertComponent(np + ":prevItem", TransparentWebMarkupContainer.class);
        tester().assertComponent(np + ":nextItem", TransparentWebMarkupContainer.class);
        tester().assertComponent(np + ":lastItem", TransparentWebMarkupContainer.class);
    }

    @Test
    public void basic() {
        table = new BootstrapDefaultDataTable<>("table", columns, provider, 10);
        tester().startComponentInPage(table, Markup.of(MARKUP));
        assertEquals("dataview table", tester().getTagByWicketId("table").getAttribute("class"));
    }

    @Test
    public void striped() {
        table = new BootstrapDefaultDataTable<>("table", columns, provider, 10);
        table.striped();
        tester().startComponentInPage(table, Markup.of(MARKUP));
        assertTrue(tester().getTagByWicketId("table").getAttribute("class").contains("table-striped"));
    }

    @Test
    public void bordered() {
        table = new BootstrapDefaultDataTable<>("table", columns, provider, 10);
        table.bordered();
        tester().startComponentInPage(table, Markup.of(MARKUP));
        assertTrue(tester().getTagByWicketId("table").getAttribute("class").contains("table-bordered"));
    }

    @Test
    public void sm() {
        table = new BootstrapDefaultDataTable<>("table", columns, provider, 10);
        table.sm();
        tester().startComponentInPage(table, Markup.of(MARKUP));
        assertTrue(tester().getTagByWicketId("table").getAttribute("class").contains("table-sm"));
    }

    @Test
    public void hover() {
        table = new BootstrapDefaultDataTable<>("table", columns, provider, 10);
        table.hover();
        tester().startComponentInPage(table, Markup.of(MARKUP));
        assertTrue(tester().getTagByWicketId("table").getAttribute("class").contains("table-hover"));
    }

    @Test
    public void dark() {
        table = new BootstrapDefaultDataTable<>("table", columns, provider, 10);
        table.dark();
        tester().startComponentInPage(table, Markup.of(MARKUP));
        assertTrue(tester().getTagByWicketId("table").getAttribute("class").contains("table-dark"));
    }

    @Test
    public void light() {
        table = new BootstrapDefaultDataTable<>("table", columns, provider, 10);
        table.light();
        tester().startComponentInPage(table, Markup.of(MARKUP));
        assertTrue(tester().getTagByWicketId("table").getAttribute("class").contains("table-light"));
    }

}
