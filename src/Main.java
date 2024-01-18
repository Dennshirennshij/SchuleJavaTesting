public class Main {
    public static final String START = "Keller";
    public static final ExtentedQueue<Character> KELLER = new ExtentedQueue<>();

    public static void main(String[] args) {
        for(int i = START.length()-1; i >= 0; i--) {
            KELLER.queue(START.charAt(i));
        }
        while(!KELLER.isEmpty()) {
            System.out.println(KELLER.dequeue());
            System.out.println(KELLER.getLength());
        }
    }
}