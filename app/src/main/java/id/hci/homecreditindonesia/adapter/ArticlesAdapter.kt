package id.hci.homecreditindonesia.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.hci.homecreditindonesia.R
import id.hci.homecreditindonesia.model.Articles
import id.hci.homecreditindonesia.utils.Helper
import kotlinx.android.synthetic.main.layout_item_article.view.*
import kotlinx.android.synthetic.main.layout_item_single_line.view.*

class ArticlesAdapter (private var dataItem: List<Any>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context:Context

    lateinit var setItemListener: SetItemListener

    companion object {
        private const val ITEM_HEADER = 0
        private const val ITEM_MENU = 1
    }

    fun setOnClickItemListener(setItemListener: SetItemListener){
        this.setItemListener=setItemListener
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataItem[position]) {
            is String -> ITEM_HEADER
            is Articles -> ITEM_MENU
            else -> throw IllegalArgumentException("Undefined type")

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        context=parent.context
        return when (viewType) {
            ITEM_HEADER -> ViewHolderHeader(layoutInflater.inflate(R.layout.layout_item_single_line,parent,false))
            ITEM_MENU -> ViewHolderArticles(layoutInflater.inflate(R.layout.layout_item_article,parent,false))
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemCount(): Int {
        return dataItem.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            ITEM_HEADER->{
                val itemHolder= holder as ViewHolderHeader
                itemHolder.bindContent(dataItem[position] as String)
            }
            ITEM_MENU->{
                val  itemHolder= holder as ViewHolderArticles
                val articlesData:Articles= dataItem[position] as Articles
                itemHolder.bindContent(articlesData)
            }

        }
    }

    inner class ViewHolderArticles(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgArticles = itemView.imgArticle
        var tvArticles: TextView = itemView.tvTitleArticle

        fun bindContent(articles: Articles){
            tvArticles.text = articles.article_title
            Helper.showBanneerFromUrl(imgArticles, articles.link,context)

            itemView.setOnClickListener {
                setItemListener.onClickItemListener(articles.link)
            }
        }

    }

    inner class ViewHolderHeader(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle = itemView.tvString

        fun bindContent(text: String){
            tvTitle.text = text
        }

    }

    interface SetItemListener{
        fun onClickItemListener(linkData: String)
    }
}