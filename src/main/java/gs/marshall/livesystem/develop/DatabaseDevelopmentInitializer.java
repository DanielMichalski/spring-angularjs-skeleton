package gs.marshall.livesystem.develop;

import gs.marshall.livesystem.entity.Book;
import gs.marshall.livesystem.entity.User;
import gs.marshall.livesystem.repo.BookRepository;
import gs.marshall.livesystem.repo.UserRepository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Only for development time.
 */
@Component
public class DatabaseDevelopmentInitializer {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	private void init() {
		initUsers();
		
		initBooks();
	}

	private void initUsers() {
		User userExists = userRepository.findByUsername("user");

		if (userExists == null) {
			User userUser = new User("user", passwordEncoder.encode("user"));
			userUser.addRole("user");
			userRepository.save(userUser);
		}

		User adminExists = userRepository.findByUsername("admin");

		if (adminExists == null) {
			User adminUser = new User("admin",
					passwordEncoder.encode("admin"));
			adminUser.addRole("user");
			adminUser.addRole("admin");
			userRepository.save(adminUser);
		}
	}
	
	private void initBooks() {
		long countNumber = bookRepository.count();
		if(countNumber < 10) {
			for(int i = 0; i < 10; i++) {
				Book book = new Book();
				book.setTitle("Book with number " + i);
				bookRepository.save(book);
			}
		}
	}

}