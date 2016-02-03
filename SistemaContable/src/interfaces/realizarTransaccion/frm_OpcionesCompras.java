package interfaces.realizarTransaccion;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import sistemacontable.*;

import javax.swing.JOptionPane;
import sistemacontable.*;

/**
 * @author Dario
 */
public final class frm_OpcionesCompras extends javax.swing.JInternalFrame {
    private Cuenta cuenta;
    
    ArrayList<Cuenta> formaPago = new ArrayList<Cuenta>();
    
    public void enviarCuenta(Cuenta cuenta){
        this.cuenta = cuenta;
    }
    
    public frm_OpcionesCompras() {
        initComponents();
        llenarCmbFormaPago();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_compraCantidadContado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_compraCantidadCredito = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_compraDescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txt_compraNumeroFactura = new javax.swing.JTextField();
        btn_frmCompraAceptar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cmb_formaPagoCompras = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        setTitle("Formulario de Compra");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Cantidad al Contado:");

        txt_compraCantidadContado.setToolTipText("");
        txt_compraCantidadContado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_compraCantidadContadoKeyTyped(evt);
            }
        });

        jLabel2.setText("Cantidad al Credito:");

        txt_compraCantidadCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_compraCantidadCreditoKeyTyped(evt);
            }
        });

        jLabel3.setText("Descripcion:");

        txt_compraDescripcion.setColumns(20);
        txt_compraDescripcion.setRows(5);
        jScrollPane1.setViewportView(txt_compraDescripcion);

        jLabel4.setText("Numero de Factura:");

        txt_compraNumeroFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_compraNumeroFacturaKeyTyped(evt);
            }
        });

        btn_frmCompraAceptar.setText("Aceptar");
        btn_frmCompraAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_frmCompraAceptarActionPerformed(evt);
            }
        });

        jLabel6.setText("Forma de pago:");

        cmb_formaPagoCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_formaPagoComprasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_frmCompraAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 105, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_compraCantidadContado, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_compraCantidadCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_compraNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(30, 30, 30)
                            .addComponent(cmb_formaPagoCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(89, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_frmCompraAceptar)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(cmb_formaPagoCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txt_compraCantidadContado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_compraCantidadCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txt_compraNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel3)
                    .addGap(134, 134, 134)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_frmCompraAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_frmCompraAceptarActionPerformed
        
        try {
            SistemaContable S = new SistemaContable();
            String pago = obtenerCodigoFormaPago();
            S.compras(this.cuenta, txt_compraCantidadContado.getText(),txt_compraCantidadCredito.getText(),pago, 1);
            
            JOptionPane.showMessageDialog(this, "Transacción Exitosa");
            limpiarForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un problema al momentos de Realizar la transacción");
        }
        
        
    }//GEN-LAST:event_btn_frmCompraAceptarActionPerformed

    private void cmb_formaPagoComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_formaPagoComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_formaPagoComprasActionPerformed

    private void txt_compraCantidadContadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_compraCantidadContadoKeyTyped
        char car = evt.getKeyChar();
        if( car<'0' || car>'9'){
            if(car!='.'){
                evt.consume();
            }
        }
    }//GEN-LAST:event_txt_compraCantidadContadoKeyTyped

    private void txt_compraCantidadCreditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_compraCantidadCreditoKeyTyped
        char car = evt.getKeyChar();
        if( car<'0' || car>'9'){
            if(car!='.'){
                evt.consume();
            }
        }
    }//GEN-LAST:event_txt_compraCantidadCreditoKeyTyped

    private void txt_compraNumeroFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_compraNumeroFacturaKeyTyped
        char car = evt.getKeyChar();
        if( car<'0' || car>'9'){
         
                evt.consume();
         
        }
    }//GEN-LAST:event_txt_compraNumeroFacturaKeyTyped

    void limpiarForm(){
        txt_compraCantidadCredito.setText("");
        txt_compraCantidadContado.setText("");
        txt_compraDescripcion.setText("");
        txt_compraNumeroFactura.setText("");
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
                    
                    cmb_formaPagoCompras.addItem(cuenta.getNombre());
                    
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al llenar Tipo de Pago");
        }
    }
    
    ///METODO QUE RETORNA EL CODIGO DE LA FORMA DE PAGO SELECCIONADA
    String obtenerCodigoFormaPago(){
        String cmbSeleccionado="",codigo="";
        cmbSeleccionado = (String)cmb_formaPagoCompras.getSelectedItem();
        
        for (Cuenta C : formaPago) {
            if(C.getNombre().equals(cmbSeleccionado)){
                codigo = C.getCodigo();
            }
        }
        return codigo;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_frmCompraAceptar;
    private javax.swing.JComboBox cmb_formaPagoCompras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_compraCantidadContado;
    private javax.swing.JTextField txt_compraCantidadCredito;
    private javax.swing.JTextArea txt_compraDescripcion;
    private javax.swing.JTextField txt_compraNumeroFactura;
    // End of variables declaration//GEN-END:variables
}
