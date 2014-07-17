package de.agilecoders.wicket.core.markup.html.bootstrap.navbar;

import de.agilecoders.wicket.core.test.TestCategory;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.mockito.Mockito.*;

/**
 * Tests the {@link ScrollSpyBehavior} class
 *
 * @author miha
 * @version 1.0
 */
@Category(TestCategory.UnitTest.class)
public class ScrollSpyBehaviorTest {

    @Test
    public void refreshScriptIsCorrect() {
        AjaxRequestTarget target = mock(AjaxRequestTarget.class);
        ScrollSpyBehavior.refresh(target);

        verify(target, times(1)).appendJavaScript("$('[data-spy=\\\"scroll\\\"]').each(function(){var $spy = $(this).scrollspy('refresh');});");
    }

}
