package rest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rest.service.FileService;

@Controller
@Transactional
public class FileController {

	private static final int BUFFER_SIZE = 4096;

	@Autowired
	private FileService fileService;

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
