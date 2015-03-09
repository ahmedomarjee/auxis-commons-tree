package org.auxis.commons.tree;

import org.auxis.commons.tree.util.TreeConsoleFormatter;
import org.auxis.commons.tree.util.TreeSession;
import org.junit.Test;

import static org.auxis.commons.tree.Selector.selector;
import static org.junit.Assert.assertEquals;

/**
 * Created by tonit on 05/03/15.
 */
public class TreeOperationsTest {
    private TreeConsoleFormatter formatter = new TreeConsoleFormatter();
    private TreeSession session = TreeSession.getSession();

    @Test
    public void testCombinerIntegrity() {
        TreeBuilder sn1 = session.createTreeBuilder();
        sn1.branch(selector("p1")).add("one".getBytes());
        sn1.branch(selector("p2")).add("two".getBytes());

        TreeBuilder sn2 = session.createTreeBuilder();
        sn2.branch(selector("p1")).add( "one".getBytes() );
        sn2.branch(selector("p3")).add( "other".getBytes() );

        Tree intersection = session.intersection(sn1.seal(), sn2.seal());
        Tree difference = session.delta( sn1.seal(), sn2.seal() );
        Tree union = session.union(sn1.seal(), sn2.seal());

        formatter.prettyPrint( sn1.seal(), sn2.seal(), intersection );

        assertEquals( union, session.union( difference, intersection ) );
        assertEquals( difference, session.delta( union, intersection ) );
        assertEquals( intersection, session.delta( union, difference ) );
    }
}
