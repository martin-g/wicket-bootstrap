package de.agilecoders.wicket.core.markup.html.bootstrap.behavior;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

import de.agilecoders.wicket.core.WicketApplicationTest;


/**
 * Tests the {@link de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameModifier}.
 *
 * @author miha
 * @version 1.0
 */
public class CssClassNameModifierTest extends WicketApplicationTest {

    private Component component;

    @Override
    protected void onBefore() {
        super.onBefore();

        component = new WebMarkupContainer("id");
        component.setOutputMarkupId(true);
    }

    @Test
    void classFromProviderIsAdded() {
        component.add(new CssClassNameModifier((ICssClassNameProvider) () -> "classX classY classZ"));

        startPageAndAssertClassNames("classX classY classZ");
    }

    @Test
    void classFromModifierIsAdded() {
        component.add(new CssClassNameAppender("classX classY classZ"));

        startPageAndAssertClassNames("classX classY classZ");
    }

    @Test
    void classFromModelIsAdded() {
        component.add(new CssClassNameModifier(Model.of("classX classY classZ")));

        startPageAndAssertClassNames("classX classY classZ");
    }

    @Test
    void classFromArrayIsAdded() {
        component.add(new CssClassNameModifier("classX", "classY", "classZ"));

        startPageAndAssertClassNames("classX classY classZ");
    }

    @Test
    void classFromListIsAdded() {
        component.add(new CssClassNameModifier(List.of("classX", "classY", "classZ")));

        startPageAndAssertClassNames("classX classY classZ");
    }

    @Test
    void allCssClassNamesBeforeWillBeRemoved() {
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
