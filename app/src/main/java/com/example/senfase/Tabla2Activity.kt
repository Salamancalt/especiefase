package com.example.senfase

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prueba.adapters.Table2Adapter
import com.example.prueba.core.Constants
import com.example.prueba.models.Alimento
import com.example.prueba.models.Table
import com.example.senfase.databinding.ActivityTabla2Binding
import com.google.android.material.snackbar.Snackbar

class Tabla2Activity : AppCompatActivity() {
    private var cantidadKg = Constants.cantidadMezcla
    private var baseCien = 100
    private lateinit var binding: ActivityTabla2Binding
    private var energiaSeleccionada: Alimento? = null
    private var proteinaSeleccionada: Alimento? = null
    private var fosforoSeleccionada: Alimento? = null
    private var calcioSeleccionada: Alimento? = null

    private val ingredientesEnergiaList = mutableListOf(
        Alimento(
            1,
            0.0,
            "Harina de Maiz",
            "",
            "14.00",
            "",
            "8.50",
            "1.80",
            "",
            "0.1",
            "0.3",
            "",
            "",
            "0.2",
            "0.2",
            "0.3",
            "",
            "",
        ),
    )
    private val ingredientesProteinaList = mutableListOf(
        Alimento(
            1,
            0.0,
            "Torta de Soya",
            "",
            "10.00",
            "",
            "43.5",
            "6.70",
            "",
            "0.27",
            "0.63",
            "",
            "",
            "2.86",
            "0.58",
            "1.18",
            "",
            "",
        ),
    )
    private val ingredientesFosforo = mutableListOf(
        Alimento(
            1,
            0.0,
            "Fosfato Dicalcico",
            "",
            "0.0",
            "",
            "0.0",
            "0.0",
            "",
            "0.21",
            "16.0",
            "",
            "",
            "0.0",
            "0.0",
            "0.0",
            "",
            "",
        ),
        Alimento(
            2,
            0.0,
            "Fosfato Monodicalcico",
            "",
            "0.0",
            "",
            "0.0",
            "0.0",
            "",
            "20.00",
            "21.00",
            "",
            "",
            "0.0",
            "0.0",
            "0.0",
            "",
            "",
        ),
        Alimento(
            2,
            0.0,
            "Harina de huesos",
            "99.00",
            "0.0",
            "",
            "0.0",
            "0.0",
            "",
            "26.00",
            "12.00",
            "0.4",
            "",
            "0.0",
            "0.0",
            "0.0",
            "",
            "",
        ),
    )

