package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import org.apache.wicket.markup.html.TransparentWebMarkupContainer;

/**
 * A simple transparent code container.
 *
 * @author miha
 */
public class TransparentCodeContainer extends TransparentWebMarkupContainer {

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
