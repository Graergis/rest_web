package rest.controller;

public class DocumentFile {

	private String name;
	private String author;
	private String comment;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Document [name=" + name + ", author=" + author + ", comment=" + comment + "]";
	}
	
	public DocumentFile(){
		
	}
	
}
