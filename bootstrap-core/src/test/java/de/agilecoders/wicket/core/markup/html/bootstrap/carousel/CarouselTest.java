package de.agilecoders.wicket.core.markup.html.bootstrap.carousel;

import com.google.common.collect.Lists;
import de.agilecoders.wicket.core.WicketApplicationTest;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.tester.TagTester;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the {@link Carousel} component
 *
 * @author miha
 */
class CarouselTest extends WicketApplicationTest {
    private static final String MARKUP = "<div wicket:id=\"id\"></div>";

    @Test
    public void renderingCarouselWorks() {
        startComponentInPage(new Carousel(id(), newImageList()), MARKUP);

        tester().assertNoErrorMessage();
    }

    @Test
    public void imageUrlsAreRendered() {
        List<ICarouselImage> images = newImageList();
        startComponentInPage(new Carousel(id(), images), MARKUP);

        List<TagTester> tags = tester().getTagsByWicketId("images");

        assertThat(tags.size(), is(equalTo(3)));

        int i = 0;
        for (ICarouselImage image : images) {
            TagTester imageTag = tags.get(i);

            TagTester tester = imageTag.getChild("src", image.url());
            assertThat(tester, is(not(nullValue())));
            assertThat(tester.getAttribute("src"), is(equalTo(image.url())));

            i++;
        }
    }

    @Test
    public void headersAreRendered() {
        List<ICarouselImage> images = newImageList();
        startComponentInPage(new Carousel(id(), images), MARKUP);

        List<TagTester> tags = tester().getTagsByWicketId("header");

        assertThat(tags.size(), is(equalTo(2)));

        for (ICarouselImage image : images) {
            if (!Strings.isEmpty(image.header())) {
                tester().assertContains("<h4 wicket:id=\"header\">" + image.header() + "</h4>");
            }
        }
    }

    @Test
    public void descriptionsAreRendered() {
        List<ICarouselImage> images = newImageList();
        startComponentInPage(new Carousel(id(), images), MARKUP);

        List<TagTester> tags = tester().getTagsByWicketId("header");

        assertThat(tags.size(), is(equalTo(2)));

        for (ICarouselImage image : images) {
            if (!Strings.isEmpty(image.description())) {
                tester().assertContains("<p wicket:id=\"description\">" + image.description() + "</p>");
            }
        }
    }

    /**
     * @return a list of carousel images
     */
    private List<ICarouselImage> newImageList() {
        return Lists.<ICarouselImage>newArrayList(
                new CarouselImage("http://wb.agilecoders.de/image1.png"),
                new CarouselImage("http://wb.agilecoders.de/image2.png", "header text"),
                new CarouselImage("http://wb.agilecoders.de/image3.png", "header text", "description")
        );
    }

}
