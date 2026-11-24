public class Player {
    private String playerId;
    private String firstName;
    private String lastName;
    private String position;
    private String team;

    public Player(String playerId, String firstName, String lastName,
                  String position, String team) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.team = team;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getTeam() {
        return team;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
