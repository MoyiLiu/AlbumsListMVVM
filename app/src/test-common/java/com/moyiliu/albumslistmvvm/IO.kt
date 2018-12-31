package com.moyiliu.albumslistmvvm

import okio.Okio

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun Any.readMockApiResponse(fileName: String): String =
    javaClass.classLoader
        .getResourceAsStream("api-response/$fileName")
        .let { inputStream ->
            Okio.buffer(Okio.source(inputStream))
                .readString(Charsets.UTF_8)
        }