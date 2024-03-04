package foxcord.group.dto.response;

import foxcord.group.entity.Groups;

public record GroupResponse(Long id, String groupName) {

    public static GroupResponse toDto(Groups groups) {
        return new GroupResponse(groups.getId(), groups.getName());
    }
}
