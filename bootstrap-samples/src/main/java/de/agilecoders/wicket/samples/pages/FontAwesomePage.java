package de.agilecoders.wicket.samples.pages;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome7CssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome7IconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome7IconTypeBuilder;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome7IconTypeBuilder.FontAwesome7Solid;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome7IconTypeBuilder.Rotation;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome7IconTypeBuilder.Size;

/** FontAwesome demo page. */
public class FontAwesomePage extends BasePage {
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public FontAwesomePage(final PageParameters varParameters) {
		super(varParameters);

		// inline icon
		add(new Icon("inline-fa", FontAwesome7IconType.camera_retro_s));

		// larger icons
		add(new Icon("large-fa", FontAwesome7IconTypeBuilder.on(FontAwesome7Solid.camera_retro).size(Size.large).build()));
		add(new Icon("2x-fa", FontAwesome7IconTypeBuilder.on(FontAwesome7Solid.camera_retro).size(Size.two).build()));
		add(new Icon("3x-fa", FontAwesome7IconTypeBuilder.on(FontAwesome7Solid.camera_retro).size(Size.three).build()));
		add(new Icon("4x-fa", FontAwesome7IconTypeBuilder.on(FontAwesome7Solid.camera_retro).size(Size.four).build()));
		add(new Icon("5x-fa", FontAwesome7IconTypeBuilder.on(FontAwesome7Solid.camera_retro).size(Size.five).build()));

		// spin icons
		add(new Icon("spinner-spin-fa", FontAwesome7IconTypeBuilder.on(FontAwesome7Solid.spinner).spin().build()));
		add(new Icon("rotate-spin-fa", FontAwesome7IconTypeBuilder.on(FontAwesome7Solid.rotate).spin().build()));
		add(new Icon("gear-spin-fa", FontAwesome7IconTypeBuilder.on(FontAwesome7Solid.gear).spin().build()));

		// rotate
		add(new Icon("shield-rotate-normal", FontAwesome7IconType.shield_halved_s));
		add(new Icon("shield-rotate-90", FontAwesome7IconTypeBuilder.on(FontAwesome7Solid.shield_halved)
            .rotate(Rotation.rotate_90).build()));
		add(new Icon("shield-rotate-180", FontAwesome7IconTypeBuilder.on(FontAwesome7Solid.shield_halved)
            .rotate(Rotation.rotate_180).build()));
		add(new Icon("shield-rotate-270", FontAwesome7IconTypeBuilder.on(FontAwesome7Solid.shield_halved)
            .rotate(Rotation.rotate_270).build()));
		add(new Icon("shield-rotate-flip-horizontal", FontAwesome7IconTypeBuilder.on(FontAwesome7Solid.shield_halved)
            .rotate(Rotation.flip_horizontal).build()));
		add(new Icon("shield-rotate-flip-vertical", FontAwesome7IconTypeBuilder.on(FontAwesome7Solid.shield_halved)
            .rotate(Rotation.flip_vertical).build()));
	}

	/**
	 * @see de.agilecoders.wicket.samples.pages.BasePage#renderHead(org.apache.wicket.markup.head.IHeaderResponse)
	 */
	@Override
	public void renderHead(final IHeaderResponse response) {
		super.renderHead(response);
		// add fontawesome css
		response.render(CssHeaderItem.forReference(FontAwesome7CssReference.instance()));
	}

}
