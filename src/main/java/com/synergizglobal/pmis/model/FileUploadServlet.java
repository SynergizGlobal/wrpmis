package com.synergizglobal.pmis.model;
 
import java.io.File;
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.synergizglobal.pmis.constants.CommonConstants2;
/**
 * 
 * @author javacodepoint
 *
 */
@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10000, // 2MB
        maxFileSize = 1024 * 1024 * 10000, // 500MB
        maxRequestSize = 1024 * 1024 * 10000) // 1GB
public class FileUploadServlet extends HttpServlet {
     
    /**
     * Location to save uploaded files on server
     */
    private static final String UPLOAD_PATH = CommonConstants2.DRONE_SURVEY_SAVING_PATH ;
     
    /**
     * Method to get file name from HTTP header content-disposition
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return null;
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            File uploadsPath = new File(UPLOAD_PATH);
            if (!uploadsPath.exists()) {
                //create upload folder if not exist.
                uploadsPath.mkdir();
            }
            for (Part part : request.getParts()) {
                String fileName = getFileName(part);
                if(fileName!=null) {
                    part.write(UPLOAD_PATH + File.separator + fileName);
                }
            }
            System.out.println("File uploaded successfully!");
        } catch (Exception e) {
            System.err.println("Error while uploading files!");
            e.printStackTrace();
        }
    }
 
}