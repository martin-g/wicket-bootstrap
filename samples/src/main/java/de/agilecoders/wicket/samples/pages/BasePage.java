package de.agilecoders.wicket.samples.pages;

import java.util.List;
import java.util.Properties;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.FilteredHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.newrelic.api.agent.NewRelic;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.DropDownSubMenu;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuHeader;
import de.agilecoders.wicket.markup.html.bootstrap.extensions.button.DropDownAutoOpen;
import de.agilecoders.wicket.markup.html.bootstrap.html.ChromeFrameMetaTag;
import de.agilecoders.wicket.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.markup.html.bootstrap.html.MetaTag;
import de.agilecoders.wicket.markup.html.bootstrap.html.OptimizedMobileViewportMetaTag;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.AffixBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.ImmutableNavbarComponent;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarDropDownButton;
import de.agilecoders.wicket.samples.WicketApplication;
import de.agilecoders.wicket.samples.assets.base.ApplicationJavaScript;
import de.agilecoders.wicket.samples.assets.base.FixBootstrapStylesCssResourceReference;
import de.agilecoders.wicket.samples.components.site.Footer;
import de.agilecoders.wicket.settings.IBootstrapSettings;
import de.agilecoders.wicket.settings.ITheme;

/**
 * Base wicket-bootstrap {@link org.apache.wicket.Page}
 *
 * @author miha
 * @version 1.0
 */
abstract class BasePage<T> extends GenericWebPage<T> {

    /**
     * Construct.
     */
    public BasePage() {
        super();

        commonInit(new PageParameters());
    }

    /**
     * Construct.
     *
     * @param model The model to use for this page
     */
    public BasePage(IModel<T> model) {
        super(model);

        commonInit(new PageParameters());
    }

    /**
     * Construct.
     *
     * @param parameters current page parameters
     */
    public BasePage(PageParameters parameters) {
        super(parameters);

        commonInit(parameters);
    }

    /**
     * @return application properties
     */
    public Properties getProperties() {
        return WicketApplication.get().getProperties();
    }

    /**
     * common initializer
     *
     * @param pageParameters current page parameters
     */
    private void commonInit(PageParameters pageParameters) {
        add(new HtmlTag("html"));

        add(new OptimizedMobileViewportMetaTag("viewport"));
        add(new ChromeFrameMetaTag("chrome-frame"));
        add(new MetaTag("description", Model.of("description"), Model.of("Apache Wicket & Twitter Bootstrap Demo")));
        add(new MetaTag("author", Model.of("author"), Model.of("Michael Haitz <michael.haitz@agile-coders.de>")));

        add(newNavbar("navbar"));
        add(newNavigation("navigation"));
        add(new Footer("footer"));

        add(new BootstrapBaseBehavior());
        add(new Code("code-internal"));

        add(new HeaderResponseContainer("footer-container", "footer-container"));

        // add new relic RUM scripts.
        add(new Label("newrelic", Model.of(NewRelic.getBrowserTimingHeader()))
                    .setEscapeModelStrings(false)
                    .setRenderBodyOnly(true)
                    .add(new AttributeModifier("id", "newrelic-rum-header")));
        add(new Label("newrelic-footer", Model.of(NewRelic.getBrowserTimingFooter()))
                    .setEscapeModelStrings(false)
                    .setRenderBodyOnly(true)
                    .add(new AttributeModifier("id", "newrelic-rum-footer")));
    }

    /**
     * creates a new {@link Navbar} instance
     *
     * @param markupId The components markup id.
     * @return a new {@link Navbar} instance
     */
    protected Navbar newNavbar(String markupId) {
        Navbar navbar = new Navbar(markupId);

        navbar.setPosition(Navbar.Position.TOP);

        // show brand name
        navbar.brandName(Model.of("Wicket Bootstrap"));

        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                                                        new NavbarButton<HomePage>(HomePage.class, Model.of("Overview")).setIconType(IconType.home),
                                                        new NavbarButton<BaseCssPage>(BaseCssPage.class, Model.of("Base CSS")),
                                                        new NavbarButton<ComponentsPage>(ComponentsPage.class, Model.of("Components")),
                                                        new NavbarButton<HomePage>(Scaffolding.class, Model.of("Scaffolding")),
                                                        newAddonsDropDownButton())
        );

        DropDownButton dropdown = new NavbarDropDownButton(Model.of("Themes")) {
            @Override
            public boolean isActive(Component item) {
                return false;
            }
        };
        dropdown.add(new DropDownAutoOpen());

        dropdown.addButton(new MenuHeader(Model.of("all available themes:")))
                .addButton(new MenuDivider()).setIconType(IconType.book);

        IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
        List<ITheme> themes = settings.getThemeProvider().available();

        for (ITheme theme : themes) {
            PageParameters params = new PageParameters();
            params.set("theme", theme.name());

            dropdown.addButton(new MenuBookmarkablePageLink<Page>(getPageClass(), params, Model.of(theme.name())));
        }

        navbar.addComponents(new ImmutableNavbarComponent(dropdown, Navbar.ComponentPosition.RIGHT));

        return navbar;
    }

    /**
     * @return new dropdown button for all addons
     */
    private Component newAddonsDropDownButton() {
        return new NavbarDropDownButton(Model.of("Addons"))
                .addButton(new MenuBookmarkablePageLink<HomePage>(Javascript.class, Model.of("Javascript")).setIconType(IconType.refresh))
                .addButton(new MenuBookmarkablePageLink<DatePickerPage>(DatePickerPage.class, Model.of("DatePicker")).setIconType(IconType.time))
                .addButton(new MenuBookmarkablePageLink<IssuesPage>(IssuesPage.class, Model.of("Github Issues")).setIconType(IconType.book))
                .addButton(new MenuBookmarkablePageLink<ExtensionsPage>(ExtensionsPage.class, Model.of("Extensions")).setIconType(IconType.alignjustify))
                .addButton(new DropDownSubMenu(Model.of("Sub-Menu Example"))
                	.addButton(new MenuBookmarkablePageLink<HomePage>(Javascript.class, Model.of("Javascript")).setIconType(IconType.refresh))
                	.addButton(new MenuBookmarkablePageLink<DatePickerPage>(DatePickerPage.class, Model.of("DatePicker")).setIconType(IconType.time)))
                .setIconType(IconType.thlarge).add(new DropDownAutoOpen());
    }

    /**
     * sets the theme for the current user.
     *
     * @param pageParameters current page parameters
     */
    private void configureTheme(PageParameters pageParameters) {
        StringValue theme = pageParameters.get("theme");

        if (!theme.isEmpty()) {
            IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
            settings.getActiveThemeProvider().setActiveTheme(theme.toString(""));
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        configureTheme(getPageParameters());
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.render(CssHeaderItem.forReference(FixBootstrapStylesCssResourceReference.INSTANCE));
        response.render(new FilteredHeaderItem(JavaScriptHeaderItem.forReference(ApplicationJavaScript.INSTANCE), "footer-container"));
    }

    protected boolean hasNavigation() {
        return false;
    }

    /**
     * creates a new navigation component.
     *
     * @param markupId The component's markup id
     * @return a new navigation component.
     */
    private Component newNavigation(String markupId) {
        WebMarkupContainer navigation = new WebMarkupContainer(markupId);
        navigation.add(new AffixBehavior("200"));
        navigation.setVisible(hasNavigation());

        return navigation;
    }

}