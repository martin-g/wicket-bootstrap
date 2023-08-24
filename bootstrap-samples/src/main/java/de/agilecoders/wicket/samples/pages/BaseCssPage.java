package de.agilecoders.wicket.samples.pages;

import java.util.List;
import java.util.stream.Stream;

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

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Quote;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapCheckbox;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapRadioChoice;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.Breadcrumb;
import de.agilecoders.wicket.core.markup.html.bootstrap.table.TableContextBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.table.TableContextType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconTypeBuilder;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconTypeBuilder.FontAwesome6Brand;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconTypeBuilder.FontAwesome6Regular;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconTypeBuilder.FontAwesome6Solid;

/**
 * The {@code BaseCssPage}
 *
 * @author miha
 * @version 1.0
 */
@MountPath(value = "/basecss", alt = "/css")
public class BaseCssPage extends BasePage {
    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public BaseCssPage(PageParameters parameters) {
        super(parameters);

        addForms();

        add(newIconsPanel("iconsPanel"));

        add(newContextualTable("table-contextual"));

        add(newQuote("quote"));
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

    protected Component newQuote(String markupId) {
        return new Quote(markupId)
            .add(new Label("quotation", Model.of("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.")));
    }

    private void addForms() {
        Form<Void> form = new BootstrapForm<>("forms");
        add(form);

        form.add(new BootstrapCheckbox("checkbox", null, Model.of("Checkbox demo")));
        form.add(new BootstrapRadioChoice<>("radio", List.of("Radio One", "Radio Two")));
    }

    protected Component newBreadcrumb(String markupId) {
        Breadcrumb breadcrumb = new Breadcrumb(markupId);
        breadcrumb.setActive(new BreadCrumbPanel("breadcrumbid", breadcrumb) {
            private static final long serialVersionUID = 1L;

            @Override
            public IModel<String> getTitle() {
                return Model.of("Title");
            }
        });
        return breadcrumb;
    }

    protected Component newPagination(String markupId) {
        IPageable pageable = new IPageable() {
            private static final long serialVersionUID = 1L;

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

        Stream.of(Stream.of(FontAwesome6Solid.values()),
                    Stream.of(FontAwesome6Regular.values()),
                    Stream.of(FontAwesome6Brand.values()))
        .flatMap(i -> i)
        .forEach(graphic -> {
                Fragment iconFragment = new Fragment(view.newChildId(), "iconFragment", BaseCssPage.this);

                FontAwesome6IconType icon = FontAwesome6IconTypeBuilder
                    .on(graphic).fixedWidth().build();
                iconFragment.add(new Icon("iconValue", Model.of(icon)));
                iconFragment.add(new Label("iconName", icon.cssClassName()));
                view.add(iconFragment);
            });
        return view;
    }

    protected Component newContextualTable(String markup) {
        RepeatingView view = new RepeatingView(markup);

        for (TableContextType contextType : TableContextType.values()) {
            Fragment contextualRow = new Fragment(view.newChildId(), "contextual-row", BaseCssPage.this);
            contextualRow.add(new TableContextBehavior(contextType));
            contextualRow.add(new Label("contextual-class", contextType.cssClassName()));
            view.add(contextualRow);
        }

        return view;
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
