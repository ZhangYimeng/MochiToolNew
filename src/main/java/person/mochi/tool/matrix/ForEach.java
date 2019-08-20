package person.mochi.tool.matrix;

import person.mochi.tool.matrix.invisible.Consumer;
import person.mochi.tool.matrix.invisible.Pool;
import person.mochi.tool.matrix.invisible.Provider;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class ForEach {

    private int column;
    private int row;
    private long worker;
    private List<Runnable> threads;
    private AtomicLong index;
    private long totalCount;
    private Pool pool;
    private Random random;

    public ForEach(int column, int row, int worker) {
        this.column = column;
        this.row= row;
        this.worker = worker;
        totalCount = row * column;
        index = new AtomicLong(0);
        pool = new Pool(column * row);
        random = new Random();
    }

    public void innitial() {

    }

    private class InnerInnitial implements Runnable {

        private Provider provider;

        public InnerInnitial(Provider provider) {
            this.provider = provider;
        }

        @Override
        public void run() {
            while (index.get() < totalCount) {
                index.incrementAndGet();
                provider.put(random.nextInt(1000));
            }
        }
    }

    private class InnerCompute implements Runnable {

        private Consumer consumer;

        public InnerCompute(Consumer consumer) {
            this.consumer = consumer;
        }

        @Override
        public void run() {

        }
    }

}
