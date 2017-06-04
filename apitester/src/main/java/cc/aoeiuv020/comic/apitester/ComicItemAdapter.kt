package cc.aoeiuv020.comic.apitester

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import cc.aoeiuv020.comic.model.ComicListItemModel
import cc.aoeiuv020.comic.util.ImageUtil
import org.jetbrains.anko.*

/**
 * Created by AoEiuV020 on 17-6-3.
 */

class ComicItemAdapter(val ctx: Context, val listItems: List<ComicListItemModel>) : BaseAdapter() {

    override fun getItem(position: Int): ComicListItemModel = listItems[position]

    override fun getItemId(position: Int): Long = 0L

    override fun getCount(): Int = listItems.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = if (convertView == null) {
            val ankoContext = AnkoContext.createReusable(ctx)
            val ui = ItemUI()
            ui.createView(ankoContext).apply {
                tag = ui
            }
        } else convertView
        (view.tag as ItemUI).apply(getItem(position))
        return view
    }

    class ItemUI : AnkoComponent<Context> {
        lateinit var name: TextView
        lateinit var image: ImageView

        override fun createView(ui: AnkoContext<Context>) = with(ui) {
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                image = imageView(android.R.drawable.ic_menu_report_image)
                        .lparams(dip(200), dip(200))
                name = textView()
            }
        }

        fun apply(comicListItemModel: ComicListItemModel) {
            name.text = comicListItemModel.name
            ImageUtil.asyncSetImageUrl(image, comicListItemModel.imgUrl)
        }
    }
}