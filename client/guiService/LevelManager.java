package guiService;

public class LevelManager {


	public static final String wall = "w";
	public static final String floor = "e";
	public static final String[][] level = 
		{
		{ "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w","w", "w", "w", "w", "w", "w", "w", "w" },
		{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "e","e", "e", "e", "e", "e", "e", "w" },
		{ "w", "e", "w", "e", "e", "w", "e", "e", "w", "w", "w", "e", "w","e", "e", "w", "e", "e", "w", "w" },
		{ "w", "e", "w", "e", "e", "w", "e", "e", "e", "w", "w", "e", "w","e", "e", "w", "e", "e", "w", "w" },
		{ "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e","e", "e", "e", "e", "e", "e", "w" },
		{ "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w", "e", "w","e", "e", "w", "e", "e", "w", "w" },
		{ "w", "e", "w", "e", "e", "e", "e", "e", "w", "w", "w", "e", "w","e", "e", "w", "e", "e", "w", "w" },
		{ "w", "e", "w", "e", "e", "e", "e", "e", "w", "e", "w", "e", "w","e", "e", "w", "e", "e", "w", "w" },
		{ "w", "e", "e", "e", "w", "e", "w", "e", "e", "w", "e", "e", "w","e", "e", "w", "e", "e", "e", "w" },
		{ "w", "e", "e", "e", "e", "e", "w", "e", "e", "w", "e", "e", "w","e", "e", "w", "e", "e", "e", "w" },
		{ "w", "e", "w", "w", "e", "w", "w", "e", "e", "e", "e", "e", "e","e", "e", "w", "e", "e", "w", "w" },
		{ "w", "e", "e", "w", "e", "w", "e", "e", "e", "e", "w", "e", "e","e", "e", "w", "e", "e", "w", "w" },
		{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w","e", "e", "w", "e", "e", "w", "w" },
		{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "w","e", "e", "w", "e", "e", "w", "w" },
		{ "w", "e", "e", "e", "e", "e", "e", "e", "e", "w", "e", "e", "e","e", "e", "w", "e", "e", "w", "w" },
		{ "w", "e", "e", "w", "e", "e", "e", "e", "e", "e", "e", "e", "e","e", "e", "e", "e", "e", "w", "w" },
		{ "w", "e", "e", "w", "e", "w", "w", "w", "e", "e", "w", "e", "w","e", "e", "w", "w", "e", "w", "w" },
		{ "w", "e", "w", "e", "e", "e", "e", "e", "e", "w", "w", "e", "w","e", "e", "e", "e", "e", "w", "w" },
		{ "w", "e", "e", "e", "w", "e", "e", "e", "w", "w", "e", "e", "w","e", "e", "e", "e", "e", "e", "w" },
		{ "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w", "w","w", "w", "w", "w", "w", "w", "w", "w" }, 
		};

	public static String getLocation(int x, int y){
		return level[x][y];
	}
}
