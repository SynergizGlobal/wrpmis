package com.synergizglobal.pmis.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.TemplateUploadService;
import com.synergizglobal.pmis.common.FileUploads;
import com.synergizglobal.pmis.common.OSValidator;
import com.synergizglobal.pmis.constants.CommonConstants;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.reference.model.TrainingType;

@Controller
public class TemplateUploadController {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(TemplateUploadController.class);
	
	@Autowired
	TemplateUploadService service;
	
	@RequestMapping(value="/template-upload",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView templateUpload(HttpSession session,@ModelAttribute TrainingType obj){
		ModelAndView model = new ModelAndView(PageConstants.templateUploadGrid);
		try {
			List<TrainingType> templatesList = service.getTemplatesList();
			model.addObject("templatesList", templatesList);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("templateUpload : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/upload-template", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView uploadTemplate(@ModelAttribute TrainingType obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		String userId = null;String userName = null;
		try{
			Random random = new Random(); 
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");
			model.setViewName("redirect:/template-upload");
			obj.setUploaded_by(userId);
			
			int renNum = random.nextInt(50);
			int renNum2 = random.nextInt(300);
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			String timeStamp_for_file = new SimpleDateFormat("yyyy-MM-dd_HH_MM_SS").format(Calendar.getInstance().getTime());
			obj.setUploaded_on(timeStamp);
			
			File theDir = new File(CommonConstants.TEMPLATE_OLD_FILEPATH);
			if (!theDir.exists()){
			    theDir.mkdirs();
			}
			String commonAttachment = obj.getCommonAttachment();
			Path oldFile  = Paths.get(CommonConstants.TEMPLATE_FILEPATH + obj.getTemplate_name()+".xlsx");
			String renameFile = obj.getTemplate_name() +"_"+ timeStamp_for_file+"_"+renNum+renNum2+".xlsx";
			Path renamingExistingFileToNewName = Files.move(oldFile, oldFile.resolveSibling(CommonConstants.TEMPLATE_FILEPATH + renameFile));
			MultipartFile file = obj.getTemplateFile();
			String fileDirectory = CommonConstants.TEMPLATE_FILEPATH ;
			
			String fileName = file.getOriginalFilename();
			String fileName_new = obj.getTemplate_name()+"."+ fileName.split("\\.")[1];
			obj.setAttachment(renameFile);
			obj.setCommonAttachment(fileName_new);
			FileUploads.singleFileSaving(file, fileDirectory, fileName_new);
			
			
			//FileUploads.singleFileSaving(file, CommonConstants.TEMPLATE_OLD_FILEPATH, renameFile);
			boolean isUnix = OSValidator.isUnix();
        	if(isUnix) {
            	String perm = "rwxrwxrwx";
            	Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(perm);
            	Files.setPosixFilePermissions(theDir.toPath(), permissions);
        	}
			Path movingExistingFileToNewPath = Files.move
			        (Paths.get(CommonConstants.TEMPLATE_FILEPATH + renameFile), 
			        Paths.get(CommonConstants.TEMPLATE_OLD_FILEPATH + renameFile));
			File old_file = new File(theDir + renameFile);
                if(old_file.exists()) {
                    //Setting file permissions for owner, group and others using PosixFilePermission
                     
                    HashSet<PosixFilePermission> permissions = new HashSet<PosixFilePermission>();
                     
                    //Adding owner's file permissions
                     
                    permissions.add(PosixFilePermission.OWNER_EXECUTE);
                    permissions.add(PosixFilePermission.OWNER_READ);
                    permissions.add(PosixFilePermission.OWNER_WRITE);
                     
                    //Adding group's file permissions
                     
                    permissions.add(PosixFilePermission.GROUP_EXECUTE);
                    permissions.add(PosixFilePermission.GROUP_READ);
                    permissions.add(PosixFilePermission.GROUP_WRITE);
                     
                    //Adding other's file permissions
                     
                    permissions.add(PosixFilePermission.OTHERS_EXECUTE);
                    permissions.add(PosixFilePermission.OTHERS_READ);
                    permissions.add(PosixFilePermission.OTHERS_WRITE);
                     
                    Files.setPosixFilePermissions(Paths.get(theDir + renameFile), permissions);
                }
        	
			boolean flag =  service.uploadTemplate(obj);
			//boolean old = setOldFilePath(obj,session,attributes);
			if(flag) {
				attributes.addFlashAttribute("success", "Template Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","uploading Template is failed. Try again.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error","uploading Template is failed. Try again.");
			logger.error("uploadTemplate : " + e.getMessage());
		}
		return model;
	}
	

	@RequestMapping(value = "/delete-template", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView deleteTemplate(@ModelAttribute TrainingType obj,RedirectAttributes attributes){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/template-upload");
			boolean flag =  service.deleteTemplate(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Template Deleted Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Something went Wrong. Try again.");
			logger.error("deleteRisk : " + e.getMessage());
		}
		return model;
	}

}
