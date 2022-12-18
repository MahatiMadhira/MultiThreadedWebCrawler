# MultiThreadedWebCrawler
This repo contains the *JAVA* code for a Multi-Threaded Web Crawler that stores the scraped URL's in an SQLite database. It runs multiple spiders to crawl multiple URL's concurrently and is my submission for the Final Project in CS-GY-9053- Introduction to JAVA.


# How to Execute the code
1. Download the code as a .zip or .tar file and unzip in the IDE workspace (I used Eclipse).
2. Add the Jsoup and SQLite .jar files to your project classpath so you can import the corresponding libraries.
3. Navigate to webCrawler/src/webCrawler/MainTest.java file and run the file to start the web crawling process.
4. If you don't have SQLite installed, please follow the [installation guide](https://www.sqlite.org/download.html) based on your OS and click on the crawler.db file to access the database containing the scraped URL's in the crawler table which contains 3 columns: id, URL, PURL which describe the kind of data being scraped. 


# Future Work
1. I plan to display the scraped URL's in a GUI. I have already written the code but am yet to integrate the GUI with the database.
2. I am yet to check if my web crawler goes through loops when visiting links.

# Results 
<img width="1470" alt="Screen Shot 2022-12-18 at 3 12 35 AM" src="https://user-images.githubusercontent.com/93003959/208289151-3bb58a4c-b884-42e4-897c-9be1e5394dcb.png">

<img width="1470" alt="Screen Shot 2022-12-18 at 3 12 41 AM" src="https://user-images.githubusercontent.com/93003959/208289155-f62e39f2-58bb-408b-86bd-73044845cdfb.png">

