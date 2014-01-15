package de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.WicketApplicationTest;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.tester.TagTester;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Tests the {@link Html5Player} class
 *
 * @author miha
 */
public class Html5PlayerTest extends WicketApplicationTest {

    @Test
    public void componentIsRendered() {
        List<Html5Player.IVideo> videoList = Lists.<Html5Player.IVideo>newArrayList(
                new Video("url", "type")
        );
        Html5Player player = new Html5Player("video", Model.ofList(videoList));

        tester().startComponentInPage(player);

        TagTester video = tester().getTagById("video1");
        Assert.assertThat("video", Matchers.equalTo(video.getName()));
        video.getAttributeIs("width", "370");
        video.getAttributeIs("height", "215");

        TagTester source = tester().getTagByWicketId("element");
        Assert.assertThat("source", Matchers.equalTo(source.getName()));

        tester().assertContains("Your browser does not support the video tag");
    }

}
