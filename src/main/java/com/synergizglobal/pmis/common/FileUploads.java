package com.synergizglobal.pmis.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploads {
	
	private static Logger logger = Logger.getLogger(FileUploads.class);
	
	public static void singleFileSaving(MultipartFile multipartFile,String saveDirectory,String fileName) throws IllegalStateException, IOException {
		try {
			if (!StringUtils.isEmpty(multipartFile) && multipartFile.getSize() > 0) {
	            //String fileName = file.getOriginalFilename();	            
	            File directory = new File(saveDirectory);
                if (!directory.exists()) {
					directory.setReadable(true, false);
					directory.setWritable(true, false);
					directory.setExecutable(true, false);
                	directory.mkdirs();
                }
                
                File file = new File(saveDirectory + fileName);
				file.setReadable(true, false);
				file.setWritable(true, false);
				file.setExecutable(true, false);
				//file.createNewFile();
                multipartFile.transferTo(file);
                
				/*File file = new File(directory + fileName);
				file.setReadable(true, false);
				file.createNewFile();
				Set<PosixFilePermission> perms = Files.readAttributes(file.toPath(), PosixFileAttributes.class).permissions();
				multipartFile.transferTo(file);
				perms.add(PosixFilePermission.OTHERS_READ);
				perms.add(PosixFilePermission.GROUP_READ);
				perms.add(PosixFilePermission.OTHERS_READ);
				Files.setPosixFilePermissions(file.toPath(), perms);*/
                
	        }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("singleFileSaving() : "+e.getMessage());
		}
    }
	
	public static void multipleFilesSaving(List<MultipartFile> files,String saveDirectory) throws IllegalStateException, IOException {
		try { 
	        if (null != files && files.size() > 0) {
	            for (MultipartFile multipartFile : files) {
	                File direcory = new File(saveDirectory);
	                if (!direcory.exists()) {
	                	direcory.mkdirs();
	                }
	                if (!StringUtils.isEmpty(multipartFile) && multipartFile.getSize() > 0) {
	                	String fileName = multipartFile.getOriginalFilename();
		                
	                    multipartFile.transferTo(new File(saveDirectory + fileName));
	                }
	                
					/*Path path = Paths.get(saveDirectory + fileName);
					if (!Files.exists(path)) Files.createFile(path);
					Set<PosixFilePermission> perms = Files.readAttributes(path,PosixFileAttributes.class).permissions();
					
					perms.add(PosixFilePermission.OWNER_WRITE);
					perms.add(PosixFilePermission.OWNER_READ);
					perms.add(PosixFilePermission.OWNER_EXECUTE);
					perms.add(PosixFilePermission.GROUP_WRITE);
					perms.add(PosixFilePermission.GROUP_READ);
					perms.add(PosixFilePermission.GROUP_EXECUTE);
					perms.add(PosixFilePermission.OTHERS_WRITE);
					perms.add(PosixFilePermission.OTHERS_READ);
					perms.add(PosixFilePermission.OTHERS_EXECUTE);
					Files.setPosixFilePermissions(path, perms);*/
	            }
	        }
		} catch (Exception e) {
			logger.error("multipleFilesSaving() : "+e.getMessage());
		}
    }
	
	/*public static void saveBackgroundImage(MultipartFile file,String saveDirectory) throws IllegalStateException, IOException {
		try {
			if (null != file && !file.isEmpty()) {
	            String fileName = file.getOriginalFilename();
	            File direcory = new File(saveDirectory);
                if (!direcory.exists()) {
                	direcory.mkdirs();
                }
	            if (!"".equalsIgnoreCase(fileName)) {
	                file.transferTo(new File(saveDirectory + "login-background.jpg"));
	            }
	        }
		} catch (Exception e) {
			logger.error("singleFileSaving() : "+e.getMessage());
		}
    }*/
	
}
