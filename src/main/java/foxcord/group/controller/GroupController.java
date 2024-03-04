package foxcord.group.controller;

import foxcord.group.dto.request.GroupCreateRequest;
import foxcord.group.dto.request.GroupUpdateRequest;
import foxcord.group.dto.response.GroupResponse;
import foxcord.group.entity.Groups;
import foxcord.group.service.GroupService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<Void> createGroup(@RequestBody GroupCreateRequest groupCreateRequest) {
        Long savedGroupId = groupService.createGroup(groupCreateRequest);
        return ResponseEntity.created(URI.create("/group/" + savedGroupId)).build();
    }

    @PatchMapping("/{groupId}")
    public ResponseEntity<GroupResponse> updateGroup(@PathVariable("groupId") Long groupId,
            @RequestBody GroupUpdateRequest groupUpdateRequest) {
        Groups updatedGroups = groupService.updateGroup(groupId, groupUpdateRequest);
        return ResponseEntity.ok().body(GroupResponse.toDto(updatedGroups));
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable("groupId") Long groupId) {
        groupService.removeGroup(groupId);
        return ResponseEntity.ok().build();
    }
}
