package foxcord.group.controller;

import foxcord.group.dto.request.GroupCreateRequest;
import foxcord.group.service.GroupService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
}
