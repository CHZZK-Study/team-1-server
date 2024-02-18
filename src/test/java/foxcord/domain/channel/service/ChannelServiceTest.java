package foxcord.domain.channel.service;

import static org.assertj.core.api.Assertions.assertThat;

import foxcord.domain.channel.dto.request.ChannelSaveRequest;
import foxcord.domain.channel.entity.Channel;
import foxcord.domain.channel.entity.ChannelType;
import foxcord.domain.channel.repository.ChannelRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class ChannelServiceTest {

    @Autowired
    ChannelService channelService;

    @Autowired
    ChannelRepository channelRepository;

    @Test
    @DisplayName("채널을 생성한다")
    void createChannel() {
        //given
        ChannelSaveRequest saveRequest = new ChannelSaveRequest("A", "채팅");

        //when
        Long savedId = channelService.create(saveRequest);

        //then
        Optional<Channel> result = channelRepository.findById(savedId);
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo(saveRequest.name());
        assertThat(result.get().getChannelType()).isEqualTo(ChannelType.TEXT);
    }

    @Test
    @DisplayName("채널 목록을 조회한다")
    void findChannels() {
        //given
        Channel channelA = new Channel("A", ChannelType.TEXT);
        Channel channelB = new Channel("B", ChannelType.VOICE);
        channelRepository.save(channelA);
        channelRepository.save(channelB);

        //when
        List<Channel> channels = channelService.findAll();

        //then
        assertThat(channels).hasSize(2)
                .containsExactly(channelA, channelB);
    }
}
