package gs.marshall.livesystem.repo;

import gs.marshall.livesystem.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
}
