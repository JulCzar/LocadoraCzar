package br.czar.model;

import java.util.ArrayList;
import java.util.List;

public class Tags {
	List<String> list;
	
	public Tags() {
		this.list = new ArrayList<>();
	}
	
	public Tags(String tags) {
		String[] tagList = tags.split(";");
		this.list = new ArrayList<>();
		
		for (String s : tagList)
			list.add(s.trim());
	}
	
	public Tags(String ...tags) {
		for (String s : tags)
			list.add(s.trim());
	}

	@Override
	public String toString() {
		StringBuffer tagString = new StringBuffer();
		
		boolean isFirst = true;
		for (String s : list) {
			if (!isFirst)
				tagString.append(", ");
			
			tagString.append(s);
			
			isFirst = false;
		}
		
		return tagString.toString();
	}
	public String stringify() {
		StringBuffer tagString = new StringBuffer();
		
		boolean isFirst = true;
		for (String s : list) {
			if (!isFirst)
				tagString.append("; ");
			
			tagString.append(s);
			
			isFirst = false;
		}
		
		return tagString.toString();
	}
	
}