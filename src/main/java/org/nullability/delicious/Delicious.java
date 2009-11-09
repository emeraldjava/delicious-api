package org.nullability.delicious;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author Daniel Quirino Oliveira - danielqo@gmail.com
 * 
 */
public class Delicious {

	public Delicious(String username, String password)
			throws IllegalArgumentException {
		sdf = new DeliciousDateFormatter();
		authenticator = new HTTPAuthenticator(username, password);
		Authenticator.setDefault(authenticator);
	}

	public List<Post> getPostsByFilter(String tag, Date date, String url)
			throws Exception {
		URL turl = (new URLBuilder()).buildURL(DeliciousResources.GETPOSTSBYFILTER,
				tag != null ? tag : "", date != null ? sdf.format(date) : "",
				url != null ? url : "");
		return getPosts(turl);
	}

	public List<Post> getLastestPosts(String tag, Integer count)
			throws Exception {
		URL url = (new URLBuilder()).buildURL(DeliciousResources.GETRECENTPOSTS,
				tag != null ? tag : "", count != null ? count.toString() : "");
		return getPosts(url);
	}

	public List<Post> getAllPosts(String tag) throws Exception {
		URL url = (new URLBuilder()).buildURL(DeliciousResources.GETALLPOSTS,
				tag != null ? tag : "");
		return getPosts(url);
	}

	public boolean addPost(String url, String description, String extended,
			String tags, Date date, boolean replace, boolean shared)
			throws Exception {
		URL turl = (new URLBuilder()).buildURL(DeliciousResources.ADDPOST,
				url != null ? url : "", description != null ? description : "",
				extended != null ? extended : "", tags != null ? tags : "",
				date != null ? sdf.format(date) : "", replace ? "yes" : "no",
				shared ? "yes" : "no");
		URLConnection uc = turl.openConnection();
		uc.connect();
		BufferedReader in = new BufferedReader(new InputStreamReader(uc
				.getInputStream()));
		in.readLine();
		String line = in.readLine();
		in.close();
		return line.contains("done");
	}

	public boolean deletePost(String url) throws Exception {
		URL turl = (new URLBuilder()).buildURL(DeliciousResources.DELETEPOST,
				url != null ? url : "");
		URLConnection uc = turl.openConnection();
		uc.connect();
		BufferedReader in = new BufferedReader(new InputStreamReader(uc
				.getInputStream()));
		in.readLine();
		String line = in.readLine();
		System.out.println(line);
		in.close();
		return line.contains("done");
	}

	public Date getLastUpdate() throws Exception {
		URL url = (new URLBuilder()).buildURL(DeliciousResources.GETLASTUPDATE);
		URLConnection uc = url.openConnection();
		uc.connect();
		BufferedReader in = new BufferedReader(new InputStreamReader(uc
				.getInputStream()));
		in.readLine();
		String line = in.readLine();
		in.close();
		String time = line.substring(line.indexOf("\"") + 1, line
				.lastIndexOf("\""));
		Date date = sdf.parse(time);
		return date;
	}

	public List<Tag> getAllTags() throws Exception {
		List<Tag> list = new ArrayList<Tag>();
		URL url = (new URLBuilder()).buildURL(DeliciousResources.GETALLTAGS);
		URLConnection uc = url.openConnection();
		uc.connect();
		Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(uc.getInputStream());
		NodeList rootNode = doc.getElementsByTagName("tags");
		Node postsNode = rootNode.item(0);
		NodeList childNodes = postsNode.getChildNodes();
		int length = childNodes.getLength();
		for (int i = 0; i < length; i++) {
			Node item = childNodes.item(i);
			if (item.getNodeName().intern() == "tag") {
				Tag t = new Tag();
				NamedNodeMap attributes = item.getAttributes();
				Node tagName = attributes.getNamedItem("tag");
				Node count = attributes.getNamedItem("count");
				t.setName(tagName.getNodeValue());
				t.setCount(Integer.valueOf(Integer.parseInt(count
						.getNodeValue())));
				list.add(t);
			}
		}

		return list;
	}

	public boolean renameTag(String oldName, String newName) throws Exception {
		URL url = (new URLBuilder()).buildURL(DeliciousResources.RENAMETAG,
				oldName != null ? oldName : "", newName != null ? newName : "");
		URLConnection uc = url.openConnection();
		uc.connect();
		BufferedReader in = new BufferedReader(new InputStreamReader(uc
				.getInputStream()));
		in.readLine();
		String line = in.readLine();
		in.close();
		return line.contains("done");
	}

