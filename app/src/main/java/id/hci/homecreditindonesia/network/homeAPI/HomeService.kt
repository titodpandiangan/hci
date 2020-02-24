package id.hci.homecreditindonesia.network.homeAPI

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface HomeService {

    @GET("home")
    fun getDataHome(
    ): Call<ResponseBody>
}