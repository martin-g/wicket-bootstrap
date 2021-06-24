package de.agilecoders.wicket.samples.pages;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5CssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5IconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5IconTypeBuilder;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5IconTypeBuilder.FontAwesome5Solid;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5IconTypeBuilder.Rotation;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5IconTypeBuilder.Size;

/** FontAwesome demo page. */
@MountPath(value = "/fontawesome")
public class FontAwesomePage extends BasePage {
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public FontAwesomePage(final PageParameters varParameters) {
		super(varParameters);

		// inline icon
		add(new Icon("inline-fa", FontAwesome5IconType.camera_retro_s));

		// larger icons
		add(new Icon("large-fa", FontAwesome5IconTypeBuilder.on(FontAwesome5Solid.camera_retro).size(Size.large).build()));
		add(new Icon("2x-fa", FontAwesome5IconTypeBuilder.on(FontAwesome5Solid.camera_retro).size(Size.two).build()));
		add(new Icon("3x-fa", FontAwesome5IconTypeBuilder.on(FontAwesome5Solid.camera_retro).size(Size.three).build()));
		add(new Icon("4x-fa", FontAwesome5IconTypeBuilder.on(FontAwesome5Solid.camera_retro).size(Size.four).build()));
		add(new Icon("5x-fa", FontAwesome5IconTypeBuilder.on(FontAwesome5Solid.camera_retro).size(Size.five).build()));

		// spin icons
		add(new Icon("spinner-spin-fa", FontAwesome5IconTypeBuilder.on(FontAwesome5Solid.spinner).spin().build()));
		add(new Icon("refresh-spin-fa", FontAwesome5IconTypeBuilder.on(FontAwesome5Solid.sync_alt).spin().build()));
		add(new Icon("cog-spin-fa", FontAwesome5IconTypeBuilder.on(FontAwesome5Solid.cog).spin().build()));

		// rotate
		add(new Icon("shield-rotate-normal", FontAwesome5IconType.shield_alt_s));
		add(new Icon("shield-rotate-90", FontAwesome5IconTypeBuilder.on(FontAwesome5Solid.shield_alt)
				.rotate(Rotation.rotate_90).build()));
		add(new Icon("shield-rotate-180", FontAwesome5IconTypeBuilder.on(FontAwesome5Solid.shield_alt)
				.rotate(Rotation.rotate_180).build()));
		add(new Icon("shield-rotate-270", FontAwesome5IconTypeBuilder.on(FontAwesome5Solid.shield_alt)
				.rotate(Rotation.rotate_270).build()));
		add(new Icon("shield-rotate-flip-horizontal", FontAwesome5IconTypeBuilder.on(FontAwesome5Solid.shield_alt)
				.rotate(Rotation.flip_horizontal).build()));
		add(new Icon("shield-rotate-flip-vertical", FontAwesome5IconTypeBuilder.on(FontAwesome5Solid.shield_alt)
				.rotate(Rotation.flip_vertical).build()));

	}

	/**
	 * @see de.agilecoders.wicket.samples.pages.BasePage#renderHead(org.apache.wicket.markup.head.IHeaderResponse)
	 */
	@Override
	public void renderHead(final IHeaderResponse response) {
		super.renderHead(response);
		// add fontawesome css
		response.render(CssHeaderItem.forReference(FontAwesome5CssReference.instance()));
	}

}
