<h1 align="center">discord-channel-html-transcript</h1>

<p align="center"><strong>Java library to generate easily shareable HTML archives of channels, bringing back the familiar Discord visuals!</strong></p>

<p align="center">
    <a href="https://central.sonatype.com/artifact/dev.skywolfxp/discord-channel-html-transcript"><img alt="Maven Central Version" src="https://img.shields.io/maven-central/v/dev.skywolfxp/discord-channel-html-transcript?style=flat-square&label=MAVEN&color=0055D2&labelColor=0055D2"></a>
    <a href="https://github.com/omardiaadev/discord-channel-html-transcript/blob/main/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/omardiaadev/discord-channel-html-transcript?style=flat-square&label=LICENSE&color=0055D2&labelColor=0055D2"></a>
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

<ul>
    <li>
        <strong>ComponentsV2</strong>
        <img height="12" src="https://img.shields.io/badge/NEW-FF2E2E" alt=""/>
    </li>
    <li><strong>Markdown</strong></li>
    <li><strong>Attachments</strong></li>
    <li><strong>Embeds</strong></li>
    <li><strong>Reactions</strong></li>
    <li><strong>Referenced Message</strong></li>
    <li><strong>Referenced Command</strong></li>
</ul>

## Requirements

- **Java 21+**

## Installation

```xml

<dependencies>
  <dependency>
    <groupId>dev.omardiaa</groupId>
    <artifactId>discord-channel-html-transcript</artifactId>
    <version>${version}</version>
  </dependency>
</dependencies>
```

```kts
repositories {
  mavenCentral()
}

dependencies {
  implementation("dev.omardiaa:discord-channel-html-transcript:${version}")
}
```

## Development

1. Run [TranscriptMockTest#render()](src/test/java/dev/omardiaa/transcript/TranscriptMockTest.java), A
   `discord-channel-html-transcript` folder will be created under:

    - **Windows:** `%USERPROFILE%\AppData\Local\Temp`
    - **macOS:** `/tmp`
    - **Linux:** `/tmp`

2. Use CSS in [style.css](src/test/resources/template/css/style.css) for live updates during development.

3. Copy CSS in [style.css](src/test/resources/template/css/style.css) to [style.jte](src/main/resources/template/style.jte) when done.

## Socials

<a href="https://fiverr.com/skywolfxp"><img alt="Fiverr" src="https://img.shields.io/badge/-1DBF73?style=for-the-badge&logo=fiverr&logoColor=FFF&logoSize=auto"/></a>
<a href="https://reddit.com/user/omardiaadev"><img alt="Reddit" src="https://img.shields.io/badge/Reddit-FF4500?style=for-the-badge&logo=reddit&logoColor=FFF&logoSize=auto"/></a>
<a href="https://discord.gg/4j7h5q5rts"><img alt="Discord" src="https://img.shields.io/discord/1055244032105787472?style=for-the-badge&logo=discord&logoColor=FFF&logoSize=auto&label=%20&color=5865F2"/></a>
