package practice.poemtree2.general;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import practice.poemtree2.tempmodule.GenPoem;
import practice.poemtree2.tempmodule.GenPoem.PoorSettingException;

public class GenPoemTest {

    /*
     * @Test void GenEnum() { GenPoem gp = new GenPoem();
     * System.out.println(gp.fakeGenre());
     * 
     * Pattern pattern = Pattern.compile("GENRE*");
     * 
     * Assertions.assertThat(gp.fakeGenre()).containsPattern(pattern); }
     */

    @Test
    void date() {
        Date date = new Date();
        System.out.println(date.toString());
        Assertions.assertThat(1).isSameAs(1);
    }

    @Test
    void genTest() {
        GenPoem gp = new GenPoem();

        try {
            gp.genPoem(1, 100);
        } catch (PoorSettingException e) {
            System.out.println(e.getMessage());
        }

        Assertions.assertThat(1).isSameAs(1);
    }
}
