/*
 * Copyright (c) 2012-2014 rebaze GmbH
 * All rights reserved. 
 * 
 * This library and the accompanying materials are made available under the terms of the Apache License Version 2.0,
 * which accompanies this distribution and is available at http://www.apache.org/licenses/LICENSE-2.0.
 *
 */
package org.auxis.commons.tree.internal;

import static org.auxis.commons.tree.Selector.*;
import static org.junit.Assert.*;

import org.auxis.commons.tree.Tree;
import org.auxis.commons.tree.TreeBuilder;
import org.auxis.commons.tree.util.TreeTools;
import org.junit.Test;

public class DefaultHashTest
{
    private TreeTools TOOLS = TreeTools.treeTools();

    @Test
    public void equalityTest()
    {
        Tree sn1 = TOOLS.createTreeBuilder().selector( selector( "c1" ) ).branch( selector( "d" ) ).add( "Some".getBytes() ).seal();
        Tree sn2 = TOOLS.createTreeBuilder().selector( selector( "c1" ) ).branch( selector( "d" ) ).add( "Some".getBytes() ).seal();
        Tree sn3 = TOOLS.createTreeBuilder().selector( selector( "c1" ) ).branch( selector( "d2" ) ).add( "Other".getBytes() ).seal();

        assertEquals( "Should be identical", sn1, sn2 );
        assertNotEquals( "Should not be identical", sn1, sn3 );
        assertNotEquals( "Should not be identical", sn2, sn3 );
        assertEquals( "Should be identical", sn3, sn3 );
    }

    @Test
    public void equalityTestBeAwareThatHashesArePrimary()
    {
        Tree sn1 = TOOLS.createTreeBuilder().selector( selector( "Here" ) ).branch( selector( "whatnow" ) ).add( "Some".getBytes() ).seal();
        Tree sn2 = TOOLS.createTreeBuilder().selector( selector( "There" ) ).branch( selector( "d" ) ).add( "Some".getBytes() ).seal();
        assertEquals( "Must be identical", sn1, sn2 );
    }

    @Test
    public void testAddOrderMatters()
    {
        Tree sn1 = TOOLS.createTreeBuilder().branch( selector( "a" ) ).add( "Some".getBytes() ).add("Other".getBytes() ).seal();
        Tree sn2 = TOOLS.createTreeBuilder().branch( selector( "a" ) ).add( "Some".getBytes() ).add( "Other".getBytes() ).seal();
        Tree sn3 = TOOLS.createTreeBuilder().branch( selector( "c" ) ).add( "Other".getBytes() ).add( "Some".getBytes() ).seal();

        assertEquals( "Must be identical", sn1, sn2 );
        assertNotEquals( "Must not be identical", sn1, sn3 );

        TreeBuilder tb = TOOLS.createTreeBuilder();
        tb.branch( sn1 );
        tb.branch( sn2 );
        tb.branch( sn3 );
        assertEquals( "Collabsed to 2 branches", 2, tb.seal().branches().length );
    }

    @Test
    public void testSubTreeOrderDoesNotMatter()
    {
        TreeBuilder sn1 = TOOLS.createTreeBuilder();
        sn1.branch( selector( "a" ) ).add( "Some".getBytes() );
        sn1.branch( selector( "b" ) ).add( "Other".getBytes() );

        TreeBuilder sn2 = TOOLS.createTreeBuilder();
        sn2.branch( selector( "a" ) ).add( "Other".getBytes() );
        sn2.branch( selector( "b" ) ).add( "Some".getBytes() );

        assertEquals( "Must be identical", sn1.seal(), sn2.seal() );
    }

    @Test
    public void testDeepEquality()
    {
        Tree sn1 = TOOLS.createTreeBuilder().selector( selector( "Here" ) ).branch( selector( "whatnow" ) ).branch( selector( "deeper" ) ).add( "Some".getBytes() ).seal();
        Tree sn2 = TOOLS.createTreeBuilder().selector( selector( "There" ) ).branch( selector( "d" ) ).add( "Some".getBytes() ).seal();
        assertEquals( "Must not be identical", sn1, sn2 );
    }
}
