package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>Important</strong>: To use font-awesome 6.x you need to declare the Maven/Gradle dependency
 * in your application pom.xml/build.gradle, for example:
 *
 * <br/>
 *  &lt;dependency&gt;<br/>
 *      &lt;groupId&gt;org.webjars&lt;/groupId&gt;<br/>
 *      &lt;artifactId&gt;font-awesome&lt;/artifactId&gt;<br/>
 *      &lt;version&gt;6.2.0&lt;/version&gt;<br/>
 *  &lt;/dependency&gt;<br/>
 *
 *
 * A builder to build {@link FontAwesome6IconType} <br />
 * <br />
 * <b>Examples :</b>
 * <ul>
 * <li>
 * To rotate an icon 90° :
 * <code>	FontAwesome6IconTypeBuilder.on(FontAwesome6Graphic.key).rotate(Rotation.rotate_90).build()</code></li>
 * <li>
 * To spin an icon : <code>	FontAwesome6IconTypeBuilder.on(FontAwesome6Graphic.key).spin().build()</code></li>
 * <li>To resize an icon 4 times :
 * <code>	FontAwesome6IconTypeBuilder.on(FontAwesome6Graphic.key).size(Size.four).build()</code></li>
 * <li>
 * All together :
 * <code>FontAwesome6IconTypeBuilder.on(FontAwesome6Graphic.key).size(Size.four).spin().rotate(Rotation.rotate_90).build()</code>
 * </li>
 * </ul>
 */
public class FontAwesome6IconTypeBuilder {

    public interface FontAwesome6Graphic {
        String getPrefix();

        String getIconName();
    }

