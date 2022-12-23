package fsoft.jits.mybatis.dto;

public class ChangePassDTO {
	private int id;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	
	public ChangePassDTO() {
		super();
	}
	public ChangePassDTO(int id, String oldPassword, String newPassword, String confirmPassword) {
		super();
		this.id = id;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
