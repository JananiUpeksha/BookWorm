package config;
import org.example.entity.Books;
import org.example.entity.Books_Users;
import org.example.entity.Branches;
import org.example.entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("hibernate.properties"));
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        Configuration configuration = new Configuration().addProperties(properties).addAnnotatedClass(Users.class).addAnnotatedClass(Books.class).addAnnotatedClass(Branches.class).addAnnotatedClass(Books_Users.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration==null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public  Session getSession(){
        return sessionFactory.openSession();
    }
}