    /**
     * All icons of style 'solid' available for free in Font Awesome.
     */
    public enum FontAwesome6Solid implements FontAwesome6Graphic {
        _0, _1, _2, _3, _4, _5, _6, _7, _8, _9, a, address_book, address_card, align_center, align_justify, align_left,
        align_right, anchor_circle_check, anchor_circle_exclamation, anchor_circle_xmark, anchor_lock, anchor,
        angle_down, angle_left, angle_right, angle_up, angles_down, angles_left, angles_right, angles_up, ankh,
        apple_whole, archway, arrow_down_1_9, arrow_down_9_1, arrow_down_a_z, arrow_down_long, arrow_circle_down,
        arrow_circle_left, arrow_circle_right, arrow_circle_up, arrow_down, arrow_down_short_wide,
        arrow_down_up_across_line, arrow_down_up_lock, arrow_down_wide_short, arrow_down_z_a, arrow_left_long,
        arrow_left, arrow_pointer, arrow_right_arrow_left, arrow_right_from_bracket, arrow_right_long, arrow_right,
        arrow_right_to_bracket, arrow_right_to_city, arrow_rotate_left, arrow_rotate_right, arrow_trend_down,
        arrow_trend_up, arrow_turn_down, arrow_turn_up, arrow_up_1_9, arrow_up_9_1, arrow_up_a_z, arrow_up_from_bracket,
        arrow_up_from_ground_water, arrow_up_from_water_pump, arrow_up_long, arrow_up_right_dots,
        arrow_up_right_from_square, arrow_up, arrow_up_short_wide, arrow_up_wide_short, arrow_up_z_a,
        arrows_down_to_line, arrows_down_to_people, arrows_left_right, arrows_left_right_to_line, arrows_rotate,
        arrows_spin, arrows_split_up_and_left, arrows_to_circle, arrows_to_dot, arrows_to_eye, arrows_turn_right,
        arrows_turn_to_dots, arrows_up_down_left_right, arrows_up_down, arrows_up_to_line, asterisk, at, atom,
        audio_description, austral_sign, award, b, baby, baby_carriage, backward_fast, backward, backward_step, bacon,
        bacteria, bacterium, bag_shopping, bahai, baht_sign, ban, ban_smoking, bandage, bangladeshi_taka_sign, barcode, bars_progress, bars,
        bars_staggered, baseball_bat_ball, baseball, basket_shopping, basketball, bath, battery_empty, battery_full,
        battery_half, battery_quarter, battery_three_quarters, bed_pulse, bed, beer_mug_empty, bell_concierge, bell,
        bell_slash, bezier_curve, bicycle, binoculars, biohazard, bitcoin_sign, blender, blender_phone, blind, blog,
        bold, bolt_lightning, bolt, bomb, bone, bong, book, book_atlas, book_bible, book_bookmark, book_journal_whills,
        book_medical, book_open_reader, book_open, book_quran, book_skull, book_tanakh, bookmark, border_all, border_none,
        border_top_left, bore_hole, bottle_droplet, bottle_water, bowl_food, bowl_rice, bowling_ball, box_archive, box,
        box_open, box_tissue, boxes_packing, boxes_stacked, braille, brain, brazilian_real_sign, bread_slice, briefcase,
        bridge_circle_check, bridge_circle_exclamation, bridge_circle_xmark, bridge_lock, bridge, bridge_water,
        briefcase_medical, broom_ball, broom, brush, bucket, bug, bug_slash, bugs, building_circle_arrow_right,
        building_circle_check, building_circle_exclamation, building_circle_xmark, building_columns, building_flag,
        building_lock, building_ngo, building, building_shield, building_un, building_user, building_wheat, bullhorn,
        bullseye, burger, burst, bus, bus_simple, business_time, c, cable_car, cake_candles, calculator, calendar, calendar_check,
        calendar_day, calendar_days, calendar_minus, calendar_plus, calendar_week, calendar_xmark, camera, camera_retro,
        camera_rotate, campground, candy_cane, cannabis, capsules, car, car_battery, car_burst, car_on,
        car_rear, car_side, car_tunnel, caravan, caret_down, caret_left, caret_right, caret_up, carrot, cart_arrow_down,
        cart_flatbed, cart_flatbed_suitcase, cart_plus, cart_shopping, cash_register, cat, cedi_sign, cent_sign,
        certificate, chair, chalkboard, chalkboard_user, champagne_glasses, charging_station, chart_area, chart_bar,
        chart_column, chart_gantt, chart_line, chart_pie, check, chart_simple, check_double, check_to_slot, cheese,
        chess, chess_bishop, chess_board, chess_king, chess_knight, chess_pawn, chess_queen, chess_rook, chevron_down,
        chevron_left, chevron_right, chevron_up, child_combatant, child, child_dress, child_reaching, children, church,
        circle, circle_arrow_down, circle_arrow_left, circle_arrow_right, circle_arrow_up, circle_check,
        circle_chevron_down, circle_chevron_left, circle_chevron_right, circle_chevron_up, circle_dollar_to_slot,
        circle_dot, circle_down, circle_exclamation, circle_h, circle_half_stroke, circle_info, circle_left,
        circle_minus, circle_nodes, circle_notch, circle_pause, circle_play, circle_plus,circle_question,
        circle_radiation, circle_right, circle_stop, circle_up, circle_user, circle_xmark, city, clapperboard,
        clipboard, clipboard_check, clipboard_list, clipboard_question, clipboard_user, clock, clock_rotate_left, clone,
        closed_captioning, cloud, cloud_arrow_down, cloud_arrow_up, cloud_bolt, cloud_meatball, cloud_moon,
        cloud_moon_rain, cloud_rain, cloud_showers_heavy, cloud_showers_water, cloud_sun, cloud_sun_rain, clover, code,
        code_branch, code_commit, code_compare, code_fork, code_merge, code_pull_request, coins,  colon_sign, comment,
        comment_dollar, comment_dots, comment_medical, comment_slash, comment_sms, comments, comments_dollar,
        compact_disc, compass, compass_drafting, compress, computer, computer_mouse, cookie, cookie_bite, copy,
        copyright, couch, cow, credit_card, crop, crop_simple, cross, crosshairs, crow, crown, crutch, cruzeiro_sign,
        cube, cubes, cubes_stacked, d, database, delete_left, democrat, desktop, dharmachakra, diagram_next,
        diagram_predecessor, diagram_project, diagram_successor, diamond, diamond_turn_right, dice, dice_d20, dice_d6,
        dice_five, dice_four, dice_one, dice_six, dice_three, dice_two, disease, display, divide, dna, dog, dollar_sign,
        dolly, dong_sign, door_closed, door_open, dove, down_left_and_up_right_to_center, down_long, download, dragon,
        draw_polygon, droplet, droplet_slash, drum, drum_steelpan, drumstick_bite, dumbbell, dumpster, dumpster_fire,
        dungeon, e, ear_deaf, ear_listen, earth_africa, earth_americas, earth_asia, earth_europe, earth_oceania, egg,
        eject, elevator, ellipsis, ellipsis_vertical, envelope_circle_check, envelope, envelopes_bulk, envelope_open,
        envelope_open_text, equals, eraser, ethernet, euro_sign, exclamation, expand, explosion, eye, eye_dropper,
        eye_low_vision, eye_slash, f, face_angry, face_dizzy, face_flushed, face_frown_open, face_frown, face_grimace,
        face_grin_beam, face_grin_beam_sweat, face_grin_hearts, face_grin, face_grin_squint, face_grin_squint_tears,
        face_grin_stars, face_grin_tears, face_grin_tongue, face_grin_tongue_squint, face_kiss,  face_grin_tongue_wink,
        face_grin_wide, face_grin_wink, face_kiss_beam, face_kiss_wink_heart, face_laugh_beam, face_laugh,
        face_laugh_squint, face_laugh_wink, face_meh_blank, face_meh, face_rolling_eyes, face_sad_cry, face_sad_tear,
        face_smile_beam, face_smile, face_smile_wink, face_surprise, face_tired, fan, faucet, faucet_drip, fax, feather,
        feather_pointed, ferry, file_arrow_down, file_arrow_up, file, file_audio, file_circle_check,
        file_circle_exclamation, file_circle_minus, file_circle_plus, file_circle_question, file_circle_xmark,
        file_code, file_contract, file_csv, file_excel, file_export, file_image, file_import, file_invoice,
        file_invoice_dollar, file_lines, file_medical, file_pdf, file_pen, file_powerpoint, file_prescription,
        file_shield, file_signature, file_video, file_waveform, file_word, file_zipper, fill, fill_drip, film, filter,
        filter_circle_dollar, filter_circle_xmark, fingerprint, fire, fire_burner, fire_extinguisher, fire_flame_curved,
        fire_flame_simple, fish, fish_fins, flag, flag_checkered, flag_usa, flask, flask_vial, floppy_disk, florin_sign,
        folder_closed, folder, folder_minus, folder_open, folder_plus, font, folder_tree, font_awesome, football,
        forward, forward_fast, forward_step, franc_sign, frog, futbol, g, gamepad, gas_pump, gauge, gauge_high,
        gauge_simple, gauge_simple_high, gavel, gear, gears, gem, genderless, ghost, gift, gifts, glass_water,
        glass_water_droplet,  glasses, globe, golf_ball_tee, gopuram, graduation_cap, greater_than, greater_than_equal,
        grip, grip_lines, grip_lines_vertical, grip_vertical, group_arrows_rotate, guarani_sign, guitar, gun, h, hammer,
        hamsa, hand, hand_back_fist, hand_dots, hand_fist,hand_holding, hand_holding_dollar, hand_holding_droplet,
        hand_holding_hand, hand_holding_heart, hand_holding_medical, hand_lizard, hand_middle_finger, hand_peace,
        hand_point_down, hand_point_left, hand_point_right, hand_point_up, hand_pointer, hand_scissors, hand_sparkles,
        hand_spock, handcuffs, hands, hands_asl_interpreting, hands_bound, hands_bubbles, hands_clapping,
        hands_holding_child, hands_holding_circle, hands_holding, hands_praying, handshake, handshake_angle,
        handshake_simple, handshake_simple_slash, handshake_slash, hanukiah, hard_drive, hashtag, hat_cowboy,
        hat_cowboy_side, hat_wizard, head_slide_cough, head_side_cough, head_side_cough_slash, head_side_mask,
        head_side_virus, heading, headphones, headphones_simple, headset, heart, heart_circle_bolt, heart_circle_check,
        heart_circle_exclamation, heart_circle_minus, heart_circle_plus, heart_circle_xmark, heart_crack, heart_pulse,
        helicopter, helicopter_symbol, helmet_safety, helmet_un, highlighter, hill_avalanche, hill_rockslide, hippo,
        hockey_puck, holly_berry, horse, horse_head, hospital, hospital_user, hot_tub_person, hotdog, hotel, hourglass,
        hourglass_end, hourglass_half, hourglass_start, house, house_chimney_crack, house_chimney_medical,
        house_chimney, house_chimney_user, house_chimney_window, house_circle_check, house_circle_exclamation,
        house_circle_xmark, house_crack, house_fire, house_flag, house_flood_water_circle_arrow_right,
        house_flood_water, house_laptop, house_lock, house_medical_circle_check, house_medical_circle_exclamation,
        house_medical_circle_xmark, house_medical_flag, house_medical, house_signal, house_tsunami, house_user,
        hryvnia_sign, hurricane, i, i_cursor, ice_cream, icicles, icons, id_badge, id_card, id_card_clip, igloo, image,
        image_portrait, images, inbox, indent, indian_rupee_sign, industry, infinity, info, italic, j, jar, jar_wheat,
        jedi, jet_fighter, jet_fighter_up, joint, jug_detergent, k, kaaba, key, keyboard, khanda, kip_sign, kit_medical,
        kitchen_set, kiwi_bird, l, land_mine_on, landmark, landmark_dome, landmark_flag, language, laptop, laptop_code,
        laptop_file, laptop_medical, lari_sign, layer_group, leaf, left_long, left_right, lemon, less_than,
        less_than_equal, life_ring, lightbulb, lines_leaning, link, link_slash, lira_sign, list, list_check, list_ol,
        list_ul, litecoin_sign, location_arrow, location_crosshairs, location_dot, location_pin_lock, location_pin,
        lock, lock_open, locust, lungs, lungs_virus, m, magnet, map, magnifying_glass_arrow_right,
        magnifying_glass_chart, magnifying_glass_dollar, magnifying_glass_location, magnifying_glass_minus,
        magnifying_glass_plus, magnifying_glass, manat_sign, map_location_dot, map_location, map_pin, marker, mars,
        mars_and_venus, mars_and_venus_burst, mars_double, mars_stroke, mars_stroke_right, mars_stroke_up,
        martini_glass_citrus, martini_glass_empty, martini_glass, mask_face, mask, mask_ventilator, masks_theater,
        mattress_pillow, maximize, medal, memory, menorah, mercury, message, meteor, microchip, microphone,
        microphone_lines, microphone_lines_slash, microphone_slash, microscope, minus, mill_sign, minimize, mitten,
        mobile, mobile_button, mobile_retro, money_bill, mobile_screen_button, mobile_screen, money_bill_1,
        money_bill_1_wave, money_bill_wave, money_bill_transfer, money_bill_trend_up, money_check, money_bill_wheat,
        money_bills, money_check_dollar, monument, moon, mortar_pestle, mosque, mosquito, mosquito_net, motorcycle,
        mound, mountain_city, mountain, mountain_sun, mug_saucer, mug_hot, music, n, naira_sign, network_wired, neuter,
        newspaper, not_equal, notdef, note_sticky, notes_medical, o, object_group, object_ungroup, oil_can, oil_well, om, otter,
        outdent, p, pager, paint_roller, paintbrush, palette, pallet, panorama, paper_plane, paperclip, parachute_box,
        paragraph, passport, paste, pause, paw, peace, pen, pen_clip, pen_fancy, pen_nib, pen_ruler, pen_to_square,
        pencil, people_arrows, people_carry_box, people_group, people_line, people_pulling, people_robbery,
        people_roof, pepper_hot, percent, person_arrow_down_to_line, person_arrow_up_from_line, person_biking,
        person_booth, person, person_breastfeeding, person_burst, person_cane, person_chalkboard, person_circle_check,
        person_circle_exclamation, person_circle_minus, person_circle_plus, person_circle_question, person_circle_xmark,
        person_digging, person_dots_from_line, person_dress_burst, person_dress, person_drowning, person_falling_burst,
        person_falling, person_half_dress, person_harassing, person_hiking, person_military_pointing,
        person_military_rifle, person_military_to_person, person_praying, person_pregnant, person_rays, person_rifle,
        person_running, person_shelter, person_skating, person_skiing_nordic, person_skiing, person_snowboarding,
        person_swimming, person_through_window, person_walking_arrow_loop_left, person_walking_arrow_right,
        person_walking_dashed_line_arrow_right, person_walking_luggage, person_walking, person_walking_with_cane,
        peseta_sign, peso_sign, phone_flip, phone, phone_slash, phone_volume, photo_film, piggy_bank, pills,
        pizza_slice, place_of_worship, plane, plane_arrival, plane_circle_check, plane_circle_exclamation,
        plane_circle_xmark, plane_departure, plane_lock, plane_slash, plane_up, plant_wilt, plate_wheat, play, plug,
        plus, plug_circle_bolt, plug_circle_check, plug_circle_exclamation, plug_circle_minus, plug_circle_plus,
        plug_circle_xmark, plus_minus, podcast, poo, poo_storm, poop, power_off, prescription, prescription_bottle,
        prescription_bottle_medical, print, pump_medical, pump_soap, puzzle_piece, q, qrcode, question, quote_left,
        quote_right, r, radiation, radio, rainbow, ranking_star, receipt, record_vinyl, rectangle_ad, rectangle_list,
        rectangle_xmark, recycle, registered, reply, repeat, reply_all, republican, restroom, retweet, ribbon,
        right_from_bracket, right_left, right_long, right_to_bracket, ring, road, road_barrier, road_bridge,
        road_circle_check, road_circle_exclamation, road_circle_xmark, road_lock, road_spikes, robot, rocket, rotate,
        rotate_left, rotate_right, route, rss, ruble_sign, rug, ruler, ruler_combined, ruler_horizontal, ruler_vertical,
        rupee_sign, rupiah_sign, s, sack_dollar, sack_xmark, sailboat, satellite, satellite_dish, school,
        scale_balanced, scale_unbalanced_flip, scale_unbalanced, school_circle_check, school_circle_exclamation,
        school_circle_xmark, school_flag, school_lock, scissors, screwdriver, screwdriver_wrench, scroll, scroll_torah,
        sd_card, section, seedling, server, shapes, share, share_from_square, share_nodes, sheet_plastic, shekel_sign,
        shield, shield_cat, shield_dog, shield_halved, shield_heart, shield_virus, ship, shirt,
        shoe_prints, shop, shop_lock, shop_slash, shower, shrimp, shuffle, shuttle_space, sign_hanging, signal,
        signature, signs_post, sim_card, sink, sitemap, skull, skull_crossbones, slash, sleigh, sliders, smog, smoking,
        snowflake, snowman, snowplow, soap, socks, solar_panel, sort, sort_down, sort_up, spa, spaghetti_monster_flying,
        spell_check, spider, spinner, splotch, spoon, spray_can, square, square_full, spray_can_sparkles,
        square_arrow_up_right, square_caret_down, square_caret_left, square_caret_right, square_caret_up, square_check,
        square_envelope, square_h, square_minus, square_nfi, square_parking, square_pen, square_person_confined,
        square_phone_flip, square_phone, square_plus, square_poll_horizontal, square_poll_vertical,
        square_root_variable, square_rss, square_share_nodes, square_up_right, square_virus, square_xmark,
        staff_snake, stairs, stamp, stapler, star, star_and_crescent, star_half, star_half_stroke, star_of_david,
        star_of_life, sterling_sign, stethoscope, stop, stopwatch, stopwatch_20, store, store_slash, street_view,
        strikethrough, stroopwafel, subscript, suitcase_medical, suitcase, sun_plant_wilt, suitcase_rolling, sun,
        superscript, swatchbook, synagogue, syringe, t, table, table_cells_row_lock, table_cells_row_unlock, table_cells, table_cells_column_lock, table_cells_large, table_columns,
        table_list, table_tennis_paddle_ball, tablet, tablet_button, tablet_screen_button, tablets, tachograph_digital,
        tag, tags, tape, tarp, tarp_droplet, taxi, teeth, teeth_open, temperature_arrow_down, temperature_arrow_up,
        temperature_empty, temperature_full, temperature_half, temperature_high, temperature_low, temperature_quarter,
        temperature_three_quarters, tenge_sign, tent_arrow_down_to_line, tent_arrow_left_right, tent_arrow_turn_left,
        tent_arrows_down, tent, tents, terminal, text_height, text_slash, text_width, thermometer, thermometer_quarter,
        thermometer_three_quarters, thumbs_down, thumbs_up, thumbtack, thumbtack_slash, ticket, ticket_simple, timeline, toggle_off,
        toggle_on, toilet, toilet_paper, toilet_paper_slash, toilet_portable, toilets_portable, toolbox, tooth,
        torii_gate, tornado, tower_broadcast, tower_cell, tower_observation, tractor, trademark, traffic_light,
        trailer, train, train_subway, train_tram,  transgender,trash, trash_can, trash_arrow_up, trash_can_arrow_up,
        tree_city, triangle_exclamation, tree, trophy, truck, truck_plane, truck_ramp_box, trowel, trowel_bricks,
        truck_arrow_right, truck_droplet, truck_fast, truck_field, truck_field_un, truck_front, truck_medical,
        truck_monster, truck_moving, truck_pickup, tty, turkish_lira_sign, turn_down, turn_up, tv, u, umbrella,
        umbrella_beach, underline, universal_access, unlock, unlock_keyhole, up_down, up_down_left_right, up_long,
        up_right_and_down_left_from_center, up_right_from_square, upload, user, user_astronaut, user_check, user_clock,
        user_doctor, user_gear, user_graduate, user_group, user_injured, user_large, user_large_slash, user_lock,
        user_minus, user_ninja, user_nurse, user_pen, user_plus, user_secret, user_shield, user_slash, user_tag,
        user_tie, users, user_xmark, users_between_lines, users_gear, users_line, users_rays, users_rectangle,
        users_slash, users_viewfinder, utensils, v, van_shuttle, vault, vector_square, venus, venus_double, venus_mars,
        vest, vest_patches, vial, vial_circle_check, vial_virus, vials, video, video_slash, virus, virus_covid,
        virus_covid_slash, virus_slash, viruses, vihara, voicemail, volcano, volleyball, volume_high, volume_low,
        volume_off, volume_xmark, vr_cardboard, w, walkie_talkie, wallet, wand_magic, wand_magic_sparkles,
        wand_sparkles, water, water_ladder, warehouse, wave_square, web_awesome, weight_hanging, weight_scale,
        wheat_awn_circle_exclamation, wheat_awn, wheelchair, wheelchair_move, whiskey_glass, wifi, wind,
        window_maximize, window_minimize, window_restore, wine_bottle, wine_glass, wine_glass_empty, won_sign, worm,
        wrench, x, xmark, xmarks_lines, x_ray, y, yen_sign, yin_yang, z;

