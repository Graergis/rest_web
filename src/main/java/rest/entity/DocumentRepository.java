package rest.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.entity.DocumentEntity;

@Repository
public interface  DocumentRepository extends JpaRepository<DocumentEntity, Long> {
	DocumentEntity findByName(int id);
}
