package foxcord.group.service;

import foxcord.group.dto.request.GroupCreateRequest;
import foxcord.group.entity.Group;
import foxcord.group.repository.GroupRepository;
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
}
