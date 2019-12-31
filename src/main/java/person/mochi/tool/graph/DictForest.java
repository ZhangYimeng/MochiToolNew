package person.mochi.tool.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import person.mochi.tool.graph.foundation.DictForestNode;

public class DictForest<K> {

    public final int layerIndex = 0;
    public DictForestNode<K> root;
    public GrossMap gross;

    public DictForest(K forestNasme) {
        root = new DictForestNode<K>(forestNasme, layerIndex);
        gross = new GrossMap();
    }

    public boolean containsKey(K key) {
        return gross.containsKey(key);
    }

    public List<DictForestNode<K>> getNodesFromGross(K key) {
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
        root.addSubNode(node);
        gross.put(node);
    }

    public void addKeySerial(List<K> keys) {
        DictForestNode<K> node = root;
        int currentLayerIndex = 0;
        ;
        for (K key : keys) {
            DictForestNode<K> subNode = node.getSubNode(key);
            if (subNode == null) {
                subNode = new DictForestNode<K>(key, ++currentLayerIndex);
                node.addSubNode(subNode);
                gross.put(subNode);
            }
            node = subNode;
        }
    }

    private String traverse(DictForestNode<K> node) {
        StringBuffer sb = new StringBuffer();
        sb.append(node.getThisNodeKey() + "(");
        Iterator<Entry<K, DictForestNode<K>>> it = node.getSubNodeEntries();
        while (it.hasNext()) {
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

    private class GrossMap {

        private Map<K, List<DictForestNode<K>>> gorss;

        public GrossMap() {
            gorss = new HashMap<K, List<DictForestNode<K>>>();
        }

        public void put(DictForestNode<K> node) {
            if (gorss.containsKey(node.getThisNodeKey())) {
                List<DictForestNode<K>> nodesList = gorss.get(node.getThisNodeKey());
                if (!nodesList.contains(node)) {
                    nodesList.add(node);
                }
            } else {
                List<DictForestNode<K>> nodesList = new LinkedList<DictForestNode<K>>();
                nodesList.add(node);
                gorss.put(node.getThisNodeKey(), nodesList);
            }
        }

        public List<DictForestNode<K>> get(K key) {
            return gorss.get(key);
        }

        public boolean containsKey(K key) {
            return gorss.containsKey(key);
        }

    }

    public static void main(String[] args) {
        DictForest<String> df = new DictForest<String>("ABC");
        List<String> list = new LinkedList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        df.addKeySerial(list);
        list = new LinkedList<String>();
        list.add("a");
        list.add("q");
        list.add("c");
        list.add("d");
        df.addKeySerial(list);
        list = new LinkedList<String>();
        list.add("a");
        list.add("q");
        list.add("c");
        list.add("d");
        df.addKeySerial(list);
        list = new LinkedList<String>();
        list.add("q");
        list.add("q");
        list.add("c");
        list.add("d");
        df.addKeySerial(list);
        list = new LinkedList<String>();
        list.add("a");
        list.add("a");
        list.add("c");
        list.add("d");
        df.addKeySerial(list);
        System.out.println(df);
        System.out.println(df.containsKey("q"));
        System.out.println(df.getNodesFromGross("a"));
    }

}
