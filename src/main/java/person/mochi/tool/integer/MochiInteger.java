package person.mochi.tool.integer;

public class MochiInteger {

    private Long integer;

    public MochiInteger() {
        integer = new Long(0);
    }

    public MochiInteger(long num) {
        integer = new Long(num);
    }

    public Long getInteger() {
        return integer;
    }

    public void addUp(long num) {
        integer += num;
    }

    public void increase() {
        integer++;
    }

    public void multiply(long num) {
        integer *= num;
    }

    public void makeDouble() {
        integer *= 2;
    }

}
