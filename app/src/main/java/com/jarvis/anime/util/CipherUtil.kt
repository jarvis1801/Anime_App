package com.jarvis.anime.util

import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object CipherUtil {
    private const val AES_PK = "pctqoJFNVzc5vitI4WN1QV6QAzi3oAK+"
    private const val AES_IV = "tTUbDVcfDTl51w=="
    private const val CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding"

    fun decode(content: String): ByteArray? {
        try {
            val secretKey: SecretKey = SecretKeySpec(AES_PK.toByteArray(), "AES")
            val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
            cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(AES_IV.toByteArray()))
            val byteContent: ByteArray = Base64.getDecoder().decode(content)
            return cipher.doFinal(byteContent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}