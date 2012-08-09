package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.WicketApplicationTest;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
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
    public void classFromProviderIsAdded() {
        component.add(new CssClassNameAppender(new CssClassNameProvider() {
            @Override
            public String cssClassName() {
                return "classX classY classZ";
            }

            @Override
            public CssClassNameAppender newCssClassNameAppender() {
                return null;
            }
        }));

        startPageAndAssertClassNames("classX classY classZ");
    }

    @Test
    public void classFromModelIsAdded() {
        component.add(new CssClassNameAppender(Model.of("classX classY classZ")));

        startPageAndAssertClassNames("classX classY classZ");
    }

    @Test
    public void classFromArrayIsAdded() {
        component.add(new CssClassNameAppender("classX", "classY", "classZ"));

        startPageAndAssertClassNames("classX classY classZ");
    }

    @Test
    public void classFromListIsAdded() {
        component.add(new CssClassNameAppender(Lists.newArrayList("classX", "classY", "classZ")));

        startPageAndAssertClassNames("classX classY classZ");
    }

    @Test
    public void duplicatedCssClassNamesWillBeRemoved() {
        component.add(new CssClassNameAppender("classX classY classZ"));
        component.add(new CssClassNameAppender("classZ"));
        component.add(new CssClassNameAppender("classX classY"));
        component.add(new CssClassNameAppender("classX classZ"));

        startPageAndAssertClassNames("classX classY classZ");
    }

    private void startPageAndAssertClassNames(final String classNames) {
        tester().startComponentInPage(component);
        TagTester tester = tester().getTagByWicketId("id");

        assertEquals(tester.getAttribute("class"), classNames);
    }
}
