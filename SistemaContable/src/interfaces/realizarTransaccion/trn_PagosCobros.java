
package interfaces.realizarTransaccion;

import conexion.Conexion;
import interfaces.Escritorio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistemacontable.Cuenta;
import sistemacontable.SistemaContable;

/**
 * @author Dario
 */
public class trn_PagosCobros extends javax.swing.JInternalFrame {

    
    public trn_PagosCobros() {
        initComponents();
    }
    ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_continuarPagoCobro = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_tipoPagoCobro = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_cuentaPagoCobro = new javax.swing.JTable();
        btn_seleccionPagoCobro = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Pagos y Cobros");
        setToolTipText("");

        btn_continuarPagoCobro.setText("Continuar");
        btn_continuarPagoCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_continuarPagoCobroActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbl_tipoPagoCobro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Luz Electrica"},
                {"Pago de Salarios"},
                {"Pago de IVA"},
                {"Pago a Cuenta"},
                {"Pago a Cuentes por pagar"},
                {"Pago de Prestamos"},
                {"Pago de Seguridad"},
                {"Pago de Impuesto Fovial"},
                {"Pago de Honorarios"},
                {"Fletes"},
                {"Documentos y cuentas por cobrar"},
                {"Cobro de IVA"}
            },
            new String [] {
                "Transaccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_tipoPagoCobro);

        tbl_cuentaPagoCobro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Cuenta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl_cuentaPagoCobro);

        btn_seleccionPagoCobro.setText(">>");
        btn_seleccionPagoCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_seleccionPagoCobroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 716, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btn_seleccionPagoCobro)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 305, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(103, 103, 103)
                            .addComponent(btn_seleccionPagoCobro))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_continuarPagoCobro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_continuarPagoCobro)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_seleccionPagoCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionPagoCobroActionPerformed
        Escritorio.limpiarTabla(tbl_cuentaPagoCobro);
        cuentas.clear();
        int trn = tbl_tipoPagoCobro.getSelectedRow();
        Conexion SIC = new Conexion();
        Connection con = SIC.conexion();;
        
        pintarTblCuenta(trn, con);
    }//GEN-LAST:event_btn_seleccionPagoCobroActionPerformed

    private void btn_continuarPagoCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_continuarPagoCobroActionPerformed
        if(tbl_cuentaPagoCobro.getSelectedRow() != -1  ){
            frm_PagosCobros frm_pagosCobros = new frm_PagosCobros();
   
            
            int cnt = tbl_cuentaPagoCobro.getSelectedRow();
            frm_pagosCobros.enviarCuenta(cuentas.get(cnt));
            
            Escritorio.Escritorio.add(frm_pagosCobros);
            frm_pagosCobros.setLocation(Escritorio.Escritorio.getWidth()/2-frm_pagosCobros.getWidth()/2, Escritorio.Escritorio.getHeight()/2-frm_pagosCobros.getHeight()/2);
            frm_pagosCobros.show();
        }
        else{
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Cuenta para continuar con la transaccion");
        }
    }//GEN-LAST:event_btn_continuarPagoCobroActionPerformed
    private void pintarTblCuenta(int trn, Connection con){
        DefaultTableModel tbl_Cuenta = (DefaultTableModel) tbl_cuentaPagoCobro.getModel();
        
        String sentenciaSQL= "";
        switch(trn){
            case 0: 
                sentenciaSQL = "select *from catalogo_cuentas where codigo='119-04'";
            break;
            case 1:
                sentenciaSQL = "select *from catalogo_cuentas where codigo='412-01'";
            break;
            case 2:
                sentenciaSQL = "select *from catalogo_cuentas where codigo='219-01-03'";
            break;
            case 3:
                sentenciaSQL = "select *from catalogo_cuentas where codigo='219-01-04'";
            break;
            case 4:
                sentenciaSQL = "select *from catalogo_cuentas where codigo='214-01'";
            break;
            case 5:
                sentenciaSQL = "select *from catalogo_cuentas where codigo='221'";
            break;
            case 6:
                sentenciaSQL = "select *from catalogo_cuentas where codigo='412-45'";
            break;
            case 7:
                sentenciaSQL = "select *from catalogo_cuentas where codigo='412-47'";
            break;
            case 8: 
                sentenciaSQL = "select *from catalogo_cuentas where codigo='412-46'";
            break;
            case 9:
                sentenciaSQL = "select *from catalogo_cuentas where codigo='412-62'";
            break;
            case 10:
                sentenciaSQL = "select *from catalogo_cuentas where codigo='112-01'";
            break;
            case 11:
                sentenciaSQL = "select *from catalogo_cuentas where codigo='219-01-05'";
            break;
        }
        
        try {
            
            Statement statement = con.createStatement();
            ResultSet resultado = statement.executeQuery(sentenciaSQL);
            
            while (resultado.next()) {
                    Cuenta cuenta = new Cuenta();
                    cuenta.setCodigo( resultado.getString("codigo") );
                    cuenta.setNombre( resultado.getString("nombre") );
                    cuentas.add(cuenta);
                    
                    tbl_Cuenta.addRow(new Object[]{cuenta.getCodigo(),cuenta.getNombre()});
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No exiten Cuentas asociadas a esta Transaccion");
          }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_continuarPagoCobro;
    private javax.swing.JButton btn_seleccionPagoCobro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_cuentaPagoCobro;
    private javax.swing.JTable tbl_tipoPagoCobro;
    // End of variables declaration//GEN-END:variables
}
