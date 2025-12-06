@import net.dv8tion.jda.api.entities.User
@import net.dv8tion.jda.api.entities.Message
@import net.dv8tion.jda.api.interactions.InteractionType

@param Message referencedMessage
@param Message.Interaction interaction

@if(referencedMessage != null)
  <div class="reference__spine"></div>

  <div class="reference user-select-none">
    !{User author = referencedMessage.getAuthor();}

    <img
      src="${author.getEffectiveAvatarUrl()}"
      alt="@${author.getName()}"
      class="reference__author__avatar"
    />

    @if(author.isBot())
      <span class="author__tag">APP</span>
    @elseif(author.isBot())
      <span class="author__tag user-select-none">SYSTEM</span>
    @endif

    <a
      href="https://discord.com/users/${author.getId()}"
      class="reference__author__name"
    >
      ${author.getEffectiveName()}
    </a>

    <a
      href="#${referencedMessage.getId()}"
      class="reference__message__context"
    >
      @if(referencedMessage.getContentRaw().isEmpty())
        <i>Click to see attachment</i>
      @else
        ${referencedMessage.getContentRaw()}
      @endif

      @if(!referencedMessage.getAttachments().isEmpty() || referencedMessage.getContentRaw().isEmpty())
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 24 24"
          width="20"
          height="20"
          fill="currentColor"
        >
          <path
            d="M2 5a3 3 0 0 1 3-3h14a3 3 0 0 1 3 3v14a3 3 0 0 1-3 3H5a3 3 0 0 1-3-3V5Zm13.35 8.13 3.5 4.67c.37.5.02 1.2-.6 1.2H5.81a.75.75 0 0 1-.59-1.22l1.86-2.32a1.5 1.5 0 0 1 2.34 0l.5.64 2.23-2.97a2 2 0 0 1 3.2 0ZM10.2 5.98c.23-.91-.88-1.55-1.55-.9a.93.93 0 0 1-1.3 0c-.67-.65-1.78-.01-1.55.9a.93.93 0 0 1-.65 1.12c-.9.26-.9 1.54 0 1.8.48.14.77.63.65 1.12-.23.91.88 1.55 1.55.9a.93.93 0 0 1 1.3 0c.67.65 1.78.01 1.55-.9a.93.93 0 0 1 .65-1.12c.9-.26.9-1.54 0-1.8a.93.93 0 0 1-.65-1.12Z"
            fill-rule="evenodd"
          />
        </svg>
      @endif
    </a>
  </div>
@endif

@if((interaction != null) && interaction.getType().equals(InteractionType.COMMAND))
  <div class="reference__spine"></div>

  <div class="reference user-select-none">
    !{User user = interaction.getUser();}

    <img
      src="${user.getEffectiveAvatarUrl()}"
      alt="@${user.getName()}"
      class="reference__author__avatar"
    />

    <a
      href="https://discord.com/users/${user.getId()}"
      title="@${user.getName()}"
      class="reference__author__name"
    >
      ${user.getEffectiveName()}
    </a>

    <span class="reference__interaction__content">used</span>

    <div class="reference__interaction__mention">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        viewBox="0 0 24 24"
        width="10"
        height="10"
        fill="currentColor"
      >
        <path d="M2.06 7.61c-.25.95.31 1.92 1.26 2.18l4.3 1.15c.94.25 1.91-.31 2.17-1.26l1.15-4.3c.25-.94-.31-1.91-1.26-2.17l-4.3-1.15c-.94-.25-1.91.31-2.17 1.26l-1.15 4.3ZM12.98 7.87a2 2 0 0 0 1.75 2.95H20a2 2 0 0 0 1.76-2.95l-2.63-4.83a2 2 0 0 0-3.51 0l-2.63 4.83ZM5.86 13.27a.89.89 0 0 1 1.28 0l.75.77a.9.9 0 0 0 .54.26l1.06.12c.5.06.85.52.8 1.02l-.13 1.08c-.02.2.03.42.14.6l.56.92c.27.43.14 1-.28 1.26l-.9.58a.92.92 0 0 0-.37.48l-.36 1.02a.9.9 0 0 1-1.15.57l-1-.36a.89.89 0 0 0-.6 0l-1 .36a.9.9 0 0 1-1.15-.57l-.36-1.02a.92.92 0 0 0-.37-.48l-.9-.58a.93.93 0 0 1-.28-1.26l.56-.93c.11-.17.16-.38.14-.59l-.12-1.08c-.06-.5.3-.96.8-1.02l1.05-.12a.9.9 0 0 0 .54-.26l.75-.77ZM18.52 13.71a1.1 1.1 0 0 0-2.04 0l-.46 1.24c-.19.5-.57.88-1.07 1.07l-1.24.46a1.1 1.1 0 0 0 0 2.04l1.24.46c.5.19.88.57 1.07 1.07l.46 1.24c.35.95 1.7.95 2.04 0l.46-1.24c.19-.5.57-.88 1.07-1.07l1.24-.46a1.1 1.1 0 0 0 0-2.04l-1.24-.46a1.8 1.8 0 0 1-1.07-1.07l-.46-1.24Z"/>
      </svg>

      <span>${interaction.getName()}</span>
    </div>
  </div>
@endif
