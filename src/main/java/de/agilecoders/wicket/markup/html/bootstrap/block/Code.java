package de.agilecoders.wicket.markup.html.bootstrap.block;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import de.agilecoders.wicket.markup.html.bootstrap.block.prettyprint.PrettyCssResourceReference;
import de.agilecoders.wicket.markup.html.bootstrap.block.prettyprint.PrettyJavaScriptReference;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.Arrays;
import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Code extends WebMarkupContainer {

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
    private Language language = Language.DYNAMIC;
    private int from = 0;

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     */
    public Code(final String componentId) {
        super(componentId);
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     * @param model       the component's model
     */
    public Code(final String componentId, final IModel<String> model) {
        super(componentId, model);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        response.renderCSSReference(PrettyCssResourceReference.INSTANCE, PrettyCssResourceReference.ID);
        response.renderJavaScriptReference(PrettyJavaScriptReference.INSTANCE, new PageParameters(), PrettyJavaScriptReference.ID, true);

        response.renderOnLoadJavaScript("window.prettyPrint && prettyPrint();");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onConfigure() {
        super.onConfigure();

        add(new CssClassNameAppender(createCssClassNames()));
    }

    /**
     * @return a list of css classnames
     */
    private List<String> createCssClassNames() {
        return Arrays.asList("prettyprint",
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
    public Code addLineNumbers() {
        this.lineNumbers = true;

        return this;
    }

    /**
     * defines from which line number the counting will start.
     *
     * @param from which line the numbers will count
     * @return this instance
     */
    public Code from(final int from) {
        this.from = from;

        return this;
    }

    /**
     * sets the language.
     *
     * @param language the language to use
     * @return this instance
     */
    public Code language(Language language) {
        this.language = language;

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        checkComponentTag(tag, "pre");
    }
}