        @Override
        public String getPrefix() {
            return "fa-solid";
        }

        @Override
        public String getIconName() {
            if (this.name().startsWith("_"))
                return "fa-" + name().substring(1);
            else
                return "fa-" + name();
        }
    }

    /**
     * All icons of style 'regular' available for free in Font Awesome.
     */
    public enum FontAwesome6Regular implements FontAwesome6Graphic {
        address_book, address_card, bell, bell_slash, bookmark, building, calendar, calendar_check, calendar_days,
        calendar_minus, calendar_plus, calendar_xmark, chart_bar, chart_simple, check_to_slot, chess_bishop, chess_king,
        chess_knight, chess_pawn, chess_queen, chess_rook, circle, circle_check, circle_dot, circle_down, circle_left,
        circle_pause, circle_play, circle_question, circle_right, circle_stop, circle_up, circle_user, circle_xmark,
        clipboard, clock, clone, closed_captioning, comment, comment_dots, comments, compass, copy, copyright,
        credit_card, envelope, envelope_open, eye, eye_slash, face_angry, face_dizzy, face_flushed, face_frown_open,
        face_frown, face_grimace, face_grin_beam, face_grin_beam_sweat, face_grin_hearts, face_grin, face_grin_squint,
        face_grin_squint_tears, face_grin_stars, face_grin_tears, face_grin_tongue, face_grin_tongue_squint,
        face_grin_tongue_wink, face_grin_wide, face_grin_wink, face_kiss_beam, face_kiss, face_kiss_wink_heart,
        face_laugh_beam, face_laugh, face_laugh_squint, face_laugh_wink, face_meh_blank, face_meh, face_rolling_eyes,
        face_sad_cry, face_sad_tear, face_smile_beam, face_smile, face_smile_wink, face_surprise, face_tired, file,
        file_audio, file_code, file_excel, file_image, file_lines, file_pdf, file_powerpoint, file_video, file_word,
        file_zipper, flag, floppy_disk, folder_closed, folder, folder_open, font_awesome, futbol, gem, hand,
        hand_back_fist, hand_lizard, hand_peace, hand_point_down, hand_point_left, hand_point_right, hand_point_up,
        hand_pointer, hand_scissors, hand_spock, handshake, hard_drive, heart, hospital, hourglass_half, hourglass, id_badge, id_card,
        image, images, keyboard, lemon, life_ring, lightbulb, list_check, map, message, money_bill_1, moon, newspaper,
        note_sticky, object_group, object_ungroup, paper_plane, paste, pen_to_square, rectangle_list, rectangle_xmark,
        registered, share_from_square, snowflake, square, square_caret_down, square_caret_left, square_caret_right,
        square_caret_up, square_check, square_full, square_minus, square_plus, star, star_half, star_half_stroke, sun,
        thumbs_down, thumbs_up, trash_can, user, window_maximize, window_minimize, window_restore;

