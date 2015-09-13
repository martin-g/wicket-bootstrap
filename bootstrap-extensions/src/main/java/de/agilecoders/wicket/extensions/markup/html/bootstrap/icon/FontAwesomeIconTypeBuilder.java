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
     * All the icons available in Font Awesome.
     */
    public enum FontAwesomeGraphic {
        px500, adjust, adn, align_center, align_justify, align_left, align_right, amazon, ambulance, anchor, android,
        angellist, angle_double_down, angle_double_left, angle_double_right, angle_double_up, angle_down, angle_left,
        angle_right, angle_up, apple, area_chart, archive, arrow_circle_down, arrow_circle_left, arrow_circle_o_down,
        arrow_circle_o_left, arrow_circle_o_right, arrow_circle_o_up, arrow_circle_right, arrow_circle_up, arrow_down,
        arrow_left, arrow_right, arrow_up, arrows, arrows_alt, arrows_h, arrows_v, asterisk, at, backward,
        balance_scale, ban, bar_chart_o, barcode, bars, battery_empty, battery_full, battery_half,
        battery_quarter, battery_three_quarters, bed, beer, behance, behance_square, bell, bell_o, bell_slash,
        bell_slash_o, bicycle, binoculars, birthday_cake, bitbucket, bitbucket_square, black_tie, bold, bolt, bomb,
        book, bookmark, bookmark_o, briefcase, btc, bug, building, building_o, bullhorn, bullseye, bus, buysellads,
        calculator, calendar, calendar_check_o, calendar_minus_o, calendar_o, calendar_plus_o, calendar_times_o, camera,
        camera_retro, car, caret_down, caret_left, caret_right, caret_square_o_down, caret_square_o_left,
        caret_square_o_right, caret_square_o_up, caret_up, cart_arrow_down, cart_plus, cc, cc_amex, cc_diners_club,
        cc_discover, cc_jcb, cc_mastercard, cc_paypal, cc_stripe, cc_visa, certificate, chain, chain_broken, check,
        check_circle, check_circle_o, check_minus_square, check_minus_square_o, check_square, check_square_o,
        check_sign, chevron_circle_down, chevron_circle_left, chevron_circle_right, chevron_circle_up, chevron_down,
        chevron_left, chevron_right, chevron_up, child, chrome, circle, circle_o, circle_o_notch, circle_thin,
        clipboard, clock_o, clone, cloud, cloud_download, cloud_upload, cny, code, code_fork, codepen, coffee, cog,
        cogs, collapse, collapse_alt, collapse_top, columns, comment, comment_o, commenting, commenting_o, comments,
        comments_o, compass, compress, connectdevelop, contao, copy, copyright, creative_commons, credit_card, crop,
        crosshairs, css3, cube, cubes, cut, cutlery, database, dashboard, dashcube, dedent, delicious, desktop,
        deviantart, diamond, digg, dot_circle_o, download, dribbble, dropbox, drupal, edit, eject, ellipsis_h,
        ellipsis_v, empire, envelope, envelope_o, envelope_square, eraser, eur, exchange, exclamation,
        exclamation_circle, exclamation_triangle, expand, expeditedssl, external_link, external_link_square, eye,
        eyedropper, eye_slash, facebook, facebook_official, facebook_square, fast_backward, fast_forward, fax, female,
        fighter_jet, file, file_archive_o, file_audio_o, file_code_o, file_excel_o, file_image_o, file_o, file_pdf_o,
        file_powerpoint_o, file_text, file_text_o, file_video_o, file_word_o, files_o, film, filter, fire, firefox,
        fire_extinguisher, flag, flag_checkered, flag_o, flash, flask, flickr, floppy_o, folder, folder_o, folder_open,
        folder_open_o, font, fonticons, forumbee, forward, foursquare, frown_o, futbol_o, gamepad, gavel, gbp,
        genderless, get_pocket, gg, gg_circle, gift, git, git_square, github, github_alt, github_square, gittip, glass,
        globe, google, google_plus, google_plus_square, google_wallet, graduation_cap, group, h_square, hacker_news,
        hand_lizard_o, hand_o_down, hand_o_left, hand_o_right, hand_o_up, hand_paper_o, hand_peace_o, hand_pointer_o,
        hand_rock_o, hand_scissors_o, hand_spock_o, hdd_o, header, headphones, heart, heart_o, heartbeat, history, home,
        hospital_o,  hourglass, hourglass_end, hourglass_half, hourglass_o, hourglass_start, houzz, html5, i_cursor,
        ils, inbox, indent, industry, info, info_circle, inr, instagram, internet_explorer, ioxhost, italic,
        joomla, jpy, jsfiddle, key, keyboard_o, krw, language, laptop, lastfm, lastfm_square, leaf, leanpub, legal,
        lemon_o, level_down, level_up, life_ring, lightbulb_o, line_chart, link, linkedin, linkedin_square, linux, list,
        list_alt, list_ol, list_ul, location_arrow, lock, long_arrow_down, long_arrow_left, long_arrow_right,
        long_arrow_up, magic, magnet, mail_forward, mail_reply, mail_reply_all, male, map, map_marker, map_o, map_pin,
        map_signs, mars, mars_double, mars_stroke, mars_stroke_h, mars_stroke_v, maxcdn, meanpath, medium, medkit,
        meh_o, mercury, microphone, microphone_slash, minus, minus_circle, minus_square, minus_square_o, mobile, money,
        moon_o, motorcycle, mouse_pointer, move, music, neuter, newspaper_o, object_group, object_ungroup,
        odnoklassniki, odnoklassniki_square, opencart, openid, opera, optin_monster, outdent, off, ok, ok_circle,
        ok_sign, pagelines, paint_brush, paper_plane, paper_plane_o, paperclip, paragraph, paste, pause, paw, paypal,
        pencil, pencil_square, pencil_square_o, phone, phone_square, picture_o, pie_chart, pied_piper, pied_piper_alt,
        pinterest, pinterest_p, pinterest_square, plane, play, play_circle, play_circle_o, plug, plus, plus_circle,
        plus_square, plus_square_o, power_off, print, puzzle_piece, qq, qrcode, question, question_circle, quote_left,
        quote_right, random, rebel, recycle, reddit, reddit_square, refresh, registered, remove, renren, repeat, reply,
        reply_all, retweet, rmb, road, rocket, rotate_left, rotate_right, rss, rss_square, rub, rupee, safari, save,
        scissors, search, sellsy, server, share, share_alt, share_alt_square, share_square, share_square_o, sheqel,
        shield, ship, shirtsinbulk, shopping_cart, sign_blank, signal, simplybuilt, sign_in, sign_out, sitemap,
        skyatlas, skype, slack, sliders, slideshare, smile_o, sort, sort_alpha_asc, sort_alpha_desc, sort_amount_asc,
        sort_amount_desc, sort_asc, sort_numeric_asc, sort_numeric_desc, sort_desc, soundcloud, space_shuttle, spinner,
        spoon, spotify, square, square_o, stack_exchange, stack_overflow, star, star_o, steam, steam_square, star_half,
        star_half_o, step_backward, step_forward, stethoscope, sticky_note, sticky_note_o, stop, street_view,
        strikethrough, stumbleupon, stumbleupon_circle, subscript, subway, suitcase, sun_o, superscript, table, tablet,
        tachometer, tag, tags, tasks, taxi, television, tencent_weibo, terminal, text_height, text_width, th, th_large,
        th_list, thumb_tack, thumbs_down, thumbs_o_down, thumbs_o_up, thumbs_up, ticket, times, times_circle,
        times_circle_o, tint, toggle_down, toggle_left, toggle_off, toggle_on, toggle_right, toggle_up, trademark,
        train, transgender, transgender_alt, trash, trash_o, tree, trello, tripadvisor, trophy, truck, tty, tumblr,
        tumblr_square, turkish_lira, twitch, twitter, twitter_square, umbrella, underline, undo, university, unlink,
        unlock, unlock_alt, unsorted, upload, usd, user, user_md, user_plus, user_secret, user_times, users, venus,
        venus_double, venus_mars, viacoin, video_camera, vimeo, vimeo_square, vine, vk, volume_down, volume_off,
        volume_up, warning, weelchair, weibo, weixin, whatsapp, wheelchair, wifi, wikipedia_w, windows, wordpress,
        wrench, xing, xing_square, y_combinator, yahoo, yelp, youtube, youtube_play, youtube_square
    }

    /**
     * Rotation that can be done on an icon.
     */
    public enum Rotation {
        flip_horizontal, flip_vertical, normal, rotate_180, rotate_270, rotate_90
    }

    /**
     * Sizes on an icon.
     */
    public enum Size {
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
