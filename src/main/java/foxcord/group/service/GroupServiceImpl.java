package foxcord.group.service;

import foxcord.group.dto.request.GroupCreateRequest;
import foxcord.group.dto.request.GroupUpdateRequest;
import foxcord.group.entity.Groups;
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
        Groups groups = groupCreateRequest.toEntity();
        Groups savedGroups = groupRepository.save(groups);
        return savedGroups.getId();
    }

    @Override
    @Transactional
    public Groups updateGroup(Long groupId, GroupUpdateRequest groupUpdateRequest) {
        Groups groups = groupRepository.findById(groupId)
                .orElseThrow(() -> new NoSuchElementException("그룹 아이디에 해당하는 그룹이 존재하지 않습니다."));
        groups.update(groupUpdateRequest.groupName());
        return groups;
    }

    @Override
    @Transactional
    public void removeGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }
}
