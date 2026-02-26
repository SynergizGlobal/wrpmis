package com.synergizglobal.wrpmis.controller;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.wrpmis.Iservice.ContractService;
import com.synergizglobal.wrpmis.Iservice.CustomReportService;
import com.synergizglobal.wrpmis.Iservice.FormsAccessService;
import com.synergizglobal.wrpmis.constants.PageConstants;
import com.synergizglobal.wrpmis.model.Contract;
import com.synergizglobal.wrpmis.model.CustomReportColumns;
import com.synergizglobal.wrpmis.model.Form;
import com.synergizglobal.wrpmis.model.StripChart;
import com.synergizglobal.wrpmis.model.User;



@Controller
public class CustomReportController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	Logger logger = Logger.getLogger(CustomReportController.class);

	@Autowired
	FormsAccessService formsAccessService;
	
	@Autowired
	ContractService contractService;	
	
	@Autowired
	CustomReportService customReportService;	
	
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

	@RequestMapping(value="/custom-report",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView CustomReport(@ModelAttribute Contract obj,HttpSession session){
		ModelAndView model = new ModelAndView(PageConstants.customReport);
		try {
			
			User uObj = (User) session.getAttribute("user");
			obj.setUser_type_fk(uObj.getUser_type_fk());
			obj.setUser_role_code(uObj.getUser_role_code());
			obj.setUser_id(uObj.getUser_id());
			
			List<Form> modulesList = formsAccessService.getAllModules();
			model.addObject("modulesList", modulesList);
			
			List<Contract> projectsList = contractService.getProjectsListForContractForm(obj);
			model.addObject("projectsList", projectsList);
			
			List<Contract> worksList = contractService.getWorkListForContractForm(obj);
			model.addObject("worksList", worksList);
			
			List<Contract> departmentList = contractService.getDepartmentList();
			model.addObject("departmentList", departmentList);
			
			List<User> hodList = contractService.setHodList();
			model.addObject("hodList", hodList);
			
			List<User> dyHodList = contractService.getDyHodList();
			model.addObject("dyHodList", dyHodList);
			
			List<Contract> contractsList = customReportService.getAllContractList(obj);
			model.addObject("contractsList", contractsList);			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("CustomReport : " + e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/ajax/getModuleGroups", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CustomReportColumns> getModuleGroups(@ModelAttribute CustomReportColumns obj,HttpSession session){
		List<CustomReportColumns> ModuleColumns = null;
		try{
			
			ModuleColumns = customReportService.getModuleGroups(obj);
	
		}catch(Exception e){
			logger.error("getModuleColumns() : "+e.getMessage());
		}
		return ModuleColumns;
	}
	
	@RequestMapping(value = "/ajax/getLayouts", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CustomReportColumns> getLayouts(@ModelAttribute CustomReportColumns obj,HttpSession session){
		List<CustomReportColumns> ModuleColumns = null;
		try{

			String userId = (String) session.getAttribute("USER_ID");
			obj.setCreated_by_user_id_fk(userId);	
			ModuleColumns = customReportService.getLayouts(obj);
	
		}catch(Exception e){
			logger.error("getModuleColumns() : "+e.getMessage());
		}
		return ModuleColumns;
	}	
	
	@RequestMapping(value = "/ajax/getModuleFiltersOptionValues", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CustomReportColumns> getModuleFiltersOptionValues(@ModelAttribute CustomReportColumns obj,HttpSession session){
		List<CustomReportColumns> ModuleColumns = null;
		try{
			
			ModuleColumns = customReportService.getModuleFiltersOptionValues(obj);
	
		}catch(Exception e){
			logger.error("getModuleColumns() : "+e.getMessage());
		}
		return ModuleColumns;
	}
	
	@RequestMapping(value = "/ajax/getModuleFilters", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CustomReportColumns> getModuleFilters(@ModelAttribute CustomReportColumns obj,HttpSession session){
		List<CustomReportColumns> ModuleColumns = null;
		try{
			
			ModuleColumns = customReportService.getModuleFilters(obj);
	
		}catch(Exception e){
			logger.error("getModuleColumns() : "+e.getMessage());
		}
		return ModuleColumns;
	}		

	@RequestMapping(value = "/ajax/getModuleGroupColumns", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CustomReportColumns> getModuleGroupColumns(@ModelAttribute CustomReportColumns obj,HttpSession session){
		List<CustomReportColumns> ModuleColumns = null;
		try{
			
			ModuleColumns = customReportService.getModuleGroupColumns(obj);
	
		}catch(Exception e){
			logger.error("getModuleColumns() : "+e.getMessage());
		}
		return ModuleColumns;
	}
	
	@RequestMapping(value = "/ajax/saveCustomReportLayout", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean saveCustomReportLayout(@ModelAttribute CustomReportColumns obj,HttpSession session){
		boolean flag=false;
		try{
			
			String userId = (String) session.getAttribute("USER_ID");
			obj.setCreated_by_user_id_fk(userId);			
			
			if(obj.getGrpHead()!=null)
			{
				flag =  customReportService.saveCustomReportLayout(obj);			
			}
	
		}catch(Exception e){
			logger.error("getModuleColumns() : "+e.getMessage());
		}
		return flag;
	}	

	@RequestMapping(value = "/ajax/checkLayoutName", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean checkLayoutName(@ModelAttribute CustomReportColumns obj, HttpSession session) throws Exception {
		boolean flag = false;
		try {
			String userId = (String) session.getAttribute("USER_ID");
			obj.setCreated_by_user_id_fk(userId);
			flag = customReportService.checkLayoutName(obj);
		} catch (SQLException e) {
			logger.error("checkLayoutName : " + e.getMessage());
		}
		return flag;
	}
	
	@RequestMapping(value = "/ajax/removeLayout", method = {RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean removeLayout(@ModelAttribute CustomReportColumns obj, HttpSession session) throws Exception {
		boolean flag = false;
		try {
			String userId = (String) session.getAttribute("USER_ID");
			obj.setCreated_by_user_id_fk(userId);
			flag = customReportService.removeLayout(obj);
		} catch (SQLException e) {
			logger.error("checkLayoutName : " + e.getMessage());
		}
		return flag;
	}	
	
	@RequestMapping(value = "/generate-custom-report", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportSafety(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute CustomReportColumns customReportColumns,RedirectAttributes attributes){
		try {
		
			XSSFWorkbook  workBook = new XSSFWorkbook ();
			
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
	        CellStyle sectioncostStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle sectionunitsStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        
	        CellStyle centerStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
	        CellStyle rightStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.RIGHT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);			
			
			if(customReportColumns.getGrpHead()!=null)
			{
				String splitStr[]=customReportColumns.getGrpHead().split(",");
				NumberFormat numberFormatter = new DecimalFormat("#0.00");
				
				for(int i1=0;i1<splitStr.length;i1++)
				{
					String splitStrColumns[]=customReportColumns.getGrpHeadColumns().split(",");
						String append="";
						String appendStr="";
						for(int j=0;j<splitStrColumns.length;j++)
						{
				            if(splitStrColumns[j].indexOf(splitStr[i1])!=-1)
				            {
								String[] hyphonStr=splitStrColumns[j].split("-");
								append +=hyphonStr[1]+"^";
								String columnName = hyphonStr[1].replaceAll(" ", "_");
								appendStr +="ISNULL(a."+columnName+",'') as "+columnName+""+",";
				            }
						}
						append=append.substring(0, append.length()-1);
						appendStr=appendStr.substring(0, appendStr.length()-1);
						
						
						CustomReportColumns tableColumns=new CustomReportColumns();
						tableColumns.setColumn_name(appendStr);
						tableColumns.setName(splitStr[i1]);
						tableColumns.setFilterColumns(customReportColumns.getFilterColumns());
						tableColumns.setModule_name_fk(customReportColumns.getModule_name_fk());
			           
						List<String[]> dataList = customReportService.getTableColumns(tableColumns);
						XSSFSheet contractsSheet = workBook.createSheet(WorkbookUtil.createSafeSheetName(splitStr[i1]));
						
						if(dataList != null && dataList.size() > 0)
						{
					        workBook.setSheetOrder(contractsSheet.getSheetName(), i1);
					        
				            XSSFRow headingRow = contractsSheet.createRow(0);
				            
				            

				                
					            String headerString = append;
					            
					            String[] headerStringArr = headerString.split("\\^");
					            
					            for (int i = 0; i < headerStringArr.length; i++) {		        	
						        	Cell cell = headingRow.createCell(i);
							        cell.setCellStyle(greenStyle);
									cell.setCellValue(headerStringArr[i]);
								}
					            
					            
					            
					            short rowNo = 1;
					           
					            
					            for(String[] p:dataList)
					            {
					                XSSFRow row = contractsSheet.createRow(rowNo);
					                int c = 0;					            	
					            	for(int m=0;m<p.length;m++)					            	
					            	{
					            		Cell cell = row.createCell(c++);
										cell.setCellStyle(sectionStyle);
										cell.setCellValue(p[m]);					            		
					            	}
					            	rowNo++;
					            }
					            
					            
					            for(int columnIndex = 0; columnIndex < headerStringArr.length; columnIndex++) {
					            	contractsSheet.setColumnWidth(columnIndex, 25 * 200);
								}
					            
							} 
						}

					
				}

					

		            
		            /*******************************************************************************************/
		            
		            
	                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
	                Date date = new Date();
	                String fileName = customReportColumns.getModule_name_fk()+"_"+dateFormat.format(date);
	                
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


			
		}catch(Exception e){	
			e.printStackTrace();
			logger.error("exportContract : "+e.getMessage());
			attributes.addFlashAttribute("error", commonError);			
		}
		//return view;
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
}

