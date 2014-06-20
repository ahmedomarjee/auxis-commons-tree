/*
 * Copyright (c) 2012-2013 rebaze GmbH
 * All rights reserved. 
 * 
 * This library and the accompanying materials are made available under the terms of the Apache License Version 2.0,
 * which accompanies this distribution and is available at http://www.apache.org/licenses/LICENSE-2.0.
 *
 */
package dogtooth.tree.internal;

import dogtooth.tree.Selector;
import dogtooth.tree.Tree;
import dogtooth.tree.annotated.Tag;
import dogtooth.tree.util.TreeTools;
import static dogtooth.tree.util.TreeCompare.*;

/**
 * Default implementation not really suitable for very large trees but fast and simple.
 *
 * @author Toni Menzel <toni.menzel@rebaze.com>
 *
 */
public class InMemoryTreeImpl implements Tree {

	final private String m_hashValue;
	final private Tree[] m_subs;
	final private Selector m_selector;
    final private Tag m_tag;
    private final TreeTools m_tools;

    public InMemoryTreeImpl(TreeTools treeTools, Selector selector, String hashValue, Tree[] subs, Tag tag) {
        m_tools = treeTools;
		m_selector = selector;
		m_hashValue = hashValue;
		m_subs = subs;
		m_tag = tag;
	}
	
	@Override
	public String fingerprint() {
		return m_hashValue;
	}
	
	@Override
	public Selector selector() {
		return m_selector;
	}

	@Override
	public Tree[] branches() {
		return m_subs;
	}
	
	public String toString() {
		return m_hashValue.substring(0,6) + " /Selector: " + m_selector + " /Children: " + m_subs.length; 
	}
	
	public int hashCode() {
		return m_hashValue.hashCode();
	}
	
	public boolean equals(Object other) {
		if (other instanceof Tree ) {
			Tree sn2 = (Tree)other;
			Tree compare = m_tools.compare( this, sn2 );
            return (compare.branches().length == 0);
		}else {
			throw new RuntimeException("Should not come here..");
		}
	}

    @Override
    public Tag tags() {
        return m_tag;
    }
}
