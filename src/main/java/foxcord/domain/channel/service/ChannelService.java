package foxcord.domain.channel.service;

import foxcord.domain.channel.dto.request.ChannelCreateRequest;
import foxcord.domain.channel.dto.request.ChannelUpdateRequest;
import foxcord.domain.channel.entity.Channel;
import java.util.List;

public interface ChannelService {

    Long create(ChannelCreateRequest createRequest);

    List<Channel> findAll();

    void delete(Long channelId);

    Channel update(Long channelId, ChannelUpdateRequest updateRequest);
}
