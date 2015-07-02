package model;
/**
 * Class the models a position of a team in a ranking. A ranking is a list of positions.
 * @author matteo
 *
 */
public class Position {
	private Team team;
	private int points;
	
	public Position(Team t, int points){
		this.team = t;
		this.points = points;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public String toString(){
		String out = this.team.getName_team() + "\t" + this.points;
		return out;
	}
	
	

}
