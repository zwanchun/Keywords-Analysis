
public class Main {

	public static void main(String[] args) {
		
		UrlParser url=null;
		//process input
		if(args.length==0){
			url=new UrlParser();//if user does not input any url
		}
		else{
			url=new UrlParser(args[0]);
		}
		
		//create a new PageParser instance
		PageParser pp=new PageParser(url.getURL());
		
		//Generate Keywords
		Keywords keywords=new Keywords();
		keywords.keywordsGenerate(pp.getText());
		
		//print out keywords
		System.out.println("The keywords and their weight in url:");
		System.out.println(url.getURL());
		keywords.printKeywords();

	}

}
