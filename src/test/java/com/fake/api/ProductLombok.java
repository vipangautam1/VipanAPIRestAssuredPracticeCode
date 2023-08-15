package com.fake.api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLombok {
	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	//inner class
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Rating {
		private int count;
		private double rate;
	}
}
