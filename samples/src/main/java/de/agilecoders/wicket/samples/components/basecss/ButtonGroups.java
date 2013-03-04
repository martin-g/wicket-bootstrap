package de.agilecoders.wicket.samples.components.basecss;

import de.agilecoders.wicket.markup.html.bootstrap.button.BootstrapBookmarkablePageLink;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonGroup;
import de.agilecoders.wicket.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.markup.html.bootstrap.button.Toolbar;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.samples.pages.ComponentsPage;
import de.agilecoders.wicket.util.Generics2;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Panel that shows the usage of button groups.
 *
 * @author miha
 * @version 1.0
 */
public class ButtonGroups extends Panel {

    /**
     * Construct.
     *
     * @param markupId The wicket markup id.
     */
    public ButtonGroups(final String markupId) {
        super(markupId);

        ButtonGroup buttonGroup = new ButtonGroup("buttonGroup") {
            @Override
            protected List<AbstractLink> newButtons(String buttonMarkupId) {
                return Generics2.newArrayList(createButton("Left"), createButton("Center"), createButton("Right"));
            }
        };
        add(buttonGroup);

        Toolbar toolbar = new Toolbar("toolbar");
        toolbar.add(newButtonGroup("buttonGroup1", 3));
        toolbar.add(newButtonGroup("buttonGroup2", 5));
        toolbar.add(newButtonGroup("buttonGroup3", 1));
        add(toolbar);

        ButtonGroup verticalButtonGroup = new ButtonGroup("verticalButtonGroup", Buttons.Orientation.Vertical) {
            @Override
            protected List<AbstractLink> newButtons(String buttonMarkupId) {
                return Generics2.newArrayList(
                        createIconButton(new Icon(IconType.alignleft)),
                        createIconButton(new Icon(IconType.aligncenter)),
                        createIconButton(new Icon(IconType.alignright)),
                        createIconButton(new Icon(IconType.alignjustify)));
            }
        };
        add(verticalButtonGroup);
    }

    /**
     * creates a new button with given icon.
     *
     * @param icon The button icon
     * @return The new button instance
     */
    private AbstractLink createIconButton(Icon icon) {
        BootstrapBookmarkablePageLink button = new BootstrapBookmarkablePageLink<ComponentsPage>(ButtonList.getButtonMarkupId(), ComponentsPage.class, Buttons.Type.Default);
        button.setLabel(Model.of(""));
        button.setIconType(icon.getType());
        return button;
    }

    /**
     * creates a new button group with given number of buttons.
     *
     * @param markupId The markup id.
     * @param noOfButtons  The number of buttons
     * @return new button group
     */
    private ButtonGroup newButtonGroup(final String markupId, final int noOfButtons) {
        return new ButtonGroup(markupId) {
            @Override
            protected List<AbstractLink> newButtons(String buttonMarkupId) {
                final List<AbstractLink> buttons = new ArrayList<AbstractLink>();

                for (int i = 0; i < noOfButtons; ++i) {
                    buttons.add(createButton(String.valueOf(i + 1)));
                }

                return buttons;
            }
        };
    }

    /**
     * creates a new button with given label.
     *
     * @param label The button label
     * @return The new button instance
     */
    private AbstractLink createButton(final String label) {
        AbstractLink button = new BookmarkablePageLink<ComponentsPage>(ButtonList.getButtonMarkupId(), ComponentsPage.class).setBody(Model.of(label));
        button.add(new ButtonBehavior());

        return button;
    }
}
