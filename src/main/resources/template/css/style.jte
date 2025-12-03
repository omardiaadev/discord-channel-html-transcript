<style>
  :root {
    --background-brand: hsl(234.935 85.556% 64.706% /1);
    --text-normal: hsl(240 3.226% 93.922% /1);
    --text-muted: hsl(232.5 3.883% 59.608% /1);
    --header-primary: hsl(0 0% 98.431% /1);
    --mention-foreground: hsl(225.682 100% 82.745% /1);
    --mention-background: hsl(234.935 85.556% 64.706% /0.23921568627450981);
    --button-brand-background: hsl(234.935 85.556% 64.706%);
    --button-brand-background-hover: hsl(233.115 49.194% 51.373% /1);
    --button-secondary-background: hsl(240 4% 60.784% /0.12156862745098039);
    --button-secondary-background-hover: hsl(240 4% 60.784% /0.2);
    --button-positive-background: hsl(145.97 100% 26.275% /1);
    --button-positive-background-hover: hsl(145.082 93.846% 25.49% /1);
    --button-danger-background: hsl(355.636 64.706% 50% /1);
    --button-danger-background-hover: hsl(356.143 63.636% 43.137% /1);
    --text-secondary: hsl(240 4.294% 68.039% /1);
    --brand-500: var(--background-brand);
    --text-link: hsl(212.561 84.536% 61.961% /1);
    --channel-icon: hsl(233.333 3.734% 52.745% /1);
    --border-subtle: var(--button-secondary-background);
    --header-bar-background: var(--background-base-lower);
    --background-base-lower: hsl(240 7.143% 10.98% /1);
    --background-base-lowest: hsl(240 5.263% 7.451% /1);
    --background-code: hsl(234.935 85.556% 64.706% /0.0784313725490196);
    --border-normal: var(--button-secondary-background-hover);
    --background-surface-high: hsl(240 6.494% 15.098% /1);
    --background-mod-subtle: hsl(240 4% 60.784% /0.0784313725490196);
    --background-mod-normal: hsl(240 4% 60.784% /0.1607843137254902);
    --border-faint: hsl(240 4% 60.784% /0.0392156862745098);
    --input-background: hsl(0 0% 0% /0.0784313725490196);
    font-family: "ggsans", "Trebuchet MS", "sans-serif";
  }

  @font-face {
    src: url(https://cdn.jsdelivr.net/gh/skywolfxp/discord-channel-html-transcript@main/src/main/resources/font/ggsansvf-VF.woff);
    font-family: "ggsans";
  }

  body {
    display: flex;
    flex-direction: column;
    background-color: var(--background-base-lower);
  }

  /* Divider */
  .divider {
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1;
    height: 0;
    margin: 1.5rem 0.875rem 0.5rem 1rem;
    border-top: thin solid var(--border-subtle);
  }

  .divider__content {
    display: block;
    padding: 0.125rem 0.25rem;
    background-color: var(--background-base-lower);
    font-size: 0.75rem;
    font-weight: 600;
    line-height: 0.8125rem;
    color: var(--text-muted);
  }

  /* Header */
  .header {
    display: flex;
    flex-direction: column;
    position: sticky;
    z-index: 100;
    top: 0;
    width: 100%;
    color: var(--text-normal);
  }

  /* Footer */
  .footer {
    display: flex;
    justify-content: center;
    width: 100%;
    height: 1.75rem;
    background-color: var(--background-base-lowest);
    border-top: 1px solid var(--border-subtle);
    font-weight: 500;
    line-height: 1.75rem;
    color: var(--text-normal);
  }

  /* Bar */
  .bar {
    display: flex;
    justify-content: center;
    height: 2.125rem;
    background-color: var(--background-base-lowest);
  }

  .bar__guild {
    display: flex;
    align-items: center;
    gap: 0.5rem;
    bottom: 0;
    max-width: 50vw;
  }

  .bar__guild__name {
    overflow: hidden;
    font-size: 0.875rem;
    font-weight: 500;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  /* Channel Header */
  .channel__header {
    display: flex;
    align-items: center;
    height: 2.875rem;
    padding: 0.5rem;
    background-color: var(--header-bar-background);
    border-bottom: 1px solid var(--border-subtle);
    font-size: 1rem;
    line-height: 1.25rem;
  }

  .channel__header__icon {
    fill: var(--channel-icon);
    margin: 0 0.5rem;
  }

  .channel__header__title {
    flex-grow: 1;
    overflow: hidden;
    font-weight: 500;
    color: var(--text-normal);
  }

  .channel__content {
    padding: 0 1rem 1.5rem 1rem;
  }

  /* Author */
  .author {
    display: flex;
    align-items: center;
    gap: 0.25rem;
  }

  .author__avatar {
    width: 2.5rem;
    height: 2.5rem;
    border-radius: 100%;
  }

  .author__avatar__container {
    grid-column: 1;
    width: 3.5rem;
  }

  .author__name {
    overflow: hidden;
    font-size: 1rem;
    font-weight: 500;
    line-height: 1.375rem;
    color: var(--header-primary);
  }

  .author__name:hover {
    text-decoration: underline;
  }

  .author__tag {
    padding: 0 0.275rem;
    background-color: var(--background-brand);
    border-radius: 0.25rem;
    font-size: 0.8rem;
    font-weight: 600;
    line-height: 0.9375rem;
    color: white;
  }

  .author__timestamp {
    display: flex;
    justify-content: center;
    margin-left: 0.25rem;
    cursor: default;
    font-size: 0.75rem;
    font-weight: 500;
    line-height: 0.75rem;
    color: var(--text-muted);
  }

  .author__timestamp:hover > .timestamp {
    display: block;
  }

  /* Message */
  .message {
    display: grid;
    grid-template-columns: auto 1fr;
  }

  .message--different-author {
    align-items: normal;
  }

  .message:has(.message--different-author) {
    padding-top: 1rem;
  }

  /* Message Content */
  .message__content {
    -webkit-user-select: text;
    -moz-user-select: text;
    user-select: text;
    gap: 0.25rem;
    overflow: hidden;
    padding: 0.125rem 0 0.125rem 0;
    font-weight: 400;
    color: var(--text-normal);
  }

  .message__content:has(.message__edited) {
    flex-direction: row;
  }

  /* Message Timestamp */
  .message__timestamp {
    z-index: 1;
    opacity: 0;
    width: 2.5rem;
    height: 1.375rem;
    font-size: 0.6875rem;
    line-height: 1.375rem;
    color: var(--text-muted);
  }

  .message:hover .message__timestamp {
    opacity: 1;
  }

  /* Message Edited */
  .message__edited {
    display: inline-flex;
    justify-content: center;
    font-size: 0.625rem;
    font-weight: 400;
    line-height: 1;
    color: var(--text-muted);
  }

  .message__edited:hover > .timestamp {
    display: block;
  }

  /* Message Reference */
  .reference {
    display: flex;
    align-items: center;
    gap: 0.25rem;
    grid-column: 2;
    height: 1.125rem;
    margin-bottom: 0.25rem;
    font-size: 0.875rem;
    line-height: 1.125rem;
    color: var(--header-primary);
  }

  .reference__spine {
    grid-column: 1;
    margin: 0.5rem 0.25rem 0.25rem 1.125rem;
    border-top: 2px solid #4f545c;
    border-left: 2px solid #4f545c;
    border-radius: 8px 0 0 0;
  }

  .reference__author__avatar {
    width: 1rem;
    height: 1rem;
    border-radius: 100%;
  }

  .reference__author__name {
    opacity: 0.64;
    overflow: hidden;
    font-size: 0.875rem;
    font-weight: 500;
    color: var(--header-primary);
  }

  .reference__author__name:hover {
    text-decoration: underline;
  }

  .reference__message__context {
    display: flex;
    align-items: center;
    gap: 0.25rem;
    opacity: 0.64;
    overflow: hidden;
    max-height: 1.125rem;
    line-height: inherit;
    color: var(--header-primary);
  }

  .reference__message__context:hover {
    opacity: 1;
    cursor: pointer;
  }

  .reference__interaction__content {
    opacity: 0.64;
    color: var(--header-primary);
  }

  .reference__interaction__mention {
    gap: 0.25rem;
    padding: 0 0.125rem;
    background-color: rgba(88, 101, 242, 0.3);
    border-radius: 3px;
    font-weight: 500;
    color: var(--text-link);
  }

  .reference__interaction__mention:hover {
    opacity: 1;
    background-color: var(--background-brand);
    color: white;
  }

  .timestamp {
    box-shadow: 0 2px 20px #00000050;
    display: none;
    align-self: self-end;
    position: absolute;
    z-index: 100;
    margin-bottom: 1.25rem;
    padding: 0.5rem 0.75rem;
    background-color: #111214;
    border-radius: 0.25rem;
    font-size: 0.875rem;
    font-weight: 500;
    line-height: 0.75rem;
    color: var(--text-normal);
  }

  /* Attachment */
  .attachment__image {
    display: flex;
    flex-flow: row nowrap;
    overflow: hidden;
    width: max-content;
    max-width: 34rem;
    height: 100%;
    padding: 0.125rem 0;
    border-radius: 8px;
  }

  .attachment__file {
    display: flex;
    gap: 0.5rem;
    width: 26rem;
    max-width: 100%;
    margin-top: 0.5rem;
    padding: 1rem;
    background-color: var(--background-surface-high);
    border: 1px solid var(--border-subtle);
    border-radius: 8px;
  }

  .attachment__file__wrapper {
    display: flex;
    flex-direction: column;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .attachment__file__name {
    overflow: hidden;
    color: var(--text-link);
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .attachment__file__name:hover {
    text-decoration: underline;
  }

  .attachment__file__size {
    font-size: 0.75rem;
    font-weight: 400;
    line-height: 1.3333333333333333;
    color: var(--text-muted);
  }

  /* Embed */
  .embed {
    display: grid;
    grid-template-rows: auto;
    grid-template-columns: auto min-content;
    overflow: hidden;
    max-width: max-content;
    padding: 0.125rem 1rem 1rem 0.75rem;
    background: var(--background-surface-high);
    border: 1px solid var(--border-subtle);
    border-left: 0.25rem solid var(--border-normal);
    border-radius: 0.25rem;
  }

  .embed--margin {
    margin-top: 0.5rem;
  }

  /* Embed Author */
  .embed__author {
    display: flex;
    align-items: center;
    grid-column: 1/1;
  }

  .embed__author__icon {
    width: 1.5rem;
    height: 1.5rem;
    margin-right: 0.5rem;
    border-radius: 100%;
  }

  .embed__author__name {
    font-size: 0.875rem;
    font-weight: 600;
    color: var(--header-primary);
  }

  /* Embed Title */
  .embed__title {
    display: inline-flex;
    grid-column: 1/1;
    font-size: 1rem;
    font-weight: 600;
    color: var(--header-primary);
  }

  /* Embed Description */
  .embed__description {
    grid-column: 1/1;
    font-size: 0.875rem;
    font-weight: 400;
    line-height: 1.125rem;
    color: var(--text-normal);
  }

  /* Embed Fields */
  .embed__fields {
    display: grid;
    grid-gap: 0.5rem;
    grid-column: 1/1;
    margin-top: 0.5rem;
  }

  .embed__field {
    grid-column: 1 / 13;
    min-width: 0;
    font-size: 0.875rem;
    line-height: 1.125rem;
  }

  .embed__field__name {
    margin-bottom: 0.125rem;
    font-weight: 600;
    color: var(--header-primary);
  }

  .embed__field__value {
    min-width: 0;
    font-size: 0.875rem;
    font-weight: 400;
    line-height: 1.125rem;
    color: var(--text-normal);
  }

  /* Embed Image */
  .embed__image {
    -o-object-fit: fill;
    object-fit: fill;
    grid-column: 1/3;
    margin-top: 1rem;
  }

  .embed__image__wrapper {
    aspect-ratio: 128/128;
    display: flex;
    width: 100%;
    max-width: 128px;
    height: 100%;
  }

  /* Embed Thumbnail */
  .embed__thumbnail {
    -o-object-fit: fill;
    object-fit: fill;
    grid-row: 1/8;
    grid-column: 2/2;
    width: 5rem;
    height: 5rem;
    margin: 0.5rem 0 0 1rem;
  }

  .embed__thumbnail__wrapper {
    aspect-ratio: 128/128;
    overflow: hidden;
    width: 100%;
    height: 100%;
  }

  /* Embed Footer */
  .embed__footer {
    display: flex;
    align-items: center;
    grid-row: auto/auto;
    grid-column: 1/3;
  }

  .embed__footer__icon {
    margin-right: 0.5rem;
    border-radius: 100%;
  }

  .embed__footer__text {
    font-size: 0.75rem;
    font-weight: 500;
    line-height: 1rem;
    color: var(--text-normal);
  }

  .embed__footer__text__separator {
    font-weight: 500;
    color: var(--text-normal);
  }

  /* action-row */
  .action-rows {
    display: flex;
    flex-direction: column;
    row-gap: 0.375rem;
    overflow: hidden;
    width: 100%;
    margin-top: 0.25rem;
    font-weight: 500;
    color: #ffffff;
  }

  .action-row {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
  }

  /* button */
  .button {
    display: flex;
    align-items: center;
    width: auto;
    min-width: 3.75rem;
    height: 2rem;
    padding: 0.125rem 1rem;
    transition: background-color 170ms ease;
    cursor: pointer;
    border-radius: 8px;
    font-size: 0.875rem;
    line-height: 1.375rem;
  }

  .button[aria-disabled="true"] {
    opacity: 0.5;
    cursor: not-allowed;
  }

  .button__emoji {
    width: 1.375rem;
    height: 1.375rem;
    margin-right: 0.25rem;
    font-size: 1rem;
  }

  .button--primary {
    background-color: var(--button-brand-background);
  }

  .button--primary:not([aria-disabled]):hover {
    background-color: var(--button-brand-background-hover);
  }

  .button--secondary {
    background-color: var(--button-secondary-background);
  }

  .button--secondary:not([aria-disabled]):hover {
    background-color: var(--button-secondary-background-hover);
  }

  .button--success {
    background-color: var(--button-positive-background);
  }

  .button--success:not([aria-disabled]):hover {
    background-color: var(--button-positive-background-hover);
  }

  .button--danger {
    background-color: var(--button-danger-background);
  }

  .button--danger:not([aria-disabled]):hover {
    background-color: var(--button-danger-background-hover);
  }

  .button--link {
    background-color: var(--button-secondary-background);
  }

  .button--link:not([aria-disabled]):hover {
    background-color: var(--button-secondary-background-hover);
  }

  .button--link-icon {
    margin-left: 0.5rem;
  }

  /* container */
  .container {
    display: flex;
    flex-direction: column;
    max-width: min(600px, 100%);
    background: var(--background-surface-high);
    border: 1px solid var(--border-subtle);
    border-radius: 0.5rem;
    padding: 1rem;
  }

  /* select-menu */
  .select-menu {
    display: grid;
    align-items: center;
    gap: 0.5rem;
    grid-template-columns: 1fr auto;
    width: 90%;
    max-width: 25rem;
    padding: 0.5rem;
    background-color: var(--input-background);
    cursor: pointer;
    border: 1px solid var(--border-normal);
    border-radius: 8px;
    color: var(--text-secondary);
  }

  /* separator */
  .separator:not([aria-hidden]) {
    border-bottom: 1px solid hsl(240 calc(1 * 4%) 60.784%/0.2);
  }

  .separator--large {
    margin: 8px;
  }

  /* text-display */
  .text-display {
    font-size: .875rem;
    line-height: 1.375rem;
  }

  /* Reactions */
  .reactions {
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    padding-top: 0.125rem;
  }

  .reaction {
    display: flex;
    align-items: center;
    gap: 0.375rem;
    min-height: 1.5rem;
    margin-right: 0.25rem;
    padding: 0.125rem 0.375rem;
    transition: none 0.1s ease;
    transition-property: background-color, border-color;
    background: var(--background-mod-subtle);
    cursor: pointer;
    border: 0.0625rem solid transparent;
    border-radius: 0.5rem;
  }

  .reaction:hover {
    background-color: var(--background-mod-normal);
    border-color: var(--border-faint);
  }

  .reaction__emoji {
    margin: 0.125rem 0;
    line-height: 1.25rem;
  }

  .reaction__count {
    font-weight: 600;
    line-height: 1.25rem;
    color: var(--text-secondary);
    text-align: center;
  }

  /* Custom Utility */
  .user-select-none {
    -webkit-user-select: none;
    -moz-user-select: none;
    user-select: none;
  }

  .size-5 {
    width: 1.25rem;
    height: 1.25rem;
  }

  /* Discord Utility */
  .image__container {
    display: flex;
    flex-flow: row nowrap;
    flex: auto;
    width: 100%;
    height: 100%;
  }

  .media__container {
    margin-top: 0.5rem;
  }

  /* markup */
  .markup__link {
    color: var(--text-link);
  }

  .markup__link:hover {
    text-decoration: underline;
  }

  .markup__code-block {
    white-space-collapse: preserve;
    display: block;
    overflow-x: auto;
    max-width: 90%;
    margin-top: 0.375rem;
    padding: 0.5em;
    background: var(--background-code);
    border: 1px solid var(--border-normal);
    border-radius: 4px;
    font-size: 0.875rem;
    line-height: 1.125rem;
    color: var(--text-normal);
  }

  .markup__code-inline {
    padding: 0 0.2em;
    background: var(--background-code);
    border: 1px solid var(--border-normal);
    border-radius: 4px;
    font-size: 0.875rem;
    line-height: 1.125rem;
  }

  .markup__header {
    display: inline-flex;
    margin: 1rem 0 0.5rem;
    font-weight: 700;
    line-height: 1.375em;
    color: var(--header-primary);
  }

  h1.markup__header {
    font-size: 1.5rem;
  }

  h2.markup__header {
    font-size: 1.25rem;
  }

  h3.markup__header {
    font-size: 1rem;
  }

  .markup__subtext {
    display: block;
    font-size: 0.8125rem;
    line-height: 1.11719rem;
    color: var(--text-muted);
  }

  .mention {
    display: inline-flex;
    align-items: center;
    gap: 0.25rem;
    padding: 0 0.125rem;
    transition: background-color 50ms ease-out, color 50ms ease-out;
    background: var(--mention-background);
    cursor: pointer;
    border-radius: 3px;
    font-weight: 500;
    line-height: 1.375rem;
    color: var(--mention-foreground);
    vertical-align: middle;
  }

  .mention:hover {
    background: var(--brand-500);
    color: white;
  }

  .mention:not([href]) {
    cursor: default;
  }

  .mention__channel-icon {
    width: 1rem;
    height: 1rem;
  }

  .mention__channel-name {
    overflow: hidden;
    text-overflow: ellipsis;
  }
</style>