package com.yougou.mcs.base;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Base implements Serializable{
	private static final long serialVersionUID = -5506668983685681106L;
	
	@Override
	public String toString(){
		return  ToStringBuilder.reflectionToString(this);
	}
}
