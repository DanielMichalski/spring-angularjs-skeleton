package gs.marshall.livesystem.repo;

import gs.marshall.livesystem.entity.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findAll();
	
}
