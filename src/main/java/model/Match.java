package model;

/**
 * class that models a single match of the schedule
 * @author matteo
 *
 */
public class Match {
	private Team home_team;
	private Team away_team;
	private int home_goals;
	private int away_goals;
	private Result result;
	
	/**
	 * Constructor for a Match, a single row of the schedule
	 * @param home_team
	 * @param away_team
	 * @param home_goals
	 * @param away_goals
	 */
	public Match(Team home_team, Team away_team, int home_goals, int away_goals){
		this.home_team = home_team;
		this.away_team = away_team;
		this.home_goals = home_goals;
		this.away_goals = away_goals;
		this.setResult(home_goals, away_goals);
	}
	
	/**
	 * set the enum for the final result
	 * @param home_goals
	 * @param away_goals
	 */
	private void setResult(int home_goals, int away_goals){
		int diff_goals = home_goals - away_goals;
		if (diff_goals == 0){
			this.result = Result.DRAW;
		}else if(diff_goals < 0){
			this.result = Result.AWAY_WIN;
		}else{
			this.result = Result.HOME_WIN;
		}
	}
	
	/* ------------------------- getters and setters ------------------------- */
	
	public Team getHome_team() {
		return home_team;
	}

	public void setHome_team(Team home_team) {
		this.home_team = home_team;
	}

	public Team getAway_team() {
		return away_team;
	}

	public void setAway_team(Team away_team) {
		this.away_team = away_team;
	}

	public int getHome_goals() {
		return home_goals;
	}

	public void setHome_goals(int home_goals) {
		this.home_goals = home_goals;
	}

	public int getAway_goals() {
		return away_goals;
	}

	public void setAway_goals(int away_goals) {
		this.away_goals = away_goals;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
	public String toString(){
		String out = this.home_team + " VS " + this.away_team + "\t" + this.home_goals+"-" + this.away_goals + "\t" + this.result;  
		return out;
	}


}
