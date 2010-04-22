package com.thoughtworks.mingle.web;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.thoughtworks.mingle.Constants;
import com.thoughtworks.mingle.converters.AuthorConverter;
import com.thoughtworks.mingle.converters.CardConverter;
import com.thoughtworks.mingle.converters.CardPropertiesConverter;
import com.thoughtworks.mingle.converters.CardTypesConverter;
import com.thoughtworks.mingle.converters.MurmurConverter;
import com.thoughtworks.mingle.converters.MurmursConverter;
import com.thoughtworks.mingle.converters.ProjectConverter;
import com.thoughtworks.mingle.converters.ProjectsConverter;
import com.thoughtworks.mingle.domain.Author;
import com.thoughtworks.mingle.domain.Card;
import com.thoughtworks.mingle.domain.CardProperties;
import com.thoughtworks.mingle.domain.CardTypes;
import com.thoughtworks.mingle.domain.Murmur;
import com.thoughtworks.mingle.domain.Murmurs;
import com.thoughtworks.mingle.domain.Project;
import com.thoughtworks.mingle.domain.Projects;
import com.thoughtworks.mingle.exceptions.CardNotFoundException;
import com.thoughtworks.mingle.exceptions.ServerUnreachableException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.Base64Encoder;

public class MingleClient {
	private final XStream xstream;
	private final Base64Encoder base64Encoder;

	private String server;
	private String project;
	private String protocol;
	private String username;
	private String password;
	private String port;

	public MingleClient(Context context) {
		base64Encoder = new Base64Encoder();

		SharedPreferences preferences = context.getSharedPreferences(Constants.APPLICATION_KEY, 0);
		username = preferences.getString(Constants.USERNAME_KEY, "puneet");
		password = preferences.getString(Constants.PASSWORD_KEY, "puneet");
		server = preferences.getString(Constants.SERVER_KEY, "10.0.2.2");
		project = preferences.getString(Constants.PROJECT_KEY, "mxl");
		protocol = preferences.getString(Constants.HTTPS_KEY, "http");
		port = preferences.getString(Constants.PORT_KEY, "");

		xstream = new XStream();
	}

	public CardTypes getCardTypes(String type) throws FileNotFoundException, IOException {
		String response = executeMQL("type=" + type);
		if ("".equals(response))
			return new CardTypes();

		xstream.registerConverter(new CardTypesConverter());
		xstream.alias("results", CardTypes.class);

		return (CardTypes) xstream.fromXML(response);
	}

	public Card getCard(int cardNumber) throws CardNotFoundException {
		String response = null;
		try {
			response = getResponseXML("/projects/" + project + "/cards/" + cardNumber + ".xml");
			xstream.registerConverter(new CardConverter());
			xstream.registerConverter(new CardPropertiesConverter());
			xstream.alias("card", Card.class);
			xstream.alias("properties", CardProperties.class);

			return (Card) xstream.fromXML(response);
		} catch (Exception e) {
			throw new CardNotFoundException(cardNumber);
		}
	}

	public Projects getProjects() throws ServerUnreachableException {
		String response;
		try {
			response = getResponseXML("/projects.xml");
			xstream.registerConverter(new ProjectsConverter());
			xstream.registerConverter(new ProjectConverter());
			xstream.alias("project", Project.class);
			xstream.alias("projects", Projects.class);

			return (Projects) xstream.fromXML(response);
		} catch (Exception e) {
			throw new ServerUnreachableException(server);
		}
	}

	/*
	 * TODO put a wait cursor around this
	 */
	public Murmurs getMurmurs() {
		String response = null;
		try {
			response = getResponseXML("/projects/" + project + "/murmurs.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ("".equals(response))
			return new Murmurs();

		xstream.registerConverter(new MurmursConverter());
		xstream.registerConverter(new MurmurConverter());
		xstream.registerConverter(new AuthorConverter());
		xstream.alias("author", Author.class);
		xstream.alias("murmur", Murmur.class);
		xstream.alias("murmurs", Murmurs.class);

		return (Murmurs) xstream.fromXML(response);
	}

	private String getResponseXML(String apiSlug) throws IOException {
		StringBuffer buffer = new StringBuffer();

		HttpURLConnection connection = null;
		URL u = new URL(constructURL(apiSlug));
		connection = (HttpURLConnection) u.openConnection();
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);

		String encode = base64Encoder.encode((username + ":" + password).getBytes());
		connection.addRequestProperty("Authorization", "Basic " + encode);
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		String line = null;
		String line_separator = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			buffer.append(line + line_separator);
		}
		connection.disconnect();
		return buffer.toString();
	}

	private String executeMQL(String mql) throws FileNotFoundException, IOException {
		return getResponseXML("/projects/" + project + "/cards/execute_mql.xml" + "?mql=" + mql);
	}

	private String constructURL(String apiSlug) {
		String urlString = protocol + "://" + server;

		if (port.length() != 0)
			urlString += (":" + port);

		urlString += ("/api/v2" + apiSlug);
		Log.v(Constants.APPLICATION_KEY, urlString);
		return urlString;
	}
}