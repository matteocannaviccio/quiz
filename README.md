Tool performed to calculate alternatives end of Premier League championships

Given a CSV file containing some data about the games in the Premier League 2013/14 season the tool determines, for each team, the minimum number of games that need to be excluded in order for that team to win the league. 

For example, suppose a ranking as:
Man City	86
Liverpool	84
Chelsea		82
Arsenal		79

Manchester City win the league as it stands, so for them the answer is 0. If we remove any one game that Manchester City won, Manchester City drop to 83 points. Liverpool have 84 points, so they then win the league. So for Liverpool, the answer is 1. The program finds that number for all teams who took part in that season.

The input of the program is a csv file with the schedule of that season (e.g. csv/PremierLeague1314.csv).

