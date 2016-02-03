
package sistemacontable;

public class Activo extends Cuenta{

    public Activo() {
    }

    public Activo(String codigo, double saldo, String nombre) {
        super(codigo, saldo,  nombre);
    }
  /*  
    public void aumentarSaldo(double monto){
    super.saldoDeudor = super.saldoDeudor+monto;
    }
    
    public void disminuirSaldo(double monto){
    super.saldoAcreedor = super.saldoAcreedor-monto;
    }
    
    public void cerrarCuenta(){
    super.saldo=saldoDeudor-saldoAcreedor;
    if(saldoDeudor>saldoAcreedor){
    saldoAcreedor=0;
    saldoDeudor=saldo;
    }
    else{
        if(saldoDeudor==saldoAcreedor){
        saldoDeudor=0;
        saldoAcreedor=0;
        }
        else{
        saldoDeudor=0;
        saldoAcreedor=saldo;
        }
        }
    }
    */
    
    
    
}
