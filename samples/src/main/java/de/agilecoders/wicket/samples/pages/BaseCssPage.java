package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.markup.html.bootstrap.navigation.BootstrapPagingNavigator;
import de.agilecoders.wicket.markup.html.bootstrap.navigation.Breadcrumb;
import de.agilecoders.wicket.samples.components.basecss.DatePickerModal;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * The {@code BaseCssPage}
 *
 * @author miha
 * @version 1.0
 */
@MountPath(value = "/basecss", alt = "/css")
public class BaseCssPage extends BasePage {

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public BaseCssPage(PageParameters parameters) {
        super(parameters);

        add(new DateTextField("date"));

        add(newModalDialog("modal"));
        add(newPagination("pagination"));
        add(newBreadcrumb("breadcrumb"));
    }

    private Component newBreadcrumb(String markupId) {
        Breadcrumb breadcrumb = new Breadcrumb(markupId);
        breadcrumb.setActive(new BreadCrumbPanel("breadcrumbid", breadcrumb) {
            @Override
            public IModel<String> getTitle() {
                return Model.of("Title");
            }
        });
        return breadcrumb;
    }

    private Component newPagination(String markupId) {
        IPageable pageable = new IPageable() {
            @Override
            public long getCurrentPage() {
                return 1;
            }

            @Override
            public void setCurrentPage(long page) {

            }

            @Override
            public long getPageCount() {
                return 5;
            }
        };

        return new BootstrapPagingNavigator(markupId, pageable).setPosition(BootstrapPagingNavigator.Position.Centered);
    }

    private Component newModalDialog(String markupId) {
        return new DatePickerModal(markupId);
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
