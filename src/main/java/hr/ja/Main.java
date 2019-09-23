package hr.ja;

import hr.ja.javalin.WebFrameworkJavalin;

public class Main {

	public static void main(String[] args) {
		WebFrameworkJavalin w = new WebFrameworkJavalin();
		w.addPage(DemoPage.class);
		w.start(8080);
		
	}
	
}

