package com.zhongzhou.Excavator.Exception;

public class DashBoardServiceException extends RuntimeException {

	private String exceptionId;

	public DashBoardServiceException(String message){
		super(message);
	}

	public DashBoardServiceException(String message,Throwable cause){
		
		super(message,cause);
	}

	public DashBoardServiceException(Throwable cause){
		
		super(cause);
	}

	public String getExceptionId() {
		return exceptionId;
	}

	public void setExceptionId(String exceptionId) {
		this.exceptionId = exceptionId;
	}
}
