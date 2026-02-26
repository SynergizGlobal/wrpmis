package com.synergizglobal.wrpmis.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synergizglobal.wrpmis.Iservice.P6NewDataService;
import com.synergizglobal.wrpmis.common.DateParser;
import com.synergizglobal.wrpmis.common.FileUploads;
import com.synergizglobal.wrpmis.constants.CommonConstants2;
import com.synergizglobal.wrpmis.model.P6Data;
import com.synergizglobal.wrpmis.model.UDFDataDTO;
import com.synergizglobal.wrpmis.model.UDFDataDTO1;

@RestController
public class TestCtrl {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	Logger logger = Logger.getLogger(TestCtrl.class);

	@Autowired
	P6NewDataService p6newdataService;

	@Value("${template.upload.common.error}")
	public String uploadCommonError;

	@Value("${template.upload.formatError}")
	public String uploadformatError;

	@RequestMapping(value = "/upload-p6-new-data2", method = { RequestMethod.GET })
	public String uploadP6Baseline(@ModelAttribute P6Data p6data, RedirectAttributes attributes,
			HttpSession session) throws Exception {
		//ModelAndView model = new ModelAndView();

	//	model.setViewName("redirect:/p6-new-data");
		System.out.println("entered to file upload ");

		String expectedHeader = "%F\ttask_id\tproj_id\twbs_id\tclndr_id\tphys_complete_pct\t"
				+ "rev_fdbk_flag\test_wt\tlock_plan_flag\tauto_compute_act_flag\t"
				+ "complete_pct_type\ttask_type\tduration_type\tstatus_code\t"
				+ "task_code\ttask_name\trsrc_id\ttotal_float_hr_cnt\tfree_float_hr_cnt\t"
				+ "remain_drtn_hr_cnt\tact_work_qty\tremain_work_qty\t"
				+ "target_work_qty\ttarget_drtn_hr_cnt\ttarget_equip_qty\t"
				+ "act_equip_qty\tremain_equip_qty\tcstr_date\tact_start_date\t"
				+ "act_end_date\tlate_start_date\tlate_end_date\texpect_end_date\t"
				+ "early_start_date\tearly_end_date\trestart_date\treend_date\t"
				+ "target_start_date\ttarget_end_date\trem_late_start_date\trem_late_end_date\t"
				+ "cstr_type\tpriority_type\tsuspend_date\tresume_date\tfloat_path\t"
				+ "float_path_order\tguid\ttmpl_guid\tcstr_date2\tcstr_type2\t"
				+ "driving_path_flag\tact_this_per_work_qty\tact_this_per_equip_qty\t"
				+ "external_early_start_date\texternal_late_end_date\tcreate_date\t"
				+ "update_date\tcreate_user\tupdate_user\tlocation_id\tcrt_path_num";

//		String header = "";
		MultipartFile multipartFile = p6data.getP6dataFile();
		if (multipartFile != null && multipartFile.getSize() > 0) {

			System.out.println("Received file: " + multipartFile.getOriginalFilename());
			System.out.println("File size: " + multipartFile.getSize());

			List<UDFDataDTO> dataDTOs = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(multipartFile.getInputStream(), StandardCharsets.UTF_8))) {
				String line;
				System.out.println("entered buffer ");

				while ((line = reader.readLine()) != null) {
					if (line.contains("%F")) {
						System.out.println("---- Header ----");
						System.out.println(line);
						System.out.println("--------");

					} else if (line.contains("%R")) {
						// Split the row into columns
						String[] columns = line.split("\t");

						UDFDataDTO dataDTO = new UDFDataDTO(columns);

						dataDTOs.add(dataDTO);

					} else if (line.contains("%E")) {
						break;
					}
				}

				// step2:
				Map<String, String> map = getData(dataDTOs);

				// step3:
				List<UDFDataDTO1> data = convertData(map);
				
				
				for(UDFDataDTO1 abc : data) {
					System.out.println(abc);
				}
				
			} catch (IOException e) {
				System.err.println("Error reading the file: " + e.getMessage());
				e.printStackTrace();
			}

		}

		return "Success.....";
	}

	Map<String, String> getData(List<UDFDataDTO> list) {
		Map<String, String> map = new HashMap<String, String>();

		for (UDFDataDTO dataDTO : list) {
			StringBuffer buffer = new StringBuffer(dataDTO.getUdf_type_id());
			buffer.append("_").append(dataDTO.getFk_id()).append("_").append(dataDTO.getProj_id());

			map.put(buffer.toString(), dataDTO.getUdf_text());

		}

		return map;
	}

	List<UDFDataDTO1> convertData(Map<String, String> map) {
		List<UDFDataDTO1> dataDTO1s = new ArrayList<UDFDataDTO1>();

		for (Map.Entry<String, String> entry : map.entrySet()) {

			String key = entry.getKey();

			if (key.startsWith("815_")) {

				UDFDataDTO1 dataDTO1 = new UDFDataDTO1();

				dataDTO1.setFk_id("");
				dataDTO1.setProj_id("");

				String[] keyParts = key.split("_");
				dataDTO1.setFk_id(keyParts[1]);
				dataDTO1.setProj_id(keyParts[2]);
				dataDTO1.setStructure_type_fk(entry.getValue());
				
				String key2 = key.replaceFirst("815_", "816_");
				String key3 = key.replaceFirst("815_", "817_");
				String key4 = key.replaceFirst("815_", "818_");
				
				String structure = map.getOrDefault(key2, "");
				String componentId = map.getOrDefault(key3, "");
				String component = map.getOrDefault(key4, "");
				
				dataDTO1.setStructure(structure);
				dataDTO1.setComponent_id(componentId);
				dataDTO1.setComponent(component);
				dataDTO1s.add(dataDTO1);

			}
			

		}
		
		return dataDTO1s;

	}
}
