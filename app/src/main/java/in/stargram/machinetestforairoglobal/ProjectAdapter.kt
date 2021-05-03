package `in`.stargram.machinetestforairoglobal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_project_layout.view.*

class ProjectAdapter(var data: List<ProjectModel>): RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.tvProjectTitle
        var id: TextView = itemView.tvID
        var date: TextView = itemView.tvDate
        var editedDate: TextView = itemView.tvEdited
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_project_layout, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProjectAdapter.ViewHolder, position: Int) {

        holder.name.text = data[position].projectName
        holder.id.text = "ID#: ${data[position].projectId}"
        holder.date.text = "Created: ${data[position].date}"
        holder.editedDate.text = "Edited: ${data[position].edited_date}"
    }

    override fun getItemCount(): Int { return data.size }
}