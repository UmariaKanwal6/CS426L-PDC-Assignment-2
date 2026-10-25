public class TelemetryThread extends Thread {
    public TelemetryThread(String name) { super(name); }
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " | CPU and memory health update " + i);
            try { Thread.sleep(800); } catch (InterruptedException e) { interrupt(); return; }
        }
    }
}
