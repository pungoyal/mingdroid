package com.thoughtworks.mingle.web;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

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

public class MingleClient {

	private DefaultHttpClient client;
	private XStream xstream;
	private String server;
	private String project;
	private final Context context;
	private String protocol;

	public MingleClient(Context context) {
		this.context = context;
		SharedPreferences preferences = context.getSharedPreferences(Constants.APPLICATION_KEY, 0);

		String username = preferences.getString(Constants.USERNAME_KEY, "puneet");
		String password = preferences.getString(Constants.PASSWORD_KEY, "puneet");
		server = preferences.getString(Constants.SERVER_KEY, "10.0.2.2");
		project = preferences.getString(Constants.PROJECT_KEY, "mxl");
		protocol = preferences.getString(Constants.HTTPS_KEY, "http://");

		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, 3000);
		HttpConnectionParams.setSoTimeout(httpParameters, 5000);

		client = new DefaultHttpClient(httpParameters);
		client.getCredentialsProvider().setCredentials(new AuthScope(null, -1),
				new UsernamePasswordCredentials(username, password));
		xstream = new XStream();
	}

	public Projects getProjects() throws IOException {
		checkAlive();

		String url = protocol + server + ":8080/api/v2/projects.xml";

		String response = getResponseXML(url);
		if ("".equals(response))
			return new Projects();

		xstream.registerConverter(new ProjectsConverter());
		xstream.registerConverter(new ProjectConverter());
		xstream.alias("project", Project.class);
		xstream.alias("projects", Projects.class);

		return (Projects) xstream.fromXML(response);
	}

	public Murmurs getMurmurs() {
		String url = protocol + server + ":8080/api/v2/projects/" + project + "/murmurs.xml";

		String response = getResponseXML(url);
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

		HttpGet request = createRequest(protocol + server + ":8080/");
		client.execute(request);
		releaseConnections();
		return true;
	}

	private String getResponseXML(String url) {
		HttpGet request = createRequest(url);
		String response = "";
		try {
			HttpResponse r = client.execute(request);
			response = EntityUtils.toString(r.getEntity());
			releaseConnections();
		} catch (HttpResponseException e) {
			Toast.makeText(context, "HTTP response problem", Toast.LENGTH_LONG).show();
		} catch (UnknownHostException e) {
			Toast.makeText(context, "Unknown server - " + server, Toast.LENGTH_LONG).show();
		} catch (ClientProtocolException e) {
			Toast.makeText(context, "Client protocol exception", Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private void releaseConnections() {
		ClientConnectionManager connectionManager = client.getConnectionManager();
		connectionManager.closeExpiredConnections();
	}

	private HttpGet createRequest(String url) {
		Log.v(Constants.APPLICATION_KEY, url);
		HttpGet request = new HttpGet(url);
		return request;
	}
}