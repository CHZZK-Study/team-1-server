package foxcord.group.dto.response;

import foxcord.group.entity.Group;

public record GroupResponse(Long id, String groupName) {

    public static GroupResponse toDto(Group group) {
        return new GroupResponse(group.getId(), group.getName());
    }
}
