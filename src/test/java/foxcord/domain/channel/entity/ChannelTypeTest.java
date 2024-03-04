package foxcord.domain.channel.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChannelTypeTest {

    @Test
    @DisplayName("채널 종류가 채팅, 음성이 아닌 경우 예외가 발생한다")
    void makeChannelTypeEx() {
        assertThatThrownBy(() -> ChannelType.from("글자"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("채널 종류를 생성한다")
    void makeChannel() {
        //given,when
        ChannelType text = ChannelType.from("채팅");
        ChannelType voice = ChannelType.from("음성");

        //then
        assertThat(text).isEqualTo(ChannelType.TEXT);
        assertThat(voice).isEqualTo(ChannelType.VOICE);
    }
}
