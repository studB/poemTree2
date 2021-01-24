package practice.poemtree2.bean;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import practice.poemtree2.DB.DBConfig;

@SpringBootTest
public class DBBeanSearchTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class);

    @Test
    void allbeans() {
        String[] names = ac.getBeanDefinitionNames();

        System.out.println("getBeanDefinitionNames");
        for (String name : names) {
            System.out.println(name);
        }

        System.out.println("getAliases");
        for (String name : ac.getAliases("DBConfig")) {
            System.out.println(name);
        }

        System.out.println("Print bean itself");
        for (int i = 0; i < names.length; i++) {
            String beanName = names[i];
            System.out.println("[Bean] " + beanName + " : " + ac.getBean(beanName));
        }

        Assertions.assertThat(1).isSameAs(1);
    }

}
