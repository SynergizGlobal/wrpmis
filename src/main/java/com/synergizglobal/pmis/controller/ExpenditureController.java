package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.TextFormat.ParseException;
import com.synergizglobal.pmis.Idao.ExpenditureDao;
import com.synergizglobal.pmis.Idao.FormsHistoryDao;
import com.synergizglobal.pmis.Iservice.ContractorService;
import com.synergizglobal.pmis.Iservice.ExpenditureService;
import com.synergizglobal.pmis.Iservice.HomeService;
import com.synergizglobal.pmis.common.DateParser;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Contract;
import com.synergizglobal.pmis.model.Contractor;
import com.synergizglobal.pmis.model.Expenditure;
import com.synergizglobal.pmis.model.ExpenditurePaginationObject;
import com.synergizglobal.pmis.model.FileFormatModel;
import com.synergizglobal.pmis.model.FormHistory;
import com.synergizglobal.pmis.model.LandAcquisition;
import com.synergizglobal.pmis.model.Project;
import com.synergizglobal.pmis.model.User;

@Controller
public class ExpenditureController {
	

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(ExpenditureController.class);
	@Autowired
	FormsHistoryDao formsHistoryDao;
	@Autowired
	ExpenditureService expenditureService;
	
	@Autowired
	ContractorService contractorService;	
	
	@Autowired
	HomeService homeService;
	
	@Autowired
	ExpenditureDao  expenditureDao;

	@Value("${common.error.message}")
	public String commonError;
	
	@Value("${record.dataexport.success}")
	public String dataExportSucess;
	
	@Value("${record.dataexport.invalid.directory}")
	public String dataExportInvalid;
	
	@Value("${record.dataexport.error}")
	public String dataExportError;
	
	@Value("${record.dataexport.nodata}")
	public String dataExportNoData;
	
	@Value("${template.upload.common.error}")
	public String uploadCommonError;
	
