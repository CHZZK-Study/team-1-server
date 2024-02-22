package foxcord.domain.group;

import static org.assertj.core.api.Assertions.assertThat;

import foxcord.group.entity.Group;
import foxcord.group.repository.GroupRepository;
import foxcord.group.service.GroupService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class GroupTest {

    @Autowired
    GroupService groupService;

    @Autowired
    GroupRepository groupRepository;

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

    @Test
    @DisplayName("주어진 그룹 id에 해당하는 그룹을 삭제한다.")
    void deleteGroup() {
        // given
        Group group = new Group("멋쟁이그룹");
        Group savedGroup = groupRepository.save(group);
        Long groupId = savedGroup.getId();

        // when
        groupRepository.deleteById(groupId);

        // then
        List<Group> groups = groupRepository.findAll();
        assertThat(groups.size()).isEqualTo(0);
    }

}
