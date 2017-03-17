package com.wxs.ck.vo;

/**
 * @author FujiRen
 * @date 2017/3/15
 */
public class Student {
	private String name;
	private String sequence;
	private String comments;

	public Student() {
	}

	public Student(String name, String sequence, String comments) {
		this.name = name;
		this.sequence = sequence;
		this.comments = comments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
