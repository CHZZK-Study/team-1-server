package foxcord.group.service;

import foxcord.group.dto.request.GroupCreateRequest;
import org.springframework.stereotype.Service;

@Service
public interface GroupService {

    Long createGroup(GroupCreateRequest groupCreateRequest);
}