	@Value("${template.upload.formatError}")
	public String uploadformatError;
	@RequestMapping(value="/expenditure",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView Expenditure(HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.expenditure);
		try {
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Expenditure : " + e.getMessage());
		}
		return model;
	}
	
	
	@RequestMapping(value = "/expenditure-report", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView LandReport(@ModelAttribute Expenditure obj, RedirectAttributes attributes, HttpSession session) {
		ModelAndView model = new ModelAndView(PageConstants.expenditureReport);
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_id(uObj.getUser_id());
			
			
			List<Expenditure> projectsList = expenditureService.getProjectsListForExpenditureForm(obj);
			model.addObject("projectsList", projectsList);

			List<Expenditure> worksList = expenditureService.getWorksFilterList(obj);
			model.addObject("worksList", worksList);

			List<Expenditure> voucherTypesFilterList = expenditureService.getVoucherTypesFilterList(obj);
			model.addObject("voucherTypesFilterList", voucherTypesFilterList);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ExpenditureReport : " + e.getMessage());
		}
		return model;
	}	
	
	
	@RequestMapping(value = "/generate-expenditure-report", method = {RequestMethod.GET,RequestMethod.POST})
	public void generateExpenditureReport(@ModelAttribute Expenditure obj,HttpServletRequest request, HttpServletResponse response,HttpSession session,RedirectAttributes attributes){
		try{
			User uObj = (User) session.getAttribute("user");
			obj.setUser_id(uObj.getUser_id());
			DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm"); 
			String report_created_date = df.format(new Date());
			
			SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-YY");
		
			Expenditure reportData =null;
			
			ResultSet rs=expenditureService.generateExpenditureReportByProject(obj); 
            List<Expenditure> rsChild=expenditureService.generateExpenditureReportByWork(obj);
            List<Expenditure> rsChildContract=expenditureService.generateExpenditureReportByContract(obj);
			
			
            XSSFWorkbook  workBook = new XSSFWorkbook ();
            XSSFSheet executionOverviewReportSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("ExpenditureReport"));
            
	        workBook.setSheetOrder(executionOverviewReportSheet.getSheetName(), 0);
	        
	        byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
	        byte[] yellowRGB = new byte[]{(byte)255, (byte)255, (byte)0};
	        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
	        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
	        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
	        byte[] grayRGB = new byte[]{(byte)217, (byte)217, (byte)217};
	        
	        
	        boolean isWrapText = true;boolean isBoldText = false;boolean isItalicText = false; int fontSize = 11;String fontName = "Times New Roman";
	        CellStyle blueStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,true,isItalicText,fontSize,fontName);
	        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,true,isItalicText,12,fontName);
	        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle grayStyle = cellFormating(workBook,grayRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        isBoldText = true;fontSize = 12;
	        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Times New Roman";
	        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle sectionPercentStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle sectioncostStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle sectionunitsStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        CellStyle centerStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle rightStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        
            

            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            XSSFRow row1 = executionOverviewReportSheet.createRow(0);
	        String headerString = "S. No.^Contract Short name^Contractor Name^Latest Contract Amount^Gross Work Done^Net Amount Paid^SD Payable^CGST Tds^SGST Tds^IGST Tds^CGST Output^SGST Output^TDS^Mob. Adv^Mob. Adv. Recovered^Mob. Adv. Pending^Withheld Amount";
            
            String[] firstHeaderStringArr = headerString.split("\\^");
            
            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
	        	Cell cell = row1.createCell(i);
		        cell.setCellStyle(greenStyle);
				cell.setCellValue(firstHeaderStringArr[i]);
			}

            
            int RowNum=1;
            while(rs.next()) {
            	
                if(rsChild.size()>0 && rsChildContract.size()>0)
                {
                XSSFRow row = executionOverviewReportSheet.createRow(RowNum);
                
                
                for(int col=0 ;col < columnsNumber;col++) {
                    Cell newpath = row.createCell(col);
                    newpath.setCellStyle(blueStyle);
                    if(col>1)
                    {
                    	if(!StringUtils.isEmpty(rs.getString(col+1)) && rs.getString(col+1)!="")
                    	{
	                    	if(Float.parseFloat(rs.getString(col+1))>0)
	                    	{
	                    		newpath.setCellValue(rs.getString(col+1)); 
	                    	}
	                    	else
	                    	{
	                    		newpath.setCellValue(""); 
	                    	}
                    	}
                    }
                    else
                    {
                    	newpath.setCellValue(rs.getString(col+1)); 
                    }
                }
                int loop=0;
                for (Expenditure cObj : rsChild) 
                {
                	String ProjectName=rs.getString(2);
                	String WorkProjectName=cObj.getProject_name();

                	if(ProjectName.compareTo(WorkProjectName)==0)
                	{
                		RowNum++;
                		loop++;

                		XSSFRow row11 = executionOverviewReportSheet.createRow(RowNum);
                		
    	                    Cell cell = row11.createCell(0);
    						cell.setCellStyle(sectionStyle);
    						cell.setCellValue(rs.getString(1)+"."+loop);	                		
    	                    
    	                    cell = row11.createCell(1);
    						cell.setCellStyle(sectionStyle);
    						cell.setCellValue(cObj.getWork_short_name());
    						
    	                    cell = row11.createCell(2);
    						cell.setCellStyle(sectionStyle);
    						cell.setCellValue(cObj.getContractor_name());    						
    						
    						cell = row11.createCell(3);
    						cell.setCellStyle(sectionStyle);

	                    	if(!StringUtils.isEmpty(cObj.getAwarded_cost()) && cObj.getAwarded_cost()!="")
	                    	{
		                    	if(Float.parseFloat(cObj.getAwarded_cost())>0)
		                    	{
		    						cell.setCellValue(cObj.getAwarded_cost());
		                    	}
		                    	else
		                    	{
		    						cell.setCellValue("");
		                    	}
	                    	}
    						
    						
    				        cell = row11.createCell(4);
    						cell.setCellStyle(sectionStyle);
    						
	                    	if(!StringUtils.isEmpty(cObj.getGross_work_done()) && cObj.getGross_work_done()!="")
	                    	{
		                    	if(Float.parseFloat(cObj.getGross_work_done())>0)
		                    	{
		                    		cell.setCellValue(cObj.getGross_work_done());
		                    	}
		                    	else
		                    	{
		    						cell.setCellValue("");
		                    	}
	                    	}	    						
    						
    						
    						cell = row11.createCell(5);
    						cell.setCellStyle(sectionStyle);
    						
	                    	if(!StringUtils.isEmpty(cObj.getNet_paid()) && cObj.getNet_paid()!="")
	                    	{
		                    	if(Float.parseFloat(cObj.getNet_paid())>0)
		                    	{
		                    		cell.setCellValue(cObj.getNet_paid());
		                    	}
		                    	else
		                    	{
		    						cell.setCellValue("");
		                    	}
	                    	}	    						
    						
    						
    						cell = row11.createCell(6);
    						cell.setCellStyle(sectionStyle);
    						
	                    	if(!StringUtils.isEmpty(cObj.getSd_payable()) && cObj.getSd_payable()!="")
	                    	{
		                    	if(Float.parseFloat(cObj.getSd_payable())>0)
		                    	{
		                    		cell.setCellValue(cObj.getSd_payable());
		                    	}
		                    	else
		                    	{
		    						cell.setCellValue("");
		                    	}
	                    	}	    						
    						
    						
    						cell = row11.createCell(7);
    						cell.setCellStyle(sectionStyle);
    						
	                    	if(!StringUtils.isEmpty(cObj.getCgst_tds()) && cObj.getCgst_tds()!="")
	                    	{
		                    	if(Float.parseFloat(cObj.getCgst_tds())>0)
		                    	{
		                    		cell.setCellValue(cObj.getCgst_tds());
		                    	}
		                    	else
		                    	{
		    						cell.setCellValue("");
		                    	}
	                    	}	    						
    						
    						
    						cell = row11.createCell(8);
    						cell.setCellStyle(sectionStyle);
    						
	                    	if(!StringUtils.isEmpty(cObj.getSgst_tds()) && cObj.getSgst_tds()!="")
	                    	{
		                    	if(Float.parseFloat(cObj.getSgst_tds())>0)
		                    	{
		                    		cell.setCellValue(cObj.getSgst_tds());
		                    	}
		                    	else
		                    	{
		    						cell.setCellValue("");
		                    	}
	                    	}	    						
    						
    						
    						cell = row11.createCell(9);
    						cell.setCellStyle(sectionStyle);
    						
	                    	if(!StringUtils.isEmpty(cObj.getIgst_tds()) && cObj.getIgst_tds()!="")
	                    	{
		                    	if(Float.parseFloat(cObj.getIgst_tds())>0)
		                    	{
		                    		cell.setCellValue(cObj.getIgst_tds());
		                    	}
		                    	else
		                    	{
		    						cell.setCellValue("");
		                    	}
	                    	}
	                    	
	                    	/****************************************************/
	                    	
	                    	
	
    						cell = row11.createCell(10);
    						cell.setCellStyle(sectionStyle);
    						
	                    	if(!StringUtils.isEmpty(cObj.getCgst_output()) && cObj.getCgst_output()!="")
	                    	{
		                    	if(Float.parseFloat(cObj.getCgst_output())>0)
		                    	{
		                    		cell.setCellValue(cObj.getCgst_output());
		                    	}
		                    	else
		                    	{
		    						cell.setCellValue("");
		                    	}
	                    	}
	                    	
	                    	
    						cell = row11.createCell(11);
    						cell.setCellStyle(sectionStyle);
    						
	                    	if(!StringUtils.isEmpty(cObj.getSgst_output()) && cObj.getSgst_output()!="")
	                    	{
		                    	if(Float.parseFloat(cObj.getSgst_output())>0)
		                    	{
		                    		cell.setCellValue(cObj.getSgst_output());	
		                    	}
		                    	else
		                    	{
		    						cell.setCellValue("");
		                    	}
	                    	}
	                    	
	                    	
    						cell = row11.createCell(12);
    						cell.setCellStyle(sectionStyle);
    						
	                    	if(!StringUtils.isEmpty(cObj.getTds()) && cObj.getTds()!="")
	                    	{
		                    	if(Float.parseFloat(cObj.getTds())>0)
		                    	{
		                    		cell.setCellValue(cObj.getTds());
		                    	}
		                    	else
		                    	{
		    						cell.setCellValue("");
		                    	}
	                    	}
	                    	
	                    	
    						cell = row11.createCell(13);
    						cell.setCellStyle(sectionStyle);
    						
	                    	if(!StringUtils.isEmpty(cObj.getMob_advance()) && cObj.getMob_advance()!="")
	                    	{
		                    	if(Float.parseFloat(cObj.getMob_advance())>0)
		                    	{
		                    		cell.setCellValue(cObj.getMob_advance());
		                    	}
		                    	else
		                    	{
		    						cell.setCellValue("");
		                    	}
	                    	}
	                    	
	                    	
    						cell = row11.createCell(14);
    						cell.setCellStyle(sectionStyle);
    						
	                    	if(!StringUtils.isEmpty(cObj.getMob_adv_recovered()) && cObj.getMob_adv_recovered()!="")
	                    	{
		                    	if(Float.parseFloat(cObj.getMob_adv_recovered())>0)
		                    	{
		                    		cell.setCellValue(cObj.getMob_adv_recovered());
		                    	}
		                    	else
		                    	{
		    						cell.setCellValue("");
		                    	}
	                    	}
	                    	
	                    	
    						cell = row11.createCell(15);
    						cell.setCellStyle(sectionStyle);
    						
	                    	if(!StringUtils.isEmpty(cObj.getMob_adv_pending()) && cObj.getMob_adv_pending()!="")
	                    	{
		                    	if(Float.parseFloat(cObj.getMob_adv_pending())>0)
		                    	{
		                    		cell.setCellValue(cObj.getMob_adv_pending());
		                    	}
		                    	else
		                    	{
		    						cell.setCellValue("");
		                    	}
	                    	}
	                    	
	                    	
    						cell = row11.createCell(16);
    						cell.setCellStyle(sectionStyle);
    						
	                    	if(!StringUtils.isEmpty(cObj.getAmount_withheld()) && cObj.getAmount_withheld()!="")
	                    	{
		                    	if(Float.parseFloat(cObj.getAmount_withheld())>0)
		                    	{
		                    		cell.setCellValue(cObj.getAmount_withheld());
		                    	}
		                    	else
		                    	{
		    						cell.setCellValue("");
		                    	}
	                    	}	                    	

                	}
                	
                    int loop1=0;
                    for (Expenditure cObj1 : rsChildContract) 
                    {
                    	
                    	String ProjectName1=rs.getString(2);
                    	String WorkProjectName1=cObj1.getProject_name();
                    	
                    	String WorkProjectNameParent=cObj.getWork_short_name();
                    	String WorkProjectNameChild=cObj1.getWork_short_name();              	


                    	if(ProjectName1.compareTo(WorkProjectName1)==0 && WorkProjectNameParent.compareTo(WorkProjectNameChild)==0)
                    	{
                    		RowNum++;
                    		loop1++;

                    		XSSFRow row112 = executionOverviewReportSheet.createRow(RowNum);
                    		
        	                    Cell cell = row112.createCell(0);
        						cell.setCellStyle(sectionStyle);
        						cell.setCellValue(loop1);	                		
        	                    
        	                    cell = row112.createCell(1);
        						cell.setCellStyle(sectionStyle);
        						cell.setCellValue(cObj1.getContract_name());
        						
        	                    cell = row112.createCell(2);
        						cell.setCellStyle(sectionStyle);
        						cell.setCellValue(cObj1.getContractor_name());    						
        						
        						cell = row112.createCell(3);
        						cell.setCellStyle(sectionStyle);

    	                    	if(!StringUtils.isEmpty(cObj1.getAwarded_cost()) && cObj1.getAwarded_cost()!="")
    	                    	{
    		                    	if(Float.parseFloat(cObj1.getAwarded_cost())>0)
    		                    	{
    		    						cell.setCellValue(cObj1.getAwarded_cost());
    		                    	}
    		                    	else
    		                    	{
    		    						cell.setCellValue("");
    		                    	}
    	                    	}
        						
        						
        				        cell = row112.createCell(4);
        						cell.setCellStyle(sectionStyle);
        						
    	                    	if(!StringUtils.isEmpty(cObj1.getGross_work_done()) && cObj1.getGross_work_done()!="")
    	                    	{
    		                    	if(Float.parseFloat(cObj1.getGross_work_done())>0)
    		                    	{
    		                    		cell.setCellValue(cObj1.getGross_work_done());
    		                    	}
    		                    	else
    		                    	{
    		    						cell.setCellValue("");
    		                    	}
    	                    	}	    						
        						
        						
        						cell = row112.createCell(5);
        						cell.setCellStyle(sectionStyle);
        						
    	                    	if(!StringUtils.isEmpty(cObj1.getNet_paid()) && cObj1.getNet_paid()!="")
    	                    	{
    		                    	if(Float.parseFloat(cObj1.getNet_paid())>0)
    		                    	{
    		                    		cell.setCellValue(cObj1.getNet_paid());
    		                    	}
    		                    	else
    		                    	{
    		    						cell.setCellValue("");
    		                    	}
    	                    	}	    						
        						
        						
        						cell = row112.createCell(6);
        						cell.setCellStyle(sectionStyle);
        						
    	                    	if(!StringUtils.isEmpty(cObj1.getSd_payable()) && cObj1.getSd_payable()!="")
    	                    	{
    		                    	if(Float.parseFloat(cObj1.getSd_payable())>0)
    		                    	{
    		                    		cell.setCellValue(cObj1.getSd_payable());
    		                    	}
    		                    	else
    		                    	{
    		    						cell.setCellValue("");
    		                    	}
    	                    	}	    						
        						
        						
        						cell = row112.createCell(7);
        						cell.setCellStyle(sectionStyle);
        						
    	                    	if(!StringUtils.isEmpty(cObj1.getCgst_tds()) && cObj1.getCgst_tds()!="")
    	                    	{
    		                    	if(Float.parseFloat(cObj1.getCgst_tds())>0)
    		                    	{
    		                    		cell.setCellValue(cObj1.getCgst_tds());
    		                    	}
    		                    	else
    		                    	{
    		    						cell.setCellValue("");
    		                    	}
    	                    	}	    						
        						
        						
        						cell = row112.createCell(8);
        						cell.setCellStyle(sectionStyle);
        						
    	                    	if(!StringUtils.isEmpty(cObj1.getSgst_tds()) && cObj1.getSgst_tds()!="")
    	                    	{
    		                    	if(Float.parseFloat(cObj1.getSgst_tds())>0)
    		                    	{
    		                    		cell.setCellValue(cObj1.getSgst_tds());
    		                    	}
    		                    	else
    		                    	{
    		    						cell.setCellValue("");
    		                    	}
    	                    	}	    						
        						
        						
        						cell = row112.createCell(9);
        						cell.setCellStyle(sectionStyle);
        						
    	                    	if(!StringUtils.isEmpty(cObj1.getIgst_tds()) && cObj1.getIgst_tds()!="")
    	                    	{
    		                    	if(Float.parseFloat(cObj1.getIgst_tds())>0)
    		                    	{
    		                    		cell.setCellValue(cObj1.getIgst_tds());
    		                    	}
    		                    	else
    		                    	{
    		    						cell.setCellValue("");
    		                    	}
    	                    	}
    	                    	
    	                    	/****************************************************/
    	                    	
    	                    	
    	
        						cell = row112.createCell(10);
        						cell.setCellStyle(sectionStyle);
        						
    	                    	if(!StringUtils.isEmpty(cObj1.getCgst_output()) && cObj1.getCgst_output()!="")
    	                    	{
    		                    	if(Float.parseFloat(cObj1.getCgst_output())>0)
    		                    	{
    		                    		cell.setCellValue(cObj1.getCgst_output());
    		                    	}
    		                    	else
    		                    	{
    		    						cell.setCellValue("");
    		                    	}
    	                    	}
    	                    	
    	                    	
        						cell = row112.createCell(11);
        						cell.setCellStyle(sectionStyle);
        						
    	                    	if(!StringUtils.isEmpty(cObj1.getSgst_output()) && cObj1.getSgst_output()!="")
    	                    	{
    		                    	if(Float.parseFloat(cObj1.getSgst_output())>0)
    		                    	{
    		                    		cell.setCellValue(cObj1.getSgst_output());	
    		                    	}
    		                    	else
    		                    	{
    		    						cell.setCellValue("");
    		                    	}
    	                    	}
    	                    	
    	                    	
        						cell = row112.createCell(12);
        						cell.setCellStyle(sectionStyle);
        						
    	                    	if(!StringUtils.isEmpty(cObj1.getTds()) && cObj1.getTds()!="")
    	                    	{
    		                    	if(Float.parseFloat(cObj1.getTds())>0)
    		                    	{
    		                    		cell.setCellValue(cObj1.getTds());
    		                    	}
    		                    	else
    		                    	{
    		    						cell.setCellValue("");
    		                    	}
    	                    	}
    	                    	
    	                    	
        						cell = row112.createCell(13);
        						cell.setCellStyle(sectionStyle);
        						
    	                    	if(!StringUtils.isEmpty(cObj1.getMob_advance()) && cObj1.getMob_advance()!="")
    	                    	{
    		                    	if(Float.parseFloat(cObj1.getMob_advance())>0)
    		                    	{
    		                    		cell.setCellValue(cObj1.getMob_advance());
    		                    	}
    		                    	else
    		                    	{
    		    						cell.setCellValue("");
    		                    	}
    	                    	}
    	                    	
    	                    	
        						cell = row112.createCell(14);
        						cell.setCellStyle(sectionStyle);
        						
    	                    	if(!StringUtils.isEmpty(cObj1.getMob_adv_recovered()) && cObj1.getMob_adv_recovered()!="")
    	                    	{
    		                    	if(Float.parseFloat(cObj1.getMob_adv_recovered())>0)
    		                    	{
    		                    		cell.setCellValue(cObj1.getMob_adv_recovered());
    		                    	}
    		                    	else
    		                    	{
    		    						cell.setCellValue("");
    		                    	}
    	                    	}
    	                    	
    	                    	
        						cell = row112.createCell(15);
        						cell.setCellStyle(sectionStyle);
        						
    	                    	if(!StringUtils.isEmpty(cObj1.getMob_adv_pending()) && cObj1.getMob_adv_pending()!="")
    	                    	{
    		                    	if(Float.parseFloat(cObj1.getMob_adv_pending())>0)
    		                    	{
    		                    		cell.setCellValue(cObj1.getMob_adv_pending());
    		                    	}
    		                    	else
    		                    	{
    		    						cell.setCellValue("");
    		                    	}
    	                    	}
    	                    	
    	                    	
        						cell = row112.createCell(16);
        						cell.setCellStyle(sectionStyle);
        						
    	                    	if(!StringUtils.isEmpty(cObj1.getAmount_withheld()) && cObj1.getAmount_withheld()!="")
    	                    	{
    		                    	if(Float.parseFloat(cObj1.getAmount_withheld())>0)
    		                    	{
    		                    		cell.setCellValue(cObj1.getAmount_withheld());
    		                    	}
    		                    	else
    		                    	{
    		    						cell.setCellValue("");
    		                    	}
    	                    	}	
                    }       
                    }
                	
                	
                }
                RowNum++;   
            
            }
            
            }
            
            for(int columnIndex = 0; columnIndex < columnsNumber; columnIndex++) {
            	executionOverviewReportSheet.setColumnWidth(columnIndex, 25 * 300);
			}	
            executionOverviewReportSheet.setColumnWidth(0, 5 * 300);
            executionOverviewReportSheet.setColumnWidth(1, 35 * 300);
            /*******************************************************************************/
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
            Date date = new Date();
            String fileName = "Expenditure_Report_"+dateFormat.format(date);
            
            try{
                /*FileOutputStream fos = new FileOutputStream(fileDirectory +fileName+".xls");
                workBook.write(fos);
                fos.flush();*/
            	
               response.setContentType("application/.csv");
 			   response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
 			   response.setContentType("application/vnd.ms-excel");
 			   // add response header
 			   response.addHeader("Content-Disposition", "attachment; filename=" + fileName+".xlsx");
 			   
 			    //copies all bytes from a file to an output stream
 			   workBook.write(response.getOutputStream()); // Write workbook to response.
	           workBook.close();
 			    //flushes output stream
 			    response.getOutputStream().flush();
            	
                
                //attributes.addFlashAttribute("success",dataExportSucess);
            	//response.setContentType("application/vnd.ms-excel");
            	//response.setHeader("Content-Disposition", "attachment; filename=filename.xls");
            	//XSSFWorkbook  workbook = new XSSFWorkbook ();
            	// ...
            	// Now populate workbook the usual way.
            	// ...
            	//workbook.write(response.getOutputStream()); // Write workbook to response.
            	//workbook.close();
            }catch(FileNotFoundException e){
                e.printStackTrace();
                logger.error("generateLandAcquisition : " + e.getMessage());
                //attributes.addFlashAttribute("error",dataExportInvalid);
            }catch(IOException e){
                e.printStackTrace();
                logger.error("generateLandAcquisition : " + e.getMessage());
                //attributes.addFlashAttribute("error",dataExportError);
            }
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("generateLandAcquisition : " + e.getMessage());
		}
		//return model;
    }	
	
	
	
	@RequestMapping(value = "/ajax/get-expenditure", method = { RequestMethod.POST, RequestMethod.GET })
	public void getActivitiesList(@ModelAttribute Expenditure obj, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {
		PrintWriter pw = null;
		//JSONObject json = new JSONObject();
		String json2 = null;
		String userId = null;
		String userName = null;
		try {
			userId = (String) session.getAttribute("USER_ID");
			userName = (String) session.getAttribute("USER_NAME");

			pw = response.getWriter();
			//Fetch the page number from client
			Integer pageNumber = 0;
			Integer pageDisplayLength = 0;
			if (null != request.getParameter("iDisplayStart")) {
				pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
				pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / pageDisplayLength) + 1;
			}
			//Fetch search parameter
			String searchParameter = request.getParameter("sSearch");

			//Fetch Page display length
			pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

			List<Expenditure> expenditureList = new ArrayList<Expenditure>();

			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			int startIndex = 0;
			int offset = pageDisplayLength;

			if (pageNumber == 1) {
				startIndex = 0;
				offset = pageDisplayLength;
				expenditureList = createPaginationData(startIndex, offset, obj, searchParameter);
			} else {
				startIndex = (pageNumber * offset) - offset;
				offset = pageDisplayLength;
				expenditureList = createPaginationData(startIndex, offset, obj, searchParameter);
			}

			//Search functionality: Returns filtered list based on search parameter
			//expenditureList = getListBasedOnSearchParameter(searchParameter,expenditureList);

			int totalRecords = getTotalRecords(obj, searchParameter);

			ExpenditurePaginationObject personJsonObject = new ExpenditurePaginationObject();
			//Set Total display record
			personJsonObject.setiTotalDisplayRecords(totalRecords);
			//Set Total record
			personJsonObject.setiTotalRecords(totalRecords);
			personJsonObject.setAaData(expenditureList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json2 = gson.toJson(personJsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"getActivitiesList : User Id - " + userId + " - User Name - " + userName + " - " + e.getMessage());
		}

		pw.println(json2);
	}

	/**
	 * @param searchParameter 
	 * @param activity 
	 * @return
	 */
	public int getTotalRecords(Expenditure obj, String searchParameter) {
		int totalRecords = 0;
		try {
			totalRecords = expenditureService.getTotalRecords(obj, searchParameter);
		} catch (Exception e) {
			logger.error("getTotalRecords : " + e.getMessage());
		}
		return totalRecords;
	}

	/**
	 * @param pageDisplayLength
	 * @param offset 
	 * @param activity 
	 * @param clientId 
	 * @return
	 */
	public List<Expenditure> createPaginationData(int startIndex, int offset,Expenditure obj, String searchParameter) {
		List<Expenditure> earthWorkList = null;
		try {
			earthWorkList = expenditureService.getExpendituresList(obj, startIndex, offset, searchParameter);
		} catch (Exception e) {
			logger.error("createPaginationData : " + e.getMessage());
		}
		return earthWorkList;
	}
	
	@RequestMapping(value = "/ajax/getWorksFilterListInExpenditure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Expenditure> getWorksFilterList(@ModelAttribute Expenditure obj) {
		List<Expenditure> worksFilterList = null;
		try {
			worksFilterList = expenditureService.getWorksFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorksFilterList : " + e.getMessage());
		}
		return worksFilterList;
	}
	
	@RequestMapping(value = "/ajax/getContractsFilterListInExpenditure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Expenditure> getContractsFilterList(@ModelAttribute Expenditure obj) {
		List<Expenditure> contractsFilterList = null;
		try {
			contractsFilterList = expenditureService.getContractsFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsFilterList : " + e.getMessage());
		}
		return contractsFilterList;
	}
	
	@RequestMapping(value = "/ajax/getLedgerAccountsFilterListInExpenditure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Expenditure> getledgerAccountsFilterList(@ModelAttribute Expenditure obj) {
		List<Expenditure> ledgerAccountsFilterList = null;
		try {
			ledgerAccountsFilterList = expenditureService.getledgerAccountsList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getledgerAccountsFilterList : " + e.getMessage());
		}
		return ledgerAccountsFilterList;
	}
	
	@RequestMapping(value = "/ajax/getContractorNamesFilterListInExpenditure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Expenditure> getContractorNamesFilterList(@ModelAttribute Expenditure obj) {
		List<Expenditure> contractorNamesFilterList = null;
		try {
			contractorNamesFilterList = expenditureService.getContractorNamesFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractorNamesFilterList : " + e.getMessage());
		}
		return contractorNamesFilterList;
	}

	@RequestMapping(value = "/ajax/getVoucherTypesFilterListInExpenditure", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Expenditure> getVoucherTypesFilterList(@ModelAttribute Expenditure obj) {
		List<Expenditure> voucherTypesFilterList = null;
		try {
			voucherTypesFilterList = expenditureService.getVoucherTypesFilterList(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getVoucherTypesFilterList : " + e.getMessage());
		}
		return voucherTypesFilterList;
	}

	@RequestMapping(value = "/add-expenditure-form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addExpenditureForm(@ModelAttribute Expenditure obj){
		ModelAndView model = new ModelAndView();
		Contractor contractorobj=new Contractor();
		try{
			model.setViewName(PageConstants.addEditExpenditure);
			model.addObject("action", "add");
			
			List<Expenditure> projectsList = expenditureService.getProjectsListForExpenditureForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Expenditure> worksList = expenditureService.getWorkListForExpenditureForm(obj);
			model.addObject("worksList", worksList);
			
			List<Expenditure> contractsList = expenditureService.getContractsListForExpenditureForm(obj);
			model.addObject("contractsList", contractsList);
			
			List<Expenditure> voucherList = expenditureService.getVoucherList();
			model.addObject("voucherList", voucherList);
			
			List<Expenditure> unitsList = expenditureService.getUnitsList();
			model.addObject("unitsList", unitsList);

			List<Contractor> contractorsList = contractorService.getContractorsList(contractorobj);
			model.addObject("contractorsList", contractorsList);
			
			
		}catch (Exception e) {
				logger.error("Expenditure : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getProjectsListForExpenditureForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Expenditure> getProjectsListForExpenditureForm(@ModelAttribute Expenditure obj) {
		List<Expenditure> objsList = null;
		try {
			objsList = expenditureService.getProjectsListForExpenditureForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getProjectsListForExpenditureForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getWorkListForExpenditureForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Expenditure> getWorkListForExpenditureForm(@ModelAttribute Expenditure obj) {
		List<Expenditure> objsList = null;
		try {
			objsList = expenditureService.getWorkListForExpenditureForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getWorkListForExpenditureForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/ajax/getContractsListForExpenditureForm", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Expenditure> getContractsListForExpenditureForm(@ModelAttribute Expenditure obj) {
		List<Expenditure> objsList = null;
		try {
			objsList = expenditureService.getContractsListForExpenditureForm(obj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getContractsListForExpenditureForm : " + e.getMessage());
		}
		return objsList;
	}
	
	@RequestMapping(value = "/get-expenditure",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getExpenditure(@ModelAttribute Expenditure obj){
		ModelAndView model = new ModelAndView();
		Contractor contractorobj=new Contractor();
		try{
			model.setViewName(PageConstants.addEditExpenditure);
			model.addObject("action", "edit");
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			List<Expenditure> voucherList = expenditureService.getVoucherList();
			model.addObject("voucherList", voucherList);
			List<Expenditure> unitsList = expenditureService.getUnitsList();
			model.addObject("unitsList", unitsList);

			List<Contractor> contractorsList = contractorService.getContractorsList(contractorobj);
			model.addObject("contractorsList", contractorsList);
			
			Expenditure expenditureDetails = expenditureService.getExpenditure(obj);
			model.addObject("expenditureDetails", expenditureDetails);
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getExpenditure : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/get-expenditure/{expenditure_id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getExpenditureWithId(@ModelAttribute Expenditure obj,@PathVariable("expenditure_id") String expenditure_id ){
		ModelAndView model = new ModelAndView();
		Contractor contractorobj=new Contractor();
		try{
			model.setViewName(PageConstants.addEditExpenditure);
			model.addObject("action", "edit");
			List<Project> projectsList = homeService.getProjectsList();
			model.addObject("projectsList", projectsList);
			List<Expenditure> voucherList = expenditureService.getVoucherList();
			model.addObject("voucherList", voucherList);
			List<Expenditure> unitsList = expenditureService.getUnitsList();
			model.addObject("unitsList", unitsList);
			List<Contractor> contractorsList = contractorService.getContractorsList(contractorobj);
			model.addObject("contractorsList", contractorsList);			
			obj.setExpenditure_id(expenditure_id);
			Expenditure expenditureDetails = expenditureService.getExpenditure(obj);
			model.addObject("expenditureDetails", expenditureDetails);
		}catch (Exception e) {
				e.printStackTrace();
				logger.error("getExpenditureWithId : " + e.getMessage());
		}
		return model;
	 }
	
	@RequestMapping(value = "/add-expenditure", method = {RequestMethod.POST})
	@ResponseBody
	public ModelAndView addExpenditure(@ModelAttribute Expenditure obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/expenditure");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			obj.setDate(DateParser.parse(obj.getDate()));
			boolean flag =  expenditureService.addExpenditure(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Expenditure Added Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Adding Expenditure is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Adding Expenditure is failed. Try again.");
			logger.error("addExpenditure : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/update-expenditure", method = {RequestMethod.POST})
	public ModelAndView updateExpenditure(@ModelAttribute Expenditure obj,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/expenditure");
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			obj.setCreated_by_user_id_fk(user_Id);
			obj.setUser_id(user_Id);
			obj.setUser_name(userName);
			obj.setDesignation(userDesignation);
			obj.setDate(DateParser.parse(obj.getDate()));
			boolean flag =  expenditureService.updateExpenditure(obj);
			if(flag) {
				attributes.addFlashAttribute("success", "Expenditure Updated Succesfully.");
			}
			else {
				attributes.addFlashAttribute("error","Updating Expenditure is failed. Try again.");
			}
		}catch (Exception e) {
			attributes.addFlashAttribute("error","Updating Expenditure is failed. Try again.");
			logger.error("updateExpenditure : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/delete-expenditure", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteExpenditure(@ModelAttribute Expenditure obj){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName("redirect:/expenditure");
			boolean flag =  expenditureService.deleteExpenditure(obj);
		}catch (Exception e) {
			logger.error("deleteExpenditure : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/export-expenditure", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportExpenditure(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute Expenditure obj,RedirectAttributes attributes){
		ModelAndView view = new ModelAndView(PageConstants.expenditure);
		List<Expenditure> dataList = new ArrayList<Expenditure>();
		try {
			view.setViewName("redirect:/expenditure");
			dataList =  expenditureService.getExpendituresListForExport(obj);
			if(dataList != null && dataList.size() > 0){
		        XSSFWorkbook  workBook = new XSSFWorkbook ();
	            XSSFSheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("Expenditure"));
		        workBook.setSheetOrder(sheet.getSheetName(), 0);
		        
		        byte[] blueRGB = new byte[]{(byte)0, (byte)176, (byte)240};
		        byte[] yellowRGB = new byte[]{(byte)255, (byte)192, (byte)0};
		        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
		        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
		        byte[] whiteRGB = new byte[]{(byte)255, (byte)255, (byte)255};
		        
		        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 11;String fontName = "Times New Roman";
		        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle whiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Times New Roman";
		        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        CellStyle sectionValueStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
		        
		        XSSFRow headingRow = sheet.createRow(0);
		        String headerString = "Work Name^Contract Id^Name of Account^Name of Contractor^Payment Date^Financial Year^Unique No.^Narration^Net Paid^Gross Paid^SD Payable^Income Tax^CGST TDS^SGST TDS^IGST TDS^Mobilization Advance^Interest on Mobilization Advance^Cess on Building^Establishment Charges on Cess^CGST (Output)^SGST (Output)^VAT WCT^Amount Withheld^Remarks";
	            
	            String[] firstHeaderStringArr = headerString.split("\\^");
	            
	            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
		        	Cell cell = headingRow.createCell(i);
			        cell.setCellStyle(whiteStyle);
					cell.setCellValue(firstHeaderStringArr[i]);
				}
	            short rowNo = 1;
	          
	            for (Expenditure eObj : dataList) {
	            	 XSSFRow row = sheet.createRow(rowNo);
		                int c = 0;
					
		                Cell cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(eObj.getWork_id_fk());
						
		                cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(eObj.getContract_id_fk());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(eObj.getLedger_account());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(eObj.getContractor_name());
						
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(eObj.getDate());						
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(eObj.getVoucher_type());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(eObj.getVoucher_no());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(eObj.getNarration());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionValueStyle);
						double Str1=Double.parseDouble(!StringUtils.isEmpty(eObj.getNet_paid())?eObj.getNet_paid():"0")*Double.parseDouble(!StringUtils.isEmpty(eObj.getNet_paid_units())?eObj.getNet_paid_units():"0");
						if(Str1!=0.0){cell.setCellValue(Str1);}else {cell.setCellValue("");}
						
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionValueStyle);
						double Str2=Double.parseDouble(!StringUtils.isEmpty(eObj.getGross_work_done())?eObj.getGross_work_done():"0")*Double.parseDouble(!StringUtils.isEmpty(eObj.getGross_work_done_units())?eObj.getGross_work_done_units():"0");
						if(Str2!=0.0){cell.setCellValue(Str2);}else {cell.setCellValue("");}
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionValueStyle);
						double Str3=Double.parseDouble(!StringUtils.isEmpty(eObj.getSd_payable())?eObj.getSd_payable():"0")*Double.parseDouble(!StringUtils.isEmpty(eObj.getSd_payable_units())?eObj.getSd_payable_units():"0");
						if(Str3!=0.0){cell.setCellValue(Str3);}else {cell.setCellValue("");}

						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionValueStyle);
						double Str4=Double.parseDouble(!StringUtils.isEmpty(eObj.getContractor_income_tax())?eObj.getContractor_income_tax():"0")*Double.parseDouble(!StringUtils.isEmpty(eObj.getContractor_income_tax_units())?eObj.getContractor_income_tax_units():"0");
						if(Str4!=0.0){cell.setCellValue(Str4);}else {cell.setCellValue("");}
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionValueStyle);
						double Str5=Double.parseDouble(!StringUtils.isEmpty(eObj.getCgst_tds())?eObj.getCgst_tds():"0")*Double.parseDouble(!StringUtils.isEmpty(eObj.getCgst_tds_units())?eObj.getCgst_tds_units():"0");
						if(Str5!=0.0){cell.setCellValue(Str5);}else {cell.setCellValue("");}

						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionValueStyle);
						double Str6=Double.parseDouble(!StringUtils.isEmpty(eObj.getSgst_tds())?eObj.getSgst_tds():"0")*Double.parseDouble(!StringUtils.isEmpty(eObj.getSgst_tds_units())?eObj.getSgst_tds_units():"0");
						if(Str6!=0.0){cell.setCellValue(Str6);}else {cell.setCellValue("");}

						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionValueStyle);
						double Str7=Double.parseDouble(!StringUtils.isEmpty(eObj.getIgst_tds())?eObj.getIgst_tds():"0")*Double.parseDouble(!StringUtils.isEmpty(eObj.getIgst_tds_units())?eObj.getIgst_tds_units():"0");
						if(Str7!=0.0){cell.setCellValue(Str7);}else {cell.setCellValue("");}
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionValueStyle);
						double Str8=Double.parseDouble(!StringUtils.isEmpty(eObj.getVat_wct())?eObj.getVat_wct():"0")*Double.parseDouble(!StringUtils.isEmpty(eObj.getVat_wct_units())?eObj.getVat_wct_units():"0");
						if(Str8!=0.0){cell.setCellValue(Str8);}else {cell.setCellValue("");}
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionValueStyle);
						double Str9=Double.parseDouble(!StringUtils.isEmpty(eObj.getMob_advance())?eObj.getMob_advance():"0")*Double.parseDouble(!StringUtils.isEmpty(eObj.getMob_advance_units())?eObj.getMob_advance_units():"0");
						if(Str9!=0.0){cell.setCellValue(Str9);}else {cell.setCellValue("");}
					
						cell = row.createCell(c++);
						cell.setCellStyle(sectionValueStyle);
						double Str10=Double.parseDouble(!StringUtils.isEmpty(eObj.getInterest_on_mob_adv())?eObj.getInterest_on_mob_adv():"0")*Double.parseDouble(!StringUtils.isEmpty(eObj.getInterest_on_mob_adv_units())?eObj.getInterest_on_mob_adv_units():"0");
						if(Str10!=0.0){cell.setCellValue(Str10);}else {cell.setCellValue("");}
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionValueStyle);
						double Str11=Double.parseDouble(!StringUtils.isEmpty(eObj.getAmount_withheld())?eObj.getAmount_withheld():"0")*Double.parseDouble(!StringUtils.isEmpty(eObj.getAmount_withheld_units())?eObj.getAmount_withheld_units():"0");
						if(Str11!=0.0){cell.setCellValue(Str11);}else {cell.setCellValue("");}
						
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(eObj.getRemarks());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(eObj.getCess_on_building());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(eObj.getEst_charges_on_cess());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(eObj.getCgst_output());
						
						cell = row.createCell(c++);
						cell.setCellStyle(sectionStyle);
						cell.setCellValue(eObj.getSgst_output());
						rowNo++;
	            }
	            for(int columnIndex = 0; columnIndex < 100; columnIndex++) {
	            	//sheet.autoSizeColumn(columnIndex);
	        		sheet.setColumnWidth(columnIndex, 25 * 200);
				}
	            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
                Date date = new Date();
                String fileName = "Expenditure_"+dateFormat.format(date);
                
                try{
	                /*FileOutputStream fos = new FileOutputStream(fileDirectory +fileName+".xls");
	                workBook.write(fos);
	                fos.flush();*/
	            	
	               response.setContentType("application/.csv");
	 			   response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	 			   response.setContentType("application/vnd.ms-excel");
	 			   // add response header
	 			   response.addHeader("Content-Disposition", "attachment; filename=" + fileName+".xlsx");
	 			   
	 			    //copies all bytes from a file to an output stream
	 			   workBook.write(response.getOutputStream()); // Write workbook to response.
		           workBook.close();
	 			    //flushes output stream
	 			    response.getOutputStream().flush();
	            	
	                
	                attributes.addFlashAttribute("success",dataExportSucess);
	            	//response.setContentType("application/vnd.ms-excel");
	            	//response.setHeader("Content-Disposition", "attachment; filename=filename.xls");
	            	//XSSFWorkbook  workbook = new XSSFWorkbook ();
	            	// ...
	            	// Now populate workbook the usual way.
	            	// ...
	            	//workbook.write(response.getOutputStream()); // Write workbook to response.
	            	//workbook.close();
	            }catch(FileNotFoundException e){
	                //e.printStackTrace();
	                attributes.addFlashAttribute("error",dataExportInvalid);
	            }catch(IOException e){
	                //e.printStackTrace();
	                attributes.addFlashAttribute("error",dataExportError);
	            }
			}else{
				attributes.addFlashAttribute("error",dataExportNoData);
			}
		}catch (Exception e) {
			 //e.printStackTrace()
		}
	}

	private CellStyle cellFormating(XSSFWorkbook workBook,byte[] rgb,HorizontalAlignment hAllign, VerticalAlignment vAllign, boolean isWrapText,boolean isBoldText,boolean isItalicText,int fontSize,String fontName) {
		CellStyle style = workBook.createCellStyle();
		//Setting Background color  
		//style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		if (style instanceof XSSFCellStyle) {
		   XSSFCellStyle xssfcellcolorstyle = (XSSFCellStyle)style;
		   xssfcellcolorstyle.setFillForegroundColor(new XSSFColor(rgb, null));
		}
		//style.setFillPattern(FillPatternType.ALT_BARS);
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setBorderTop(BorderStyle.MEDIUM);
		style.setBorderLeft(BorderStyle.MEDIUM);
		style.setBorderRight(BorderStyle.MEDIUM);
		style.setAlignment(hAllign);
		style.setVerticalAlignment(vAllign);
		style.setWrapText(isWrapText);
		
		Font font = workBook.createFont();
        //font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        font.setFontHeightInPoints((short)fontSize);  
        font.setFontName(fontName);  //"Times New Roman"
        
        font.setItalic(isItalicText); 
        font.setBold(isBoldText);
        // Applying font to the style  
        style.setFont(font); 
        
        return style;
	}
	@RequestMapping(value = "/upload-expenditures", method = {RequestMethod.POST})
	public ModelAndView uploadRisk(@ModelAttribute Expenditure expenditure,RedirectAttributes attributes,HttpSession session){
		ModelAndView model = new ModelAndView();
		try {
			String user_Id = (String) session.getAttribute("USER_ID");
			String userName = (String) session.getAttribute("USER_NAME");
			String userDesignation = (String) session.getAttribute("USER_DESIGNATION");
			
			expenditure.setCreated_by_user_id_fk(user_Id);
			expenditure.setUser_id(user_Id);
			expenditure.setUser_name(userName);
			expenditure.setDesignation(userDesignation);
			model.setViewName("redirect:/expenditure");
			
			if(!StringUtils.isEmpty(expenditure.getExpenditureFile())){
				MultipartFile multipartFile = expenditure.getExpenditureFile();
				// Creates a workbook object from the uploaded excelfile
				if (multipartFile.getSize() > 0){					
					XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
					// Creates a worksheet object representing the first sheet
					int sheetsCount = workbook.getNumberOfSheets();
					if(sheetsCount > 0) {
						XSSFSheet risksDrawingsSheet = workbook.getSheetAt(0);
						//System.out.println(uploadFilesSheet.getSheetName());
						//header row
						XSSFRow headerRow = risksDrawingsSheet.getRow(0);
						//checking given file format
						if(headerRow != null){
							List<String> fileFormat = FileFormatModel.getExpenditureFileFormat();	
							int noOfColumns = headerRow.getLastCellNum();
							if(noOfColumns == fileFormat.size()){
								for (int i = 0; i < fileFormat.size();i++) {
				                	//System.out.println(headerRow.getCell(i).getStringCellValue().trim());
				                	//if(!fileFormat.get(i).trim().equals(headerRow.getCell(i).getStringCellValue().trim())){
									String columnName = headerRow.getCell(i).getStringCellValue().trim();
									if(!columnName.equals(fileFormat.get(i).trim()) && !columnName.contains(fileFormat.get(i).trim())){
				                		attributes.addFlashAttribute("error",uploadformatError);
				                		return model;
				                	}
								}
							}else{
								attributes.addFlashAttribute("error",uploadformatError);
		                		return model;
							}
						}else{
							attributes.addFlashAttribute("error",uploadformatError);
	                		return model;
						}
						
						int count = uploadExpenditures(expenditure,user_Id,userName);
						if(count > 0) {
							attributes.addFlashAttribute("success", + count + " Expenditures uploaded successfully.");
							FormHistory formHistory = new FormHistory();
							formHistory.setCreated_by_user_id_fk(expenditure.getCreated_by_user_id_fk());
							formHistory.setUser(expenditure.getDesignation()+" - "+expenditure.getUser_name());
							formHistory.setModule_name_fk("Finance");
							formHistory.setForm_name("Upload Expenditure");
							formHistory.setForm_action_type("Upload");
							formHistory.setForm_details( count + " Expenditures uploaded successfully.");
							formHistory.setWork(expenditure.getWork_id_fk());
							formHistory.setContract(expenditure.getContract_id_fk());
							
							boolean history_flag = formsHistoryDao.saveFormHistory(formHistory);
							/********************************************************************************/
							
						}
						
					}
					workbook.close();
				}
			} else {
				attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("error", "Something went wrong. Please try after some time");
			logger.fatal("updateDataDate() : "+e.getMessage());
		}
		return model;
	}

	private int uploadExpenditures(Expenditure obj, String userId,String userName) throws Exception {
		Expenditure expenditure = null;
		List<Expenditure> expendituresList = new ArrayList<Expenditure>();
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat formatter3 = new SimpleDateFormat("MMM/dd/yyyy");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");		
		Writer w = null;
		int count = 0 ;
		try {	
			MultipartFile excelfile = obj.getExpenditureFile();
			// Creates a workbook object from the uploaded excelfile
			if (!StringUtils.isEmpty(excelfile) && excelfile.getSize() > 0 ){
				
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				int sheetsCount = workbook.getNumberOfSheets();
				if(sheetsCount > 0) {
					XSSFSheet risksDrawingsSheet = workbook.getSheetAt(0);
					//System.out.println(uploadFilesSheet.getSheetName());
					//header row
					//XSSFRow headerRow = uploadFilesSheet.getRow(0);							
					DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
					//System.out.println(uploadFilesSheet.getLastRowNum());
					for(int i = 1; i <= risksDrawingsSheet.getLastRowNum();i++){
						XSSFRow row = risksDrawingsSheet.getRow(i);
						// Sets the Read data to the model class
						// Cell cell = row.getCell(0);
						// String j_username = formatter.formatCellValue(row.getCell(0));
						//System.out.println(i);
						expenditure = new Expenditure();
						String val = null;
						if(!StringUtils.isEmpty(row)) {		
							
							val = formatter.formatCellValue(row.getCell(0)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setWork_id_fk(val);}							
						  
							val = formatter.formatCellValue(row.getCell(1)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setContract_id_fk(val);}
							
							val = formatter.formatCellValue(row.getCell(2)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setLedger_account(val);}								
							
							val = formatter.formatCellValue(row.getCell(3)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setContractor_name(val);}
							

							
							val = formatter.formatCellValue(row.getCell(4)).trim();
							if(!StringUtils.isEmpty(val)) { 
								
								
								String Str = val;
								String[] parts = Str.split("-");
								int TotalDate=Integer.parseInt(parts[2]);
								String Concat = TotalDate+"-"+getNumberFromMonthName(parts[1], Locale.ENGLISH)+"-"+parts[0];
								expenditure.setDate(Concat);
								expenditure.setDate(DateParser.parse(expenditure.getDate()));
								

							}					
							
							val = formatter.formatCellValue(row.getCell(5)).trim();
							if(!StringUtils.isEmpty(val)) { 
								
								String Str=val;
								int getIndex=Str.indexOf("(");
								int getIndex1=Str.indexOf(")");
								
								if(getIndex!=-1)
								{
									String VoucherType=Str.substring(getIndex+1,getIndex1);
									expenditure.setVoucher_type(VoucherType);
								}
								else
								{
									expenditure.setVoucher_type(val);
								}
					
							
							}								
							
							val = formatter.formatCellValue(row.getCell(6)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setVoucher_no(val);}										
							
							val = formatter.formatCellValue(row.getCell(7)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setNarration(val);}
							
							val = formatter.formatCellValue(row.getCell(8)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setNet_paid(val);expenditure.setNet_paid_units("1");}
							
							val = formatter.formatCellValue(row.getCell(9)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setGross_work_done(val);expenditure.setGross_work_done_units("1");}
							
							
							val = formatter.formatCellValue(row.getCell(10)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setSd_payable(val);expenditure.setSd_payable_units("1");}
							
							val = formatter.formatCellValue(row.getCell(11)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setContractor_income_tax(val);expenditure.setContractor_income_tax_units("1");}
							
							val = formatter.formatCellValue(row.getCell(12)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setCgst_tds(val);expenditure.setCgst_tds_units("1");}	
							
							val = formatter.formatCellValue(row.getCell(13)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setSgst_tds(val);expenditure.setSgst_tds_units("1");}
							
							val = formatter.formatCellValue(row.getCell(14)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setIgst_tds(val);expenditure.setIgst_tds_units("1");}
							
							val = formatter.formatCellValue(row.getCell(15)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setMob_advance(val);expenditure.setMob_advance_units("1");}
							
							val = formatter.formatCellValue(row.getCell(16)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setInterest_on_mob_adv(val);expenditure.setInterest_on_mob_adv_units("1");}
							
							
							val = formatter.formatCellValue(row.getCell(17)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setCess_on_building(val);expenditure.setCess_on_building_units("1");}
							
							val = formatter.formatCellValue(row.getCell(18)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setEst_charges_on_cess(val);expenditure.setEst_charges_on_cess_units("1");}
							
							val = formatter.formatCellValue(row.getCell(19)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setCgst_output(val);expenditure.setCgst_output_units("1");}
							
							val = formatter.formatCellValue(row.getCell(20)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setSgst_output(val);expenditure.setSgst_output_units("1");}							
							
							
							
							val = formatter.formatCellValue(row.getCell(21)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setVat_wct(val);expenditure.setVat_wct_units("1");}
							
							val = formatter.formatCellValue(row.getCell(22)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setAmount_withheld(val);expenditure.setAmount_withheld_units("1");}
							
							val = formatter.formatCellValue(row.getCell(23)).trim();
							if(!StringUtils.isEmpty(val)) { expenditure.setRemarks(val);}

						
						}						
						boolean flag = expenditure.checkNullOrEmpty();
						
						if(!flag) {
							expendituresList.add(expenditure);
						}
					}
					
					if(!expendituresList.isEmpty()){
						count  = expenditureService.uploadExpenditures(expendituresList);
					}
				}
				workbook.close();
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadExpenditures() : "+e.getMessage());
			throw new Exception(e);	
		}finally{
		    try{
		        if ( w != null)
		        	w.close( );
		    }catch ( IOException e){
		    	e.printStackTrace();
		    	logger.error("uploadExpenditures() : "+e.getMessage());
		    	throw new Exception(e);
		    }
		}
		
		return count;
	}

	   public static int getNumberFromMonthName(String monthName, Locale locale) throws ParseException {
		   
	        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMM")
	                                        .withLocale(locale);
	        TemporalAccessor temporalAccessor = dtFormatter.parse(monthName);
	        return temporalAccessor.get(ChronoField.MONTH_OF_YEAR);
	    }
}
