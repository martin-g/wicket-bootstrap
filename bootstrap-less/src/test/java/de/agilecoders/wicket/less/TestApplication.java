package de.agilecoders.wicket.less;

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
		BootstrapLess.install(this);
	}

	@Override
	protected ResourceReferenceRegistry newResourceReferenceRegistry() {
		return new ResourceReferenceRegistry(new LessRRR());
	}

	private static class LessRRR extends ResourceReferenceRegistry.DefaultResourceReferenceFactory {

		@Override
		public ResourceReference create(ResourceReference.Key key) {
			if (key.getName().endsWith(".less")) {
				return new LessResourceReference(key);
			} else {
				return super.create(key);
			}
		}
	}
}
