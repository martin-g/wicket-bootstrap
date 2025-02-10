package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.ICssClassNameProvider;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.prettyprint.PrettifyCssResourceReference;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.prettyprint.PrettifyJavaScriptReference;
import de.agilecoders.wicket.core.util.Components;
import de.agilecoders.wicket.core.util.CssClassNames;
import de.agilecoders.wicket.core.util.References;
import de.agilecoders.wicket.jquery.util.Strings2;

/**
 * #### Description
 *
 * Wrap inline snippets of code with <code> and use <pre> for multiple
 * lines of code. Angle brackets will be escaped in the code for proper rendering.
 *
 * #### Usage
 *
 * ```java
 * component.add(new CodeBehavior()
 *                  .setShowLineNumbers(true) // whether to show line numbers or not
 *                  .setLanguage(Language.JAVA) // use java as code language; default is dynamic
 *                  .setStartFromLine(20)); // hide first 20 lines
 * ```
 *
 * ```html
 * {@code <code wicket:id="id">content</code>}
 * ```
 *
 * It's possible to use `pre`, `code` and `xmp` as tag name.
 */
public class CodeBehavior extends Behavior {
    private static final long serialVersionUID = 1L;

	/**
     * enum that holds all possible languages
     */
    public enum Language implements ICssClassNameProvider {
        DYNAMIC, // use this or don't set a lang for auto detection

        // auto detected
        BSH, C, CC, CPP, CS, CSH, CYC, CV, HTM, HTML,
        JAVA, JS, M, MXML, PERL, PL, PM, PY, RB, SH,
        XHTML, XML, XSL,

        // plugins
        APOLLO, BASIC, CLJ, CSS, DART, ERLANG, GO, HS, LISP,
        LLVM, LUA, MATLAB, ML, MUMPS, N, PASCAL, PROTO, R, RD,
        SCALA, SQL, TCL, TEX, VB, VHDL, WIKI, XQ, YAML;

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

        response.render(CssHeaderItem.forReference(PrettifyCssResourceReference.INSTANCE));

        References.renderWithFilter(Bootstrap.getSettings(), response, JavaScriptHeaderItem.forReference(PrettifyJavaScriptReference.INSTANCE));

        response.render(OnDomReadyHeaderItem.forScript(createInitializerScript(Strings2.getMarkupId(component))));
    }

    private CharSequence createInitializerScript(CharSequence markupId) {
        return "$('#" + markupId + "').html(PR.prettyPrintOne($('#" + markupId + "').html().replace(/\\r\\n|\\r|\\n/g,'<br>'), '', $('#" + markupId + "').attr('class')));";
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

        Components.assertTag(component, tag, "code", "pre","xmp");
    }
}
