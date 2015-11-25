package de.agilecoders.wicket.core.markup.html.bootstrap.html;

import de.agilecoders.wicket.core.util.Components;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.util.string.Strings;

/**
 * A special {@link MetaTag} that adds viewport related settings
 * like scale and width
 *
 * @author David Beer
 */
public class MobileViewportMetaTag extends WebComponent {

    private String width;
    private String height;
    private String initialScale;
    private String minimumScale;
    private String maximumScale;
    private boolean userScalable = true;

    /**
     * Default Constructor
     *
     * @param id the markup id
     */
    public MobileViewportMetaTag(final String id) {
        super(id);
    }

    public String getWidth() {
        return width;
    }

    public MobileViewportMetaTag setWidth(String width) {
        this.width = width;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public MobileViewportMetaTag setHeight(String height) {
        this.height = height;
        return this;
    }

    public String getInitialScale() {
        return initialScale;
    }

    public MobileViewportMetaTag setInitialScale(String value) {
        this.initialScale = value;
        return this;
    }

    public String getMinimumScale() {
        return minimumScale;
    }

    public MobileViewportMetaTag setMinimumScale(String minimumScale) {
        this.minimumScale = minimumScale;
        return this;
    }

    public String getMaximumScale() {
        return maximumScale;
    }

    public MobileViewportMetaTag setMaximumScale(String maximumScale) {
        this.maximumScale = maximumScale;
        return this;
    }

    public boolean isUserScalable() {
        return userScalable;
    }

    public MobileViewportMetaTag setUserScalable(boolean userScalable) {
        this.userScalable = userScalable;
        return this;
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        Components.assertTag(this, tag, "meta");

        tag.put("name", "viewport");

        StringBuilder content = new StringBuilder();

        String _width = getWidth();
        if (!Strings.isEmpty(_width)) {
            content.append("width=").append(_width);
        }

        String _height = getHeight();
        if (!Strings.isEmpty(_height)) {
            ensureComma(content);
            content.append("height=").append(_height);
        }

        String _initialScale = getInitialScale();
        if (!Strings.isEmpty(_initialScale)) {
            ensureComma(content);
            content.append("initial-scale=").append(_initialScale);
        }

        String _minimumScale = getMinimumScale();
        if (!Strings.isEmpty(_minimumScale)) {
            ensureComma(content);
            content.append("minimum-scale=").append(_minimumScale);
        }

        String _maximumScale = getMaximumScale();
        if (!Strings.isEmpty(_maximumScale)) {
            ensureComma(content);
            content.append("maximum-scale=").append(_maximumScale);
        }

        if (!isUserScalable()) {
            ensureComma(content);
            content.append("user-scalable=no");
        }

        tag.put("content", content);
    }

    private void ensureComma(StringBuilder content) {
        if (content.length() > 0) {
            content.append(',');
        }
    }


}
