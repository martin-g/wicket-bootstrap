package de.agilecoders.wicket.less;

import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.ResourceReferenceRegistry;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class LessResourceReferenceFactoryTest extends Assert {

    @Test
    public void createLessResourceReference() {
        TestApplication application = new TestApplication();
        WicketTester tester = new WicketTester(application);
        try {
            ResourceReferenceRegistry registry = application.getResourceReferenceRegistry();
            ResourceReference.Key key = new ResourceReference.Key(LessResourceReferenceFactoryTest.class.getName(),
                                                                  "responsive.less", null, null, null);
            ResourceReference reference = registry.getResourceReference(key, false, true);
            assertThat(reference, is(instanceOf(LessResourceReference.class)));
        } finally {
            tester.destroy();
        }
    }
}
