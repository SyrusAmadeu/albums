package com.dws.albums.exception;

public class AlbumNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6335567552258481619L;

	public AlbumNotFoundException(String message) {
		super(message);
	}
}
