package com.pet.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//it is my master POJO 


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetLombok {
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("category")
	private Category category;
	@JsonProperty("name")
	private String name;
	@JsonProperty("photoUrls")
	private List<String> photoUrls;
	@JsonProperty("tags")
	private List<Tag> tags;
	@JsonProperty("status")
	private String status;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Category{
		private Integer id;
		private String name;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class  Tag{
		private Integer id;
		private String name;
		
	}
	
	
	
}
