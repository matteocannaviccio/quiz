package tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import engine.MainQuiz;
import model.Position;
import model.Team;

/**
 * Implementation of a Node in the tree. It is able to calculate its childs considering the pool of draws.
 * @author matteo
 *
 */
public class Node {

	private List<Position> positions;
	private Map<String, NodeRow> rows;
	private List<Pair<Team, Team>> draws;
	private int level;

	/**
	 * Constructor for a Node. Each node knows about a specific pool of draws to use in expanding the tree.
	 * @param level
	 * @param positions
	 * @param draws
	 */
	public Node(int level, List<Position> positions, List<Pair<Team, Team>> draws){
		this.level = level;
		this.positions = positions;
		this.draws = draws;
		this.rows = new HashMap<String, NodeRow>();
		fillRows(positions);
	}

	/**
	 * Fill the rows of the node creating NodeRows.
	 * @param positions
	 */
	private void fillRows(List<Position> positions){
		for (Position p : positions){
			NodeRow nr = new NodeRow(p.getPoints());
			rows.put(p.getTeam().getName_team(), nr);
		}
	}

	/**
	 * calculate the cost for a Node
	 * @param positions
	 * @return
	 */
	public int getCost(){
		int cost = 0;
		for (NodeRow nr : rows.values()){
			cost += nr.getRowCost();
		}
		return this.getLevel() + cost;
	}

	/**
	 * print a Node
	 */
	public String toString(){
		StringBuffer out = new StringBuffer();
		for (Map.Entry<String, NodeRow> nr : rows.entrySet()){
			out.append(nr.getKey() +"  " + nr.getValue().toString() + "\n");
		}
		out.append("Cost:	 "  + this.getCost());
		return out.toString();
	}

	/**
	 * Core method to expand a node of the tree.
	 * Each node can generate a child if there is a draw that is usable.
	 * A draw is usable if it is within two team that have N points, with N mod 3=1  
	 * @param node
	 * @return
	 */
	public List<Node> getChilds(){
		List<Node> childs = new LinkedList<Node>();
		List<Pair<Team, Team>> draws_app = new ArrayList<Pair<Team, Team>>(this.getDraws());
		for (Pair<Team, Team> teamsDraw : draws_app){
			int nmodA = this.getRows().get(teamsDraw.key.getName_team()).getNmod3();
			int nmodB = this.getRows().get(teamsDraw.value.getName_team()).getNmod3();
			if (nmodA == 1 || nmodB == 1){
				this.getDraws().remove(teamsDraw);
				Node child = new Node(this.getLevel() + 1 , modifiesPositions(teamsDraw.key, teamsDraw.value, positions), this.getDraws());
				childs.add(child);
			}
		}
		return childs;
	}

	/**
	 * Update the position of a node (creates the positions for the child nodes)
	 * @param a
	 * @param b
	 * @param old_positions
	 * @return
	 */
	private List<Position> modifiesPositions(Team a, Team b, List<Position> old_positions){
		List<Position> novel_positions = new ArrayList<Position>(old_positions.size());
		for (Position p : old_positions){
			if (p.getTeam().equals(a) || p.getTeam().equals(b))
				novel_positions.add(new Position(p.getTeam(), p.getPoints() - MainQuiz.DRAW_POINTS));
			else
				novel_positions.add(p);
		}
		return novel_positions;
	}
	
	

	public Map<String, NodeRow> getRows() {
		return rows;
	}

	public void setRows(Map<String, NodeRow> rows) {
		this.rows = rows;
	}


	public List<Pair<Team, Team>> getDraws() {
		return draws;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public int getLevel() {
		return level;
	}


}