    private val list = mutableListOf(
        Table(
            "MP",
            "Cant\nkg",
            "Prot\n%",
            "Fib\n%",
            "Ener\nMcal/kg",
            "Calc\n%",
            "Fosf\n%",
            "Lisn\n%",
            "Metn\n%",
            "Met+Cis \n%",
        ), Table(
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
        ), Table(
            "Energia",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
        ), Table(
            "Fosforo",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
        ), Table(
            "Calcio",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
        ), Table(
            "Lisina",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
        ), Table(
            "Metionina",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
        ), Table(
            "Total",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
        ), Table(
            "Balance",
            cantidadKg.toString(),
            Constants.faseActual.proteina,
            Constants.faseActual.fibra_cruda,
            Constants.faseActual.e_m_ave.toString(),
            Constants.faseActual.calcio,
            Constants.faseActual.fosf_disp,
            Constants.faseActual.lisina,
            Constants.faseActual.metionina,
            Constants.faseActual.met_cis,
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabla2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        setUpEnergia(ingredientesEnergiaList)
        setUpProteina(ingredientesProteinaList)
        clicks()


    }

    private fun clicks() {
        binding.actvProteina.setOnItemClickListener { parent, view, position, id ->
            energiaSeleccionada = ingredientesEnergiaList[position]
            añadirPrecio()
        }
        binding.actvEnergia.setOnItemClickListener { parent, view, position, id ->
            proteinaSeleccionada = ingredientesProteinaList[position]
        }
        binding.actvFosforo.setOnItemClickListener { parent, view, position, id ->
            fosforoSeleccionada = ingredientesFosforo[position]
        }
        binding.btnCalcular.setOnClickListener {
            if (energiaSeleccionada == null || proteinaSeleccionada == null) {
                Snackbar.make(binding.root, "Debe Seleccionar los datos", Snackbar.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            encontrarCantidadesPE()
        }
        binding.btnFosforo.setOnClickListener {
            if (fosforoSeleccionada == null ) {
                Snackbar.make(binding.root, "Debe Seleccionar los datos", Snackbar.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            encontrarCantidadesFos()
        }
    }

    private fun encontrarCantidadesFos() {
        val resta = list[list.size-1].fosforo.toDouble() - list[list.size-2].fosforo.toDouble()
        val cantidad = resta/(fosforoSeleccionada!!.fosf_disp.toDouble()/100)
        Log.e("encontrarCantidadesFos: ",resta.toString() )
        Log.e("encontrarCantidadesFos: ",fosforoSeleccionada!!.fosf_disp )
        Log.e("encontrarCantidadesFos: ",cantidad.toString() )
        list[3].cantidad = String.format("%.2f", cantidad)
        list[3].materia = fosforoSeleccionada!!.ingrediente
        list[3].fibra = fosforoSeleccionada!!.fibra_cruda
        list[3].energia = fosforoSeleccionada!!.e_m_ave
        list[3].calcio = fosforoSeleccionada!!.calcio
        list[3].fosforo = fosforoSeleccionada!!.fosf_disp
        list[3].lisina = fosforoSeleccionada!!.lisina
        list[3].metionina = fosforoSeleccionada!!.metionina
        list[3].metCis = fosforoSeleccionada!!.met_cis

        setUpRecycler(list)

    }

    private fun encontrarCantidadesCal() {
        val resta = list[list.size-1].calcio.toDouble() - list[list.size-2].calcio.toDouble()
        val cantidad = resta/(calcioSeleccionada!!.calcio.toDouble()/100)
        Log.e("encontrarCantidadesFos: ",resta.toString() )
        Log.e("encontrarCantidadesFos: ",calcioSeleccionada!!.calcio )
        Log.e("encontrarCantidadesFos: ",cantidad.toString() )
    }

    private fun añadirPrecio() {


//        val dialogBinding = AddPriceBinding.inflate(layoutInflater)
//
//        val alert = AlertDialog.Builder(applicationContext).apply {
//            setView(dialogBinding.root)
//        }.create()
//
//        dialogBinding.txtTitleParameter.text =
//            "Ingrese el precio de ${energiaSeleccionada!!.ingrediente} en su region :"
//
//        dialogBinding.btnPrice.setOnClickListener {
//            if (dialogBinding.edtSample.text.toString().isNullOrEmpty()){
//                return@setOnClickListener
//                Snackbar.make(binding.root, "Debe escribir un precio", Snackbar.LENGTH_SHORT)
//            }
//            alert.dismiss()
//        }
//        alert.setCancelable(false)
//        alert.show()


    }

    private fun encontrarCantidadesPE() {
        val difEnergia: Double = if (energiaSeleccionada!!.proteina.toDouble() > Constants.faseActual.proteina.toDouble()) {
            (energiaSeleccionada!!.proteina.toDouble() - Constants.faseActual.proteina.toDouble())
        } else {
            (Constants.faseActual.proteina.toDouble()-energiaSeleccionada!!.proteina.toDouble())
        }
        val difProteina: Double = if (proteinaSeleccionada!!.proteina.toDouble() > Constants.faseActual.proteina.toDouble()) {
            (proteinaSeleccionada!!.proteina.toDouble() - Constants.faseActual.proteina.toDouble())
        } else {
            (Constants.faseActual.proteina.toDouble()-proteinaSeleccionada!!.proteina.toDouble())
        }

        val sum = difEnergia + difProteina

        energiaSeleccionada!!.cantidad = (difProteina * cantidadKg ) / sum
        proteinaSeleccionada!!.cantidad = (difEnergia * cantidadKg ) / sum
        list[1].materia = proteinaSeleccionada!!.ingrediente
        list[1].cantidad = String.format("%.2f", proteinaSeleccionada!!.cantidad)
        list[2].materia = energiaSeleccionada!!.ingrediente
        list[2].cantidad = String.format("%.2f", energiaSeleccionada!!.cantidad)

        list[list.size-2].cantidad = (list[1].cantidad.toDouble() + list[2].cantidad.toDouble()).toString()
        balanceProteina()
        balanceFibra()
        balanceEm()
        balanceCalcio()
        balanceFosforo()
        balanceLisina()
        balanceMetionina()
        balanceMetCis()

        binding.linearLayout5.visibility = View.GONE
        binding.lLFosforo.visibility = View.VISIBLE
        setUpFosforo(ingredientesFosforo)
        setUpRecycler(list)
    }

    private fun setUpFosforo(fosforo: MutableList<Alimento>) {
        val list = mutableListOf<String>()
        fosforo.forEach {
            list.add(it.ingrediente)
        }
        val adapter = ArrayAdapter(applicationContext, R.layout.list_item, list)
        binding.actvFosforo.setAdapter(adapter)
    }

    private fun balanceFibra() {
        val aporteEnerigia = (energiaSeleccionada!!.cantidad * energiaSeleccionada!!.fibra_cruda.toDouble()) / baseCien
        val aporteProteina = (proteinaSeleccionada!!.cantidad * proteinaSeleccionada!!.fibra_cruda.toDouble()) / baseCien
        list[1].fibra = String.format("%.2f",aporteProteina)
        list[2].fibra = String.format("%.2f",aporteEnerigia)
        list[list.size-2].fibra = (list[1].fibra.toDouble() + list[2].fibra.toDouble()).toString()
    }

    private fun balanceProteina() {
        val aporteProteinaEnerigia = (energiaSeleccionada!!.cantidad * energiaSeleccionada!!.proteina.toDouble()) / baseCien
        val aporteProteinaProteina = (proteinaSeleccionada!!.cantidad * proteinaSeleccionada!!.proteina.toDouble()) / baseCien
        Log.e("balanceProteina: ", "" )
        list[1].proteina = String.format("%.2f",aporteProteinaProteina)
        list[2].proteina = String.format("%.2f",aporteProteinaEnerigia)
        list[list.size-2].proteina = (list[1].proteina.toDouble() + list[2].proteina.toDouble()).toString()
    }

    private fun balanceEm() {
        val aporteEnerigia = (energiaSeleccionada!!.cantidad * energiaSeleccionada!!.e_m_ave.toDouble()) / baseCien
        val aporteProteina = (proteinaSeleccionada!!.cantidad * proteinaSeleccionada!!.e_m_ave.toDouble()) / baseCien
        list[1].energia = String.format("%.2f",aporteProteina)
        list[2].energia = String.format("%.2f",aporteEnerigia)
        list[list.size-2].energia = (list[1].energia.toDouble() + list[2].energia.toDouble()).toString()
    }

    private fun balanceCalcio() {
        val aporteEnerigia = (energiaSeleccionada!!.cantidad * energiaSeleccionada!!.calcio.toDouble()) / baseCien
        val aporteProteina = (proteinaSeleccionada!!.cantidad * proteinaSeleccionada!!.calcio.toDouble()) / baseCien
        list[1].calcio = String.format("%.2f",aporteProteina)
        list[2].calcio = String.format("%.2f",aporteEnerigia)
        list[list.size-2].calcio = (list[1].calcio.toDouble() + list[2].calcio.toDouble()).toString()
    }

    private fun balanceFosforo() {
        val aporteEnerigia = (energiaSeleccionada!!.cantidad * energiaSeleccionada!!.fosf_disp.toDouble()) / baseCien
        val aporteProteina = (proteinaSeleccionada!!.cantidad * proteinaSeleccionada!!.fosf_disp.toDouble()) / baseCien
        list[1].fosforo = String.format("%.2f",aporteProteina)
        list[2].fosforo = String.format("%.2f",aporteEnerigia)
        list[list.size-2].fosforo = (list[1].fosforo.toDouble() + list[2].fosforo.toDouble()).toString()
    }

    private fun balanceLisina() {
        val aporteEnerigia = (energiaSeleccionada!!.cantidad * energiaSeleccionada!!.lisina.toDouble()) / baseCien
        val aporteProteina = (proteinaSeleccionada!!.cantidad * proteinaSeleccionada!!.lisina.toDouble()) / baseCien
        list[1].lisina = String.format("%.2f",aporteProteina)
        list[2].lisina = String.format("%.2f",aporteEnerigia)
        list[list.size-2].lisina = (list[1].lisina.toDouble() + list[2].lisina.toDouble()).toString()
    }

    private fun balanceMetionina() {
        val aporteEnerigia = (energiaSeleccionada!!.cantidad * energiaSeleccionada!!.metionina.toDouble()) / baseCien
        val aporteProteina = (proteinaSeleccionada!!.cantidad * proteinaSeleccionada!!.metionina.toDouble()) / baseCien
        list[1].metionina = String.format("%.2f",aporteProteina)
        list[2].metionina = String.format("%.2f",aporteEnerigia)
        list[list.size-2].metionina =  String.format("%.2f",(list[1].metionina.toDouble() + list[2].metionina.toDouble()))
    }

    private fun balanceMetCis() {
        val aporteEnerigia = (energiaSeleccionada!!.cantidad * energiaSeleccionada!!.met_cis.toDouble()) / baseCien
        val aporteProteina = (proteinaSeleccionada!!.cantidad * proteinaSeleccionada!!.met_cis.toDouble()) / baseCien
        list[1].metCis = String.format("%.2f",aporteProteina)
        list[2].metCis = String.format("%.2f",aporteEnerigia)
        list[list.size-2].metCis = (list[1].metCis.toDouble() + list[2].metCis.toDouble()).toString()
    }

    private fun setUpRecycler(list: MutableList<Table>) {
        binding.rv.adapter = Table2Adapter(list)
        binding.rv.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun setUpEnergia(especies: MutableList<Alimento>) {
        val list = mutableListOf<String>()
        especies.forEach {
            list.add(it.ingrediente)
        }
        val adapter = ArrayAdapter(applicationContext, R.layout.list_item, list)
        binding.actvEnergia.setAdapter(adapter)
    }

    private fun setUpProteina(especies: MutableList<Alimento>) {
        val list = mutableListOf<String>()
        especies.forEach {
            list.add(it.ingrediente)
        }
        val adapter = ArrayAdapter(applicationContext, R.layout.list_item, list)
        binding.actvProteina.setAdapter(adapter)
    }
}