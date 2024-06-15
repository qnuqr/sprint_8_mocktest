import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockTest {
    @Mock
    private Cat cat;

    @Mock
    private Dog dog;

    @Test
    public void test() {
        // Стаб, возвращающий половину кота с параметрами - 2 лапы, говорит "Я самый умный"
        Mockito.when(cat.createHalfCat()).thenReturn(new Cat(2, "Я самый умный"));

        // Стаб, возвращающий половину пса с параметрами - 2 лапы, говорит "Я самый весёлый"
        Mockito.when(dog.createHalfDog()).thenReturn(new Dog(2, "Я самый весёлый"));

        // Вызов методов createHalfCat() и createHalfDog()
        Cat halfCat = cat.createHalfCat();
        Dog halfDog = dog.createHalfDog();

        // Создание объекта CatDog
        CatDog catDog = new CatDog(halfCat, halfDog);

        // Проверка, что createHalfCat() вызвался один раз
        Mockito.verify(cat, Mockito.times(1)).createHalfCat();

        // Проверка, что createHalfDog() вызвался один раз
        Mockito.verify(dog, Mockito.times(1)).createHalfDog();

        // Проверка, что у Котопса 4 лапы
        Assert.assertEquals(4, catDog.getLegsCount());

        // Проверка, что Котопёс говорит "Единственный в мире малыш Котопёс"
        Assert.assertEquals("Единственный в мире малыш Котопёс", catDog.getSound());
    }
}
