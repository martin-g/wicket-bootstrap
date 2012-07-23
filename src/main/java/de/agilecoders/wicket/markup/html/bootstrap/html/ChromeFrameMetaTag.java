package de.agilecoders.wicket.markup.html.bootstrap.html;

import org.apache.wicket.model.Model;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class ChromeFrameMetaTag extends MetaTag {
    public ChromeFrameMetaTag(String id) {
        super(id, Model.of("X-UA-Compatible"), Model.of("IE=edge,chrome=1"));
    }
}