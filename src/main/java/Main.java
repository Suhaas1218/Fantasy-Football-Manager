import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("     FANTASY FOOTBALL MANAGER");
        System.out.println("=================================");

        NFLApiClient api = new NFLApiClient();
        FantasyTeam fantasyTeam = new FantasyTeam();

        try {
            List<Player> players = api.getActivePlayers();

            System.out.println("Successfully retrieved NFL players!");
            System.out.println("Total active players: " + players.size());

            Scanner scanner = new Scanner(System.in);

            boolean running = true;

            while (running) {

                System.out.println("\n========== MAIN MENU ==========");
                System.out.println("1. Search NFL players");
                System.out.println("2. Add player to roster");
                System.out.println("3. View roster");
                System.out.println("4. Remove player from roster");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");

                String choice = scanner.nextLine();

                switch (choice) {

                    case "1":
                        System.out.print("Enter player name: ");
                        String search = scanner.nextLine().toLowerCase();

                        int count = 0;

                        for (Player player : players) {
                            if (player.getFullName().toLowerCase()
                                    .contains(search)) {

                                System.out.println(
                                        player.getFullName() + " | "
                                        + player.getPosition() + " | "
                                        + player.getTeam() + " | ID: "
                                        + player.getPlayerId()
                                );

                                count++;

                                if (count == 20) {
                                    break;
                                }
                            }
                        }

                        if (count == 0) {
                            System.out.println("No players found.");
                        }
                        break;

                    case "2":
                        System.out.print("Enter player ID: ");
                        String playerId = scanner.nextLine();

                        Player selectedPlayer = null;

                        for (Player player : players) {
                            if (player.getPlayerId().equals(playerId)) {
                                selectedPlayer = player;
                                break;
                            }
                        }

                        if (selectedPlayer == null) {
                            System.out.println("Player not found.");
                        } else if (fantasyTeam.addPlayer(selectedPlayer)) {
                            System.out.println(
                                    selectedPlayer.getFullName()
                                    + " added to your roster!"
                            );
                        } else {
                            System.out.println(
                                    "Player is already on your roster."
                            );
                        }
                        break;

                    case "3":
                        fantasyTeam.displayRoster();
                        break;

                    case "4":
                        System.out.print("Enter player ID to remove: ");
                        String removeId = scanner.nextLine();

                        if (fantasyTeam.removePlayer(removeId)) {
                            System.out.println("Player removed successfully.");
                        } else {
                            System.out.println("Player not found on roster.");
                        }
                        break;

                    case "5":
                        running = false;
                        System.out.println(
                                "Thanks for using Fantasy Football Manager!"
                        );
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }

        } catch (Exception e) {
            System.out.println("An error occurred.");
            System.out.println(e.getMessage());
        }
    }
}