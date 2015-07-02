package engine;

import java.io.File;
import java.io.IOException;

import model.Ranking;
import model.Team;

/**
 * Tool used to perform a quiz. Given a schedule of a championship (ex, Premier League) it returns 
 * the number of matches to delete from the schedule, in order to allow for each team to win the league.
 *
 * Input: a csv file with the schedule
 * Output: a csv file with the results, for each team (results_nameinputfile.csv)
 *
 * Note: the tool is independent by the number of teams involved in the league
 */
public class MainQuiz{
	// some parameters to simulate a quick config file
	public static final int WIN_POINTS = 3;
	public static final int DRAW_POINTS = 1;

	/**
	 * Read a csv file, executes the algorithm for each team and write the csv output results
	 * file in the same folder of the input file 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		// read the input file
		File csvInput = new File(args[0]);
		IOData.uploadData(csvInput);

		// this is the original PremierLeague13/14 ranking
		Ranking ranking = new Ranking(IOData.teams.values());
		
		// run the tool
		String[][] output = executeTool(ranking);

		// write the output file
		String nameInputDirectory = csvInput.getAbsolutePath().replace(csvInput.getName(), "");
		File csvOutput = new File(nameInputDirectory + "/results_"+ csvInput.getName());
		System.out.println("Results file:	" + csvOutput.getPath());
		IOData.writeOutput(csvOutput, output);
	}
	
	/**
	 * Method that runs the tool over all the teams in the league
	 * @param ranking
	 * @return
	 */
	private static String[][] executeTool(Ranking ranking){
		String[][] output = new String[ranking.getTeams().size()][2];
		
		// for each team of the league run a depth_search algorithm...
		int position = 0;
		for (Team t : ranking.getTeams()){
			Algorithm alg = new Algorithm(t, ranking);
			output[position][0] = t.getName_team();
			output[position][1] = String.valueOf(alg.getNumberMatchDeletion());
			position++;
		}
		return output;
	}




}



