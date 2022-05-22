package com.jarvis.acg.model

class BookUpdateOrigin(book: Book) {
    var painter_id_list: ArrayList<String>? = book.painter_id_list
    var publishing_house_id_list: ArrayList<String>? = book.publishing_house_id_list
    var publish_start_date: String? = book.publish_start_date
    var publish_end_date: String? = book.publish_end_date


    var extra_name: Translation? = book.extra_name
    var author_id_list: ArrayList<String>? = book.author_id_list
    var work_id: String? = book.work_id
    var ended: Boolean? = book.ended
    var volume_id_list: ArrayList<String>? = book.volume_id_list

    var id: String = book.id
    var created_at: String? = book.created_at
    var updated_at: String? = book.updated_at
}