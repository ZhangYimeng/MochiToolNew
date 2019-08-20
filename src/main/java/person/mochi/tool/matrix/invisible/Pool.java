package person.mochi.tool.matrix.invisible;

import java.util.concurrent.ArrayBlockingQueue;

public class Pool {

    private ArrayBlockingQueue<Long> array;

    public Pool(int size) {
        array = new ArrayBlockingQueue<Long>(size);
    }

    public void put(long figure) throws InterruptedException {
        array.put(figure);
    }

    public long poll() {
        return array.poll();
    }

}
