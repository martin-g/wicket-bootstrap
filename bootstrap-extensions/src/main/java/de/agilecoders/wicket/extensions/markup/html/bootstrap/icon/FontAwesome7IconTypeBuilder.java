package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>Important</strong>: To use font-awesome 6.x you need to declare the Maven/Gradle dependency
 * in your application pom.xml/build.gradle, for example:
 * <br/>
 *  &lt;dependency&gt;<br/>
 *      &lt;groupId&gt;org.webjars&lt;/groupId&gt;<br/>
 *      &lt;artifactId&gt;font-awesome&lt;/artifactId&gt;<br/>
 *      &lt;version&gt;6.2.0&lt;/version&gt;<br/>
 *  &lt;/dependency&gt;<br/>
 *
 * A builder to build {@link FontAwesome7IconType} <br />
 * <br />
 * <b>Examples :</b>
 * <ul>
 * <li>
 * To rotate an icon 90° :
 * <code>	FontAwesome7IconTypeBuilder.on(FontAwesome7Graphic.key).rotate(Rotation.rotate_90).build()</code></li>
 * <li>
 * To spin an icon : <code>	FontAwesome7IconTypeBuilder.on(FontAwesome7Graphic.key).spin().build()</code></li>
 * <li>To resize an icon 4 times :
 * <code>	FontAwesome7IconTypeBuilder.on(FontAwesome7Graphic.key).size(Size.four).build()</code></li>
 * <li>
 * All together :
 * <code>FontAwesome7IconTypeBuilder.on(FontAwesome7Graphic.key).size(Size.four).spin().rotate(Rotation.rotate_90).build()</code>
 * </li>
 * </ul>
 */
public class FontAwesome7IconTypeBuilder {

    public interface FontAwesome7Graphic {
        String getPrefix();

        String getIconName();
    }

