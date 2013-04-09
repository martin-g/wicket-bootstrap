package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.markup.html.references.BootstrapPrettifyCssReference;
import de.agilecoders.wicket.core.markup.html.references.BootstrapPrettifyJavaScriptReference;
import de.agilecoders.wicket.core.util.Components;
import de.agilecoders.wicket.core.util.CssClassNames;
import de.agilecoders.wicket.core.util.References;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Wrap inline snippets of code with <code> and use <pre> for multiple
 * lines of code. Angle brackets will be escaped in the code for proper rendering.
 * <p/>
 * Example markup:
 * <pre>
 *     &lt;pre&gt;
 *         &lt;p&gt;Sample text here...&lt;/p&gt;
 *    &lt;/pre&gt;
 * </pre>
 *
 * @author miha
 */
public class CodeBehavior extends Behavior {

    /**
     * enum that holds all possible languages
     */
    public enum Language implements ICssClassNameProvider {
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

    }

    private final IModel<Boolean> lineNumbers;
    private final IModel<String> cssClassNameModel;
    private final IModel<Language> language;
    private final IModel<Integer> from;

    private final CssClassNameAppender cssClassNameAppender;

    /**
     * Constructor.
     */
    public CodeBehavior() {
        super();

        lineNumbers = Model.of(false);
        cssClassNameModel = Model.of("");
        language = Model.of(Language.DYNAMIC);
        from = Model.of(0);

        cssClassNameAppender = new CssClassNameAppender(cssClassNameModel);
    }

    @Override
    public void detach(Component component) {
        super.detach(component);

        lineNumbers.detach();
        cssClassNameModel.detach();
        language.detach();
        from.detach();
    }

    @Override
    public void renderHead(final Component component, final IHeaderResponse response) {
        super.renderHead(component, response);

        response.render(CssHeaderItem.forReference(BootstrapPrettifyCssReference.INSTANCE));

        References.renderWithFilter(Bootstrap.getSettings(), response, JavaScriptHeaderItem.forReference(BootstrapPrettifyJavaScriptReference.INSTANCE));

        response.render(OnDomReadyHeaderItem.forScript("window.prettyPrint && prettyPrint();"));
    }

    @Override
    public void bind(final Component component) {
        super.bind(component);

        BootstrapBaseBehavior.addTo(component);
        component.add(cssClassNameAppender);
    }

    @Override
    public void unbind(final Component component) {
        super.unbind(component);

        BootstrapBaseBehavior.removeFrom(component);
        component.remove(cssClassNameAppender);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConfigure(final Component component) {
        super.onConfigure(component);

        cssClassNameModel.setObject(createCssClassNames());
    }

    /**
     * @return a list of css class names
     */
    private String createCssClassNames() {
        return CssClassNames.parse("prettyprint").add(
                createLineNumbersCssClass(),
                language.getObject().cssClassName()).asString();
    }

    /**
     * @return the css class name for the line number class
     */
    private String createLineNumbersCssClass() {
        if (hasLineNumbers()) {
            return "linenums" + (from.getObject() > 0 ? ":" + from.getObject() : "");
        }

        return "";
    }

    /**
     * @return true, if line numbers will be rendered
     */
    public final boolean hasLineNumbers() {
        return lineNumbers.getObject();
    }

    /**
     * adds line numbers on the left side of code block.
     *
     * @return this instance
     */
    public final CodeBehavior setShowLineNumbers(final boolean showLineNumbers) {
        this.lineNumbers.setObject(showLineNumbers);

        return this;
    }

    /**
     * defines from which line number the counting will start.
     *
     * @param from which line the numbers will count
     * @return this instance
     */
    public final CodeBehavior setStartFromLine(final int from) {
        setShowLineNumbers(true);
        this.from.setObject(from);

        return this;
    }

    /**
     * sets the language.
     *
     * @param language the language to use
     * @return this instance
     */
    public final CodeBehavior setLanguage(final Language language) {
        this.language.setObject(language);

        return this;
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        super.onComponentTag(component, tag);

        Components.assertTag(component, tag, "code", "pre");
    }
}
