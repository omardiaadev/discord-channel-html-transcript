<h1 align="center">discord-channel-html-transcript</h1>

<p align="center"><strong>Java library to generate easily shareable HTML archives of channels, bringing back the familiar Discord visuals!</strong></p>

<p align="center">
    <a href="https://central.sonatype.com/artifact/dev.skywolfxp/discord-channel-html-transcript"><img alt="Maven Central Version" src="https://img.shields.io/maven-central/v/dev.skywolfxp/discord-channel-html-transcript?style=flat-square&label=MAVEN&color=0055D2&labelColor=0055D2"></a>
    <a href="https://github.com/skywolfxp/discord-channel-html-transcript/blob/main/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/skywolfxp/discord-channel-html-transcript?style=flat-square&label=LICENSE&color=0055D2&labelColor=0055D2"></a>
</p>

## About

This Java library is the **most** up-to-date Transcript generator, displaying your favorite Discord look!\
Uses [**Java Discord API**](https://github.com/discord-jda/JDA)
& [**Java Template Engine**](https://github.com/casid/jte/).


<details>
    <summary>
        <strong>Contents</strong>
    </summary>
    <ol>
        <li><a href="#legal-compliance">Legal Compliance</a></li>
        <li><a href="#features">Features</a></li>
        <li><a href="#requirements">Requirements</a></li>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#development">Development</a></li>
        <li><a href="#socials">Socials</a></li>
    </ol>
</details>

## Legal Compliance

**This project utilizes the [gg sans](https://my.corebook.io/1zObrQ89Q4wHhgFCfYIUhMUvmNf4XjxO/03-typography/download)
font that is the property of Discord Inc.**\
**I do not claim ownership of this font.**

## Features

- **Markdown**
    - Standard Markdown
    - [Discord Chat Formatting](https://support.discord.com/hc/en-us/articles/210298617-Markdown-Text-101-Chat-Formatting-Bold-Italic-Underline)
- **Attachments**
    - Images
    - Other Files
- **Embeds**
- **Action Rows**
    - Buttons
    - Select Menus
- **Reactions**
- **Referenced Message**
- **Referenced Command**

## Requirements

- **Java 21+**

## Installation

### Maven

```xml

<dependencies>
  <dependency>
    <groupId>dev.skywolfxp</groupId>
    <artifactId>discord-channel-html-transcript</artifactId>
    <version>${version}</version>
  </dependency>
</dependencies>
```

### Gradle

```kts
repositories {
  mavenCentral()
}

dependencies {
  implementation("dev.skywolfxp:discord-channel-html-transcript:${version}")
}
```

## Development

1. Run [TranscriptGeneratorTest#createTranscript()](src/test/java/dev/skywolfxp/transcript/TranscriptGeneratorTest.java), A
   `discord-channel-html-transcript` folder will be created under:

    - **Windows:** `%USERPROFILE%\AppData\Local\Temp`
    - **macOS:** `/tmp`
    - **Linux:** `/tmp`

2. Copy the CSS in [style.jte](src/main/resources/template/css/style.jte) to
   `/discord-channel-html-transcript/style.css` and experiment with your own CSS!

## Socials

<a href="https://fiverr.com/skywolfxp"><img alt="Fiverr" src="https://img.shields.io/badge/-1DBF73?style=for-the-badge&logo=fiverr&logoColor=FFFFFF&logoSize=auto"></a>
<a href="https://reddit.com/user/omardiaadev"><img alt="Reddit" src="https://img.shields.io/badge/Reddit-FF4500?style=for-the-badge&logo=reddit&logoColor=FFFFFF&logoSize=auto"></a>
<a href="https://discord.gg/4j7h5q5rts"><img alt="Discord" src="https://img.shields.io/discord/1055244032105787472?style=for-the-badge&logo=discord&logoColor=FFFFFF&logoSize=auto&label=%20&color=5865F2"></a>
