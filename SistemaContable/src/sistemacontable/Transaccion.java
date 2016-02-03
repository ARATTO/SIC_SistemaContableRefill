    
package sistemacontable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaccion {
    private int id;
    private int nTransaccion;
    private double cargar;  //cargar el saldo de cualquier cuenta va a aumentar
    private double abonar;  //el saldo de cualqueir cuenta va a disminuir
    private String descripcion;
    private int tipo;
    private String fecha;

    private Cuenta cta;

    public Transaccion(int id, double cargar, double abonar, String descripcion, int tipo) {
        this.id = id;
        this.cargar = cargar;
        this.abonar = abonar;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public Transaccion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCargar() {
        return cargar;
    }

    public void setCargar(double cargar) {
        this.cargar = cargar;
    }

    public double getAbonar() {
        return abonar;
    }

    public void setAbonar(double abonar) {
        this.abonar = abonar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
     public int getnTransaccion() {
        return nTransaccion;
    }

    public void setnTransaccion(int nTransaccion) {
        this.nTransaccion = nTransaccion;
    }

    public Cuenta getCta() {
        return cta;
    }

    public void setCta(Cuenta cta) {
        this.cta = cta;
    }
       
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String Fecha) {
        this.fecha = Fecha;
    }

    @Override
    public String toString() {
        return "Transaccion{" + "id=" + id + ", cargar=" + cargar + ", abonar=" + abonar + ", descripcion=" + descripcion + ", tipo=" + tipo + '}'+" fecha:"+fecha;
    }

    
    public void ingresarTransaccion(Cuenta cuenta,Connection conexion2) throws SQLException{
          String sentenciaSql = "INSERT INTO transaccion(id,n_transaccion,codigo,cargar,abonar,descripcion,fecha,tipo)VALUES (?,?,?,?,?,?,?,?) "; 
            PreparedStatement preparedStatement2 = conexion2.prepareStatement(sentenciaSql);
            
            preparedStatement2.setInt(1, cuenta.transaccion.getId());
            preparedStatement2.setInt(2,cuenta.transaccion.getnTransaccion());
            preparedStatement2.setString(3,cuenta.codigo);
            preparedStatement2.setDouble(4,cuenta.transaccion.getCargar());
            preparedStatement2.setDouble(5,cuenta.transaccion.getAbonar());
            preparedStatement2.setString(6,cuenta.transaccion.getDescripcion());
            preparedStatement2.setString(7,cuenta.transaccion.getFecha());
            preparedStatement2.setInt(8,cuenta.transaccion.getTipo());
            preparedStatement2.execute();
    }
    
}
