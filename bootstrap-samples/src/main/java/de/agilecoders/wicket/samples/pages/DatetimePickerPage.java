package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime.DatetimePickerConfig;
import de.agilecoders.wicket.samples.panels.DatetimePickerPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Date;

/**
 * Examples page of datetime picker usage
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
        DatetimePickerConfig simpleConfig = new DatetimePickerConfig()
            .withFormat("dd/MM/yyyy HH:mm:ss");
        DatetimePickerConfig birthDayConfig = new DatetimePickerConfig()
            .useView(DatetimePickerConfig.ViewModeType.YEARS)
            .withFormat("dd/MM/yyyy")
            .withMaxDate(new Date());
        DatetimePickerConfig maskedConfig = new DatetimePickerConfig()
            .withFormat("dd/MM/yyyy HH:mm:ss")
            .useMaskInput(true);
        add(
            new DatetimePickerPanel("simple", simpleConfig),
            new Code("default-java-code", Model.of("new DatetimePickerConfig()\n"
                + "            .withFormat(\"dd/MM/yyyy HH:mm:ss\");")),
            new DatetimePickerPanel("birthday", birthDayConfig),
            new Code("birthday-java-code", Model.of("new DatetimePickerConfig()\n"
                + "            .useView(DatetimePickerConfig.ViewModeType.YEARS)\n"
                + "            .withFormat(\"dd/MM/yyyy\")\n"
                + "            .withMaxDate(new Date());")),
            new DatetimePickerPanel("masked", maskedConfig),
            new Code("masked-java-code", Model.of("new DatetimePickerConfig()\n"
                + "            .withFormat(\"dd/MM/yyyy HH:mm:ss\")\n"
                + "            .useMaskInput(true);"))
        );
    }

    @Override
    protected boolean hasNavigation() {
        return true;
    }
}
