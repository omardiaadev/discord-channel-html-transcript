@import net.dv8tion.jda.api.components.mediagallery.MediaGallery
@import net.dv8tion.jda.api.components.mediagallery.MediaGalleryItem

@param MediaGallery mediaGallery

<div class="image__container" data-count="${mediaGallery.getItems().size()}">
  @for(MediaGalleryItem item : mediaGallery.getItems())
    @template.common.image(src = item.getUrl(), alt = "Media Gallery Item")
  @endfor
</div>
