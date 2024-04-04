package de.agilecoders.wicket.core.settings;

import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;

import de.agilecoders.wicket.core.Bootstrap;

/**
 * #### Description
 *
 * Special theme that uses {@link de.agilecoders.wicket.core.settings.IBootstrapSettings#getCssResourceReference()}
 */
public class NoopThemeProvider implements ThemeProvider {

    private final ITheme theme = new NoopTheme();

    @Override
    public ITheme byName(String name) {
        return theme;
    }

    @Override
    public List<ITheme> available() {
        return List.of(theme);
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
        public List<HeaderItem> getDependencies() {
            return List.of();
        }

        @Override
        public void renderHead(IHeaderResponse response) {
            response.render(CssHeaderItem.forReference(Bootstrap.getSettings().getCssResourceReference()));
        }

        @Override
        public Iterable<String> getCdnUrls() {
            return List.of();
        }
    }
}
