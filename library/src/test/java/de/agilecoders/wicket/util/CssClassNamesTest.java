package de.agilecoders.wicket.util;

import de.agilecoders.wicket.test.TestCategory;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Tests the {@link CssClassNames} class
 *
 * @author miha
 */
@Category(TestCategory.UnitTest.class)
public class CssClassNamesTest {

    @Test
    public void splitSplitsStringCorrect() {
        assertThat(CssClassNames.split("class1 class2 class3"), is(equalTo(Generics2.newHashSet("class1", "class2", "class3"))));
    }

    @Test
    public void joinJoinsClassNamesCorrect() {
        assertThat(CssClassNames.join(Generics2.newHashSet("class1", "class2", "class3")), is(equalTo("class1 class2 class3")));
    }

    @Test
    public void containsReturnsFalseForNonExistingClassName() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");

        assertThat(builder.contains("class"), is(equalTo(false)));
        assertThat(builder.contains("class10"), is(equalTo(false)));
    }

    @Test
    public void containsReturnsTrueForExistingClassName() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");

        assertThat(builder.contains("class1"), is(equalTo(true)));
        assertThat(builder.contains("class2"), is(equalTo(true)));
        assertThat(builder.contains("class3"), is(equalTo(true)));
    }

    @Test
    public void parseReturnsBuilderWithAllGivenClassNames() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");

        assertThat(builder.asSet(), is(equalTo(Generics2.newHashSet("class1", "class2", "class3"))));
    }

    @Test
    public void addClassNameAsStringToBuilderWorks() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");
        builder.add("class4", "class5");

        assertThat(builder.asSet(), is(equalTo(Generics2.newHashSet("class1", "class2", "class3", "class4", "class5"))));
    }

    @Test
    public void addClassNameAsSetToBuilderWorks() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");
        builder.add(Generics2.newHashSet("class4", "class5"));

        assertThat(builder.asSet(), is(equalTo(Generics2.newHashSet("class1", "class2", "class3", "class4", "class5"))));
    }

    @Test
    public void addClassNameAsBuilderToBuilderWorks() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");
        builder.add(CssClassNames.newBuilder().add("class4", "class5"));

        assertThat(builder.asString(), is(equalTo("class4 class5 class1 class2 class3")));
    }

    @Test
    public void removeClassNameAsStringFromBuilderWorks() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");
        builder.remove("class2", "class3");

        assertThat(builder.asString(), is(equalTo("class1")));
    }

    @Test
    public void removeClassNameAsSetFromBuilderWorks() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");
        builder.remove(Generics2.newHashSet("class2", "class3"));

        assertThat(builder.asSet(), is(equalTo(Generics2.newHashSet("class1"))));
    }

    @Test
    public void removeClassNameAsBuilderFromBuilderWorks() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");
        builder.remove(CssClassNames.newBuilder().add("class3", "class2"));

        assertThat(builder.asString(), is(equalTo("class1")));
    }
}
