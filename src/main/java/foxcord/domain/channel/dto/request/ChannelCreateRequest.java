package foxcord.domain.channel.dto.request;

import foxcord.domain.channel.entity.Channel;
import foxcord.domain.channel.entity.ChannelType;

public record ChannelCreateRequest(
        String name,
        String type
) {

    public Channel toEntity() {
        return new Channel(name, ChannelType.from(type));
    }
}
