public class Main {
    public static final String START = "Keller";
    public static final ADT_Storage<Character> KELLER = new Stack<Character>();

    public static void main(String[] args) {
        for(int i = START.length()-1; i >= 0; i--) {
            KELLER.push(START.charAt(i));
        }
        while(!KELLER.isEmpty()) {
            System.out.println(KELLER.pop());
        }
    }
}