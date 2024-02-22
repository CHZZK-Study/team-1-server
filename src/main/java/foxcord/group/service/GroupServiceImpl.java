package foxcord.group.service;

import foxcord.group.dto.request.GroupCreateRequest;
import foxcord.group.dto.request.GroupUpdateRequest;
import foxcord.group.entity.Group;
import foxcord.group.repository.GroupRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    @Transactional
    public Long createGroup(GroupCreateRequest groupCreateRequest) {
        Group group = groupCreateRequest.toEntity();
        Group savedGroup = groupRepository.save(group);
        return savedGroup.getId();
    }

    @Override
    @Transactional
    public Group updateGroup(Long groupId, GroupUpdateRequest groupUpdateRequest) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new NoSuchElementException("그룹 아이디에 해당하는 그룹이 존재하지 않습니다."));
        group.update(groupUpdateRequest.groupName());
        return group;
    }

    @Override
    @Transactional
    public void removeGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }
}
