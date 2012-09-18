package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.AssertTagNameBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameProvider;
import de.agilecoders.wicket.markup.html.references.BootstrapPrettifyCssReference;
import de.agilecoders.wicket.markup.html.references.BootstrapPrettifyJavaScriptReference;
import de.agilecoders.wicket.util.CssClassNames;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.Model;

/**
 * Wrap inline snippets of code with <code> and use <pre> for multiple
 * lines of code. Angle brackets will be escaped in the code for proper rendering.
 *
 * Example markup:
 * <pre>
 *     &lt;pre&gt;
 *         &lt;p&gt;Sample text here...&lt;/p&gt;
 *    &lt;/pre&gt;
 * </pre>
 *
 * @author miha
 * @version 1.0
 */
public class CodeBehavior extends AssertTagNameBehavior {

    /**
     * enum that holds all possible languages
     */
    public enum Language implements CssClassNameProvider {
        DYNAMIC, BSH, C, CC, CPP, CS, CSH, CYC, CV, HTM, HTML,
        JAVA, JS, M, MXML, PERL, PL, PM, PY, RB, SH,
        XHTML, XML, XSL;

        @Override
        public String cssClassName() {
            if (!DYNAMIC.equals(this)) {
                return "lang-" + name().toLowerCase();
            }

            return "";
        }

        @Override
        public AttributeModifier newCssClassNameModifier() {
            return new CssClassNameAppender(this);
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

        response.render(OnDomReadyHeaderItem.forScript("window.prettyPrint && prettyPrint();"));
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

        cssClassNameModel.setObject(createCssClassNames());
    }

    /**
     * @return a list of css class names
     */
    private String createCssClassNames() {
        return CssClassNames.parse("prettyprint").add(
                createLineNumbersCssClass(),
                language.cssClassName()).asString();
    }

    /**
     * @return the css class name for the line number class
     */
    private String createLineNumbersCssClass() {
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
    public CodeBehavior setLanguage(final Language language) {
        this.language = language;

        return this;
    }

}
