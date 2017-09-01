package com.yougou.mcs.domain;

/**
 * <p>Title: ImageCutElement</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年6月13日
 */
public class ImageCutElement {
	// 元素指定标号
	private int code;
	// 元素定义名称
	private String name;
	// 元素分配空间
	private ImageGeometry geometry;
	// 缩放比例
	private double sacle;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ImageGeometry getGeometry() {
		return geometry;
	}
	public void setGeometry(ImageGeometry geometry) {
		this.geometry = geometry;
	}
	public double getSacle() {
		return sacle;
	}
	public void setSacle(double sacle) {
		this.sacle = sacle;
	}
} 
