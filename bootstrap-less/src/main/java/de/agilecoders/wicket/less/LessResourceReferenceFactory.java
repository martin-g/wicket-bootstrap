package de.agilecoders.wicket.less;

import org.apache.wicket.request.resource.IResourceReferenceFactory;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.lang.Args;

/**
 * A factory that creates a new instance of {@link de.agilecoders.wicket.less.LessResourceReference}
 * when there is no registered one in Wicket's {@link org.apache.wicket.request.resource.ResourceReferenceRegistry}
 * for a resource with extension <em>.less</em>
 */
public class LessResourceReferenceFactory implements IResourceReferenceFactory {

    /**
     * A factory to delegate the creation of the ResourceReference if the key's name
     * doesn't have extension <em>.less</em>
     */
    private final IResourceReferenceFactory delegate;

    /**
     * Constructor.
     *
     * @param delegate A factory to delegate the creation of the ResourceReference if the key's name
     *                 doesn't have extension <em>.less</em>
     */
    public LessResourceReferenceFactory(IResourceReferenceFactory delegate) {
        this.delegate = Args.notNull(delegate, "delegate");
    }

    @Override
    public ResourceReference create(ResourceReference.Key key) {
        String name = key.getName();
        String variation = key.getVariation();
        if (ContextRelativeLessResourceReference.CONTEXT_RELATIVE_LESS_REFERENCE_VARIATION.equals(variation)) {
            return new ContextRelativeLessResourceReference(name); // TODO what about the min extension ?!
        }
        if (name != null && name.endsWith(".less")) {
            return new LessResourceReference(key);
        } else {
            return delegate.create(key);
        }
    }
}
