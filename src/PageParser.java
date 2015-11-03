import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*fetch web page
 * extract different part of page
 * input: url(String)
 * output: Text[] array(contains different part of text contents and location)
 * */
public class PageParser {
	private Text title;
	private Text meta_keywords;
	private Text body;

	public PageParser(String url) {
		//initialize three part of texts
		title = new Text("title");
		meta_keywords = new Text("metadata");
		body = new Text("body");
		//catch connection exception
		try {
			parsePage(url);
		} catch (HttpStatusException e) {
			e.printStackTrace();
		}
	}

	//helper class for constructor
	//fetch page and store text into three Text variables
	private void parsePage(String url) throws HttpStatusException {

		Document doc = null;
		// make connection to the url
		Connection con = Jsoup.connect(url);

		try {
			doc = con.get();//fetch page
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		//store title text
		title.setText(doc.select("title").text());
        
		//store keywords in metadata part
		for (Element meta : doc.select("meta")) {
			if (meta.attr("name").equals("keywords")) {
				meta_keywords.setText(meta.attr("content"));
			}
		}
        
		//store body text
		body.setText(doc.select("body").text());

	}

	//getter
	public Text[] getText() {
		Text[] texts = new Text[3];
		texts[0] = title;
		texts[1] = meta_keywords;
		texts[2] = body;
		return texts;
	}

}
