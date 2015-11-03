/*helper class for PageParser
 *store the text and location of the text(title,metadata,body)
 *input:text contents(String)
 *output:text contents(String), location(String)
 */
public class Text {
	
	private String text;
	private String location;
	
	//constructor
	public Text(String location){
		this.location=location;
	}
	
	//setter for text contents
	public void setText(String text){
		this.text=text;
	}
	
	//getter for text
	public String getText(){
		return this.text;
	}
	
	//getter for text location in web page
	public String getLocation(){
		return this.location;
	}
}
