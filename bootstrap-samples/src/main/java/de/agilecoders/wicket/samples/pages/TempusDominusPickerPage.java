package de.agilecoders.wicket.samples.pages;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField;
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeTextField;
import org.apache.wicket.extensions.markup.html.form.datetime.LocalTimeTextField;
import org.apache.wicket.extensions.markup.html.form.datetime.ZonedDateTimeField;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.form.AbstractTextComponent.ITextFormatProvider;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converter.AbstractJavaTimeConverter;
import org.apache.wicket.util.convert.converter.LocalDateTimeConverter;
import org.apache.wicket.util.convert.converter.ZonedDateTimeConverter;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus.AbstractTempusDominusWithIcon;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus.TempusDominusBehavior;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus.TempusDominusConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus.TempusDominusDisplayConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus.TempusDominusIconConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus.TempusDominusLocalizationConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus.TempusDominusRestrictionsConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus.TempusDominusDisplayConfig.ButtonType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus.TempusDominusDisplayConfig.ComponentType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus.TempusDominusDisplayConfig.ViewModeType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6CssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconType;

/**
 * Example page of tempus-dominus picker usage
 *
 */
@MountPath(value = "/tempusdominus")
public class TempusDominusPickerPage extends BasePage {
    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     *
     * @param parameters current page parameters
     */
    public TempusDominusPickerPage(PageParameters parameters) {
        super(parameters);

        Form<Object> form = new Form<>("form");
        add(form);

        LocalDate max = LocalDate.now();
        LocalDate min = max.minusWeeks(1);

        {
            // java.util.Date
            TempusDominusConfig simpleConfig = new TempusDominusConfig()
                    .withRestrictions(new TempusDominusRestrictionsConfig()
                            .withMinDate(Date.from(min.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                            .withMaxDate(Date.from(max.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    )
                    .withLocalization(new TempusDominusLocalizationConfig()
                            .withFormat("dd/MM/yyyy HH:mm:ss")
                    )
                    .withClass(Date.class);

            TempusDominusConfig birthDayConfig = new TempusDominusConfig()
                    .withDisplayConfig(new TempusDominusDisplayConfig()
                            .withViewMode(ViewModeType.YEARS)
                            .withComponent(ComponentType.CLOCK, false)
                    )
                    .withLocalization(new TempusDominusLocalizationConfig()
                            .withFormat("dd/MM/yyyy")
                    )
                    .withRestrictions(new TempusDominusRestrictionsConfig()
                            .withMinDate(Date.from(min.minusYears(6).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    )
                    .withClass(Date.class);
            TempusDominusConfig iconsConfig = new TempusDominusConfig()
                    .withDisplayConfig(new TempusDominusDisplayConfig()
                            .withButton(ButtonType.TODAY, true)
                            .withButton(ButtonType.CLEAR, true)
                            .withButton(ButtonType.CLOSE, true)
                            .withIcons(new TempusDominusIconConfig()
                                    .withDateIcon(FontAwesome6IconType.calendar_days_r)
                                    .withTimeIcon(FontAwesome6IconType.clock_s)
                                    .withUpIcon(FontAwesome6IconType.arrow_up_s)
                                    .withDownIcon(FontAwesome6IconType.arrow_down_s)
                                    .withPreviousIcon(FontAwesome6IconType.arrow_left_s)
                                    .withNextIcon(FontAwesome6IconType.arrow_right_s)
                                    .withTodayIcon(FontAwesome6IconType.calendar_check_s)
                                    .withClearIcon(FontAwesome6IconType.eraser_s)
                                    .withCloseIcon(FontAwesome6IconType.xmark_s)
                            )
                            .withComponent(ComponentType.SECONDS, true)
                    )
                    .withLocalization(new TempusDominusLocalizationConfig()
                            .withFormat("dd/MM/yyyy HH:mm:ss")
                    )
                    .withClass(Date.class);

            form.add(
                new DateTextField("simple", simpleConfig.getFormat()).add(new TempusDominusBehavior(simpleConfig)),
                new Code("simple-java-code", Model.of(
                        "new TempusDominusConfig()\n" + //
                        "        .withRestrictions(new TempusDominusRestrictionsConfig()\n" + //
                        "                .withMinDate(Date.from(min.atStartOfDay(ZoneId.systemDefault()).toInstant()))\n" + //
                        "                .withMaxDate(Date.from(max.atStartOfDay(ZoneId.systemDefault()).toInstant()))\n" + //
                        "        )\n" + //
                        "        .withLocalization(new TempusDominusLocalizationConfig()\n" + //
                        "                .withFormat(\"dd/MM/yyyy HH:mm:ss\")\n" + //
                        "        ).withClass(Date.class);\n" + //
                        "")),

                new DateTextField("birthday", simpleConfig.getFormat()).add(new TempusDominusBehavior(birthDayConfig)),
                new Code("birthday-java-code", Model.of(
                        "new TempusDominusConfig()\n" + //
                        "        .withDisplayConfig(new TempusDominusDisplayConfig()\n" + //
                        "                .withViewMode(ViewModeType.YEARS)\n" + //
                        "                .withComponent(ComponentType.CLOCK, false)\n" + //
                        "        )\n" + //
                        "        .withLocalization(new TempusDominusLocalizationConfig()\n" + //
                        "                .withFormat(\"dd/MM/yyyy\")\n" + //
                        "        )\n" + //
                        "        .withRestrictions(new TempusDominusRestrictionsConfig()\n" + //
                        "                .withMinDate(Date.from(min.minusYears(6).atStartOfDay(ZoneId.systemDefault()).toInstant()))\n" + //
                        "        ).withClass(Date.class);\n" + //
                        "")),

                new DateTextField("icons", iconsConfig.getFormat()).add(new TempusDominusBehavior(iconsConfig)),
                new Code("icons-java-code", Model.of(
                        "new TempusDominusConfig()\n" + //
                        "        .withDisplayConfig(new TempusDominusDisplayConfig()\n" + //
                        "                .withButton(ButtonType.TODAY, true)\n" + //
                        "                .withButton(ButtonType.CLEAR, true)\n" + //
                        "                .withButton(ButtonType.CLOSE, true)\n" + //
                        "                .withIcons(new TempusDominusIconConfig()\n" + //
                        "                        .withDateIcon(FontAwesome6IconType.calendar_days_r)\n" + //
                        "                        .withTimeIcon(FontAwesome6IconType.clock_s)\n" + //
                        "                        .withUpIcon(FontAwesome6IconType.arrow_up_s)\n" + //
                        "                        .withDownIcon(FontAwesome6IconType.arrow_down_s)\n" + //
                        "                        .withPreviousIcon(FontAwesome6IconType.arrow_left_s)\n" + //
                        "                        .withNextIcon(FontAwesome6IconType.arrow_right_s)\n" + //
                        "                        .withTodayIcon(FontAwesome6IconType.calendar_check_s)\n" + //
                        "                        .withClearIcon(FontAwesome6IconType.eraser_s)\n" + //
                        "                        .withCloseIcon(FontAwesome6IconType.xmark_s)\n" + //
                        "                )\n" + //
                        "                .withComponent(ComponentType.SECONDS, true)\n" + //
                        "        )\n" + //
                        "        .withLocalization(new TempusDominusLocalizationConfig()\n" + //
                        "                .withFormat(\"dd/MM/yyyy HH:mm:ss\")\n" + //
                        "        ).withClass(Date.class);\n" + //
                        ""))
                    );
        }
        {
            // java.time.*
            TempusDominusConfig simpleConfig = new TempusDominusConfig()
                    .withRestrictions(new TempusDominusRestrictionsConfig()
                            .withMinDate(min.atStartOfDay())
                            .withMaxDate(max.atStartOfDay())
                    )
                    .withLocalization(new TempusDominusLocalizationConfig()
                            .withFormat("dd/MM/yyyy HH:mm:ss")
                    )
                    .withClass(LocalDateTime.class);

            TempusDominusConfig birthDayConfig = new TempusDominusConfig()
                    .withDisplayConfig(new TempusDominusDisplayConfig()
                            .withViewMode(ViewModeType.YEARS)
                    )
                    .withRestrictions(new TempusDominusRestrictionsConfig()
                            .withMinDate(min.minusYears(6))
                    )
                    .withClass(LocalDate.class);
            TempusDominusConfig timeConfig = new TempusDominusConfig()
                    .withClass(LocalTime.class);

            TempusDominusConfig zonedConfig = new TempusDominusConfig()
                    .withRestrictions(new TempusDominusRestrictionsConfig()
                            .withDayOfWeekDisabled(0)
                            .withDayOfWeekDisabled(6)
                    )
                    .withClass(ZonedDateTime.class);

            form.add(
                new LocalDateTimeTextField("localsimple", simpleConfig.getFormat()).add(new TempusDominusBehavior(simpleConfig)),
                new Code("localsimple-java-code", Model.of(
                        "new TempusDominusConfig()\n" + //
                        "        .withRestrictions(new TempusDominusRestrictionsConfig()\n" + //
                        "                .withMinDate(min.atStartOfDay())\n" + //
                        "                .withMaxDate(max.atStartOfDay())\n" + //
                        "        )\n" + //
                        "        .withLocalization(new TempusDominusLocalizationConfig()\n" + //
                        "                .withFormat(\"dd/MM/yyyy HH:mm:ss\")\n" + //
                        "        ).withClass(LocalDateTime.class);\n" + //
                        "")),

                new LocalDateTextField("localbirthday", simpleConfig.getFormat()).add(new TempusDominusBehavior(birthDayConfig)),
                new Code("localbirthday-java-code", Model.of(
                        "new TempusDominusConfig()\n" + //
                        "        .withDisplayConfig(new TempusDominusDisplayConfig()\n" + //
                        "                .withViewMode(ViewModeType.YEARS)\n" + //
                        "        )\n" + //
                        "        .withRestrictions(new TempusDominusRestrictionsConfig()\n" + //
                        "                .withMinDate(min.minusYears(6))\n" + //
                        "        ).withClass(LocalDate.class);\n" + //
                        "")),

                new LocalTimeTextField("localtime", timeConfig.getFormat()).add(new TempusDominusBehavior(timeConfig)),
                new Code("localtime-java-code", Model.of(
                        "new TempusDominusConfig().withClass(LocalTime.class)")),

                new AbstractTempusDominusWithIcon<ZonedDateTime>("zoned", zonedConfig) {
                        private static final long serialVersionUID = 1L;

                        @Override
                        protected FormComponent<ZonedDateTime> newInput(String wicketId, String dateFormat) {
                                return new ZonedDateTimeTextField(wicketId, dateFormat);
                        }

                },
                new Code("zoned-java-code", Model.of(
                        "new TempusDominusConfig()\n" + //
                        "        .withRestrictions(new TempusDominusRestrictionsConfig()\n" + //
                        "                .withDayOfWeekDisabled(0)\n" + //
                        "                .withDayOfWeekDisabled(6)\n" + //
                        "        ).withClass(ZonedDateTime.class);\n" + //
                        ""))
                );
        }
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(new CssResourceReference(TempusDominusPickerPage.class, "css/layout.css")));
        response.render(CssHeaderItem.forReference(FontAwesome6CssReference.instance()));
    }

    private class ZonedDateTimeTextField extends TextField<ZonedDateTime> implements ITextFormatProvider {
        private static final long serialVersionUID = 1L;

        /**
         * The converter for the TextField
         */
        private final TextFormatConverter converter;

        /**
         * Construct with a pattern.
         *
         * @param id
         *            the component id
         * @param model
         *            the model
         * @param dateTimePattern
         *            the pattern to use
         */
        public ZonedDateTimeTextField(String id, IModel<ZonedDateTime> model, String dateTimePattern) {
            super(id, model, ZonedDateTime.class);

            this.converter = new TextFormatConverter() {
                private static final long serialVersionUID = 1L;

                @Override
                public DateTimeFormatter getDateTimeFormatter(Locale locale) {
                    return DateTimeFormatter.ofPattern(dateTimePattern).withLocale(locale);
                }

                @Override
                public String getTextFormat(Locale locale) {
                    return dateTimePattern;
                }
            };
        }

        /**
         * Construct with a pattern.
         *
         * @param id
         *            the component id
         * @param dateTimePattern
         *            the pattern to use
         */
        public ZonedDateTimeTextField(String id, String dateTimePattern) {
            this(id, null, dateTimePattern);
        }

        /**
         * @return The specialized converter.
         * @see org.apache.wicket.Component#createConverter(java.lang.Class)
         */
        @Override
        protected IConverter<?> createConverter(Class<?> clazz) {
            if (ZonedDateTime.class.isAssignableFrom(clazz)) {
                return converter;
            }
            return null;
        }

        /**
         * @see org.apache.wicket.markup.html.form.AbstractTextComponent.ITextFormatProvider#getTextFormat()
         */
        @Override
        public final String getTextFormat() {
            return converter.getTextFormat(getLocale());
        }

        private abstract class TextFormatConverter extends ZonedDateTimeConverter {
            private static final long serialVersionUID = 1L;

            public abstract String getTextFormat(Locale locale);
        }
    }
}
