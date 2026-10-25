public class AsyncWebEngineApp {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread: " + Thread.currentThread().getName());
        TelemetryThread telemetry = new TelemetryThread("Telemetry-Worker");
        Thread logger = new Thread(new LogProcessorTask(), "Log-Worker");
        telemetry.start(); logger.start();
        System.out.println("Telemetry state: " + telemetry.getState());
        System.out.println("Log worker state: " + logger.getState());
        telemetry.join(); logger.join();
        System.out.println("Both workers completed.");
    }
}
