public abstract class Comperators{
    public static List.Comperator<Integer> INTEGER_ASCENDING = new List.Comperator<Integer>() {
        @Override
        public boolean compare(Integer a, Integer b) {
            return a > b;
        }
    };
    public static List.Comperator<Integer> INTEGER_DESCENDING = new List.Comperator<Integer>() {
        @Override
        public boolean compare(Integer a, Integer b) {
            return a < b;
        }
    };
    public static List.Comperator<String> STRING_ASCENDING = new List.Comperator<String>() {
        @Override
        public boolean compare(String a, String b) {
            return a.compareTo(b) > 0;
        }
    };
    public static List.Comperator<String> STRING_DESCENDING = new List.Comperator<String>() {
        @Override
        public boolean compare(String a, String b) {
            return a.compareTo(b) < 0;
        }
    };
}