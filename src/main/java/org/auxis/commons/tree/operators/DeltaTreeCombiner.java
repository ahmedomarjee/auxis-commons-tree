package org.auxis.commons.tree.operators;

import org.auxis.commons.tree.*;
import org.auxis.commons.tree.annotated.Tag;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import static org.auxis.commons.tree.util.TreeSession.wrapAsIndex;

/**
 * This {@link TreeCombiner} creates a special diff tree containing
 * changes from both inputs annotated with modification tags.
 *
 * It is different to a regular diff where the resulting tree honors
 * combinator rules together with Union and Intersection.
 *
 * @author Toni Menzel (rebaze)
 * @since 0.3
 */
@Singleton
public class DeltaTreeCombiner implements TreeCombiner
{
    private final Provider<TreeBuilder> treeBuilderProvider;

    @Inject
    public DeltaTreeCombiner( Provider<TreeBuilder> builder )
    {
        treeBuilderProvider = builder;
    }

    @Override
    public Tree combine( Tree left, Tree right )
    {
        TreeBuilder builder = treeBuilderProvider.get();
        compare( builder, wrapAsIndex( left ), wrapAsIndex( right ) );
        return builder.seal();
    }

    /**
     *
     */
    private void compare( TreeBuilder collector, TreeIndex left, TreeIndex right )
    {

    }
}
