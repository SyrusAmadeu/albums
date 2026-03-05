package com.dws.albums.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {

	@Schema(description = "Album ID", example = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
	@Pattern(regexp = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$", message = "The value must be in the following format: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
	private String id;

	@Schema(description = "Album name", example = "Riding the Lightning")
	private String name;

	@Schema(description = "Albums release date", example = "1985-02-02T12:00:00:0000")
	private LocalDateTime releasedDate;

	@Schema(description = "Author")
	private Band band;
}
