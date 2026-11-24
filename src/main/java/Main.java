import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("     FANTASY FOOTBALL MANAGER");
        System.out.println("=================================");

        NFLApiClient api = new NFLApiClient();

        try {
            List<Player> players = api.getActivePlayers();

            System.out.println("\nSuccessfully retrieved NFL players!");
            System.out.println("Total active players: " + players.size());

            System.out.println("\nFirst 10 players:");

            for (int i = 0; i < Math.min(10, players.size()); i++) {
                Player player = players.get(i);

                System.out.println(
                        player.getFullName() + " | "
                        + player.getPosition() + " | "
                        + player.getTeam()
                );
            }

        } catch (Exception e) {
            System.out.println("Could not retrieve NFL players.");
            System.out.println(e.getMessage());
        }
    }
}
