package de.agilecoders.wicket.samples.assets.base;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class DocsCssResourceReference extends CssResourceReference {

    public static DocsCssResourceReference GOOGLE = new DocsCssResourceReference("google-docs.css");

    private DocsCssResourceReference(String name) {
        super(DocsCssResourceReference.class, name);
    }
}
