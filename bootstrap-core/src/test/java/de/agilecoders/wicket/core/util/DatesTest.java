package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.test.TestCategory;
import de.agilecoders.wicket.core.util.Dates;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Tests the {@link Dates} class
 *
 * @author miha
 */
@Category(TestCategory.UnitTest.class)
public class DatesTest {

    @Test
    public void toJavaScriptWorks() {
        assertThat(Dates.toJavaScriptDateFormat("dd.MM.yyyy"), is(equalTo("dd.mm.yyyy")));
        assertThat(Dates.toJavaScriptDateFormat("dd-MMMM-yyyy"), is(equalTo("dd-mmmm-yyyy")));
        assertThat(Dates.toJavaScriptDateFormat("dd-MMM-yyyy"), is(equalTo("dd-mmM-yyyy")));
    }

    @Test
    public void toJavaWorks() {
        assertThat(Dates.toJavaDateFormat("dd.mm.yyyy"), is(equalTo("dd.MM.yyyy")));
        assertThat(Dates.toJavaDateFormat("dd-mmmm-yyyy"), is(equalTo("dd-MMMM-yyyy")));
        assertThat(Dates.toJavaDateFormat("dd-mmm-yyyy"), is(equalTo("dd-MMm-yyyy")));
    }

}
