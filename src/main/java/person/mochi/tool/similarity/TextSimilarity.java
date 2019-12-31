package person.mochi.tool.similarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import person.mochi.tool.graph.CountMap;

public class TextSimilarity {

    private List<String> l1;
    private List<String> l2;
    private HashMap<String, Integer> l1PositionMap;
    private HashMap<String, Integer> l2PositionMap;
    private CountMap<String> col;
    private CountMap<String> colForl1;
    private CountMap<String> colForl2;
    private Set<Entry<String, AtomicInteger>> set;
    private List<Integer> l2Sequence;

    public TextSimilarity(String text1, String text2) {
        l1 = new ArrayList<String>();
        l2 = new ArrayList<String>();
        col = new CountMap<String>();
        colForl1 = new CountMap<String>();
        colForl2 = new CountMap<String>();
        l1.addAll(Arrays.asList(text1.split("")));
        l2.addAll(Arrays.asList(text2.split("")));
        l1PositionMap = new HashMap<String, Integer>();
        l2Sequence = new ArrayList<Integer>();
        l2PositionMap = new HashMap<String, Integer>();
    }

    private List<String> intersect() {
        List<String> intersectList = new ArrayList<String>();
        Iterator<Entry<String, AtomicInteger>> it = set.iterator();
        while (it.hasNext()) {
            Entry<String, AtomicInteger> entry = it.next();
            if (entry.getValue().get() >= 2) {
                intersectList.add(entry.getKey());
            }
        }
        return intersectList;
    }

    private List<String> union() {
        List<String> unionList = new ArrayList<String>();
        Iterator<Entry<String, AtomicInteger>> it = set.iterator();
        while (it.hasNext()) {
            Entry<String, AtomicInteger> entry = it.next();
            unionList.add(entry.getKey());
        }
        return unionList;
    }

    public double caculateSimilarity() {
        int l1Length = l1.size();
        for (int i = 0; i < l1Length; i++) {
            String chara = l1.get(i);
            colForl1.put(chara);
            l1PositionMap.put(chara + colForl1.getCount(chara), i);
        }
        int l2Length = l2.size();
        for (int i = 0; i < l2Length; i++) {
            String chara = l2.get(i);
            colForl2.put(chara);
            l2PositionMap.put(chara + colForl2.getCount(chara), i);
            if (l1PositionMap.containsKey(chara + colForl2.getCount(chara))) {
                l2Sequence.add(l1PositionMap.get(chara + colForl2.getCount(chara)));
            }
        }
        int totalLength = l2Length + l1Length;
        Iterator<Entry<String, Integer>> itFor1 = l1PositionMap.entrySet().iterator();
        while (itFor1.hasNext()) {
            col.put(itFor1.next().getKey());
        }
        Iterator<Entry<String, Integer>> itFor2 = l2PositionMap.entrySet().iterator();
        while (itFor2.hasNext()) {
            col.put(itFor2.next().getKey());
        }
        set = col.getEntrySet();
        double sim = (double) intersect().size() / (double) union().size();
        int inversNumber = inverseNumber(l2Sequence);
        if (sim == 1) {
            if (inversNumber == 0) {
                return sim;
            } else {
                sim = sigmoid((-Math.log((double) (0.1 + inversNumber) / (double) intersect().size())) * sim);
                return modifiedSigmoid(totalLength) * sim;
            }
        } else if (sim == 0) {
            return sim;
        } else {
            // 方案1
            // return sim = sim - (sim / union().size()) * inversNumber;
            // 方案2
            sim = sigmoid((-Math.log((double) (0.1 + inversNumber) / (double) intersect().size())) * sim);
            return modifiedSigmoid(totalLength) * sim;
        }
    }

    public boolean caculateSimilarityAndJudge() {
        if (caculateSimilarity() > 0.75) {
            return true;
        } else {
            return false;
        }
    }

    // 用于字数修正
    private static double modifiedSigmoid(int x) {
        return 1 / (1 + Math.pow(Math.E, 3.4 - (double) x));
    }

    private static double sigmoid(double x) {
        return 1 / (1 + Math.pow(Math.E, -x));
    }

    // 求逆序数
    private int inverseNumber(List<Integer> sequence) {
        Iterator<Integer> it = sequence.iterator();
        int inverseNumber = 0;
        int pre = Integer.MIN_VALUE;
        int current = 0;
        while (it.hasNext()) {
            current = it.next();
            if (current < pre) {
                inverseNumber++;
            }
            pre = current;
        }
        return inverseNumber;
    }

    public static void main(String[] args) {
        TextSimilarity ts = new TextSimilarity("上海交通大学", "交");
        System.out.println(ts.caculateSimilarityAndJudge());
        ts = new TextSimilarity("上海交通大学", "交大");
        System.out.println(ts.caculateSimilarityAndJudge());
        ts = new TextSimilarity("长征四号丙型火箭", "长四丙火箭");
        System.out.println(ts.caculateSimilarityAndJudge());
        ts = new TextSimilarity("地球中国江苏省徐州市", "火星奥林匹克徐州");
        System.out.println(ts.caculateSimilarityAndJudge());
        ts = new TextSimilarity("睡眠不足", "睡眠良好");
        System.out.println(ts.caculateSimilarityAndJudge());
        ts = new TextSimilarity("中国的江苏省非常富裕", "江苏省富裕");
        System.out.println(ts.caculateSimilarityAndJudge());
        ts = new TextSimilarity("上海市浦东新区新金桥路27号金桥现代产业园区13号楼网达软件", "上海市浦东新区新金桥路27号13号楼");
        System.out.println(ts.caculateSimilarityAndJudge());
        ts = new TextSimilarity("上海", "上海");
        System.out.println(ts.caculateSimilarityAndJudge());
        ts = new TextSimilarity("上海", "海");
        System.out.println(ts.caculateSimilarityAndJudge());
    }

}
