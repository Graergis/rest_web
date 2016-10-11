package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
