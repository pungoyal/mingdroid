package com.thoughtworks.mingle.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.thoughtworks.mingle.Constants;
import com.thoughtworks.mingle.converters.AuthorConverter;
import com.thoughtworks.mingle.converters.CardConverter;
import com.thoughtworks.mingle.converters.CardPropertiesConverter;
import com.thoughtworks.mingle.converters.CardTypeConverter;
import com.thoughtworks.mingle.converters.MurmurConverter;
import com.thoughtworks.mingle.converters.MurmursConverter;
import com.thoughtworks.mingle.converters.ProjectConverter;
import com.thoughtworks.mingle.converters.ProjectsConverter;
import com.thoughtworks.mingle.domain.Author;
import com.thoughtworks.mingle.domain.Card;
import com.thoughtworks.mingle.domain.CardProperties;
import com.thoughtworks.mingle.domain.CardType;
import com.thoughtworks.mingle.domain.Murmur;
import com.thoughtworks.mingle.domain.Murmurs;
import com.thoughtworks.mingle.domain.Project;
import com.thoughtworks.mingle.domain.Projects;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.Base64Encoder;

public class MingleClient {
	private final XStream xstream;
	private final Base64Encoder base64Encoder;
	private final Context context;

	private String server;
	private String project;
	private String protocol;
	private String username;
	private String password;
	private String port;

	public MingleClient(Context context) {
		this.context = context;
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

	public Card getCard(int cardNumber) {
		String response = getResponseXML("/projects/iraqi_youth_project/cards/" + cardNumber + ".xml");
		if ("".equals(response))
			return new Card();

		xstream.registerConverter(new CardConverter());
		xstream.registerConverter(new CardTypeConverter());
		xstream.registerConverter(new CardPropertiesConverter());
		xstream.alias("card", Card.class);
		xstream.alias("card_type", CardType.class);
		xstream.alias("properties", CardProperties.class);

		return (Card) xstream.fromXML(response);
	}

	public Projects getProjects() {
		String response = getResponseXML("/projects.xml");
		if ("".equals(response))
			return new Projects();

		xstream.registerConverter(new ProjectsConverter());
		xstream.registerConverter(new ProjectConverter());
		xstream.alias("project", Project.class);
		xstream.alias("projects", Projects.class);

		return (Projects) xstream.fromXML(response);
	}

	public Murmurs getMurmurs() {
		String response = getResponseXML("/projects/" + project + "/murmurs.xml");
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

	private String getResponseXML(String apiSlug) {
		StringBuffer buffer = new StringBuffer();

		HttpURLConnection connection = null;

		try {
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
		} catch (IOException e) {
			Toast.makeText(context, "Server cannot be reached. Try again!", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		return buffer.toString();
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