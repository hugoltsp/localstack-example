package com.localstack.sample

import com.amazonaws.services.s3.AmazonS3
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/s3")
class S3Endpoint(val s3Client: AmazonS3) {

    @PostMapping
    fun post(@RequestBody request: S3Document) {
        s3Client.putObject("bucket", request.name, request.content)
    }

    @GetMapping("/{name}")
    fun get(name: String): ResponseEntity<Any> {
        if (!s3Client.doesObjectExist("bucket", name)) {
            return ResponseEntity.noContent().build()
        }

        return ResponseEntity.ok(String(s3Client.getObject("bucket", name).objectContent.readAllBytes()))
    }

}