        @Override
        public String getPrefix() {
            return "fa-regular";
        }

        @Override
        public String getIconName() {
            return "fa-" + name();
        }
    }

    /**
     * All icons of style 'brand' available for free in Font Awesome.
     */
    public enum FontAwesome6Brand implements FontAwesome6Graphic {
        _42_group, _500px, accessible_icon, accusoft, adn, adversal, affiliatetheme, airbnb, algolia, alipay, amazon,
        amazon_pay, amilia, android, angellist, angrycreative, angular, app_store, app_store_ios, apper, apple,
        apple_pay, artstation, asymmetrik, atlassian, audible, autoprefixer, avianex, aviato, aws, bandcamp, battle_net,
        behance, bilibili, bimobject, bitbucket, bitcoin, bity, black_tie, blackberry, blogger,
        blogger_b, bluesky, bluetooth, bluetooth_b, bootstrap, bots, brave, brave_reverse, btc, buffer, buromobelexperte, buy_n_large, buysellads,
        canadian_maple_leaf, cc_amazon_pay, cc_amex, cc_apple_pay, cc_diners_club, cc_discover, cc_jcb, cc_mastercard,
        cc_paypal, cc_stripe, cc_visa, centercode, centos, chrome, chromecast, cloudflare, cloudscale, cloudsmith,
        cloudversify, cmplid, codepen, codiepie, confluence, connectdevelop, contao, cotton_bureau, cpanel,
        creative_commons, creative_commons_by, creative_commons_nc, creative_commons_nc_eu, creative_commons_nc_jp,
        creative_commons_nd, creative_commons_pd, creative_commons_pd_alt, creative_commons_remix, creative_commons_sa,
        creative_commons_sampling, creative_commons_sampling_plus, creative_commons_share, creative_commons_zero,
        critical_role, css3, css3_alt, cuttlefish, d_and_d, d_and_d_beyond, dailymotion, dart_lang, dashcube, debian, deezer, delicious,
        deploydog, deskpro, dev, deviantart, dhl, diaspora, digg, digital_ocean, discord, discourse, dochub, docker,
        draft2digital, dribbble, dropbox, drupal, dyalog, earlybirds, ebay, edge, edge_legacy,
        elementor, ello, ember, empire, envira, erlang, ethereum, etsy, evernote, expeditedssl, facebook, facebook_f,
        facebook_messenger, fantasy_flight_games, fedex, fedora, figma, firefox, firefox_browser,
        first_order, first_order_alt, firstdraft, flickr, flipboard, flutter, fly, font_awesome, fonticons, fonticons_fi,
        fort_awesome, fort_awesome_alt, forumbee, foursquare, free_code_camp, freebsd, fulcrum, galactic_republic,
        galactic_senate, get_pocket, gg, gg_circle, git, git_alt, github, github_alt,
        gitkraken, gitlab, gitter, glide, glide_g, gofore, golang, goodreads, goodreads_g, google, google_drive,
        google_pay, google_play, google_plus, google_plus_g, google_scholar, google_wallet, gratipay, grav,
        gripfire, grunt, guilded, gulp, hacker_news, hackerrank, hashnode, hips, hire_a_helper,
        hive, hooli, hornbill, hotjar, houzz, html5, hubspot, ideal, imdb, instagram, instalod,
        intercom, internet_explorer, invision, ioxhost, itch_io, itunes, itunes_note, java, jedi_order, jenkins, jira,
        joget, joomla, js, jsfiddle, jxl, kaggle, keybase, keycdn, kickstarter, kickstarter_k, korvue, laravel,
        lastfm, leanpub, less, letterboxd, line, linkedin, linkedin_in, linode, linux, lyft, magento, mailchimp,
        mandalorian, markdown, mastodon, maxcdn, mdb, medapps, medium, medrt, meetup, megaport, mendeley, meta, microblog,
        microsoft, mintbit, mix, mixcloud, mixer, mizuni, modx, monero, napster, neos, nfc_directional, nfc_symbol, nimblr,
        node, node_js, npm, ns8, nutritionix, octopus_deploy, odnoklassniki, odysee, old_republic,
        opencart, openid, opensuse, opera, optin_monster, orcid, osi, padlet, page4, pagelines, palfed, patreon, paypal, perbyte,
        periscope, phabricator, phoenix_framework, phoenix_squadron, php, pied_piper, pied_piper_alt, pied_piper_hat,
        pied_piper_pp, pinterest, pinterest_p, pix, pixiv, playstation, product_hunt,
        pushed, python, qq, quinscape, quora, r_project, raspberry_pi, ravelry, react, reacteurope, readme, rebel,
        red_river, reddit, reddit_alien, redhat, renren, replyd, researchgate, resolving, rev,
        rocketchat, rockrms, rust, safari, salesforce, sass, schlix, screenpal, scribd, searchengin, sellcast, sellsy,
        servicestack, shirtsinbulk, shoelace, shopify, shopware, signal_messenger, simplybuilt, sistrix, sith, sitrox, sketch, skyatlas, skype,
        slack, slideshare, snapchat, soundcloud, sourcetree, space_awesome, speakap, speaker_deck, spotify,
        square_behance, square_dribbble, square_facebook, square_font_awesome, square_font_awesome_stroke, square_git,
        square_github, square_gitlab, square_google_plus, square_hacker_news, square_instagram, square_js, square_lastfm,
        square_letterboxd, square_odnoklassniki, square_pied_piper, square_pinterest, square_reddit, square_snapchat,
        square_steam, square_threads, square_tumblr, square_twitter, square_upwork, square_viadeo, square_vimeo, square_web_awesome, square_web_awesome_stroke, square_whatsapp,
        square_x_twitter, square_xing, square_youtube, squarespace, stack_exchange, stack_overflow, stackpath,
        staylinked, steam, steam_symbol, sticker_mule, strava, stripe, stripe_s, stubber, studiovinari,
        stumbleupon, stumbleupon_circle, superpowers, supple, suse, swift, symfony, teamspeak, telegram, tencent_weibo,
        the_red_yeti, themeco, themeisle, think_peaks, threads, tiktok, trade_federation, trello, tumblr, twitch,
        twitter, typo3, uber, ubuntu, uikit, umbraco, uncharted, uniregistry, unity, unsplash, untappd,
        ups, upwork, usb, usps, ussunnah, vaadin, viacoin, viadeo, viber, vimeo, vimeo_v, vine, vk,
        vnv, vuejs, watchman_monitoring, waze, web_awesome, webflow, weebly, weibo, weixin, whatsapp, whmcs, wikipedia_w,
        windows, wirsindhandwerk, wix, wizards_of_the_coast, wodu, wolf_pack_battalion, wordpress, wordpress_simple,
        wpbeginner, wpexplorer, wpforms, wpressr, x_twitter, xbox, xing, y_combinator, yahoo, yammer, yandex,
        yandex_international, yarn, yelp, yoast, youtube, zhihu;

