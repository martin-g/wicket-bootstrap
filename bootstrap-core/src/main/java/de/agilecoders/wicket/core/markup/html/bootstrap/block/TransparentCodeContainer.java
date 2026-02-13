package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import org.apache.wicket.markup.html.TransparentWebMarkupContainer;

/**
 * #### Description
 *
 * A simple "transparent" markup container that contains formatted content.
 *
 * see: {@link de.agilecoders.wicket.core.markup.html.bootstrap.block.Code}
 *
 * #### Usage
 *
 * ```java
 * TransparentCodeContainer code = new TransparentCodeContainer("id", Model.of("content"))
 *                  .setShowLineNumbers(true) // whether to show line numbers or not
 *                  .setLanguage(Language.JAVA) // use java as code language; default is dynamic
 *                  .setStartFromLine(20); // hide first 20 lines
 * add(code);
 * ```
 *
 * ```html
 * {@code <code wicket:id="id">content</code>}
 * ```
 *
 * It's possible to use `pre`, `code` and `xmp` as tag name.
 */
public class TransparentCodeContainer extends TransparentWebMarkupContainer {
    private static final long serialVersionUID = 1L;
    private final CodeBehavior behavior;

    /**
     * Construct.
     *
     * @param id The component id.
     */
    public TransparentCodeContainer(String id) {
        super(id);

        add(behavior = new CodeBehavior());
    }

    /**
     * @return true, if line numbers will be rendered
     */
    public final boolean hasLineNumbers() {
        return behavior.hasLineNumbers();
    }

    /**
     * adds line numbers on the left side of code block.
     *
     * @return this instance
     */
    public final TransparentCodeContainer setShowLineNumbers(final boolean showLineNumbers) {
        behavior.setShowLineNumbers(showLineNumbers);

        return this;
    }

    /**
     * defines from which line number the counting will start.
     *
     * @param from which line the numbers will count
     * @return this instance
     */
    public final TransparentCodeContainer setStartFromLine(final int from) {
        behavior.setStartFromLine(from);

        return this;
    }

    /**
     * sets the language.
     *
     * @param language the language to use
     * @return this instance
     */
    public final TransparentCodeContainer setLanguage(CodeBehavior.Language language) {
        behavior.setLanguage(language);

        return this;
    }
}
