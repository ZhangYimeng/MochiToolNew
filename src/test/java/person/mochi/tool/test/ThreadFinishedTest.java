package person.mochi.tool.test;

public class ThreadFinishedTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new SelfRunnable());
        t.start();
        while (true) {
            Thread.sleep(500);
            System.out.println(t.isAlive());
        }
    }

    private static class SelfRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(500);
                    System.out.println("正在训练中…………");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
