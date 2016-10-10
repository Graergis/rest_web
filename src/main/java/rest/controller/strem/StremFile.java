package rest.controller.strem;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class StremFile {

	private static String rootPath = "C:\\path\\";

	public static File stremFile(MultipartFile file) throws IOException {
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