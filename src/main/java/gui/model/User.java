package gui.model;

public class User {

	private Long id;
	private String role;
	private boolean confirm;
	private String forbidden;
	
	public User() {
		
	}

	public User(Long id, String role, boolean confirm, String forbidden) {
		this.id = id;
		this.role = role;
		this.confirm = confirm;
		this.forbidden = forbidden;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getForbidden() {
		return forbidden;
	}
	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", role='" + role + '\'' +
				", confirm=" + confirm +
				", forbidden='" + forbidden + '\'' +
				'}';
	}
}
