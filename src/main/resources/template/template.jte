@import java.util.List
@import net.dv8tion.jda.api.components.actionrow.ActionRow
@import net.dv8tion.jda.api.entities.Message
@import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
@import dev.omardiaa.transcript.TranscriptConstants
@import dev.omardiaa.transcript.TranscriptUtils

@param List<Message> messages
@param TextChannel textChannel
@param String devStyles

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <title>#${textChannel.getName()} | ${textChannel.getGuild().getName()}</title>

  <link
    rel="icon"
    href="data:image/png;base64,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"
  />

  @template.css.reset()

  @if(devStyles != null)
    <link
      rel="stylesheet"
      href="${devStyles}"
    />
  @else
    @template.css.style()
  @endif
</head>

<body>
<div class="header user-select-none">
  <div class="bar">
    <div class="bar__guild">
      <img
        src="${textChannel.getGuild().getIconUrl()}"
        alt="Server Icon"
        class="size-5"
      />

      <div class="bar__guild__name">
        ${textChannel.getGuild().getName()}
      </div>
    </div>
  </div>

  <section class="channel__header">
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 24 24"
      role="img"
      class="channel__header__icon size-5"
    >
      <path
        fill-rule="evenodd"
        d="M10.99 3.16A1 1 0 1 0 9 2.84L8.15 8H4a1 1 0 0 0 0 2h3.82l-.67 4H3a1 1 0 1 0 0 2h3.82l-.8 4.84a1 1 0 0 0 1.97.32L8.85 16h4.97l-.8 4.84a1 1 0 0 0 1.97.32l.86-5.16H20a1 1 0 1 0 0-2h-3.82l.67-4H21a1 1 0 1 0 0-2h-3.82l.8-4.84a1 1 0 1 0-1.97-.32L15.15 8h-4.97l.8-4.84ZM14.15 14l.67-4H9.85l-.67 4h4.97Z"
      />
    </svg>

    <h1 class="channel__header__title">${textChannel.getName()}</h1>
  </section>
</div>

<ol class="channel__content">
  !{var previousMessage = messages.getFirst();}

  @for(var message : messages)
    !{boolean isDifferentAuthor = !previousMessage.getAuthor().equals(message.getAuthor());}
    !{boolean isDividerShown = previousMessage.equals(message) || (previousMessage.getTimeCreated().getDayOfYear() != message.getTimeCreated().getDayOfYear());}

    @template.discord.divider(message = message, isDividerShown = isDividerShown)

    <li class="message">
      @template.discord.reference(message = message)

      <div class="author__avatar__container user-select-none ${(isDifferentAuthor || isDividerShown) ? "message--different-author" : null}">
        @if(isDifferentAuthor || isDividerShown)
          <img
            src="${message.getAuthor().getEffectiveAvatarUrl()}"
            alt="Author Avatar"
            class="author__avatar"
          />
        @else
          <span class="message__timestamp">${message.getTimeCreated().format(TranscriptConstants.TIME_SHORT)}</span>
        @endif
      </div>

      <div>
        @if(isDifferentAuthor || isDividerShown)
          <h3 class="author">
            <a
              href="https://discord.com/users/${message.getAuthor().getId()}"
              class="author__name"
            >
              ${message.getAuthor().getEffectiveName()}
            </a>

            @if(message.getAuthor().isBot())
              <span class="author__tag user-select-none">APP</span>
            @endif

            <time class="author__timestamp">
              <time class="timestamp user-select-none">
                ${message.getTimeCreated().format(TranscriptConstants.DATE_FULL)}
              </time>

              ${message.getTimeCreated().format(TranscriptConstants.DATE_SHORT)}
            </time>
          </h3>
        @endif

        <div
          id="${message.getId()}"
          class="message__content markup"
        >
          $unsafe{TranscriptUtils.parseMarkup(message.getGuild(), message.getContentRaw())}

          @template.discord.attachments(message = message)

          @if(message.getTimeEdited() != null)
            <span class="message__edited user-select-none">
              <time class="timestamp user-select-none">
                ${message.getTimeEdited().format(TranscriptConstants.DATE_FULL)}
              </time>
              (edited)
            </span>
          @endif
        </div>

        @if (!message.getComponents().isEmpty())
          @if(message.isUsingComponentsV2())
            @template.discord.message-component-tree(guild = message.getGuild(), componentTree = message.getComponentTree())
          @else
            @template.discord.embed(message = message)

            @for(var actionRow : message.getComponentTree().findAll(ActionRow.class))
              @template.discord.component.action-row(actionRow = actionRow)
            @endfor
          @endif
        @endif

        @template.discord.reaction(message = message)
      </div>
    </li>
    !{previousMessage = message;}
  @endfor
</ol>

@template.discord.footer()
</body>
</html>