    /**
     * All icons of style 'solid' available for free in Font Awesome.
     */
    public enum FontAwesome7Solid implements FontAwesome7Graphic {
        _0, _1, _2, _3, _4, _5, _6, _7, _8, _9, _try, a, ad, add, address_book, address_card, adjust, air_freshener, alarm_clock, align_center, align_justify, align_left,
        align_right, allergies, ambulance, american_sign_language_interpreting, anchor_circle_check, anchor_circle_exclamation, anchor_circle_xmark, anchor_lock, anchor,
        angle_double_down, angle_double_left, angle_double_right, angle_double_up, angle_down, angle_left, angle_right, angle_up, angles_down, angles_left, angles_right, angles_up, angry, ankh,
        apple_alt, apple_whole, area_chart, archive, archway, arrow_down_1_9, arrow_down_9_1, arrow_down_a_z, arrow_down_long, arrow_alt_circle_down, arrow_alt_circle_left, arrow_alt_circle_right, arrow_alt_circle_up, arrow_circle_down,
        arrow_circle_left, arrow_circle_right, arrow_circle_up, arrow_down, arrow_down_short_wide,
        arrow_down_up_across_line, arrow_down_up_lock, arrow_down_wide_short, arrow_down_z_a, arrow_left_long,
        arrow_left_rotate, arrow_left, arrow_pointer, arrow_right_arrow_left, arrow_right_from_bracket, arrow_right_from_file, arrow_right_long, arrow_right_rotate, arrow_right,
        arrow_right_to_bracket, arrow_right_to_city, arrow_right_to_file, arrow_rotate_back, arrow_rotate_backward, arrow_rotate_forward, arrow_rotate_left, arrow_rotate_right, arrow_trend_down,
        arrow_trend_up, arrow_turn_down, arrow_turn_up, arrow_up_1_9, arrow_up_9_1, arrow_up_a_z, arrow_up_from_bracket,
        arrow_up_from_ground_water, arrow_up_from_water_pump, arrow_up_long, arrow_up_right_dots,
        arrow_up_right_from_square, arrow_up, arrow_up_short_wide, arrow_up_wide_short, arrow_up_z_a,
        arrows_alt_h, arrows_alt, arrows_alt_v, arrows_down_to_line, arrows_down_to_people, arrows_h, arrows_left_right, arrows_left_right_to_line, arrows_rotate,
        arrows, arrows_spin, arrows_split_up_and_left, arrows_to_circle, arrows_to_dot, arrows_to_eye, arrows_turn_right,
        arrows_turn_to_dots, arrows_up_down_left_right, arrows_up_down, arrows_up_to_line, arrows_v, asl_interpreting, assistive_listening_systems, asterisk, at, atlas, atom,
        audio_description, austral_sign, automobile, award, b, baby, baby_carriage, backspace, backward_fast, backward, backward_step, bacon,
        bacteria, bacterium, bag_shopping, bahai, baht_sign, balance_scale_left, balance_scale_right, balance_scale, ban, ban_smoking, band_aid, bandage, bangladeshi_taka_sign, bank, bar_chart, barcode, bars_progress, bars,
        bars_staggered, baseball_ball, baseball_bat_ball, baseball, basket_shopping, basketball_ball, basketball, bath, bathtub, battery_0, battery_2, battery_3, battery_4, battery_5, battery_car, battery_empty, battery_full,
        battery_half, battery_quarter, battery, battery_three_quarters, bed_pulse, bed, beer_mug_empty, beer, bell_concierge, bell,
        bell_slash, bezier_curve, bible, bicycle, biking, binoculars, biohazard, birthday_cake, bitcoin_sign, blender, blackboard, blender_phone, blind, blog,
        bold, bolt_lightning, bolt, bomb, bone, bong, book_reader, book, book_atlas, book_bible, book_bookmark, book_dead, book_journal_whills,
        book_medical, book_open_reader, book_open, book_quran, book_skull, book_tanakh, bookmark, border_all, border_none,
        border_style, border_top_left, bore_hole, bottle_droplet, bottle_water, bowl_food, bowl_rice, bowling_ball, box_archive, box,
        box_open, box_tissue, boxes_alt, boxes_packing, boxes, boxes_stacked, braille, brain, brazilian_real_sign, bread_slice, briefcase,
        bridge_circle_check, bridge_circle_exclamation, bridge_circle_xmark, bridge_lock, bridge, bridge_water,
        briefcase_clock, briefcase_medical, broadcast_tower, broom_ball, broom, brush, bucket, bug, bug_slash, bugs, building_circle_arrow_right,
        building_circle_check, building_circle_exclamation, building_circle_xmark, building_columns, building_flag,
        building_lock, building_ngo, building, building_shield, building_un, building_user, building_wheat, bullhorn,
        bullseye, burger, burn, burst, bus_alt, bus, bus_side, bus_simple, business_time, c, cab, cable_car, cake_candles, cake, calculator, calendar, calendar_alt, calendar_check,
        calendar_day, calendar_days, calendar_minus, calendar_plus, calendar_times, calendar_week, calendar_xmark, camera, camera_alt, camera_retro,
        camera_rotate, campground, cancel, candy_cane, cannabis, capsules, car, car_alt, car_battery, car_burst, car_crash, car_on,
        car_rear, car_side, car_tunnel, caravan, caret_down, caret_left, caret_right, caret_square_down, caret_square_left, caret_square_right, caret_square_up, caret_up, carriage_baby, carrot, cart_arrow_down,
        cart_flatbed, cart_flatbed_suitcase, cart_plus, cart_shopping, cash_register, cat, cedi_sign, cent_sign,
        certificate, chain_broken, chain, chain_slash, chair, chalkboard, chalkboard_teacher, chalkboard_user, champagne_glasses, charging_station, chart_area, chart_bar,
        chart_column, chart_diagram, chart_gantt, chart_line, chart_pie, check, chart_simple, check_circle, check_double, check_square, check_to_slot, cheese,
        chess, chess_bishop, chess_board, chess_king, chess_knight, chess_pawn, chess_queen, chess_rook, chevron_circle_down, chevron_circle_left, chevron_circle_right, chevron_circle_up, chevron_down,
        chevron_left, chevron_right, chevron_up, child_combatant, child_rifle, child, child_dress, child_reaching, children, church,
        circle, circle_arrow_down, circle_arrow_left, circle_arrow_right, circle_arrow_up, circle_check,
        circle_chevron_down, circle_chevron_left, circle_chevron_right, circle_chevron_up, circle_dollar_to_slot,
        circle_dot, circle_down, circle_exclamation, circle_h, circle_half_stroke, circle_info, circle_left,
        circle_minus, circle_nodes, circle_notch, circle_pause, circle_play, circle_plus,circle_question,
        circle_radiation, circle_right, circle_stop, circle_up, circle_user, circle_xmark, city, clapperboard,
        clipboard, clinic_medical, clipboard_check, clipboard_list, clipboard_question, clipboard_user, clock, clock_four, clock_rotate_left, clone,
        close, closed_captioning, cloud, cloud_arrow_down, cloud_arrow_up, cloud_bolt, cloud_download_alt, cloud_download, cloud_meatball, cloud_moon,
        cloud_moon_rain, cloud_rain, cloud_showers_heavy, cloud_showers_water, cloud_sun, cloud_sun_rain, cloud_upload_alt, cloud_upload, clover, code,
        cny, cocktail, code_branch, code_commit, code_compare, code_fork, code_merge, code_pull_request, coffee, cog, cogs, coins,  colon_sign, comment,
        columns, comment_alt, comment_dollar, comment_dots, comment_medical, comment_nodes, comment_slash, comment_sms, comments, commenting, comments_dollar,
        compact_disc, compass, compass_drafting, compress_alt, compress_arrows_alt, compress, computer, computer_mouse, cookie, concierge_bell, contact_book, contact_card, cookie_bite, copy,
        copyright, couch, cow, credit_card_alt, credit_card, crop_alt, crop, crop_simple, cross, crosshairs, crow, crown, crutch, cruzeiro_sign,
        cube, cubes, cubes_stacked, cut, cutlery, d, dashboard, database, deaf, deafness, dedent, delete_left, democrat, desktop_alt, desktop, dharmachakra, diagnoses, diagram_next,
        diagram_predecessor, diagram_project, diagram_successor, diamond, diamond_turn_right, dice, dice_d20, dice_d6,
        dice_five, dice_four, dice_one, dice_six, dice_three, dice_two, digging, digital_tachograph, directions, disease, display, divide, dizzy, dna, dog, dollar, dollar_sign,
        dolly_box, dolly_flatbed, dolly, donate, dong_sign, door_closed, door_open, dot_circle, dove, down_left_and_up_right_to_center, down_long, download, drafting_compass, dragon,
        draw_polygon, drivers_license, droplet, droplet_slash, drum, drum_steelpan, drumstick_bite, dumbbell, dumpster, dumpster_fire,
        dungeon, e, ear_deaf, ear_listen, earth_africa, earth_america, earth_americas, earth_asia, earth_europe, earth_oceania, earth, edit, egg,
        eject, elevator, ellipsis_h, ellipsis, ellipsis_v, ellipsis_vertical, envelope_circle_check, envelope, envelope_square, envelopes_bulk, envelope_open,
        envelope_open_text, equals, eraser, ethernet, eur, euro, euro_sign, exchange_alt, exchange, exclamation_circle, exclamation, exclamation_triangle, expand_alt, expand_arrows_alt, expand, explosion, eye, external_link_alt, external_link, external_link_square_alt, external_link_square, eye_dropper_empty, eye_dropper,
        eye_low_vision, eye_slash, eyedropper, f, face_angry, face_dizzy, face_flushed, face_frown_open, face_frown, face_grimace,
        face_grin_beam, face_grin_beam_sweat, face_grin_hearts, face_grin, face_grin_squint, face_grin_squint_tears,
        face_grin_stars, face_grin_tears, face_grin_tongue, face_grin_tongue_squint, face_kiss,  face_grin_tongue_wink,
        face_grin_wide, face_grin_wink, face_kiss_beam, face_kiss_wink_heart, face_laugh_beam, face_laugh,
        face_laugh_squint, face_laugh_wink, face_meh_blank, face_meh, face_rolling_eyes, face_sad_cry, face_sad_tear,
        face_smile_beam, face_smile, face_smile_wink, face_surprise, face_tired, fan, faucet, fast_backward, fast_forward, faucet_drip, fax, feather,
        feather_alt, feather_pointed, feed, female, ferry, fighter_jet, file_alt, file_archive, file_arrow_down, file_arrow_up, file, file_audio, file_circle_check,
        file_circle_exclamation, file_circle_minus, file_circle_plus, file_circle_question, file_circle_xmark,
        file_clipboard, file_code, file_contract, file_csv, file_download, file_edit, file_excel, file_export, file_fragment, file_half_dashed, file_image, file_import, file_invoice,
        file_invoice_dollar, file_lines, file_medical_alt, file_medical, file_pdf, file_pen, file_powerpoint, file_prescription,
        file_shield, file_signature, file_text, file_upload, file_video, file_waveform, file_word, file_zipper, fill, fill_drip, film_alt, film, filter,
        film_simple, filter_circle_dollar, filter_circle_xmark, fingerprint, fire, fire_alt, fire_burner, fire_extinguisher, fire_flame_curved,
        fire_flame_simple, fish, first_aid, fish_fins, flag, fist_raised, flag_checkered, flag_usa, flask, flask_vial, floppy_disk, florin_sign,
        flushed, folder_blank, folder_closed, folder, folder_minus, folder_open, folder_plus, font, folder_tree, font_awesome_flag, font_awesome_logo_full, font_awesome, football_ball, football,
        forward, forward_fast, forward_step, franc_sign, frog, frown_open, frown, funnel_dollar, futbol_ball, futbol, g, gamepad, gas_pump, gauge_med, gauge, gauge_high,
        gauge_simple_med, gauge_simple, gauge_simple_high, gavel, gbp, gear, gears, gem, genderless, ghost, gift, gifts, glass_water,
        glass_cheers, glass_martini_alt, glass_martini, glass_water_droplet,  glass_whiskey, glasses, globe_africa, globe_americas, globe_asia, globe_europe, globe_oceania, globe, golf_ball, golf_ball_tee, gopuram, graduation_cap, greater_than, greater_than_equal,
        grip, grid_horizontal, grid_vertical, grimace, grin_alt, grin_beam, grin_beam_sweat, grin_hearts, grin, grin_squint, grin_squint_tears, grin_stars, grin_tears, grin_tongue, grin_tongue_squint, grin_tongue_wink, grin_wink, grip_horizontal, grip_lines, grip_lines_vertical, grip_vertical, group_arrows_rotate, guarani_sign, guitar, gun, h, h_square, hamburger, hammer,
        hamsa, hand_rock, hand, hand_back_fist, hand_dots, hand_fist,hand_holding, hand_holding_dollar, hand_holding_droplet,
        hand_holding_hand, hand_holding_heart, hand_holding_medical, hand_holding_usd, hand_holding_water, hand_lizard, hand_middle_finger, hand_paper, hand_peace,
        hand_point_down, hand_point_left, hand_point_right, hand_point_up, hand_pointer, hand_scissors, hand_sparkles,
        hand_spock, handcuffs, hands, hands_american_sign_language_interpreting, hands_asl_interpreting, hands_bound, hands_bubbles, hands_clapping,
        hands_helping, hands_holding_child, hands_holding_circle, hands_holding, hands_praying, handshake, hands_wash, handshake_alt, handshake_alt_slash, handshake_angle,
        handshake_simple, handshake_simple_slash, handshake_slash, hanukiah, hard_drive, hard_hat, hard_of_hearing, hashtag, hat_cowboy,
        hat_cowboy_side, hat_hard, hat_wizard, head_slide_cough, haykal, hdd, head_side_cough, head_side_cough_slash, head_side_mask,
        head_side_virus, header, heading, headphones_alt, headphones, headphones_simple, headset, heart, heart_broken, heart_circle_bolt, heart_circle_check,
        heart_circle_exclamation, heart_circle_minus, heart_circle_plus, heart_circle_xmark, heart_crack, heart_music_camera_bolt, heart_pulse,
        heartbeat, helicopter, helicopter_symbol, helmet_safety, helmet_un, heptagon, hexagon_nodes_bolt, hexagon_nodes, hexagon, highlighter, hiking, hill_avalanche, hill_rockslide, hippo,
        history, hockey_puck, holly_berry, horse, home_alt, home_lg_alt, home_lg, home, home_user, horse_head, hospital_alt, hospital, hospital_symbol, hospital_user, hospital_wide, hot_tub_person, hot_tub, hotdog, hotel, hourglass,
        hourglass_1, hourglass_2, hourglass_3, hourglass_empty, hourglass_end, hourglass_half, hourglass_start, house, house_chimney_crack, house_chimney_medical,
        house_chimney, house_chimney_user, house_chimney_window, house_circle_check, house_circle_exclamation,
        house_circle_xmark, house_crack, house_damage, house_fire, house_flag, house_flood_water_circle_arrow_right,
        house_flood_water, house_laptop, house_lock, house_medical_circle_check, house_medical_circle_exclamation,
        house_medical_circle_xmark, house_medical_flag, house_medical, house_signal, house_tsunami, house_user,
        hryvnia, hryvnia_sign, hurricane, i, i_cursor, ice_cream, icicles, icons, id_badge, id_card, id_card_alt, id_card_clip, igloo, image,
        ils, image_portrait, images, inbox, indent, indian_rupee, indian_rupee_sign, industry, infinity, info_circle, info, inr, institution, italic, j, jar, jar_wheat,
        jedi, jet_fighter, jet_fighter_up, joint, journal_whills, jpy, jug_detergent, k, kaaba, key, keyboard, khanda, kip_sign, kiss_beam, kiss, kiss_wink_heart, kit_medical,
        kitchen_set, kiwi_bird, krw, l, ladder_water, land_mine_on, landmark, landmark_alt, landmark_dome, landmark_flag, language, laptop, laptop_code,
        laptop_file, laptop_house, laptop_medical, lari_sign, laugh_beam, laugh, laugh_squint, laugh_wink, layer_group, leaf, left_long, left_right, legal, lemon, less_than,
        less_than_equal, level_down_alt, level_down, level_up_alt, level_up, life_ring, lightbulb, line_chart, lines_leaning, link, link_slash, lira_sign, list, list_1_2, list_alt, list_check, list_dots, list_numeric, list_ol,
        list_squares, list_ul, litecoin_sign, location_arrow, location_crosshairs, location_dot, location_pin_lock, location_pin,
        lock, location, lock_open, locust, long_arrow_alt_down, long_arrow_alt_left, long_arrow_alt_right, long_arrow_alt_up, long_arrow_down, long_arrow_left, long_arrow_right, long_arrow_up, low_vision, luggage_cart, lungs, lungs_virus, m, magic, magic_wand_sparkles, magnet, map, magnifying_glass_arrow_right,
        magnifying_glass_chart, magnifying_glass_dollar, magnifying_glass_location, magnifying_glass_minus,
        magnifying_glass_plus, magnifying_glass, mail_bulk, mail_forward, mail_reply_all, mail_reply, male, manat_sign, map_location_dot, map_location, map_marked_alt, map_marked, map_marker_alt, map_marker, map_pin, map_signs, marker, mars,
        mars_and_venus, mars_and_venus_burst, mars_double, mars_stroke, mars_stroke_h, mars_stroke_right, mars_stroke_up,
        mars_stroke_v, martini_glass_citrus, martini_glass_empty, martini_glass, mask_face, mask, mask_ventilator, masks_theater,
        mattress_pillow, maximize, medal, medkit, meh_blank, meh_rolling_eyes, meh, memory, menorah, mercury, message, meteor, microchip, microphone,
        microphone_alt, microphone_alt_slash, microphone_lines, microphone_lines_slash, microphone_slash, microscope, minus_circle, minus, mill_sign, minimize, minus_square, mitten,
        mobile, mobile_alt, mobile_android_alt, mobile_android, mobile_button, mobile_phone, mobile_retro, money_bill_alt, money_bill, mobile_screen_button, mobile_screen, mobile_vibrate, money_bill_1,
        money_bill_1_wave, money_bill_wave_alt, money_bill_wave, money_bill_transfer, money_bill_trend_up, money_check, money_bill_wheat,
        money_bills, money_check_alt, money_check_dollar, monument, moon, mortar_board, mortar_pestle, mosque, mosquito, mosquito_net, motorcycle,
        mound, mountain_city, mountain, mountain_sun, mug_saucer, mouse_pointer, mouse, mug_hot, multiply, museum, music, n, naira_sign, navicon, network_wired, neuter,
        newspaper, non_binary, not_equal, notdef, note_sticky, notes_medical, o, object_group, object_ungroup, octagon, oil_can, oil_well, om, otter,
        outdent, p, pager, paint_brush, paint_roller, paintbrush, palette, pallet, panorama, paper_plane, paperclip, parachute_box,
        paragraph, parking, passport, pastafarianism, paste, pause_circle, pause, paw, peace, pen, pen_alt, pen_clip, pen_fancy, pen_nib, pen_ruler, pen_square, pen_to_square,
        pencil_alt, pencil_ruler, pencil, pencil_square, pentagon, people_arrows_left_right, people_arrows, people_carry_box, people_carry, people_group, people_line, people_pulling, people_robbery,
        people_roof, pepper_hot, percent, percentage, person_arrow_down_to_line, person_arrow_up_from_line, person_biking,
        person_booth, person, person_breastfeeding, person_burst, person_cane, person_chalkboard, person_circle_check,
        person_circle_exclamation, person_circle_minus, person_circle_plus, person_circle_question, person_circle_xmark,
        person_digging, person_dots_from_line, person_dress_burst, person_dress, person_drowning, person_falling_burst,
        person_falling, person_half_dress, person_harassing, person_hiking, person_military_pointing,
        person_military_rifle, person_military_to_person, person_praying, person_pregnant, person_rays, person_rifle,
        person_running, person_shelter, person_skating, person_skiing_nordic, person_skiing, person_snowboarding,
        person_swimming, person_through_window, person_walking_arrow_loop_left, person_walking_arrow_right,
        person_walking_dashed_line_arrow_right, person_walking_luggage, person_walking, person_walking_with_cane,
        peseta_sign, peso_sign, phone_alt, phone_flip, phone, phone_slash, phone_square_alt, phone_square, phone_volume, photo_film, photo_video, pie_chart, piggy_bank, pills,
        ping_pong_paddle_ball, pizza_slice, place_of_worship, plane, plane_arrival, plane_circle_check, plane_circle_exclamation,
        plane_circle_xmark, plane_departure, plane_lock, plane_slash, plane_up, plant_wilt, plate_wheat, play_circle, play, plug,
        plus, plug_circle_bolt, plug_circle_check, plug_circle_exclamation, plug_circle_minus, plug_circle_plus,
        plug_circle_xmark, plus_circle, plus_minus, plus_square, podcast, poll_h, poll, poo_bolt, poo, poo_storm, poop, portrait, pound_sign, power_off, prescription, prescription_bottle,
        pray, praying_hands, prescription_bottle_alt, prescription_bottle_medical, print, procedures, project_diagram, pump_medical, pump_soap, puzzle_piece, q, qrcode, question_circle, question, quidditch_broom_ball, quidditch, quote_left_alt, quote_left,
        quote_right_alt, quote_right, quran, r, radiation_alt, radiation, radio, rainbow, random, ranking_star, receipt, record_vinyl, rectangle_ad, rectangle_list,
        rectangle_times, rectangle_xmark, recycle, redo_alt, redo, refresh, registered, reply, remove_format, remove, reorder, repeat, reply_all, republican, restroom, retweet, ribbon,
        right_from_bracket, right_left, right_long, right_to_bracket, ring, road, rmb, road_barrier, road_bridge,
        road_circle_check, road_circle_exclamation, road_circle_xmark, road_lock, road_spikes, robot, rocket, rotate,
        rod_asclepius, rod_snake, rotate_back, rotate_backward, rotate_forward, rotate_left, rotate_right, rouble, route, rss, rss_square, rub, ruble, ruble_sign, rug, ruler, ruler_combined, ruler_horizontal, ruler_vertical,
        running, rupee, rupee_sign, rupiah_sign, s, sack_dollar, sack_xmark, sad_cry, sad_tear, sailboat, satellite, satellite_dish, school,
        save, scale_balanced, scale_unbalanced_flip, scale_unbalanced, school_circle_check, school_circle_exclamation,
        school_circle_xmark, school_flag, school_lock, scissors, screwdriver, screwdriver_wrench, scroll, scroll_torah,
        sd_card, search_dollar, search_location, search_minus, search_plus, search, section, seedling, septagon, server, shapes, share, share_alt, share_alt_square, share_from_square, share_nodes, share_square, sheet_plastic, shekel, shekel_sign,
        sheqel, sheqel_sign, shield, shield_alt, shield_blank, shield_cat, shield_dog, shield_halved, shield_heart, shield_virus, ship, shipping_fast, shirt,
        shoe_prints, shop, shop_lock, shop_slash, shopping_bag, shopping_basket, shopping_cart, shower, shrimp, shuffle, shuttle_space, shuttle_van, sign_hanging, sign_in_alt, sign_in, sign_language, sign_out_alt, sign_out, sign, signal_5, signal_perfect, signal,
        signature, signing, signs_post, sim_card, single_quote_left, single_quote_right, sink, sitemap, skating, skiing_nordic, skiing, skull_crossbones, skull, slash, sleigh, sliders_h, sliders, smile_beam, smile, smile_wink, smog, smoking_ban, smoking,
        sms, snowboarding, snowflake, snowman, snowplow, soap, soccer_ball, socks, solar_panel, sort_numeric_asc, sort_numeric_desc, sort_numeric_down_alt, sort_numeric_down, sort_numeric_up_alt, sort_numeric_up, sort, sort_alpha_asc, sort_alpha_desc, sort_alpha_down_alt, sort_alpha_down, sort_alpha_up_alt, sort_alpha_up, sort_amount_asc, sort_amount_desc, sort_amount_down_alt, sort_amount_down, sort_amount_up_alt, sort_amount_up, sort_asc, sort_desc, sort_down, sort_up, spa, space_shuttle, spaghetti_monster_flying,
        spell_check, spider, spinner, spiral, splotch, spoon, spray_can, square, square_full, spray_can_sparkles,
        sprout, square_arrow_up_right, square_binary, square_caret_down, square_caret_left, square_caret_right, square_caret_up, square_check,
        square_envelope, square_h, square_minus, square_nfi, square_parking, square_pen, square_person_confined,
        square_phone_flip, square_phone, square_plus, square_poll_horizontal, square_poll_vertical,
        square_root_alt, square_root_variable, square_rss, square_share_nodes, square_up_right, square_virus, square_xmark,
        staff_aesculapius, staff_snake, stairs, stamp, stapler, star, star_and_crescent, star_half_alt, star_half, star_half_stroke, star_of_david,
        star_of_life, step_backward, step_forward, sterling_sign, stethoscope, sticky_note, stop_circle, stop, stopwatch, stopwatch_20, store_alt, store_alt_slash, store, store_slash, stream, street_view,
        strikethrough, stroopwafel, subscript, subtract, subway, suitcase_medical, suitcase, sun_plant_wilt, suitcase_rolling, sun,
        superscript, surprise, swatchbook, swimmer, swimming_pool, synagogue, sync_alt, sync, syringe, t, table, table_cells_row_lock, table_cells_row_unlock, table_cells, t_shirt, table_cells_column_lock, table_cells_large, table_columns,
        table_list, table_tennis_paddle_ball, tablet, table_tennis, tablet_alt, tablet_android, tablet_button, tablet_screen_button, tablets, tachograph_digital,
        tachometer_alt_average, tachometer_alt_fast, tachometer_alt, tachometer_average, tachometer_fast, tachometer, tag, tags, tanakh, tape, tarp, tarp_droplet, tasks_alt, tasks, taxi, teeth, teeth_open, teletype, television, temperature_0, temperature_1, temperature_2, temperature_3, temperature_4, temperature_5, temperature_6, temperature_arrow_down, temperature_arrow_up,
        temperature_down, temperature_empty, temperature_full, temperature_half, temperature_high, temperature_low, temperature_quarter,
        temperature_three_quarters, temperature_up, tenge, tenge_sign, tent_arrow_down_to_line, tent_arrow_left_right, tent_arrow_turn_left,
        tent_arrows_down, tent, tents, terminal, text_height, text_slash, text_width, thermometer, th_large, th_list, th, theater_masks, thermometer_0, thermometer_1, thermometer_2, thermometer_3, thermometer_4, thermometer_empty, thermometer_full, thermometer_half, thermometer_quarter,
        thermometer_three_quarters, thumb_tack, thumb_tack_slash, thumbs_down, thumbs_up, thumbtack, thumbtack_slash, thunderstorm, ticket_alt, ticket, ticket_simple, timeline, times_circle, times_rectangle, times, times_square, tint, tint_slash, tired, toggle_off,
        toggle_on, toilet, toilet_paper_alt, toilet_paper_blank, toilet_paper, toilet_paper_slash, toilet_portable, toilets_portable, toolbox, tools, tooth,
        torah, torii_gate, tornado, tower_broadcast, tower_cell, tower_observation, tractor, trademark, traffic_light,
        trailer, train, train_subway, train_tram,  tram, transgender_alt, transgender, trash_restore_alt, trash_restore, trash, trash_can, trash_alt, trash_arrow_up, trash_can_arrow_up,
        tree_city, triangle_circle_square, triangle_exclamation, tree, trophy, truck, truck_plane, truck_ramp_box, trowel, trowel_bricks,
        truck_arrow_right, truck_droplet, truck_fast, truck_field, truck_field_un, truck_front, truck_loading, truck_medical,
        truck_monster, truck_moving, truck_pickup, tshirt, tty, turkish_lira, turkish_lira_sign, turn_down, turn_up, tv_alt, tv, u, umbrella,
        umbrella_beach, underline, undo_alt, undo, universal_access, unlock, university, unlink, unlock_alt, unlock_keyhole, up_down, unsorted, up_down_left_right, up_long,
        up_right_and_down_left_from_center, up_right_from_square, upload, user, usd, user_alt, user_alt_slash, user_astronaut, user_check, user_circle, user_clock,
        user_cog, user_doctor, user_edit, user_friends, user_gear, user_graduate, user_group, user_injured, user_large, user_large_slash, user_lock,
        user_md, user_minus, user_ninja, user_nurse, user_pen, user_plus, user_secret, user_shield, user_slash, user_tag,
        user_tie, users, user_times, user_xmark, users_between_lines, users_cog, users_gear, users_line, users_rays, users_rectangle,
        users_slash, users_viewfinder, utensil_spoon, utensils, v, van_shuttle, vault, vector_square, venus, vcard, vector_polygon, venus_double, venus_mars,
        vest, vest_patches, vial, vial_circle_check, vial_virus, vials, video_camera, video, video_slash, virus, virus_covid,
        virus_covid_slash, virus_slash, viruses, vihara, voicemail, volcano, volleyball_ball, volleyball, volume_control_phone, volume_down, volume_high, volume_low,
        volume_mute, volume_off, volume_times, volume_up, volume_xmark, vote_yea, vr_cardboard, w, walkie_talkie, walking, wallet, wand_magic, wand_magic_sparkles,
        wand_sparkles, water, warning, water_ladder, warehouse, wave_square, web_awesome, weight_hanging, weight, weight_scale, wheat_alt,
        wheat_awn_circle_exclamation, wheat_awn, wheelchair, wheelchair_alt, wheelchair_move, whiskey_glass, wifi_3, wifi, wifi_strong, wind, window_close,
        window_maximize, window_minimize, window_restore, wine_bottle, wine_glass_alt, wine_glass, won, wine_glass_empty, won_sign, worm,
        wrench, x, xmark_circle, xmark, xmark_square, xmarks_lines, x_ray, y, yen, yen_sign, yin_yang, z, zap;

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
    public enum FontAwesome7Regular implements FontAwesome7Graphic {
        address_book, address_card, alarm_clock, angry, arrow_alt_circle_down, arrow_alt_circle_left, arrow_alt_circle_right, arrow_alt_circle_up, bar_chart, bell, bell_slash, bookmark, building, calendar, calendar_alt, calendar_check, calendar_days,
        calendar_minus, calendar_plus, calendar_times, calendar_xmark, camera_alt, camera, caret_square_down, caret_square_left, caret_square_right, caret_square_up, chart_bar, chart_simple, check_to_slot, check_circle, check_square, chess_bishop, chess_king,
        chess_knight, chess_pawn, chess_queen, chess_rook, circle, circle_check, circle_dot, circle_down, circle_left,
        circle_pause, circle_play, circle_question, circle_right, circle_stop, circle_up, circle_user, circle_xmark,
        clipboard, clock_four, clock, clone, closed_captioning, cloud, comment_alt, comment_dots, comment, commenting, comments, compass, contact_book, contact_card, copy, copyright,
        credit_card_alt, credit_card, dizzy, dot_circle, drivers_license, edit, envelope_open, envelope, eye, eye_slash, face_angry, face_dizzy, face_flushed, face_frown_open,
        face_frown, face_grimace, face_grin_beam, face_grin_beam_sweat, face_grin_hearts, face_grin, face_grin_squint,
        face_grin_squint_tears, face_grin_stars, face_grin_tears, face_grin_tongue, face_grin_tongue_squint,
        face_grin_tongue_wink, face_grin_wide, face_grin_wink, face_kiss_beam, face_kiss, face_kiss_wink_heart,
        face_laugh_beam, face_laugh, face_laugh_squint, face_laugh_wink, face_meh_blank, face_meh, face_rolling_eyes,
        face_sad_cry, face_sad_tear, face_smile_beam, face_smile, face_smile_wink, face_surprise, face_tired, file,
        file_alt, file_archive, file_audio, file_clipboard, file_code, file_excel, file_image, file_lines, file_pdf, file_powerpoint, file_text, file_video, file_word,
        file_zipper, flag, floppy_disk, flushed, folder_blank, folder_closed, folder, folder_open, font_awesome_flag, font_awesome_logo_full, font_awesome, frown_open, frown, futbol_ball, futbol, gem, hand,
        grimace, grin_alt, grin_beam, grin_beam_sweat, grin_hearts, grin, grin_squint, grin_squint_tears, grin_stars, grin_tears, grin_tongue, grin_tongue_squint, grin_tongue_wink, grin_wink, hand_back_fist, hand_lizard, hand_paper, hand_peace, hand_point_down, hand_point_left, hand_point_right, hand_point_up,
        hand_pointer, hand_rock, hand_scissors, hand_spock, handshake_alt, handshake, handshake_simple, hard_drive, hdd, headphones_alt, headphones, headphones_simple, heart, home_alt, home_lg_alt, home, hospital_alt, hospital, hospital_wide, hourglass_2, hourglass_empty, hourglass_half, hourglass, house, id_badge, id_card,
        image, images, keyboard, kiss_beam, kiss, kiss_wink_heart, laugh_beam, laugh, laugh_squint, laugh_wink, lemon, life_ring, lightbulb, list_alt, list_check, map, meh_blank, meh, meh_rolling_eyes, message, minus_square, money_bill_1, money_bill_alt, moon, newspaper,
        note_sticky, object_group, object_ungroup, paper_plane, paste, pause_circle, pen_to_square, play_circle, plus_square, question_circle, rectangle_list, rectangle_times, rectangle_xmark,
        registered, sad_cry, sad_tear, save, share_from_square, share_square, smile_beam, smile, smile_wink, snowflake, soccer_ball, square, square_caret_down, square_caret_left, square_caret_right,
        square_caret_up, square_check, square_full, square_minus, square_plus, star, star_half_alt, star_half, star_half_stroke, sticky_note, stop_circle, sun, surprise,
        thumbs_down, thumbs_up, times_circle, times_rectangle, tired, trash_alt, trash_can, truck, user_alt, user_circle, user_large, user, vcard, window_close, window_maximize, window_minimize, window_restore, xmark_circle;

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
    public enum FontAwesome7Brand implements FontAwesome7Graphic {
        _11ty, _42_group, _500px, accessible_icon, accusoft, adn, adversal, affiliatetheme, airbnb, algolia, alipay, amazon,
        amazon_pay, amilia, android, angellist, angrycreative, angular, app_store, app_store_ios, apper, apple,
        apple_pay, artstation, asymmetrik, atlassian, audible, autoprefixer, avianex, aviato, aws, bandcamp, battle_net,
        behance, behance_square, bilibili, bimobject, bitbucket, bitcoin, bity, black_tie, blackberry, blogger,
        blogger_b, bluesky, bluetooth, bluetooth_b, bootstrap, bots, brave, brave_reverse, btc, buffer, buromobelexperte, buy_n_large, buysellads,
        canadian_maple_leaf, cash_app, cc_amazon_pay, cc_amex, cc_apple_pay, cc_diners_club, cc_discover, cc_jcb, cc_mastercard,
        cc_paypal, cc_stripe, cc_visa, centercode, centos, chrome, chromecast, cloudflare, cloudscale, cloudsmith,
        cloudversify, cmplid, codepen, codiepie, confluence, connectdevelop, contao, cotton_bureau, cpanel,
        creative_commons, creative_commons_by, creative_commons_nc, creative_commons_nc_eu, creative_commons_nc_jp,
        creative_commons_nd, creative_commons_pd, creative_commons_pd_alt, creative_commons_remix, creative_commons_sa,
        creative_commons_sampling, creative_commons_sampling_plus, creative_commons_share, creative_commons_zero,
        critical_role, css, css3, css3_alt, cuttlefish, d_and_d, d_and_d_beyond, dailymotion, dart_lang, dashcube, debian, deezer, delicious,
        deploydog, deskpro, dev, deviantart, dhl, diaspora, digg, digital_ocean, discord, discourse, disqus, dochub, docker,
        draft2digital, dribbble, dribbble_square, dropbox, drupal, duolingo, dyalog, earlybirds, ebay, edge, edge_legacy,
        elementor, eleventy, ello, ember, empire, envira, erlang, ethereum, etsy, evernote, expeditedssl, facebook, facebook_f,
        facebook_messenger, facebook_square, fantasy_flight_games, fedex, fedora, figma, files_pinwheel, firefox, firefox_browser,
        first_order, first_order_alt, firstdraft, flickr, flipboard, flutter, fly, font_awesome, font_awesome_alt, font_awesome_flag, font_awesome_logo_full, fonticons, fonticons_fi,
        fort_awesome, fort_awesome_alt, forumbee, foursquare, free_code_camp, freebsd, fulcrum, galactic_republic,
        galactic_senate, get_pocket, gg, gg_circle, git, git_alt, git_square, github, github_alt, github_square,
        gitkraken, gitlab, gitlab_square, gitter, glide, glide_g, gofore, golang, goodreads, goodreads_g, google, google_drive,
        google_pay, google_play, google_plus, google_plus_g, google_plus_square, google_scholar, google_wallet, gratipay, grav,
        gripfire, grunt, guilded, gulp, hacker_news, hacker_news_square, hackerrank, hashnode, hips, hire_a_helper,
        hive, hooli, hornbill, hotjar, houzz, html5, hubspot, ideal, imdb, innosoft, instagram, instagram_square, instalod,
        intercom, internet_explorer, invision, ioxhost, itch_io, itunes, itunes_note, java, jedi_order, jenkins, jira,
        joget, joomla, js, js_square, jsfiddle, jxl, kaggle, kakao_talk, keybase, keycdn, kickstarter, kickstarter_k, korvue, laravel,
        lastfm, lastfm_square, leanpub, less, letterboxd, line, linkedin, linkedin_in, linktree, linode, linux, lumon, lumon_drop, lyft, magento, mailchimp,
        mandalorian, markdown, mastodon, maxcdn, mdb, medapps, medium, medium_m, medrt, meetup, megaport, mendeley, meta, microblog,
        microsoft, mintbit, mix, mixcloud, mixer, mizuni, modx, monero, napster, neos, nfc_directional, nfc_symbol, nimblr,
        node, node_js, notion, npm, ns8, nutritionix, octopus_deploy, odnoklassniki, odnoklassniki_square, odysee, old_republic, openai,
        opencart, openid, opensuse, opera, optin_monster, orcid, osi, padlet, page4, pagelines, palfed, pandora, patreon, paypal, perbyte,
        periscope, phabricator, phoenix_framework, phoenix_squadron, php, pied_piper, pied_piper_alt, pied_piper_hat,
        pied_piper_pp, pied_piper_square, pinterest, pinterest_p, pinterest_square, pix, pixelfed, pixiv, playstation, product_hunt,
        pushed, python, qq, quinscape, quora, r_project, raspberry_pi, ravelry, react, reacteurope, readme, rebel,
        red_river, reddit, reddit_alien, reddit_square, redhat, rendact, renren, replyd, researchgate, resolving, rev,
        rocketchat, rockrms, rust, safari, salesforce, sass, schlix, screenpal, scribd, searchengin, sellcast, sellsy,
        servicestack, shirtsinbulk, shoelace, shopify, shopware, signal_messenger, simplybuilt, sistrix, sith, sitrox, sketch, skyatlas, skype,
        slack, slack_hash, slideshare, snapchat, snapchat_ghost, snapchat_square, soundcloud, sourcetree, space_awesome, speakap, speaker_deck, spotify,
        square_behance, square_bluesky, square_dribbble, square_facebook, square_figma, square_font_awesome, square_font_awesome_stroke, square_git,
        square_github, square_gitlab, square_google_plus, square_hacker_news, square_instagram, square_js, square_kickstarter, square_lastfm,
        square_letterboxd, square_linkedin, square_odnoklassniki, square_pied_piper, square_pinterest, square_reddit, square_snapchat,
        square_steam, square_threads, square_tumblr, square_twitter, square_upwork, square_viadeo, square_vimeo, square_web_awesome, square_web_awesome_stroke, square_whatsapp,
        square_x_twitter, square_xing, square_youtube, squarespace, stack_exchange, stack_overflow, stackpath,
        staylinked, steam, steam_square, steam_symbol, sticker_mule, strava, stripe, stripe_s, stubber, studiovinari,
        stumbleupon, stumbleupon_circle, superpowers, supple, suse, swift, symfony, teamspeak, telegram, telegram_plane, tencent_weibo, tex,
        the_red_yeti, themeco, themeisle, think_peaks, threads, tidal, tiktok, trade_federation, trello, tumblr, tumblr_square, twitch,
        twitter, twitter_square, typo3, uber, ubuntu, uikit, umbraco, uncharted, uniregistry, unity, unsplash, untappd,
        ups, upwork, usb, usps, ussunnah, vaadin, viacoin, viadeo, viadeo_square, viber, vimeo, vimeo_square, vimeo_v, vine, vk,
        vnv, vsco, vuejs, w3c, watchman_monitoring, waze, web_awesome, webflow, weebly, weibo, weixin, whatsapp, whatsapp_square, whmcs, wikipedia_w,
        windows, wirsindhandwerk, wix, wizards_of_the_coast, wodu, wolf_pack_battalion, wordpress, wordpress_simple,
        wpbeginner, wpexplorer, wpforms, wpressr, wsh, x_twitter, xbox, xing, xing_square, y_combinator, yahoo, yammer, yandex,
        yandex_international, yarn, yelp, yoast, youtube, youtube_square, zhihu;

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

