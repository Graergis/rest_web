package rest.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Document")
public class DocumentEntity implements Serializable {

	@Id
    //@Column(name = "id")
	@OneToMany
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_creation")
    private String date;

    @Column(name = "author")
    private String author;

    @Column(name = "comment")
    private String comment;
    
    public DocumentEntity(){
    	
    }
    
    public DocumentEntity(Long id, String name, String date, String author, String comment){
    	this.id = id;
    	this.name = name;
    	this.date = date;
    	this.author = author;
    	this.comment = comment;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
}
