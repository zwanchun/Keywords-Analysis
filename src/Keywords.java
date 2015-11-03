import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

/*process each part of text
 *lowercase, remove tags, split the text into words
 *for each word, remove special characters
 *store keywords and frequency into hashmap
 *sort the hashmap
 *print keyword list
 *input: stopwords list, each part of text
 *output: sorted keywords list
 * */
public class Keywords {

	private Map<String, Double> wordlist;
	private Stopwords stopwords;

	public Keywords(String stopwords_path) {
		//initialize
		wordlist = new HashMap<String, Double>();
		stopwords = new Stopwords(stopwords_path);
	}

	public Keywords() {
		wordlist = new HashMap<String, Double>();
		stopwords = new Stopwords();
	}

	//method to store keywords and weight into hashmap
	public void keywordsGenerate(Text[] texts) {
		try {
			for (Text t : texts) {//iterate through each part of text
				if (t.getText() != null) {//if text contents exist
					if (t.getText() != "") {
						keywordsGenerate(t);
					}

				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
    
	
	// helper method for public defined KeywordsGenerate method
	private void keywordsGenerate(Text text) {
		// set different weight to different location of text
		if (text != null) {
			// set weight
			double weight = 0;
			if (text.getLocation().equals("title")) {// for words in title,
														// weigh as 5
				weight = 5;
			} else if (text.getLocation().equals("metadata")) {// for words in
																// metadata
																// weigh as 3
				weight = 3;
			} else {// for words in body, weigh as 1
				weight = 1;
			}
			
			//convert all characters to lower case
			String tmp_text = text.getText().toLowerCase();
			
			//using regex remove tags remaining in text
			tmp_text = tmp_text.replaceAll("\\<[^>]*>", "");
			
			//split text into words
			String[] words = tmp_text.trim().split(" ");
			
			//traverse and process every word
			for (String word : words) {
				//trim special characters
				word = trimSpecialCharacters(word);
				/*if not stopword and the word not empty*/
				if (!stopwords.isStopword(word) && !word.equals("")) {
					if (wordlist.containsKey(word)) {//if already in the list, add weight
						wordlist.put(word, wordlist.get(word) + weight);
					} else {//not in the list, store weight
						wordlist.put(word, weight);
					}
				}
			}
		}

	}
    
	//helper method for keywordGenerate method, trim special characters in text
	private String trimSpecialCharacters(String word) {
		//using regex to removee special characters
		word = word.replaceAll("[.,!?$%#+()-:/|=^&\"]", "");
		
		if (word.startsWith("'") || word.endsWith("'")) {
			word = word.replace("'", "");
		}
		return word;
	}

	//sort keywords by their weight
	private void sortKeywordsFrequence() {
		//store hashmap into a list
		List list = new LinkedList(wordlist.entrySet());
		//use Collections sort method to sort the list
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue())
						.compareTo(((Map.Entry) (o1)).getValue());
			}
		});
		//put sorted list into LinkedHashMap
		wordlist = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			wordlist.put((String) entry.getKey(), (Double) entry.getValue());
		}

	}

	public void printKeywords() {
		//first sort the keywords list
		sortKeywordsFrequence();
		//iterate through the map and print key&value
		Iterator iterator = wordlist.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			System.out.println(key + ": " + val);
		}

	}

}
