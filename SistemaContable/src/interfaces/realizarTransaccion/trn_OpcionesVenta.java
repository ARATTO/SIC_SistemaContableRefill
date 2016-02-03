
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
public class trn_OpcionesVenta extends javax.swing.JInternalFrame {

    
    public trn_OpcionesVenta() {
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

        btn_continuarOpcionesVenta = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_tipoOpcionesVenta = new javax.swing.JTable();
        btn_seleccionOpcionesVenta = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_cuentaOpcionesVenta = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Opciones de Venta");

        btn_continuarOpcionesVenta.setText("Continuar");
        btn_continuarOpcionesVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_continuarOpcionesVentaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbl_tipoOpcionesVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Ventas al contado"},
                {"Ventas al credito"}
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
        jScrollPane1.setViewportView(tbl_tipoOpcionesVenta);

        btn_seleccionOpcionesVenta.setText(">>");
        btn_seleccionOpcionesVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_seleccionOpcionesVentaActionPerformed(evt);
            }
        });

        tbl_cuentaOpcionesVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl_cuentaOpcionesVenta);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btn_seleccionOpcionesVenta)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 305, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(106, 106, 106)
                            .addComponent(btn_seleccionOpcionesVenta))
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
                    .addComponent(btn_continuarOpcionesVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_continuarOpcionesVenta)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_seleccionOpcionesVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionOpcionesVentaActionPerformed
        Escritorio.limpiarTabla(tbl_cuentaOpcionesVenta);
        cuentas.clear();
        int trn = tbl_tipoOpcionesVenta.getSelectedRow();
        Conexion SIC = new Conexion();
        Connection con = SIC.conexion();
        
        pintarTblCuenta(trn, con);
    }//GEN-LAST:event_btn_seleccionOpcionesVentaActionPerformed

    private void btn_continuarOpcionesVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_continuarOpcionesVentaActionPerformed
        if(tbl_cuentaOpcionesVenta.getSelectedRow() != -1  ){
            frm_OpcionesVenta frm_opcionesVenta = new frm_OpcionesVenta();
   
            
            int cnt = tbl_cuentaOpcionesVenta.getSelectedRow();
            frm_opcionesVenta.enviarCuenta(cuentas.get(cnt));
            
            Escritorio.Escritorio.add(frm_opcionesVenta);
            frm_opcionesVenta.setLocation(Escritorio.Escritorio.getWidth()/2-frm_opcionesVenta.getWidth()/2, Escritorio.Escritorio.getHeight()/2-frm_opcionesVenta.getHeight()/2);
            frm_opcionesVenta.show();
        }
        else{
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Cuenta para continuar con la transaccion");
        }
    }//GEN-LAST:event_btn_continuarOpcionesVentaActionPerformed
    private void pintarTblCuenta(int trn, Connection con){
        DefaultTableModel tbl_Cuenta = (DefaultTableModel) tbl_cuentaOpcionesVenta.getModel();
        
        String sentenciaSQL= "";
        switch(trn){
            case 0: 
                sentenciaSQL = "select *from catalogo_cuentas where codigo='511-01'";
            break;
            case 1:
                sentenciaSQL = "select *from catalogo_cuentas where codigo='511-02'";
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
    private javax.swing.JButton btn_continuarOpcionesVenta;
    private javax.swing.JButton btn_seleccionOpcionesVenta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_cuentaOpcionesVenta;
    private javax.swing.JTable tbl_tipoOpcionesVenta;
    // End of variables declaration//GEN-END:variables
}