        @Override
        public String getPrefix() {
            return "fa-brands";
        }

        @Override
        public String getIconName() {
            if (this.name().startsWith("_"))
                return "fa-" + name().substring(1);
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
    public static FontAwesome6IconTypeBuilder on(final FontAwesome6Graphic fontAwesomeGraphic) {
        return new FontAwesome6IconTypeBuilder(fontAwesomeGraphic);
    }

    /**
     * Icon used in the builder.
     */
    private final FontAwesome6Graphic fontAwesomeGraphic;
    /**
     * rotation to apply to the icon (default none).
     */
    private       Rotation            rotation;
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
    private FontAwesome6IconTypeBuilder(final FontAwesome6Graphic fontAwesomeGraphic) {
        this.fontAwesomeGraphic = fontAwesomeGraphic;
    }

    /**
     * @return build the icon
     */
    public FontAwesome6IconType build() {
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

        return new FontAwesome6IconType(styles.toArray(new String[0]));
    }

    /**
     * @param rotation rotation to apply to the icon
     * @return the builder
     */
    public FontAwesome6IconTypeBuilder rotate(final Rotation rotation) {
        this.rotation = rotation;
        return this;
    }

    /**
     * @param size size to apply to the icon
     * @return the builder
     */
    public FontAwesome6IconTypeBuilder size(final Size size) {
        this.size = size;
        return this;
    }

    /**
     * make the icon spin
     *
     * @return the builder
     */
    public FontAwesome6IconTypeBuilder spin() {
        this.spin = true;
        return this;
    }

    /**
     * make the icon fixed width
     *
     * @return the builder
     */
    public FontAwesome6IconTypeBuilder fixedWidth() {
        this.fixedWidth = true;
        return this;
    }

    /**
     * An alias for {@link #fixedWidth()}
     *
     * @return the builder
     */
    public FontAwesome6IconTypeBuilder fw() {
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
