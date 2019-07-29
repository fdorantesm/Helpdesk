package helpdesk;

public class Ticket {
    private long fechai,fechaf;
    private int id, cliente_id, status, prioridad, espera,categoria;
    private String asunto, descripcion, ip;
    
    public Ticket(String asunto, String descripcion, int prioridad, int categoria){
        this.asunto=asunto;
        this.descripcion=descripcion;
        this.prioridad=prioridad;
        this.categoria=categoria;
//        this.fechai=System.currentTimeMillis();
//        this.status=1;
//        this.prioridad=2;
//        this.espera=1;
//        this.ip="127.0.0.1";
    }

}