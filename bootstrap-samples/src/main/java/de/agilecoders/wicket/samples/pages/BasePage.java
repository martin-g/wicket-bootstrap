package de.agilecoders.wicket.samples.pages;

import com.newrelic.api.agent.NewRelic;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapExternalLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuHeader;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.*;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.*;
import de.agilecoders.wicket.core.markup.html.references.BootlintHeaderItem;
import de.agilecoders.wicket.core.markup.html.references.RespondJavaScriptReference;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ITheme;
import de.agilecoders.wicket.samples.WicketApplication;
import de.agilecoders.wicket.samples.assets.base.ApplicationJavaScript;
import de.agilecoders.wicket.samples.assets.base.DocsCssResourceReference;
import de.agilecoders.wicket.samples.components.site.Footer;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.FilteredHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Base wicket-bootstrap {@link org.apache.wicket.Page}
 *
 * @author miha
 */
abstract class BasePage extends GenericWebPage<Void> {

    /**
     * Construct.
     *
     * @param parameters current page parameters
     */
    public BasePage(final PageParameters parameters) {
        super(parameters);

        add(new HtmlTag("html"));
        MobileViewportMetaTag mvt = new MobileViewportMetaTag("viewport");
        mvt.setWidth("device-width");
        mvt.setInitialScale("1");
        add(mvt);
//        add(new OptimizedMobileViewportNoZoomMetaTag("viewport1"));
        add(new IeEdgeMetaTag("ie-edge"));
        add(new MetaTag("description", Model.of("description"), Model.of("Apache Wicket & Bootstrap Demo")));
        add(new MetaTag("author", Model.of("author"), Model.of("Michael Haitz <michael.haitz@agile-coders.de>")));

        add(newNavbar("navbar"));
        add(newNavigation("navigation"));
        add(new Footer("footer"));

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
     * @return application properties
     */
    public Properties getProperties() {
        return WicketApplication.get().getProperties();
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
        navbar.setInverted(true);

        // show brand name
        navbar.setBrandName(Model.of("Wicket Bootstrap"));

        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                        new NavbarButton<Void>(HomePage.class, Model.of("Overview")).setIconType(GlyphIconType.home),
                        new NavbarButton<Void>(BaseCssPage.class, Model.of("Base CSS")),
                        new NavbarButton<Void>(ComponentsPage.class, Model.of("Components")),
                        new NavbarExternalLink(Model.of("https://github.com/l0rdn1kk0n/wicket-bootstrap"))
                                .setLabel(Model.of("Github"))
                                .setTarget(BootstrapExternalLink.Target.blank)
                                .setIconType(GlyphIconType.export),
                        newAddonsDropDownButton())
        );
        navbar.addComponents(new NavbarText(navbar.newExtraItemId(), "Plain text").position(Navbar.ComponentPosition.RIGHT));

        DropDownButton dropdown = new NavbarDropDownButton(Model.of("Themes")) {
            @Override
            public boolean isActive(Component item) {
                return false;
            }

            @Override
            protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId) {
                final List<AbstractLink> subMenu = new ArrayList<>();
                subMenu.add(new MenuHeader(Model.of("all available themes:")));
                subMenu.add(new MenuDivider());

                final IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
                final List<ITheme> themes = settings.getThemeProvider().available();

                for (final ITheme theme : themes) {
                    PageParameters params = new PageParameters();
                    params.set("theme", theme.name());

                    subMenu.add(new MenuBookmarkablePageLink<Void>(getPageClass(), params, Model.of(theme.name())));
                }

                return subMenu;
            }
        }.setIconType(GlyphIconType.book);

        navbar.addComponents(new ImmutableNavbarComponent(dropdown, Navbar.ComponentPosition.RIGHT));

        return navbar;
    }

    /**
     * @return new dropdown button for all addons
     */
    private Component newAddonsDropDownButton() {
        return new NavbarDropDownButton(Model.of("Addons")) {
            /** serialVersionUID. */
            private static final long serialVersionUID = 1L;

            @Override
            protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
                final List<AbstractLink> subMenu = new ArrayList<>();

                subMenu.add(new MenuBookmarkablePageLink<Void>(Javascript.class, Model.of("Javascript")).setIconType(GlyphIconType.refresh));
                subMenu.add(new MenuBookmarkablePageLink<Void>(DatePickerPage.class, Model.of("DatePicker")).setIconType(GlyphIconType.time));
                subMenu.add(new MenuBookmarkablePageLink<Void>(SliderPage.class, Model.of("Slider")).setIconType(GlyphIconType.screenshot));
                subMenu.add(new MenuBookmarkablePageLink<Void>(DatetimePickerPage.class, Model.of("DateTimePicker")).setIconType(GlyphIconType.time));
                subMenu.add(new MenuBookmarkablePageLink<Void>(SelectPage.class, Model.of("SelectPicker")).setIconType(GlyphIconType.search));
                subMenu.add(new MenuBookmarkablePageLink<Void>(IssuesPage.class, Model.of("Github Issues")).setIconType(GlyphIconType.book));
                subMenu.add(new MenuBookmarkablePageLink<Void>(ExtensionsPage.class, Model.of("Extensions")).setIconType(GlyphIconType.alignjustify));
                subMenu.add(new MenuBookmarkablePageLink<Void>(ExtensionsBootstrapFileInputPage.class, Model.of("Extensions - Bootstrap FileInput")).setIconType(GlyphIconType.alignjustify));
                subMenu.add(new MenuBookmarkablePageLink<Void>(FontAwesomePage.class, Model.of("Font Awesome")).setIconType(GlyphIconType.font));
                subMenu.add(new MenuBookmarkablePageLink<Void>(XEditablePage.class, Model.of("X-Editable")).setIconType(GlyphIconType.pencil));
                subMenu.add(new MenuBookmarkablePageLink<Void>(TooltipValidationPage.class, Model.of("Validation")).setIconType(GlyphIconType.okcircle));
                subMenu.add(new MenuBookmarkablePageLink<Void>(SummernotePage.class, Model.of("Summernote")).setIconType(GlyphIconType.edit));
                subMenu.add(new MenuBookmarkablePageLink<Void>(MarkdownPage.class, Model.of("Markdown")).setIconType(GlyphIconType.edit));
                subMenu.add(new MenuBookmarkablePageLink<Void>(CheckboxesPage.class, Model.of("Checkboxes and Toggles")).setIconType(GlyphIconType.check));
                return subMenu;
            }
        }.setIconType(GlyphIconType.thlarge);
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

    protected ITheme activeTheme() {
        IBootstrapSettings settings = Bootstrap.getSettings(getApplication());

        return settings.getActiveThemeProvider().getActiveTheme();
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        configureTheme(getPageParameters());
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

//        response.render(CssHeaderItem.forReference(FixBootstrapStylesCssResourceReference.INSTANCE));
        response.render(new FilteredHeaderItem(JavaScriptHeaderItem.forReference(ApplicationJavaScript.INSTANCE), "footer-container"));
        response.render(RespondJavaScriptReference.headerItem());

        if ("google".equalsIgnoreCase(activeTheme().name())) {
            response.render(CssHeaderItem.forReference(DocsCssResourceReference.GOOGLE));
        }

        if (!getRequest().getRequestParameters().getParameterValue("bootlint").isNull()) {
            response.render(BootlintHeaderItem.INSTANCE);
        }
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

