package practice.poemtree2.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class commonTest {

    @Test
    void randomTest() {

        Random rand = new Random();

        int bound = 100;
        List<Integer> index = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            index.add(rand.nextInt(bound));
        }

        System.out.println(index.toString());

        Assertions.assertThat(1).isEqualTo(1);

    }

}
