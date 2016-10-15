package rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	List<Document> findByNameContainingAndCommentContainingAndAuthorContaining(String name, String comment,
			String author);

}
