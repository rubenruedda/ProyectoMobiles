package model

data class PartidoDetalle(
    val partido: Partido,
    val eventos: List<Evento>,
    val alineacionLocal: List<Alineacion>,
    val alineacionVisitante: List<Alineacion>
) : java.io.Serializable