package com.justinnelson.harmonymod.interactions.commands.customcommands;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageReceivedInteractionEvent {

    String name;
    String messageID;
    Long messageIDLong;
    String channelID;
    Guild guild;
    Message message;
    User author;
    MessageChannelUnion messageChannelUnion;
    Member member;
    ChannelType channelType;
    boolean isFromThread;

    public MessageReceivedInteractionEvent (MessageReceivedEvent event) {
        String[] content = event.getMessage().getContentRaw().split(" ");
        this.setName(content[0].substring(1));
        System.out.println(getName());
        this.setMessage(event.getMessage());
        this.setAuthor(event.getAuthor());
        this.setChannelType(event.getChannelType());
        this.setMessageChannelUnion(event.getChannel());
        this.setMessageID(event.getMessageId());
        this.setMessageIDLong(event.getMessageIdLong());
        this.setGuild(event.getGuild());
        this.setMember(event.getMember());
        this.setFromThread(event.isFromThread());
    }

    public void setName(String name) {this.name = name;}
    public void setMessageID(String messageID) {this.messageID = messageID;}
    public void setMessageIDLong(Long messageIDLong) {this.messageIDLong = messageIDLong;}
    public void setChannelID(String channelID) {this.channelID = channelID;}
    public void setGuild(Guild guild) {this.guild = guild;}
    public void setMessage(Message message) {this.message = message;}
    public void setAuthor(User author) {this.author = author;}
    public void setMessageChannelUnion(MessageChannelUnion messageChannelUnion) {this.messageChannelUnion = messageChannelUnion;}
    public void setMember(Member member) {this.member = member;}
    public void setChannelType(ChannelType channelType) {this.channelType = channelType;}
    public void setFromThread(boolean fromThread) {isFromThread = fromThread;}

    public String getName() {return name;}
    public String getMessageID() {return messageID;}
    public Long getMessageIDLong() {return messageIDLong;}
    public String getChannelID() {return channelID;}
    public Guild getGuild() {return guild;}
    public Message getMessage() {return message;}
    public User getAuthor() {return author;}
    public MessageChannelUnion getMessageChannelUnion() {return messageChannelUnion;}
    public Member getMember() {return member;}
    public ChannelType getChannelType() {return channelType;}
    public boolean getFromThread() {return isFromThread;}
}
