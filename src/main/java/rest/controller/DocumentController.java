package rest.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import rest.controller.strem.StremFile;
import rest.entity.Document;
import rest.service.DocumentService;
import rest.service.FileService;

@Controller
@Transactional
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@Autowired
	private FileService fileService;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public ModelAndView getHomePage() {
		List<Document> list = documentService.getAll();
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("lists", list);
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView uploadFile(@RequestParam("file1") MultipartFile fileOne,
			@RequestParam("file2") MultipartFile fileTwo, @RequestParam("file3") MultipartFile fileThree,
			@RequestParam("name") String name, @RequestParam("author") String author,
			@RequestParam("comment") String comment) throws IOException {
		Document document = new Document(name, new Date(), author, comment);
		document = documentService.addDocument(document);
		if (fileOne.getOriginalFilename()!=""){
			File pathFileOne = StremFile.stremFile(fileOne);
			rest.entity.File file1 = new rest.entity.File(pathFileOne.getName(), pathFileOne.getAbsolutePath(),
					document);
			fileService.addFile(file1);
		}
		if(fileTwo.getOriginalFilename()!=""){
			File pathFileTwo = StremFile.stremFile(fileTwo);
			rest.entity.File file2 = new rest.entity.File(pathFileTwo.getName(), pathFileTwo.getAbsolutePath(),
					document);
			fileService.addFile(file2);
		}
		if(fileThree.getOriginalFilename()!=""){
			File pathFileTree = StremFile.stremFile(fileThree);
			rest.entity.File file3 = new rest.entity.File(pathFileTree.getName(), pathFileTree.getAbsolutePath(),
					document);
			fileService.addFile(file3);
		}
		return getHomePage();
	}
}