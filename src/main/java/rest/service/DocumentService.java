package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.entity.Document;
import rest.repository.DocumentRepository;

@Service
public class DocumentService {

	@Autowired
	private DocumentRepository documentRepository;

	public Document save(Document document) {
		Document saveDocument = documentRepository.save(document);

		return saveDocument;
	}

	public List<Document> findAll() {
		return documentRepository.findAll();
	}
}