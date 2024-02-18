package foxcord.domain.channel.service;

import foxcord.domain.channel.dto.request.ChannelSaveRequest;
import foxcord.domain.channel.entity.Channel;
import java.util.List;

public interface ChannelService {

    Long create(ChannelSaveRequest saveRequest);

    List<Channel> findAll();

    void delete(Long channelId);
}
