package foxcord.domain.channel.service;

import foxcord.domain.channel.dto.request.ChannelSaveRequest;

public interface ChannelService {

    Long create(ChannelSaveRequest saveRequest);
}
