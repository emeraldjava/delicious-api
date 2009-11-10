package org.nullability.delicious;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

public class URLBuilderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBuildURLAllParameters() {
		String expected = "https://api.del.icio.us/v1/posts/add?url=TESTE_URL&description=TESTE_DESC&extended=TESTE&tags=TESTE&dt=TESTE&replace=TESTE&shared=TESTE";
		URLBuilder builder = new URLBuilder();
		URL actual;
		try {
			actual = builder.buildURL(DeliciousResources.ADDPOST, "TESTE_URL", "TESTE_DESC", "TESTE", "TESTE", "TESTE", "TESTE", "TESTE");
			System.out.println(actual);
			System.out.println(expected);
			assertEquals(expected, actual.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	@Test
	public void testBuildURLSomeParams() {
		String expected = "https://api.del.icio.us/v1/posts/add?url=TESTE_URL&description=TESTE_DESC";
		URLBuilder builder = new URLBuilder();
		URL actual;
		try {
			actual = builder.buildURL(DeliciousResources.ADDPOST, "TESTE_URL", "TESTE_DESC");
			System.out.println(actual);
			System.out.println(expected);
			assertEquals(expected, actual.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
