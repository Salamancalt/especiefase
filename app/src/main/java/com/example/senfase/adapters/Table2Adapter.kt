package com.example.prueba.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba.models.Alimento
import com.example.prueba.models.Table
import com.example.senfase.R
import com.example.senfase.databinding.ItemTableBinding

class Table2Adapter (
    private val list: List<Table>,
) :
    RecyclerView.Adapter<Table2Adapter.Table2ViewHolder>() {
    private val listAutoComplete = mutableListOf<AutoCompleteTextView>()
    private var proteina = Alimento(
        0, 0.0, "", "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )

    private var energia = Alimento(
        0, 0.0, "", "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Table2ViewHolder {
        val itemBinding =
            ItemTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Table2ViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: Table2ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class Table2ViewHolder(
        private val binding: ItemTableBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Table) {
            if (adapterPosition == 0) {
                binding.lLElement.setBackgroundResource(R.color.white)
                binding.l1.setBackgroundResource(R.color.white)
                binding.l2.setBackgroundResource(R.color.white)
                binding.l3.setBackgroundResource(R.color.white)
                binding.l4.setBackgroundResource(R.color.white)
                binding.l5.setBackgroundResource(R.color.white)
                binding.l6.setBackgroundResource(R.color.white)
                binding.l7.setBackgroundResource(R.color.white)
                binding.l8.setBackgroundResource(R.color.white)
                binding.l9.setBackgroundResource(R.color.white)
            } else if (adapterPosition == list.size - 1) {
                binding.lLElement.setBackgroundResource(R.drawable.corner_card_green)
            } else if (adapterPosition == list.size - 2) {
                binding.lLElement.setBackgroundResource(R.drawable.corner_card_green_light)
            } else {
                listAutoComplete.add(binding.actvMp)
            }
            binding.actvMp.hint = item.materia
            binding.tvMp.text = item.materia
            binding.tvCant.text = item.cantidad
            binding.tvProt.text = item.proteina
            binding.tvFib.text = item.fibra
            binding.tvEner.text = item.energia
            binding.tvCal.text = item.calcio
            binding.tvfos.text = item.fosforo
            binding.tvLis.text = item.lisina
            binding.tvMetio.text = item.metionina
            binding.tvMetcis.text = item.metCis

        }

    }
}
