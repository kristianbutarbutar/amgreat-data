package com.amgreat.vo;

public class ResponseVO extends VO {
	private ResponseVO next = null;
	public ResponseVO getNext() { return next; }
	public void setNext(ResponseVO next) { this.next = next; }
}
