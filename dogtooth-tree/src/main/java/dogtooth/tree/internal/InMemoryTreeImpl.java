/*
 * Copyright (c) 2012-2013 rebaze GmbH
 * All rights reserved. 
 * 
 * This library and the accompanying materials are made available under the terms of the Apache License Version 2.0,
 * which accompanies this distribution and is available at http://www.apache.org/licenses/LICENSE-2.0.
 *
 */
package dogtooth.tree.internal;

import dogtooth.tree.Tree;
import dogtooth.tree.TreeTools;

public class InMemoryTreeImpl implements Tree {

	final private String m_hashValue;
	final private Tree[] m_subs;
	final private String m_selector;
	final private long m_size;

	public InMemoryTreeImpl(  String selector, String hashValue,Tree[] subs) {
		m_selector = selector;
		m_hashValue = hashValue;
		m_subs = subs;
		long total = 1; // self
		for (Tree h : subs) {
			total += h.effectiveSize();
		}
		m_size = total;
	}
	
	@Override
	public String fingerprint() {
		return m_hashValue;
	}
	
	@Override
	public String selector() {
		return m_selector;
	}

	@Override
	public Tree[] branches() {
		return m_subs;
	}
	
	public String toString() {
		return m_hashValue.substring(0,6) + " /Selector: " + m_selector + " /Children: " + m_subs.length + " /Total: " + m_size; 
	}
	
	public int hashCode() {
		return m_hashValue.hashCode();
	}
	
	public boolean equals(Object other) {
		if (other instanceof Tree ) {
			Tree sn2 = (Tree)other;
			return (new TreeTools().compare(this, sn2).branches().length == 0);
		}else {
			throw new RuntimeException("Should not come here..");
		}
	}

	@Override
	public long effectiveSize() {
		return m_size;
	}
}
