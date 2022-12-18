package webCrawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class webCrawlerBot implements Runnable{
	private static final int MAX_DEPTH = 10;
	private Thread thread;
	private String start_link;
	private ArrayList<String> visited_links = new ArrayList<String>();
	private int bot_id;
	private static int count = 0;
	Database_URL db = new Database_URL();
	
	public webCrawlerBot(String link, int bot_num)
	{
		System.out.println("Initiating WebCrawler Bot: "+bot_num);
		start_link = link;
		bot_id = bot_num;
		db.CreateDB();
		thread = new Thread(this);
		thread.start();
	}
	public void run() {
		crawl(1,start_link);
	}
	
	private void insert_db(String plink, String link){
		db.connectCreate();
		db.insertDB(link, plink);
		count++;
		db.connectClose();
	}

	private void crawl(int level, String url)
	{
		if(level<=MAX_DEPTH)
		{
			if(count > 2) return;
			
			Document doc = request(url);
			if(doc != null)
			{
				for(Element link: doc.select("a[href]"))
				{
					String next_link = link.absUrl("href");
					if(visited_links.contains(next_link) == false)
					{
						crawl(level++,next_link);
						insert_db(url, next_link);
					}
				}
			}
		}
	}
	
	private Document request(String url)
	{
		try {
				Connection conn = Jsoup.connect(url);
				Document doc = conn.get();
				
				if(conn.response().statusCode() == 200)
				{
					System.out.println("Bot ID: " +bot_id+" Received Webpage at: "+url);
					
					String title = doc.title();
					System.out.println(title);
					visited_links.add(url);
					
					return doc;
				}
				return null;
				
			}
		
		catch(IOException e)
			{
				return null;
			}
	}
	
	public Thread getThread() {
		return thread;
	}
}
