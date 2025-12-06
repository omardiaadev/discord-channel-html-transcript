@import java.time.OffsetDateTime
@import dev.omardiaa.transcript.TranscriptConstants

@param OffsetDateTime offsetDateTime

<div class="timestamp">
  <time>${offsetDateTime.format(TranscriptConstants.DATE_FULL)}</time>

  <div class="timestamp__caret">
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 16 10"
      width="16"
      height="10"
      fill="none"
      class="timestamp__caret-fill"
      style="overflow-clip-margin: content-box; overflow: hidden;"
    >
      <path
        d="M10.3426 7.0715C9.14163 8.57272 6.85837 8.57272 5.65739 7.0715L0 -0.000244141L16 -0.000244141L10.3426 7.0715Z"
        class="timestamp__caret-fill"
      />

      <mask
        id="timestamp-caret-mask"
        x="0"
        y="-1"
        width="16"
        height="10"
        style="mask-type: alpha;"
      >
        <path
          d="M10.3426 7.0715C9.14163 8.57272 6.85837 8.57272 5.65739 7.0715L0 -0.000244141L16 -0.000244141L10.3426 7.0715Z"
          class="timestamp__caret-fill"
        />
      </mask>

      <g mask="url(#timestamp-caret-mask)">
        <path
          d="M9.93457 6.84546C8.93433 8.06766 7.06567 8.06766 6.06543 6.84546L0.0546875 -0.500244L15.9453 -0.500244L9.93457 6.84546Z"
          class="timestamp__caret-stroke"
        />
      </g>
    </svg>
  </div>
</div>
