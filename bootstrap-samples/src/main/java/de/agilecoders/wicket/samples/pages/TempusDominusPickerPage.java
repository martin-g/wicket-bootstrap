package de.agilecoders.wicket.samples.pages;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField;
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeTextField;
import org.apache.wicket.extensions.markup.html.form.datetime.LocalTimeTextField;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.form.AbstractTextComponent.ITextFormatProvider;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.LambdaChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converter.ZonedDateTimeConverter;
import org.apache.wicket.util.string.Strings;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus.AbstractTempusDominusWithIcon;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus.TempusDominusBehavior;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus.TempusDominusConfig;
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
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        LocalDate max = LocalDate.now();
        LocalDate min = max.minusWeeks(1);

        Form<Object> form = new Form<>("form");
        final NotificationPanel feedback = new NotificationPanel("feedback");
        add(form.setOutputMarkupId(true));

        DropDownChoice<String> languages = new DropDownChoice<String>("languages"
                , Model.of(Session.get().getLocale().getLanguage())
                , List.of(Locale.getISOLanguages())
                , new LambdaChoiceRenderer<String>(
                        lng -> Locale.forLanguageTag(lng).getDisplayLanguage(Locale.ENGLISH)
                        , lng -> lng));
        DropDownChoice<String> countries = new DropDownChoice<String>("countries"
                , Model.of(Session.get().getLocale().getCountry())
                , List.of(Locale.getISOCountries())
                , new LambdaChoiceRenderer<String>(
                        country -> Locale.forLanguageTag("en-" + country).getDisplayCountry(Locale.ENGLISH)
                        , country -> country));
        form.add(feedback.setOutputMarkupId(true), languages.setRequired(true), countries);

        // java.util.Date
        TempusDominusConfig simpleConfig = new TempusDominusConfig()
                .withClass(Date.class)
                .withRestrictions(cfg -> cfg
                        .withMinDate(Date.from(min.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .withMaxDate(Date.from(max.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                );

        TempusDominusConfig birthDayConfig = new TempusDominusConfig()
                .withClass(Date.class)
                .withDisplayConfig(cfg -> cfg
                        .withViewMode(ViewModeType.YEARS)
                        .withComponent(ComponentType.CLOCK, false)
                )
                .withLocalization(cfg -> cfg
                        .withFormat("dd/MM/yyyy")
                )
                .withRestrictions(cfg -> cfg
                        .withMinDate(Date.from(min.minusYears(6).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                );
        TempusDominusConfig iconsConfig = new TempusDominusConfig()
                .withClass(Date.class)
                .withDisplayConfig(cfg -> cfg
                        .withButton(ButtonType.TODAY, true)
                        .withButton(ButtonType.CLEAR, true)
                        .withButton(ButtonType.CLOSE, true)
                        .withIcons(icons -> icons
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
                .withLocalization(cfg -> cfg
                        .withFormat("dd/MM/yyyy HH:mm:ss")
                );

            form.add(
                new DateTextField("simple", Model.of((Date)null), simpleConfig.getFormat()).add(new TempusDominusBehavior(simpleConfig)),
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

                new DateTextField("birthday", Model.of((Date)null), simpleConfig.getFormat()).add(new TempusDominusBehavior(birthDayConfig)),
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

                new DateTextField("icons", Model.of((Date)null), iconsConfig.getFormat()).add(new TempusDominusBehavior(iconsConfig)),
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
        // java.time.*
        TempusDominusConfig localConfig = new TempusDominusConfig()
                .withClass(LocalDateTime.class)
                .withRestrictions(cfg -> cfg
                        .withMinDate(min.atStartOfDay())
                        .withMaxDate(max.atStartOfDay())
                )
                .withLocalization(cfg -> cfg
                        .withFormat("dd/MM/yyyy HH:mm:ss")
                );

        TempusDominusConfig localBirthConfig = new TempusDominusConfig()
                .withClass(LocalDate.class)
                .withDisplayConfig(cfg -> cfg
                        .withViewMode(ViewModeType.YEARS)
                )
                .withRestrictions(cfg -> cfg
                        .withMinDate(min.minusYears(6))
                );
        TempusDominusConfig timeConfig = new TempusDominusConfig()
                .withClass(LocalTime.class);

        TempusDominusConfig zonedConfig = new TempusDominusConfig()
                .withClass(ZonedDateTime.class)
                .withRestrictions(cfg -> cfg
                        .withDayOfWeekDisabled(0)
                        .withDayOfWeekDisabled(6)
                );

        form.add(
            new LocalDateTimeTextField("localsimple", Model.of((LocalDateTime)null), localConfig.getFormat()).add(new TempusDominusBehavior(localConfig)),
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

            new LocalDateTextField("localbirthday", Model.of((LocalDate)null), localConfig.getFormat()).add(new TempusDominusBehavior(localBirthConfig)),
            new Code("localbirthday-java-code", Model.of(
                    "new TempusDominusConfig()\n" + //
                    "        .withDisplayConfig(new TempusDominusDisplayConfig()\n" + //
                    "                .withViewMode(ViewModeType.YEARS)\n" + //
                    "        )\n" + //
                    "        .withRestrictions(new TempusDominusRestrictionsConfig()\n" + //
                    "                .withMinDate(min.minusYears(6))\n" + //
                    "        ).withClass(LocalDate.class);\n" + //
                    "")),

            new LocalTimeTextField("localtime", Model.of((LocalTime)null), timeConfig.getFormat()).add(new TempusDominusBehavior(timeConfig)),
            new Code("localtime-java-code", Model.of(
                    "new TempusDominusConfig().withClass(LocalTime.class)")),

            new AbstractTempusDominusWithIcon<ZonedDateTime>("zoned", Model.of((ZonedDateTime)null), zonedConfig) {
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
        form.add(new AjaxButton("submit") {
            protected void onSubmit(AjaxRequestTarget target) {
                String tag = languages.getModelObject();
                final String country = countries.getModelObject();
                if (!Strings.isEmpty(country)) {
                    tag += "-" + country;
                }
                Locale loc = Locale.forLanguageTag(tag);
                form.info("'" + loc + "' has been set");
                Session.get().setLocale(loc);
                simpleConfig.withLocalization(cfg -> cfg.withLocale(loc).withClass(Date.class));
                target.add(form);
            }

            protected void onError(AjaxRequestTarget target) {
                target.add(feedback);
            }
        });
    }

    @Override
    protected boolean hasNavigation() {
        return false; // we will use internal one
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
