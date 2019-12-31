package person.mochi.tool.graph;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class CountMap<T> {

    private HashMap<T, AtomicInteger> countMap;

    public CountMap() {
        countMap = new HashMap<T, AtomicInteger>();
    }

    public void put(T object, int time) {
        if (countMap.containsKey(object)) {
            countMap.get(object).addAndGet(time);
        } else {
            countMap.put(object, new AtomicInteger(time));
        }
    }

    public void put(T object) {
        if (countMap.containsKey(object)) {
            countMap.get(object).incrementAndGet();
        } else {
            countMap.put(object, new AtomicInteger(1));
        }
    }

    public int getCount(T object) {
        return countMap.get(object).get();
    }

    public Set<Entry<T, AtomicInteger>> getEntrySet() {
        return countMap.entrySet();
    }

}
