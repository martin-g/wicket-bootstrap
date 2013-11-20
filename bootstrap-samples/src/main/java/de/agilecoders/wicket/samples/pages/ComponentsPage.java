package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.SplitButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.samples.components.basecss.ButtonGroups;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ComponentsPage}
 *
 * @author miha
 */
@MountPath(value = "/components")
public class ComponentsPage extends BasePage {

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public ComponentsPage(PageParameters parameters) {
        super(parameters);

        add(newSplitButton("splitbutton"));
        add(new ButtonGroups("buttonGroups"));

        // add example for dropdown button with sub-menu
//        add(newDropDownSubMenuExample());
    }

    /**
     * creates a new split button with some submenu links.
     *
     * @param markupId the markup id of that the split button has to use
     * @return new {@link SplitButton} instance
     */
    private Component newSplitButton(final String markupId) {
        return new SplitButton(markupId, Model.of("Action")) {
            @Override
            protected AbstractLink newBaseButton(String markupId, IModel<String> labelModel, IModel<IconType> iconTypeModel) {
                return new BootstrapAjaxLink<String>(markupId, labelModel, Buttons.Type.Default) {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        target.appendJavaScript("alert('clicked');");
                    }
                };
            }

            @Override
            protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
                final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();
                subMenu.add(new MenuBookmarkablePageLink<Page>(ComponentsPage.class, Model.of("Link 1")));
                subMenu.add(new MenuBookmarkablePageLink<Page>(ComponentsPage.class, Model.of("Link 2")));
                subMenu.add(new MenuBookmarkablePageLink<Page>(ComponentsPage.class, Model.of("Link 3")));

                return subMenu;
            }
        };
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }

    /**
     * @return
     */
    private Component newDropDownSubMenuExample() {
        return new DropDownButton("dropDownSubMenuExample", Model.of("Addons")) {

            @Override
            protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
                List<AbstractLink> links = new ArrayList<AbstractLink>();

                links.add(new MenuBookmarkablePageLink<HomePage>(Javascript.class, Model.of("Javascript")).setIconType(GlyphIconType.refresh));
                links.add(new MenuBookmarkablePageLink<DatePickerPage>(DatePickerPage.class, Model.of("DatePicker")).setIconType(GlyphIconType.time));
                links.add(new MenuBookmarkablePageLink<IssuesPage>(IssuesPage.class, Model.of("Github Issues")).setIconType(GlyphIconType.book));
                links.add(new MenuBookmarkablePageLink<ExtensionsPage>(ExtensionsPage.class, Model.of("Extensions")).setIconType(GlyphIconType.alignjustify));
                links.add(new MenuDivider());

                return links;
            }
        }.setIconType(GlyphIconType.thlarge);
    }
}
