package com.synergizglobal.pmis.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
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
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.Br;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.CTShd;
import org.docx4j.wml.CTVerticalJc;
import org.docx4j.wml.Color;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.R;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STBorder;
import org.docx4j.wml.STHint;
import org.docx4j.wml.STVerticalJc;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.TblBorders;
import org.docx4j.wml.TblPr;
import org.docx4j.wml.TblWidth;
import org.docx4j.wml.Tc;
import org.docx4j.wml.TcPr;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.docx4j.wml.U;
import org.docx4j.wml.UnderlineEnumeration;
import org.docx4j.wml.PPrBase.Spacing;
import org.docx4j.wml.TcPrInner.HMerge;
import org.docx4j.wml.TcPrInner.TcBorders;
import org.docx4j.wml.TcPrInner.VMerge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergizglobal.pmis.Iservice.UserLoginReportService;
import com.synergizglobal.pmis.constants.PageConstants;
import com.synergizglobal.pmis.model.Alerts;
import com.synergizglobal.pmis.model.Training;
import com.synergizglobal.pmis.model.User;
import com.synergizglobal.pmis.model.User;

@Controller
public class UserLoginReportController {
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	public static Logger logger = Logger.getLogger(UserLoginReportController.class);
	
	@Autowired
	UserLoginReportService userLoginReportSrvice;
	
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
	
