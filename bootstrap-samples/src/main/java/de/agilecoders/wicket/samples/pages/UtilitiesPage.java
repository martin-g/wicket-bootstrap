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

import com.google.common.collect.Lists;

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
        List<BorderBehavior.Type> additiveTypes = Lists.newArrayList(
                BorderBehavior.Type.All,
                BorderBehavior.Type.Top,
                BorderBehavior.Type.Right,
                BorderBehavior.Type.Bottom,
                BorderBehavior.Type.Left
        );
        add(new ListView<BorderBehavior.Type>("additive-border-types", additiveTypes) {

            @Override
            protected void populateItem(ListItem<BorderBehavior.Type> item) {
                BorderBehavior.Type type = item.getModelObject();
                Label label = new Label("border-type", "");
                label.add(new BorderBehavior().type(type).color(BorderBehavior.Color.Dark));
                item.add(label);
            }
        });

        List<BorderBehavior.Type> subtractiveTypes = Lists.newArrayList(
                BorderBehavior.Type.None,
                BorderBehavior.Type.ExceptTop,
                BorderBehavior.Type.ExceptRight,
                BorderBehavior.Type.ExceptBottom,
                BorderBehavior.Type.ExceptLeft
        );
        add(new ListView<BorderBehavior.Type>("subtractive-border-types", subtractiveTypes) {

            @Override
            protected void populateItem(ListItem<BorderBehavior.Type> item) {
                BorderBehavior.Type type = item.getModelObject();
                Label label = new Label("border-type", "");
                label.add(new BorderBehavior().type(type).color(BorderBehavior.Color.Dark));
                item.add(label);
            }
        });

        add(new Code("border-type-code", Model.of(
                "List<BorderBehavior.Type> additiveTypes = Lists.newArrayList(\n" +
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
        List<BorderBehavior.Color> colors = Lists.newArrayList(BorderBehavior.Color.values());
        add(new ListView<BorderBehavior.Color>("border-colors", colors) {

            @Override
            protected void populateItem(ListItem<BorderBehavior.Color> item) {
                BorderBehavior.Color color = item.getModelObject();
                Label label = new Label("border-color", "");
                label.add(new BorderBehavior().color(color).type(BorderBehavior.Type.All));
                item.add(label);
            }
        });

        add(new Code("border-colors-code", Model.of(
            "List<BorderBehavior.Color> colors = Lists.newArrayList(BorderBehavior.Color.values());\n" +
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
        List<BorderBehavior.Radius> radiuses = Lists.newArrayList(BorderBehavior.Radius.values());
        add(new ListView<BorderBehavior.Radius>("border-radiuses", radiuses) {
            @Override
            protected void populateItem(ListItem<BorderBehavior.Radius> item) {
                BorderBehavior.Radius radius = item.getModelObject();
                ExternalImage img = new ExternalImage("border-radius", "http://placehold.it/260x180");
                img.add(new BorderBehavior().radius(radius));
                item.add(img);
            }
        });

        add(new Code("border-radius-code", Model.of(
            "List<BorderBehavior.Radius> radiuses = Lists.newArrayList(BorderBehavior.Radius.values());\n" +
            "add(new ListView<BorderBehavior.Radius>(\"border-radiuses\", radiuses) {\n" +
            "    @Override\n" +
            "    protected void populateItem(ListItem<BorderBehavior.Radius> item) {\n" +
            "        BorderBehavior.Radius radius = item.getModelObject();\n" +
            "        ExternalImage img = new ExternalImage(\"border-radius\", \"http://placehold.it/260x180\");\n" +
            "        img.add(new BorderBehavior().radius(radius));\n" +
            "        item.add(img);\n" +
            "    }\n" +
            "});"
        )).setLanguage(CodeBehavior.Language.JAVA));
    }

    private void addColors() {
        List<ColorBehavior.Color> colors = Lists.newArrayList(ColorBehavior.Color.values());
        add(new ListView<ColorBehavior.Color>("text-colors", colors) {
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

        add(new ListView<ColorBehavior.Color>("link-colors", colors) {
            @Override
            protected void populateItem(ListItem<ColorBehavior.Color> item) {
                ColorBehavior.Color color = item.getModelObject();
                AbstractLink link = new Link<Void>("color") {
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
        List<BackgroundColorBehavior.Color> colors = Lists.newArrayList(BackgroundColorBehavior.Color.values());

        add(new ListView<BackgroundColorBehavior.Color>("background-colors", colors) {
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
