package de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.hamcrest.Matchers;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.WicketApplicationTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link Html5Player} class
 *
 * @author miha
 */
class Html5PlayerTest extends WicketApplicationTest {

    @Test
    void componentIsRendered() {
        List<Html5Player.IVideo> videoList = List.of(
                new Video("url", "type")
        );
        Html5Player player = new Html5Player("video", Model.ofList(videoList));

        tester().startComponentInPage(player);

        TagTester video = tester().getTagById("video1");
        assertThat("video", Matchers.equalTo(video.getName()));
        video.getAttributeIs("width", "370");
        video.getAttributeIs("height", "215");

        TagTester source = tester().getTagByWicketId("element");
        assertThat("source", Matchers.equalTo(source.getName()));

        tester().assertContains("Your browser does not support the video tag");
    }

}
