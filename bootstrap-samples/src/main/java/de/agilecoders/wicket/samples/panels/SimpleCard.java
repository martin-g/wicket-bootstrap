package de.agilecoders.wicket.samples.panels;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.image.ExternalImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.panel.BootstrapGenericPanel;

/**
 * @author Jan Ferko
 */
public class SimpleCard extends BootstrapGenericPanel<String> {

    public SimpleCard(String id) {
        super(id, Model.of("Simple Card Model"), Model.of("Simple Card"));
    }

    @Override
    protected Panel newBodyPanel(String id, IModel<String> model) {
        return new Body(id, model);
    }

    @Override
    protected Panel newFooterPanel(String id, IModel<String> model) {
        return new Footer(id, model);
    }

    @Override
    protected Component newTopImage(String id, IModel<String> model) {
        return new ExternalImage(id, "https://via.placeholder.com/260x180");
    }

    private class Footer extends Panel {
        public Footer(String id, IModel<String> model) {
            super(id, model);
            add(new BootstrapButton("submit", Model.of("Submit"), Buttons.Type.Primary));
            add(new BootstrapButton("cancel", Model.of("Cancel"), Buttons.Type.Secondary));
        }
    }

    private class Body extends Panel {

        public Body(String id, IModel<String> model) {
            super(id, model);
            add(new Link<Void>("card-link") {
                @Override
                public void onClick() {
                }
            });
        }
    }
}
