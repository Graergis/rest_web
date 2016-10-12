package rest.controller.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	public static File saveToDisc(String rootPath, MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			File dir = new File(rootPath + File.separator + "loadFiles");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String name = file.getOriginalFilename();
			File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);
			try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile))) {
				byte[] bytes = file.getBytes();
				stream.write(bytes);
				stream.flush();
			}
			return uploadedFile;
		} else {
			return null;
		}
	}
}