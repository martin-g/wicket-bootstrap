package de.agilecoders.wicket.core.settings;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.jquery.util.Generics2;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.ResourceReference;

import java.util.Collections;
import java.util.List;

/**
 * #### Description
 *
 * Special theme that uses {@link de.agilecoders.wicket.core.settings.IBootstrapSettings#getCssResourceReference()}
 *
 * @author Michael Haitz <michael.haitz@agilecoders.de>
 */
public class NoopThemeProvider implements ThemeProvider {

    private final ITheme theme = new NoopTheme();

    @Override
    public ITheme byName(String name) {
        return theme;
    }

    @Override
    public List<ITheme> available() {
        return Generics2.newArrayList(theme);
    }

    @Override
    public ITheme defaultTheme() {
        return theme;
    }

    private static final class NoopTheme implements ITheme {

        @Override
        public String name() {
            return "bootstrap";
        }

        @Override
        public List<ResourceReference> getResourceReferences() {
            return Collections.emptyList();
        }

        @Override
        public void renderHead(IHeaderResponse response) {
            response.render(CssHeaderItem.forReference(Bootstrap.getSettings().getCssResourceReference()));
        }

        @Override
        public Iterable<String> getCdnUrls() {
            return Collections.emptyList();
        }
    }
}
