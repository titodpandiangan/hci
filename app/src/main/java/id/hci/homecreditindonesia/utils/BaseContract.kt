package id.hci.homecreditindonesia.utils

interface BaseContract {
    interface BaseView{
        fun onClickListener()
    }

    interface BasePresenter{
        fun start()
    }
}