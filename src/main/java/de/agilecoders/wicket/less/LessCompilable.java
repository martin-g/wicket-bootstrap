package de.agilecoders.wicket.less;

import java.io.IOException;
import java.util.List;

/**
 * TODO: document
 *
 * @author miha
 * @version 1.0
 */
public interface LessCompilable {

    List<Resource> files();

    void writeTo(byte[] content) throws IOException;
}
