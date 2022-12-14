package com.example.prueba.models.fase

data class RequerimientoAnimal(
    val id_requerimiento_animal: Int,
    val nombre_fase: String,
    val m_s: Any,
    val e_m_ave: Any,
    val e_d_cerdo: String,
    val proteina: String,
    val fibra_cruda: String,
    val ext_etereo: String,
    val calcio: String,
    val fosf_disp: String,
    val sodio: String,
    val arginina: String,
    val lisina: String,
    val metionina: String,
    val met_cis: String,
    val treonina: String,
    val triptofano: String,
    val ceniza: String,
    val creado: Any,
    val actualizado: Any,
    val especie_id: String,
    val id_especie: Int,
    val nombre_especie: String,
)