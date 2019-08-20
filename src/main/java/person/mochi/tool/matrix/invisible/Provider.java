package person.mochi.tool.matrix.invisible;

public class Provider {

    private Pool pool;

    public Provider(Pool pool) {
        this.pool = pool;
    }

    public void put(long figure) {
        try {
            pool.put(figure);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
