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
	public String saveDocument(@RequestParam("file") MultipartFile[] files, @RequestParam("name") String name,
			@RequestParam("author") String author, @RequestParam("comment") String comment) throws IOException {
		Document document = new Document(name, new Date(), author, comment);
		document = documentService.addDocument(document);
		for (MultipartFile file : files) {
			File savedFile = StremFile.stremFile(file);
			if (savedFile != null) {
				rest.entity.File file1 = new rest.entity.File(savedFile.getName(), savedFile.getAbsolutePath(),
						document);
				fileService.addFile(file1);
			}
		}
		return "redirect:/";
	}
}