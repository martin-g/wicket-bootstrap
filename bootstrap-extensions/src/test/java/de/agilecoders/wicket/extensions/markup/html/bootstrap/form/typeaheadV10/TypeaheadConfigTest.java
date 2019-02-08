package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.WicketApplicationTest;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound.Bloodhound;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound.BloodhoundConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.typeaheadV10.bloodhound.Remote;

import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class TypeaheadConfigTest extends WicketApplicationTest {

    @Test
    public void assertValidHeaderScript() {

        WicketTester tester = tester();

        Bloodhound bloodhound = new Bloodhound("engine", new BloodhoundConfig()) {

            @Override
            public Iterable getChoices(String input) {
                return Collections.EMPTY_LIST;
            }

            @Override
            public String renderChoice(Object choice) {
                return null;
            }
        };

        DataSet dataSet = new DataSet(bloodhound);
        TypeaheadConfig config = new TypeaheadConfig(dataSet);

        Typeahead field = new Typeahead("typeahead", Model.of(""), config);
        tester.startComponentInPage(field, Markup.of("<html><head></head><body><input type='text' wicket:id='typeahead'/></body></html>"));


        OnDomReadyHeaderItem item = field.getDomReadyScript(config);
        String expected = "var engine = new Bloodhound({\"datumTokenizer\":function(d) { return Bloodhound.tokenizers.whitespace(d.value); },\"queryTokenizer\":Bloodhound.tokenizers.whitespace,\"remote\":\"./wicket/page?0-1.0-typeahead&term=%QUERY\"});engine.initialize();$('#typeahead1').typeahead({},{\"name\":\"engine\",\"source\":engine.ttAdapter()});";
        assertEquals(expected, item.getJavaScript());
    }

    @Test
    public void testComplexRemote() {

        Remote remote = new Remote();
        remote.withUrl("foo").withWildcard("%FOO");

        BloodhoundConfig config = new BloodhoundConfig();
        config.withRemote(remote);

        String expected = "{\"datumTokenizer\":function(d) { return Bloodhound.tokenizers.whitespace(d.value); },\"queryTokenizer\":Bloodhound.tokenizers.whitespace,\"remote\":{\"url\":\"foo\",\"wildcard\":\"%FOO\"}}";
        assertEquals(expected, config.toJsonString());

    }
    @Test
    public void testSimpleRemote() {

        Remote remote = new Remote("foo");

        BloodhoundConfig config = new BloodhoundConfig();
        config.withRemote(remote);

        String expected = "{\"datumTokenizer\":function(d) { return Bloodhound.tokenizers.whitespace(d.value); },\"queryTokenizer\":Bloodhound.tokenizers.whitespace,\"remote\":\"foo\"}";
        assertEquals(expected, config.toJsonString());

    }
}
