package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
/**
 * Class that models a Ranking as a list of Position.
 * @author matteo
 *
 */
public class Ranking {
	
	private List<Position> ranking_table;
	
	/**
	 * 
	 * @param teams
	 */
	public Ranking(Collection<Team> teams){
		this.ranking_table = calculateStanding(teams);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Team> getTeams(){
		List<Team> teams = new ArrayList<Team>(this.ranking_table.size());
		for (Position p : this.ranking_table){
			teams.add(p.getTeam());
		}
		return teams;
	}

	/**
	 * 
	 * @param teams
	 * @return
	 */
	public List<Position> calculateStanding(Collection<Team> teams){
		List<Position> standing = new ArrayList<Position>(teams.size());
		for (Team t : teams){
			Position p = new Position(t, t.getPoints());
			standing.add(p);
		}
		Collections.sort(standing, new Comparator<Position>(){
			public int compare(Position pos1, Position pos2){
				int diff = pos2.getPoints() - pos1.getPoints();
				return diff;
			}
		});
		return standing;
	}
	
	/**
	 * 
	 * @param t
	 * @return
	 */
	public List<Position> getTeamsInvolved(Team t){
		List<Position> subsetStdg = new LinkedList<Position>();
		final int benchmark_points = t.getPoints();
		for (Position p : ranking_table){
			if (!p.getTeam().equals(t)){
				Position new_p = new Position(p.getTeam(), p.getPoints()-benchmark_points);
				subsetStdg.add(new_p);
			}else{
				break;
			}
		}
		return subsetStdg;
	}

	/**
	 * 
	 * @return
	 */
	public List<Position> getStandingTable() {
		return ranking_table;
	}

	/**
	 * 
	 * @param standing
	 */
	public void setStandingTable(List<Position> standing) {
		this.ranking_table = standing;
	}

}
