@import java.util.List
@import net.dv8tion.jda.api.entities.MessageReaction
@import net.dv8tion.jda.api.entities.emoji.CustomEmoji
@import net.dv8tion.jda.api.entities.emoji.UnicodeEmoji

@param List<MessageReaction> reactions

@if(!reactions.isEmpty())
  <div class="reactions user-select-none">
    @for(MessageReaction reaction : reactions)
      <div class="reaction">
        @if(reaction.getEmoji() instanceof CustomEmoji customEmoji)
          <img
            src="${customEmoji.getImageUrl()}"
            alt="Custom Emoji"
            title="${customEmoji.getName()}"
            class="reaction__emoji"
          />
        @elseif(reaction.getEmoji() instanceof UnicodeEmoji unicodeEmoji)
          <span class="reaction__emoji">&#x${unicodeEmoji.getAsCodepoints().replace("U+", "")};</span>
        @endif

        <span class="reaction__count">${reaction.getCount()}</span>
      </div>
    @endfor
  </div>
@endif
