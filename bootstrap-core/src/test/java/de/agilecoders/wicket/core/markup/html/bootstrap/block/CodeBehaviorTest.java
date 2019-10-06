package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link CodeBehavior} class
 *
 * @author miha
 */
class CodeBehaviorTest extends WicketApplicationTest {

    @Test
    void tagNameWasAsserted() {
        assertThrows(MarkupException.class, () ->
            tester().startComponentInPage(new WebMarkupContainer(id()).add(new CodeBehavior())));
    }

    @Test
    void codeTagIsAllowed() {
        tester().startComponentInPage(new WebMarkupContainer(id()).add(new CodeBehavior()), createMarkup("code"));

        tester().assertNoErrorMessage();
        tester().assertVisible(id());
    }

    @Test
    void preTagIsAllowed() {
        tester().startComponentInPage(new WebMarkupContainer(id()).add(new CodeBehavior()), createMarkup("pre"));

        tester().assertNoErrorMessage();
        tester().assertVisible(id());
    }

    @Test
    void linenumberWithStartCssClassNameWasSet() {
        tester().startComponentInPage(new WebMarkupContainer(id()).add(new CodeBehavior().setStartFromLine(5)), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "linenums:5");
    }

    @Test
    void linenumberCssClassNameWasSet() {
        tester().startComponentInPage(new WebMarkupContainer(id()).add(new CodeBehavior().setShowLineNumbers(true)), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "linenums");
    }

    @Test
    void languageCssClassNameWasSet() {
        tester().startComponentInPage(new WebMarkupContainer(id()).add(new CodeBehavior().setLanguage(CodeBehavior.Language.XHTML)), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "lang-xhtml");
    }

    @Test
    void combinationOfCssClassNamesAreSet() {
        tester().startComponentInPage(new WebMarkupContainer(id()).add(new CodeBehavior().setLanguage(CodeBehavior.Language.XHTML).setStartFromLine(5)), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "lang-xhtml", "linenums:5", "prettyprint");
    }

    @Test
    void defaultCssClassNamesAreSet() {
        tester().startComponentInPage(new WebMarkupContainer(id()).add(new CodeBehavior()), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "prettyprint");
    }

    /**
     * creates default markup for CodeBehavior
     *
     * @param tagName The tag name to use
     * @return code markup
     */
    private Markup createMarkup(final String tagName) {
        return Markup.of("<" + tagName + " wicket:id=\"" + id() + "\">" + "content" + "</" + tagName + ">");
    }

}
