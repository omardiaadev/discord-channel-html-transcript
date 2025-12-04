@import dev.omardiaa.transcript.TranscriptConstants
@import dev.omardiaa.transcript.TranscriptUtils
@import net.dv8tion.jda.api.entities.Message

@param Message message

@for(var embed : message.getEmbeds())
  <article
    style="border-left: 0.25rem solid ${"#%1$06X".formatted(embed.getColorRaw())};"
    class="embed"
  >
    @if(embed.getAuthor() != null)
      <div class="embed__author embed--margin">
        @if(embed.getAuthor().getIconUrl() != null)
          <img
            src="${embed.getAuthor().getIconUrl()}"
            alt="Embed Author"
            loading="lazy"
            class="embed__author__icon"
          />
        @endif

        @if(embed.getAuthor().getName() != null)
          <span class="embed__author__name">
            ${embed.getAuthor().getName()}
          </span>
        @endif
      </div>
    @endif

    @if(embed.getTitle() != null)
      <div class="embed__title embed--margin">
        <span>${embed.getTitle()}</span>
      </div>
    @endif

    @if(embed.getDescription() != null)
      <div class="embed__description embed--margin">
        <span>$unsafe{TranscriptUtils.parseMarkup(message.getGuild(), embed.getDescription())}</span>
      </div>
    @endif

    @if(!embed.getFields().isEmpty())
      <div class="embed__fields">
        @for(var field : embed.getFields())
          <div class="embed__field">
            @if(field.getName() != null)
              <div class="embed__field__name">
                <span>${field.getName()}</span>
              </div>
            @endif

            @if(field.getValue() != null)
              <div class="embed__field__value">
                <span>${field.getValue()}</span>
              </div>
            @endif
          </div>
        @endfor
      </div>
    @endif

    @if(embed.getImage() != null)
      <div class="embed__image">
        <img
          src="${embed.getImage().getUrl()}"
          alt="Embed Image"
          loading="lazy"
          class="embed__image__wrapper"
        />
      </div>
    @endif

    @if(embed.getThumbnail() != null)
      <div class="embed__thumbnail">
        <img
          src="${embed.getThumbnail().getUrl()}"
          alt="Embed Thumbnail"
          loading="lazy"
          class="embed__thumbnail__wrapper"
        />
      </div>
    @endif

    @if(embed.getFooter() != null)
      <div class="embed__footer embed--margin">
        @if(embed.getFooter().getIconUrl() != null)
          <img
            src="${embed.getFooter().getIconUrl()}"
            alt="Embed Footer Icon"
            loading="lazy"
            class="embed__footer__icon size-5"
          />
        @endif

        @if(embed.getFooter().getText() != null)
          <span class="embed__footer__text">
            <span>${embed.getFooter().getText()}</span>

            @if(embed.getTimestamp() != null)
              <span class="embed__footer__text__separator">•</span>

              <span>${embed.getTimestamp().format(TranscriptConstants.DATE_SHORT)}</span>
            @endif
          </span>
        @endif
      </div>
    @endif
  </article>
@endfor
