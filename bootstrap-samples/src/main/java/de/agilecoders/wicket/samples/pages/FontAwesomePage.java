package de.agilecoders.wicket.samples.pages;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6CssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconTypeBuilder;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconTypeBuilder.FontAwesome6Solid;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconTypeBuilder.Rotation;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconTypeBuilder.Size;

/** FontAwesome demo page. */
@MountPath(value = "/fontawesome")
public class FontAwesomePage extends BasePage {
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public FontAwesomePage(final PageParameters varParameters) {
		super(varParameters);

		// inline icon
		add(new Icon("inline-fa", FontAwesome6IconType.camera_retro_s));

		// larger icons
		add(new Icon("large-fa", FontAwesome6IconTypeBuilder.on(FontAwesome6Solid.camera_retro).size(Size.large).build()));
		add(new Icon("2x-fa", FontAwesome6IconTypeBuilder.on(FontAwesome6Solid.camera_retro).size(Size.two).build()));
		add(new Icon("3x-fa", FontAwesome6IconTypeBuilder.on(FontAwesome6Solid.camera_retro).size(Size.three).build()));
		add(new Icon("4x-fa", FontAwesome6IconTypeBuilder.on(FontAwesome6Solid.camera_retro).size(Size.four).build()));
		add(new Icon("5x-fa", FontAwesome6IconTypeBuilder.on(FontAwesome6Solid.camera_retro).size(Size.five).build()));

		// spin icons
		add(new Icon("spinner-spin-fa", FontAwesome6IconTypeBuilder.on(FontAwesome6Solid.spinner).spin().build()));
		add(new Icon("refresh-spin-fa", FontAwesome6IconTypeBuilder.on(FontAwesome6Solid.rotate).spin().build()));
		add(new Icon("cog-spin-fa", FontAwesome6IconTypeBuilder .on(FontAwesome6Solid.gear).spin().build()));

		// rotate
		add(new Icon("shield-rotate-normal", FontAwesome6IconType.shield_halved_s));
		add(new Icon("shield-rotate-90", FontAwesome6IconTypeBuilder.on(FontAwesome6Solid.shield_halved)
            .rotate(Rotation.rotate_90).build()));
		add(new Icon("shield-rotate-180", FontAwesome6IconTypeBuilder.on(FontAwesome6Solid.shield_halved)
            .rotate(Rotation.rotate_180).build()));
		add(new Icon("shield-rotate-270", FontAwesome6IconTypeBuilder.on(FontAwesome6Solid.shield_halved)
            .rotate(Rotation.rotate_270).build()));
		add(new Icon("shield-rotate-flip-horizontal", FontAwesome6IconTypeBuilder.on(FontAwesome6Solid.shield_halved)
            .rotate(Rotation.flip_horizontal).build()));
		add(new Icon("shield-rotate-flip-vertical", FontAwesome6IconTypeBuilder.on(FontAwesome6Solid.shield_halved)
            .rotate(Rotation.flip_vertical).build()));
	}

	/**
	 * @see de.agilecoders.wicket.samples.pages.BasePage#renderHead(org.apache.wicket.markup.head.IHeaderResponse)
	 */
	@Override
	public void renderHead(final IHeaderResponse response) {
		super.renderHead(response);
		// add fontawesome css
		response.render(CssHeaderItem.forReference(FontAwesome6CssReference.instance()));
	}

}
