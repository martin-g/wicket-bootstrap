package de.agilecoders.wicket.extensions.request;

import org.apache.wicket.core.request.mapper.ResourceReferenceMapper;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.IRequestMapper;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.handler.resource.ResourceReferenceRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParametersEncoder;
import org.apache.wicket.request.resource.caching.IResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.IStaticCacheableResource;
import org.apache.wicket.util.IProvider;
import org.apache.wicket.util.string.Strings;

/**
 * Enables a Wicket application to have its static resources proxied by a CDN, for example
 * by Amazon Cloudfront. This works by intercepting Wicket's default behavior for rendering
 * URLs of resource references, and then rewriting those URLs by prepending a CDN hostname
 * (or any arbitrary URL fragment). The web browser will therefore make requests to the
 * CDN host instead of the Wicket app.
 * <p/>
 * Here's an example. Normally a CSS resource reference is rendered by Wicket like this:
 * <pre class="example">
 * /wicket/resource/com.mycompany.WicketApplication/test.css</pre>
 * <p/>
 * With {@code StaticResourceRewriteMapper} installed, that resource reference URL is transformed into this:
 * <pre class="example">
 * http://age39p8hg23.cloudfront.net/wicket/resource/com.mycompany.WicketApplication/test.css</pre>
 * <p/>
 * <b>Please note: {@code StaticResourceRewriteMapper} will not rewrite resource reference URLs that
 * include query string parameters.</b> Our reasoning is that parameterized URLs usually
 * indicate that the resource is dynamic, and therefore not appropriate for serving via
 * CDN. Furthermore it should be noted that Amazon CloudFront will refuse to proxy
 * URLs that contain query string parameters (it strips the parameters off).
 * <p/>
 * When configuring the CDN host, the easiest setup is a reverse-proxy. For example, with
 * Amazon CloudFront, you would specify your Wicket app as the <em>custom origin</em>, and specify
 * the CloudFront host when constructing this StaticResourceRewriteMapper. It's that easy.
 * <pre class="example">
 * public class MyApplication extends WebApplication
 * {
 * &#064;Override
 * protected void init() {
 * super.init();
 * // Enable CDN when in deployment mode
 * if(usesDeploymentConfig()) {
 * StaticResourceRewriteMapper.withBaseUrl("//age39p8hg23.cloudfront.net").install(this);
 * }
 * }
 * }</pre>
 * <p/>
 * Notice in this example that we've used "//" instead of "http://" for the CDN URL.
 * This trick ensures that "http" or "https" will be automatically selected by the
 * browser based on the enclosing web page.
 * <p/>
 * <em>For those familiar with Ruby on Rails, {@code StaticResourceRewriteMapper} is inspired by the Rails
 * {@code action_controller.asset_host} configuration setting.</em>
 * <em>This class is a fork of https://github.com/55minutes/fiftyfive-wicket/blob/v3.2/fiftyfive-wicket-core/src/main/java/fiftyfive/wicket/resource/SimpleCDN.java</em>
 */
public class StaticResourceRewriteMapper implements IRequestMapper {

    /**
     * creates a new {@link Builder} with given base url
     *
     * @param baseUrl the base url
     * @return new builder instance
     */
    public static Builder withBaseUrl(final String baseUrl) {
        return new Builder(baseUrl);
    }

    private final String baseUrl;
    private final IRequestMapper chain;

    /**
     * Construct a {@code StaticResourceRewriteMapper} that will rewrite resource reference URLs
     * by prepending the given {@code baseUrl}.
     *
     * @param baseUrl  For example, "//age39p8hg23.cloudfront.net"
     * @param chain the base request mapper
     */
    private StaticResourceRewriteMapper(final String baseUrl, final IRequestMapper chain) {
        this.baseUrl = baseUrl;
        this.chain = chain;
    }

    /**
     * If the {@code requestHandler} is a {@link ResourceReferenceRequestHandler}, delegate to
     * Wicket's default mapper for creating an appropriate URL, and then prepend the
     * {@code baseUrl} that was provided to the {@code StaticResourceRewriteMapper} constructor.
     *
     * @return a rewritten Url to the resource, or {@code null} if {@code requestHandler} is
     *         not for a resource reference
     */
    @Override
    public Url mapHandler(final IRequestHandler requestHandler) {
        // StaticResourceRewriteMapper doesn't apply to non-resources
        if (requestHandler instanceof ResourceReferenceRequestHandler) {
            final ResourceReferenceRequestHandler resourceReferenceRequestHandler = (ResourceReferenceRequestHandler) requestHandler;

            // StaticResourceRewriteMapper doesn't apply to non-static resources
            if (resourceReferenceRequestHandler.getResource() instanceof IStaticCacheableResource) {
                final Url url = chain.mapHandler(requestHandler);

                if (url != null && url.getQueryParameters().isEmpty()) {
                    return Url.parse(Strings.join("/", baseUrl, url.toString()));
                } else {
                    return url;
                }
            }
        }

        return null;
    }

    /**
     * Always return {@code null}, since {@code StaticResourceRewriteMapper} does not play any part in handling requests
     * (they will be handled by Wicket's default mechanism).
     */
    @Override
    public IRequestHandler mapRequest(final Request request) {
        return null;
    }

    /**
     * Always return {@code 0}, since {@code StaticResourceRewriteMapper} does not play any part in handling requests
     * (they will be handled by Wicket's default mechanism).
     */
    @Override
    public int getCompatibilityScore(final Request request) {
        return 0;
    }

    /**
     * builder class for {@link StaticResourceRewriteMapper}
     */
    public static final class Builder {

        private final String url;

        /**
         * Construct.
         *
         * @param url base url
         */
        private Builder(String url) {
            this.url = url;
        }

        /**
         * Install this {@code StaticResourceRewriteMapper} into the given application. The
         * {@code StaticResourceRewriteMapper} instance will not have any effect unless it is installed.
         */
        public void install(final WebApplication app) {
            final IRequestMapper delegate = new ResourceReferenceMapper(new PageParametersEncoder(),
                                                                        newParentFolderPlaceholder(app),
                                                                        newResourceCachingStrategy(app));

            final StaticResourceRewriteMapper mapper = new StaticResourceRewriteMapper(url, delegate);
            app.mount(mapper);
        }

        /**
         * creates a new parent folder placeholder provider
         *
         * @param app The web application
         * @return new provider
         */
        private IProvider<String> newParentFolderPlaceholder(final WebApplication app) {
            return new IProvider<String>() {
                @Override
                public String get() {
                    return app.getResourceSettings().getParentFolderPlaceholder();
                }
            };
        }

        /**
         * creates a new caching strategy provider
         *
         * @param app The web application
         * @return new provider
         */
        private IProvider<IResourceCachingStrategy> newResourceCachingStrategy(final WebApplication app) {
            return new IProvider<IResourceCachingStrategy>() {
                @Override
                public IResourceCachingStrategy get() {
                    return app.getResourceSettings().getCachingStrategy();
                }
            };
        }
    }
}
