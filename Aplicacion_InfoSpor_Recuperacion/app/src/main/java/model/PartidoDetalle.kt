package model

import java.io.Serializable

data class PartidoDetalle(
    val partido: Partido,
    val eventos: List<Evento>,
    val alineacionLocal: List<Alineacion>,
    val alineacionVisitante: List<Alineacion>
) : Serializable