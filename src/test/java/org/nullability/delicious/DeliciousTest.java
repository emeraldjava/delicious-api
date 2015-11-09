package org.nullability.delicious;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * Delicious API (silly) tests and usage example.
 * 
 * @author Daniel Quirino Oliveira - danielqo@gmail.com
 *
 */
public class DeliciousTest{

	private Delicious delicious;
	private long begin;

	private static String ENV_USERNAME = "username";
	private static String ENV_PASSWORD = "password";

	public DeliciousTest() {
		begin = System.currentTimeMillis();
	}

	@Before
	public void setup() throws Exception {
		String username = System.getProperty(ENV_USERNAME);
		String password = System.getProperty(ENV_PASSWORD);
		System.out.println(username);
		delicious = new Delicious(username,password);
	}

	@Test
	public void getLastestPosts() throws Exception {
		List<Post> l = delicious.getLastestPosts();
		for (Post post : l) {
			System.out.println(post.markup());
		}
		assertFalse(l.isEmpty());
	}

//	@Test
//	public void testPostsByFilter() throws Exception {
//		// FIXME this resource seems to be broken
//		List<Post> l = delicious.getPostsByFilter("java", null, null);
//		for (Post post : l) {
//			System.out.println(post);
//		}
//		assertFalse(l.isEmpty());
//	}
//
//	@Test
//	public void testGetRecent() throws Exception {
//		List<Post> l = delicious.getLastestPosts("java", 20);
//		assertFalse(l.isEmpty());
//	}
//
//	@Test
//	public void testGetAll() throws Exception {
//		List<Post> l = delicious.getAllPosts("Club");
//		assertFalse(l.isEmpty());
//	}
//
//	@Test
//	public void testAddPost() throws Exception {
//		boolean ok = delicious.addPost("http://deliciousapiteste.com","Delicious API Test \351\341\355 {} ()", "", "DAPITeste",new Date(), false, true);
//		assertTrue(ok);
//	}
//
//	@Test
//	public void testDeletePost() throws Exception {
//		boolean ok = delicious.deletePost("http://deliciousapiteste.com");
//		assertTrue(ok);
//	}
//
//	@Test
//	public void testGetLastUpdate() throws Exception {
//		Date lastUpdate = delicious.getLastUpdate();
//		assertNotNull(lastUpdate);
//	}
//
//	@Test
//	public void testGetAllTags() throws Exception {
//		List<Tag> tags = delicious.getAllTags();
//		for (Tag tag : tags) {
//			System.out.println(tag);
//		}
//		assertNotNull(tags);
//	}
//
//	@Test
//	public void testRenameTag() throws Exception {
//		boolean ok = delicious.renameTag("DAPITeste", "DAPITeste2");
//		assertTrue(ok);
//	}
//
//	@Test
//	public void testGetBundles() throws Exception {
//		Map<String, List<Tag>> bundles = delicious.getBundles();
//		for (String key : bundles.keySet()) {
//			List<Tag> tags = bundles.get(key);
//			System.out.println("Bundle: "+key);
//			for (Tag tag : tags) {
//				System.out.println("\t\t "+tag.getName());
//			}
//		}
//		assertNotNull(bundles);
//	}
//
//	@Test
//	public void testCreateBundle() throws Exception {
//		boolean ok = delicious.createBundle("Teste", "Java WebServices");
//		assertTrue(ok);
//	}
//
//	@Test
//	public void testDeleteBundle() throws Exception {
//		boolean ok = delicious.deleteBundle("Teste");
//		assertTrue(ok);
//		long elapsed = System.currentTimeMillis() - begin;
//		System.out.println((new StringBuilder("Elapsed: (ms) "))
//				.append(elapsed).toString());
//	}
}
