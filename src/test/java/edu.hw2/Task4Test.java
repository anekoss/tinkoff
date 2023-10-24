package edu.hw2;

import edu.hw2.Task4.CallingInfo;
import edu.hw2.Task4.Info;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {
    @Test
    void callingInfoTest1() {
        assertThat(Info.callingInfo()).isEqualTo(new CallingInfo(this.getClass().getName(), "callingInfoTest1"));
    }

    @Test
    void callingInfoTest2() {
        assertThat(Info.callingInfo()).isEqualTo(new CallingInfo(this.getClass().getName(), "callingInfoTest2"));
    }

    @Test
    void callingInfoTest3() {
        callingInfoTest1();
        callingInfoTest2();
        assertThat(Info.callingInfo()).isEqualTo(new CallingInfo(this.getClass().getName(), "callingInfoTest3"));
    }
}