	public Map<String, List<Tag>> getBundles() throws Exception {
		Map<String, List<Tag>> bundles = new TreeMap<String, List<Tag>>();
		URL url = (new URLBuilder()).buildURL(DeliciousResources.GETBUNDLES);
		URLConnection uc = url.openConnection();
		uc.connect();
		Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(uc.getInputStream());
		NodeList rootNode = doc.getElementsByTagName("bundles");
		Node postsNode = rootNode.item(0);
		NodeList childNodes = postsNode.getChildNodes();
		int length = childNodes.getLength();
		for (int i = 0; i < length; i++) {
			Node item = childNodes.item(i);
			if (item.getNodeName().intern() == "bundle") {
				List<Tag> list = new ArrayList<Tag>();
				NamedNodeMap attributes = item.getAttributes();
				Node bundleNameAttribute = attributes.getNamedItem("name");
				Node tagsAttribute = attributes.getNamedItem("tags");
				String tags[] = tagsAttribute.getNodeValue().split(" ");
				String as[] = tags;
				int j = 0;
				for (int k = as.length; j < k; j++) {
					String tagName = as[j];
					Tag t = new Tag();
					t.setName(tagName);
					list.add(t);
				}

				bundles.put(bundleNameAttribute.getNodeValue(), list);
			}
		}

		return bundles;
	}

	public boolean createBundle(String bundleName, String tags)
			throws Exception {
		URL url = (new URLBuilder()).buildURL(DeliciousResources.CREATEBUNDLE,
				bundleName != null ? bundleName : "", tags != null ? tags : "");
		URLConnection uc = url.openConnection();
		uc.connect();
		BufferedReader in = new BufferedReader(new InputStreamReader(uc
				.getInputStream()));
		in.readLine();
		String line = in.readLine();
		in.close();
		return line.contains("ok");
	}

	public boolean deleteBundle(String bundleName) throws Exception {
		URL url = (new URLBuilder()).buildURL(DeliciousResources.DELETEBUNDLE,
				bundleName != null ? bundleName : "");
		URLConnection uc = url.openConnection();
		uc.connect();
		BufferedReader in = new BufferedReader(new InputStreamReader(uc
				.getInputStream()));
		in.readLine();
		String line = in.readLine();
		in.close();
		return line.contains("ok");
	}

	private List<Post> getPosts(URL turl) throws IOException, SAXException,
			ParserConfigurationException, MalformedURLException, ParseException {
		List<Post> l = new ArrayList<Post>();
		URLConnection uc = turl.openConnection();
		uc.connect();
		Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(uc.getInputStream());
		parsePostListXML(l, doc);
		return l;
	}

	private void parsePostListXML(List<Post> l, Document doc)
			throws MalformedURLException, ParseException {
		NodeList rootNode = doc.getElementsByTagName("posts");
		Node postsNode = rootNode.item(0);
		NodeList childNodes = postsNode.getChildNodes();
		int length = childNodes.getLength();
		for (int i = 0; i < length; i++) {
			Node item = childNodes.item(i);
			if (item.getNodeName().intern() == "post") {
				Post p = new Post();
				NamedNodeMap attributes = item.getAttributes();
				Node link = attributes.getNamedItem("href");
				Node description = attributes.getNamedItem("description");
				Node hash = attributes.getNamedItem("hash");
				Node time = attributes.getNamedItem("time");
				Node tagsAttribute = attributes.getNamedItem("tag");
				String tagsValue = tagsAttribute.getNodeValue();
				String tags[] = tagsValue.split(" ");
				Set<String> postTags = new TreeSet<String>();
				String as[] = tags;
				int j = 0;
				for (int k = as.length; j < k; j++) {
					String thisTag = as[j];
					postTags.add(thisTag);
				}

				p.setDescription(description.getNodeValue());
				p.setUrl(new URL(link.getNodeValue()));
				p.setLastUpdate(sdf.parse(time.getNodeValue()));
				p.setHash(hash.getNodeValue());
				l.add(p);
			}
		}

	}

	private HTTPAuthenticator authenticator;
	private DeliciousDateFormatter sdf;
}
