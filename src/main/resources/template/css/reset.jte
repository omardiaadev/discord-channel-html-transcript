<style>
  @layer base {
    *,
    ::after,
    ::before,
    ::backdrop,
    ::file-selector-button {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
      border: 0 solid;
    }

    html,
    :host {
      line-height: 1.5;
      -webkit-text-size-adjust: 100%;
      tab-size: 4;
      -webkit-tap-highlight-color: transparent;
    }

    body {
      line-height: inherit;
    }

    hr {
      height: 0;
      color: inherit;
      border-top-width: 1px;
    }

    abbr:where([title]) {
      -webkit-text-decoration: underline dotted;
      text-decoration: underline dotted;
    }

    h1,
    h2,
    h3,
    h4,
    h5,
    h6 {
      font-size: inherit;
      font-weight: inherit;
    }

    a {
      color: inherit;
      text-decoration: inherit;
    }

    b,
    strong {
      font-weight: bolder;
    }

    code,
    kbd,
    samp,
    pre {
      font-size: 1em;
    }

    small {
      font-size: 80%;
    }

    sub,
    sup {
      font-size: 75%;
      line-height: 0;
      position: relative;
      vertical-align: baseline;
    }

    sub {
      bottom: -0.25em;
    }

    sup {
      top: -0.5em;
    }

    table {
      text-indent: 0;
      border-color: inherit;
      border-collapse: collapse;
    }

    :-moz-focusring {
      outline: auto;
    }

    progress {
      vertical-align: baseline;
    }

    summary {
      display: list-item;
    }

    ol,
    ul,
    menu {
      list-style: none;
    }

    img,
    video {
      max-width: 100%;
      height: auto;
    }

    button,
    input,
    select,
    optgroup,
    textarea,
    ::file-selector-button {
      font: inherit;
      font-feature-settings: inherit;
      font-variation-settings: inherit;
      letter-spacing: inherit;
      color: inherit;
      border-radius: 0;
      background-color: transparent;
      opacity: 1;
    }

    :where(select:is([multiple], [size])) optgroup {
      font-weight: bolder;
    }

    :where(select:is([multiple], [size])) optgroup option {
      padding-inline-start: 20px;
    }

    ::file-selector-button {
      margin-inline-end: 4px;
    }

    ::placeholder {
      opacity: 1;
      color: color-mix(in oklab, currentColor 50%, transparent);
    }

    textarea {
      resize: vertical;
    }

    ::-webkit-search-decoration {
      -webkit-appearance: none;
    }

    ::-webkit-date-and-time-value {
      min-height: 1lh;
      text-align: inherit;
    }

    ::-webkit-datetime-edit-fields-wrapper {
      padding: 0;
    }

    ::-webkit-datetime-edit-year-field,
    ::-webkit-datetime-edit-month-field,
    ::-webkit-datetime-edit-day-field,
    ::-webkit-datetime-edit-hour-field {
      padding-block: 0;
    }

    :-moz-ui-invalid {
      box-shadow: none;
    }

    button,
    input:where([type="button"], [type="reset"], [type="submit"]),
    ::file-selector-button {
      appearance: button;
    }
  }
</style>
