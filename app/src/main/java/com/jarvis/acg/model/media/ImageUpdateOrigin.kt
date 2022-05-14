package com.jarvis.acg.model.media

class ImageUpdateOrigin(image: Image) {
    var id: String = image.id
    var created_at: String? = image.created_at
    var updated_at: String? = image.updated_at

    var imageWidth: Int? = image.imageWidth
    var imageHeight: Int? = image.imageHeight

    var url: String? = image.url
    var order: Int? = image.order
}