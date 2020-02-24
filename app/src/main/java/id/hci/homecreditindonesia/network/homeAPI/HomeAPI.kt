package id.hci.homecreditindonesia.network.homeAPI

import id.hci.homecreditindonesia.network.HCIClient
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeAPI {
    private var homeAPIInterface:HomeService= HCIClient.clientAPI().create(HomeService::class.java)
    private lateinit var homeLoadDataListener:HomeLoadDataListener

    fun setHomeLoadDataListener(homeLoadDataListener: HomeLoadDataListener){
        this.homeLoadDataListener=homeLoadDataListener
    }

    fun loadData(){
        val call=homeAPIInterface.getDataHome()
        call.enqueue(object : Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                homeLoadDataListener.onConnectionFailure()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    val body = response.body()!!.string()
                    val jsonBody = JSONObject(body)

                    homeLoadDataListener.onHomeLoadSuccess(jsonBody)
                }
            }

        })
    }

    interface HomeLoadDataListener{
        fun onHomeLoadSuccess(jsonObject: JSONObject)
        fun onConnectionFailure()
    }
}