        private final String style;

        Size(final String factor) {
            this.style = "fa-" + factor;
        }}

    /**
     * @param fontAwesomeGraphic icon to use in the builder
     * @return a builder for this icon
     */
    public static FontAwesome7IconTypeBuilder on(final FontAwesome7Graphic fontAwesomeGraphic) {
        return new FontAwesome7IconTypeBuilder(fontAwesomeGraphic);
    }

    /**
     * Icon used in the builder.
     */
    private final FontAwesome7Graphic fontAwesomeGraphic;
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
    private FontAwesome7IconTypeBuilder(final FontAwesome7Graphic fontAwesomeGraphic) {
        this.fontAwesomeGraphic = fontAwesomeGraphic;
    }

    /**
     * @return build the icon
     */
    public FontAwesome7IconType build() {
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

        return new FontAwesome7IconType(styles.toArray(new String[0]));
    }

    /**
     * @param rotation rotation to apply to the icon
     * @return the builder
     */
    public FontAwesome7IconTypeBuilder rotate(final Rotation rotation) {
        this.rotation = rotation;
        return this;
    }

    /**
     * @param size size to apply to the icon
     * @return the builder
     */
    public FontAwesome7IconTypeBuilder size(final Size size) {
        this.size = size;
        return this;
    }

    /**
     * make the icon spin
     *
     * @return the builder
     */
    public FontAwesome7IconTypeBuilder spin() {
        this.spin = true;
        return this;
    }

    /**
     * make the icon fixed width
     *
     * @return the builder
     */
    public FontAwesome7IconTypeBuilder fixedWidth() {
        this.fixedWidth = true;
        return this;
    }

    /**
     * An alias for {@link #fixedWidth()}
     *
     * @return the builder
     */
    public FontAwesome7IconTypeBuilder fw() {
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
