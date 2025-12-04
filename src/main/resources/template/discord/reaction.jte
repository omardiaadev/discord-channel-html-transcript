@import net.dv8tion.jda.api.entities.Message
@import net.dv8tion.jda.api.entities.emoji.Emoji

@param Message message

@if(!message.getReactions().isEmpty())
  <div class="reactions">
    @for(var reaction : message.getReactions())
      <div class="reaction user-select-none">
        @if(reaction.getEmoji().getType().equals(Emoji.Type.CUSTOM))
          <img
            src="${reaction.getEmoji().asCustom().getImageUrl()+"&animated=true"}"
            alt="Reaction"
            loading="lazy"
            class="reaction__emoji size-5"
          />
        @elseif(reaction.getEmoji().getType().equals(Emoji.Type.UNICODE))
          <span class="reaction__emoji size-5">
            &#x${reaction.getEmoji().asUnicode().getAsCodepoints().replace("U+", "")};
          </span>
        @endif

        <span class="reaction__count">${reaction.getCount()}</span>
      </div>
    @endfor
  </div>
@endif