package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import tacos.Client;

@Data
public class RegistrationForm {

	private String username;
	private String password;
	private String fullname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone;

	public Client toUser(PasswordEncoder passwordEncoder) {
		return new Client(username, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
	}

}
