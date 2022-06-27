package model;

public class Driver {
    private final String name;

    private final String team;

    public Driver(String name,String team) {
        this.name = name;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }
}
