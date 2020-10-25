package com.localstack.sample

import com.amazonaws.services.s3.AmazonS3
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/s3")
class S3Endpoint(val s3Client: AmazonS3) {

    @PostMapping
    fun post(@RequestBody request: S3Document) {
        s3Client.putObject("bucket", request.name, request.content)
    }

    @GetMapping("/{name}")
    fun get(name: String) = String(s3Client.getObject("bucket", name).objectContent.readAllBytes())

}