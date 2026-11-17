package com.springboot.domain;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.validator.BookId;

import lombok.Data;

@Data
public class Book {
	@BookId
	private String bookid;//도서id
	private String name;//도서 제목
	private BigDecimal unitPrice;//가격
	private String author;//저자
	private String description;//설명
	private String publisher;//출판사
	private String category;//분류
	private long unitsInStock;//재고수
	private String releaseDate;//출판일(년/월)
	private String condition;//신규도서or중고도서or 전자책
	private String fileName;
	private MultipartFile bookImage;
	
/*	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}	
*/
}
