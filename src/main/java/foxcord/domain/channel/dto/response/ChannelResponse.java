package foxcord.domain.channel.dto.response;

import foxcord.domain.channel.entity.Channel;

public record ChannelResponse(
        Long id,
        String name,
        String type
) {

    public static ChannelResponse toDto(Channel channel) {
        return new ChannelResponse(channel.getId(), channel.getName(),
                channel.getChannelType().getType());
    }
}
