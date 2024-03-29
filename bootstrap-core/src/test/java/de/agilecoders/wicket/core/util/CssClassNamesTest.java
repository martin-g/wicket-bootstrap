package de.agilecoders.wicket.core.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import de.agilecoders.wicket.jquery.util.Generics2;

/**
 * Tests the {@link CssClassNames} class
 *
 * @author miha
 */
class CssClassNamesTest {

    @Test
    void splitSplitsStringCorrect() {
        assertThat(CssClassNames.split("class1 class2 class3"), is(equalTo(Set.of("class1", "class2", "class3"))));
    }

    @Test
    void joinJoinsClassNamesCorrect() {
        assertThat(CssClassNames.join(Generics2.newLinkedHashSet(List.of("class1", "class2", "class3"))), is(equalTo("class1 class2 class3")));
    }

    @Test
    void containsReturnsFalseForNonExistingClassName() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");

        assertThat(builder.contains("class"), is(equalTo(false)));
        assertThat(builder.contains("class10"), is(equalTo(false)));
    }

    @Test
    void containsReturnsTrueForExistingClassName() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");

        assertThat(builder.contains("class1"), is(equalTo(true)));
        assertThat(builder.contains("class2"), is(equalTo(true)));
        assertThat(builder.contains("class3"), is(equalTo(true)));
    }

    @Test
    void parseReturnsBuilderWithAllGivenClassNames() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");

        assertThat(builder.asSet(), is(equalTo(Set.of("class1", "class2", "class3"))));
    }

    @Test
    void addClassNameAsStringToBuilderWorks() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");
        builder.add("class4", "class5");

        assertThat(builder.asSet(), is(equalTo(Set.of("class1", "class2", "class3", "class4", "class5"))));
    }

    @Test
    void addClassNameAsSetToBuilderWorks() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");
        builder.add(Set.of("class4", "class5"));

        assertThat(builder.asSet(), is(equalTo(Set.of("class1", "class2", "class3", "class4", "class5"))));
    }

    @Test
    void addClassNameAsBuilderToBuilderWorks() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");
        builder.add(CssClassNames.newBuilder().add("class4", "class5"));

        assertThat(builder.asString(), is(equalTo("class1 class2 class3 class4 class5")));
    }

    @Test
    void removeClassNameAsStringFromBuilderWorks() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");
        builder.remove("class2", "class3");

        assertThat(builder.asString(), is(equalTo("class1")));
    }

    @Test
    void removeClassNameAsSetFromBuilderWorks() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");
        builder.remove(Set.of("class2", "class3"));

        assertThat(builder.asSet(), is(equalTo(Set.of("class1"))));
    }

    @Test
    void removeClassNameAsBuilderFromBuilderWorks() {
        CssClassNames.Builder builder = CssClassNames.parse("class1 class2 class3");
        builder.remove(CssClassNames.newBuilder().add("class3", "class2"));

        assertThat(builder.asString(), is(equalTo("class1")));
    }
}
