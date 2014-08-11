package de.agilecoders.wicket.samples.components.basecss;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.ButtonList;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Toolbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.jquery.util.Generics2;
import de.agilecoders.wicket.samples.components.base.Section;
import de.agilecoders.wicket.samples.pages.ComponentsPage;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Panel that shows the usage of button groups.
 *
 * @author miha
 */
public class ButtonGroups extends Section<Void> {

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
                        createIconButton(new Icon(GlyphIconType.alignleft)),
                        createIconButton(new Icon(GlyphIconType.aligncenter)),
                        createIconButton(new Icon(GlyphIconType.alignright)),
                        createIconButton(new Icon(GlyphIconType.alignjustify)));
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
        BootstrapBookmarkablePageLink<Void> button = new BootstrapBookmarkablePageLink<>(ButtonList.getButtonMarkupId(), ComponentsPage.class, Buttons.Type.Default);
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
