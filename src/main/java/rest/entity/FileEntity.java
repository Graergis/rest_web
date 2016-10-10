package rest.entity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class FileEntity {

	@Id
    @Column(name = "id")
    private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Id_doc")
	private Long idDoc;
	
    @Column(name = "name")
    private String name;
    
    @Column(name = "path")
    private String path;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdDoc() {
		return idDoc;
	}

	public void setIdDoc(Long idDoc) {
		this.idDoc = idDoc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
    
}
