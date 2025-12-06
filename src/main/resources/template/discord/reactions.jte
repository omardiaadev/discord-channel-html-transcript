@import java.util.List
@import net.dv8tion.jda.api.entities.MessageReaction
@import net.dv8tion.jda.api.entities.emoji.Emoji
@import net.dv8tion.jda.api.entities.emoji.EmojiUnion

@param List<MessageReaction> reactions

@if(!reactions.isEmpty())
  <div class="reactions user-select-none">
    @for(MessageReaction reaction : reactions)
      !{EmojiUnion emoji = reaction.getEmoji();}

      <div class="reaction">
        @if(emoji.getType() == Emoji.Type.CUSTOM)
          <img
            src="${emoji.asCustom().getImageUrl()}&animated=true"
            alt="${emoji.getName()}"
            class="reaction__emoji"
          />
        @elseif(emoji.getType() == Emoji.Type.UNICODE)
          <span class="reaction__emoji">
            &#x${emoji.asUnicode().getAsCodepoints().replace("U+", "")};
          </span>
        @endif

        <span class="reaction__count">${reaction.getCount()}</span>
      </div>
    @endfor
  </div>
@endif
