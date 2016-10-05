package rest.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import rest.connect.ConnectSQL;
 

@Controller
public class CustomerRestController {
	
	@Autowired
	MultipartConfigElement multipartConfigElement;
	
	private static final String INTERNAL_FILE="test2.jpg";
    private static final String EXTERNAL_FILE_PATH="E:/test.zip";
     
 
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
	//@ResponseBody
	public String uploadFile(@RequestParam("file1") MultipartFile file) {
 
		String name = null;
 
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
 
				name = file.getOriginalFilename();
 
				String rootPath = "C:\\path\\";  
				File dir = new File(rootPath + File.separator + "loadFiles");
 
				if (!dir.exists()) {
					dir.mkdirs();
				}
 
				File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);
 
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
				stream.write(bytes);
				stream.flush();
				stream.close();
 
 
				return "loadFile";
 
			} catch (Exception e) {
				return "Ошибка загрузки файла " + name + " => " + e.getMessage();
			}
		} else {
			return "error";
		}
	}
    
    
    @RequestMapping(value={"/load"}, method = RequestMethod.GET)
    public String getLoad(ModelMap model) {
        return "load";
    }
    
    @RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView client() {
			List<String> list = ConnectSQL.getList();
			ModelAndView model = new ModelAndView("download");
			model.addObject("lists", list);
			return model;
	}
    
    @RequestMapping(value="/download/{type}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, @PathVariable("type") String type) throws IOException {
     
        File file = null;
         
        if(type.equalsIgnoreCase("internal")){
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            file = new File(classloader.getResource(INTERNAL_FILE).getFile());
        }else{
            file = new File(EXTERNAL_FILE_PATH);
        }
         
        if(!file.exists()){
            String errorMessage = "Файл, который вы ищете, не существует";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }
         
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
         
        System.out.println("mimetype : "+mimeType);
         
        response.setContentType(mimeType);
         
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
 
        response.setContentLength((int)file.length());
 
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
 
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
}