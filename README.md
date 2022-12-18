# MultiThreadedWebCrawler
This repo contains the *JAVA* code for a Multi-Threaded Web Crawler that stores the scraped URL's in an SQLite database. It runs multiple spiders to crawl multiple URL's concurrently and is my submission for the Final Project in CS-GY-9053- Introduction to JAVA.


# How to Execute the code
1. Download the code as a .zip or .tar file and unzip in the IDE workspace (I used Eclipse).
2. Add the Jsoup and SQLite .jar files to your project classpath so you can import the corresponding libraries.
3. Navigate to webCrawler/src/webCrawler/MainTest.java file and run the file to start the web crawling process.
4. If you don't have SQLite installed, please follow the [installation guide](https://www.sqlite.org/download.html) based on your OS and click on the crawler.db file to access the database containing the scraped URL's in the crawler table which contains 3 columns: id, URL, PURL which describe the kind of data being scraped. 
