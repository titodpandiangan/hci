package id.hci.homecreditindonesia.view

import id.hci.homecreditindonesia.model.Articles
import id.hci.homecreditindonesia.model.Product
import id.hci.homecreditindonesia.utils.BaseContract

interface HomeContract {
    interface HomeView: BaseContract.BaseView{
        fun showProgresbar()
        fun stopProgresbar()
        fun showErrorMessage(message:String)
        fun showDataHome(dataProduct: MutableList<Product>)
        fun showArticle (dataArticles: List<Any>)
    }

    interface HomePresenter:BaseContract.BasePresenter{
        fun OnButtonTryAgain()
    }
}