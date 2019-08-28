package org.hiree.salesreports.util.enums;

public enum IconStylesEnum {
	EDIT_IMG_PLAIN_FA("fa fa-pencil-square-o"),
	DELETE_IMG_TRASH_O("fa fa-trash-o");
	
	private final String text;

	/**
	 * @param text
	 */
	private IconStylesEnum(final String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return text;
	}
}
