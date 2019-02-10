package de.agilecoders.wicket.samples.pages;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.FontAwesomeSolid;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.Rotation;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.Size;

/** FontAwesome demo page. */
@MountPath(value = "/fontawesome")
public class FontAwesomePage extends BasePage {
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public FontAwesomePage(final PageParameters varParameters) {
		super(varParameters);

		// inline icon
		add(new Icon("inline-fa", FontAwesomeIconType.camera_retro_s));

		// larger icons
		add(new Icon("large-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeSolid.camera_retro).size(Size.large).build()));
		add(new Icon("2x-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeSolid.camera_retro).size(Size.two).build()));
		add(new Icon("3x-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeSolid.camera_retro).size(Size.three).build()));
		add(new Icon("4x-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeSolid.camera_retro).size(Size.four).build()));
		add(new Icon("5x-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeSolid.camera_retro).size(Size.five).build()));

		// spin icons
		add(new Icon("spinner-spin-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeSolid.spinner).spin().build()));
		add(new Icon("refresh-spin-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeSolid.sync_alt).spin().build()));
		add(new Icon("cog-spin-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeSolid.cog).spin().build()));

		// rotate
		add(new Icon("shield-rotate-normal", FontAwesomeIconType.shield_alt_s));
		add(new Icon("shield-rotate-90", FontAwesomeIconTypeBuilder.on(FontAwesomeSolid.shield_alt)
		        .rotate(Rotation.rotate_90).build()));
		add(new Icon("shield-rotate-180", FontAwesomeIconTypeBuilder.on(FontAwesomeSolid.shield_alt)
		        .rotate(Rotation.rotate_180).build()));
		add(new Icon("shield-rotate-270", FontAwesomeIconTypeBuilder.on(FontAwesomeSolid.shield_alt)
		        .rotate(Rotation.rotate_270).build()));
		add(new Icon("shield-rotate-flip-horizontal", FontAwesomeIconTypeBuilder.on(FontAwesomeSolid.shield_alt)
		        .rotate(Rotation.flip_horizontal).build()));
		add(new Icon("shield-rotate-flip-vertical", FontAwesomeIconTypeBuilder.on(FontAwesomeSolid.shield_alt)
		        .rotate(Rotation.flip_vertical).build()));

	}

	/**
	 * @see de.agilecoders.wicket.samples.pages.BasePage#renderHead(org.apache.wicket.markup.head.IHeaderResponse)
	 */
	@Override
	public void renderHead(final IHeaderResponse response) {
		super.renderHead(response);
		// add fontawesome css
		response.render(CssHeaderItem.forReference(FontAwesomeCssReference.instance()));
	}

}
