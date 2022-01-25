package com.synergizglobal.pmis.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class OverviewDashboard {
	
	private String id,name,icon,link_url;
	private List<OverviewDashboard> formsSubMenu;
	
	private String [] ids,parentids,orderids,parentidsorder;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLink_url() {
		return link_url;
	}

	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}

	public List<OverviewDashboard> getFormsSubMenu() {
		return formsSubMenu;
	}

	public void setFormsSubMenu(List<OverviewDashboard> formsSubMenu) {
		this.formsSubMenu = formsSubMenu;
	}

	public String [] getIds() {
		return ids;
	}

	public void setIds(String [] ids) {
		this.ids = ids;
	}

	public String [] getParentids() {
		return parentids;
	}

	public void setParentids(String [] parentids) {
		this.parentids = parentids;
	}

	public String [] getOrderids() {
		return orderids;
	}

	public void setOrderids(String [] orderids) {
		this.orderids = orderids;
	}

	public String [] getParentidsorder() {
		return parentidsorder;
	}

	public void setParentidsorder(String [] parentidsorder) {
		this.parentidsorder = parentidsorder;
	}
	
}
