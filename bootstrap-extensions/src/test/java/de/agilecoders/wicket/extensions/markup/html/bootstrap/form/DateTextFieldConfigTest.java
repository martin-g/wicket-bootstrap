package de.agilecoders.wicket.extensions.markup.html.bootstrap.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for DateTextFieldConfig
 */
class DateTextFieldConfigTest {

    @Test
    void serializeTodayButton() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        assertEquals("false", mapper.writeValueAsString(DateTextFieldConfig.TodayButton.FALSE));
        assertEquals("true", mapper.writeValueAsString(DateTextFieldConfig.TodayButton.TRUE));
        assertEquals("\"linked\"", mapper.writeValueAsString(DateTextFieldConfig.TodayButton.LINKED));
    }
}
