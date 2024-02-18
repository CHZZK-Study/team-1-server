package foxcord.domain.channel.service;

import foxcord.domain.channel.dto.request.ChannelSaveRequest;
import foxcord.domain.channel.entity.Channel;
import foxcord.domain.channel.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;

    @Override
    @Transactional
    public Long create(ChannelSaveRequest saveRequest) {
        Channel savedChannel = channelRepository.save(saveRequest.toEntity());
        return savedChannel.getId();
    }
}
