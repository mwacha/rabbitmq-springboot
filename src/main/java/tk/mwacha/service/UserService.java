package tk.mwacha.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.entities.User;
import tk.mwacha.repositories.UserRepository;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository repository;

	@Transactional(propagation=Propagation.SUPPORTS, readOnly= true)
	public User findByEmail(String email) {
        return repository.findByEmail(email);
    }


	private User getUserByLoginAndPass(String email, String pass) {
		
		User user = findByEmail(email);
		
		if (user != null && new BCryptPasswordEncoder().matches((String) pass, user.getPass())) {
			return user;
        }
		
		return null;
	}


	private String encodePass(String pass){
		return new BCryptPasswordEncoder().encode(pass);
	}

	public String crypto(String senha) {
		return encodePass(senha);
	}
	
}
