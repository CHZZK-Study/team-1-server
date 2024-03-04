package foxcord.group.service;

import foxcord.group.dto.request.GroupCreateRequest;
import foxcord.group.dto.request.GroupUpdateRequest;
import foxcord.group.entity.Groups;
import org.springframework.stereotype.Service;

@Service
public interface GroupService {

    Long createGroup(GroupCreateRequest groupCreateRequest);

    Groups updateGroup(Long groupId, GroupUpdateRequest groupUpdateRequest);

    void removeGroup(Long groupId);
}
