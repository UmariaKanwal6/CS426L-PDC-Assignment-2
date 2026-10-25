public class TradingEngineApp {
    public static void main(String[] args) {
        TradeEvent event1 = new FIXOrderEvent("Gateway-FIX", System.currentTimeMillis(), 1500.00, "ORD-101");
        TradeEvent event2 = new ReutersQuoteEvent("Reuters-GW", System.currentTimeMillis(), 1500.00, 1499.50, 1500.25);
        TradeEvent event3 = new TradeEvent("Internal-System", System.currentTimeMillis(), 1500.00);

        event1.dispatch();
        event2.dispatch();
        event3.dispatch();
    }
}
