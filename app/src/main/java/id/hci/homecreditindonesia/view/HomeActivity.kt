package id.hci.homecreditindonesia.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.hci.homecreditindonesia.R
import id.hci.homecreditindonesia.adapter.ArticlesAdapter
import id.hci.homecreditindonesia.adapter.ProductAdapter
import id.hci.homecreditindonesia.model.Articles
import id.hci.homecreditindonesia.model.Product

class HomeActivity : AppCompatActivity(), HomeContract.HomeView {

    lateinit var rvProduct:RecyclerView
    lateinit var rvArticle:RecyclerView
    lateinit var progresBar:ProgressBar
    lateinit var tvMessage:TextView

    lateinit var homePresenter: HomePresenter
    lateinit var homeAdapter: ProductAdapter
    lateinit var articlesAdapter: ArticlesAdapter
    lateinit var btnTryAgain:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setHomePresenter()
    }

    fun initView(){
        rvProduct=findViewById(R.id.rvProduct)
        rvArticle=findViewById(R.id.rvArticle)
        progresBar=findViewById(R.id.progressBar)
        tvMessage=findViewById(R.id.textViewMessage)
        btnTryAgain=findViewById(R.id.btnTryAgain)
    }

    fun setHomePresenter(){
        homePresenter= HomePresenter(this)
        homePresenter.start()

    }

    override fun showDataHome(dataProduct: MutableList<Product>) {
        val mLayoutManager = GridLayoutManager(this, 3)
        rvProduct.layoutManager = mLayoutManager
        rvProduct.isNestedScrollingEnabled=false
        homeAdapter= ProductAdapter(dataProduct)
        homeAdapter.setOnClickItemListener(object : ProductAdapter.SetItemListener{
            override fun onClickItemListener(linkData: String) {
                directToBrowser(linkData)
            }

        })
        rvProduct.adapter=homeAdapter
        homeAdapter.notifyDataSetChanged()
    }

    override fun showArticle(dataArticles: List<Any>) {

        val mLayoutManager = LinearLayoutManager(this)
        rvArticle.layoutManager = mLayoutManager
        rvArticle.isNestedScrollingEnabled=false
        articlesAdapter= ArticlesAdapter(dataArticles)
        articlesAdapter.setOnClickItemListener(object : ArticlesAdapter.SetItemListener{
            override fun onClickItemListener(linkData: String) {
                directToBrowser(linkData)
            }

        })

        rvArticle.adapter=articlesAdapter
        articlesAdapter.notifyDataSetChanged()
    }

    override fun showProgresbar() {
        btnTryAgain.visibility=View.INVISIBLE
        tvMessage.visibility=View.VISIBLE
        progresBar.visibility=View.VISIBLE
        tvMessage.text=resources.getString(R.string.loading)
    }

    override fun stopProgresbar() {
        tvMessage.visibility=View.INVISIBLE
        progresBar.visibility=View.INVISIBLE
        btnTryAgain.visibility=View.INVISIBLE
    }

    override fun showErrorMessage(message: String) {
        tvMessage.visibility=View.VISIBLE
        progresBar.visibility=View.INVISIBLE
        btnTryAgain.visibility=View.VISIBLE
        tvMessage.text=message
    }

    override fun onClickListener() {
        btnTryAgain.setOnClickListener {
            homePresenter.OnButtonTryAgain()
        }
    }

    fun directToBrowser(linkData: String){
        val uris = Uri.parse(linkData)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        startActivity(intents)
    }
}
