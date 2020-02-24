package id.hci.homecreditindonesia.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.io.Serializable
import java.util.ArrayList

class Product(@SerializedName("product_name") val product_name: String,
              @SerializedName("product_image") val product_image: String,
              @SerializedName("link") val link: String) : Serializable {

    companion object {
        fun parseData(jsonData: JSONObject): ArrayList<Product> {
            val result = ArrayList<Product>()
            try {
                val array = jsonData.getJSONArray("items")
                val gson = Gson()
                val dataList = gson.fromJson(array.toString(),
                    Array<Product>::class.java)
                if (dataList != null && dataList.isNotEmpty()) {
                    for (curr in dataList) {
                        result.add(curr)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return result
        }
    }
}