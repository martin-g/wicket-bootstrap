package de.agilecoders.wicket.markup.html.bootstrap.behavior;

import de.agilecoders.wicket.WicketApplicationTest;
import de.agilecoders.wicket.test.IntegrationTest;
import de.agilecoders.wicket.util.Generics2;
import org.apache.wicket.AttributeModifier;
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
 * Tests the {@link de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameModifier}.
 *
 * @author miha
 * @version 1.0
 */
@Category(IntegrationTest.class)
public class CssClassNameModifierTest extends WicketApplicationTest {

    private Component component;

    @Override
    protected void onBefore() {
        super.onBefore();

        component = new WebMarkupContainer("id");
        component.setOutputMarkupId(true);
    }

    @Test
    public void classFromProviderIsAdded() {
        component.add(new CssClassNameModifier(new ICssClassNameProvider() {
            @Override
            public String cssClassName() {
                return "classX classY classZ";
            }
        }));

        startPageAndAssertClassNames("classX classY classZ");
    }

    @Test
    public void classFromModifierIsAdded() {
        component.add(new ICssClassNameModifier() {
            @Override
            public AttributeModifier newCssClassNameModifier() {
                return new CssClassNameAppender("classX classY classZ");
            }
        }.newCssClassNameModifier());

        startPageAndAssertClassNames("classX classY classZ");
    }

    @Test
    public void classFromModelIsAdded() {
        component.add(new CssClassNameModifier(Model.of("classX classY classZ")));

        startPageAndAssertClassNames("classX classY classZ");
    }

    @Test
    public void classFromArrayIsAdded() {
        component.add(new CssClassNameModifier("classX", "classY", "classZ"));

        startPageAndAssertClassNames("classX classY classZ");
    }

    @Test
    public void classFromListIsAdded() {
        component.add(new CssClassNameModifier(Generics2.newArrayList("classX", "classY", "classZ")));

        startPageAndAssertClassNames("classX classY classZ");
    }

    @Test
    public void allCssClassNamesBeforeWillBeRemoved() {
        component.add(new CssClassNameModifier("classX classY classZ"));
        component.add(new CssClassNameModifier("classU classV"));

        startPageAndAssertClassNames("classU classV");
    }

    private void startPageAndAssertClassNames(final String classNames) {
        tester().startComponentInPage(component);
        TagTester tester = tester().getTagByWicketId("id");

        assertThat(tester.getAttribute("class"), is(equalTo(classNames)));
    }
}
