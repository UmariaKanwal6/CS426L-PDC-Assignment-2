public class PacketSymmetryAuditor {
    public static void main(String[] args) {
        String payload = "ABCDCBA"; // Change this test payload if desired
        TransactionStack stack = new TransactionStack(payload.length());
        RequestQueue queue = new RequestQueue(payload.length());
        for (char c : payload.toCharArray()) { stack.push(c); queue.enqueue(c); }
        boolean symmetric = true;
        while (!stack.isEmpty() && !queue.isEmpty()) {
            if (stack.pop() != queue.dequeue()) { symmetric = false; break; }
        }
        System.out.println("Packet payload: " + payload);
        System.out.println("Symmetry verified: " + symmetric);
    }
}
