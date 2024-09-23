package com.amgreat.vo;

public class AttributeVO extends CommandVO {
	private AttributeVO next = null;
	private String name;
	private String value;
	private String type;
	private String label;
	
	private AttributeVO filter = null;
	private AttributeVO ijoin = null;
	private AttributeVO rjoin = null;
	private AttributeVO ljoin = null;
	
	public String getLabel() { return label; }
	public void setLabel(String label) { this.label = label; }
	public AttributeVO getFilter() { return filter; }
	public void setFilter(AttributeVO filter) { this.filter = filter; }
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
	public AttributeVO getNext() { return next; }
	public AttributeVO setNext(AttributeVO next) { this.next = next; return this.next;}
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }
	
	public AttributeVO addNext() {
		if(this.getNext()!=null) return this.getNext().addNext();
		else {
			this.setNext(new AttributeVO());
			return this.getNext();
		}
	}
	public AttributeVO getIjoin() {
		return ijoin;
	}
	public void setIjoin(AttributeVO ijoin) {
		this.ijoin = ijoin;
	}
	public AttributeVO getRjoin() {
		return rjoin;
	}
	public void setRjoin(AttributeVO rjoin) {
		this.rjoin = rjoin;
	}
	public AttributeVO getLjoin() {
		return ljoin;
	}
	public void setLjoin(AttributeVO ljoin) {
		this.ljoin = ljoin;
	}
}
