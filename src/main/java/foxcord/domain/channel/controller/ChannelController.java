package foxcord.domain.channel.controller;

import foxcord.domain.channel.dto.request.ChannelSaveRequest;
import foxcord.domain.channel.service.ChannelService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/channel")
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ChannelSaveRequest saveRequest) {
        Long channelId = channelService.create(saveRequest);
        return ResponseEntity.created(URI.create("/channel/" + channelId)).build();
    }
}
