package br.com.erudio.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String author;

	private LocalDateTime launchDate;
	
	private Double price;
	
	private String title;

	private Integer updatedTimes = 0;
	
	public Book() {}
}