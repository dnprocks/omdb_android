package br.com.estudoprova.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.estudoprova.R
import br.com.estudoprova.models.Episodes
import kotlinx.android.synthetic.main.item_episodes.view.*

class EpisodesAdapter(var context: Context, var list: List<Episodes>) :
    RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodesAdapter.EpisodesViewHolder {

        var view = LayoutInflater.from(context).inflate(R.layout.item_episodes, parent, false)
        return EpisodesViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: EpisodesAdapter.EpisodesViewHolder, position: Int) {
        holder.bind(context, list[position], position)
    }

    class EpisodesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title = itemView.title
        var rating = itemView.rating

        fun bind(context: Context, itemView: Episodes, p: Int) {

            title.text = itemView.Title
            rating.text = itemView.imdbRating

        }
    }
}