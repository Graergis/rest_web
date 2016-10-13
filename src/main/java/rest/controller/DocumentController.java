package rest.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import rest.controller.util.FileUtils;
import rest.entity.Document;
import rest.entity.User;
import rest.service.DocumentService;
import rest.service.FileService;
import rest.service.UserService;

@Controller
@Transactional
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@Autowired
	private FileService fileService;
	
	@Autowired
	private UserService userService;
	
	@Value("${path}")
	private String rootPath;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public ModelAndView getHomePage() {
		List<Document> list = documentService.findAll();
		ModelAndView model = new ModelAndView("welcome");
		model.addObject("lists", list);
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String saveDocument(@RequestParam("file") MultipartFile[] files, @RequestParam("name") String name,
			@RequestParam("author") String author, @RequestParam("comment") String comment) throws IOException {
		Document document = new Document(name, new Date(), comment);
		document = documentService.save(document);
		User user = new User(author, "private", document);
		user = userService.save(user);
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