	@RequestMapping(value = "/user-login-report", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportTraining(HttpServletRequest request, HttpServletResponse response,HttpSession session,@ModelAttribute User dObj,RedirectAttributes attributes){
			ModelAndView view = new ModelAndView();
			List<User> departmentList = new ArrayList<User>();
			List<User> designationList = new ArrayList<User>();
			List<User> userLoginList = new ArrayList<User>();
			try {
				    view.setViewName("redirect:/home");
				    departmentList =   userLoginReportSrvice.getDepartmentList(dObj);
				    //User userLoginDetails = userLoginReportSrvice.getUserLoginDetails(dObj);
				    XSSFWorkbook  workBook = new XSSFWorkbook ();
			        byte[] blueRGB = new byte[]{(byte)34, (byte)34, (byte)59};
			        byte[] yellowRGB = new byte[]{(byte)74, (byte)78, (byte)105};
			        byte[] greenRGB = new byte[]{(byte)146, (byte)208, (byte)80};
			        byte[] redRGB = new byte[]{(byte)255, (byte)0, (byte)0};
			        byte[] whiteRGB = new byte[]{(byte)230, (byte)230, (byte)250};
			        
			        boolean isWrapText = true;boolean isBoldText = true;boolean isItalicText = false; int fontSize = 12 ;int headerFontSize = 14;String fontName = "Calibri";
			        CellStyle blueStyle = cellFormating(workBook,blueRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,headerFontSize,fontName);
			        CellStyle yellowStyle = cellFormating(workBook,yellowRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,false,isItalicText,fontSize,fontName);
			        CellStyle greenStyle = cellFormating(workBook,greenRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
			        CellStyle redStyle = cellFormating(workBook,redRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
			        CellStyle whiteStyle = cellFormating1(workBook,whiteRGB,HorizontalAlignment.CENTER,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
			        
			        CellStyle indexWhiteStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,isBoldText,isItalicText,fontSize,fontName);
			        
			        isWrapText = true;isBoldText = false;isItalicText = false; fontSize = 9;fontName = "Times New Roman";
			        CellStyle sectionStyle = cellFormating(workBook,whiteRGB,HorizontalAlignment.LEFT,VerticalAlignment.CENTER,isWrapText,false,isItalicText,fontSize,fontName);

					if(departmentList != null && departmentList.size() > 0){
			          
			            int sheetNum = 0;
			            XSSFSheet[] sheet = new XSSFSheet[departmentList.size()];
			            		
			        	for (User designationLists : departmentList) {
			        		short sNO = 1;
			        		short count = 1;
			        		sheet[sheetNum] = workBook.createSheet(WorkbookUtil.createSafeSheetName(designationLists.getDepartment_name()));
			            	workBook.setSheetOrder(sheet[sheetNum].getSheetName(), sheetNum);
			        		String dept = designationLists.getDepartment_name();
			        		dObj.setDepartment_name(dept);
			        		designationList =   userLoginReportSrvice.getDesignationList(dObj);
			    			int cellIndex = 0;	
			    			 
		            	    XSSFRow headingRow = sheet[sheetNum].createRow(count++);
				            String headerString = "S.No.  ^Designation  ^Name ^No of Logins  ";
				            String[] firstHeaderStringArr = headerString.split("\\^");
				            for (int i = 0; i < firstHeaderStringArr.length; i++) {		        	
					        	Cell cell = headingRow.createCell(i);
						        cell.setCellStyle(blueStyle);
								cell.setCellValue(firstHeaderStringArr[i]);
				            }
			        		for (User obj : designationList) {
			        		
					            int hStartRowIndx = count, hEndRowIndx = count, hStartColIndx = 0,hEndColIndx = 4;
					        	XSSFRow headerRow = sheet[sheetNum].createRow(count++);
								cellIndex = 0;

								Cell headerCell = headerRow.createCell(cellIndex++);
								headerCell.setCellStyle(yellowStyle);
								headerCell.setCellValue(obj.getReporting_to_designation() +" - "+obj.getReporting_to_name());
								
								headerCell = headerRow.createCell(cellIndex++);
								headerCell.setCellStyle(yellowStyle);
								headerCell.setCellValue("");
								
								
								headerCell = headerRow.createCell(cellIndex++);
								headerCell.setCellStyle(yellowStyle);
								headerCell.setCellValue("");
								
								hEndColIndx = cellIndex;
								headerCell = headerRow.createCell(cellIndex++);
								headerCell.setCellStyle(yellowStyle);
								headerCell.setCellValue("");
								
								sheet[sheetNum].addMergedRegion(new CellRangeAddress(hStartRowIndx, hEndRowIndx, hStartColIndx,hEndColIndx));
								
								String reporting_to = obj.getReporting_to_designation();
				        		dObj.setReporting_to_designation(reporting_to);
								userLoginList =   userLoginReportSrvice.getUserLoginList(dObj);
				                for (User user : userLoginList) {
					                XSSFRow row = sheet[sheetNum].createRow(count++);
					                int a = 0;
					                
					                Cell cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue(sNO++);
									
					                cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue(user.getDesignation());
									
					                cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue(user.getUser_name());
									
					                cell2 = row.createCell(a++);
									cell2.setCellStyle(whiteStyle);
									cell2.setCellValue(user.getLoginCount());
									
				                }
			        		}
			        		 for(int columnIndex = 0; columnIndex < departmentList.size(); columnIndex++) {
							     //sheet[sheetNum].setColumnWidth(columnIndex, 25 * 250);
							     sheet[sheetNum].autoSizeColumn(columnIndex);
							}
		            	 sheetNum++;
			        	}

		            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		            Date date = new Date();
		            String fileName = "PMIS - User Login Report" +"( "+dateFormat.format(date)+" )" ;
		            
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
				// TODO: handle exception
			}
		}
	public static void addHeading(WordprocessingMLPackage wordMLPackage,
			MainDocumentPart t, ObjectFactory factory,JcEnumeration alignment, RPr titleRPr, String contentValue) throws Exception {
		P paragraph = factory.createP();
		setParagraphAlign(factory, paragraph, alignment);
		Text txt = factory.createText();
		txt.setValue(contentValue);
		R run = factory.createR();
		run.getContent().add(txt);
		run.setRPr(titleRPr);
		paragraph.getContent().add(run);
		t.addObject(paragraph);		
	}
	
	private static void addPageBreak(MainDocumentPart documentPart) {
		 ObjectFactory objectFactory = new ObjectFactory();
	        //P paragraph = objectFactory.createP();
	        //R run = objectFactory.createR();
	        P p = objectFactory.createP();
	        // Create object for r
	        R r = objectFactory.createR();
	        p.getContent().add(r);
	        // Create object for br
	        Br br = objectFactory.createBr();
	        r.getContent().add(br);
	        br.setType(org.docx4j.wml.STBrType.PAGE);
	        documentPart.addObject(p);
	}
	/*****************************
	 * 
	 * 
	 */
	public static RPr getRPr(ObjectFactory factory, String fontFamily,
			String colorVal, String fontSize, STHint sTHint, boolean isBlod,
			boolean isUnderLine, boolean isItalic, boolean isStrike) {
		RPr rPr = factory.createRPr();
		RFonts rf = new RFonts();
		rf.setHint(sTHint);
		rf.setAscii(fontFamily);
		rf.setHAnsi(fontFamily);
		rPr.setRFonts(rf);

		BooleanDefaultTrue bdt = factory.createBooleanDefaultTrue();
		rPr.setBCs(bdt);
		if (isBlod) {
			rPr.setB(bdt);
		}
		if (isItalic) {
			rPr.setI(bdt);
		}
		if (isStrike) {
			rPr.setStrike(bdt);
		}
		if (isUnderLine) {
			U underline = new U();
			underline.setVal(UnderlineEnumeration.SINGLE);
			rPr.setU(underline);
		}

		Color color = new Color();
		color.setVal(colorVal);
		rPr.setColor(color);
		

		HpsMeasure sz = new HpsMeasure();
		sz.setVal(new BigInteger(fontSize));
		rPr.setSz(sz);
		rPr.setSzCs(sz);
		
		return rPr;
	}
	
	public static void addBorders(Tbl table, String borderSize) {
		table.setTblPr(new TblPr());
		CTBorder border = new CTBorder();
		border.setColor("a0a3bb");
		border.setSz(new BigInteger(borderSize));
		border.setSpace(new BigInteger("0"));
		border.setVal(STBorder.SINGLE);
		TblBorders borders = new TblBorders();
		borders.setBottom(border);
		borders.setLeft(border);
		borders.setRight(border);
		borders.setTop(border);
		borders.setInsideH(border);
		borders.setInsideV(border);
		table.getTblPr().setTblBorders(borders);
	}

	
	public static void setParagraphAlign(ObjectFactory factory, P p,
			JcEnumeration jcEnumeration) {
		PPr pPr = p.getPPr();
		if (pPr == null) {
			pPr = factory.createPPr();
		}
		Jc jc = pPr.getJc();
		if (jc == null) {
			jc = new Jc();
		}
		jc.setVal(jcEnumeration);
		pPr.setJc(jc);
		p.setPPr(pPr);
	}

	public static void setTableAlign(ObjectFactory factory, Tbl table,
			JcEnumeration jcEnumeration) {
		TblPr tablePr = table.getTblPr();
		if (tablePr == null) {
			tablePr = factory.createTblPr();
		}
		Jc jc = tablePr.getJc();
		if (jc == null) {
			jc = new Jc();
		}
		jc.setVal(jcEnumeration);
		tablePr.setJc(jc);
		
		
		TblWidth tblwidth = factory.createTblWidth();
        tblwidth.setW( BigInteger.valueOf( 5000) ); // 5000 = 100%
        tblwidth.setType("pct");
        tablePr.setTblW(tblwidth);
        
		table.setTblPr(tablePr);
	}

	
	public static void addTableCell(ObjectFactory factory,
			WordprocessingMLPackage wordMLPackage, Tr tableRow, String content,
			RPr rpr, JcEnumeration jcEnumeration, boolean hasBgColor,
			String backgroudColor) {
		Tc tableCell = factory.createTc();
		P p = factory.createP();
		setParagraphAlign(factory, p, jcEnumeration);
		//Text t = factory.createText();
		//t.setValue(content);
		R run = factory.createR();
		run.setRPr(rpr);
		
		//run.getContent().add(t);
		
	    p.getContent().add(run);  
	    if (content != null) {  
	        String[] contentArr = content.split("\n");  
	        Text text = factory.createText();  
	        text.setSpace("preserve");  
	        text.setValue(contentArr[0]);  
	        run.getContent().add(text);  
	  
	        for (int i = 1, len = contentArr.length; i < len; i++) {  
	            Br br = factory.createBr();  
	            run.getContent().add(br);// 换行  
	            text = factory.createText();  
	            text.setSpace("preserve");  
	            text.setValue(contentArr[i]);  
	            run.getContent().add(text);  
	        }  
	    }  
		
		

		TcPr tcPr = tableCell.getTcPr();
		if (tcPr == null) {
			tcPr = factory.createTcPr();
		}
		
		CTVerticalJc valign = factory.createCTVerticalJc();
		valign.setVal(STVerticalJc.CENTER);
		tcPr.setVAlign(valign);		
		
		//Removing space in cells
		PPr pPr = factory.createPPr();
		Spacing spacing = new Spacing();
		spacing.setBefore(BigInteger.TWO);
		spacing.setAfter(BigInteger.TWO);
		//spacing.setAfterLines(BigInteger.TEN);
		//spacing.setBeforeLines(BigInteger.TEN);
		pPr.setSpacing(spacing);
		
		Jc justification = factory.createJc();
		justification.setVal(jcEnumeration);
		pPr.setJc(justification);

		
		p.setPPr(pPr);
		
		tableCell.getContent().add(p);
		if (hasBgColor) {
			CTShd shd = tcPr.getShd();
			if (shd == null) {
				shd = factory.createCTShd();
			}
			shd.setColor("#ecf2ff");
			shd.setFill(backgroudColor);
			tcPr.setShd(shd);
		}
		
		TcBorders tcb = factory.createTcPrInnerTcBorders();
		CTBorder ctb = factory.createCTBorder();
		STBorder stb = STBorder.NONE;
		ctb.setVal(stb);
		tcb.setBottom(ctb);
		tcb.setRight(ctb);
		tcb.setLeft(ctb);
		tcb.setTop(ctb);
		tcPr.setTcBorders(tcb);
		
		tableCell.setTcPr(tcPr);
		
		tableRow.getContent().add(tableCell);
	}
	
	public static void mergeCellsHorizontal(Tbl tbl, int row, int fromCell, int toCell) {
	    if (row < 0 || fromCell < 0 || toCell < 0) {
	        return;
	    }
	    List<Tr> trList = getTblAllTr(tbl);
	    if (row > trList.size()) {
	        return;
	    }
	    Tr tr = trList.get(row);
	    List<Tc> tcList = getTrAllCell(tr);
	    for (int cellIndex = fromCell, len = Math
	            .min(tcList.size() - 1, toCell); cellIndex <= len; cellIndex++) {
	        Tc tc = tcList.get(cellIndex);
	        TcPr tcPr = getTcPr(tc);
	        HMerge hMerge = tcPr.getHMerge();
	        if (hMerge == null) {
	            hMerge = new HMerge();
	            tcPr.setHMerge(hMerge);
	        }
	        if (cellIndex == fromCell) {
	            hMerge.setVal("restart");
	        } else {
	            hMerge.setVal("continue");
	        }
	    }
	}
	
	public static Tc getTc(Tbl tbl, int row, int cell) {  
        if (row < 0 || cell < 0) {  
            return null;  
        }  
        List<Tr> trList = getTblAllTr(tbl);  
        if (row >= trList.size()) {  
            return null;  
        }  
        List<Tc> tcList = getTrAllCell(trList.get(row));  
        if (cell >= tcList.size()) {  
            return null;  
        }  
        return tcList.get(cell);  
    }  
  
   
    public static List<Tc> getTrAllCell(Tr tr) {  
        List<Object> objList = getAllElementFromObject(tr, Tc.class);  
        List<Tc> tcList = new ArrayList<Tc>();  
        if (objList == null) {  
            return tcList;  
        }  
        for (Object tcObj : objList) {  
            if (tcObj instanceof Tc) {  
                Tc objTc = (Tc) tcObj;  
                tcList.add(objTc);  
            }  
        }  
        return tcList;  
    }  
  
    public static TcPr getTcPr(Tc tc) {  
        TcPr tcPr = tc.getTcPr();  
        if (tcPr == null) {  
            tcPr = new TcPr();  
            tc.setTcPr(tcPr);  
        }  
        return tcPr;  
    }  
  
   
    public static List<Tr> getTblAllTr(Tbl tbl) {  
        List<Object> objList = getAllElementFromObject(tbl, Tr.class);  
        List<Tr> trList = new ArrayList<Tr>();  
        if (objList == null) {  
            return trList;  
        }  
        for (Object obj : objList) {  
            if (obj instanceof Tr) {  
                Tr tr = (Tr) obj;  
                trList.add(tr);  
            }  
        }  
        return trList;  
    }  
  
   
    public static List<Object> getAllElementFromObject(Object obj,  
            Class<?> toSearch) {  
        List<Object> result = new ArrayList<Object>();  
        if (obj instanceof JAXBElement)  
            obj = ((JAXBElement<?>) obj).getValue();  
        if (obj.getClass().equals(toSearch))  
            result.add(obj);  
        else if (obj instanceof ContentAccessor) {  
            List<?> children = ((ContentAccessor) obj).getContent();  
            for (Object child : children) {  
                result.addAll(getAllElementFromObject(child, toSearch));  
            }  
        }  
        return result;  
    }  
    
    
    public static void mergeCellsVertically(Tbl tbl, int col, int fromRow, int toRow) {  
        if (col < 0 || fromRow < 0 || toRow < 0) {  
            return;  
        }  
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {  
            Tc tc = getTc(tbl, rowIndex, col);  
            if (tc == null) {  
                break;  
            }  
            TcPr tcPr = getTcPr(tc);  
            VMerge vMerge = tcPr.getVMerge();  
            if (vMerge == null) {  
                vMerge = new VMerge();  
                tcPr.setVMerge(vMerge);  
            }  
            if (rowIndex == fromRow) {  
                vMerge.setVal("restart");  
            } else {  
                vMerge.setVal("continue");  
            }  
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
	        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
	        font.setFontHeightInPoints((short)fontSize);  
	        font.setFontName(fontName);  //"Times New Roman"
	        
	        font.setItalic(isItalicText); 
	        font.setBold(isBoldText);
	        // Applying font to the style  
	        style.setFont(font); 
	        
	        return style;
		}
		private CellStyle cellFormating1(XSSFWorkbook workBook,byte[] rgb,HorizontalAlignment hAllign, VerticalAlignment vAllign, boolean isWrapText,boolean isBoldText,boolean isItalicText,int fontSize,String fontName) {
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
	        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
	        font.setFontHeightInPoints((short)fontSize);  
	        font.setFontName(fontName);  //"Times New Roman"
	        
	        font.setItalic(isItalicText); 
	        //font.setBold(isBoldText);
	        // Applying font to the style  
	        style.setFont(font); 
	        
	        return style;
		}
		
	}