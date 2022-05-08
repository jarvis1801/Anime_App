package com.jarvis.acg.glide

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import java.io.*

class SecureDecoder(private val bitmapPool: BitmapPool) : ResourceDecoder<File?, Bitmap?> {

    @Throws(IOException::class)
    private fun getStringFromStream(inputStream: InputStream): String {
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        var receiveString: String?
        val stringBuilder = StringBuilder()
        while (bufferedReader.readLine().also { receiveString = it } != null) {
            stringBuilder.append(receiveString)
        }
        inputStream.close()
        return stringBuilder.toString()
    }

    @Throws(IOException::class)
    override fun handles(source: File, options: Options): Boolean {
        val base64EncryptedData = getStringFromStream(FileInputStream(source))
        val decryptedData = String(com.jarvis.acg.util.CipherUtil.decode(base64EncryptedData) ?: byteArrayOf())
        return decryptedData != base64EncryptedData
    }

    @Throws(IOException::class)
    override fun decode(source: File, width: Int, height: Int, options: Options): Resource<Bitmap?>? {
        val base64EncryptedData = getStringFromStream(FileInputStream(source))
        val decryptedData: ByteArray? = com.jarvis.acg.util.CipherUtil.decode(base64EncryptedData)
        return BitmapResource.obtain(BitmapFactory.decodeByteArray(decryptedData, 0, decryptedData?.size ?: 0), bitmapPool)
    }

}