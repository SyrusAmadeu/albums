package com.dws.albums.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dws.albums.model.Album;
import com.dws.albums.service.IAlbumService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/albums")
@Validated
@AllArgsConstructor
public class AlbumController {

	private final IAlbumService iAlbumService;

	@Operation(summary = "List all albums", description = "Returns all albums available.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List successfully returned"),
        @ApiResponse(responseCode = "502", description = "External API Bad Gateway", 
		content = @Content(mediaType = "application/json",
    		schema = @Schema(type = "string", example = "Error 502: Bad Gateway")))
    })
	@GetMapping
	public ResponseEntity<List<Album>> getAllAlbums() {
		List<Album> albums = iAlbumService.getAllAlbums();
		return ResponseEntity.status(HttpStatus.OK).body(albums);
	}

	@Operation(summary = "Search a album by ID", description = "Returns album data")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Album Found"),
	        @ApiResponse(responseCode = "400", description = "Error 400: Bad Request: Incorrect ID format",
	        		content = @Content(mediaType = "application/json", 
	        			schema = @Schema(type = "string", example = "The given id must be in the following format: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"))),
	        @ApiResponse(responseCode = "404", description = "Error 404: Album Not Found", 
	        		content = @Content(mediaType = "application/json",
                    	schema = @Schema(type = "string", example = "Album Not Found"))),
	        @ApiResponse(responseCode = "502", description = "External API Bad Gateway", 
	        		content = @Content(mediaType = "application/json",
                		schema = @Schema(type = "string", example = "Error 502: Bad Gateway")))
	    })
	@GetMapping("/{albumId}")
	public ResponseEntity<Album> getAlbum(
            @PathVariable
            @Pattern(regexp = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$",
					message = "The given id must be in the following format: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
            String albumId) {
		Album album = iAlbumService.getAlbum(albumId);
		return ResponseEntity.status(HttpStatus.OK).body(album);
	}
	
	@Operation(summary = "Clean albums cache", description = "Clean all albums cache")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "204", description = "Cache cleaned")
	    })
	@DeleteMapping("/cache")
	public ResponseEntity<Void> clearCache() {
		iAlbumService.refreshCache();
	    return ResponseEntity.noContent().build();
	}
}
