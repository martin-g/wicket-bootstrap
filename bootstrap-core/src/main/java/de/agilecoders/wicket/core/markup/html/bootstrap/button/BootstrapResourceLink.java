package de.agilecoders.wicket.core.markup.html.bootstrap.button;

import org.apache.wicket.IResourceListener;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.resource.ResourceReferenceRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.IResource.Attributes;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;

/**
 * 
 * Same as default {@link ResourceLink} but based on {@link BootstrapLink} which is styled by bootstrap. 
 * 
 * @author ymlaine
 *
 */
public class BootstrapResourceLink extends BootstrapLink<Void> implements IResourceListener {

	private static final long serialVersionUID = 1L;

	/** The Resource reference */
	private final ResourceReference resourceReference;

	/** The Resource */
	private final IResource resource;

	/** The resource parameters */
	private final PageParameters resourceParameters;

	/**
	 * Constructs an ResourceLink from an resourcereference. That resource reference will bind its
	 * resource to the current SharedResources.
	 * 
	 * @param id
	 *            See Component
	 * @param resourceReference
	 *            The shared resource to link to
	 */
	public BootstrapResourceLink(final String id, final ResourceReference resourceReference, Type type)
	{
		this(id, resourceReference, null, type);
	}

	/**
	 * Constructs an ResourceLink from an resourcereference. That resource reference will bind its
	 * resource to the current SharedResources.
	 * 
	 * @param id
	 *            See Component
	 * @param resourceReference
	 *            The shared resource to link to
	 * @param resourceParameters
	 *            The resource parameters
	 */
	public BootstrapResourceLink(final String id, final ResourceReference resourceReference,
		PageParameters resourceParameters, Type type)
	{
		super(id, type);
		this.resourceReference = resourceReference;
		this.resourceParameters = resourceParameters;
		resource = null;
	}

	/**
	 * Constructs a link directly to the provided resource.
	 * 
	 * @param id
	 *            See Component
	 * @param resource
	 *            The resource
	 */
	public BootstrapResourceLink(final String id, final IResource resource, Type type)
	{
		super(id, type);
		this.resource = resource;
		resourceReference = null;
		resourceParameters = null;
	}

	/**
	 * @see org.apache.wicket.markup.html.link.Link#onClick()
	 */
	@Override
	public void onClick()
	{
	}

	/**
	 * @see org.apache.wicket.IResourceListener#onResourceRequested()
	 */
	@Override
	public final void onResourceRequested()
	{

		Attributes a = new Attributes(RequestCycle.get().getRequest(), RequestCycle.get()
			.getResponse(), null);
		resource.respond(a);
		onLinkClicked();
	}

	/**
	 * @see org.apache.wicket.markup.html.link.Link#getURL()
	 */
	@Override
	protected final CharSequence getURL()
	{
		if (resourceReference != null)
		{
			// TODO post 1.2: should we have support for locale changes when the
			// resource reference (or resource??) is set manually..
			// We should get a new resource reference for the current locale
			// then
			// that points to the same resource but with another locale if it
			// exists.
			// something like
			// SharedResource.getResourceReferenceForLocale(resourceReference);
			if (resourceReference.canBeRegistered())
			{
				getApplication().getResourceReferenceRegistry().registerResourceReference(
					resourceReference);
			}

			return getRequestCycle().urlFor(
				new ResourceReferenceRequestHandler(resourceReference, resourceParameters));
		}
		return urlFor(IResourceListener.INTERFACE, resourceParameters);
	}

}
