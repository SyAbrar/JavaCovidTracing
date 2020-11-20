public class Main {
    public static void main (String[] args) {
        Government gov = new Government("gov.config");
        MobileDevice device = new MobileDevice("mobile.config", gov);

    }
}
