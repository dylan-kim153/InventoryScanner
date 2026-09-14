package com.dylankim.inventoryscanner.data.export

import android.content.Context
import android.net.Uri

class CsvFileWriter(
    private val context: Context
) {

    fun write(uri: Uri, csv: String) {
        val outputStream = context.contentResolver.openOutputStream(uri)
            ?: throw IllegalStateException("파일을 열 수 없습니다.")

        outputStream.use {
            it.write("\uFEFF".toByteArray(Charsets.UTF_8))
            it.write(csv.toByteArray(Charsets.UTF_8))
        }
    }
}