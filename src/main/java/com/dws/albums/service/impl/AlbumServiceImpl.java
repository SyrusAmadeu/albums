package com.dws.albums.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dws.albums.client.AlbumsApiClient;
import com.dws.albums.exception.AlbumNotFoundException;
import com.dws.albums.mapper.AlbumMapper;
import com.dws.albums.model.Album;
import com.dws.albums.model.dto.AlbumDto;
import com.dws.albums.service.IAlbumService;

@Service
public class AlbumServiceImpl implements IAlbumService {

	private static final Logger log = LoggerFactory.getLogger(AlbumServiceImpl.class);
	
	private final AlbumsApiClient albumsApiClient;
	
	public AlbumServiceImpl(AlbumsApiClient albumsApiClient) {
		this.albumsApiClient = albumsApiClient;
	}

    @Override
	@Cacheable(value = "albums", unless = "#result == null || #result.isEmpty()")
    public List<Album> getAllAlbums() {
    	log.info(">> getAllAlbums() :: Fetching all albums.");
        return albumsApiClient.fetchAlbums()
                .stream()
                .map(AlbumMapper::dtoToAlbum)
                .toList();
    }


    @Override
	@Cacheable(value = "album", keyGenerator = "customKeyGen")
    public Album getAlbum(String albumId) {
    	log.info(">> getAlbum() :: Fetching album with ID: {}.", albumId);
    	AlbumDto album = albumsApiClient.fetchAlbum(albumId);
        if( album == null) {
        	throw new AlbumNotFoundException(String.format("Album Not Found. Album ID: %s", albumId));
        }
		
		return AlbumMapper.dtoToAlbum(album);
    }

    @Override
    @CacheEvict(value = {"albums", "album"}, allEntries = true)
    public void refreshCache() {
    	log.info(">> refreshCache() :: Refrshing albums cache.");
    }

   
    
    
}
