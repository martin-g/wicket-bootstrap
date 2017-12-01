package de.agilecoders.wicket.core.markup.html.bootstrap.dialog;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.Test;

/**
 * Test ensures the Modal component renders without throwing an exception. This test
 * was originally created for this issue https://github.com/l0rdn1kk0n/wicket-bootstrap/issues/713.
 */
public class ModalTest extends WicketApplicationTest
{
    private static final String MARKUP = "<html><head></head><body><div wicket:id=\"id\"></div></body></html>'";

    @Test
    public void rendersWithoutException()
    {
        startComponentInPage(new ModalTestPanel(id()), MARKUP);
    }

}
