package com.example.prueba.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.example.prueba.models.Alimento
import com.example.prueba.models.Table
import com.example.senfase.R
import com.example.senfase.databinding.ItemTableBinding

class TableAdapter(
    private val list: List<Table>,
) :
    RecyclerView.Adapter<TableAdapter.TableViewHolder>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val itemBinding =
            ItemTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TableViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class TableViewHolder(
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
                binding.tILMp.visibility = View.VISIBLE
                binding.tvMp.visibility = View.GONE
//                setUpIngredientes(item.list)
                clicks(item)
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

        private fun clicks(item: Table) {
            binding.actvMp.setOnItemClickListener { parent, view, position, id ->
//                item.list?.let {
//                    val data = it[position]
//                    if (adapterPosition == 1) {
//                        proteina = data
//                        if (listAutoComplete[1].text.toString().isNullOrEmpty()) {
//                            Toast.makeText(
//                                context,
//                                "Debe seleccionar una energia",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        } else {
//                            Toast.makeText(
//                                context,
//                                "${proteina.ingrediente} ${energia.ingrediente}",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    } else if (adapterPosition == 2) {
//                        energia = data
//                        if (listAutoComplete[0].text.toString().isNullOrEmpty()) {
//                            Toast.makeText(
//                                context,
//                                "Debe seleccionar una proteina",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        } else {
//                            Toast.makeText(
//                                context,
//                                "${proteina.ingrediente} ${energia.ingrediente}",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    } else if (adapterPosition == 3) {
//
//                    } else if (adapterPosition == 4) {
//
//                    } else if (adapterPosition == 5) {
//
//                    } else if (adapterPosition == 6) {
//
//                    }
//                    Log.e("clicks: ", data.toString())
//                }
            }
        }

        private fun setUpIngredientes(ingrediente: List<Alimento>?) {
            val listFinal = mutableListOf<String>()
            ingrediente?.let {
                it.forEach { alimento ->
                    listFinal.add(alimento.ingrediente)
                }
            }
            val adapter = ArrayAdapter(context, R.layout.list_item, listFinal)
            binding.actvMp.setAdapter(adapter)
        }
    }
}
