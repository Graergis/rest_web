package rest.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import rest.controller.util.FileUtils;
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
	
	@Value("${path}")
	private String rootPath;

	@RequestMapping(value = { "/", "/search" }, method = RequestMethod.GET)
	public ModelAndView getHomePage(@RequestParam(value = "comment", required = false) String comment, 
			@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "name", required = false) String name) {
		List<Document> list = documentService.findByNameContainingAndCommentContainingAndAuthorContaining(name,
				comment, author);
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("lists", list);
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String saveDocument(@RequestParam("file") MultipartFile[] files, @RequestParam("name") String name, @RequestParam("comment") String comment) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Document document = new Document(name, new Date(), auth.getName(), comment);
		document = documentService.save(document);
		for (MultipartFile file : files) {
			File savedFile = FileUtils.saveToDisc(rootPath, file);
			if (savedFile != null) {
				rest.entity.File file1 = new rest.entity.File(savedFile.getName(), savedFile.getAbsolutePath(),
						document);
				fileService.save(file1);
			}
		}
		return "redirect:/";
	}
}