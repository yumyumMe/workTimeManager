package com.example.demo.model;

import javax.validation.constraints.NotBlank;

public class WorkForm {

	public int workId;

	@NotBlank(message="※開始時間を入力してください")
	public String startTime;

	@NotBlank(message="※終了時間を入力してください")
	public String endTime;

	public int workKbn;

	public String workDetail;

	public String other;

	public String registDate;

	public int getWorkId() {
		return workId;
	}

	public void setWorkId(int workId) {
		this.workId = workId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getWorkKbn() {
		return workKbn;
	}

	public void setWorkKbn(int workKbn) {
		this.workKbn = workKbn;
	}

	public String getWorkDetail() {
		return workDetail;
	}

	public void setWorkDetail(String workDetail) {
		this.workDetail = workDetail;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

}
