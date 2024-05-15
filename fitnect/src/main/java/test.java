import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class test {
	public static void main(String[] args) {
		
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
//		List<String> passwords = new ArrayList<>();
//		
//		passwords.add("admin");
//		
//		for (int i = 1; i <= 2; i++) {
//			passwords.add("trainer" + i);
//		}
//		
//		for (int i = 1; i <= 18; i++) {
//			passwords.add("user" + i);
//		}
//		
//		for (String password : passwords) {
//			System.out.println(encoder.encode(password));
//		}
		
		System.out.println(HttpStatus.OK.value());
	}
}
