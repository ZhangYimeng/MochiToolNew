package person.mochi.tool.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import person.mochi.tool.graph.foundation.DictForestNode;

public class DictForest<K> {

	public final int layerIndex = 0;
	public DictForestNode<K> root;
	public Map<K, DictForestNode<K>> gross;
	
	public DictForest(K forestNasme) {
		root = new DictForestNode<K>(forestNasme, layerIndex);
		gross = new HashMap<K, DictForestNode<K>>();
	}
	
	public boolean containsKey(K key) {
		return gross.containsKey(key);
	}
	
	public DictForestNode<K> getNodeFromGross(K key) {
		return gross.get(key);
	}
	
	public void setRoot(DictForestNode<K> root) {
		this.root = root;
	}
	
	public DictForestNode<K> getRoot() {
		return root;
	}
	
	public K getDictForestName() {
		return root.getThisNodeKey();
	}
	
	public void setRoot(K rootKey) {
		this.root = new DictForestNode<K>(rootKey, layerIndex);
	}
	
	public DictForestNode<K> getSubNode(K key) {
		return root.getSubNode(key);
	}
	
	public void addSubNode(DictForestNode<K> node) {
		root.addSubNode(node.getThisNodeKey(), node);
		gross.put(node.getThisNodeKey(), node);
	}
	
	public void addKeySerial(List<K> keys) {
		DictForestNode<K> node = root;
		int currentLayerIndex = 0;;
		for(K key: keys) {
			DictForestNode<K> subNode = node.getSubNode(key);
			if(subNode == null) {
				subNode = new DictForestNode<K>(key, ++currentLayerIndex);
				node.addSubNode(key, subNode);
				gross.put(key, subNode);
			}
			node = subNode;
		}
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
		return traverse(root);
	}
	
	public static void main(String[] args) {
		DictForest<String> df = new DictForest<String>("ABC");
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		df.addKeySerial(list);
		list = new ArrayList<String>();
		list.add("a");
		list.add("q");
		list.add("d");
		df.addKeySerial(list);
		System.out.println(df);
		System.out.println(df.containsKey("q"));
		System.out.println(df.getNodeFromGross("q"));
	}
	
}
