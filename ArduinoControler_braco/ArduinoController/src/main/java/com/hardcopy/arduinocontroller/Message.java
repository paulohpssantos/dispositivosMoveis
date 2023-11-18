package com.hardcopy.arduinocontroller;

public class Message {
	/**
	//---------- buffer structure when received
	mDataArray[0] : a
	mDataArray[1] : 1
	mDataArray[2] : 2
	mDataArray[3] : .
	mDataArray[4] : 3
	mDataArray[5] : 4
	...
	mDataArray[n] : z
	
	==> converts into float number : 12.34xxxxxx
	 */
	
	
	public StringBuilder mStringBuffer;
	
	public Message() {
		mStringBuffer = new StringBuilder();
	}
	
	public void initialize() {
		mStringBuffer = new StringBuilder();
	}
	
	public void addChar(char c) {
		if(c < 0x00)
			return;
		if(c == '\n') {
			initialize();
		} else {
			mStringBuffer.append(c);
		}
	}
	
	public String toString() {
		return mStringBuffer.length() > 0 ? mStringBuffer.toString() : "No data";
	}
}
