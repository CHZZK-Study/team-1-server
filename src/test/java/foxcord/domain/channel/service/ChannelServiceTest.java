package foxcord.domain.channel.service;

import static java.lang.Long.MAX_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import foxcord.domain.channel.dto.request.ChannelSaveRequest;
import foxcord.domain.channel.dto.request.ChannelUpdateRequest;
import foxcord.domain.channel.entity.Channel;
import foxcord.domain.channel.entity.ChannelType;
import foxcord.domain.channel.repository.ChannelRepository;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Test
    @DisplayName("채널을 삭제한다")
    void deleteChannel() {
        //given
        Channel channel = new Channel("A", ChannelType.TEXT);
        channelRepository.save(channel);

        //when
        channelService.delete(channel.getId());

        //then
        List<Channel> channels = channelRepository.findAll();
        assertThat(channels).isEmpty();
    }

    @Test
    @DisplayName("존재하지 않은 채널 삭제 시 예외가 발생한다")
    void deleteChannelWithNotExist() {
        assertThatThrownBy(() -> channelService.delete(MAX_VALUE))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("채널 이름을 수정한다")
    void updateChannelName() {
        //given
        Channel channel = new Channel("A", ChannelType.TEXT);
        channelRepository.save(channel);

        ChannelUpdateRequest updateRequest = new ChannelUpdateRequest("B");

        //when
        channelService.update(channel.getId(), updateRequest);

        //then
        assertThat(channel.getName()).isEqualTo(updateRequest.newName());
    }

    @Test
    @DisplayName("존재하지 않은 채널 수정 시 예외가 발생한다")
    void updateChannelWithNotExist() {
        //given
        ChannelUpdateRequest updateRequest = new ChannelUpdateRequest("new");

        //when, then
        assertThatThrownBy(() -> channelService.update(MAX_VALUE, updateRequest))
                .isInstanceOf(NoSuchElementException.class);
    }
}
