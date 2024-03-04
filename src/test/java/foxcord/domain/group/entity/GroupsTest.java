package foxcord.domain.group.entity;

import static org.assertj.core.api.Assertions.assertThat;

import foxcord.group.entity.Groups;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupsTest {

    @Test
    @DisplayName("그룹 이름을 통해 그룹을 생성한다.")
    void generateGroup() {
        // given, when
        Groups groups = new Groups("fxGroup");

        // then
        assertThat(groups).isInstanceOf(Groups.class);
        assertThat(groups.getName()).isEqualTo("fxGroup");
    }

    @Test
    @DisplayName("주어진 그룹 이름으로 그룹을 수정한다.")
    void updateGroup() {
        // given
        Groups groups = new Groups("멋쟁이그룹");
        String updateGroupName = "정말멋쟁이그룹";

        // when
        groups.update(updateGroupName);

        // then
        assertThat(groups.getName()).isEqualTo(updateGroupName);
    }

}
