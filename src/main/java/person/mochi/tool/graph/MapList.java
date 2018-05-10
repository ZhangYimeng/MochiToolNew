package person.mochi.tool.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapList<K, T> {

	private Map<K, List<T>> classifierBag;

	public MapList() {
		classifierBag = new HashMap<K, List<T>>();
	}

	/**
	 * To see the given object belongs to which sub list.
	 * 
	 * @param object
	 *            the given object.
	 * @return index of sub list, null if there is no this object.
	 */
	public K belong(T object) {
		Iterator<Entry<K, List<T>>> it = classifierBag.entrySet().iterator();
		while (it.hasNext()) {
			Entry<K, List<T>> entry = it.next();
			if (entry.getValue().contains(object)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Add an object to specified list.
	 * 
	 * @param index
	 *            the index of sub list.
	 * @param object
	 *            the object to add to list.
	 */
	public void put(K index, T object) {
		if (classifierBag.containsKey(index)) {
			classifierBag.get(index).add(object);
		} else {
			List<T> list = new ArrayList<T>();
			list.add(object);
			classifierBag.put(index, list);
		}
	}

	/**
	 * Get specified sub list.
	 * 
	 * @param index
	 *            the index of sub list.
	 * @return the specified sub list.
	 */
	public List<T> getSubList(K index) {
		return classifierBag.get(index);
	}

}
