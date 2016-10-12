package rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.entity.File;
import rest.repository.FileRepository;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepository;

	public File save(File file) {
		File saveFile = fileRepository.save(file);

		return saveFile;
	}

	public List<File> findAll() {
		return fileRepository.findAll();
	}

	public File findOne(Long type) {
		return fileRepository.findOne(type);
	}
}
