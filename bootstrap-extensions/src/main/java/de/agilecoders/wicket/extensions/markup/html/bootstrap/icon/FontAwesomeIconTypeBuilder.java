package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import java.util.ArrayList;
import java.util.List;

/**
 * A builder to build {@link FontAwesomeIconType} <br />
 * <br />
 * <b>Examples :</b>
 * <ul>
 * <li>
 * To rotate an icon 90° :
 * <code>	FontAwesomeIconTypeBuilder.on(FontAwesomeGraphic.key).rotate(Rotation.rotate_90).build()</code></li>
 * <li>
 * To spin an icon : <code>	FontAwesomeIconTypeBuilder.on(FontAwesomeGraphic.key).spin().build()</code></li>
 * <li>To resize an icon 4 times :
 * <code>	FontAwesomeIconTypeBuilder.on(FontAwesomeGraphic.key).size(Size.four).build()</code></li>
 * <li>
 * All together :
 * <code>FontAwesomeIconTypeBuilder.on(FontAwesomeGraphic.key).size(Size.four).spin().rotate(Rotation.rotate_90).build()</code>
 * </li>
 * </ul>
 */
public class FontAwesomeIconTypeBuilder {
    /**
     * All the icons available in fontawesome.
     */
    public static enum FontAwesomeGraphic {
        adjust, adn, align_center, align_justify, align_left, align_right, ambulance, anchor, android,
        angle_double_down, angle_double_left, angle_double_right, angle_double_up, angle_down, angle_left, angle_right,
        angle_up, apple, archive, arrow_circle_down, arrow_circle_left, arrow_circle_o_down, arrow_circle_o_left,
        arrow_circle_o_right, arrow_circle_o_up, arrow_circle_right, arrow_circle_up, arrow_down, arrow_left,
        arrow_right, arrow_up, arrows, arrows_alt, arrows_h, arrows_v, asterisk, backward, ban, bar_chart_o, barcode,
        bars, beer, bell, bell_o, bitbucket, bitbucket_square, bold, bolt, book, bookmark, bookmark_o, briefcase, btc,
        bug, building_o, bullhorn, bullseye, calendar, calendar_o, camera, camera_retro, caret_down, caret_left,
        caret_right, caret_square_o_down, caret_square_o_left, caret_square_o_right, caret_square_o_up, caret_up,
        certificate, chain, chain_broken, check, check_circle, check_circle_o, check_minus_square,
        check_minus_square_o, check_square, check_square_o, check_sign, chevron_circle_down, chevron_circle_left,
        chevron_circle_right, chevron_circle_up, chevron_down, chevron_left, chevron_right, chevron_up, circle,
        circle_o, clipboard, clock_o, cloud, cloud_download, cloud_upload, cny, code, code_fork, coffee, cog, cogs,
        collapse, collapse_alt, collapse_top, columns, comment, comment_o, comments, comments_o, compass, compress,
        copy, credit_card, crop, crosshairs, css3, cut, cutlery, database, dashboard, dedent, desktop, dot_circle_o, download,
        dribbble, dropbox, edit, eject, ellipsis_h, ellipsis_v, envelope, envelope_o, eraser, eur, exchange,
        exclamation, exclamation_circle, exclamation_triangle, expand, external_link, external_link_square, eye,
        eye_slash, facebook, facebook_square, fast_backward, fast_forward, female, fighter_jet, file, file_o,
        file_text, file_text_o, files_o, film, filter, fire, fire_extinguisher, flag, flag_checkered, flag_o, flash,
        flask, flickr, floppy_o, folder, folder_o, folder_open, folder_open_o, font, forward, foursquare, frown_o,
        gamepad, gavel, gbp, gift, github, github_alt, github_square, gittip, glass, globe, google_plus,
        google_plus_square, group, h_square, hand_o_down, hand_o_left, hand_o_right, hand_o_up, hdd_o, headphones,
        heart, heart_o, home, hospital_o, html5, inbox, indent, info, info_circle, inr, instagram, italic, jpy, key,
        keyboard_o, krw, laptop, leaf, legal, lemon_o, level_down, level_up, lightbulb_o, link, linkedin,
        linkedin_square, linux, list, list_alt, list_ol, list_ul, location_arrow, lock, long_arrow_down,
        long_arrow_left, long_arrow_right, long_arrow_up, magic, magnet, mail_forward, mail_reply, mail_reply_all,
        male, map_marker, maxcdn, medkit, meh_o, microphone, microphone_slash, minus, minus_circle, minus_square,
        minus_square_o, mobile, money, moon_o, move, music, outdent, off, ok, ok_circle, ok_sign, pagelines, paperclip,
        paste, pause, pencil, pencil_square, pencil_square_o, phone, phone_square, picture_o, pinterest,
        pinterest_square, plane, play, play_circle, play_circle_o, plus, plus_circle, plus_square, plus_square_o,
        power_off, print, puzzle_piece, qrcode, question, question_circle, quote_left, quote_right, random, refresh,
        remove, renren, repeat, reply, reply_all, retweet, rmb, road, rocket, rotate_left, rotate_right, rss,
        rss_square, rub, rupee, save, scissors, search, share, share_square, share_square_o, shield, shopping_cart,
        sign_blank, signal, sign_in, sign_out, sitemap, skype, smile_o, sort, sort_alpha_asc, sort_alpha_desc,
        sort_amount_asc, sort_amount_desc, sort_asc, sort_numeric_asc, sort_numeric_desc, sort_desc, spinner, square,
        square_o, stack_exchange, stack_overflow, star, star_o, star_half, star_half_o, step_backward, step_forward,
        stethoscope, stop, strikethrough, subscript, suitcase, sun_o, superscript, table, tablet, tachometer, tag,
        tags, tasks, terminal, text_height, text_width, th, th_large, th_list, thumb_tack, thumbs_down, thumbs_o_down,
        thumbs_o_up, thumbs_up, ticket, times, times_circle, times_circle_o, tint, toggle_down, toggle_left,
        toggle_right, toggle_up, trash_o, trello, trophy, truck, tumblr, tumblr_square, turkish_lira, twitter,
        twitter_square, umbrella, underline, undo, unlink, unlock, unlock_alt, unsorted, upload, usd, user, user_md,
        users, vimeo_square, video_camera, vk, volume_down, volume_off, volume_up, warning, weelchair, weibo, windows,
        wrench, xing, xing_square, youtube, youtube_play, youtube_square, won
    }

