package com.justinnelson.harmonymod.core;

import static com.justinnelson.harmonymod.core.HarmonyMod.jda;

import com.justinnelson.harmonymod.interactions.commands.customcommands.MessageReceivedInteractionEvent;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.EventProcessor;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.events.GatewayPingEvent;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.RawGatewayEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ReconnectedEvent;
import net.dv8tion.jda.api.events.ResumedEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.StatusChangeEvent;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.channel.ChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.ChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.GenericChannelEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateArchiveTimestampEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateArchivedEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateAutoArchiveDurationEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateBitrateEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateInvitableEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateLockedEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateNSFWEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateParentEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateRegionEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateSlowmodeEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateTopicEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateTypeEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateUserLimitEvent;
import net.dv8tion.jda.api.events.channel.update.GenericChannelUpdateEvent;
import net.dv8tion.jda.api.events.emoji.EmojiAddedEvent;
import net.dv8tion.jda.api.events.emoji.EmojiRemovedEvent;
import net.dv8tion.jda.api.events.emoji.GenericEmojiEvent;
import net.dv8tion.jda.api.events.emoji.update.EmojiUpdateNameEvent;
import net.dv8tion.jda.api.events.emoji.update.EmojiUpdateRolesEvent;
import net.dv8tion.jda.api.events.emoji.update.GenericEmojiUpdateEvent;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.guild.GuildAvailableEvent;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildTimeoutEvent;
import net.dv8tion.jda.api.events.guild.GuildUnavailableEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.events.guild.UnavailableGuildJoinedEvent;
import net.dv8tion.jda.api.events.guild.UnavailableGuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.invite.GenericGuildInviteEvent;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteCreateEvent;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteDeleteEvent;
import net.dv8tion.jda.api.events.guild.member.GenericGuildMemberEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberUpdateEvent;
import net.dv8tion.jda.api.events.guild.member.update.GenericGuildMemberUpdateEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateAvatarEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateBoostTimeEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdatePendingEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateTimeOutEvent;
import net.dv8tion.jda.api.events.guild.override.GenericPermissionOverrideEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideCreateEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideDeleteEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideUpdateEvent;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateAfkChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateAfkTimeoutEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBannerEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBoostCountEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBoostTierEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateCommunityUpdatesChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateDescriptionEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateExplicitContentLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateFeaturesEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateIconEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateLocaleEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMFALevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMaxMembersEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMaxPresencesEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNSFWLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNotificationLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateOwnerEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateRulesChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateSplashEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateSystemChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateVanityCodeEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateVerificationLevelEvent;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceRequestToSpeakEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceStreamEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSuppressEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceVideoEvent;
import net.dv8tion.jda.api.events.http.HttpRequestEvent;
import net.dv8tion.jda.api.events.interaction.GenericAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.ApplicationCommandUpdatePrivilegesEvent;
import net.dv8tion.jda.api.events.interaction.command.ApplicationUpdatePrivilegesEvent;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.GenericContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.GenericPrivilegeUpdateEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericComponentInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEmojiEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.role.GenericRoleEvent;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.events.role.update.GenericRoleUpdateEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateColorEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateHoistedEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateIconEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateMentionableEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateNameEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePositionEvent;
import net.dv8tion.jda.api.events.self.GenericSelfUpdateEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateAvatarEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateMFAEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateNameEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateVerifiedEvent;
import net.dv8tion.jda.api.events.stage.GenericStageInstanceEvent;
import net.dv8tion.jda.api.events.stage.StageInstanceCreateEvent;
import net.dv8tion.jda.api.events.stage.StageInstanceDeleteEvent;
import net.dv8tion.jda.api.events.stage.update.GenericStageInstanceUpdateEvent;
import net.dv8tion.jda.api.events.stage.update.StageInstanceUpdatePrivacyLevelEvent;
import net.dv8tion.jda.api.events.stage.update.StageInstanceUpdateTopicEvent;
import net.dv8tion.jda.api.events.sticker.GenericGuildStickerEvent;
import net.dv8tion.jda.api.events.sticker.GuildStickerAddedEvent;
import net.dv8tion.jda.api.events.sticker.GuildStickerRemovedEvent;
import net.dv8tion.jda.api.events.sticker.update.GenericGuildStickerUpdateEvent;
import net.dv8tion.jda.api.events.sticker.update.GuildStickerUpdateAvailableEvent;
import net.dv8tion.jda.api.events.sticker.update.GuildStickerUpdateDescriptionEvent;
import net.dv8tion.jda.api.events.sticker.update.GuildStickerUpdateNameEvent;
import net.dv8tion.jda.api.events.sticker.update.GuildStickerUpdateTagsEvent;
import net.dv8tion.jda.api.events.thread.GenericThreadEvent;
import net.dv8tion.jda.api.events.thread.ThreadHiddenEvent;
import net.dv8tion.jda.api.events.thread.ThreadRevealedEvent;
import net.dv8tion.jda.api.events.thread.member.GenericThreadMemberEvent;
import net.dv8tion.jda.api.events.thread.member.ThreadMemberJoinEvent;
import net.dv8tion.jda.api.events.thread.member.ThreadMemberLeaveEvent;
import net.dv8tion.jda.api.events.user.GenericUserEvent;
import net.dv8tion.jda.api.events.user.UserActivityEndEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateActivitiesEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateActivityOrderEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateAvatarEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateDiscriminatorEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateFlagsEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateNameEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

