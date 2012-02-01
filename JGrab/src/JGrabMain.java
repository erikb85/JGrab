import java.io.IOException;
import java.util.List;

import parsers.SimpleMatchParser;
import readers.SimpleHttpReader;
import writers.SimpleTextWriter;
import framework.CSVFileManager;
import framework.Token;
import framework.TokenType;

/**
 * This class works as the Startpoint and also as the manager for the other
 * classes
 * 
 * @author sholvar
 * 
 */
public class JGrabMain {

	/**
	 * where the parser token types are stored
	 */
	public static final String CONFIG_FILE = "res/types.txt";

	/**
	 * get the "home" directory of the user and point the results of the parsing
	 * process there
	 */
	public static final String OUTPUT_FILE = System.getProperty("user.home")
			+ "\\Documents\\results.txt";

	/**
	 * the website to be parsed
	 */
	private static final String INPUT_URL = "http://news.ycombinator.com/";

	
	public static void main(String[] args) throws IOException {
		//understand this deeply before making changes here.
		//probably your change will be better added to one of the other files!
		CSVFileManager csvMgr = new CSVFileManager();
		SimpleHttpReader reader = new SimpleHttpReader();
		SimpleMatchParser parser = new SimpleMatchParser(TokenType.parse(csvMgr
				.readFile(CONFIG_FILE).getCSVObject()));
		SimpleTextWriter writer = new SimpleTextWriter();
		List<Token> result = parser.parse(reader.readURL(INPUT_URL));
		writer.writeFile(""+result, OUTPUT_FILE);
		System.out.println("done(size:"+result.size()+"):"+result+";");
	}

}
