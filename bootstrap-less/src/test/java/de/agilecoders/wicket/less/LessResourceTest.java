package de.agilecoders.wicket.less;

import org.apache.wicket.util.time.Time;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Tests the {@link LessResource} class
 *
 * @author miha
 */
public class LessResourceTest {

    @Test
    public void getInputStreamReturnsCorrectContent() {
        final LessResource lessResource = new LessResource(LessResourceTest.class, "resources/test-a.less");

        assertThat(lessResource.asText(), is(equalTo(".rule {background: #FFF;}")));
    }

    @Test
    public void getRelativePathOutsideOfCurrentScopeWillReturnCorrectRelativeLessResource() {
        final LessResource lessResource = new LessResource(LessResourceTest.class, "resources/test-a.less");
        final LessResource relative = lessResource.getRelative("../../resources/test-c.less");

        assertThat(relative.getPath(), is(equalTo("../resources/test-c.less")));
    }

    @Test
    public void getRelativePathOutsideOfCurrentScopeWillReturnCorrectRelativeLessResourceContent() {
        final LessResource lessResource = new LessResource(LessResourceTest.class, "resources/test-a.less");
        final LessResource relative = lessResource.getRelative("../../resources/test-c.less");

        assertThat(relative.asText(), is(equalTo(".rule {background: #XXX;}")));
    }

    @Test
    public void getRelativeWillReturnCorrectRelativeLessResource() {
        final LessResource lessResource = new LessResource(LessResourceTest.class, "resources/test-a.less");
        final LessResource relative = lessResource.getRelative("test-b.less");

        assertThat(relative.getPath(), is(equalTo("resources/test-b.less")));
    }

    @Test
    public void getRelativeWillReturnCorrectRelativeLessResourceContent() {
        final LessResource lessResource = new LessResource(LessResourceTest.class, "resources/test-a.less");
        final LessResource relative = lessResource.getRelative("test-b.less");

        assertThat(relative.asText(), is(equalTo(".rule {background: #CCC;}")));
    }

    @Test
    public void lastModificationTimeIsValid() {
        final LessResource lessResource = new LessResource(LessResourceTest.class, "resources/test-a.less");

        assertThat(lessResource.getLastModificationTime().before(Time.now()), is(equalTo(true)));
        assertThat(lessResource.getLastModificationTime().after(Time.START_OF_UNIX_TIME), is(equalTo(true)));
    }

    @Test
    public void existsReturnsTrueForExistingResource() {
        final LessResource lessResource = new LessResource(LessResourceTest.class, "resources/test-a.less");

        assertThat(lessResource.exists(), is(equalTo(true)));
    }

    @Test
    public void getNameExtractsCorrectFileName() {
        final LessResource lessResource = new LessResource(LessResourceTest.class, "resources/test-a.less");

        assertThat(lessResource.getName(), is(equalTo("test-a.less")));
    }

    @Test
    public void getPathReturnsCorrectPath() {
        final LessResource lessResource = new LessResource(LessResourceTest.class, "resources/test-a.less");

        assertThat(lessResource.getPath(), is(equalTo("resources/test-a.less")));
    }

    @Test
    public void classPathResourceWillBeFound() {
        final LessResource lessResource = new LessResource(ClasspathResourceScope.class, "test.less");

        assertThat(lessResource.asText(), is(equalTo(".rule {background: #000;}")));
    }

    @Test
    public void classPathResourceWillBeFoundAlsoAResourceWithSameFileExistsInCurrentPath() {
        final LessResource lessResource = new LessResource(ClasspathResourceScope.class, "resources/test-a.less");

        assertThat(lessResource.asText(), is(equalTo(".rule {background: #AAA;}")));
    }

    @Test
    public void relativeResourceWillBeFoundAlsoAResourceWithSameFileExistsInClasspath() {
        final LessResource lessResource = new LessResource(LessResourceTest.class, "resources/test-a.less");

        assertThat(lessResource.asText(), is(equalTo(".rule {background: #FFF;}")));
    }
}
