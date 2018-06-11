package de.agilecoders.wicket.sass;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.ResourceReferenceRegistry;

/**
 * Application object for your web application.
 */
public class TestApplication extends WebApplication
{
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	@Override
	public void init()
	{
		super.init();
		BootstrapSass.install(this);
	}

	@Override
	protected ResourceReferenceRegistry newResourceReferenceRegistry() {
		return new ResourceReferenceRegistry(new SassRRR());
	}

	private static class SassRRR extends ResourceReferenceRegistry.DefaultResourceReferenceFactory {

		@Override
		public ResourceReference create(ResourceReference.Key key) {
			if (key.getName().endsWith(".scss") || key.getName().endsWith(".sass")) {
				return new SassResourceReference(key);
			} else {
				return super.create(key);
			}
		}
	}
}
