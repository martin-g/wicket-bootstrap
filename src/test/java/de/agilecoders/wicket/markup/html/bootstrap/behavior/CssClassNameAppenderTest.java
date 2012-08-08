package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.WicketApplicationTest;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class CssClassNameAppenderTest extends WicketApplicationTest {

    private Component component;

    @Override
    protected void onBefore() {
        super.onBefore();

        component = new WebMarkupContainer("id");
        component.setOutputMarkupId(true);
    }

    @Test
    public void duplicatedCssClassNamesWillBeRemoved() {
        component.add(new CssClassNameAppender("classX classY classZ"));
        component.add(new CssClassNameAppender("classZ"));
        component.add(new CssClassNameAppender("classX classY"));
        component.add(new CssClassNameAppender("classX classZ"));

        tester().startComponentInPage(component);
        TagTester tester = tester().getTagByWicketId("id");

        assertEquals(tester.getAttribute("class"), "classX classY classZ");
    }
}
