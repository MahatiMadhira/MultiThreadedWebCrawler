package webCrawler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Download_URL {

	public Download_URL(String link) {
		
		try {
			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			//byte[] encodedBytes = Base64.encodeBase64("root:pass".getBytes());
			//String encoding = new String(encodedBytes);
			connection.setRequestMethod("GET");
	        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
	        connection.setRequestProperty("Accept-Charset", "UTF-8");
	        connection.setDoInput(true);
	        //connection.setRequestProperty("Authorization", "Basic " + encoding);
	        connection.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
}
