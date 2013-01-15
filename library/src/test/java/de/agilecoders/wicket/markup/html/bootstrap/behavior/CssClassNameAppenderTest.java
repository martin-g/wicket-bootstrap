package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.WicketApplicationTest;
import de.agilecoders.wicket.test.IntegrationTest;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


/**
 * Tests the {@link CssClassNameAppender}.
 *
 * @author miha
 * @version 1.0
 */
@Category(IntegrationTest.class)
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
        component.add(new CssClassNameAppender(new ICssClassNameProvider() {
            @Override
            public String cssClassName() {
                return "classX classY classZ";
            }

            @Override
            public CssClassNameAppender newCssClassNameModifier() {
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

        startPageAndAssertClassNames("classY classX classZ");
    }

    private void startPageAndAssertClassNames(final String classNames) {
        tester().startComponentInPage(component);
        TagTester tester = tester().getTagByWicketId("id");

        assertThat(tester.getAttribute("class"), is(equalTo(classNames)));
    }
}
