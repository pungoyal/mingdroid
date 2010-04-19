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

import com.thoughtworks.mingle.Constants;
import com.thoughtworks.mingle.converters.AuthorConverter;
import com.thoughtworks.mingle.converters.MurmurConverter;
import com.thoughtworks.mingle.converters.MurmursConverter;
import com.thoughtworks.mingle.converters.ProjectConverter;
import com.thoughtworks.mingle.converters.ProjectsConverter;
import com.thoughtworks.mingle.domain.Author;
import com.thoughtworks.mingle.domain.Murmur;
import com.thoughtworks.mingle.domain.Murmurs;
import com.thoughtworks.mingle.domain.Project;
import com.thoughtworks.mingle.domain.Projects;
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

	public Projects getProjects() throws IOException {
		checkAlive();

		String response = getResponseXML("/api/v2/projects.xml");
		if ("".equals(response))
			return new Projects();

		xstream.registerConverter(new ProjectsConverter());
		xstream.registerConverter(new ProjectConverter());
		xstream.alias("project", Project.class);
		xstream.alias("projects", Projects.class);

		return (Projects) xstream.fromXML(response);
	}

	public Murmurs getMurmurs() {
		String response = "";
		try {
			response = getResponseXML("/api/v2/projects/" + project + "/murmurs.xml");
		} catch (IOException e) {
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

	public boolean checkAlive() throws IOException {
		getResponseXML("/");
		return true;
	}

	private String getResponseXML(String apiSlug) throws IOException {
		Log.v(Constants.APPLICATION_KEY, apiSlug);

		StringBuffer buffer = new StringBuffer();

		URL u = new URL(constructURL(apiSlug));
		HttpURLConnection connection = (HttpURLConnection) u.openConnection();
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

	private String constructURL(String apiSlug) {
		String urlString = protocol + "://" + server;

		if (port.length() != 0)
			urlString += (":" + port);

		urlString += apiSlug;
		return urlString;
	}
}