package engine;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Match;
import model.Team;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * Class that manages the input/output of data from/to the csv file
 * @author matteo
 *
 */
public class IOData {

	static List<Match> schedule = new LinkedList<Match>();
	static Map<String, Team> teams = new HashMap<String, Team>();

	/**
	 * Read the csv file and upload data. This method can be improved.
	 * @param csvFile
	 * @throws IOException
	 */
	public static void uploadData(File csvFile) throws IOException{
		CSVReader csvReader = new CSVReader(new FileReader(csvFile));
		String[] row = null;
		csvReader.readNext(); //skip header
		while((row = csvReader.readNext()) != null) {
			String home_team_name = row[2];
			String away_team_name = row[3];
			int home_goals = Integer.parseInt(row[4]);
			int away_goals = Integer.parseInt(row[5]);
			if (!teams.containsKey(home_team_name)){
				Team homeTeam = new Team(home_team_name);
				teams.put(home_team_name, homeTeam);
			}
			if (!teams.containsKey(away_team_name)){
				Team awayTeam = new Team(away_team_name); 
				teams.put(away_team_name, awayTeam);
			}
			Match current_match = new Match(teams.get(home_team_name), teams.get(away_team_name), home_goals, away_goals);
			teams.get(home_team_name).getProper_matches().add(current_match);	
			teams.get(away_team_name).getProper_matches().add(current_match);
			schedule.add(current_match);
		}
		for (Team t : teams.values()){
			t.setMatches();
		}
		csvReader.close();
	}


	/**
	 * Writes the output csv file
	 * @param csv_out
	 * @param output
	 * @throws IOException
	 */
	public static void writeOutput(File csv_out, String[][] output) throws IOException{
		CSVWriter writer = new CSVWriter(new FileWriter(csv_out), ',');
		for (int i = 0; i<output.length; i++){
			writer.writeNext(output[i]);
		}
		writer.close(); 
	}

}
