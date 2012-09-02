package de.agilecoders.wicket.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.markup.html.bootstrap.behavior.CssClassNameAppender;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.Model;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class MenuDivider extends AbstractLink {

    /**
     * Construct.
     */
    public MenuDivider() {
        super(DropDownButton.getButtonMarkupId());

        setBody(Model.of("&nbsp;"));
        setEscapeModelStrings(false);
        setRenderBodyOnly(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        getParent().add(new CssClassNameAppender("divider"));
    }

}
