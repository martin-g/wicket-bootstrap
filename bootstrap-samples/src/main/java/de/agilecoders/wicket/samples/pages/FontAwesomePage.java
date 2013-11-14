package de.agilecoders.wicket.samples.pages;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.FontAwesomeGraphic;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.Rotation;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconTypeBuilder.Size;

/** FontAwesome demo page. */
@MountPath(value = "/fontawesome")
public class FontAwesomePage extends BasePage<Void> {
	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public FontAwesomePage(final PageParameters varParameters) {
		super(varParameters);

		// inline icon
		add(new Icon("inline-fa", FontAwesomeIconType.camera_retro));

		// larger icons
		add(new Icon("large-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeIconTypeBuilder.FontAwesomeGraphic.camera_retro).size(Size.large).build()));
		add(new Icon("2x-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeIconTypeBuilder.FontAwesomeGraphic.camera_retro).size(Size.two).build()));
		add(new Icon("3x-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeIconTypeBuilder.FontAwesomeGraphic.camera_retro).size(Size.three).build()));
		add(new Icon("4x-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeGraphic.camera_retro).size(Size.four).build()));
		add(new Icon("5x-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeIconTypeBuilder.FontAwesomeGraphic.camera_retro).size(Size.five).build()));

		// spin icons
		add(new Icon("spinner-spin-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeGraphic.spinner).spin().build()));
		add(new Icon("refresh-spin-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeIconTypeBuilder.FontAwesomeGraphic.refresh).spin().build()));
		add(new Icon("cog-spin-fa", FontAwesomeIconTypeBuilder.on(FontAwesomeGraphic.cog).spin().build()));

		// rotate
		add(new Icon("shield-rotate-normal", FontAwesomeIconType.shield));
		add(new Icon("shield-rotate-90", FontAwesomeIconTypeBuilder.on(FontAwesomeIconTypeBuilder.FontAwesomeGraphic.shield)
		        .rotate(Rotation.rotate_90).build()));
		add(new Icon("shield-rotate-180", FontAwesomeIconTypeBuilder.on(FontAwesomeIconTypeBuilder.FontAwesomeGraphic.shield)
		        .rotate(Rotation.rotate_180).build()));
		add(new Icon("shield-rotate-270", FontAwesomeIconTypeBuilder.on(FontAwesomeIconTypeBuilder.FontAwesomeGraphic.shield)
		        .rotate(Rotation.rotate_270).build()));
		add(new Icon("shield-rotate-flip-horizontal", FontAwesomeIconTypeBuilder.on(FontAwesomeIconTypeBuilder.FontAwesomeGraphic.shield)
		        .rotate(Rotation.flip_horizontal).build()));
		add(new Icon("shield-rotate-flip-vertical", FontAwesomeIconTypeBuilder.on(FontAwesomeGraphic.shield)
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
