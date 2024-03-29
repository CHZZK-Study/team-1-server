package foxcord.domain.channel.controller;

import foxcord.domain.channel.dto.request.ChannelCreateRequest;
import foxcord.domain.channel.dto.request.ChannelUpdateRequest;
import foxcord.domain.channel.dto.response.ChannelResponse;
import foxcord.domain.channel.entity.Channel;
import foxcord.domain.channel.service.ChannelService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<Void> create(@RequestBody ChannelCreateRequest createRequest) {
        Long channelId = channelService.create(createRequest);
        return ResponseEntity.created(URI.create("/channel/" + channelId)).build();
    }

    @GetMapping
    public ResponseEntity<List<ChannelResponse>> getChannels() {
        List<Channel> channels = channelService.findAll();

        List<ChannelResponse> channelResponses = channels.stream()
                .map(ChannelResponse::toDto)
                .toList();

        return ResponseEntity.ok(channelResponses);
    }

    @DeleteMapping("/{channelId}")
    public ResponseEntity<Void> delete(@PathVariable("channelId") Long channelId) {
        channelService.delete(channelId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{channelId}")
    public ResponseEntity<ChannelResponse> update(@PathVariable("channelId") Long channelId,
            @RequestBody ChannelUpdateRequest updateRequest) {
        Channel updatedChannel = channelService.update(channelId, updateRequest);
        ChannelResponse channelResponse = ChannelResponse.toDto(updatedChannel);
        return ResponseEntity.ok(channelResponse);
    }
}
