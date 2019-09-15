package hr.ja.test.newelement;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TestParse {

	
	public static void main(String[] args) throws IOException {
		InputStream in = TestParse.class.getResourceAsStream(TestParse.class.getSimpleName()+".html");
		String res = IOUtils.toString(in, StandardCharsets.UTF_8);
		System.out.println(res);
		
	}
}
