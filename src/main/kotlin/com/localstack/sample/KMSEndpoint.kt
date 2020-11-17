package com.localstack.sample

import com.amazonaws.services.kms.AWSKMS
import com.amazonaws.services.kms.model.DecryptRequest
import com.amazonaws.services.kms.model.DecryptResult
import com.amazonaws.services.kms.model.EncryptRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.ByteBuffer
import java.util.Base64


@RestController
@RequestMapping("/kms")
class KMSEndpoint(val kmsClient: AWSKMS) {

    @PostMapping("/encrypt/{data}")
    fun encrypt(@RequestBody request: KMSData): String {
        val byteBuffer = getByteBuffer(request.data)
        val encryptRequest = EncryptRequest()
                .withKeyId(KEY_ID)
                .withPlaintext(byteBuffer)
        val encryptResult = kmsClient.encrypt(encryptRequest)
        return getString(Base64.getEncoder().encode(encryptResult.ciphertextBlob))
    }

    @PostMapping("/decrypt/{data}")
    fun decrypt(@RequestBody request: KMSData): String {
        val buffer = getByteBuffer(request.data)
        val decryptRequest = DecryptRequest()
                .withKeyId(KEY_ID)
                .withCiphertextBlob(buffer)
        val decryptResult: DecryptResult = kmsClient.decrypt(decryptRequest)
        return getString(decryptResult.plaintext)
    }

    private fun getString(byteBuffer: ByteBuffer): String {
        val bytes = ByteArray(byteBuffer.remaining())
        byteBuffer[bytes]
        return String(bytes)
    }

    private fun getByteBuffer(string: String): ByteBuffer {
        val bytes = string.toByteArray()
        return ByteBuffer.allocate(bytes.size).apply {
            put(bytes)
            flip()
        }
    }

    data class KMSData(val data: String)

    private companion object {
        const val KEY_ID = "45da2137-76f5-4cf8-85f2-0a56e3992afd"
    }
}
