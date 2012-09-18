package de.agilecoders.wicket.markup.html.bootstrap.block;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.references.BootstrapPrettifyCssReference;
import de.agilecoders.wicket.markup.html.references.BootstrapPrettifyJavaScriptReference;
import de.agilecoders.wicket.util.CssClassNames;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.model.Model;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class CodeBehavior extends AssertTagNameBehavior {

    /**
     * enum that holds all possible languages
     */
    public enum Language {
        DYNAMIC, BSH, C, CC, CPP, CS, CSH, CYC, CV, HTM, HTML,
        JAVA, JS, M, MXML, PERL, PL, PM, PY, RB, SH,
        XHTML, XML, XSL;

        /**
         * @return the css class name of the selected language.
         */
        private String cssClassName() {
            if (!DYNAMIC.equals(this)) {
                return "lang-" + name().toLowerCase();
            }

            return "";
        }

    }

    private boolean lineNumbers = false;
    private Model<String> cssClassNameModel;
    private Language language = Language.DYNAMIC;
    private int from = 0;

    /**
     * Constructor.
     */
    public CodeBehavior() {
        super("code", "pre");

        this.cssClassNameModel = Model.of("");
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        response.render(CssHeaderItem.forReference(BootstrapPrettifyCssReference.INSTANCE));
        response.render(JavaScriptHeaderItem.forReference(BootstrapPrettifyJavaScriptReference.INSTANCE));

        response.render(OnLoadHeaderItem.forScript("window.prettyPrint && prettyPrint();"));
    }

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.add(new CssClassNameAppender(cssClassNameModel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        cssClassNameModel.setObject(CssClassNames.join(createCssClassNames()));
    }

    /**
     * @return a list of css classnames
     */
    private List<String> createCssClassNames() {
        return Lists.newArrayList("prettyprint",
                                  createLinenumsCssClass(),
                                  language.cssClassName());
    }

    /**
     * @return the css class name for the line number class
     */
    private String createLinenumsCssClass() {
        if (hasLineNumbers()) {
            return "linenums" + (from > 0 ? ":" + from : "");
        }

        return "";
    }

    /**
     * @return true, if line numbers will be rendered
     */
    public boolean hasLineNumbers() {
        return lineNumbers;
    }

    /**
     * adds line numbers on the left side of code block.
     *
     * @return this instance
     */
    public CodeBehavior setShowLineNumbers(final boolean showLineNumbers) {
        this.lineNumbers = showLineNumbers;

        return this;
    }

    /**
     * defines from which line number the counting will start.
     *
     * @param from which line the numbers will count
     * @return this instance
     */
    public CodeBehavior setStartFromLine(final int from) {
        this.from = from;

        return this;
    }

    /**
     * sets the language.
     *
     * @param language the language to use
     * @return this instance
     */
    public CodeBehavior setLanguage(Language language) {
        this.language = language;

        return this;
    }

}
