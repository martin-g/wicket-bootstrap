package de.agilecoders.wicket.samples.pages;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapCheckbox;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapRadioChoice;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.Breadcrumb;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder;

/**
 * The {@code BaseCssPage}
 *
 * @author miha
 * @version 1.0
 */
@MountPath(value = "/basecss", alt = "/css")
public class BaseCssPage extends BasePage {

    //private final DatePickerModal modal;

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public BaseCssPage(PageParameters parameters) {
        super(parameters);

        addForms();

        add(newIconsPanel("iconsPanel"));
/*
        add(new DateTextField("date"));

        add(modal = newModalDialog("modal"));
        Button button = new Button("open-modal");
        modal.addOpenerAttributesTo(button);
        add(button);

        add(newPagination("pagination"));
        add(newBreadcrumb("breadcrumb"));
*/
    }

    private void addForms() {
        Form<Void> form = new BootstrapForm<>("forms");
        add(form);

        form.add(new BootstrapCheckbox("checkbox", null, Model.of("Checkbox demo")));
        form.add(new BootstrapRadioChoice<>("radio", Lists.newArrayList("Radio One", "Radio Two")));
    }

    protected Component newBreadcrumb(String markupId) {
        Breadcrumb breadcrumb = new Breadcrumb(markupId);
        breadcrumb.setActive(new BreadCrumbPanel("breadcrumbid", breadcrumb) {
            @Override
            public IModel<String> getTitle() {
                return Model.of("Title");
            }
        });
        return breadcrumb;
    }

    protected Component newPagination(String markupId) {
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

        return new BootstrapPagingNavigator(markupId, pageable)/*.setPosition(BootstrapPagingNavigator.Position.Centered)*/;
    }

    protected Component newIconsPanel(String markupId) {
        RepeatingView view = new RepeatingView(markupId);

        for (FontAwesomeIconTypeBuilder.FontAwesomeGraphic graphic : FontAwesomeIconTypeBuilder.FontAwesomeGraphic.values()) {
            Fragment iconFragment = new Fragment(view.newChildId(), "iconFragment", BaseCssPage.this);

            FontAwesomeIconType icon = FontAwesomeIconTypeBuilder.on(graphic).fixedWidth().build();
            iconFragment.add(new Icon("iconValue", Model.of(icon)));
            iconFragment.add(new Label("iconName", icon.cssClassName()));
            view.add(iconFragment);
        }
        return view;
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
