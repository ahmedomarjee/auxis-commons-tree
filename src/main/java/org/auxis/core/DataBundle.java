package org.auxis.core;

import org.auxis.commons.tree.Tree;

import java.io.InputStream;

/**
 * Data Bundle is a piece of data.
 * It should have all properties of a modular we find in software: high cohesion, lose coupling..
 *
 * It has a deep index structure ({@link Tree}) as well as {@link DataBundleMetadata} attached.
 *
 */
public interface DataBundle
{
    /**
     * @return the main tree with access to all data parts.
     */
    Tree index();

    /**
     * Allows you to access all (main index) or parts (sub tree) of the underlying data.
     *
     * @return The data portion as selected by Tree
     */
    InputStream data( Tree key );

    /**
     * @return Metadata of this Bundle.
     */
    DataBundleMetadata metadata();

}
