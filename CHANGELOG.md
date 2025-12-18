# Changelog

## 4.0.0

### Changed

- **Migrate maven GroupID from `dev.skywolfxp` to `dev.omardiaa`.**
- Add Discord Components V2.
- Add message formatting inside embeds.
- Add `style.css` to test resources for modification during testing.
- Update the Trancript to be returned in a CompletableFuture.

### Fixed

- Fix handling of the generated tanscript output.

## 3.0.0

### Changed

- Remove the ability to construct Transcript with a custom TemplateEngine.

### Fixed

- Fix NPE when loading JTE precompiled classes ([#1](https://github.com/omardiaadev/discord-channel-html-transcript/issues/1))

## 2.0.0

### Changed

- **Migrate the maven GroupID from `io.github.skywolfxp` to `dev.skywolfxp`.**
- Update footer styling.

## 1.1.1

### Changed

- Replace Google's "DM sans" font with Discord's "gg sans" font.
- Update footer styling.

## 1.1.0

### Changed

- Update HTML and CSS to match Discord's new 2025 interface.

### Fixed

- Fix Discord markdown parsing only the first matched mention.
- Fix role mention background and foreground colors.
