package de.agilecoders.wicket.markup.html.bootstrap.block;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class Code extends WebMarkupContainer {

    private final CodeBehavior codeBehavior;

    /**
     * Constructor.
     *
     * @param componentId The non-null id of a new component
     */
    public Code(final String componentId) {
        this(componentId, null);
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
    public boolean hasLineNumbers() {
        return codeBehavior.hasLineNumbers();
    }

    /**
     * adds line numbers on the left side of code block.
     *
     * @return this instance
     */
    public Code addLineNumbers() {
        codeBehavior.addLineNumbers();

        return this;
    }

    /**
     * defines from which line number the counting will start.
     *
     * @param from which line the numbers will count
     * @return this instance
     */
    public Code from(final int from) {
        codeBehavior.from(from);

        return this;
    }

    /**
     * sets the language.
     *
     * @param language the language to use
     * @return this instance
     */
    public Code language(CodeBehavior.Language language) {
        codeBehavior.language(language);

        return this;
    }

}
