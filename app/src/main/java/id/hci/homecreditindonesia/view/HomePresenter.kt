package id.hci.homecreditindonesia.view

import id.hci.homecreditindonesia.model.Articles
import id.hci.homecreditindonesia.model.Product
import id.hci.homecreditindonesia.network.homeAPI.HomeAPI
import org.json.JSONObject
import java.lang.IllegalArgumentException

class HomePresenter(var view:HomeContract.HomeView): HomeContract.HomePresenter,HomeAPI.HomeLoadDataListener {



    lateinit var homeAPI: HomeAPI
    var dataProduct= mutableListOf<Product>()
    private val dataViewArticles = mutableListOf<Any>()

    override fun start() {
        initAPI()
        view.onClickListener()
    }

    override fun onHomeLoadSuccess(jsonObject: JSONObject) {
        view.stopProgresbar()
        val data= jsonObject.getJSONArray("data")

        for(i in 0 until data.length()){
            val dataJson= data.getJSONObject(i)
            when {
                dataJson.getString("section") == "products" -> dataProduct=Product.parseData(dataJson)
                dataJson.getString("section") == "articles" -> {
                    dataViewArticles.add(dataJson.getString("section_title"))
                    val articlesArray= dataJson.getJSONArray("items")

                    for (i in 0 until articlesArray.length()){
                        val arr=articlesArray.getJSONObject(i)
                        dataViewArticles.add(Articles(arr.getString("article_title"),arr.getString("article_image"),arr.getString("link")))
                    }
                }
                else -> throw IllegalArgumentException("Undefined Section")
            }
        }

        view.showDataHome(dataProduct)
        view.showArticle(dataViewArticles)


    }

    override fun onConnectionFailure() {
        view.showErrorMessage("Something went wrong")
    }

    override fun OnButtonTryAgain() {
        view.showProgresbar()
        homeAPI.loadData()
    }

    fun initAPI(){
        homeAPI= HomeAPI()
        homeAPI.setHomeLoadDataListener(this)

        //homeAPI.loadData()
    }
}