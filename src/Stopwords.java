import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

//generate stopwords list
//input: file path of stopwords(optional)
//output: a HashSet containing a list of stopwords
public class Stopwords {

	private HashSet<String> stopwords;

	// constructor
	public Stopwords(String file_path) {
		stopwords = new HashSet<String>();
		try {
			createStopwordsList(file_path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// chaining constructor, generating a default stopwords list
	public Stopwords() {
		this("stopwords.txt");// stopwords.txt required from
								// https://sites.google.com/site/kevinbouge/stopwords-lists
	}

	//helper class, read in stopwords file and create stopwords list
	private void createStopwordsList(String file_path) throws IOException {

		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream(file_path);
		Scanner filescanner = new Scanner(new InputStreamReader(in));
		//add each stopwords into HashSet
		while (filescanner.hasNextLine()) {
			stopwords.add(filescanner.nextLine());
		}

	}

	//getter
	public HashSet<String> getStopwords() {
		return this.stopwords;
	}

	//determine if a word is stopword
	public boolean isStopword(String word) {
		return stopwords.contains(word);
	}

}
