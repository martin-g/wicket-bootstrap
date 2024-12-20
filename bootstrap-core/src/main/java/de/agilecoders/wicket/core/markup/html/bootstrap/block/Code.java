package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * #### Description
 *
 * Wrap inline snippets of code with <code> and use <pre> for multiple
 * lines of code. Angle brackets will be escaped in the code for proper rendering.
 *
 * #### Usage
 *
 * ```java
 * Code code = new Code("id", Model.of("content"))
 *                  .setShowLineNumbers(true) // whether to show line numbers or not
 *                  .setLanguage(Language.JAVA) // use java as code language; default is dynamic
 *                  .setStartFromLine(20); // hide first 20 lines
 * add(code);
 * ```
 *
 * ```html
 * {@code <code wicket:id="id"></code>}
 * ```
 *
 * It's possible to use `pre`, `code` and `xmp` as tag name.
 */
public class Code extends Label {

    private final CodeBehavior codeBehavior;

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     */
    public Code(final String componentId) {
        this(componentId, new Model<>(""));
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     * @param model       the component's model
     */
    public Code(final String componentId, final IModel<String> model) {
        super(componentId, model);

        codeBehavior = new CodeBehavior();
        add(codeBehavior);
    }

    /**
     * @return true, if line numbers will be rendered
     */
    public final boolean hasLineNumbers() {
        return codeBehavior.hasLineNumbers();
    }

    /**
     * adds line numbers on the left side of code block.
     *
     * @return this instance
     */
    public final Code setShowLineNumbers(final boolean showLineNumbers) {
        codeBehavior.setShowLineNumbers(showLineNumbers);

        return this;
    }

    /**
     * defines from which line number the counting will start.
     *
     * @param from which line the numbers will count
     * @return this instance
     */
    public final Code setStartFromLine(final int from) {
        codeBehavior.setStartFromLine(from);

        return this;
    }

    /**
     * sets the language.
     *
     * @param language the language to use
     * @return this instance
     */
    public final Code setLanguage(CodeBehavior.Language language) {
        codeBehavior.setLanguage(language);

        return this;
    }

}
