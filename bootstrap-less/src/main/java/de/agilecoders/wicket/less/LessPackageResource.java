package de.agilecoders.wicket.less;

import org.apache.wicket.request.resource.CssPackageResource;
import org.apache.wicket.util.resource.IResourceStream;

import java.util.Locale;

/**
 * A package resource that uses a custom IResourceStream to
 * load Less content but return CSS content generated out of it.
 */
public class LessPackageResource extends CssPackageResource {

    /**
     * Constructor.
     *
     * @param scope     This argument will be used to get the class loader for loading the package
     *                  resource, and to determine what package it is in
     * @param name      The relative path to the resource
     * @param locale    The locale of the resource
     * @param style     The style of the resource
     * @param variation The variation of the resource
     */
    public LessPackageResource(Class<?> scope, String name, Locale locale, String style, String variation) {
        super(scope, name, locale, style, variation);
    }

    @Override
    public IResourceStream getCacheableResourceStream() {
        IResourceStream resourceStream = super.getCacheableResourceStream();
        return new LessResourceStream(resourceStream, getScope().getName());
    }

}
