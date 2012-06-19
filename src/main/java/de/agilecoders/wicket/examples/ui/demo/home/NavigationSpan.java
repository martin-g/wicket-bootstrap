package de.agilecoders.wicket.examples.ui.demo.home;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonType;
import de.agilecoders.wicket.markup.html.bootstrap.button.TypedPageButton;
import de.agilecoders.wicket.markup.html.bootstrap.layout.Span;
import de.agilecoders.wicket.examples.pages.ButtonsPage;
import de.agilecoders.wicket.examples.pages.HeadingPage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;

import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public class NavigationSpan extends Span {

    public NavigationSpan(String id) {
        super();

        add(new ListView<TypedPageButton>("links", newButtonList()) {
            @Override
            protected void populateItem(ListItem<TypedPageButton> components) {
                components.add(components.getModelObject());
            }
        });
    }

    private List<? extends TypedPageButton> newButtonList() {
        return Lists.newArrayList(
                createButton(ButtonsPage.class, "Buttons"),
                createButton(HeadingPage.class, "Heading")
        );
    }

    private TypedPageButton createButton(Class<? extends Page> pageClass, String title) {
        TypedPageButton<? extends Page> typedPageButton = new TypedPageButton<Page>("link", pageClass, ButtonType.Default);
        typedPageButton.setBody(Model.of(title));

        return typedPageButton;
    }
}
