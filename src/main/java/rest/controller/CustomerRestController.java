package rest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
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
public class CustomerRestController {

	private static final int BUFFER_SIZE = 4096;

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
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html; charset=utf-8");
		if (fileOne.getOriginalFilename()!=""){
			File pathFileOne = StremFile.stremFile(fileOne);
			Document document = new Document(name, new Date(), author, comment);
			document = documentService.addDocument(document);
			rest.entity.File file1 = new rest.entity.File(pathFileOne.getName(), pathFileOne.getAbsolutePath(),
					document);
			fileService.addFile(file1);
		}
		if(fileTwo.getOriginalFilename()!=""){
			Document document = new Document(name, new Date(), author, comment);
			document = documentService.addDocument(document);
			File pathFileTwo = StremFile.stremFile(fileTwo);
			rest.entity.File file2 = new rest.entity.File(pathFileTwo.getName(), pathFileTwo.getAbsolutePath(),
					document);
			fileService.addFile(file2);
		}
		if(fileThree.getOriginalFilename()!=""){
			Document document = new Document(name, new Date(), author, comment);
			document = documentService.addDocument(document);
			File pathFileTree = StremFile.stremFile(fileThree);
			rest.entity.File file3 = new rest.entity.File(pathFileTree.getName(), pathFileTree.getAbsolutePath(),
					document);
			fileService.addFile(file3);
		}
		return getHomePage();
	}

	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long fileId)
			throws IOException, SQLException {
		File file = null;
		String filePath = "";
		filePath = fileService.findOne(fileId).getPath();
		file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		ServletContext context = request.getServletContext();
		String mimeType = context.getMimeType(filePath);
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setContentLength((int) file.length());
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
		response.setHeader(headerKey, headerValue);
		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		outStream.close();
	}
}