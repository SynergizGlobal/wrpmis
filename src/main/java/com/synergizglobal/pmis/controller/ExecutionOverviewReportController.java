package com.synergizglobal.pmis.controller;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.synergizglobal.pmis.constants.PageConstants;

@Controller
public class ExecutionOverviewReportController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ExecutionOverviewReportController.class);
		
	@RequestMapping(value="/execution-overview-report",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView ExecutionOverviewReport(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.ExecutionOverviewReport);
		return model;
	}
	
}
