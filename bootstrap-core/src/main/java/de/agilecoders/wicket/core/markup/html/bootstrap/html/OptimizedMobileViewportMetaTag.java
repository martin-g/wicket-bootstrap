package de.agilecoders.wicket.core.markup.html.bootstrap.html;

/**
 * A special {@link MetaTag} that adds a TODO
 *
 * @author miha
 */
public class OptimizedMobileViewportMetaTag extends MetaTag {

    /**
     * Construct.
     *
     * @param id the markup id
     */
    public OptimizedMobileViewportMetaTag(final String id) {
        super(id, "viewport", "width=device-width, initial-scale=1");
    }
}
