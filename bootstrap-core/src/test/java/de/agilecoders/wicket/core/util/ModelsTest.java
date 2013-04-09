package de.agilecoders.wicket.core.util;

import de.agilecoders.wicket.core.test.TestCategory;
import de.agilecoders.wicket.core.util.Models;
import org.apache.wicket.model.Model;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Tests the {@link Models} class
 *
 * @author miha
 */
@Category(TestCategory.UnitTest.class)
public class ModelsTest {

    @Test
    public void isNullOrEmptyReturnsTrueForEmptyModel() {
        assertThat(Models.isNullOrEmpty(Model.of("")), is(equalTo(true)));
    }

    @Test
    public void isNullOrEmptyReturnsTrueForNullValueModel() {
        assertThat(Models.isNullOrEmpty(Model.of((String)null)), is(equalTo(true)));
    }

    @Test
    public void isNullOrEmptyReturnsTrueForNullModel() {
        assertThat(Models.isNullOrEmpty(null), is(equalTo(true)));
    }

    @Test
    public void isNullOrEmptyReturnsFalseForFilledModel() {
        assertThat(Models.isNullOrEmpty(Model.of("value")), is(equalTo(false)));
    }

}
