package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book firstBook = new Book(1, "Путешествие на Кон-Тики", 476, "Тур Хейердал");
    private Book secondBook = new Book(2, "Год в Провансе", 168, "Питер Мейл");
    private Book thirdBook = new Book(3, "Вокруг света в восемьдесят дней", 129, "Жюль Верн");
    private Smartphone firstSmart = new Smartphone(4, "Redmi Note 11 Pro", 39990, "Xiaomi");
    private Smartphone secondSmart = new Smartphone(5, "50 Lite", 21990, "Honor");
    private Smartphone thirdSmart = new Smartphone(6, "Nord 2", 45990, "OnePlus");


    @BeforeEach
    void add() {
        manager.add(firstBook);
        manager.add(secondBook);
        manager.add(thirdBook);
        manager.add(firstSmart);
        manager.add(secondSmart);
        manager.add(thirdSmart);
    }

    @Test
    void shouldFindAll() {
        Product[] expected = new Product[]{
                firstBook, secondBook, thirdBook, firstSmart, secondSmart, thirdSmart
        };
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindByAuthorBook() {
        Product[] expected = new Product[]{firstBook};
        Product[] actual = manager.searchBy("Тур Хейердал");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindByNameBook() {
        Product[] expected = new Product[]{secondBook};
        Product[] actual = manager.searchBy("Год в Провансе");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindByMakerSmartphone() {
        Product[] expected = new Product[]{firstSmart};
        Product[] actual = manager.searchBy("Xiaomi");
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldFindByNameSmartphone() {
        Product[] expected = new Product[]{secondSmart};
        Product[] actual = manager.searchBy("50 Lite");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFound() {
        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("Something");
        assertArrayEquals(expected, actual);
    }
}