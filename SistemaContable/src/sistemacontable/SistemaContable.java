package sistemacontable;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SistemaContable {

    ArrayList<Cuenta> catalogo = new ArrayList<Cuenta>();
    ArrayList<Cuenta> Activo = new ArrayList<Cuenta>();
    ArrayList<Cuenta> Pasivo = new ArrayList<Cuenta>();
    ArrayList<Cuenta> Capital = new ArrayList<Cuenta>();
    ArrayList<Cuenta> Gasto = new ArrayList<Cuenta>();
    ArrayList<Cuenta> Ingreso = new ArrayList<Cuenta>();
    
    Conexion CR = new Conexion();

    public Connection conexion = CR.conexion();

    public static void main(String[] args) {
        
    }

    public void compras(Cuenta cuen, String cantidadcontado, String cantidadcredito, String formaPago, int tipo) {

        SistemaContable S = new SistemaContable();
        Conexion conec = new Conexion();

        int Nid;
        int Ntransaccion;
        Connection C = conec.conexion();
        double cantidadContado = 0.0;
        double cantidadCredito = 0.0;

        if (cuen.getCodigo().equals("117-06")) {
            tipo = 2;
        }

        if (cantidadcontado.length() != 0) {
            cantidadContado = Double.parseDouble(cantidadcontado);
        }
        if (cantidadcredito.length() != 0) {
            //System.out.println(cantidadcredito.length() + "credito");
            cantidadCredito = Double.parseDouble(cantidadcredito);
        }

        double saldo;
        S.eliminar();
        S.consulta();

        Nid = S.Nid();
        Ntransaccion = S.Ntransaccion();

        Calendar fecha = new GregorianCalendar();
        int mes = (fecha.get(Calendar.MONTH)) + 1;
        int año = fecha.get(Calendar.YEAR);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaActual = Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(año);

        switch (tipo) {
            case 1:/*compras con efectivo*/

                Cuenta compra = new Activo();
                Cuenta efectivo = new Activo();
                Cuenta CXP = new Pasivo();
                Cuenta IVACreditoFiscal = new Activo();
                //System.out.println(cuen.getCodigo());
                compra = S.buscar(cuen.getCodigo());
                efectivo = S.buscar(formaPago);
                IVACreditoFiscal = S.buscar("113-01");
                CXP = S.buscar("214-01");

                Transaccion trans;
                Transaccion trans2;
                Transaccion trans3 = null;
                Transaccion trans4 = null;

                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                trans = compra.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                trans.setCargar(cantidadContado + cantidadCredito);
                trans.setTipo(1);
                trans.setFecha(fechaActual);
                Nid++;

                saldo = compra.getSaldo();
                saldo = saldo + cantidadContado + cantidadCredito;
                compra.setSaldo(saldo);
                saldo = 0;
                //System.out.println(compra.toString());
                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

                trans2 = IVACreditoFiscal.crearTransaccion();
                double iva;
                double ivaCon = (cantidadContado) * 0.13;
                double ivaCre = (cantidadCredito) * 0.13;
                iva = ivaCon + ivaCre;
                trans2.setId(Nid);
                trans2.setnTransaccion(Ntransaccion);
                trans2.setCargar(iva);
                trans2.setTipo(1);
                trans2.setFecha(fechaActual);
                Nid++;

                saldo = IVACreditoFiscal.getSaldo();
                saldo = saldo + iva;
                IVACreditoFiscal.setSaldo(saldo);
                saldo = 0;

                //System.out.println(IVACreditoFiscal.toString());

                /**
                 * ***********************************************************
                 */
                if (cantidadContado != 0) {
                    trans3 = efectivo.crearTransaccion();
                    trans3.setId(Nid);
                    trans3.setnTransaccion(Ntransaccion);
                    trans3.setAbonar(cantidadContado + ivaCon);
                    trans3.setTipo(1);
                    trans3.setFecha(fechaActual);
                    Nid++;

                    saldo = efectivo.getSaldo();
                    saldo = saldo - cantidadContado - ivaCon;
                    efectivo.setSaldo(saldo);
                    saldo = 0;
                    //System.out.println(efectivo.toString());
                    efectivo.acutalizarSaldo(efectivo, C);
                }

                if (cantidadCredito != 0) {
                    trans4 = CXP.crearTransaccion();
                    trans4.setId(Nid);
                    trans4.setnTransaccion(Ntransaccion);
                    trans4.setAbonar(cantidadCredito + ivaCre);
                    trans4.setTipo(1);
                    trans4.setFecha(fechaActual);
                    Nid++;
                    Ntransaccion++;

                    saldo = CXP.getSaldo();
                    saldo = saldo + cantidadCredito + ivaCre;
                    CXP.setSaldo(saldo);
                    saldo = 0;
                    //System.out.println(CXP.toString());
                    CXP.acutalizarSaldo(CXP, C);
                }

              //System.out.println(CXP.toString());
                compra.acutalizarSaldo(compra, C);

                IVACreditoFiscal.acutalizarSaldo(IVACreditoFiscal, C);

                S.eliminar();
                S.consulta();
                break;

            case 2:
                Cuenta devYReb = new Ingreso();
                efectivo = new Activo();
                CXP = new Pasivo();
                IVACreditoFiscal = new Activo();
                //System.out.println(cuen.getCodigo());
                devYReb = S.buscar(cuen.getCodigo());
                efectivo = S.buscar(formaPago);
                IVACreditoFiscal = S.buscar("113-01");
                CXP = S.buscar("214-01");

                trans = null;
                trans2 = null;
                trans3 = null;
                trans4 = null;

                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                trans = devYReb.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                double Cantidad = cantidadContado + cantidadCredito;
                trans.setAbonar(Cantidad);
                trans.setTipo(2);
                trans.setFecha(fechaActual);
                Nid++;

                saldo = devYReb.getSaldo();
                saldo = saldo + Cantidad;
                devYReb.setSaldo(saldo);
                saldo = 0;
                //System.out.println(devYReb.toString());
                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

                trans2 = IVACreditoFiscal.crearTransaccion();
                iva = 0;
                ivaCon = (cantidadContado) * 0.13;
                ivaCre = (cantidadCredito) * 0.13;
                iva = ivaCon + ivaCre;
                trans2.setId(Nid);
                trans2.setnTransaccion(Ntransaccion);
                trans2.setAbonar(iva);
                trans2.setTipo(2);
                trans2.setFecha(fechaActual);
                Nid++;

                saldo = IVACreditoFiscal.getSaldo();
                saldo = saldo - iva;
                IVACreditoFiscal.setSaldo(saldo);
                saldo = 0;

                //System.out.println(IVACreditoFiscal.toString());

                /**
                 * ***********************************************************
                 */
                if (cantidadContado != 0) {
                    trans3 = efectivo.crearTransaccion();
                    trans3.setId(Nid);
                    trans3.setnTransaccion(Ntransaccion);
                    trans3.setCargar(cantidadContado + ivaCon);
                    trans3.setTipo(2);
                    trans3.setFecha(fechaActual);
                    Nid++;

                    saldo = efectivo.getSaldo();
                    saldo = saldo + cantidadContado + ivaCon;
                    efectivo.setSaldo(saldo);
                    saldo = 0;
                    //System.out.println(efectivo.toString());
                    efectivo.acutalizarSaldo(efectivo, C);
                }

                if (cantidadCredito != 0) {
                    trans4 = CXP.crearTransaccion();
                    trans4.setId(Nid);
                    trans4.setnTransaccion(Ntransaccion);
                    trans4.setCargar(cantidadCredito + ivaCre);
                    trans4.setTipo(2);
                    trans4.setFecha(fechaActual);
                    Nid++;
                    Ntransaccion++;

                    saldo = CXP.getSaldo();
                    saldo = saldo - cantidadCredito - ivaCre;
                    CXP.setSaldo(saldo);
                    saldo = 0;
                    //System.out.println(CXP.toString());
                    CXP.acutalizarSaldo(CXP, C);
                }

              //System.out.println(CXP.toString());
                devYReb.acutalizarSaldo(devYReb, C);

                IVACreditoFiscal.acutalizarSaldo(IVACreditoFiscal, C);

                S.eliminar();
                S.consulta();
                break;
        }

    }

    public void ventas(Cuenta cuen, String cantidadcontado, String cantidadcredito, String cantidaddescuento, String formaPago, int tipo) {

        SistemaContable S = new SistemaContable();
        Conexion conec = new Conexion();
        int Nid;
        int Ntransaccion;
        Connection C = conec.conexion();
        double cantidadContado = 0.0;
        double cantidadCredito = 0.0;
        double cantidadDescuento = 0.0;

        if (cuen.getCodigo().equals("511-02")) {
            tipo = 2;
        }

        if (cantidadcontado.length() != 0) {
            cantidadContado = Double.parseDouble(cantidadcontado);
        }
        if (cantidadcredito.length() != 0) {
            cantidadCredito = Double.parseDouble(cantidadcredito);
        }
        if (cantidaddescuento.length() != 0) {
            cantidadDescuento = Double.parseDouble(cantidaddescuento);
        }

        double saldo;
        S.eliminar();
        S.consulta();

        Nid = S.Nid();
        Ntransaccion = S.Ntransaccion();

        Calendar fecha = new GregorianCalendar();
        int mes = (fecha.get(Calendar.MONTH)) + 1;
        int año = fecha.get(Calendar.YEAR);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaActual = Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(año);

        switch (tipo) {
            case 1:/*ventas*/

                Cuenta venta = new Ingreso();
                Cuenta efectivo = new Activo();
                Cuenta IVADebitoFiscal = new Pasivo();
                Cuenta desboni = new Gasto();

                //System.out.println(cuen.getCodigo());
                venta = S.buscar(cuen.getCodigo());
                efectivo = S.buscar(formaPago);
                IVADebitoFiscal = S.buscar("218-01");
                desboni = S.buscar("412-06");

                Transaccion trans = null;
                Transaccion trans2 = null;
                Transaccion trans3 = null;
                Transaccion trans4 = null;

                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                trans = venta.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                trans.setAbonar(cantidadContado);
                trans.setTipo(3);
                trans.setFecha(fechaActual);
                Nid++;

                saldo = venta.getSaldo();
                saldo = saldo + cantidadContado;
                venta.setSaldo(saldo);
                saldo = 0;
               // System.out.println(venta.toString());
                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

                trans2 = IVADebitoFiscal.crearTransaccion();
                double iva;
                double ivaCon = (cantidadContado - cantidadDescuento) * 0.13;
                iva = ivaCon;
                trans2.setId(Nid);
                trans2.setnTransaccion(Ntransaccion);
                trans2.setAbonar(iva);
                trans2.setTipo(3);
                trans2.setFecha(fechaActual);
                Nid++;

                saldo = IVADebitoFiscal.getSaldo();
                saldo = saldo + iva;
                IVADebitoFiscal.setSaldo(saldo);
                saldo = 0;

               // System.out.println(IVADebitoFiscal.toString());

                /**
                 * ***********************************************************
                 */
                if (cantidadContado != 0) {
                    trans3 = efectivo.crearTransaccion();
                    trans3.setId(Nid);
                    trans3.setnTransaccion(Ntransaccion);
                    trans3.setCargar(cantidadContado - cantidadDescuento + ivaCon);
                    trans3.setTipo(3);
                    trans3.setFecha(fechaActual);
                    Nid++;

                    saldo = efectivo.getSaldo();
                    saldo = saldo + cantidadContado + ivaCon - cantidadDescuento;
                    efectivo.setSaldo(saldo);
                    saldo = 0;
                    //System.out.println(efectivo.toString());
                    efectivo.acutalizarSaldo(efectivo, C);

                    if (cantidadDescuento != 0) {
                        trans4 = desboni.crearTransaccion();
                        trans4.setId(Nid);
                        trans4.setnTransaccion(Ntransaccion);
                        trans4.setCargar(cantidadDescuento);
                        trans4.setTipo(3);
                        trans4.setFecha(fechaActual);
                        Nid++;

                        saldo = desboni.getSaldo();
                        saldo = cantidadDescuento;
                        desboni.setSaldo(saldo);
                        saldo = 0;
                        desboni.acutalizarSaldo(desboni, C);
                    }

                }

              //System.out.println(CXP.toString());
                venta.acutalizarSaldo(venta, C);

                IVADebitoFiscal.acutalizarSaldo(IVADebitoFiscal, C);

                S.eliminar();
                S.consulta();
                break;

            case 2:/*ventas*/

                venta = new Activo();
                Cuenta CXC = new Pasivo();
                IVADebitoFiscal = new Pasivo();

                //System.out.println(cuen.getCodigo());
                venta = S.buscar(cuen.getCodigo());
                IVADebitoFiscal = S.buscar("218-01");
                CXC = S.buscar("112-01");

                trans = null;
                trans2 = null;
                trans3 = null;
                trans4 = null;

                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                trans = venta.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                trans.setAbonar(cantidadCredito);
                trans.setTipo(4);
                trans.setFecha(fechaActual);
                Nid++;

                saldo = venta.getSaldo();
                saldo = saldo + cantidadCredito;
                venta.setSaldo(saldo);
                saldo = 0;
                //System.out.println(venta.toString());
                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

                trans2 = IVADebitoFiscal.crearTransaccion();
                iva = 0;

                double ivaCre = (cantidadCredito) * 0.13;
                iva = ivaCre;
                trans2.setId(Nid);
                trans2.setnTransaccion(Ntransaccion);
                trans2.setAbonar(iva);
                trans2.setTipo(4);
                trans2.setFecha(fechaActual);
                Nid++;

                saldo = IVADebitoFiscal.getSaldo();
                saldo = saldo + iva;
                IVADebitoFiscal.setSaldo(saldo);
                saldo = 0;

                //System.out.println(IVADebitoFiscal.toString());

                /**
                 * ***********************************************************
                 */
                if (cantidadCredito != 0) {
                    trans4 = CXC.crearTransaccion();
                    trans4.setId(Nid);
                    trans4.setnTransaccion(Ntransaccion);
                    trans4.setCargar(cantidadCredito + ivaCre);
                    trans4.setTipo(4);
                    trans4.setFecha(fechaActual);
                    Nid++;
                    Ntransaccion++;

                    saldo = CXC.getSaldo();
                    saldo = saldo + cantidadCredito + ivaCre;
                    CXC.setSaldo(saldo);
                    saldo = 0;
                    //System.out.println(CXC.toString());
                    CXC.acutalizarSaldo(CXC, C);
                }

              //System.out.println(CXP.toString());
                venta.acutalizarSaldo(venta, C);

                IVADebitoFiscal.acutalizarSaldo(IVADebitoFiscal, C);

                S.eliminar();
                S.consulta();
                break;
        }

    }

    public void pago(Cuenta cuen, String cantidadcontado, String formaPago, int tipo) {

        SistemaContable S = new SistemaContable();
        Conexion conec = new Conexion();
        int Nid;
        int Ntransaccion;
        Connection C = conec.conexion();
        double cantidadContado = 0.0;

        String cod = cuen.getCodigo();

        switch (cod) {
            case "112-01":
                tipo = 2;
                break;
            case "219-01-05":
                tipo = 2;
                break;
        }

        if (cantidadcontado.length() != 0) {
            cantidadContado = Double.parseDouble(cantidadcontado);
        }

        double saldo;
        S.eliminar();
        S.consulta();

        Nid = Nid();
        Ntransaccion = S.Ntransaccion();

        Calendar fecha = new GregorianCalendar();
        int mes = (fecha.get(Calendar.MONTH)) + 1;
        int año = fecha.get(Calendar.YEAR);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaActual = Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(año);

        switch (tipo) {
            case 1:/*pago de servicios*/

                Cuenta Pagos = new Activo();
                Cuenta efectivo = new Activo();

               // System.out.println(cuen.getCodigo());
                Pagos = S.buscar(cuen.getCodigo());
                efectivo = S.buscar(formaPago);

                Transaccion trans = null;
                Transaccion trans2 = null;
                Transaccion trans3 = null;
                Transaccion trans4 = null;

                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                trans = Pagos.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                trans.setCargar(cantidadContado);
                trans.setTipo(5);
                trans.setFecha(fechaActual);
                Nid++;

                saldo = Pagos.getSaldo();
                 if(Pagos.getCodigo().equals("214-01")){
                     saldo = saldo - cantidadContado;
                 }
                 else{
                     saldo = saldo + cantidadContado;
                 }

                Pagos.setSaldo(saldo);
                saldo = 0;
                //System.out.println(Pagos.toString());
                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

                /**
                 * ***********************************************************
                 */
                if (cantidadContado != 0) {
                    trans3 = efectivo.crearTransaccion();
                    trans3.setId(Nid);
                    trans3.setnTransaccion(Ntransaccion);
                    trans3.setAbonar(cantidadContado);
                    trans3.setTipo(5);
                    trans3.setFecha(fechaActual);
                    Nid++;

                    saldo = efectivo.getSaldo();
                    saldo = saldo - cantidadContado;
                    efectivo.setSaldo(saldo);
                    saldo = 0;
                    //System.out.println(efectivo.toString());
                    efectivo.acutalizarSaldo(efectivo, C);
                }

              //System.out.println(CXP.toString());
                Pagos.acutalizarSaldo(Pagos, C);

                S.eliminar();
                S.consulta();
                break;

            case 2:
                Cuenta cobros = new Activo();

                //System.out.println(cuen.getCodigo());
                cobros = S.buscar(cuen.getCodigo());
                efectivo = S.buscar(formaPago);

                trans = null;
                trans2 = null;
                trans3 = null;
                trans4 = null;

                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                trans = cobros.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                trans.setAbonar(cantidadContado);
                trans.setTipo(6);
                trans.setFecha(fechaActual);
                Nid++;

                saldo = cobros.getSaldo();
                saldo = saldo + cantidadContado;
                cobros.setSaldo(saldo);
                saldo = 0;
                //System.out.println(cobros.toString());
                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

                /**
                 * ***********************************************************
                 */
                if (cantidadContado != 0) {
                    trans3 = efectivo.crearTransaccion();
                    trans3.setId(Nid);
                    trans3.setnTransaccion(Ntransaccion);
                    trans3.setCargar(cantidadContado);
                    trans3.setTipo(6);
                    trans3.setFecha(fechaActual);
                    Nid++;

                    saldo = efectivo.getSaldo();
                    saldo = saldo - cantidadContado;
                    efectivo.setSaldo(saldo);
                    saldo = 0;
                   // System.out.println(efectivo.toString());
                    efectivo.acutalizarSaldo(efectivo, C);
                }

              //System.out.println(CXP.toString());
                cobros.acutalizarSaldo(cobros, C);

                S.eliminar();
                S.consulta();
                break;
        }

    }

    public void pagoMantenimiento(Cuenta cuen, String cantidadcontado, String cantidadcredito, String formaPago, int tipo) {

        SistemaContable S = new SistemaContable();
        Conexion conec = new Conexion();
        int Nid;
        int Ntransaccion;
        Connection C = conec.conexion();
        double cantidadContado = 0.0;
        double cantidadCredito = 0.0;

        if (cantidadcontado.length() != 0) {
            cantidadContado = Double.parseDouble(cantidadcontado);
        }
        if (cantidadcredito.length() != 0) {
            cantidadCredito = Double.parseDouble(cantidadcredito);
        }

        double saldo;
        S.eliminar();
        S.consulta();

        Nid = Nid();
        Ntransaccion = S.Ntransaccion();

        Calendar fecha = new GregorianCalendar();
        int mes = (fecha.get(Calendar.MONTH)) + 1;
        int año = fecha.get(Calendar.YEAR);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaActual = Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(año);

        switch (tipo) {
            case 1:/*pago de servicios*/

                Cuenta pagosMantenimiento = new Activo();
                Cuenta efectivo = new Activo();
                Cuenta IVACreditoFiscal = new Activo();
                Cuenta CXP = new Activo();

               // System.out.println(cuen.getCodigo());
                pagosMantenimiento = S.buscar(cuen.getCodigo());
                efectivo = S.buscar(formaPago);
                IVACreditoFiscal = S.buscar("113-01");
                CXP = S.buscar("214-01");

                Transaccion trans = null;
                Transaccion trans2 = null;
                Transaccion trans3 = null;
                Transaccion trans4 = null;

                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                trans = pagosMantenimiento.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                trans.setCargar(cantidadContado+cantidadCredito);
                trans.setTipo(7);
                trans.setFecha(fechaActual);
                Nid++;

                saldo = pagosMantenimiento.getSaldo();
                saldo = saldo + cantidadContado;
                pagosMantenimiento.setSaldo(saldo);
                saldo = 0;
               // System.out.println(pagosMantenimiento.toString());
                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

                trans2 = IVACreditoFiscal.crearTransaccion();
                double iva;
                double ivaCon = (cantidadContado) * 0.13;
                double ivaCre = (cantidadCredito) * 0.13;
                iva = ivaCon + ivaCre;
                trans2.setId(Nid);
                trans2.setnTransaccion(Ntransaccion);
                trans2.setCargar(iva);
                trans2.setTipo(7);
                trans2.setFecha(fechaActual);
                Nid++;

                saldo = IVACreditoFiscal.getSaldo();
                saldo = saldo + iva;
                IVACreditoFiscal.setSaldo(saldo);
                saldo = 0;

                IVACreditoFiscal.acutalizarSaldo(IVACreditoFiscal, C);

                /**
                 * ***********************************************************
                 */
                if (cantidadContado != 0) {
                    trans3 = efectivo.crearTransaccion();
                    trans3.setId(Nid);
                    trans3.setnTransaccion(Ntransaccion);
                    trans3.setAbonar(cantidadContado + ivaCon);
                    trans3.setTipo(7);
                    trans3.setFecha(fechaActual);
                    Nid++;

                    saldo = efectivo.getSaldo();
                    saldo = saldo - cantidadContado - ivaCon;
                    efectivo.setSaldo(saldo);
                    saldo = 0;
                    //System.out.println(efectivo.toString());
                    efectivo.acutalizarSaldo(efectivo, C);
                }

                if (cantidadCredito != 0) {
                    trans4 = CXP.crearTransaccion();
                    trans4.setId(Nid);
                    trans4.setnTransaccion(Ntransaccion);
                    trans4.setAbonar(cantidadCredito + ivaCre);
                    trans4.setTipo(7);
                    trans4.setFecha(fechaActual);
                    Nid++;
                    Ntransaccion++;

                    saldo = CXP.getSaldo();
                    saldo = saldo + cantidadCredito + ivaCre;
                    CXP.setSaldo(saldo);
                    saldo = 0;
                    //System.out.println(CXP.toString());
                    CXP.acutalizarSaldo(CXP, C);
                }

                pagosMantenimiento.acutalizarSaldo(pagosMantenimiento, C);

                S.eliminar();
                S.consulta();
                break;

        }

    }

    public void AumentoActivos(Cuenta cuen, String cantidadcontado, String formaPago, int tipo) {

        SistemaContable S = new SistemaContable();
        Conexion conec = new Conexion();
        int Nid;
        int Ntransaccion;
        Connection C = conec.conexion();
        double cantidadContado = 0.0;

        String cod = cuen.getCodigo();

        switch (cod) {
            case "118-01":
                tipo = 2;
            break;
            case "221-01":
                tipo = 3;
            break;

        }

        if (cantidadcontado.length() != 0) {
            cantidadContado = Double.parseDouble(cantidadcontado);
        }

        double saldo;
        S.eliminar();
        S.consulta();

        Nid = Nid();
        Ntransaccion = S.Ntransaccion();

        Calendar fecha = new GregorianCalendar();
        int mes = (fecha.get(Calendar.MONTH)) + 1;
        int año = fecha.get(Calendar.YEAR);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaActual = Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(año);

        switch (tipo) {
            case 1:/*aumento activo compras*/

                Cuenta aumentoActivo = new Activo();
                Cuenta efectivo = new Activo();

                //System.out.println(cuen.getCodigo());
                aumentoActivo = S.buscar(cuen.getCodigo());
                efectivo = S.buscar(formaPago);

                Transaccion trans = null;
                Transaccion trans2 = null;
                Transaccion trans3 = null;
                Transaccion trans4 = null;

                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                trans = aumentoActivo.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                trans.setCargar(cantidadContado);
                trans.setTipo(8);
                trans.setFecha(fechaActual);
                Nid++;

                saldo = aumentoActivo.getSaldo();
                saldo = saldo + cantidadContado;
                aumentoActivo.setSaldo(saldo);
                saldo = 0;
                //System.out.println(aumentoActivo.toString());
                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

                /**
                 * ***********************************************************
                 */
                if (cantidadContado != 0) {
                    trans3 = efectivo.crearTransaccion();
                    trans3.setId(Nid);
                    trans3.setnTransaccion(Ntransaccion);
                    trans3.setAbonar(cantidadContado);
                    trans3.setTipo(8);
                    trans3.setFecha(fechaActual);
                    Nid++;

                    saldo = efectivo.getSaldo();
                    saldo = saldo - cantidadContado;
                    efectivo.setSaldo(saldo);
                    saldo = 0;
                    //System.out.println(efectivo.toString());
                    efectivo.acutalizarSaldo(efectivo, C);
                }

              //System.out.println(CXP.toString());
                aumentoActivo.acutalizarSaldo(aumentoActivo, C);

                S.eliminar();
                S.consulta();
                break;
            case 2: //aumento de inversiones

                Cuenta patrimonio = new Capital();

                aumentoActivo = S.buscar(cuen.getCodigo());
                patrimonio = S.buscar("311-02");

                trans = null;
                trans2 = null;
                trans3 = null;
                trans4 = null;

                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                trans = aumentoActivo.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                trans.setCargar(cantidadContado);
                trans.setTipo(9);
                trans.setFecha(fechaActual);
                Nid++;

                saldo = aumentoActivo.getSaldo();
                saldo = saldo + cantidadContado;
                aumentoActivo.setSaldo(saldo);
                saldo = 0;
               // System.out.println(aumentoActivo.toString());
                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

                /**
                 * ***********************************************************
                 */
                if (cantidadContado != 0) {
                    trans3 = patrimonio.crearTransaccion();
                    trans3.setId(Nid);
                    trans3.setnTransaccion(Ntransaccion);
                    trans3.setAbonar(cantidadContado);
                    trans3.setTipo(9);
                    trans3.setFecha(fechaActual);
                    Nid++;

                    saldo = patrimonio.getSaldo();
                    saldo = saldo + cantidadContado;
                    patrimonio.setSaldo(saldo);
                    saldo = 0;
                    //System.out.println(patrimonio.toString());
                    patrimonio.acutalizarSaldo(patrimonio, C);
                }

              //System.out.println(CXP.toString());
                aumentoActivo.acutalizarSaldo(aumentoActivo, C);

                S.eliminar();
                S.consulta();

                break;
            case 3: //prestamo

                Cuenta prestamo = new Capital();
                
                aumentoActivo = S.buscar(cuen.getCodigo());
                prestamo = S.buscar(formaPago);

                trans = null;
                trans2 = null;
                trans3 = null;
                trans4 = null;

                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                trans = aumentoActivo.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                trans.setAbonar(cantidadContado);
                trans.setTipo(10);
                trans.setFecha(fechaActual);
                Nid++;

                saldo = aumentoActivo.getSaldo();
                saldo = saldo + cantidadContado;
                aumentoActivo.setSaldo(saldo);
                saldo = 0;
                //System.out.println(aumentoActivo.toString());
                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

                /**
                 * ***********************************************************
                 */
                if (cantidadContado != 0) {
                    trans3 = prestamo.crearTransaccion();
                    trans3.setId(Nid);
                    trans3.setnTransaccion(Ntransaccion);
                    trans3.setCargar(cantidadContado);
                    trans3.setTipo(10);
                    trans3.setFecha(fechaActual);
                    Nid++;

                    saldo = prestamo.getSaldo();
                    saldo = saldo + cantidadContado;
                    prestamo.setSaldo(saldo);
                    saldo = 0;
                    //System.out.println(prestamo.toString());
                    prestamo.acutalizarSaldo(prestamo, C);
                }

              //System.out.println(CXP.toString());
                aumentoActivo.acutalizarSaldo(aumentoActivo, C);

                S.eliminar();
                S.consulta();

                break;

        }
    }
    
     public void ajuste(Cuenta cuen1, String saldoT, int tipo){
        
        SistemaContable S = new SistemaContable();
        Conexion conec = new Conexion();
        int Nid;
        int Ntransaccion;
        Connection C = conec.conexion();
  
        S.consulta();
        double saldo1=0.0;
        double saldo=0.0;
        
        if (saldoT.length() != 0) {
            saldo = Double.parseDouble(saldoT);
        }
        
        String cod = cuen1.getCodigo();

        if(cod.equals("119-04")  || cod.equals("119-01") || cod.equals("119-05") || cod.equals("119-10") || cod.equals("119-02")){
            tipo = 2;
        }
        else{
            if(cod.equals("121-01") || cod.equals("121-02") || cod.equals("121-03") || cod.equals("121-04") ){
                tipo = 3;
            }
            else{
                if(cod.equals("121-05")){
                    tipo = 4;
                }
                else{
                    if(cod.equals("117-07")){
                        tipo = 5;    
                    }
                    else{
                        tipo = 1;
                    }
                    
                }
            }
        }
        
                
        Nid = S.Nid();
        Ntransaccion = S.Ntransaccion();

        Calendar fecha = new GregorianCalendar();
        int mes = (fecha.get(Calendar.MONTH))+1;
        int año = fecha.get(Calendar.YEAR);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaActual = Integer.toString(dia)+"/"+Integer.toString(mes)+"/"+Integer.toString(año);
       
        
        
        switch (tipo){
            case 1://calculo de cuentas incobrables
                Cuenta cliente = new Activo();
                Cuenta estimaCuentaIncobrable = new Activo();
                cliente = S.buscar("112-01");
                estimaCuentaIncobrable = S.buscar("112-05");
                
                Transaccion trans;
                Transaccion trans2;
                
                trans = estimaCuentaIncobrable.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                trans.setCargar(saldo);   
                trans.setTipo(11);
                trans.setFecha(fechaActual);
                Nid++;

                
                saldo1 = estimaCuentaIncobrable.getSaldo();
                saldo1 = saldo1+saldo;
                estimaCuentaIncobrable.setSaldo(saldo1);
                saldo1 =0;   
                //System.out.println(estimaCuentaIncobrable.toString());
                estimaCuentaIncobrable.acutalizarSaldo(estimaCuentaIncobrable,C);
              
                trans2 = cliente.crearTransaccion();
                trans2.setId(Nid);
                trans2.setnTransaccion(Ntransaccion);
                trans2.setAbonar(saldo);  
                trans2.setTipo(11);
                trans2.setFecha(fechaActual);
                Nid++;

                
                saldo1 = cliente.getSaldo();
                saldo1 = saldo1-saldo;
                cliente.setSaldo(saldo1);
                saldo1 =0;   
               // System.out.println(cliente.toString());
                cliente.acutalizarSaldo(cliente,C);
              
                S.eliminar();
                S.consulta();
                break;
            case 2:      // gastos pagados por anticipados
                Cuenta activoExtrae = new Activo(); // guarda cuenta activo
                Cuenta gastoPagado = new Gasto();  // gasto acumulado
                
                if(cuen1.getCodigo().equals("119-05")){  // gastos por publicidad
                activoExtrae = S.buscar("119-05");
                gastoPagado = S.buscar("413-41");
                }else{
                    if (cuen1.getCodigo().equals("119-04")){  // gastos por luz
                        activoExtrae = S.buscar("119-04");
                        gastoPagado = S.buscar("413-15");
                    }else{
                        if(cuen1.getCodigo().equals("119-01")){  // gasto por seguros
                            activoExtrae = S.buscar("119-01");
                            gastoPagado = S.buscar("412-38");
                        }else{ if  (cuen1.getCodigo().equals("119-02")){      // gasto por papeleria y utiles
                                activoExtrae = S.buscar("119-02");
                                gastoPagado = S.buscar("413-13");
                                }else{if(cuen1.getCodigo().equals("412-10")){  // lubricante y combustibles
                                    activoExtrae = S.buscar("412-10");
                                    gastoPagado = S.buscar("413-11");
                                }else{
                                    JOptionPane.showMessageDialog(null, "no existe la cuenta");// caso de error
                                    }
                                }
                        }
                    }
                }
                
                
                //Transaccion trans;
                //Transaccion trans2;
                
                trans = gastoPagado.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                trans.setCargar(saldo);   
                trans.setTipo(12);
                trans.setFecha(fechaActual);
                Nid++;

                
                saldo1 = gastoPagado.getSaldo();
                saldo1 = saldo1+saldo;
                gastoPagado.setSaldo(saldo1);
                saldo1 =0;   
                //System.out.println(gastoPagado.toString());
                gastoPagado.acutalizarSaldo(gastoPagado,C);
              
                trans2 = activoExtrae.crearTransaccion();
                trans2.setId(Nid);
                trans2.setnTransaccion(Ntransaccion);
                trans2.setAbonar(saldo);  
                trans2.setTipo(12);
                trans2.setFecha(fechaActual);
                Nid++;

                
                saldo1 = activoExtrae.getSaldo();
                saldo1 = saldo1-saldo;
                activoExtrae.setSaldo(saldo1);
                saldo1 =0;   
               // System.out.println(activoExtrae.toString());
                activoExtrae.acutalizarSaldo(activoExtrae,C);
              
                S.eliminar();
                S.consulta();
               
                break;
            case 3:  // depreciacion
                Cuenta depreciacionAcumulada = new Activo();
                Cuenta gastoDepreciacion = new Gasto();
                
                if(cuen1.getCodigo().equals("121-01")){// depreciacion para edificio 
                    depreciacionAcumulada = S.buscar("121-06-01");
                    gastoDepreciacion = S.buscar("412-24"); 
                }else{
                    if (cuen1.getCodigo().equals("121-02")){  // depreciacion para instalaciones
                        depreciacionAcumulada = S.buscar("121-06-02");
                        gastoDepreciacion = S.buscar("412-25");
                    }else{
                        if(cuen1.getCodigo().equals("121-03")){  // depreciacion para mob y eq
                            depreciacionAcumulada = S.buscar("121-06-03");
                            gastoDepreciacion = S.buscar("412-26");
                        }else{if(cuen1.getCodigo().equals("121-04")){  // depreciacion para vehiculo
                            depreciacionAcumulada = S.buscar("121-06-03");
                            gastoDepreciacion = S.buscar("412-28");
                            }else{
                                JOptionPane.showMessageDialog(null, "no existe la cuenta"); // caso de error
                            }
                        }
                    }
                }
                //Transaccion trans;
                //Transaccion trans2;
                
                trans = gastoDepreciacion.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                trans.setCargar(saldo);   
                trans.setTipo(13);
                trans.setFecha(fechaActual);
                Nid++;

                
                saldo1 = gastoDepreciacion.getSaldo();
                saldo1 = saldo1+saldo;
                gastoDepreciacion.setSaldo(saldo1);
                saldo1 =0;   
                //System.out.println(gastoDepreciacion.toString());
                gastoDepreciacion.acutalizarSaldo(gastoDepreciacion,C);
              
                trans2 = depreciacionAcumulada.crearTransaccion();
                trans2.setId(Nid);
                trans2.setnTransaccion(Ntransaccion);
                trans2.setAbonar(saldo);  
                trans2.setTipo(13);
                trans2.setFecha(fechaActual);
                Nid++;

                
                saldo1 = depreciacionAcumulada.getSaldo();
                saldo1 = saldo1-saldo;
                depreciacionAcumulada.setSaldo(saldo1);
                saldo1 =0;   
               // System.out.println(depreciacionAcumulada.toString());
                depreciacionAcumulada.acutalizarSaldo(depreciacionAcumulada,C);
              
                S.eliminar();
                S.consulta();
                
                break;
            case 4:   //gastos acumulados
                Cuenta estimaCtaIncobrable = new Activo();
                Cuenta gastoVario = new Gasto();
                estimaCtaIncobrable = S.buscar("121-05");
                gastoVario = S.buscar("412-66");
                
                //Transaccion trans;
                //Transaccion trans2;
                
                trans = gastoVario.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                trans.setCargar(saldo);   
                trans.setTipo(14);
                trans.setFecha(fechaActual);
                Nid++;

                
                saldo1 = gastoVario.getSaldo();
                saldo1 = saldo1+saldo;
                gastoVario.setSaldo(saldo1);
                saldo1 =0;   
               // System.out.println(gastoVario.toString());
                gastoVario.acutalizarSaldo(gastoVario,C);
              
                trans2 = estimaCtaIncobrable.crearTransaccion();
                trans2.setId(Nid);
                trans2.setnTransaccion(Ntransaccion);
                trans2.setAbonar(saldo);  
                trans2.setTipo(14);
                trans2.setFecha(fechaActual);
                Nid++;

                
                saldo1 = estimaCtaIncobrable.getSaldo();
                saldo1 = saldo1-saldo;
                estimaCtaIncobrable.setSaldo(saldo1);
                saldo1 =0;   
               // System.out.println(estimaCtaIncobrable.toString());
                estimaCtaIncobrable.acutalizarSaldo(estimaCtaIncobrable,C);
              
                S.eliminar();
                S.consulta();
                
                break;
            case 5:
                Cuenta InventarioFinal = new Activo();
                
                InventarioFinal = S.buscar("117-07");

                             
                trans2 = InventarioFinal.crearTransaccion();
                trans2.setId(Nid);
                trans2.setnTransaccion(Ntransaccion);
                trans2.setCargar(saldo);  
                trans2.setTipo(15);
                trans2.setFecha(fechaActual);
                Nid++;

                InventarioFinal.setSaldo(saldo1);
                saldo1 =0;   
               // System.out.println(estimaCtaIncobrable.toString());
                InventarioFinal.acutalizarSaldo(InventarioFinal,C);
              
                S.eliminar();
                S.consulta();
            break;
        }
    
    }
     
    public void calculoCV(){
        SistemaContable S = new SistemaContable();
        Conexion conec = new Conexion();
        int Nid;
        int Ntransaccion;
        Connection C = conec.conexion();
        double comprasNetas = 0;
        double CV =0;

        double saldo;
        S.eliminar();
        S.consulta();

        Nid = Nid();
        Ntransaccion = S.Ntransaccion();

        Calendar fecha = new GregorianCalendar();
        int mes = (fecha.get(Calendar.MONTH)) + 1;
        int año = fecha.get(Calendar.YEAR);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaActual = Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(año);

                /*calculo de costo de lo vendido*/

                Cuenta costoDeLoVendido = new Gasto();
                Cuenta compras = new Activo();
                Cuenta rebajas = new Ingreso();
                Cuenta fletes = new Gasto();
                Cuenta inventarioF = new Activo();
                Cuenta inventarioI = new Activo();

                //System.out.println(cuen.getCodigo());
                costoDeLoVendido = S.buscar("411-01");
                compras = S.buscar("117-01");
                rebajas = S.buscar("117-06");
                fletes = S.buscar("412-62");
                inventarioF = S.buscar("117-07");
                inventarioI = S.buscar("117");

                Transaccion trans = null;
                Transaccion trans2 = null;
                Transaccion trans3 = null;
                Transaccion trans4 = null;
                Transaccion trans5 = null;

                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                saldo = compras.getSaldo();
                
                trans = compras.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                trans.setAbonar(saldo);
                trans.setTipo(16);
                trans.setFecha(fechaActual);
                Nid++;

                comprasNetas+=saldo;
                saldo = 0;
                compras.setSaldo(saldo);
                compras.acutalizarSaldo(compras, C);
                
                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

                /**
                 * ***********************************************************
                 */
                    saldo = rebajas.getSaldo();
                    
                    trans2 = rebajas.crearTransaccion();
                    trans2.setId(Nid);
                    trans2.setnTransaccion(Ntransaccion);
                    trans2.setCargar(saldo);
                    trans2.setTipo(16);
                    trans2.setFecha(fechaActual);
                    Nid++;

                    comprasNetas-=saldo;
                    saldo = 0;
                    rebajas.setSaldo(saldo);
                    
                    //System.out.println(efectivo.toString());
                    rebajas.acutalizarSaldo(rebajas, C);
                    
                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++*/    
                saldo = fletes.getSaldo();
                
                trans3 = fletes.crearTransaccion();
                trans3.setId(Nid);
                trans3.setnTransaccion(Ntransaccion);
                trans3.setAbonar(saldo);
                trans3.setTipo(16);
                trans3.setFecha(fechaActual);
                Nid++;

                comprasNetas+=saldo;
                saldo = 0;
                fletes.setSaldo(saldo);
                fletes.acutalizarSaldo(fletes, C);
                 
                /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                saldo = inventarioF.getSaldo();
                double saldoI = inventarioI.getSaldo();
                
                CV =  saldoI +comprasNetas - saldo;
               
                trans4 = costoDeLoVendido.crearTransaccion();
                trans4.setId(Nid);
                trans4.setnTransaccion(Ntransaccion);
                trans4.setCargar(CV);
                trans4.setTipo(16);
                trans4.setFecha(fechaActual);
                Nid++;

                saldo = 0;
                costoDeLoVendido.setSaldo(CV);
                costoDeLoVendido.acutalizarSaldo(costoDeLoVendido, C);
                
                /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                trans5 = inventarioI.crearTransaccion();
                trans5.setId(Nid);
                trans5.setnTransaccion(Ntransaccion);
                trans5.setCargar(saldoI);
                trans5.setTipo(16);
                trans5.setFecha(fechaActual);
                Nid++;

                saldo = 0;
                inventarioI.setSaldo(saldo);
                inventarioI.acutalizarSaldo(inventarioI, C);
                
                
                S.eliminar();
                S.consulta();
        }
    
    
     public void actualizarCuenta(Cuenta cuenta, double nuevo){
        SistemaContable S = new SistemaContable();
        Conexion conec = new Conexion();
        int Nid;
        int Ntransaccion;
        Connection C = conec.conexion();

        double saldo;
        S.eliminar();
        S.consulta();

        Nid = Nid();
        Ntransaccion = S.Ntransaccion();

        Calendar fecha = new GregorianCalendar();
        int mes = (fecha.get(Calendar.MONTH)) + 1;
        int año = fecha.get(Calendar.YEAR);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaActual = Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(año);

                /*calculo de costo de lo vendido*/

                Cuenta liquidada = new Gasto();


                //System.out.println(cuen.getCodigo());
                liquidada = S.buscar(cuenta.getCodigo());

                Transaccion trans = null;

                /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
                saldo = liquidada.getSaldo();
                
                trans = liquidada.crearTransaccion();
                trans.setId(Nid);
                trans.setnTransaccion(Ntransaccion);
                if(cuenta.getCodigo().equals("Ingresos")){
                    trans.setCargar(saldo);
                }
                else{
                    trans.setAbonar(saldo);
                }
                trans.setTipo(16);
                trans.setFecha(fechaActual);
                Nid++;

                saldo = 0;
                liquidada.setSaldo(nuevo);
                liquidada.acutalizarSaldo(liquidada, C);
                
                S.eliminar();
                S.consulta();
     }
     
    public void eliminar() {
        catalogo.clear();
    }

    public void consulta() {

        try {
            String sentenciaSql = "SELECT *FROM catalogo_cuentas";
            Statement statement = this.conexion.createStatement();

            ResultSet resultado = statement.executeQuery(sentenciaSql);

            while (resultado.next()) {
                Cuenta cuenta = new Cuenta();

                cuenta.codigo = resultado.getString("codigo");
                cuenta.nombre = resultado.getString("nombre");
                cuenta.saldo = resultado.getDouble("saldo");
                cuenta.tipoCuenta = resultado.getString("tipocuenta");

                  //System.out.println(cuenta.codigo);
                catalogo.add(cuenta);
            }

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(this,"Error al recuperar los productos de la base de datos");
            ex.printStackTrace();
        }
    }

    public Cuenta buscar(String codigo) {
        Cuenta cuenta = null;
        for (Cuenta c : catalogo) {

            if (c.codigo.equals(codigo)) {
                cuenta = c;
            }
        }
        return cuenta;
    }

    public int Nid() {
        int Nid = 0;
        try {
            String sentenciaSql = "SELECT *FROM transaccion";
            Statement statement = this.conexion.createStatement();

            ResultSet resultado = statement.executeQuery(sentenciaSql);

            while (resultado.next()) {
                Transaccion transaccion = new Transaccion();

                transaccion.setId(resultado.getInt("id"));

                if (Nid <= transaccion.getId()) {
                    Nid = transaccion.getId();
                }

            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(this,"Error al recuperar los productos de la base de datos");
            ex.printStackTrace();
        }
        Nid++;
        return Nid;
    }

    public int Ntransaccion() {
        int Ntran = 0;
        try {
            String sentenciaSql = "SELECT *FROM transaccion";
            Statement statement = this.conexion.createStatement();

            ResultSet resultado = statement.executeQuery(sentenciaSql);

            while (resultado.next()) {
                Transaccion transaccion = new Transaccion();

                transaccion.setnTransaccion(resultado.getInt("n_transaccion"));

                if (Ntran <= transaccion.getnTransaccion()) {
                    Ntran = transaccion.getnTransaccion();
                   // System.out.println(Ntran);
                }
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(this,"Error al recuperar los productos de la base de datos");
            ex.printStackTrace();
        }
        Ntran++;
        return Ntran;
    }
}
