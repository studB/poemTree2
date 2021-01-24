package practice.poemtree2.serviceTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import practice.poemtree2.Poemtree2Application;
import practice.poemtree2.DB.DBConfig;
import practice.poemtree2.Service.ListSetting;
import practice.poemtree2.domain.FavorInfo;
import practice.poemtree2.domain.ListInfo;
import practice.poemtree2.domain.favors.Form;
import practice.poemtree2.domain.favors.Genre;

public class ListSettingTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class);
    AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(Poemtree2Application.class);

    @Test
    void initCallListTest() {

        ListSetting ls = acac.getBean(ListSetting.class);

        FavorInfo fi = new FavorInfo();
        fi.setForm(Form.FORMA.name());
        // fi.setGenre(Genre.GENREA.name());
        for (ListInfo ll : ls.initCallList(fi)) {
            System.out.println(ll.toString());
        }

        Assertions.assertThat(1).isEqualTo(1);

    }
}
