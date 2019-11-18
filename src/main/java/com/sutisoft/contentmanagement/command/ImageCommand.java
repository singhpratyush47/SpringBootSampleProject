package com.sutisoft.contentmanagement.command;

public class ImageCommand {

	private String base64String;
	private String fileName;
	private String folder;
	private String subfolder;
	public String getBase64String() {
		return base64String;
	}
	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public String getSubfolder() {
		return subfolder;
	}
	public void setSubfolder(String subfolder) {
		this.subfolder = subfolder;
	}
	@Override
	public String toString() {
		return "ImageCommand [base64String=" + base64String + ", fileName=" + fileName + ", folder=" + folder
				+ ", subfolder=" + subfolder + "]";
	}
}
