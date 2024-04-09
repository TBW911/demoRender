package com.pruebas.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PedidosModificadoresId implements Serializable {

    @Column(name = "ID_Pedido")
    private Long idPedido;

    @Column(name = "ID_Modificador")
    private Long idModificador;
    
    public PedidosModificadoresId(Long idPedido, Long idModificador) {
        this.idPedido = idPedido;
        this.idModificador = idModificador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidosModificadoresId that = (PedidosModificadoresId) o;
        return Objects.equals(idPedido, that.idPedido) &&
                Objects.equals(idModificador, that.idModificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido, idModificador);
    }
}
