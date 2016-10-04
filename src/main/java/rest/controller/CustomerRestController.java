package rest.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
 
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 

@Controller
public class CustomerRestController {
	
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
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam("login") String login, @RequestParam("password") String password) throws Exception {
		if (login != null && login != ""){
			System.out.println("Пользователь : " + login + " авторизован");
			return "";
		}
		return "error";
	}
    
    @RequestMapping(value={"/download"}, method = RequestMethod.GET)
    public String getDownload(ModelMap model) {
        return "download";
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