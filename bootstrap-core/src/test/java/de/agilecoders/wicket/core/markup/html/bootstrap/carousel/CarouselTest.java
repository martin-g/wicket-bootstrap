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
    void renderingCarouselWorks() {
        startComponentInPage(new Carousel(id(), newImageList()), MARKUP);

        tester().assertNoErrorMessage();
    }

    @Test
    void imageUrlsAreRendered() {
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
    void headersAreRendered() {
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
    void descriptionsAreRendered() {
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

    @Test
    void controlsAreRendered() {
        List<ICarouselImage> images = newImageList();
        Carousel carousel = new Carousel(id(), images);
        startComponentInPage(carousel, MARKUP);

        TagTester prevTag = tester().getTagByWicketId("prev");
        assertCssClass(prevTag, "carousel-control-prev");
        assertThat(prevTag.getValue(), is(equalTo(carousel.createPrevLabel().getObject())));

        TagTester nextTag = tester().getTagByWicketId("next");
        assertCssClass(nextTag, "carousel-control-next");
        assertThat(nextTag.getValue(), is(equalTo(carousel.createNextLabel().getObject())));

    }

    /**
     * @return a list of carousel images
     */
    private List<ICarouselImage> newImageList() {
        return Lists.newArrayList(
                new CarouselImage("http://wb.agilecoders.de/image1.png"),
                new CarouselImage("http://wb.agilecoders.de/image2.png", "header text"),
                new CarouselImage("http://wb.agilecoders.de/image3.png", "header text", "description")
        );
    }

}
