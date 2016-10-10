package rest.controller.strem;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class StremFile {
	
	public static String stremFile(MultipartFile file) {
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
				System.out.println(uploadedFile.getAbsolutePath());
				return uploadedFile.getAbsolutePath();
 
			} catch (Exception e) {
				return "Ошибка загрузки файла " + name + " => " + e.getMessage();
			}
		} else {
			return "error";
		}
	}
}