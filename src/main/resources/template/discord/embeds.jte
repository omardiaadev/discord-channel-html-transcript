@import java.util.List
@import dev.omardiaa.transcript.TranscriptConstants
@import dev.omardiaa.transcript.TranscriptUtils
@import net.dv8tion.jda.api.entities.Guild
@import net.dv8tion.jda.api.entities.MessageEmbed

@param Guild guild
@param List<MessageEmbed> embeds

<%-- TODO: Refactor --%>
@for(MessageEmbed embed : embeds)
  <article
    style="border-left: 0.25rem solid ${"#%1$06X".formatted(embed.getColorRaw())};"
    class="embed"
  >
    !{MessageEmbed.AuthorInfo author = embed.getAuthor();}

    @if(author != null)
      <div class="embed__author embed__margin">
        @if(author.getIconUrl() != null)
          <img
            src="${author.getIconUrl()}"
            alt="Embed Author"
            class="embed__author__icon"
          />
        @endif

        @if(author.getName() != null)
          @if(author.getUrl() != null)
            <a
              href="${author.getUrl()}"
              class="embed__author__name markup__link"
            >
              ${author.getName()}
            </a>
          @else
            <span class="embed__author__name">${author.getName()}</span>
          @endif
        @endif
      </div>
    @endif

    @if(embed.getTitle() != null)
      <div class="embed__title embed__margin">
        @if(embed.getUrl() != null)
          <a
            href="${embed.getUrl()}"
            class="markup__link"
          >
            ${embed.getTitle()}
          </a>
        @else
          <span>${embed.getTitle()}</span>
        @endif
      </div>
    @endif

    @if(embed.getDescription() != null)
      <div class="embed__description embed__margin">
        <span>$unsafe{TranscriptUtils.parseMarkup(guild, embed.getDescription())}</span>
      </div>
    @endif

    @if(!embed.getFields().isEmpty())
      <div class="embed__fields">
        @for(MessageEmbed.Field field : embed.getFields())
          <div class="embed__field" inline="${field.isInline()}">
            @if(field.getName() != null)
              <div class="embed__field__name">
                <span>${field.getName()}</span>
              </div>
            @endif

            @if(field.getValue() == null || field.getValue().isBlank())
              <div class="embed__field__value">
                <span>$unsafe{"&#8203;"}</span>
              </div>
            @else
              <div class="embed__field__value">
                <span>${field.getValue()}</span>
              </div>
            @endif
          </div>
        @endfor
      </div>
    @endif

    @if(embed.getImage() != null)
      <div class="embed__image__wrapper">
        <img
          src="${embed.getImage().getUrl()}"
          alt="Embed Image"
          class="embed__image"
        />
      </div>
    @endif

    @if(embed.getThumbnail() != null)
      <div class="embed__thumbnail__wrapper">
        <img
          src="${embed.getThumbnail().getUrl()}"
          alt="Embed Thumbnail"
          class="embed__thumbnail"
        />
      </div>
    @endif

    @if(embed.getFooter() != null)
      <div class="embed__footer embed__margin">
        @if(embed.getFooter().getIconUrl() != null)
          <img
            src="${embed.getFooter().getIconUrl()}"
            alt="Embed Footer Icon"
            height="20"
            width="20"
            class="embed__footer__icon"
          />
        @endif

        @if(embed.getFooter().getText() != null)
          <span class="embed__footer__text">
            <span>${embed.getFooter().getText()}</span>

            @if(embed.getTimestamp() != null)
              <span>•</span>

              <span>${embed.getTimestamp().format(TranscriptConstants.DATE_SHORT)}</span>
            @endif
          </span>
        @endif
      </div>
    @endif
  </article>
@endfor