    /**
     * Rotation that can be done on an icon.
     */
    public static enum Rotation {
        flip_horizontal, flip_vertical, normal, rotate_180, rotate_270, rotate_90
    }

    /**
     * Sizes on an icon.
     */
    public static enum Size {
        two, three, four, five, large
    }

    /**
     * @param fontAwesomeGraphic icon to use in the builder
     * @return a builder for this icon
     */
    public static FontAwesomeIconTypeBuilder on(final FontAwesomeGraphic fontAwesomeGraphic) {
        return new FontAwesomeIconTypeBuilder(fontAwesomeGraphic);
    }

    /**
     * Icon used in the builder.
     */
    private final FontAwesomeGraphic fontAwesomeGraphic;
    /**
     * rotation to apply to the icon (default none).
     */
    private Rotation rotation;
    /**
     * size to apply to the icon (by default *1).
     */
    private Size size;
    /**
     * Do we have to make the icon spin?
     */
    private boolean spin;

    /**
     * @param fontAwesomeGraphic icon to use in the builder
     */
    private FontAwesomeIconTypeBuilder(final FontAwesomeGraphic fontAwesomeGraphic) {
        this.fontAwesomeGraphic = fontAwesomeGraphic;
    }

    /**
     * @return build the icon
     */
    public FontAwesomeIconType build() {
        final List<String> styles = new ArrayList<String>();

        // replace all underscore to dashes
        styles.add(underscoresToDashes(fontAwesomeGraphic.name()));

        // add spin class?
        if (spin) {
            styles.add("fa-spin");
        }

        // add rotation class?
        if (rotation != null) {
            styles.add("fa-" + underscoresToDashes(rotation.name()));
        }

        // add size class
        if (size != null) {
            switch (size) {
                case two:
                    styles.add("fa-2x");
                    break;
                case three:
                    styles.add("fa-3x");
                    break;
                case four:
                    styles.add("fa-4x");
                    break;
                case five:
                    styles.add("fa-5x");
                    break;
                case large:
                    styles.add("fa-lg");
                    break;
            }
        }

        return new FontAwesomeIconType(styles.toArray(new String[styles.size()]));
    }

    /**
     * @param rotation rotation to apply to the icon
     * @return the builder
     */
    public FontAwesomeIconTypeBuilder rotate(final Rotation rotation) {
        this.rotation = rotation;
        return this;
    }

    /**
     * @param size size to apply to the icon
     * @return the builder
     */
    public FontAwesomeIconTypeBuilder size(final Size size) {
        this.size = size;
        return this;
    }

    /**
     * make the icon spin
     *
     * @return the builder
     */
    public FontAwesomeIconTypeBuilder spin() {
        this.spin = true;
        return this;
    }

    /**
     * @param string string to work on!
     * @return the string with the underscores replace with dashes
     */
    private String underscoresToDashes(final String string) {
        return string.replace('_', '-');
    }
}
