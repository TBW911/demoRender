package com.pruebas.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PEDIDOS_MODIFICADORES")
public class PedidosModificadores {

    @EmbeddedId
    private PedidosModificadoresId id;

    @ManyToOne
    @JoinColumn(name = "ID_Pedido", insertable = false, updatable = false)
    private Pedidos pedido;

    @ManyToOne
    @JoinColumn(name = "ID_Modificador", insertable = false, updatable = false)
    private Modificadores modificador;

    // Otros atributos y métodos de la entidad
    // ...

    public PedidosModificadoresId getId() {
        return id;
    }

    public void setId(PedidosModificadoresId id) {
        this.id = id;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }

    public Modificadores getModificador() {
        return modificador;
    }

    public void setModificador(Modificadores modificador) {
        this.modificador = modificador;
    }
}
