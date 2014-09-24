package de.agilecoders.wicket.core.markup.html.bootstrap.html;

/**
 * A special {@link de.agilecoders.wicket.core.markup.html.bootstrap.html.MetaTag} that adds the IE=edge
 * compatibility mode.
 */
public class IeEdgeMetaTag extends MetaTag {

    /**
     * Construct.
     *
     * @param id wicket markup id
     */
    public IeEdgeMetaTag(final String id) {
        super(id, "X-UA-Compatible", "IE=edge");

        type(Type.HttpEquiv);
    }
}
