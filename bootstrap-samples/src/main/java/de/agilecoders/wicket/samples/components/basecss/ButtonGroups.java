package de.agilecoders.wicket.samples.components.basecss;

import java.util.ArrayList;
import java.util.List;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeSettings;
import org.apache.wicket.Application;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Toolbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.samples.components.base.Section;
import de.agilecoders.wicket.samples.pages.ComponentsPage;

/**
 * Panel that shows the usage of button groups.
 *
 * @author miha
 */
public class ButtonGroups extends Section<Void> {
    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     *
     * @param markupId The wicket markup id.
     */
    public ButtonGroups(final String markupId) {
        super(markupId);

        ButtonGroup buttonGroup = new ButtonGroup("buttonGroup") {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<AbstractLink> newButtons(String buttonMarkupId) {
                return List.of(createButton("Left"), createButton("Center"), createButton("Right"));
            }
        };
        add(buttonGroup);

        Toolbar toolbar = new Toolbar("toolbar");
        toolbar.add(newButtonGroup("buttonGroup1", 3));
        toolbar.add(newButtonGroup("buttonGroup2", 5));
        toolbar.add(newButtonGroup("buttonGroup3", 1));
        add(toolbar);

        ButtonGroup verticalButtonGroup = new ButtonGroup("verticalButtonGroup", Buttons.Orientation.Vertical) {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<AbstractLink> newButtons(String buttonMarkupId) {
                FontAwesomeSettings fas = FontAwesomeSettings.get(Application.get());
                return List.of(
                        createIconButton(new Icon(fas.getIconType(FontAwesomeSettings.IconKey.ALIGN_LEFT))),
                        createIconButton(new Icon(fas.getIconType(FontAwesomeSettings.IconKey.ALIGN_CENTER))),
                        createIconButton(new Icon(fas.getIconType(FontAwesomeSettings.IconKey.ALIGN_RIGHT))),
                        createIconButton(new Icon(fas.getIconType(FontAwesomeSettings.IconKey.ALIGN_JUSTIFY))));
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
        BootstrapBookmarkablePageLink<Void> button = new BootstrapBookmarkablePageLink<>(ButtonList.getButtonMarkupId(), ComponentsPage.class, Buttons.Type.Secondary);
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
            private static final long serialVersionUID = 1L;

            @Override
            protected List<AbstractLink> newButtons(String buttonMarkupId) {
                final List<AbstractLink> buttons = new ArrayList<>();

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
        AbstractLink button = new BookmarkablePageLink<Void>(ButtonList.getButtonMarkupId(), ComponentsPage.class).setBody(Model.of(label));
        button.add(new ButtonBehavior());

        return button;
    }
}
