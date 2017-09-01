package com.yougou.mcs.domain;

import java.util.List;

/**
 * <p>Title: ImageTemplate</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年6月13日
 */
public class ImageTemplate {
	private String name;
	private String templateImagePath;
	private List<ImageElement> elementList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemplateImagePath() {
		return templateImagePath;
	}
	public void setTemplateImagePath(String templateImagePath) {
		this.templateImagePath = templateImagePath;
	}
	public List<ImageElement> getElementList() {
		return elementList;
	}
	public void setElementList(List<ImageElement> elementList) {
		this.elementList = elementList;
	}
}
