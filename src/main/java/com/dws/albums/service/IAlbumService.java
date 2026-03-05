package com.dws.albums.service;

import java.util.List;

import com.dws.albums.model.Album;

public interface IAlbumService {

	/**
	 * List all albums from Band Client API
	 * 
	 * @return a list of albums
	 */
	public List<Album> getAllAlbums();

	/**
	 * Fetch a album with a given ID from Bands Client API
	 * 
	 * @param albumId Band ID
	 * @return Album Object
	 */
	public Album getAlbum(String albumId);
	
	/**
	 * Refresh Albums cache
	 */
	public void refreshCache();

}
