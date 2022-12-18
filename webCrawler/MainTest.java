package webCrawler;

import java.util.ArrayList;

public class MainTest {

	public static void main(String[] args) {
		Database_URL d = new Database_URL();
		ArrayList<webCrawlerBot> bots = new ArrayList<webCrawlerBot>();
		bots.add(new webCrawlerBot("https://abcnews.go.com", 1));
		bots.add(new webCrawlerBot("https://npr.org", 2));
		bots.add(new webCrawlerBot("https://nytimes.com", 3));
		bots.add(new webCrawlerBot("https://foxnews.com", 4));

		for(webCrawlerBot w : bots)
		{
			try {
				w.getThread().join();
			}
			catch(InterruptedException e){
				e.printStackTrace();
				
			}
		}
		
		new Crawler_GUI(d);
	}

}
