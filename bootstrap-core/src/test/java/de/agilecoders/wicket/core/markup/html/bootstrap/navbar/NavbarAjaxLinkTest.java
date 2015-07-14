package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;
import org.junit.Test;

/**
 * Tests the {@link NavbarAjaxLink} component
 *
 * @author miha
 */
public class NavbarAjaxLinkTest extends WicketApplicationTest {

    @Override
    protected void init(WebApplication app) {
        super.init(app);

        app.getMarkupSettings().setStripWicketTags(true);
    }

    @Test
    public void splitterIsVisibleIfIconIs() {
        startComponentInPage(new NavbarAjaxLink<String>(id(), Model.of("label")) {
            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
            }
        }.setIconType(GlyphIconType.adjust));

        tester().assertVisible("id:splitter");
        tester().assertContains("</i>&nbsp;label");
    }

    @Test
    public void splitterIsHiddenIfIconIs() {
        startComponentInPage(new NavbarAjaxLink<String>(id(), Model.of("label")) {
            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
            }
        }.setIconType(null));

        tester().assertInvisible("id:splitter");
        tester().assertContainsNot("&nbsp;label");
    }
}
