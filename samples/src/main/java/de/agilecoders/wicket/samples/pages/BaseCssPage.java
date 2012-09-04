package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.markup.html.bootstrap.navbar.AffixBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.ScrollSpyBehavior;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
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

        add(newNavigation("navigation"));
    }

    /**
     * creates a new navigation component.
     *
     * @param markupId The component's markup id
     * @return a new navigation component.
     */
    private Component newNavigation(String markupId) {
        WebMarkupContainer navigation = new WebMarkupContainer(markupId);
        navigation.add(new AffixBehavior("{\n"
                                         + "      offset: {\n"
                                         + "        top: function () { return $window.width() <= 980 ? 290 : 210 }\n"
                                         + "      , bottom: 270\n"
                                         + "}}"));
        navigation.add(new ScrollSpyBehavior());

        return navigation;
    }
}
