package com.example.recyclerview.web_service

import com.example.recyclerview.commons.DETAILS_END_POINT
import com.example.recyclerview.commons.MEMBERS_END_POINT
import com.example.recyclerview.commons.URL_BASE
import com.example.recyclerview.objects.DetailMember
import com.example.recyclerview.objects.Members
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MembersApi {

    @GET(MEMBERS_END_POINT)
    suspend fun getMember(): List<Members>

    @GET(DETAILS_END_POINT)
    suspend fun getMemberDetail(@Path("id") memberId: String): DetailMember

    companion object {
        fun getRetrofitClient(): MembersApi = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MembersApi::class.java)
    }

}


