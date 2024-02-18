package foxcord.domain.channel.entity;

import java.util.Arrays;

public enum ChannelType {
    TEXT("채팅"),
    VOICE("음성");

    private final String type;

    ChannelType(String type) {
        this.type = type;
    }

    public static ChannelType from(String type) {
        return Arrays.stream(ChannelType.values())
                .filter(channelType -> channelType.type.equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 채팅 종류입니다."));
    }
}
