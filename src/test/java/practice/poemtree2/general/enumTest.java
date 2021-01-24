package practice.poemtree2.general;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import practice.poemtree2.domain.favors.Form;

public class enumTest {

    @Test
    void enumString() {
        String f = "FORMA";

        Assertions.assertThat(Form.FORMA.toString()).isSameAs(f);
    }
}
