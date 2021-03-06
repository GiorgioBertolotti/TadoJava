package dev.bertolotti.tadojava;

public class ContactDetails {
	private String name;
	private String email;
	private String phone;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public ContactDetails(String name, String email, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "TadoContact [name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
}