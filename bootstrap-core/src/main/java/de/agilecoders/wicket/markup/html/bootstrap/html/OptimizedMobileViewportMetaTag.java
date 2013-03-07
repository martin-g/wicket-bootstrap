package de.agilecoders.wicket.markup.html.bootstrap.html;

import org.apache.wicket.model.Model;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class OptimizedMobileViewportMetaTag extends MetaTag {
    public OptimizedMobileViewportMetaTag(String id) {
        super(id, Model.of("viewport"), Model.of("width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no"));
    }
}
