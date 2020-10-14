package com.synergizglobal.pmis.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUploads {
	
	private static Logger logger = Logger.getLogger(FileUploads.class);
	
	public static void singleFileSaving(MultipartFile file,String saveDirectory,String fileName) throws IllegalStateException, IOException {
		try {
			if (null != file && !file.isEmpty()) {
	            //String fileName = file.getOriginalFilename();	            
	            File direcory = new File(saveDirectory);
                if (!direcory.exists()) {
                	direcory.mkdirs();
                }
				/*Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
				FileAttribute<Set<PosixFilePermission>> fileAttributes = PosixFilePermissions.asFileAttribute(permissions);
				Files.createDirectories(direcory.toPath(), fileAttributes);*/
	            
	            if (!"".equalsIgnoreCase(fileName)) {
	                file.transferTo(new File(saveDirectory + fileName));
	            }
	            
	            
				/* Path path = Paths.get(saveDirectory + fileName);
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
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("singleFileSaving() : "+e.getMessage());
		}
    }
	
	public static void multipleFilesSaving(List<MultipartFile> files,String saveDirectory) throws IllegalStateException, IOException {
		try { 
	        if (null != files && files.size() > 0) {
	            for (MultipartFile multipartFile : files) {
	                String fileName = multipartFile.getOriginalFilename();
	                File direcory = new File(saveDirectory);
	                if (!direcory.exists()) {
	                	direcory.mkdirs();
	                }
	                if (!"".equalsIgnoreCase(fileName)) {
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
