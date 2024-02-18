package foxcord.domain.group;

import static org.assertj.core.api.Assertions.assertThat;

import foxcord.group.entity.Group;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GroupTest {

    @Test
    @DisplayName("그룹 이름을 통해 그룹을 생성한다.")
    void generateGroup() {
        // given, when
        Group group = new Group("fxGroup");

        // then
        assertThat(group).isInstanceOf(Group.class);
        assertThat(group.getName()).isEqualTo("fxGroup");
    }

}
