package com.luoyi.android.study.model;

import java.io.Serializable;

public class Demo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 620331188532370541L;
	
	private String name;
	private String desc;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
