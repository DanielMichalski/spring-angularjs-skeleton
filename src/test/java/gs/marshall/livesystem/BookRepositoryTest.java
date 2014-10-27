package gs.marshall.livesystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gs.marshall.livesystem.entity.Book;
import gs.marshall.livesystem.repo.BookRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-context-test.xml")
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;
	
	@Test
	public void test() {
		Book book = new Book();
		String expectedTitle = "Witcher";
		book.setTitle(expectedTitle);
		
		repository.save(book);
		
		Book bookFromDb = repository.findOne(book.getId());
		assertNotNull(bookFromDb);
		assertEquals(expectedTitle, bookFromDb.getTitle());
	}

}