public class Listeners extends ListenerAdapter {
    public Listeners() {
        super();
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    void info(String message){ log.info(message); }
    void error(String message){ log.error(message); }
    void debug(String message){ log.debug(message); }
    void trace(String message){ log.trace(message); }
    void warn(String message){ log.warn(message); }

    @Override
    public void onSlashCommandInteraction(@Nonnull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        debug(event.getName());
        HarmonyMod.commandProcessor.process(event);
    }
    @Override
    public void onGenericEvent(@Nonnull GenericEvent event) {
        super.onGenericEvent(event);
    }
    @Override
    public void onGenericUpdate(@Nonnull UpdateEvent<?, ?> event) {
        super.onGenericUpdate(event);
    }
    @Override
    public void onRawGateway(@Nonnull RawGatewayEvent event) {
        super.onRawGateway(event);
    }
    @Override
    public void onGatewayPing(@Nonnull GatewayPingEvent event) {
        super.onGatewayPing(event);
    }
    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        super.onReady(event);
        info("Total Guilds: " + event.getGuildTotalCount());
        info("Available Guilds: " + event.getGuildAvailableCount());
        info("Unavailable Guilds: " + event.getGuildUnavailableCount());
    }
    @Override
    public void onResumed(@Nonnull ResumedEvent event) {
        super.onResumed(event);
    }
    @Override
    public void onReconnected(@Nonnull ReconnectedEvent event) {
        super.onReconnected(event);
    }
    @Override
    public void onDisconnect(@Nonnull DisconnectEvent event) {
        super.onDisconnect(event);
    }
    @Override
    public void onShutdown(@Nonnull ShutdownEvent event) {
        super.onShutdown(event);
        jda.getPresence().setActivity(Activity.playing("Shutting Down"));
    }
    @Override
    public void onStatusChange(@Nonnull StatusChangeEvent event) {
        super.onStatusChange(event);
    }
    @Override
    public void onException(@Nonnull ExceptionEvent event) {
        super.onException(event);
    }
    @Override
    public void onUserContextInteraction(@Nonnull UserContextInteractionEvent event) {
        super.onUserContextInteraction(event);
        HarmonyMod.commandProcessor.process(event);
    }
    @Override
    public void onMessageContextInteraction(@Nonnull MessageContextInteractionEvent event) {
        super.onMessageContextInteraction(event);
        HarmonyMod.commandProcessor.process(event);
    }
    @Override
    public void onButtonInteraction(@Nonnull ButtonInteractionEvent event) {
        super.onButtonInteraction(event);
        HarmonyMod.eventProcessor.process(event);
    }
    @Override
    public void onSelectMenuInteraction(@Nonnull SelectMenuInteractionEvent event) {
        super.onSelectMenuInteraction(event);
        HarmonyMod.eventProcessor.process(event);
    }
    @Override
    public void onCommandAutoCompleteInteraction(@Nonnull CommandAutoCompleteInteractionEvent event) {
        super.onCommandAutoCompleteInteraction(event);
    }
    @Override
    public void onModalInteraction(@Nonnull ModalInteractionEvent event) {
        super.onModalInteraction(event);
        HarmonyMod.eventProcessor.process(event);
    }
    @Override
    public void onUserUpdateName(@Nonnull UserUpdateNameEvent event) {
        super.onUserUpdateName(event);
    }
    @Override
    public void onUserUpdateDiscriminator(@Nonnull UserUpdateDiscriminatorEvent event) {
        super.onUserUpdateDiscriminator(event);
    }
    @Override
    public void onUserUpdateAvatar(@Nonnull UserUpdateAvatarEvent event) {
        super.onUserUpdateAvatar(event);
    }
    @Override
    public void onUserUpdateOnlineStatus(@Nonnull UserUpdateOnlineStatusEvent event) {
        super.onUserUpdateOnlineStatus(event);
    }
    @Override
    public void onUserUpdateActivityOrder(@Nonnull UserUpdateActivityOrderEvent event) {
        super.onUserUpdateActivityOrder(event);
    }
    @Override
    public void onUserUpdateFlags(@Nonnull UserUpdateFlagsEvent event) {
        super.onUserUpdateFlags(event);
    }
    @Override
    public void onUserTyping(@Nonnull UserTypingEvent event) {
        super.onUserTyping(event);
    }
    @Override
    public void onUserActivityStart(@Nonnull UserActivityStartEvent event) {
        super.onUserActivityStart(event);
    }
    @Override
    public void onUserActivityEnd(@Nonnull UserActivityEndEvent event) {
        super.onUserActivityEnd(event);
    }
    @Override
    public void onUserUpdateActivities(@Nonnull UserUpdateActivitiesEvent event) {
        super.onUserUpdateActivities(event);
    }
    @Override
    public void onSelfUpdateAvatar(@Nonnull SelfUpdateAvatarEvent event) {
        super.onSelfUpdateAvatar(event);
    }
    @Override
    public void onSelfUpdateMFA(@Nonnull SelfUpdateMFAEvent event) {
        super.onSelfUpdateMFA(event);
    }
    @Override
    public void onSelfUpdateName(@Nonnull SelfUpdateNameEvent event) {
        super.onSelfUpdateName(event);
    }
    @Override
    public void onSelfUpdateVerified(@Nonnull SelfUpdateVerifiedEvent event) {
        super.onSelfUpdateVerified(event);
    }
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        //TODO set first element of split string array as command name
        if (event.getMessage().getContentStripped().startsWith(HarmonyMod.botConfig.getCustomPrefix())){
            MessageReceivedInteractionEvent newEvent = new MessageReceivedInteractionEvent(event);
            HarmonyMod.commandProcessor.process(newEvent);
        }
    }
    @Override
    public void onMessageUpdate(@Nonnull MessageUpdateEvent event) {
        super.onMessageUpdate(event);

    }
    @Override
    public void onMessageDelete(@Nonnull MessageDeleteEvent event) {
        super.onMessageDelete(event);

    }
    @Override
    public void onMessageBulkDelete(@Nonnull MessageBulkDeleteEvent event) {
        super.onMessageBulkDelete(event);
    }
    @Override
    public void onMessageEmbed(@Nonnull MessageEmbedEvent event) {
        super.onMessageEmbed(event);
    }
    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {
        super.onMessageReactionAdd(event);
    }
    @Override
    public void onMessageReactionRemove(@Nonnull MessageReactionRemoveEvent event) {
        super.onMessageReactionRemove(event);
    }
    @Override
    public void onMessageReactionRemoveAll(@Nonnull MessageReactionRemoveAllEvent event) {
        super.onMessageReactionRemoveAll(event);
    }
    @Override
    public void onMessageReactionRemoveEmoji(@Nonnull MessageReactionRemoveEmojiEvent event) {
        super.onMessageReactionRemoveEmoji(event);
    }
    @Override
    public void onPermissionOverrideDelete(@Nonnull PermissionOverrideDeleteEvent event) {
        super.onPermissionOverrideDelete(event);
    }
    @Override
    public void onPermissionOverrideUpdate(@Nonnull PermissionOverrideUpdateEvent event) {
        super.onPermissionOverrideUpdate(event);
    }
    @Override
    public void onPermissionOverrideCreate(@Nonnull PermissionOverrideCreateEvent event) {
        super.onPermissionOverrideCreate(event);
    }
    @Override
    public void onStageInstanceDelete(@Nonnull StageInstanceDeleteEvent event) {
        super.onStageInstanceDelete(event);
    }
    @Override
    public void onStageInstanceUpdateTopic(@Nonnull StageInstanceUpdateTopicEvent event) {
        super.onStageInstanceUpdateTopic(event);
    }
    @Override
    public void onStageInstanceUpdatePrivacyLevel(@Nonnull StageInstanceUpdatePrivacyLevelEvent event) {
        super.onStageInstanceUpdatePrivacyLevel(event);
    }
    @Override
    public void onStageInstanceCreate(@Nonnull StageInstanceCreateEvent event) {
        super.onStageInstanceCreate(event);
    }
    @Override
    public void onChannelCreate(@Nonnull ChannelCreateEvent event) {
        super.onChannelCreate(event);
    }
    @Override
    public void onChannelDelete(@Nonnull ChannelDeleteEvent event) {
        super.onChannelDelete(event);
    }
    @Override
    public void onChannelUpdateBitrate(@Nonnull ChannelUpdateBitrateEvent event) {
        super.onChannelUpdateBitrate(event);
    }
    @Override
    public void onChannelUpdateName(@Nonnull ChannelUpdateNameEvent event) {
        super.onChannelUpdateName(event);
    }
    @Override
    public void onChannelUpdateNSFW(@Nonnull ChannelUpdateNSFWEvent event) {
        super.onChannelUpdateNSFW(event);
    }
    @Override
    public void onChannelUpdateParent(@Nonnull ChannelUpdateParentEvent event) {
        super.onChannelUpdateParent(event);
    }
    @Override
    public void onChannelUpdatePosition(@Nonnull ChannelUpdatePositionEvent event) {
        super.onChannelUpdatePosition(event);
    }
    @Override
    public void onChannelUpdateRegion(@Nonnull ChannelUpdateRegionEvent event) {
        super.onChannelUpdateRegion(event);
    }
    @Override
    public void onChannelUpdateSlowmode(@Nonnull ChannelUpdateSlowmodeEvent event) {
        super.onChannelUpdateSlowmode(event);
    }
    @Override
    public void onChannelUpdateTopic(@Nonnull ChannelUpdateTopicEvent event) {
        super.onChannelUpdateTopic(event);
    }
    @Override
    public void onChannelUpdateType(@Nonnull ChannelUpdateTypeEvent event) {
        super.onChannelUpdateType(event);
    }
    @Override
    public void onChannelUpdateUserLimit(@Nonnull ChannelUpdateUserLimitEvent event) {
        super.onChannelUpdateUserLimit(event);
    }
    @Override
    public void onChannelUpdateArchived(@Nonnull ChannelUpdateArchivedEvent event) {
        super.onChannelUpdateArchived(event);
    }
    @Override
    public void onChannelUpdateArchiveTimestamp(@Nonnull ChannelUpdateArchiveTimestampEvent event) {
        super.onChannelUpdateArchiveTimestamp(event);
    }
    @Override
    public void onChannelUpdateAutoArchiveDuration(@Nonnull ChannelUpdateAutoArchiveDurationEvent event) {
        super.onChannelUpdateAutoArchiveDuration(event);
    }
    @Override
    public void onChannelUpdateLocked(@Nonnull ChannelUpdateLockedEvent event) {
        super.onChannelUpdateLocked(event);
    }
    @Override
    public void onChannelUpdateInvitable(@Nonnull ChannelUpdateInvitableEvent event) {
        super.onChannelUpdateInvitable(event);
    }
    @Override
    public void onThreadRevealed(@Nonnull ThreadRevealedEvent event) {
        super.onThreadRevealed(event);
    }
    @Override
    public void onThreadHidden(@Nonnull ThreadHiddenEvent event) {
        super.onThreadHidden(event);
    }
    @Override
    public void onThreadMemberJoin(@Nonnull ThreadMemberJoinEvent event) {
        super.onThreadMemberJoin(event);
    }
    @Override
    public void onThreadMemberLeave(@Nonnull ThreadMemberLeaveEvent event) {
        super.onThreadMemberLeave(event);
    }
    @Override
    public void onGuildReady(@Nonnull GuildReadyEvent event) {
        super.onGuildReady(event);
    }
    @Override
    public void onGuildTimeout(@Nonnull GuildTimeoutEvent event) {
        super.onGuildTimeout(event);
    }
    @Override
    public void onGuildJoin(@Nonnull GuildJoinEvent event) {
        super.onGuildJoin(event);
        HarmonyMod.db.checkJoinedGuildExists(event.getGuild());
    }
    @Override
    public void onGuildLeave(@Nonnull GuildLeaveEvent event) {
        super.onGuildLeave(event);
    }
    @Override
    public void onGuildAvailable(@Nonnull GuildAvailableEvent event) {
        super.onGuildAvailable(event);
    }
    @Override
    public void onGuildUnavailable(@Nonnull GuildUnavailableEvent event) {
        super.onGuildUnavailable(event);
    }
    @Override
    public void onUnavailableGuildJoined(@Nonnull UnavailableGuildJoinedEvent event) {
        super.onUnavailableGuildJoined(event);
    }
    @Override
    public void onUnavailableGuildLeave(@Nonnull UnavailableGuildLeaveEvent event) {
        super.onUnavailableGuildLeave(event);
    }
    @Override
    public void onGuildBan(@Nonnull GuildBanEvent event) {
        super.onGuildBan(event);
    }
    @Override
    public void onGuildUnban(@Nonnull GuildUnbanEvent event) {
        super.onGuildUnban(event);
    }
    @Override
    public void onGuildMemberRemove(@Nonnull GuildMemberRemoveEvent event) {
        super.onGuildMemberRemove(event);
    }
    @Override
    public void onGuildUpdateAfkChannel(@Nonnull GuildUpdateAfkChannelEvent event) {
        super.onGuildUpdateAfkChannel(event);
    }
    @Override
    public void onGuildUpdateSystemChannel(@Nonnull GuildUpdateSystemChannelEvent event) {
        super.onGuildUpdateSystemChannel(event);
    }
    @Override
    public void onGuildUpdateRulesChannel(@Nonnull GuildUpdateRulesChannelEvent event) {
        super.onGuildUpdateRulesChannel(event);
    }
    @Override
    public void onGuildUpdateCommunityUpdatesChannel(@Nonnull GuildUpdateCommunityUpdatesChannelEvent event) {
        super.onGuildUpdateCommunityUpdatesChannel(event);
    }
    @Override
    public void onGuildUpdateAfkTimeout(@Nonnull GuildUpdateAfkTimeoutEvent event) {
        super.onGuildUpdateAfkTimeout(event);
    }
    @Override
    public void onGuildUpdateExplicitContentLevel(@Nonnull GuildUpdateExplicitContentLevelEvent event) {
        super.onGuildUpdateExplicitContentLevel(event);
    }
    @Override
    public void onGuildUpdateIcon(@Nonnull GuildUpdateIconEvent event) {
        super.onGuildUpdateIcon(event);
    }
    @Override
    public void onGuildUpdateMFALevel(@Nonnull GuildUpdateMFALevelEvent event) {
        super.onGuildUpdateMFALevel(event);
    }
    @Override
    public void onGuildUpdateName(@Nonnull GuildUpdateNameEvent event) {
        super.onGuildUpdateName(event);
    }
    @Override
    public void onGuildUpdateNotificationLevel(@Nonnull GuildUpdateNotificationLevelEvent event) {
        super.onGuildUpdateNotificationLevel(event);
    }
    @Override
    public void onGuildUpdateOwner(@Nonnull GuildUpdateOwnerEvent event) {
        super.onGuildUpdateOwner(event);
    }
    @Override
    public void onGuildUpdateSplash(@Nonnull GuildUpdateSplashEvent event) {
        super.onGuildUpdateSplash(event);
    }
    @Override
    public void onGuildUpdateVerificationLevel(@Nonnull GuildUpdateVerificationLevelEvent event) {
        super.onGuildUpdateVerificationLevel(event);
    }
    @Override
    public void onGuildUpdateLocale(@Nonnull GuildUpdateLocaleEvent event) {
        super.onGuildUpdateLocale(event);
    }
    @Override
    public void onGuildUpdateFeatures(@Nonnull GuildUpdateFeaturesEvent event) {
        super.onGuildUpdateFeatures(event);
    }
    @Override
    public void onGuildUpdateVanityCode(@Nonnull GuildUpdateVanityCodeEvent event) {
        super.onGuildUpdateVanityCode(event);
    }
    @Override
    public void onGuildUpdateBanner(@Nonnull GuildUpdateBannerEvent event) {
        super.onGuildUpdateBanner(event);
    }
    @Override
    public void onGuildUpdateDescription(@Nonnull GuildUpdateDescriptionEvent event) {
        super.onGuildUpdateDescription(event);
    }
    @Override
    public void onGuildUpdateBoostTier(@Nonnull GuildUpdateBoostTierEvent event) {
        super.onGuildUpdateBoostTier(event);
    }
    @Override
    public void onGuildUpdateBoostCount(@Nonnull GuildUpdateBoostCountEvent event) {
        super.onGuildUpdateBoostCount(event);
    }
    @Override
    public void onGuildUpdateMaxMembers(@Nonnull GuildUpdateMaxMembersEvent event) {
        super.onGuildUpdateMaxMembers(event);
    }
    @Override
    public void onGuildUpdateMaxPresences(@Nonnull GuildUpdateMaxPresencesEvent event) {
        super.onGuildUpdateMaxPresences(event);
    }
    @Override
    public void onGuildUpdateNSFWLevel(@Nonnull GuildUpdateNSFWLevelEvent event) {
        super.onGuildUpdateNSFWLevel(event);
    }
    @Override
    public void onGuildInviteCreate(@Nonnull GuildInviteCreateEvent event) {
        super.onGuildInviteCreate(event);
    }
    @Override
    public void onGuildInviteDelete(@Nonnull GuildInviteDeleteEvent event) {
        super.onGuildInviteDelete(event);
    }
    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        super.onGuildMemberJoin(event);
    }
    @Override
    public void onGuildMemberRoleAdd(@Nonnull GuildMemberRoleAddEvent event) {
        super.onGuildMemberRoleAdd(event);
    }
    @Override
    public void onGuildMemberRoleRemove(@Nonnull GuildMemberRoleRemoveEvent event) {
        super.onGuildMemberRoleRemove(event);
    }
    @Override
    public void onGuildMemberUpdate(@Nonnull GuildMemberUpdateEvent event) {
        super.onGuildMemberUpdate(event);
    }
    @Override
    public void onGuildMemberUpdateNickname(@Nonnull GuildMemberUpdateNicknameEvent event) {
        super.onGuildMemberUpdateNickname(event);
    }
    @Override
    public void onGuildMemberUpdateAvatar(@Nonnull GuildMemberUpdateAvatarEvent event) {
        super.onGuildMemberUpdateAvatar(event);
    }
    @Override
    public void onGuildMemberUpdateBoostTime(@Nonnull GuildMemberUpdateBoostTimeEvent event) {
        super.onGuildMemberUpdateBoostTime(event);
    }
    @Override
    public void onGuildMemberUpdatePending(@Nonnull GuildMemberUpdatePendingEvent event) {
        super.onGuildMemberUpdatePending(event);
    }
    @Override
    public void onGuildMemberUpdateTimeOut(@Nonnull GuildMemberUpdateTimeOutEvent event) {
        super.onGuildMemberUpdateTimeOut(event);
    }
    @Override
    public void onGuildVoiceUpdate(@Nonnull GuildVoiceUpdateEvent event) {
        super.onGuildVoiceUpdate(event);
    }
    @Override
    public void onGuildVoiceJoin(@Nonnull GuildVoiceJoinEvent event) {
        super.onGuildVoiceJoin(event);
    }
    @Override
    public void onGuildVoiceMove(@Nonnull GuildVoiceMoveEvent event) {
        super.onGuildVoiceMove(event);
    }
    @Override
    public void onGuildVoiceLeave(@Nonnull GuildVoiceLeaveEvent event) {
        super.onGuildVoiceLeave(event);
    }
    @Override
    public void onGuildVoiceMute(@Nonnull GuildVoiceMuteEvent event) {
        super.onGuildVoiceMute(event);
    }
    @Override
    public void onGuildVoiceDeafen(@Nonnull GuildVoiceDeafenEvent event) {
        super.onGuildVoiceDeafen(event);
    }
    @Override
    public void onGuildVoiceGuildMute(@Nonnull GuildVoiceGuildMuteEvent event) {
        super.onGuildVoiceGuildMute(event);
    }
    @Override
    public void onGuildVoiceGuildDeafen(@Nonnull GuildVoiceGuildDeafenEvent event) {
        super.onGuildVoiceGuildDeafen(event);
    }
    @Override
    public void onGuildVoiceSelfMute(@Nonnull GuildVoiceSelfMuteEvent event) {
        super.onGuildVoiceSelfMute(event);
    }
    @Override
    public void onGuildVoiceSelfDeafen(@Nonnull GuildVoiceSelfDeafenEvent event) {
        super.onGuildVoiceSelfDeafen(event);
    }
    @Override
    public void onGuildVoiceSuppress(@Nonnull GuildVoiceSuppressEvent event) {
        super.onGuildVoiceSuppress(event);
    }
    @Override
    public void onGuildVoiceStream(@Nonnull GuildVoiceStreamEvent event) {
        super.onGuildVoiceStream(event);
    }
    @Override
    public void onGuildVoiceVideo(@Nonnull GuildVoiceVideoEvent event) {
        super.onGuildVoiceVideo(event);
    }
    @Override
    public void onGuildVoiceRequestToSpeak(@Nonnull GuildVoiceRequestToSpeakEvent event) {
        super.onGuildVoiceRequestToSpeak(event);
    }
    @Override
    public void onRoleCreate(@Nonnull RoleCreateEvent event) {
        super.onRoleCreate(event);
    }
    @Override
    public void onRoleDelete(@Nonnull RoleDeleteEvent event) {
        super.onRoleDelete(event);
    }
    @Override
    public void onRoleUpdateColor(@Nonnull RoleUpdateColorEvent event) {
        super.onRoleUpdateColor(event);
    }
    @Override
    public void onRoleUpdateHoisted(@Nonnull RoleUpdateHoistedEvent event) {
        super.onRoleUpdateHoisted(event);
    }
    @Override
    public void onRoleUpdateIcon(@Nonnull RoleUpdateIconEvent event) {
        super.onRoleUpdateIcon(event);
    }
    @Override
    public void onRoleUpdateMentionable(@Nonnull RoleUpdateMentionableEvent event) {
        super.onRoleUpdateMentionable(event);
    }
    @Override
    public void onRoleUpdateName(@Nonnull RoleUpdateNameEvent event) {
        super.onRoleUpdateName(event);
    }
    @Override
    public void onRoleUpdatePermissions(@Nonnull RoleUpdatePermissionsEvent event) {
        super.onRoleUpdatePermissions(event);
    }
    @Override
    public void onRoleUpdatePosition(@Nonnull RoleUpdatePositionEvent event) {
        super.onRoleUpdatePosition(event);
    }
    @Override
    public void onEmojiAdded(@Nonnull EmojiAddedEvent event) {
        super.onEmojiAdded(event);
    }
    @Override
    public void onEmojiRemoved(@Nonnull EmojiRemovedEvent event) {
        super.onEmojiRemoved(event);
    }
    @Override
    public void onEmojiUpdateName(@Nonnull EmojiUpdateNameEvent event) {
        super.onEmojiUpdateName(event);
    }
    @Override
    public void onEmojiUpdateRoles(@Nonnull EmojiUpdateRolesEvent event) {
        super.onEmojiUpdateRoles(event);
    }
    @Override
    public void onGenericPrivilegeUpdate(@Nonnull GenericPrivilegeUpdateEvent event) {
        super.onGenericPrivilegeUpdate(event);
    }
    @Override
    public void onApplicationCommandUpdatePrivileges(@Nonnull ApplicationCommandUpdatePrivilegesEvent event) {
        super.onApplicationCommandUpdatePrivileges(event);
    }
    @Override
    public void onApplicationUpdatePrivileges(@Nonnull ApplicationUpdatePrivilegesEvent event) {
        super.onApplicationUpdatePrivileges(event);
    }
    @Override
    public void onGuildStickerAdded(@Nonnull GuildStickerAddedEvent event) {
        super.onGuildStickerAdded(event);
    }
    @Override
    public void onGuildStickerRemoved(@Nonnull GuildStickerRemovedEvent event) {
        super.onGuildStickerRemoved(event);
    }
    @Override
    public void onGuildStickerUpdateName(@Nonnull GuildStickerUpdateNameEvent event) {
        super.onGuildStickerUpdateName(event);
    }
    @Override
    public void onGuildStickerUpdateTags(@Nonnull GuildStickerUpdateTagsEvent event) {
        super.onGuildStickerUpdateTags(event);
    }
    @Override
    public void onGuildStickerUpdateDescription(@Nonnull GuildStickerUpdateDescriptionEvent event) {
        super.onGuildStickerUpdateDescription(event);
    }
    @Override
    public void onGuildStickerUpdateAvailable(@Nonnull GuildStickerUpdateAvailableEvent event) {
        super.onGuildStickerUpdateAvailable(event);
    }
    @Override
    public void onHttpRequest(@Nonnull HttpRequestEvent event) {
        super.onHttpRequest(event);
    }
    @Override
    public void onGenericInteractionCreate(@Nonnull GenericInteractionCreateEvent event) {
        super.onGenericInteractionCreate(event);
    }
    @Override
    public void onGenericAutoCompleteInteraction(@Nonnull GenericAutoCompleteInteractionEvent event) {
        super.onGenericAutoCompleteInteraction(event);
    }
    @Override
    public void onGenericComponentInteractionCreate(@Nonnull GenericComponentInteractionCreateEvent event) {
        super.onGenericComponentInteractionCreate(event);
    }
    @Override
    public void onGenericCommandInteraction(@Nonnull GenericCommandInteractionEvent event) {
        super.onGenericCommandInteraction(event);
    }
    @Override
    public void onGenericContextInteraction(@Nonnull GenericContextInteractionEvent<?> event) {
        super.onGenericContextInteraction(event);
    }
    @Override
    public void onGenericMessage(@Nonnull GenericMessageEvent event) {
        super.onGenericMessage(event);
    }
    @Override
    public void onGenericMessageReaction(@Nonnull GenericMessageReactionEvent event) {
        super.onGenericMessageReaction(event);
    }
    @Override
    public void onGenericUser(@Nonnull GenericUserEvent event) {
        super.onGenericUser(event);
    }
    @Override
    public void onGenericUserPresence(@Nonnull GenericUserPresenceEvent event) {
        super.onGenericUserPresence(event);
    }
    @Override
    public void onGenericSelfUpdate(@Nonnull GenericSelfUpdateEvent event) {
        super.onGenericSelfUpdate(event);
    }
    @Override
    public void onGenericStageInstance(@Nonnull GenericStageInstanceEvent event) {
        super.onGenericStageInstance(event);
    }
    @Override
    public void onGenericStageInstanceUpdate(@Nonnull GenericStageInstanceUpdateEvent event) {
        super.onGenericStageInstanceUpdate(event);
    }
    @Override
    public void onGenericChannel(@Nonnull GenericChannelEvent event) {
        super.onGenericChannel(event);
    }
    @Override
    public void onGenericChannelUpdate(@Nonnull GenericChannelUpdateEvent<?> event) {
        super.onGenericChannelUpdate(event);
    }
    @Override
    public void onGenericThread(@Nonnull GenericThreadEvent event) {
        super.onGenericThread(event);
    }
    @Override
    public void onGenericThreadMember(@Nonnull GenericThreadMemberEvent event) {
        super.onGenericThreadMember(event);
    }
    @Override
    public void onGenericGuild(@Nonnull GenericGuildEvent event) {
        super.onGenericGuild(event);
    }
    @Override
    public void onGenericGuildUpdate(@Nonnull GenericGuildUpdateEvent event) {
        super.onGenericGuildUpdate(event);
    }
    @Override
    public void onGenericGuildInvite(@Nonnull GenericGuildInviteEvent event) {
        super.onGenericGuildInvite(event);
    }
    @Override
    public void onGenericGuildMember(@Nonnull GenericGuildMemberEvent event) {
        super.onGenericGuildMember(event);
    }
    @Override
    public void onGenericGuildMemberUpdate(@Nonnull GenericGuildMemberUpdateEvent event) {
        super.onGenericGuildMemberUpdate(event);
    }
    @Override
    public void onGenericGuildVoice(@Nonnull GenericGuildVoiceEvent event) {
        super.onGenericGuildVoice(event);
    }
    @Override
    public void onGenericRole(@Nonnull GenericRoleEvent event) {
        super.onGenericRole(event);
    }
    @Override
    public void onGenericRoleUpdate(@Nonnull GenericRoleUpdateEvent event) {
        super.onGenericRoleUpdate(event);
    }
    @Override
    public void onGenericEmoji(@Nonnull GenericEmojiEvent event) {
        super.onGenericEmoji(event);
    }
    @Override
    public void onGenericEmojiUpdate(@Nonnull GenericEmojiUpdateEvent event) {
        super.onGenericEmojiUpdate(event);
    }
    @Override
    public void onGenericGuildSticker(@Nonnull GenericGuildStickerEvent event) {
        super.onGenericGuildSticker(event);
    }
    @Override
    public void onGenericGuildStickerUpdate(@Nonnull GenericGuildStickerUpdateEvent event) {
        super.onGenericGuildStickerUpdate(event);
    }
    @Override
    public void onGenericPermissionOverride(@Nonnull GenericPermissionOverrideEvent event) {
        super.onGenericPermissionOverride(event);
    }
}
