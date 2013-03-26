package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.test.TestCategory;
import de.agilecoders.wicket.core.util.JQuery;
import de.agilecoders.wicket.core.util.Json;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Tests the {@code Json} class
 *
 * @author miha
 */
@Category(TestCategory.UnitTest.class)
public class JsonTest {
    private static final String JSON_STRING = "{\"\":null,\"1\":\"value2\",\"3\":true,\"double\":1.2,\"key\":\"value\"}";
    private static final String NONSTANDARD_JSON_STRING = "{\"\":null,'1':\"value2\",\"3\":true,double:1.2,\"key\":'value'}";

    @Test
    public void toJsonCreatesValidJsonNode() throws Exception {
        JsonNode jsonNode = Json.toJson(createData());

        assertThat(jsonNode.get("key").asText(), is(equalTo("value")));
        assertThat(jsonNode.get("1").asText(), is(equalTo("value2")));
        assertThat(jsonNode.get("3").asBoolean(), is(equalTo(true)));
        assertThat(jsonNode.get("double").asDouble(), is(equalTo(1.2)));
        assertThat(jsonNode.get("").asText(), is(equalTo("null")));
    }

    @Test
    public void stringifyNullValueReturnsEmptyJson() {
        assertThat(Json.stringify(null), is(equalTo("{}")));
    }

    @Test
    public void stringifyNullObjectReturnsEmptyJson() {
        assertThat(Json.stringify((Object) null), is(equalTo("{}")));
    }

    @Test(expected = Json.ParseException.class)
    public void fromNullJsonNodeThrowsParseException() throws Exception {
        Json.fromJson((JsonNode) null, Map.class);
    }

    @Test(expected = Json.ParseException.class)
    public void fromNullJsonObjectThrowsParseException() throws Exception {
        Json.fromJson((String) null, JQuery.class);
    }

    @Test
    public void fromNullJsonObjectToMapWorks() throws Exception {
        Json.fromJson((String) null, Map.class);
    }

    @Test
    public void fromJsonNodeReturnsCorrectObject() throws Exception {
        Map data = Json.fromJson(Json.parse(JSON_STRING), Map.class);

        assertThat(String.valueOf(data.get("key")), is(equalTo("value")));
        assertThat(String.valueOf(data.get("1")), is(equalTo("value2")));
        assertThat((Boolean) data.get("3"), is(equalTo(true)));
        assertThat(((Double) data.get("double")), is(equalTo(1.2)));
        assertThat(data.get(""), is(equalTo(null)));
    }

    @Test
    public void fromJsonStringReturnsCorrectObject() throws Exception {
        Map data = Json.fromJson(JSON_STRING, Map.class);

        assertThat(String.valueOf(data.get("key")), is(equalTo("value")));
        assertThat(String.valueOf(data.get("1")), is(equalTo("value2")));
        assertThat((Boolean) data.get("3"), is(equalTo(true)));
        assertThat(((Double) data.get("double")), is(equalTo(1.2)));
        assertThat(data.get(""), is(equalTo(null)));
    }

    @Test
    public void isValidReturnsTrueForValidJson() throws Exception {
        assertThat(Json.isValid(JSON_STRING), is(equalTo(true)));
    }

    @Test
    public void isValidReturnsFalseForInvalidJson() throws Exception {
        assertThat(Json.isValid("{1:[}"), is(equalTo(false)));
    }

    @Test
    public void isValidReturnsFalseForNullOrEmptyJson() throws Exception {
        assertThat(Json.isValid(""), is(equalTo(false)));
        assertThat(Json.isValid(null), is(equalTo(false)));
    }

    @Test
    public void stringifyJsonNodeReturnsCorrectJsonString() throws Exception {
        String value = Json.stringify(Json.toJson(createData()));

        assertThat(value, is(equalTo(JSON_STRING)));
    }

    @Test
    public void stringifyObjectReturnsCorrectJsonString() throws Exception {
        String value = Json.stringify(createData());

        assertThat(value, is(equalTo(JSON_STRING)));
    }

    @Test
    public void parseEmptyValueReturnsEmptyObject() {
        assertThat(Json.parse(""), is(instanceOf(ObjectNode.class)));
    }

    @Test
    public void parseNullValueReturnsEmptyObject() {
        assertThat(Json.parse(null), is(instanceOf(ObjectNode.class)));
    }

    @Test(expected = Json.ParseException.class)
    public void parseThrowsExceptionForInvalidJsonNode() throws Exception {
        Json.parse("{1:[}");
    }

    @Test
    public void parseReturnsCorrectJsonNode() throws Exception {
        JsonNode jsonNode = Json.parse(JSON_STRING);

        assertThat(jsonNode.get("key").asText(), is(equalTo("value")));
        assertThat(jsonNode.get("1").asText(), is(equalTo("value2")));
        assertThat(jsonNode.get("3").asBoolean(), is(equalTo(true)));
        assertThat(jsonNode.get("double").asDouble(), is(equalTo(1.2)));
        assertThat(jsonNode.get("").asText(), is(equalTo("null")));
    }

    @Test
    public void parseNonStandardJsonReturnsCorrectJsonNode() throws Exception {
        JsonNode jsonNode = Json.parse(NONSTANDARD_JSON_STRING);

        assertThat(jsonNode.get("key").asText(), is(equalTo("value")));
        assertThat(jsonNode.get("1").asText(), is(equalTo("value2")));
        assertThat(jsonNode.get("3").asBoolean(), is(equalTo(true)));
        assertThat(jsonNode.get("double").asDouble(), is(equalTo(1.2)));
        assertThat(jsonNode.get("").asText(), is(equalTo("null")));
    }

    private Object createData() {
        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("key", "value");
        data.put(1, "value2");
        data.put(3, true);
        data.put("double", 1.2d);
        data.put("", null);
        return data;
    }
}
