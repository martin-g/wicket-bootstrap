package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for DateTextFieldConfig
 */
class DateTextFieldConfigTest {

    @Test
    void serializeTodayButton() throws JacksonException {
        ObjectMapper mapper = new ObjectMapper();
        assertEquals("false", mapper.writeValueAsString(DateTextFieldConfig.TodayButton.FALSE));
        assertEquals("true", mapper.writeValueAsString(DateTextFieldConfig.TodayButton.TRUE));
        assertEquals("\"linked\"", mapper.writeValueAsString(DateTextFieldConfig.TodayButton.LINKED));
    }
}
