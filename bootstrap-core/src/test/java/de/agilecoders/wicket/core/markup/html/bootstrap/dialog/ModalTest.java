package de.agilecoders.wicket.core.markup.html.bootstrap.dialog;

import de.agilecoders.wicket.core.WicketApplicationTest;
import org.junit.Test;

public class ModalTest extends WicketApplicationTest
{
    private static final String MARKUP = "<html><head></head><body><div wicket:id=\"id\"></div></body></html>'";

    @Test
    public void rendersWithoutException()
    {
        startComponentInPage(new ModalTestPanel(id()), MARKUP);
    }

}
