package com.example.demo.model;

import java.util.List;

public class SpecNews {
	private String status;
	private List<SpecSource> sources;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<SpecSource> getSources() {
		return sources;
	}
	public void setSources(List<SpecSource> sources) {
		this.sources = sources;
	}
}
