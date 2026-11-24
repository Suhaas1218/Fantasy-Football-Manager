public class Main {
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("     FANTASY FOOTBALL MANAGER");
        System.out.println("=================================");

        NFLApiClient api = new NFLApiClient();

        try {
            String nflState = api.getNFLState();

            System.out.println("\nNFL API Connection Successful!");
            System.out.println("Current NFL State:");
            System.out.println(nflState);

        } catch (Exception e) {
            System.out.println("Could not connect to NFL API.");
            System.out.println(e.getMessage());
        }
    }
}
