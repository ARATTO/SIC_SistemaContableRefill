
package interfaces.realizarTransaccion;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sistemacontable.Cuenta;
import sistemacontable.SistemaContable;

/**
 * @author Dario
 */
public class frm_OpcionesVenta extends javax.swing.JInternalFrame {

    private Cuenta cuenta;
    ArrayList<Cuenta> formaPago = new ArrayList<Cuenta>();
    
    public void enviarCuenta(Cuenta cuenta){
        this.cuenta = cuenta;
    }
    public frm_OpcionesVenta() {
        initComponents();
        llenarCmbFormaPago();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_frmOpcionesVenta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmb_formaPagoVenta = new javax.swing.JComboBox();
        txt_ventaCantidad = new javax.swing.JTextField();
        txt_ventaDescuento = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_ventaDescripcion = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setTitle("Venta");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_frmOpcionesVenta.setText("Aceptar");
        btn_frmOpcionesVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_frmOpcionesVentaActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipo de pago: ");

        jLabel2.setText("Cantidad: ");

        jLabel3.setText("Descuento: ");

        jLabel5.setText("Descripcion:");

        cmb_formaPagoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_formaPagoVentaActionPerformed(evt);
            }
        });

        txt_ventaCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ventaCantidadKeyTyped(evt);
            }
        });

        txt_ventaDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ventaDescuentoActionPerformed(evt);
            }
        });
        txt_ventaDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ventaDescuentoKeyTyped(evt);
            }
        });

        txt_ventaDescripcion.setColumns(20);
        txt_ventaDescripcion.setRows(5);
        jScrollPane1.setViewportView(txt_ventaDescripcion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_frmOpcionesVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_formaPagoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ventaCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ventaDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 132, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(144, 144, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmb_formaPagoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ventaCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ventaDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(btn_frmOpcionesVenta)
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(248, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_frmOpcionesVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_frmOpcionesVentaActionPerformed
        try {
            SistemaContable S = new SistemaContable();
            String pago = obtenerCodigoFormaPago();
            S.ventas(this.cuenta, txt_ventaCantidad.getText(), this.txt_ventaCantidad.getText(), this.txt_ventaDescuento.getText(), pago, 1);
             JOptionPane.showMessageDialog(this, "Transacción Exitosa");
            limpiarForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un problema al momentos de Realizar la transacción");
        }
        
    }//GEN-LAST:event_btn_frmOpcionesVentaActionPerformed

    private void txt_ventaDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ventaDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ventaDescuentoActionPerformed

    private void cmb_formaPagoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_formaPagoVentaActionPerformed
        if(cmb_formaPagoVenta.getSelectedItem() == "Tarjeta"){
            txt_ventaDescuento.setEnabled(false);
            txt_ventaDescuento.setText("");
        }
        else{
            txt_ventaDescuento.setEnabled(true);
        }
    }//GEN-LAST:event_cmb_formaPagoVentaActionPerformed

    private void txt_ventaCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ventaCantidadKeyTyped
        char car = evt.getKeyChar();
        if( car<'0' || car>'9'){
            if(car!='.'){
                evt.consume();
            }
        }
    }//GEN-LAST:event_txt_ventaCantidadKeyTyped

    private void txt_ventaDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ventaDescuentoKeyTyped
        char car = evt.getKeyChar();
        if( car<'0' || car>'9'){
            if(car!='.'){
                evt.consume();
            }
        }
    }//GEN-LAST:event_txt_ventaDescuentoKeyTyped
    
    void limpiarForm(){
        txt_ventaCantidad.setText("");
        txt_ventaDescuento.setText("");
        txt_ventaDescripcion.setText("");
    }
    void llenarCmbFormaPago(){
        Conexion SIC = new Conexion();
        Connection con = SIC.conexion();
        
        String sentenciaSQL= "SELECT codigo, nombre FROM catalogo_cuentas WHERE codigo LIKE '111-01' OR codigo LIKE '111-02' OR codigo LIKE '111-03-01'";
        
        try {
            Statement statement = con.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            
            while (resultado.next()) {
                    Cuenta cuenta = new Cuenta();
                    cuenta.setNombre( resultado.getString("nombre") );
                    cuenta.setCodigo(resultado.getString("codigo") );
                    formaPago.add(cuenta);
                    if(cuenta.getNombre().equals("Banco Promerica")){
                        cmb_formaPagoVenta.addItem("Tarjeta");
                    }
                    else{
                        cmb_formaPagoVenta.addItem(cuenta.getNombre());
                    }
                            
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al llenar Tipo de Pago");
        }
    }
    
    ///METODO QUE RETORNA EL CODIGO DE LA FORMA DE PAGO SELECCIONADA
    String obtenerCodigoFormaPago(){
        String cmbSeleccionado="",codigo="";
        cmbSeleccionado = (String)cmb_formaPagoVenta.getSelectedItem();
        
        for (Cuenta C : formaPago) {
            if(C.getNombre().equals(cmbSeleccionado)){
                codigo = C.getCodigo();
            }
        }
        return codigo;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_frmOpcionesVenta;
    private javax.swing.JComboBox cmb_formaPagoVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_ventaCantidad;
    private javax.swing.JTextArea txt_ventaDescripcion;
    private javax.swing.JTextField txt_ventaDescuento;
    // End of variables declaration//GEN-END:variables
}
