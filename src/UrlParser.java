import java.net.MalformedURLException;
import java.net.URL;

//read in url and check if the url is correctly formed
//intput:user input string
//output:checked url(String)
public class UrlParser {

	private URL url;

	//constructor
	public UrlParser(String url) {
		try {//check if url is correctly formed
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	//chaining constructor, example
	public UrlParser() {
		this("http://www.cnn.com/2013/06/10/politics/edward-snowden-profile/");
	}
	
	//getter
	public String getURL(){
		return url.toString();
	}
	

}
