package foxcord.domain.channel.entity;

public enum ChannelType {
    TEXT("채팅"),
    VOICE("음성");

    private final String type;

    ChannelType(String type) {
        this.type = type;
    }
}
