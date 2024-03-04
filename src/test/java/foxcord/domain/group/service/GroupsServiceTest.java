package foxcord.domain.group.service;

import static org.assertj.core.api.Assertions.assertThat;

import foxcord.group.dto.request.GroupCreateRequest;
import foxcord.group.entity.Groups;
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
public class GroupsServiceTest {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    @DisplayName("주어진 그룹 id에 해당하는 그룹을 삭제한다.")
    void deleteGroup() {
        // given
        GroupCreateRequest groupCreateRequest = new GroupCreateRequest("멋쟁이그룹");
        Long groupId = groupService.createGroup(groupCreateRequest);

        // when
        groupService.removeGroup(groupId);

        // then
        List<Groups> groups = groupRepository.findAll();
        assertThat(groups.size()).isEqualTo(0);
    }
}
