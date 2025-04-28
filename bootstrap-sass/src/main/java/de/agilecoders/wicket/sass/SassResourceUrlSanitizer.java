package de.agilecoders.wicket.sass;

import org.apache.wicket.request.resource.IResourceUrlSanitizer;
import org.apache.wicket.request.resource.ResourceReference;

public class SassResourceUrlSanitizer implements IResourceUrlSanitizer
{

    private IResourceUrlSanitizer delegate;

    public SassResourceUrlSanitizer(IResourceUrlSanitizer delegate)
    {
        this.delegate = delegate;
    }

    @Override
    public ResourceReference.UrlAttributes sanitize(ResourceReference.UrlAttributes urlAttributes,
        Class<?> scope, String name)
    {
        if (ContextRelativeSassResourceReference.CONTEXT_RELATIVE_SASS_REFERENCE_VARIATION.equals(
            urlAttributes.getVariation()))
        {
            return urlAttributes;
        }
        else
        {
            return delegate.sanitize(urlAttributes, scope, name);
        }
    }
}
