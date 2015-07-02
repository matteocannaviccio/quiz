package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import engine.MainQuiz;

/**
 * class that models a single team providing some information
 * @author matteo
 *
 */
public class Team {
	private String name_team;
	private List<Match> proper_matches;
	private List<Team> matches_won;
	private List<Team> matches_drawn;
	private int stand_point;

	/**
	 * Constructor for a Team.
	 * @param name_team
	 */
	public Team(String name_team){
		this.name_team = name_team;
		proper_matches = new ArrayList<Match>(38);
		matches_won = new LinkedList<Team>();
		matches_drawn = new LinkedList<Team>();
		setMatches();
	}

	/**
	 * calculates the points
	 * @param proper_matches
	 * @return
	 */
	public int getPoints(){
		int standing_points = MainQuiz.WIN_POINTS * this.matches_won.size() + MainQuiz.DRAW_POINTS * this.matches_drawn.size();
		return standing_points;
	}

	/**
	 * 
	 */
	public void setMatches(){
		for (Match m : this.proper_matches){
			switch(m.getResult()){
			case HOME_WIN:
				if (m.getHome_team().equals(this))
					matches_won.add(m.getAway_team());
				break;
			case AWAY_WIN:
				if (m.getAway_team().equals(this))
					matches_won.add(m.getHome_team());
				break;
			case DRAW:
				if (m.getAway_team().equals(this))
					matches_drawn.add(m.getHome_team());
				else
					matches_drawn.add(m.getAway_team());
				break;
			}
		}
	}


	/* ------------------------- getters and setters ------------------------- */

	public void setName_team(String name_team) {
		this.name_team = name_team;
	}


	public String getName_team() {
		return name_team;
	}

	public String toString(){
		return this.name_team;
	}

	public List<Match> getProper_matches() {
		return proper_matches;
	}

	public void setProper_matches(List<Match> proper_matches) {
		this.proper_matches = proper_matches;
	}

	public int getStand_point() {
		return stand_point;
	}

	public void setStand_point(int stand_point) {
		this.stand_point = stand_point;
	}

	/**
	 * Redefined equals method
	 */
	public boolean equals(Object o){
		Team to = (Team) o;
		return (this.getName_team().equals(to.getName_team()));

	}

	public List<Team> getMatches_won() {
		return matches_won;
	}

	public void setMatches_won(List<Team> matches_won) {
		this.matches_won = matches_won;
	}

	public List<Team> getMatches_drawn() {
		return matches_drawn;
	}

	public void setMatches_drawn(List<Team> matches_drawn) {
		this.matches_drawn = matches_drawn;
	}



}
