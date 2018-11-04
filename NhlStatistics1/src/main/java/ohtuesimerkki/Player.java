package src.main.java.ohtuesimerkki;

import java.util.Objects;

public class Player implements Comparable<Player> {

    private String name;
    private String team;
    private int goals;
    private int assists;

    public Player(String name, String team, int goals, int assists) {
        this.name = name;
        this.team = team;
        this.goals = goals;
        this.assists = assists;
    }
        
    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPoints() {
        return goals + assists;
    }

    @Override
    public String toString() {      
        return String.format("%-20s",name) + " " + team + " " + String.format("%2d",goals) + " + " 
                + String.format("%2d",assists) + " = " + getPoints();
    }

    public int compareTo(Player t) {
        return t.getPoints()-this.getPoints();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return goals == player.goals &&
                assists == player.assists &&
                Objects.equals(name, player.name) &&
                Objects.equals(team, player.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team, goals, assists);
    }
}
