package foxcord.domain.channel.service;

import foxcord.domain.channel.dto.request.ChannelCreateRequest;
import foxcord.domain.channel.dto.request.ChannelUpdateRequest;
import foxcord.domain.channel.entity.Channel;
import foxcord.domain.channel.repository.ChannelRepository;
import java.util.List;
import java.util.NoSuchElementException;
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
    public Long create(ChannelCreateRequest createRequest) {
        Channel savedChannel = channelRepository.save(createRequest.toEntity());
        return savedChannel.getId();
    }

    @Override
    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long channelId) {
        Channel findChannel = findOne(channelId);
        channelRepository.delete(findChannel);
    }

    @Override
    @Transactional
    public Channel update(Long channelId, ChannelUpdateRequest updateRequest) {
        Channel channel = findOne(channelId);
        channel.update(updateRequest.newName());
        return channel;
    }

    private Channel findOne(Long channelId) {
        return channelRepository.findById(channelId)
                .orElseThrow(() -> new NoSuchElementException("채널이 존재하지 않습니다. [ID] " + channelId));
    }
}
