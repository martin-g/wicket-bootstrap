package de.agilecoders.wicket.samples.pages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ExternalImage;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.CodeBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.CodeBehavior.Language;
import de.agilecoders.wicket.core.markup.html.bootstrap.helpers.TextAndBackgroundColorBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.helpers.TextAndBackgroundColorBehavior.TextAndBackgroundColor;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundColorBehavior.BackgroundColor;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BackgroundOpacity;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.BorderBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.utilities.TextColorBehavior;

/**
 * @author Jan Ferko
 */
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
		addTextColors();
		addBackgroundColors();
		addBackgroundColorOpacity();
		addBackgroundColorGradients();
		addTextAndBackgroundColors();
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

	private void addTextColors() {
		List<TextColorBehavior.TextColor> colors = List.of(TextColorBehavior.TextColor.values());
		add(new ListView<>("text-colors", colors) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<TextColorBehavior.TextColor> item) {
				TextColorBehavior.TextColor color = item.getModelObject();
				Label label = new Label("color", Model.of(color.cssClassName()));
				label.add(new TextColorBehavior(color));

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
			protected void populateItem(ListItem<TextColorBehavior.TextColor> item) {
				TextColorBehavior.TextColor color = item.getModelObject();
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

				link.add(new TextColorBehavior(color));
				item.add(link);
			}
		});
	}

	private void addBackgroundColors() {
		final List<BackgroundColor> colors = List.of(BackgroundColor.values());

		add(new ListView<>("background-colors", colors) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<BackgroundColor> item) {
				final BackgroundColor color = item.getModelObject();
				final Label label = new Label("color", Model.of(color.cssClassName()));

				switch (color) {
					case Light, Light_Subtle:
					case Transparent:
					case Warning, Warning_Subtle:
					case Body, Body_Secondary, Body_Tertiary:
					case Primary_Subtle, Secondary_Subtle, Success_Subtle, Info_Subtle, Danger_Subtle, Dark_Subtle:
					case White:
						label.add(TextColorBehavior.dark());
						break;
					default:
						label.add(TextColorBehavior.white());
						break;
				}

				label.add(new BackgroundColorBehavior(color));
				item.add(label);
			}
		});

		final String code = """
			add(new ListView<>("background-colors", colors) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<BackgroundColor> item) {
					final BackgroundColor color = item.getModelObject();
					final Label label = new Label("color", Model.of(color.cssClassName()));

					switch (color) {
						case Light, Light_Subtle:
						case Transparent:
						case Warning, Warning_Subtle:
						case Body, Body_Secondary, Body_Tertiary:
						case Primary_Subtle, Secondary_Subtle, Success_Subtle, Info_Subtle, Danger_Subtle, Dark_Subtle:
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
			});""";

		add(new Code("background-colors-code", Model.of(code)).setLanguage(CodeBehavior.Language.JAVA));

		final String htmlCode = """
			<div class="border rounded mb-5 p-2">
				<wicket:container wicket:id="background-colors">
					<div wicket:id="color" class="mb-2 p-2"></div>
				</wicket:container>
			</div>""";
		add(new Code("htmlCodeBackground", Model.of(htmlCode)).setLanguage(Language.HTML));
	}

	private void addBackgroundColorOpacity() {

		//
		final List<BackgroundColor> backgroundColors = Arrays.stream(BackgroundColor.values())
			.filter(c -> !c.cssClassName().contains("subtle")) // ignore subtles because opacity makes no sense for them
			.toList();
		record BackgroundColorAndOpacity(BackgroundColor backgroundColor, BackgroundOpacity backgroundOpacity) implements Serializable {

		}
		final List<BackgroundColorAndOpacity> bgColorsAndOpacities = new ArrayList<>();
		for (final BackgroundColor backgroundColor : backgroundColors) {
			for (final BackgroundOpacity backgroundOpacity : BackgroundOpacity.values()) {
				bgColorsAndOpacities.add(new BackgroundColorAndOpacity(backgroundColor, backgroundOpacity));
			}
		}

		add(new ListView<>("backgroundColorsOpacity", bgColorsAndOpacities) {

			@Override
			protected void populateItem(ListItem<BackgroundColorAndOpacity> item) {
				final BackgroundColorAndOpacity colorAndOpacity = item.getModelObject();
				final Label label = new Label("color",
					Model.of(colorAndOpacity.backgroundColor.cssClassName() + " " + colorAndOpacity.backgroundOpacity.cssClassName()));

				switch (colorAndOpacity.backgroundColor) {
					case Light:
					case Transparent:
					case Warning:
					case Body, Body_Secondary, Body_Tertiary:
					case White:
						label.add(TextColorBehavior.dark());
						break;
					default:
						// Settings the TextColor to Dark may not work properly for all themes in combination with opacity
						if (colorAndOpacity.backgroundOpacity == BackgroundOpacity.Opacity_10
							|| colorAndOpacity.backgroundOpacity == BackgroundOpacity.Opacity_25
						) {
							label.add(TextColorBehavior.dark());
						} else {
							label.add(TextColorBehavior.white());
						}
						break;
				}

				label.add(new BackgroundColorBehavior(colorAndOpacity.backgroundColor).withOpacity(colorAndOpacity.backgroundOpacity));
				item.add(label);
			}
		});
		final String code = """
			add(new ListView<>("background-colors-opacity", bgColorsAndOpacities) {

				@Override
				protected void populateItem(ListItem<BackgroundColorAndOpacity> item) {
					final BackgroundColorAndOpacity colorAndOpacity = item.getModelObject();
					final Label label = new Label("color",
						Model.of(colorAndOpacity.backgroundColor.cssClassName() + " " + colorAndOpacity.backgroundOpacity.cssClassName()));

					switch (colorAndOpacity.backgroundColor) {
						case Light:
						case Transparent:
						case Warning:
						case Body, Body_Secondary, Body_Tertiary:
						case White:
							label.add(ColorBehavior.dark());
							break;
						default:
							// Settings the TextColor to Dark may not work properly for all themes in combination with opacity
							if (colorAndOpacity.backgroundOpacity == BackgroundOpacity.Opacity_10
								|| colorAndOpacity.backgroundOpacity == BackgroundOpacity.Opacity_25
							) {
								label.add(ColorBehavior.dark());
							} else {
								label.add(ColorBehavior.white());
							}
							break;
					}

					label.add(new BackgroundColorBehavior(colorAndOpacity.backgroundColor).withOpacity(colorAndOpacity.backgroundOpacity));
					item.add(label);
				}
			});""";
		add(new Code("backgroundColorsOpacityCode", Model.of(code)).setLanguage(CodeBehavior.Language.JAVA));

		final String htmlCode = """
			<div class="border rounded mb-5 p-2">
				<wicket:container wicket:id="background-colors-opacity">
					<div wicket:id="color" class="mb-2 p-2"></div>
				</wicket:container>
			</div>""";
		add(new Code("htmlCodeBackgroundOpacity", Model.of(htmlCode)).setLanguage(Language.HTML));
	}

	private void addBackgroundColorGradients() {

		// avoid not necessary background colors
		final List<BackgroundColor> backgroundColors = Arrays.stream(BackgroundColor.values())
			.filter(c -> !c.cssClassName().contains("subtle"))
			.filter(c -> !c.cssClassName().contains("body"))
			.filter(c -> !c.cssClassName().contains("transparent"))
			.toList();

		add(new ListView<>("backgroundColorsGradient", backgroundColors) {

			@Override
			protected void populateItem(final ListItem<BackgroundColor> item) {
				final BackgroundColor backgroundColor = item.getModelObject();
				final Label label = new Label("color", Model.of(backgroundColor.cssClassName()));
				label.add(new BackgroundColorBehavior(backgroundColor).withGradient(true));
				switch (backgroundColor) {
					case Light:
					case Warning:
					case Secondary:
					case White:
						label.add(TextColorBehavior.dark());
						break;
					default:
						label.add(TextColorBehavior.white());
						break;
				}
				item.add(label);
			}
		});

		final String code = """
			add(new ListView<>("backgroundColorsGradient", backgroundColors) {

				@Override
				protected void populateItem(final ListItem<BackgroundColor> item) {
					final BackgroundColor backgroundColor = item.getModelObject();
					final Label label = new Label("color", Model.of(backgroundColor.cssClassName()));
					label.add(new BackgroundColorBehavior(backgroundColor).withGradient(true));
					switch (backgroundColor) {
			  				case Light:
			  				case Warning:
			  				case Secondary:
			  				case White:
			  					label.add(TextColorBehavior.dark());
			  					break;
			  				default:
			  					label.add(TextColorBehavior.white());
			  					break;
			  		}
					item.add(label);
				}
			});""";

		add(new Code("backgroundColorsGradientCode", Model.of(code)).setLanguage(CodeBehavior.Language.JAVA));

		final String htmlCode = """
			<div class="border rounded mb-5 p-2">
				<wicket:container wicket:id="backgroundColorsGradient">
					<div wicket:id="color" class="mb-2 p-2"></div>
				</wicket:container>
			</div>""";

		add(new Code("htmlCodeBackgroundGradient", Model.of(htmlCode)).setLanguage(Language.HTML));
	}

	private void addTextAndBackgroundColors() {

		add(new ListView<>("textAndBackgroundColors", Arrays.asList(TextAndBackgroundColor.values())) {

			@Override
			protected void populateItem(final ListItem<TextAndBackgroundColor> item) {
				final TextAndBackgroundColor textAndBackgroundColor = item.getModelObject();
				final Label label = new Label("color", Model.of(textAndBackgroundColor.cssClassName()));
				label.add(new TextAndBackgroundColorBehavior(textAndBackgroundColor));
				item.add(label);
			}
		});

		final String code = """
			add(new ListView<>("textAndBackgroundColors", Arrays.asList(TextAndBackgroundColor.values())) {

				@Override
				protected void populateItem(final ListItem<TextAndBackgroundColor> item) {
					final TextAndBackgroundColor textAndBackgroundColor = item.getModelObject();
					final Label label = new Label("color", Model.of(textAndBackgroundColor.cssClassName()));
					label.add(new TextAndBackgroundColorBehavior(textAndBackgroundColor));
					item.add(label);
				}
			});""";
		add(new Code("textAndBackgroundColorCode", Model.of(code)).setLanguage(CodeBehavior.Language.JAVA));

		final String htmlCode = """
			<div class="border rounded mb-5 p-2">
				<wicket:container wicket:id="textAndBackgroundColor">
					<div wicket:id="color" class="mb-2 p-2"></div>
				</wicket:container>
			</div""";
		add(new Code("textAndBackgroundColorHtml", Model.of(htmlCode)).setLanguage(Language.HTML));
	}
}
