package foxcord.domain.channel.service;

import foxcord.domain.channel.dto.request.ChannelSaveRequest;
import foxcord.domain.channel.entity.Channel;
import foxcord.domain.channel.repository.ChannelRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;

    @Override
    @Transactional
    public Long create(ChannelSaveRequest saveRequest) {
        Channel savedChannel = channelRepository.save(saveRequest.toEntity());
        return savedChannel.getId();
    }

    @Override
    public List<Channel> findAll() {
        return channelRepository.findAll();
    }
}
