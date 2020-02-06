package de.agilecoders.wicket.samples.pages;

import static de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig.BTN_SHOW_CLEAR;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig.BTN_SHOW_CLOSE;
import static de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig.BTN_SHOW_TODAY;

import java.util.Map;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.resource.JQueryPluginResourceReference;
import org.joda.time.LocalDate;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerIconConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5CssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5IconType;
import de.agilecoders.wicket.samples.panels.DatetimePickerPanel;
import de.agilecoders.wicket.samples.panels.LocalDateTimePickerPanel;

/**
 * Example page of datetime picker usage
 *
 * @author Alexey Volkov
 * @since 01.02.15
 */
@MountPath(value = "/datetimepicker")
public class DatetimePickerPage extends BasePage {

    private static final long serialVersionUID = -4378694382016836954L;

    /**
     * Construct.
     *
     * @param parameters current page parameters
     */
    public DatetimePickerPage(PageParameters parameters) {
        super(parameters);

        Form<Object> form = new Form<Object>("form");
        add(form);

        LocalDate max = LocalDate.now();
        LocalDate min = max.minusWeeks(1);

        DatetimePickerConfig simpleConfig = new DatetimePickerConfig()
            .withMinDate(min.toDate())
            .withMaxDate(max.toDate())
            .withFormat("dd/MM/yyyy HH:mm:ss");

        DatetimePickerConfig birthDayConfig = new DatetimePickerConfig()
            .useView(DatetimePickerConfig.ViewModeType.YEARS)
            .withFormat("dd/MM/yyyy")
            .withMaxDate(max.toDate());
        DatetimePickerConfig maskedConfig = new DatetimePickerConfig()
            .withFormat("dd/MM/yyyy HH:mm:ss")
            .useMaskInput(true);
        DatetimePickerConfig iconsConfig = new DatetimePickerConfig()
            .withFormat("dd/MM/yyyy HH:mm:ss")
            .withButtons(Map.of(BTN_SHOW_TODAY, true, BTN_SHOW_CLEAR, true, BTN_SHOW_CLOSE, true))
            .with(
                new DatetimePickerIconConfig()
                    .useDateIcon(FontAwesome5IconType.calendar_s)
                    .useTimeIcon(FontAwesome5IconType.clock_s)
                    .useUpIcon(FontAwesome5IconType.arrow_up_s)
                    .useDownIcon(FontAwesome5IconType.arrow_down_s)
                    .usePreviousIcon(FontAwesome5IconType.arrow_left_s)
                    .useNextIcon(FontAwesome5IconType.arrow_right_s)
                    .useTodayIcon(FontAwesome5IconType.calendar_check_s)
                    .useClearIcon(FontAwesome5IconType.eraser_s)
                    .useCloseIcon(FontAwesome5IconType.times_s)
            );

        form.add(
            new DatetimePickerPanel("simple", simpleConfig),
            new Code("default-java-code", Model.of("new DatetimePickerConfig()\n"
                                                   + "            .withMinDate(min.toDate())\n"
                                                   + "            .withMaxDate(max.toDate())\n"
                                                   + "            .withFormat(\"dd/MM/yyyy HH:mm:ss\");")),
            new DatetimePickerPanel("birthday", birthDayConfig),
            new Code("birthday-java-code", Model.of("new DatetimePickerConfig()\n"
                                                    + "            .useView(DatetimePickerConfig.ViewModeType.YEARS)\n"
                                                    + "            .withFormat(\"dd/MM/yyyy\")\n"
                                                    + "            .withMaxDate(new Date());")),
            new DatetimePickerPanel("masked", maskedConfig),
            new Code("masked-java-code", Model.of("new DatetimePickerConfig()\n"
                                                  + "            .withFormat(\"dd/MM/yyyy HH:mm:ss\")\n"
                                                  + "            .useMaskInput(true);")),
            new DatetimePickerPanel("icons", iconsConfig),
            new Code("icons-java-code", Model.of("new DatetimePickerConfig()\n"
                                                 + "            .withFormat(\"dd/MM/yyyy HH:mm:ss\")\n"
                                                 + "            .withButtons(Map.of(BTN_SHOW_TODAY, true, BTN_SHOW_CLEAR, true, BTN_SHOW_CLOSE, true))\n"
                                                 + "            .with(\n"
                                                 + "                new DatetimePickerIconConfig()\n"
                                                 + "                    .useDateIcon(FontAwesomeIconType.calendar)\n"
                                                 + "                    .useTimeIcon(FontAwesomeIconType.clock_o)\n"
                                                 + "                    .useUpIcon(FontAwesomeIconType.arrow_up)\n"
                                                 + "                    .useDownIcon(FontAwesomeIconType.arrow_down)\n"
                                                 + "                    .usePreviousIcon(FontAwesome5IconType.arrow_left_s)\n"
                                                 + "                    .useNextIcon(FontAwesome5IconType.arrow_right_s)\n"
                                                 + "                    .useTodayIcon(FontAwesome5IconType.calendar_check_s)\n"
                                                 + "                    .useClearIcon(FontAwesome5IconType.eraser_s)\n"
                                                 + "                    .useCloseIcon(FontAwesome5IconType.times_s)\n"
                                                 + "            );")),
            new LocalDateTimePickerPanel("localsimple", simpleConfig),
            new Code("localdefault-java-code", Model.of("new DatetimePickerConfig()\n"
                                                   + "            .withFormat(\"dd/MM/yyyy HH:mm:ss\");")),
            new LocalDateTimePickerPanel("localbirthday", birthDayConfig),
            new Code("localbirthday-java-code", Model.of("new DatetimePickerConfig()\n"
                                                    + "            .useView(DatetimePickerConfig.ViewModeType.YEARS)\n"
                                                    + "            .withFormat(\"dd/MM/yyyy\")\n"
                                                    + "            .withMaxDate(new Date());")),
            new LocalDateTimePickerPanel("localmasked", maskedConfig),
            new Code("localmasked-java-code", Model.of("new DatetimePickerConfig()\n"
                                                  + "            .withFormat(\"dd/MM/yyyy HH:mm:ss\")\n"
                                                  + "            .useMaskInput(true);")),
            new DatetimePickerPanel("localicons", iconsConfig),
            new Code("localicons-java-code", Model.of("new DatetimePickerConfig()\n"
                    + "            .withFormat(\"dd/MM/yyyy HH:mm:ss\")\n"
                    + "            .withButtons(Map.of(BTN_SHOW_TODAY, true, BTN_SHOW_CLEAR, true, BTN_SHOW_CLOSE, true))\n"
                    + "            .with(\n"
                    + "                new DatetimePickerIconConfig()\n"
                    + "                    .useDateIcon(FontAwesomeIconType.calendar)\n"
                    + "                    .useTimeIcon(FontAwesomeIconType.clock_o)\n"
                    + "                    .useUpIcon(FontAwesomeIconType.arrow_up)\n"
                    + "                    .useDownIcon(FontAwesomeIconType.arrow_down)\n"
                    + "                    .usePreviousIcon(FontAwesome5IconType.arrow_left_s)\n"
                    + "                    .useNextIcon(FontAwesome5IconType.arrow_right_s)\n"
                    + "                    .useTodayIcon(FontAwesome5IconType.calendar_check_s)\n"
                    + "                    .useClearIcon(FontAwesome5IconType.eraser_s)\n"
                    + "                    .useCloseIcon(FontAwesome5IconType.times_s)\n"
                    + "            );"))
        );
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(FontAwesome5CssReference.instance()));

        // Use JS to show/hide the demo <section>s because otherwise DateTimePicker JS widget confuses which <input> is being clicked
        response.render(JavaScriptHeaderItem.forReference(new JQueryPluginResourceReference(DatetimePickerPage.class, "DatetimePickerPage.js")));
    }
}
