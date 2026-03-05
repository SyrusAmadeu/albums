package com.dws.albums.model.dto;

import java.time.LocalDateTime;

import com.dws.albums.model.Band;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDto {

	private String id;
	private String name;
	private LocalDateTime releasedDate;
	private Band band;
}
