package de.agilecoders.wicket.core.markup.html.bootstrap.html;

/**
 * A special {@link MetaTag} that adds the chrome-frame header tag.
 *
 * @author miha
 */
public class ChromeFrameMetaTag extends MetaTag {

    /**
     * Construct.
     *
     * @param id wicket markup id
     */
    public ChromeFrameMetaTag(final String id) {
        super(id, "X-UA-Compatible", "IE=edge,chrome=1");
    }
}