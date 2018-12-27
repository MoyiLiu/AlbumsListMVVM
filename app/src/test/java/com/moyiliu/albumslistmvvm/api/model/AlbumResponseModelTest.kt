package com.moyiliu.albumslistmvvm.api.model

import com.google.gson.GsonBuilder
import org.junit.Assert.assertEquals
import org.junit.Test

class AlbumResponseModelTest {

    private val gson by lazy { GsonBuilder().serializeNulls().create() }

    @Test
    fun shouldDeserialise() {
        val result = gson.fromJson(json, AlbumResponseModel::class.java)
        val expected = AlbumResponseModel(userId = 1, id = 1, title = testTitle)

        assertEquals(expected, result)
    }

    @Test
    fun shouldDeserialise_withNullTitle() {
        val result = gson.fromJson(jsonNullTitle, AlbumResponseModel::class.java)
        val expected = AlbumResponseModel(userId = 2, id = 2, title = null)
        assertEquals(expected, result)
    }

    @Test
    fun shouldSerialise() {
        val result = gson.toJson(model, AlbumResponseModel::class.java)
        val expected = json.removeSpaceLineBreak()

        assertEquals(expected, result)
    }

    @Test
    fun shouldSerialise_withNullTitle(){
        val result = gson.toJson(modelNullTitle, AlbumResponseModel::class.java)
        val expected = jsonNullTitle.removeSpaceLineBreak()

        assertEquals(expected, result)
    }

    private fun String.removeSpaceLineBreak(): String = replace("\\s|\\n".toRegex(),"")

    private val json = """
            |{
            |   "userId":$id_1,
            |   "id":$id_1,
            |   "title":"$testTitle"
            |}
        """.trimMargin()

    private val jsonNullTitle = """
            |{
            |   "userId":$id_2,
            |   "id":$id_2,
            |   "title":null
            |}
        """.trimMargin()

    private val model = AlbumResponseModel(userId = id_1, id = id_1, title = testTitle)
    private val modelNullTitle = AlbumResponseModel(userId = id_2, id = id_2, title = null)

    private val id_1 get() = 1
    private val id_2 get() = 2
    private val testTitle get() = "test_title"
}