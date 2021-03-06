package org.auxis.commons.tree.operators;

import org.auxis.commons.tree.Tree;
import org.auxis.commons.tree.TreeBuilder;
import org.auxis.commons.tree.TreeCombiner;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * Rebuilds left with paths only where right is a leaf.
 *
 * Helpful for relocating subtrees within larger trees.
 *
 */
@Singleton
public class SubstructCombiner implements TreeCombiner
{
    private final Provider<TreeBuilder> treeBuilderProvider;

    @Inject
    public SubstructCombiner( Provider<TreeBuilder> builder )
    {
        treeBuilderProvider = builder;
    }

    @Override public Tree combine( Tree left, Tree right )
    {
        TreeBuilder tb = treeBuilderProvider.get();
        walk(tb,left,right);
        return tb.seal();
    }

    private void walk( TreeBuilder tb, Tree base, Tree target )
    {
        boolean relevant = false;
        //depth first:
        for (Tree t : base.branches()) {
            if (t.equals( target )) {
                relevant = true;
            }else {

            }
            walk(tb,t,target);
        }
        if (relevant) {
            tb.branch( base );
        }
    }
}
