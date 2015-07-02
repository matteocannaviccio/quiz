package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Position;
import model.Ranking;
import model.Team;
import tool.Node;
import tool.Pair;

/**
 * Implementation of the depth_search in a tree, in order to find the node with the minimum cost. 
 * The tree is composed by nodes. A node describe a possible ways to 
 *  eliminate matches between the teams involved (i.e. considering or not the draws).
 * @author matteo
 *
 */
public class Algorithm {

	private List<Position> initialPositions;
	private Node root;

	public Algorithm(Team team2Evaluate, Ranking standing){
		this.initialPositions = standing.getTeamsInvolved(team2Evaluate);
		this.root = new Node(0, initialPositions, calcDrawns(retrieveTeamsInvolved(initialPositions)));
	}
	
	/**
	 * This is the main method. It calls a recursive search to find the path with the minimum cost
	 * @param position
	 * @return
	 */
	public int getNumberMatchDeletion(){
		int match2delete = -1;
		List<Node> nodes = recursiveSearch(this.getRoot());
		nodes.add(this.getRoot()); //add the root in the alternatives
		match2delete = findMinimumCost(nodes);
		return match2delete;
	}
	
	/**
	 * It chooses the node with minimum cost within the ones in the list
	 * @param nodes
	 * @return
	 */
	private int findMinimumCost(List<Node> nodes){
		int min = Integer.MAX_VALUE;
		for (Node n : nodes){
			if (n.getCost() < min)
				min = n.getCost();
		}
		return min;
	}


	/**
	 * Retrieves the list of draws between the teams involved
	 * @return
	 */
	private List<Pair<Team, Team>> calcDrawns(List<Team> teamInvolved){
		List<Pair<Team, Team>> drawns = new LinkedList<Pair<Team, Team>>();
		Map<Team, List<Team>> drawns2exclude = new HashMap<Team, List<Team>>();
		for (Team t : teamInvolved){
			for (Team d : t.getMatches_drawn()){
				if (drawns2exclude.containsKey(t)){
					if (drawns2exclude.get(t).contains(d)){
						drawns2exclude.get(t).remove(d);
						continue;
					}
				}
				if (teamInvolved.contains(d)){
					Pair<Team, Team> draw = new Pair<Team, Team>(t,d);
					drawns.add(draw);
					if (drawns2exclude.containsKey(d)){
						drawns2exclude.get(d).add(t);
					}else{
						List<Team> list2exclude = new LinkedList<Team>();
						list2exclude.add(t);
						drawns2exclude.put(d, list2exclude);
					}
				}
			}
		}
		return drawns;
	}

	/**
	 * This is the core recursive method to visit the tree. It calls the method of each node to expand its childs.
	 * @param node
	 * @return
	 */
	private List<Node> recursiveSearch(Node node){
		List<Node> childs = node.getChilds();  // each node know its childs
		List<Node> childs_app = new LinkedList<Node>(childs);
		for (Node c : childs_app){
			childs.addAll(recursiveSearch(c));
		}
		return childs;

	}

	/**
	 * Retrieve the root node of the tree
	 * @return
	 */
	private Node getRoot() {
		return root;
	}
	
	/**
	 * Retrieve the teams involved in the current execution of the algorithm
	 * @param positions
	 * @return
	 */
	private List<Team> retrieveTeamsInvolved(List<Position> positions){
		List<Team> teamsInvolved = new ArrayList<Team>(positions.size());
		for (Position p : positions){
			teamsInvolved.add(p.getTeam());
		}
		return teamsInvolved;
	}





}
