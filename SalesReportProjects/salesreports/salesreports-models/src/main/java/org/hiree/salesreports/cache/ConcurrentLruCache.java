/**
 * @(#)ConcurrentLruCache.java
 * @author SVARMA001
 * @date Sep 7, 2017
 * 
 * $Id: ConcurrentLruCache.java $
 * 
 * Copyright (c) 2017 Educational Testing Service. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Educational Testing Service.
 * ("Confidential Information").
 */
package org.hiree.salesreports.cache;

import java.util.HashSet;
import java.util.Set;

/**
 * <span style="color: Purple; font-weight:bold;">Concurrent LRU Cache Implementation</span><br>
 * <br>
 * Simple synchronization applied to all actions on a LinkedHashMap
 * used as the {@link IBISLruCache LRU Cache} to make it thread-safe
 * 
 * @see IBISLruCache IBISLruCache
 */
public class ConcurrentLruCache<K, V> {
	protected int maxSize;
	protected IBISLruCache<K,V> map = null;
	
	protected static final Set<Object> EMPTY_SET = new HashSet<Object>(0);
	
	/**
	 * {@link ConcurrentLruCache Concurrent LRU Cache} Constructor
	 * 
	 * @param maxSize Maximum size of cache. 0 indicates unlimited size
	 */
	public ConcurrentLruCache(int maxSize) {
		this.maxSize = maxSize;
		this.map = new IBISLruCache<K, V>(maxSize);
	}
	
	/**
	 * Maximum-possible cache size
	 * 
	 * @return <b>int</b> Maximum size of cache
	 */
	public int getMaxSize() {
		return maxSize;
	}
	
	/**
	 * Returns entry for <b>key</b>, or null if not found
	 * 
	 * @param key Key
	 * @return {@code V}
	 */
	public V get(K key) {
		// Make "get" thread-safe
		synchronized(map) {
			return map.get(key);
		}
	}
	
	/**
	 * Sets entry for <b>key</b>
	 * 
	 * @param key Key
	 * @param value Value
	 * @return <b>V</b> Returns prior value stored at key, if found.
	 * 		Else returns current value
	 */
	public V put(K key, V value) {
		// Make "put" thread-safe
		synchronized(map) {
			return map.put(key, value);
		}
	}
	
	/**
	 * Removes entry for <b>key</b>
	 * 
	 * @param key Key
	 * @return <b>V</b> Returns prior object {@code V} stored at key, if found
	 */
	public V remove(K key) {
		// Make "remove" thread-safe
		synchronized(map) {
			return map.remove(key);
		}
	}
	
	/**
	 * Clears all entries from cache
	 */
	public void clear() {
		// Make "clear" thread-safe
		synchronized(map) {
			map.clear();
		}
	}
	
	/**
	 * Current cache size
	 * 
	 * @return <b>int</b> Size
	 */
	public int size() {
		// Make "size" thread-safe
		synchronized(map) {
			return map.size();
		}
	}
	
	/**
	 * Returns new Set containing Keys from cache, so that caller can
	 * iterate over Key Set without concerns about thread-safety. This Set
	 * should ideally not be modified by caller, and considered Read-Only
	 * 
	 * @return {@code Set<K>}
	 */
	@SuppressWarnings("unchecked")
	public Set<K> keys() {
		// Make "keys" thread-safe
		synchronized(map) {
			if (map.size()==0) {
				return (Set<K>) EMPTY_SET;
			}
			return new HashSet<K>(map.keySet());
		}
	}
}