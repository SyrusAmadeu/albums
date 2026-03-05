package com.dws.albums.mapper;

import com.dws.albums.model.Album;
import com.dws.albums.model.dto.AlbumDto;

public class AlbumMapper {

    public static Album dtoToAlbum(AlbumDto albumDto) {
        Album album = new Album();
        album.setId(albumDto.getId());
        album.setName(albumDto.getName());
        album.setBand(albumDto.getBand());
        album.setReleasedDate(albumDto.getReleasedDate());

        return album;
    }
}
