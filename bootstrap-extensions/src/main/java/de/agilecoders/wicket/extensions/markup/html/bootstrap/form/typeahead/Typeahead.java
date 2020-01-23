package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeahead;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.InputBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.layout.col.SpanType;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

import java.util.Collections;

import static de.agilecoders.wicket.jquery.JQuery.$;

/**
 * An integration with <a href="https://github.com/twitter/typeahead.js">Twitter Typeahead</a> plugin.
 */
public class Typeahead<T> extends TextField<T> {
    private static final long serialVersionUID = 1L;

    /**
     * The configuration for the JavaScript plugin.
     */
    private final Dataset config;

    /**
     * A behavior that can be used to set the size of the input field.
     */
    private final InputBehavior inputBehavior;

    /**
     * A behavior that provides the callback functionality.
     */
    private TypeaheadBehavior<T> remoteBehavior;

    /**
     * Constructor.
     *
     * @param markupId   The component's id
     * @param config     The typeahead configuration
     */
    public Typeahead(final String markupId, final Dataset config) {
        this(markupId, null, config);
    }

    /**
     * Constructor.
     *
     * @param markupId   The component's id
     * @param model      The textfield value
     * @param config     The typeahead configuration
     */
    public Typeahead(final String markupId, final IModel<T> model, final Dataset config) {
        super(markupId, model);

        this.config = Args.notNull(config, "config");

        add(inputBehavior = new InputBehavior());

        BootstrapResourcesBehavior.addTo(this);
    }

    /**
     * sets the size of textfield
     *
     * @param size mandatory parameter
     * @return this instance for chaining
     */
    public Typeahead<T> size(final SpanType size) {
        this.inputBehavior.size(size);
        return this;
    }

    /**
     * sets the size of textfield
     *
     * @param size mandatory parameter
     * @return this instance for chaining
     */
    public Typeahead<T> size(final InputBehavior.Size size) {
        this.inputBehavior.size(size);
        return this;
    }

    /**
     * Disables/Enables the remote functionality for this component
     *
     * @param remote a flag indicating whether the Typeahead uses remote data source
     * @return this instance for chaining
     */
    public Typeahead<T> remote(final boolean remote) {
        if (remote && remoteBehavior == null) {
            remoteBehavior = createTypeaheadBehavior();
            add(remoteBehavior);

        } else if (!remote && remoteBehavior != null) {
            remove(remoteBehavior);
            remoteBehavior = null;
        }

        return this;
    }

    protected TypeaheadBehavior<T> createTypeaheadBehavior() {
        return new TypeaheadBehavior<>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected Iterable<T> getChoices(String input) {
                return Typeahead.this.getChoices(input);
            }
        };
    }

    /**
     * Returns an iterable with all choices that match the
     * provided input.
     *
     * @param input The input to match against to.
     * @return All matching choices.
     */
    protected Iterable<T> getChoices(String input) {
        return Collections.emptyList();
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        checkComponentTag(tag, "input");

        super.onComponentTag(tag);
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        if (remoteBehavior != null) {
            Remote remoteConfig = config.getRemote();
            if (remoteConfig == null) {
                remoteConfig = new Remote();
                config.withRemote(remoteConfig);
            }
            remoteConfig.withUrl(remoteBehavior.getCallbackUrl());
        }

        response.render(newTypeaheadJsHeaderItem());
        response.render($(this).chain("typeahead", config).asDomReadyScript());
    }

    protected HeaderItem newTypeaheadJsHeaderItem() {
        return JavaScriptHeaderItem.forReference(TypeaheadJsReference.instance());
    }
}
