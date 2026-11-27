
import java.util.ArrayList;
import java.util.List;

public class FantasyTeam {

    private List<Player> roster;

    public FantasyTeam() {
        roster = new ArrayList<>();
    }

    public boolean addPlayer(Player player) {
        if (player == null) {
            return false;
        }

        for (Player existingPlayer : roster) {
            if (existingPlayer.getPlayerId()
                    .equals(player.getPlayerId())) {
                return false;
            }
        }

        roster.add(player);
        return true;
    }

    public boolean removePlayer(String playerId) {
        for (Player player : roster) {
            if (player.getPlayerId().equals(playerId)) {
                roster.remove(player);
                return true;
            }
        }

        return false;
    }

    public List<Player> getRoster() {
        return new ArrayList<>(roster);
    }

    public int getRosterSize() {
        return roster.size();
    }

    public void displayRoster() {
        if (roster.isEmpty()) {
            System.out.println("Your roster is empty.");
            return;
        }

        System.out.println("\n========== YOUR ROSTER ==========");

        for (Player player : roster) {
            System.out.println(
                    player.getFullName() + " | "
                    + player.getPosition() + " | "
                    + player.getTeam()
            );
        }

        System.out.println("Total players: " + roster.size());
    }
}

