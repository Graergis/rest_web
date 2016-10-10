package rest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import rest.controller.strem.StremFile;
import rest.entity.ConnectSQL;
import rest.entity.DocumentEntity;

@Controller
public class CustomerRestController {
	
	private static final int BUFFER_SIZE = 4096;
	
	@Autowired
	MultipartConfigElement multipartConfigElement;

    @RequestMapping(value={"/","/welcome"}, method = RequestMethod.GET)
    public String getHomePage(ModelMap model) {
        return "welcome";
    }
    
    @RequestMapping(value={"/error"}, method = RequestMethod.GET)
    public String getError(ModelMap model) {
        return "error";
    }
    
    @RequestMapping(value={"/loadFile"}, method = RequestMethod.GET)
    public String getLoadFile(ModelMap model) {
        return "loadFile";
    }

    @RequestMapping(value = "/loadFile", method = RequestMethod.POST)
	public String uploadFile(@RequestParam("file1") MultipartFile fileOne, @RequestParam("file2") MultipartFile fileTwo, @RequestParam("file3") MultipartFile fileThree,
			@RequestParam("name") String name,@RequestParam("author") String author,@RequestParam("comment") String comment) {
		String pathFileOne = StremFile.stremFile(fileOne);
		String pathFileTwo = StremFile.stremFile(fileTwo);
		String pathFileTree = StremFile.stremFile(fileThree);
		if (pathFileOne!="" || pathFileTwo!="" || pathFileTree!=""){
			ConnectSQL connect = new ConnectSQL(pathFileOne, pathFileTwo, pathFileTree, name, author, comment);
			return "loadFile";
		} else {
			return "error";
		}
	}
    
    @RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView client() {
		List<DocumentEntity> list = ConnectSQL.getList();
		ModelAndView model = new ModelAndView("download");
		model.addObject("lists", list);
		return model;
	}

    @RequestMapping(value="/download/{type}", method = RequestMethod.GET)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("type") String type) throws IOException, SQLException {
	    File file = null;
	    String filePath = "";
	    filePath = ConnectSQL.connec(type);
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