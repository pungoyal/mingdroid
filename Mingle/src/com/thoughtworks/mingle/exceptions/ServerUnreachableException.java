package com.thoughtworks.mingle.exceptions;

public class ServerUnreachableException extends Exception {

	private final String server;
	private static final long serialVersionUID = 4637325489342532887L;

	public ServerUnreachableException(String server) {
		this.server = server;
	}
}
