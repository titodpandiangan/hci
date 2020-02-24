package id.hci.homecreditindonesia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.hci.homecreditindonesia.R
import id.hci.homecreditindonesia.model.Product
import id.hci.homecreditindonesia.utils.Helper
import kotlinx.android.synthetic.main.layout_item_product.view.*

class ProductAdapter(var dataItem: MutableList<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    lateinit var context:Context
    lateinit var setItemListener: SetItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_item_product, parent, false)
        context = parent.context
        return ViewHolder(view)
    }


    fun setOnClickItemListener(setItemListener: SetItemListener){
        this.setItemListener=setItemListener
    }



    override fun getItemCount():Int {
        return dataItem.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNamaProduk.text=dataItem.get(position).product_name
        Helper.showImageFromUrl(holder.imgProduct, dataItem[position].link,context)
        holder.itemView.setOnClickListener {
            setItemListener.onClickItemListener(dataItem[position].link)
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var imgProduct = itemView.imgProduct
        internal var tvNamaProduk: TextView = itemView.tvNamaProduct

    }

    interface SetItemListener{
        fun onClickItemListener(linkData: String)
    }

}