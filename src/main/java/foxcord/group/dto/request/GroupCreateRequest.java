package foxcord.group.dto.request;

import foxcord.group.entity.Groups;

public record GroupCreateRequest(String groupName) {

    public Groups toEntity() {
        return new Groups(groupName);
    }
}
