package com.synergizglobal.pmis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.service.LandAcquisitionService;

@Controller
public class LandAcquisitionController {
	Logger logger = Logger.getLogger(LandAcquisitionController.class);
	@Autowired
	LandAcquisitionService service;
	
	@Value("${common.error.message}")
	public String commonError;
	
	@Value("${input.sheet.task.update}")
	public String taskUpdate;
	
	@Value("${input.sheet.task.complete}")
	public String taskComplete;
	
	@Value("${template.upload.common.error}")
	public String uploadCommonError;
	
	@Value("${template.upload.formatError}")
	public String uploadformatError;
	
	/**
	 * This method landAcquisition() will execute when user request the for land-acquisition module.
	 * 
	 * @param session it will check the session of the user.
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/land-acquisition",method=RequestMethod.GET)
	public ModelAndView landAcquisition(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.landAcquisition);
		try {
			List<LandAcquisition> landAcquisitionList = service.getLandAcquisitionList();
			model.addObject("landAcquisitionList", landAcquisitionList);
			
			List<LandAcquisition> statusList = service.getLandAcquisitionSatausList();
			model.addObject("statusList", statusList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("landAcquisition : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method getLandAcquisition() will execute when user click on the update button.
	 * 
	 * @param landAcquisitionId it takes the land acquisition id as request parameter.
 	 * @param session it will check the session of the user. 
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/get-land-acquisition",method=RequestMethod.POST)
	public ModelAndView getLandAcquisition(@RequestParam String landAcquisitionId, HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.updateLandAcquisition);
		try {
			model.addObject("homeHeader", "yes");
			List<LandAcquisition> statusList = service.getLandAcquisitionSatausList();
			model.addObject("statusList", statusList);
			
			LandAcquisition obj = service.getLandAcquisition(landAcquisitionId);
			model.addObject("obj", obj);
		} catch (Exception e) {
			logger.info("getLandAcquisition : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method getLandAcquisition() call the ajax request
	 * 
	 * @param landAcquisitionId it takes the land acquisition id as request parameter.
	 * @return of this method is obj.
	 */
	@RequestMapping(value = "/ajax/getLandAcquisition", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public LandAcquisition getLandAcquisition(@RequestParam String landAcquisitionId){
		LandAcquisition obj = null;
		try{
			obj = service.getLandAcquisition(landAcquisitionId);			
		}catch(Exception e){
			logger.error("getLandAcquisition() : "+e.getMessage());
		}
		return obj;
	}	
	
	
	/**
	 * This method updateLandAcquisition() will execute when user update Land Acquisition form and it will display the success and error message.
	 * 
	 * @param obj is object for the model class LandAcquisition. 
	 * @param session it will check the session of the user.
	 * @param attributes will show the flash message on the current request.
	 * @return of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/update-land-acquisition",method=RequestMethod.POST)
	public ModelAndView updateLandAcquisition(@ModelAttribute LandAcquisition obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/land-acquisition");
		try {
			boolean flag = service.updateLandAcquisition(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Land Acquisition updated successfully.");
			}else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			logger.info("updateLandAcquisition : " + e.getMessage());
		}
		return model;
	}
	
	
	
	/**************************************************************************************************************/
	
	/**
	 * This method landAcquisition1() will execute when user request the for land-acquisition-1 module.
	 * 
	 * @param session it will check the session of the user.
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 
	 */
	@RequestMapping(value="/land-acquisition-1",method=RequestMethod.GET)
	public ModelAndView landAcquisition1(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.landAcquisition1);
		try {
			List<LandAcquisition> landAcquisitionList = service.getLandAcquisitionList();
			model.addObject("landAcquisitionList", landAcquisitionList);
			
			List<LandAcquisition> statusList = service.getLandAcquisitionSatausList();
			model.addObject("statusList", statusList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("landAcquisition1 : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method getLandAcquisition-1() will execute when user click on the update button.
	 * 
	 * @param landAcquisitionId it takes the land acquisition id as request parameter.
 	 * @param session it will check the session of the user. 
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	@RequestMapping(value="/get-land-acquisition-1",method=RequestMethod.POST)
	public ModelAndView getLandAcquisition1(@RequestParam String landAcquisitionId, HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.updateLandAcquisition1);
		try {
			model.addObject("homeHeader", "yes");
			List<LandAcquisition> statusList = service.getLandAcquisitionSatausList();
			model.addObject("statusList", statusList);
			
			LandAcquisition obj = service.getLandAcquisition(landAcquisitionId);
			model.addObject("obj", obj);
		} catch (Exception e) {
			logger.info("getLandAcquisition1 : " + e.getMessage());
		}
		return model;
	}
	
	
	/**
	 * This method updateLandAcquisition-1() will execute when user update Land Acquisition form and it will display the success and error message.
	 * 
	 * @param obj is object for the model class LandAcquisition. 
	 * @param session it will check the session of the user.
	 * @param attributes will show the flash message on the current request.
	 * @return of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	@RequestMapping(value="/update-land-acquisition-1",method=RequestMethod.POST)
	public ModelAndView updateLandAcquisition1(@ModelAttribute LandAcquisition obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/land-acquisition-1");
		try {
			boolean flag = service.updateLandAcquisition1(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Land Acquisition updated successfully.");
			}else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			logger.info("updateLandAcquisition1 : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method updateLandAcquisitionStatus() will update the status of land acquisition.
	 * 
	 * @param obj is object for the model class LandAcquisition. 
	 * @param session it will check the session of the user.
	 * @param attributes will show the flash message on the current request.
	 * @return of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/update-land-acquisition-status",method=RequestMethod.POST)
	public ModelAndView updateLandAcquisitionStatus(@ModelAttribute LandAcquisition obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/land-acquisition-1");
		try {
			boolean flag = service.updateLandAcquisitionStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", obj.getActiveStatus() + " Stage completed successfully.");
			}else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			logger.info("updateLandAcquisitionStatus : " + e.getMessage());
		}
		return model;
	}
	
	/**************************************************************************************************************/

/**************************************************************************************************************/
	
	/**
	 * This method landAcquisition2() will execute when user request the for land-acquisition-2 module.
	 * 
	 * @param session it will check the session of the user.
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	@RequestMapping(value="/land-acquisition-2",method=RequestMethod.GET)
	public ModelAndView landAcquisition2(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.landAcquisition2);
		try {
			List<LandAcquisition> landAcquisitionList = service.getLandAcquisitionList();
			model.addObject("landAcquisitionList", landAcquisitionList);
			
			List<LandAcquisition> statusList = service.getLandAcquisitionSatausList();
			model.addObject("statusList", statusList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("landAcquisition2 : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method getLandAcquisition2() will execute when user click on the update button.
	 * 
	 * @param landAcquisitionId it takes the land acquisition id as request parameter.
 	 * @param session it will check the session of the user. 
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	@RequestMapping(value="/get-land-acquisition-2",method=RequestMethod.POST)
	public ModelAndView getLandAcquisition2(@RequestParam String landAcquisitionId, HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.updateLandAcquisition2);
		try {
			model.addObject("homeHeader", "yes");
			List<LandAcquisition> statusList = service.getLandAcquisitionSatausList();
			model.addObject("statusList", statusList);
			
			LandAcquisition obj = service.getLandAcquisition(landAcquisitionId);
			model.addObject("obj", obj);
		} catch (Exception e) {
			logger.info("getLandAcquisition2 : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method getLandAcquisition2() will execute when user click on the update button.
	 * 
	 * @param landAcquisitionId it takes the land acquisition id as request parameter.
 	 * @param session it will check the session of the user. 
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	@RequestMapping(value="/update-land-acquisition-2",method=RequestMethod.POST)
	public ModelAndView updateLandAcquisition2(@ModelAttribute LandAcquisition obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/land-acquisition-2");
		try {
			boolean flag = service.updateLandAcquisition1(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Land Acquisition updated successfully.");
			}else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			logger.info("updateLandAcquisition2 : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method updateLandAcquisitionStatus2() will update the status of land acquisition.
	 * 
	 * @param obj is object for the model class LandAcquisition. 
	 * @param session it will check the session of the user.
	 * @param attributes will show the flash message on the current request.
	 * @return of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	@RequestMapping(value="/update-land-acquisition-status-2",method=RequestMethod.POST)
	public ModelAndView updateLandAcquisitionStatus2(@ModelAttribute LandAcquisition obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/land-acquisition-2");
		try {
			boolean flag = service.updateLandAcquisitionStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", obj.getActiveStatus() + " Stage completed successfully.");
			}else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			logger.info("updateLandAcquisitionStatus2 : " + e.getMessage());
		}
		return model;
	}
	
	/**************************************************************************************************************/
	
/**************************************************************************************************************/
	
	/**
	 * This method landAcquisition3() will execute when user request the for land-acquisition-3 module.
	 * 
	 * @param session it will check the session of the user.
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	@RequestMapping(value="/land-acquisition-3",method=RequestMethod.GET)
	public ModelAndView landAcquisition3(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.landAcquisition3);
		try {
			List<LandAcquisition> landAcquisitionList = service.getLandAcquisitionList();
			model.addObject("landAcquisitionList", landAcquisitionList);
			
			List<LandAcquisition> statusList = service.getLandAcquisitionSatausList();
			model.addObject("statusList", statusList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("landAcquisition3 : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method getLandAcquisition3() will execute when user click on the update button.
	 * 
	 * @param landAcquisitionId it takes the land acquisition id as request parameter.
 	 * @param session it will check the session of the user. 
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/get-land-acquisition-3",method=RequestMethod.POST)
	public ModelAndView getLandAcquisition3(@RequestParam String landAcquisitionId, HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.updateLandAcquisition3);
		try {
			model.addObject("homeHeader", "yes");
			List<LandAcquisition> statusList = service.getLandAcquisitionSatausList();
			model.addObject("statusList", statusList);
			
			LandAcquisition obj = service.getLandAcquisition(landAcquisitionId);
			model.addObject("obj", obj);
		} catch (Exception e) {
			logger.info("getLandAcquisition3 : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method updateLandAcquisition3() will execute when user update Land Acquisition form and it will display the success and error message.
	 * 
	 * @param obj is object for the model class LandAcquisition. 
	 * @param session it will check the session of the user.
	 * @param attributes will show the flash message on the current request.
	 * @return of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/update-land-acquisition-3",method=RequestMethod.POST)
	public ModelAndView updateLandAcquisition3(@ModelAttribute LandAcquisition obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/land-acquisition-3");
		try {
			boolean flag = service.updateLandAcquisition1(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Land Acquisition updated successfully.");
			}else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			logger.info("updateLandAcquisition1 : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method updateLandAcquisitionStatus3() will update the status of land acquisition.
	 * 
	 * @param obj is object for the model class LandAcquisition. 
	 * @param session it will check the session of the user.
	 * @param attributes will show the flash message on the current request.
	 * @return of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	@RequestMapping(value="/update-land-acquisition-status-3",method=RequestMethod.POST)
	public ModelAndView updateLandAcquisitionStatus3(@ModelAttribute LandAcquisition obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/land-acquisition-3");
		try {
			boolean flag = service.updateLandAcquisitionStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", obj.getActiveStatus() + " Stage completed successfully.");
			}else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			logger.info("updateLandAcquisitionStatus3 : " + e.getMessage());
		}
		return model;
	}
	
	/**************************************************************************************************************/
	
/**************************************************************************************************************/
	
	/**
	 * This method landAcquisition4() will execute when user request the for land-acquisition-4 module.
	 * 
	 * @param session it will check the session of the user.
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	@RequestMapping(value="/land-acquisition-4",method=RequestMethod.GET)
	public ModelAndView landAcquisition4(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.landAcquisition4);
		try {
			List<LandAcquisition> landAcquisitionList = service.getLandAcquisitionList();
			model.addObject("landAcquisitionList", landAcquisitionList);
			
			List<LandAcquisition> statusList = service.getLandAcquisitionSatausList();
			model.addObject("statusList", statusList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("landAcquisition4 : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method getLandAcquisition4() will execute when user click on the update button.
	 * 
	 * @param landAcquisitionId it takes the land acquisition id as request parameter.
 	 * @param session it will check the session of the user. 
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	@RequestMapping(value="/get-land-acquisition-4",method=RequestMethod.POST)
	public ModelAndView getLandAcquisition4(@RequestParam String landAcquisitionId, HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.updateLandAcquisition4);
		try {
			model.addObject("homeHeader", "yes");
			List<LandAcquisition> statusList = service.getLandAcquisitionSatausList();
			model.addObject("statusList", statusList);
			
			LandAcquisition obj = service.getLandAcquisition(landAcquisitionId);
			model.addObject("obj", obj);
		} catch (Exception e) {
			logger.info("getLandAcquisition4 : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method updateLandAcquisition4() will execute when user update Land Acquisition form and it will display the success and error message.
	 * 
	 * @param obj is object for the model class LandAcquisition. 
	 * @param session it will check the session of the user.
	 * @param attributes will show the flash message on the current request.
	 * @return of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	
	@RequestMapping(value="/update-land-acquisition-4",method=RequestMethod.POST)
	public ModelAndView updateLandAcquisition4(@ModelAttribute LandAcquisition obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/land-acquisition-4");
		try {
			boolean flag = service.updateLandAcquisition1(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Land Acquisition updated successfully.");
			}else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			logger.info("updateLandAcquisition4 : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method updateLandAcquisitionStatus4() will update the status of land acquisition.
	 * 
	 * @param obj is object for the model class LandAcquisition. 
	 * @param session it will check the session of the user.
	 * @param attributes will show the flash message on the current request.
	 * @return of this method is model.
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/update-land-acquisition-status-4",method=RequestMethod.POST)
	public ModelAndView updateLandAcquisitionStatus4(@ModelAttribute LandAcquisition obj, HttpSession session,RedirectAttributes attributes) throws IOException {
		ModelAndView model = new ModelAndView("redirect:/land-acquisition-4");
		try {
			boolean flag = service.updateLandAcquisitionStatus(obj);
			if(flag) {
				attributes.addFlashAttribute("success", obj.getActiveStatus() + " Stage completed successfully.");
			}else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try again.");
			logger.info("updateLandAcquisitionStatus4 : " + e.getMessage());
		}
		return model;
	}
	
	/**************************************************************************************************************/
	/**
	 * This method landAcquisitionAddForm() will execute when user click on the add from button
	 * 
	 * @param session it will check the session of the user.
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/land-acquisition-add-form",method=RequestMethod.GET)
	public ModelAndView landAcquisitionAddForm(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.addEditLandAcquisition);
		try {
			
		} catch (Exception e) {
			logger.info("landAcquisitionAddForm : " + e.getMessage());
		}
		return model;
	}
	
	/**
	 * This method landAcquisitionEditForm() will execute when user click on the edit from button
	 * 
	 * @param session it will check the session of the user.
	 * @return type of this method is model
	 * @throws IOException will raise an exception when abnormal termination occur.
	 */
	@RequestMapping(value="/land-acquisition-edit-form",method=RequestMethod.POST)
	public ModelAndView landAcquisitionEditForm(HttpSession session) throws IOException {
		ModelAndView model = new ModelAndView(PageConstants.addEditLandAcquisition);
		try {
			
		} catch (Exception e) {
			logger.info("landAcquisitionEditForm : " + e.getMessage());
		}
		return model;
	}
}
