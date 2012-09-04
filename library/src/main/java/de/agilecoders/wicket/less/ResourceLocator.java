package de.agilecoders.wicket.less;

/**
 * A {@link ResourceLocator} that looks in a folder in the classpath to find
 * a given resource.
 *
 * @author miha
 * @version 1.0
 */
public class ResourceLocator {

    /**
     * searchs for a given path relative to the given class.
     *
     * @param clazz The scope
     * @param path  The path of resource to locate
     * @return the found resource
     */
    public Resource findResource(Class clazz, String path) {
        if (clazz != null && path != null) {
            final String className = "/" + toPath(clazz.getPackage().getName()) + "/" + path;

            return new Resource(clazz, className);
        }

        throw new IllegalArgumentException("invalid class/path");
    }

    /**
     * transforms a given package name to a path name
     *
     * @param name the package name
     * @return a path name
     */
    private String toPath(String name) {
        return name.replaceAll("\\.", "/");
    }

}

