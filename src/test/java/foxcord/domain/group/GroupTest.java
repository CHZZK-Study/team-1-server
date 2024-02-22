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

    @Test
    @DisplayName("주어진 그룹 이름으로 그룹을 수정한다.")
    void updateGroup() {
        // given
        Group group = new Group("멋쟁이그룹");
        String updateGroupName = "정맒멋쟁이그룹";

        // when
        group.update(updateGroupName);

        // then
        assertThat(group.getName()).isEqualTo(updateGroupName);
    }

}
