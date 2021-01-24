package practice.poemtree2.bean;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import practice.poemtree2.Poemtree2Application;
import practice.poemtree2.Service.ListSetting;

public class ComponentBeanTest {

    @Test
    void getListSettingBean() {
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(Poemtree2Application.class);

        ListSetting ls = acac.getBean(ListSetting.class);

        Assertions.assertThat(ls.getClass()).isEqualTo(ListSetting.class);
    }

}
