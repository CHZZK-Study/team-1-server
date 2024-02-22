package foxcord.group.service;

import foxcord.group.dto.request.GroupCreateRequest;
import foxcord.group.dto.request.GroupUpdateRequest;
import foxcord.group.entity.Group;
import org.springframework.stereotype.Service;

@Service
public interface GroupService {

    Long createGroup(GroupCreateRequest groupCreateRequest);

    Group updateGroup(Long groupId, GroupUpdateRequest groupUpdateRequest);
}
