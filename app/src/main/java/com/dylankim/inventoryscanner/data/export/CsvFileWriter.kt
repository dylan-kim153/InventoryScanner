package com.dylankim.inventoryscanner.data.export

import android.content.Context
import android.net.Uri

class CsvFileWriter(
    private val context: Context
) {

    fun write(uri: Uri, csv: String) {
        context.contentResolver.openOutputStream(uri)?.use { outputStream ->
            outputStream.write(csv.toByteArray(Charsets.UTF_8))
        }
    }
}