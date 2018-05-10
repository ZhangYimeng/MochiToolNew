package person.mochi.tool.graph.foundation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class DictForestNode<K> {

	private K key;
	private int layerIndex;
	private Map<K, DictForestNode<K>> nodes;
	
	public DictForestNode(K key, int layerIndex) {
		this.key = key;
		this.layerIndex = layerIndex;
		nodes = new HashMap<K, DictForestNode<K>>();
	}
	
	public void addSubNode(DictForestNode<K> node) {
		nodes.put(node.getThisNodeKey(), node);
	}
	
	public DictForestNode<K> getSubNode(K key) {
		return nodes.get(key);
	}
	
	public int getLayerIndex() {
		return layerIndex;
	}
	
	public Iterator<Entry<K, DictForestNode<K>>> getSubNodeEntries() {
		return nodes.entrySet().iterator();
	}
	
	public K getThisNodeKey() {
		return key;
	}
	
	private String traverse(DictForestNode<K> node) {
		StringBuffer sb = new StringBuffer();
		sb.append(node.getThisNodeKey() + "(");
		Iterator<Entry<K, DictForestNode<K>>> it = node.getSubNodeEntries();
		while(it.hasNext()) {
			Entry<K, DictForestNode<K>> entry = it.next();
			String subForest = traverse(entry.getValue());
			sb.append(subForest);
		}
		sb.append(")");
		return sb.toString();
	}
	
	public String toString() {
		return traverse(this);
	}
	
}
