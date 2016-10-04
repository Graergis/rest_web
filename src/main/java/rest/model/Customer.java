package rest.model;

import java.util.Date;

public class Customer {

	private Long id;
	private String nameDocument;
	private String nameAuthor;
	private String note;
	private Date date;

	public Customer(long id, String nameDocument, String nameAuthor, String note) {
		this.id = id;
		this.nameDocument = nameDocument;
		this.nameAuthor = nameAuthor;
		this.note = note;
		this.date = new Date();
	}

	public Customer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameDocument() {
		return nameDocument;
	}

	public void setNameDocument(String nameDocument) {
		this.nameDocument = nameDocument;
	}

	public String getNameAuthor() {
		return nameAuthor;
	}

	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Customer [nameDocument=" + nameDocument + ", nameAuthor=" + nameAuthor + ", note=" + note + ", date="
				+ date + "]";
	}

}