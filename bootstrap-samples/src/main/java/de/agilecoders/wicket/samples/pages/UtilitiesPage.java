package de.agilecoders.wicket.samples.pages;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ExternalImage;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.CodeBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BorderBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.ColorBehavior;

/**
 * @author Jan Ferko
 */
@MountPath("/utilities")
public class UtilitiesPage extends BasePage {
    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     *
     * @param parameters current page parameters
     */
    public UtilitiesPage(PageParameters parameters) {
        super(parameters);

        addBorderTypes();
        addBorderColors();
        addBorderRadius();
        addColors();
        addBackgroundColors();
    }

    private void addBorderTypes() {
        List<BorderBehavior.Type> additiveTypes = List.of(
                BorderBehavior.Type.All,
                BorderBehavior.Type.Top,
                BorderBehavior.Type.Right,
                BorderBehavior.Type.Bottom,
                BorderBehavior.Type.Left
        );
        add(new ListView<>("additive-border-types", additiveTypes) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<BorderBehavior.Type> item) {
                BorderBehavior.Type type = item.getModelObject();
                Label label = new Label("border-type", "");
                label.add(new BorderBehavior().type(type).color(BorderBehavior.Color.Dark));
                item.add(label);
            }
        });

        List<BorderBehavior.Type> subtractiveTypes = List.of(
                BorderBehavior.Type.None,
                BorderBehavior.Type.ExceptTop,
                BorderBehavior.Type.ExceptRight,
                BorderBehavior.Type.ExceptBottom,
                BorderBehavior.Type.ExceptLeft
        );
        add(new ListView<>("subtractive-border-types", subtractiveTypes) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<BorderBehavior.Type> item) {
                BorderBehavior.Type type = item.getModelObject();
                Label label = new Label("border-type", "");
                label.add(new BorderBehavior().type(type).color(BorderBehavior.Color.Dark));
                item.add(label);
            }
        });

        add(new Code("border-type-code", Model.of(
                "List<BorderBehavior.Type> additiveTypes = List.of(\n" +
                "    BorderBehavior.Type.All,\n" +
                "    BorderBehavior.Type.Top,\n" +
                "    BorderBehavior.Type.Right,\n" +
                "    BorderBehavior.Type.Bottom,\n" +
                "    BorderBehavior.Type.Left\n" +
                ");\n" +
                "add(new ListView<BorderBehavior.Type>(\"additive-border-types\", additiveTypes) {\n\n" +
                "    @Override\n" +
                "    protected void populateItem(ListItem<BorderBehavior.Type> item) {\n" +
                "       BorderBehavior.Type type = item.getModelObject();\n" +
                "       Label label = new Label(\"border-type\", \"\");\n" +
                "       label.add(new BorderBehavior().type(type).color(BorderBehavior.Color.Dark));\n" +
                "       item.add(label);\n" +
                "   }\n" +
                "});"
        )).setLanguage(CodeBehavior.Language.JAVA));
    }

    private void addBorderColors() {
        List<BorderBehavior.Color> colors = List.of(BorderBehavior.Color.values());
        add(new ListView<>("border-colors", colors) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<BorderBehavior.Color> item) {
                BorderBehavior.Color color = item.getModelObject();
                Label label = new Label("border-color", "");
                label.add(new BorderBehavior().color(color).type(BorderBehavior.Type.All));
                item.add(label);
            }
        });

        add(new Code("border-colors-code", Model.of(
            "List<BorderBehavior.Color> colors = List.of(BorderBehavior.Color.values());\n" +
            "add(new ListView<BorderBehavior.Color>(\"border-colors\", colors) {\n\n" +
            "    @Override\n" +
            "    protected void populateItem(ListItem<BorderBehavior.Color> item) {\n" +
            "        BorderBehavior.Color color = item.getModelObject();\n" +
            "        Label label = new Label(\"border-color\", \"\");\n" +
            "        label.add(new BorderBehavior().color(color).type(BorderBehavior.Type.All));\n" +
            "        item.add(label);\n" +
            "    }\n" +
            "});"
        )).setLanguage(CodeBehavior.Language.JAVA));
    }

    private void addBorderRadius() {
        List<BorderBehavior.Radius> radiuses = List.of(BorderBehavior.Radius.values());
        add(new ListView<>("border-radiuses", radiuses) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<BorderBehavior.Radius> item) {
                BorderBehavior.Radius radius = item.getModelObject();
                ExternalImage img = new ExternalImage("border-radius", "https://via.placeholder.com/260x180");
                img.add(new BorderBehavior().radius(radius));
                item.add(img);
            }
        });

        add(new Code("border-radius-code", Model.of(
            "List<BorderBehavior.Radius> radiuses = List.of(BorderBehavior.Radius.values());\n" +
            "add(new ListView<BorderBehavior.Radius>(\"border-radiuses\", radiuses) {\n" +
            "    @Override\n" +
            "    protected void populateItem(ListItem<BorderBehavior.Radius> item) {\n" +
            "        BorderBehavior.Radius radius = item.getModelObject();\n" +
            "        ExternalImage img = new ExternalImage(\"border-radius\", \"https://via.placeholder.com/260x180\");\n" +
            "        img.add(new BorderBehavior().radius(radius));\n" +
            "        item.add(img);\n" +
            "    }\n" +
            "});"
        )).setLanguage(CodeBehavior.Language.JAVA));
    }

    private void addColors() {
        List<ColorBehavior.Color> colors = List.of(ColorBehavior.Color.values());
        add(new ListView<>("text-colors", colors) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<ColorBehavior.Color> item) {
                ColorBehavior.Color color = item.getModelObject();
                Label label = new Label("color", Model.of(color.cssClassName()));
                label.add(new ColorBehavior(color));

                switch (color) {
                    case White:
                    case Light:
                    case White50:
                        label.add(BackgroundColorBehavior.dark());
                        break;
                    default:
                        break;
                }

                item.add(label);
            }
        });

        add(new Code("text-colors-code", Model.of(
                "add(new ListView<ColorBehavior.Color>(\"text-colors\", colors) {\n\n" +
                "    protected void populateItem(ListItem<ColorBehavior.Color> item) {\n" +
                "         ColorBehavior.Color color = item.getModelObject();\n" +
                "         Label label = new Label(\"color\", Model.of(color.cssClassName()));\n" +
                "         label.add(new ColorBehavior(color));\n\n" +
                "         switch (color) {\n" +
                "             case White:\n" +
                "             case Light:\n" +
                "             case White50:\n" +
                "                 label.add(BackgroundColorBehavior.dark());\n" +
                "                 break;\n" +
                "             default:\n" +
                "                 break;\n" +
                "         }\n\n" +
                "         item.add(label);\n" +
                "    }\n" +
                "});"
        )).setLanguage(CodeBehavior.Language.JAVA));

        add(new ListView<>("link-colors", colors) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<ColorBehavior.Color> item) {
                ColorBehavior.Color color = item.getModelObject();
                AbstractLink link = new Link<Void>("color") {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick() {
                    }
                }.setBody(Model.of(color.cssClassName()));

                switch (color) {
                    case White:
                    case Light:
                    case White50:
                        item.add(BackgroundColorBehavior.dark());
                        break;
                    default:
                        break;
                }

                link.add(new ColorBehavior(color));
                item.add(link);
            }
        });
    }

    private void addBackgroundColors() {
        List<BackgroundColorBehavior.Color> colors = List.of(BackgroundColorBehavior.Color.values());

        add(new ListView<>("background-colors", colors) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<BackgroundColorBehavior.Color> item) {
                BackgroundColorBehavior.Color color = item.getModelObject();
                Label label = new Label("color", Model.of(color.cssClassName()));

                switch (color) {
                    case Light:
                    case Transparent:
                    case Warning:
                    case White:
                        label.add(ColorBehavior.dark());
                        break;
                    default:
                        label.add(ColorBehavior.white());
                        break;
                }

                label.add(new BackgroundColorBehavior(color));
                item.add(label);
            }
        });

        add(new Code("background-colors-code", Model.of(
                "add(new ListView<BackgroundColorBehavior.Color>(\"background-colors\", colors) {\n\n" +
                "    protected void populateItem(ListItem<BackgroundColorBehavior.Color> item) {\n" +
                "        BackgroundColorBehavior.Color color = item.getModelObject();\n" +
                "        Label label = new Label(\"color\", Model.of(color.cssClassName()));\n\n" +
                "        switch (color) {\n" +
                "            case Transparent:\n" +
                "            case White:\n" +
                "            case Light:\n" +
                "                label.add(ColorBehavior.dark());" +
                "                break;\n" +
                "            default:\n" +
                "                label.add(ColorBehavior.white());\n" +
                "                break;\n" +
                "        }\n\n" +
                "        label.add(new BackgroundColorBehavior(color));\n" +
                "        item.add(label);\n" +
                "    }\n" +
                "});\n"
                )).setLanguage(CodeBehavior.Language.JAVA));
    }
}
