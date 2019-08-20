package person.mochi.tool.matrix.invisible;

public class Consumer {

    private Pool pool;

    public Consumer(Pool pool) {
        this.pool = pool;
    }

    public long consume() {
        return pool.poll();
    }

}
