package foxcord.group.dto.request;

import foxcord.group.entity.Group;

public record GroupCreateRequest(String groupName) {

    public Group toEntity() {
        return new Group(groupName);
    }
}
