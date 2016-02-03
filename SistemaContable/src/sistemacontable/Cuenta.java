
package sistemacontable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Cuenta {
    protected String codigo;
    protected double saldo;
    protected String tipoCuenta;
    protected String nombre;
    protected Transaccion transaccion;
    
    public Cuenta() {
    }

    public Cuenta(String codigo, double saldo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "codigo=" + codigo + ", saldo=" +  saldo+ ", nombre=" + nombre + "}\n"+transaccion.toString()+"\n\n";
    }
    
    public Transaccion crearTransaccion(){
        transaccion  = new Transaccion();
        
        return transaccion;
    }
    
    public void transaccionCuenta(Transaccion trans){
        transaccion = trans;
    }
    
        public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    
        public void acutalizarSaldo(Cuenta cuenta, Connection conexion2){
        
            try {
            //String sentenciaSql3 = "INSERT INTO catalogo_cuentas(codigo,nombre,saldo)VALUES (?,?,?) "; 
            String sentenciaSql3 = "UPDATE catalogo_cuentas SET saldo=? where codigo=?"; 
            PreparedStatement preparedStatement = conexion2.prepareStatement(sentenciaSql3);
            
           // preparedStatement.setString(1,cuenta.nombre);
            preparedStatement.setDouble(1, cuenta.getSaldo());
            preparedStatement.setString(2,cuenta.getCodigo());
            preparedStatement.execute();
            
          /*  preparedStatement.setString(1, cuenta.codigo);
            preparedStatement.setString(2,cuenta.nombre);
            preparedStatement.setDouble(3,cuenta.saldo);
            
            preparedStatement.execute();
            */
           cuenta.transaccion.ingresarTransaccion(cuenta,conexion2);
            
        } catch (SQLException ex) {
            Logger.getLogger(ex.getLocalizedMessage());
        }
      }
    
    
    
}
