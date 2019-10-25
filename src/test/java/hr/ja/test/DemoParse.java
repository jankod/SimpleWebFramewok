package hr.ja.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DemoParse {

	
	public static void main(String[] args) throws IOException {
		StopWatch s = StopWatch.createStarted();
		Document result = Jsoup.parse(new File("C:\\Libs\\CSS\\Smart Admin WB0573SK0 4.0.2\\smartadmin-html-full\\dist\\datatables_responsive_alt.html"), "UTF-8");
		
		s.stop();
		
		System.out.println("finish "+ DurationFormatUtils.formatDurationHMS(s.getTime()));
		System.out.println(result.getAllElements().size());
	}
}
