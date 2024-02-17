package repositories.factories;

import exceptions.TelecomException;
import repositories.CustomerRepository;
import repositories.Repository;

import java.lang.reflect.InvocationTargetException;

public class RepositoryFactory {

    public static <T extends Repository> T getRepository(Class<T> tClass) throws TelecomException {
        try {
            return tClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new TelecomException();
        }

    }
}
