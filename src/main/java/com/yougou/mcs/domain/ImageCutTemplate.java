package com.yougou.mcs.domain;

import java.util.List;

/**
 * <p>Title: ImageCutTemplate</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年6月13日
 */
public class ImageCutTemplate {
	private String name;
	private List<ImageCutElement> elementList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ImageCutElement> getElementList() {
		return elementList;
	}
	public void setElementList(List<ImageCutElement> elementList) {
		this.elementList = elementList;
	}
}
