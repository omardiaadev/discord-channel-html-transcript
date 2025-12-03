@import net.dv8tion.jda.api.components.mediagallery.MediaGallery

@param MediaGallery mediaGallery

!{var galleryItems = mediaGallery.getItems();}
!{int currentSize = galleryItems.size();}
!{int offset = 0;}

<div class="media-gallery">
  @if(galleryItems.size() == 4)
    <div class="media-gallery__2x2">
      @for(var item : galleryItems)
        <div class="media-gallery__item">
          @template.html.message.image(src = item.getUrl(), alt = "Media Gallery Item")
        </div>
      @endfor
    </div>
  @elseif(galleryItems.size() == 3)
    <div class="media-gallery__2x1">
      <div class="media-gallery__item">
        @template.html.message.image(src = galleryItems.get(0).getUrl(), alt = "Media Gallery Item")
      </div>

      <div class="media-gallery__2x1__2">
        <div class="media-gallery__item">
          @template.html.message.image(src = galleryItems.get(1).getUrl(), alt = "Media Gallery Item")
        </div>

        <div class="media-gallery__item">
          @template.html.message.image(src = galleryItems.get(2).getUrl(), alt = "Media Gallery Item")
        </div>
      </div>
    </div>
  @else
    @for(int i = 0; i < galleryItems.size(); i++)
      @if((currentSize % 3) == 1)
        <div class="media-gallery__1x1">
          <div class="media-gallery__item">
            @template.html.message.image(src = galleryItems.get(i).getUrl(), alt = "Media Gallery Item")
          </div>
        </div>

        !{currentSize = currentSize - 1;}
      @elseif((currentSize % 3) == 2)
        <div class="media-gallery__1x2">
          <div class="media-gallery__item">
            @template.html.message.image(src = galleryItems.get(i).getUrl(), alt = "Media Gallery Item")
          </div>

          <div class="media-gallery__item">
            @template.html.message.image(src = galleryItems.get(i + 1).getUrl(), alt = "Media Gallery Item")
          </div>
        </div>

        !{currentSize = currentSize - 2;}
        !{i = i + 1;}
      @else
        <div class="media-gallery__3x3">
          @for(var item : galleryItems.subList(i, galleryItems.size()))
            <div class="media-gallery__item">
              @template.html.message.image(src = item.getUrl(), alt = "Media Gallery Item")
            </div>
          @endfor

          !{i = galleryItems.size();}
        </div>
      @endif
    @endfor
  @endif
</div>