/**
 * @(#)IBISLruCache.java
 * @author PAllam
 * @date July 20, 2011
 *
 * $Id: IBISLruCache.java $
 *
 * Copyright (c) 2011 Educational Testing Service. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Educational
 * Testing Service. ("Confidential Information").
 */
package org.hiree.salesreports.cache;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * <span style="color: Purple; font-weight:bold;">LRU Cache Implementation</span><br>
 * <br>
 * Least Recently Used (LRU) Cache implementation using LinkedHashMap. For a thread-safe
 * implementation, use {@link ConcurrentLruCache} instead
 * 
 * @author PAllam
 *
 * @param <K> Key
 * @param <V> Value
 * 
 * @see ConcurrentLruCache
 */
public class IBISLruCache<K, V> extends LinkedHashMap<K, V> {
	
	private static final long serialVersionUID = 3191174607594424808L;
	private int mMaxEntries;
	
	/**
	 * removeEldestEntry() is called after a put(). To allow maxEntries in
	 * cache, capacity should be maxEntries + 1 (+1 for the entry which will
	 * be removed). Load factor is taken as 1 because size is fixed. This is
	 * less space efficient when very less entries are present, but there
	 * will be no effect on time complexity for get(). The third parameter
	 * in the base class constructor says that this map is access-order oriented.<br>
	 * <br>
	 * Passing 0 for <b>maxEntries</b> indicates a unlimited sized map
	 * 
	 * @param maxEntries Maximum size of cache. 0 indicates unlimited size
	 */
	public IBISLruCache(int maxEntries) {
		super((maxEntries>0?maxEntries:10) + 1, maxEntries>0?1f:0.75f, true);
		this.mMaxEntries = maxEntries;
	}
	
	/**
	 * After size exceeds max entries, this statement returns true and the
	 * oldest value will be removed. Since this map is access oriented the
	 * oldest value would be least recently used.<br>
	 * <br>
	 * If <b>maxEntries</b> is 0, then always returns false
	 * 
	 * @param eldest Eldest entry
	 */
	@Override
	protected boolean removeEldestEntry(Entry<K, V> eldest) {
		if (mMaxEntries>0) {
			return size() > mMaxEntries;
		}
		
		// Unlimited cache, hence return false
		return false;
	}
}
