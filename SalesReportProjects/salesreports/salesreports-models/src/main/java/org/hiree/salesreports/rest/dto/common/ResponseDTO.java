package org.hiree.salesreports.rest.dto.common;

import org.hiree.salesreports.rest.dto.interfaces.IRestBase;

/**
 * ResponseDTO
 */
public class ResponseDTO implements IRestBase {

	private static final long serialVersionUID = 7353381964158470746L;

	private String title;
	private String content;
	
	private String yesLabel;
	private String noLabel;
	
	public ResponseDTO(String title, String content){
		this.title=title;
		this.content=content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	public String getYesLabel() {
		return yesLabel;
	}

	public void setYesLabel(String yesLabel) {
		this.yesLabel = yesLabel;
	}

	public String getNoLabel() {
		return noLabel;
	}

	public void setNoLabel(String noLabel) {
		this.noLabel = noLabel;
	}

	public ResponseDTO() {
		super();
	}

	
}
