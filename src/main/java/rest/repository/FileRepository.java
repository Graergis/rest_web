package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rest.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
