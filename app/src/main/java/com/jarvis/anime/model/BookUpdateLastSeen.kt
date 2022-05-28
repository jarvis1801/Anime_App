package com.jarvis.anime.model

class BookUpdateLastSeen(book: Book) {
    var id: String = book.id
    var last_volume_id = book.last_volume_id
    var last_chapter_id = book.last_chapter_id
}