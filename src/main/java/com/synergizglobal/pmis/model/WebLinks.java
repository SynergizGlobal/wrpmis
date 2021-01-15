package com.synergizglobal.pmis.model;

public class WebLinks {
	private String id,name,link,priority,removableWebLinkIds;
	
	private String[] ids,names,links,prioritys;
	

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public String[] getLinks() {
		return links;
	}

	public void setLinks(String[] links) {
		this.links = links;
	}

	public String[] getPrioritys() {
		return prioritys;
	}

	public void setPrioritys(String[] prioritys) {
		this.prioritys = prioritys;
	}

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getRemovableWebLinkIds() {
		return removableWebLinkIds;
	}

	public void setRemovableWebLinkIds(String removableWebLinkIds) {
		this.removableWebLinkIds = removableWebLinkIds;
	}
	
}
