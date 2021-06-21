package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>Important</strong>: To use font-awesome 5.x you need to declare the Maven/Gradle dependency
 * in your application pom.xml/build.gradle, for example:
 *
 * <br/>
 *  &lt;dependency&gt;<br/>
 *      &lt;groupId&gt;org.webjars&lt;/groupId&gt;<br/>
 *      &lt;artifactId&gt;font-awesome&lt;/artifactId&gt;<br/>
 *      &lt;version&gt;5.15.3&lt;/version&gt;<br/>
 *  &lt;/dependency&gt;<br/>
 *
 *
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

    public interface FontAwesomeGraphic {
        String getPrefix();

        String getIconName();
    }

    /**
     * All icons of style 'solid' available for free in Font Awesome.
     */
    public enum FontAwesomeSolid implements FontAwesomeGraphic {
        ad, address_book, address_card, adjust, air_freshener, align_center, align_justify, align_left, align_right,
        allergies, ambulance, american_sign_language_interpreting, anchor, angle_double_down, angle_double_left,
        angle_double_right, angle_double_up, angle_down, angle_left, angle_right, angle_up, angry, ankh, apple_alt,
        archive, archway, arrow_alt_circle_down, arrow_alt_circle_left, arrow_alt_circle_right, arrow_alt_circle_up,
        arrow_circle_down, arrow_circle_left, arrow_circle_right, arrow_circle_up, arrow_down, arrow_left, arrow_right,
        arrow_up, arrows_alt, arrows_alt_h, arrows_alt_v, assistive_listening_systems, asterisk, at, atlas, atom,
        audio_description, award, baby, baby_carriage, backspace, backward, bacon, bacteria, bacterium, bahai,
        balance_scale_left, balance_scale_right, balance_scale, ban, band_aid,
        barcode, bars, baseball_ball, basketball_ball, bath, battery_empty, battery_full, battery_half, battery_quarter,
        battery_three_quarters, bed, beer, bell, bell_slash, bezier_curve, bible, bicycle, biking, binoculars, biohazard,
        birthday_cake, blender, blender_phone, blind, blog, bold, bolt, bomb, bone, bong, book, book_dead, book_medical,
        book_open, book_reader, bookmark, border_all, border_none, border_style, bowling_ball, box, box_open, box_tissue, boxes, braille, brain, bread_slice, briefcase,
        briefcase_medical, broadcast_tower, broom, brush, bug, building, bullhorn, bullseye, burn, bus, bus_alt,
        business_time, calculator, calendar, calendar_alt, calendar_check, calendar_day, calendar_minus, calendar_plus,
        calendar_times, calendar_week, camera, camera_retro, campground, candy_cane, cannabis, capsules, car, car_alt,
        car_battery, car_crash, car_side, caravan, caret_down, caret_left, caret_right, caret_square_down, caret_square_left,
        caret_square_right, caret_square_up, caret_up, carrot, cart_arrow_down, cart_plus, cash_register, cat,
        certificate, chair, chalkboard, chalkboard_teacher, charging_station, chart_area, chart_bar, chart_line,
        chart_pie, check, check_circle, check_double, check_square, cheese, chess, chess_bishop, chess_board,
        chess_king, chess_knight, chess_pawn, chess_queen, chess_rook, chevron_circle_down, chevron_circle_left,
        chevron_circle_right, chevron_circle_up, chevron_down, chevron_left, chevron_right, chevron_up, child, church,
        circle, circle_notch, city, clinic_medical, clipboard, clipboard_check, clipboard_list, clock, clone,
        closed_captioning, cloud, cloud_download_alt, cloud_meatball, cloud_moon, cloud_moon_rain, cloud_rain,
        cloud_showers_heavy, cloud_sun, cloud_sun_rain, cloud_upload_alt, cocktail, code, code_branch, coffee, cog,
        cogs, coins, columns, comment, comment_alt, comment_dollar, comment_dots, comment_medical, comment_slash,
        comments, comments_dollar, compact_disc, compass, compress, compress_alt, compress_arrows_alt, concierge_bell, cookie,
        cookie_bite, copy, copyright, couch, credit_card, crop, crop_alt, cross, crosshairs, crow, crown, crutch, cube,
        cubes, cut, database, deaf, democrat, desktop, dharmachakra, diagnoses, dice, dice_d20, dice_d6, dice_five,
        dice_four, dice_one, dice_six, dice_three, dice_two, digital_tachograph, directions, disease, divide, dizzy, dna, dog,
        dollar_sign, dolly, dolly_flatbed, donate, door_closed, door_open, dot_circle, dove, download, drafting_compass,
        dragon, draw_polygon, drum, drum_steelpan, drumstick_bite, dumbbell, dumpster, dumpster_fire, dungeon, edit,
        egg, eject, ellipsis_h, ellipsis_v, envelope, envelope_open, envelope_open_text, envelope_square, equals,
        eraser, ethernet, euro_sign, exchange_alt, exclamation, exclamation_circle, exclamation_triangle, expand, expand_alt,
        expand_arrows_alt, external_link_alt, external_link_square_alt, eye, eye_dropper, eye_slash, fan, fast_backward,
        fast_forward, faucet, fax, feather, feather_alt, female, fighter_jet, file, file_alt, file_archive, file_audio,
        file_code, file_contract, file_csv, file_download, file_excel, file_export, file_image, file_import,
        file_invoice, file_invoice_dollar, file_medical, file_medical_alt, file_pdf, file_powerpoint, file_prescription,
        file_signature, file_upload, file_video, file_word, fill, fill_drip, film, filter, fingerprint, fire, fire_alt,
        fire_extinguisher, first_aid, fish, fist_raised, flag, flag_checkered, flag_usa, flask, flushed, folder,
        folder_minus, folder_open, folder_plus, font, football_ball, forward, frog, frown, frown_open, funnel_dollar,
        futbol, gamepad, gas_pump, gavel, gem, genderless, ghost, gift, gifts, glass_cheers, glass_martini,
        glass_martini_alt, glass_whiskey, glasses, globe, globe_africa, globe_americas, globe_asia, globe_europe,
        golf_ball, gopuram, graduation_cap, greater_than, greater_than_equal, grimace, grin, grin_alt, grin_beam,
        grin_beam_sweat, grin_hearts, grin_squint, grin_squint_tears, grin_stars, grin_tears, grin_tongue,
        grin_tongue_squint, grin_tongue_wink, grin_wink, grip_horizontal, grip_lines, grip_lines_vertical,
        grip_vertical, guitar, h_square, hamburger, hammer, hamsa, hand_holding, hand_holding_heart, hand_holding_medical, hand_holding_usd,
        hand_lizard, hand_middle_finger, hand_paper, hand_peace, hand_point_down, hand_point_left, hand_point_right,
        hand_holding_water, hand_point_up, hand_pointer, hand_rock, hand_scissors, hand_sparkles, hand_spock,
        hands, hands_helping, hands_wash, handshake, handshake_alt_slash, handshake_slash, hanukiah,
        hard_hat, hashtag, hat_cowboy, hat_cowboy_side, hat_wizard, hdd, head_slide_cough, head_side_cough, head_side_cough_slash,
        head_side_mask, head_side_virus, heading, headphones, headphones_alt, headset, heart, heart_broken,
        heartbeat, helicopter, highlighter, hiking, hippo, history, hockey_puck, holly_berry, home, horse, horse_head,
        hospital, hospital_alt, hospital_user, hospital_symbol, hot_tub, hotdog, hotel, hourglass, hourglass_end, hourglass_half,
        hourglass_start, house_damage, house_user, hryvnia, i_cursor, ice_cream, icicles, icons, id_badge, id_card, id_card_alt, igloo,
        image, images, inbox, indent, industry, infinity, info, info_circle, italic, jedi, joint, journal_whills,
        kaaba, key, keyboard, khanda, kiss, kiss_beam, kiss_wink_heart, kiwi_bird, landmark, language, laptop,
        laptop_code, laptop_house, laptop_medical, laugh, laugh_beam, laugh_squint, laugh_wink, layer_group, leaf, lemon, less_than,
        less_than_equal, level_down_alt, level_up_alt, life_ring, lightbulb, link, lira_sign, list, list_alt, list_ol,
        list_ul, location_arrow, lock, lock_open, long_arrow_alt_down, long_arrow_alt_left, long_arrow_alt_right,
        long_arrow_alt_up, low_vision, luggage_cart, lungs, lungs_virus, magic, magnet, mail_bulk, male, map, map_marked, map_marked_alt,
        map_marker, map_marker_alt, map_pin, map_signs, marker, mars, mars_double, mars_stroke, mars_stroke_h,
        mars_stroke_v, mask, medal, medkit, meh, meh_blank, meh_rolling_eyes, memory, menorah, mercury, meteor,
        microchip, microphone, microphone_alt, microphone_alt_slash, microphone_slash, microscope, minus, minus_circle,
        minus_square, mitten, mobile, mobile_alt, money_bill, money_bill_alt, money_bill_wave, money_bill_wave_alt,
        money_check, money_check_alt, monument, moon, mortar_pestle, mosque, motorcycle, mountain, mouse_pointer, mouse,
        mug_hot, music, network_wired, neuter, newspaper, not_equal, notes_medical, object_group, object_ungroup,
        oil_can, om, otter, outdent, pager, paint_brush, paint_roller, palette, pallet, paper_plane, paperclip,
        parachute_box, paragraph, parking, passport, pastafarianism, paste, pause, pause_circle, paw, peace, pen,
        pen_alt, pen_fancy, pen_nib, pen_square, pencil_alt, pencil_ruler, people_arrows, people_carry, pepper_hot, percent,
        percentage, person_booth, phone, phone_alt, phone_slash, phone_square_alt, phone_square, phone_volume, photo_video, piggy_bank, pills, pizza_slice,
        place_of_worship, plane, plane_arrival, plane_departure, plane_slash, play, play_circle, plug, plus, plus_circle,
        plus_square, podcast, poll, poll_h, poo, poo_storm, poop, portrait, pound_sign, power_off, pray, praying_hands,
        prescription, prescription_bottle, prescription_bottle_alt, print, procedures, project_diagram, pump_medical, pump_soap, puzzle_piece,
        qrcode, question, question_circle, quidditch, quote_left, quote_right, quran, radiation, radiation_alt, rainbow,
        random, receipt, record_vinyl, recycle, redo, redo_alt, registered, reply, remove_format, reply_all, republican, restroom, retweet, ribbon,
        ring, road, robot, rocket, route, rss, rss_square, ruble_sign, ruler, ruler_combined, ruler_horizontal,
        ruler_vertical, running, rupee_sign, sad_cry, sad_tear, satellite, satellite_dish, save, school, screwdriver,
        scroll, sd_card, search, search_dollar, search_location, search_minus, search_plus, seedling, server, shapes,
        share, share_alt, share_alt_square, share_square, shekel_sign, shield_alt, shield_virus, ship, shipping_fast, shoe_prints,
        shopping_bag, shopping_basket, shopping_cart, shower, shuttle_van, sign, sign_in_alt, sign_language,
        sign_out_alt, signal, signature, sim_card, sink, sitemap, skating, skiing, skiing_nordic, skull, skull_crossbones,
        slash, sleigh, sliders_h, smile, smile_beam, smile_wink, smog, smoking, smoking_ban, sms, snowboarding,
        snowflake, snowman, snowplow, soap, socks, solar_panel, sort, sort_alpha_down_alt, sort_alpha_down, sort_alpha_up_alt,
        sort_alpha_up, sort_amount_down_alt, sort_amount_down, sort_amount_up_alt, sort_amount_up, sort_down, sort_numeric_down_alt,
        sort_numeric_down, sort_numeric_up_alt, sort_numeric_up, sort_up, spa, space_shuttle, spell_check, spider, spinner,
        splotch, spray_can, square, square_full, square_root_alt, stamp, star, star_and_crescent, star_half,
        star_half_alt, star_of_david, star_of_life, step_backward, step_forward, stethoscope, sticky_note, stop,
        stop_circle, stopwatch, stopwatch_20, store, store_alt, store_alt_slash, store_slash, stream, street_view, strikethrough, stroopwafel, subscript, subway,
        suitcase, suitcase_rolling, sun, superscript, surprise, swatchbook, swimmer, swimming_pool, synagogue, sync,
        sync_alt, syringe, table, table_tennis, tablet, tablet_alt, tablets, tachometer_alt, tag, tags, tape, tasks,
        taxi, teeth, teeth_open, temperature_high, temperature_low, tenge, terminal, text_height, text_width, th,
        th_large, th_list, theater_masks, thermometer, thermometer_empty, thermometer_full, thermometer_half,
        thermometer_quarter, thermometer_three_quarters, thumbs_down, thumbs_up, thumbtack, ticket_alt, times,
        times_circle, tint, tint_slash, tired, toggle_off, toggle_on, toilet, toilet_paper, toilet_paper_slash, toolbox, tools, tooth,
        torah, torii_gate, tractor, trademark, traffic_light, trailer, train, tram, transgender, transgender_alt, trash,
        trash_alt, trash_restore, trash_restore_alt, tree, trophy, truck, truck_loading, truck_monster, truck_moving,
        truck_pickup, tshirt, tty, tv, umbrella, umbrella_beach, underline, undo, undo_alt, universal_access,
        university, unlink, unlock, unlock_alt, upload, user, user_alt, user_alt_slash, user_astronaut, user_check,
        user_circle, user_clock, user_cog, user_edit, user_friends, user_graduate, user_injured, user_lock, user_md,
        user_minus, user_ninja, user_nurse, user_plus, user_secret, user_shield, user_slash, user_tag, user_tie,
        user_times, users, users_cog, users_slash, utensil_spoon, utensils, vector_square, venus, venus_double, venus_mars, vest, vest_patches, vial,
        vials, video, video_slash, virus, virus_slash, viruses, vihara, voicemail, volleyball_ball, volume_down, volume_mute, volume_off, volume_up, vote_yea,
        vr_cardboard, walking, wallet, warehouse, water, wave_square, weight, weight_hanging, wheelchair, wifi, wind, window_close,
        window_maximize, window_minimize, window_restore, wine_bottle, wine_glass, wine_glass_alt, won_sign, wrench,
        x_ray, yen_sign, yin_yang;

        @Override
        public String getPrefix() {
            return "fas";
        }

        @Override
        public String getIconName() {
            return "fa-" + name();
        }
    }

    /**
     * All icons of style 'regular' available for free in Font Awesome.
     */
    public enum FontAwesomeRegular implements FontAwesomeGraphic {
        address_book, address_card, angry, arrow_alt_circle_down, arrow_alt_circle_left, arrow_alt_circle_right,
        arrow_alt_circle_up, bell, bell_slash, bookmark, building, calendar, calendar_alt, calendar_check,
        calendar_minus, calendar_plus, calendar_times, caret_square_down, caret_square_left, caret_square_right,
        caret_square_up, chart_bar, check_circle, check_square, circle, clipboard, clock, clone, closed_captioning,
        comment, comment_alt, comment_dots, comments, compass, copy, copyright, credit_card, dizzy, dot_circle, edit,
        envelope, envelope_open, eye, eye_slash, file, file_alt, file_archive, file_audio, file_code, file_excel,
        file_image, file_pdf, file_powerpoint, file_video, file_word, flag, flushed, folder, folder_open, frown,
        frown_open, futbol, gem, grimace, grin, grin_alt, grin_beam, grin_beam_sweat, grin_hearts, grin_squint,
        grin_squint_tears, grin_stars, grin_tears, grin_tongue, grin_tongue_squint, grin_tongue_wink, grin_wink,
        hand_lizard, hand_paper, hand_peace, hand_point_down, hand_point_left, hand_point_right, hand_point_up,
        hand_pointer, hand_rock, hand_scissors, hand_spock, handshake, hdd, heart, hospital, hourglass, id_badge,
        id_card, image, images, keyboard, kiss, kiss_beam, kiss_wink_heart, laugh, laugh_beam, laugh_squint, laugh_wink,
        lemon, life_ring, lightbulb, list_alt, map, meh, meh_blank, meh_rolling_eyes, minus_square, money_bill_alt,
        moon, newspaper, object_group, object_ungroup, paper_plane, pause_circle, play_circle, plus_square,
        question_circle, registered, sad_cry, sad_tear, save, share_square, smile, smile_beam, smile_wink, snowflake,
        square, star, star_half, sticky_note, stop_circle, sun, surprise, thumbs_down, thumbs_up, times_circle, tired,
        trash_alt, user, user_circle, window_close, window_maximize, window_minimize, window_restore;

        @Override
        public String getPrefix() {
            return "far";
        }

        @Override
        public String getIconName() {
            return "fa-" + name();
        }
    }

    /**
     * All icons of style 'brand' available for free in Font Awesome.
     */
    public enum FontAwesomeBrand implements FontAwesomeGraphic {
        _500px, accessible_icon, accusoft, acquisitions_incorporated, adn, adversal, affiliatetheme, airbnb, algolia,
        alipay, amazon, amazon_pay, amilia, android, angellist, angrycreative, angular, app_store, app_store_ios, apper,
        apple, apple_pay, artstation, asymmetrik, atlassian, audible, autoprefixer, avianex, aviato, aws, bandcamp, battle_net,
        behance, behance_square, bimobject, bitbucket, bitcoin, bity, black_tie, blackberry, blogger, blogger_b,
        bluetooth, bluetooth_b, bootstrap, btc, buffer, buromobelexperte, buy_n_large, buysellads, canadian_maple_leaf, cc_amazon_pay, cc_amex,
        cc_apple_pay, cc_diners_club, cc_discover, cc_jcb, cc_mastercard, cc_paypal, cc_stripe, cc_visa, centercode,
        centos, chrome, chromecast, cloudflare, cloudscale, cloudsmith, cloudversify, codepen, codiepie, confluence, connectdevelop, contao, cotton_bureau,
        cpanel, creative_commons, creative_commons_by, creative_commons_nc, creative_commons_nc_eu,
        creative_commons_nc_jp, creative_commons_nd, creative_commons_pd, creative_commons_pd_alt,
        creative_commons_remix, creative_commons_sa, creative_commons_sampling, creative_commons_sampling_plus,
        creative_commons_share, creative_commons_zero, critical_role, css3, css3_alt, cuttlefish, d_and_d,
        d_and_d_beyond, dailymotion, dashcube, deezer, delicious, deploydog, deskpro, dev, deviantart, dhl, diaspora, digg, digital_ocean,
        discord, discourse, dochub, docker, draft2digital, dribbble, dribbble_square, dropbox, drupal, dyalog,
        earlybirds, ebay, edge, edge_legacy, elementor, ello, ember, empire, envira, erlang, ethereum, etsy, evernote, expeditedssl, facebook,
        facebook_f, facebook_messenger, facebook_square, fantasy_flight_games, fedex, fedora, figma, firefox, firefox_browser,
        first_order, first_order_alt, firstdraft, flickr, flipboard, fly, font_awesome, font_awesome_alt,
        font_awesome_flag, fonticons, fonticons_fi, fort_awesome, fort_awesome_alt, forumbee,
        foursquare, free_code_camp, freebsd, fulcrum, galactic_republic, galactic_senate, get_pocket, gg, gg_circle,
        git, git_alt, git_square, github, github_alt, github_square, gitkraken, gitlab, gitter, glide, glide_g, gofore,
        goodreads, goodreads_g, google, google_drive, google_pay, google_play, google_plus, google_plus_g, google_plus_square,
        google_wallet, gratipay, grav, gripfire, grunt, guilded, gulp, hacker_news, hacker_news_square, hackerrank, hips,
        hire_a_helper, hive, hooli, hornbill, hotjar, houzz, html5, hubspot, ideal, imdb, innosoft, instagram, instagram_square, instalod, intercom, internet_explorer,
        invision, ioxhost, itch_io, itunes, itunes_note, java, jedi_order, jenkins, jira, joget, joomla, js, js_square,
        jsfiddle, kaggle, keybase, keycdn, kickstarter, kickstarter_k, korvue, laravel, lastfm, lastfm_square, leanpub,
        less, line, linkedin, linkedin_in, linode, linux, lyft, magento, mailchimp, mandalorian, markdown, mastodon,
        maxcdn, mdb, medapps, medium, medium_m, medrt, meetup, megaport, mendeley, microblog, microsoft, mix, mixcloud, mixer, mizuni, modx,
        monero, napster, neos, nimblr, node, node_js, npm, ns8, nutritionix, octopus_deploy, odnoklassniki,
        odnoklassniki_square, old_republic, opencart, openid, opera, optin_monster, orcid, osi, page4, pagelines, palfed,
        patreon, paypal, penny_arcade, perbyte, periscope, phabricator, phoenix_framework, phoenix_squadron, php, pied_piper,
        pied_piper_alt, pied_piper_hat, pied_piper_pp, pied_piper_square, pinterest, pinterest_p, pinterest_square, playstation,
        product_hunt, pushed, python, qq, quinscape, quora, r_project, raspberry_pi, ravelry, react, reacteurope,
        readme, rebel, red_river, reddit, reddit_alien, reddit_square, redhat, renren, replyd, researchgate, resolving,
        rev, rocketchat, rockrms, rust, safari, salesforce, sass, schlix, scribd, searchengin, sellcast, sellsy, servicestack,
        shirtsinbulk, shopify, shopware, simplybuilt, sistrix, sith, sketch, skyatlas, skype, slack, slack_hash, slideshare,
        snapchat, snapchat_ghost, snapchat_square, soundcloud, sourcetree, speakap, speaker_deck, spotify, squarespace,
        stack_exchange, stack_overflow, stackpath, staylinked, steam, steam_square, steam_symbol, sticker_mule, strava, stripe,
        stripe_s, studiovinari, stumbleupon, stumbleupon_circle, superpowers, supple, suse, swift, symfony, teamspeak,
        telegram, telegram_plane, tencent_weibo, the_red_yeti, themeco, themeisle, think_peaks, tiktok, trade_federation,
        trello, tripadvisor, tumblr, tumblr_square, twitch, twitter, twitter_square, typo3, uber, ubuntu, uikit, umbraco, uncharted,
        uniregistry, unity, unsplash, untappd, ups, usb, usps, ussunnah, vaadin, viacoin, viadeo, viadeo_square, viber, vimeo,
        vimeo_square, vimeo_v, vine, vk, vnv, vuejs, watchman_monitoring, waze, weebly, weibo, weixin, whatsapp, whatsapp_square, whmcs,
        wikipedia_w, windows, wix, wizards_of_the_coast, wodu, wolf_pack_battalion, wordpress, wordpress_simple, wpbeginner,
        wpexplorer, wpforms, wpressr, xbox, xing, xing_square, y_combinator, yahoo, yammer, yandex,
        yandex_international, yarn, yelp, yoast, youtube, youtube_square, zhihu;

        @Override
        public String getPrefix() {
            return "fab";
        }

        @Override
        public String getIconName() {
            if (this == _500px)
                return "fa-500px";
            else
                return "fa-" + name();
        }
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
        two("2x"), three("3x"), four("4x"), five("5x"), large("lg");

        private String style;

        Size(final String factor) {
            this.style = "fa-" + factor;
        }}

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

    private boolean fixedWidth;

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
        final List<String> styles = new ArrayList<>();

        styles.add(fontAwesomeGraphic.getPrefix());

        // replace all underscore to dashes
        styles.add(underscoresToDashes(fontAwesomeGraphic.getIconName()));

        // add spin class?
        if (spin) {
            styles.add("fa-spin");
        }

        if (fixedWidth) {
            styles.add("fa-fw");
        }

        // add rotation class?
        if (rotation != null) {
            styles.add("fa-" + underscoresToDashes(rotation.name()));
        }

        // add size class
        if (size != null) {
            styles.add(size.style);
        }

        return new FontAwesomeIconType(styles.toArray(new String[0]));
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
     * make the icon fixed width
     *
     * @return the builder
     */
    public FontAwesomeIconTypeBuilder fixedWidth() {
        this.fixedWidth = true;
        return this;
    }

    /**
     * An alias for {@link #fixedWidth()}
     *
     * @return the builder
     */
    public FontAwesomeIconTypeBuilder fw() {
        return fixedWidth();
    }

    /**
     * @param string string to work on!
     * @return the string with the underscores replace with dashes
     */
    private String underscoresToDashes(final String string) {
        return string.replace('_', '-');
    }
}
