package com.ps.common;

import java.io.Serializable;
import java.util.List;

import com.ps.model.Model;

public class JTableList<L> implements Serializable {

	private static final long serialVersionUID = 6695625930049463695L;

	private String Result = "ERROR";
	private List<L> Records;
	private List<JTableCombo> Options; 
	private long TotalRecordCount;
	private Model model;
	private Model Record;
	public String Message;

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	public List<L> getRecords() {
		return Records;
	}

	public void setRecords(List<L> records) {
		Records = records;
	}

	public List<JTableCombo> getOptions() {
		return Options;
	}

	public void setOptions(List<JTableCombo> options) {
		Options = options;
	}

	public long getTotalRecordCount() {
		return TotalRecordCount;
	}

	public void setTotalRecordCount(long totalRecordCount) {
		TotalRecordCount = totalRecordCount;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Model getRecord() {
		return Record;
	}

	public void setRecord(Model record) {
		Record = record;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

}
