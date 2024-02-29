package UserInterface.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import BusinessLogic.LZHormigaBL;
import DataAccess.DTO.LZHormigaDTO;
import UserInterface.CustomerControl.PatButton;
import UserInterface.CustomerControl.PatLabel;
import UserInterface.CustomerControl.PatTextBox;

public class LZPnlHormiga extends JPanel implements ActionListener {
    private Integer idHormiga, idMaxHormiga;
    private LZHormigaBL lzhormigaBL = null;
    private LZHormigaDTO lzhormiga = null;

    public LZPnlHormiga() throws Exception {
        setGridBagLayout();
        loadData();
        showData();
        showTable();

        btnIni.addActionListener(this);
        btnAnt.addActionListener(this);
        btnSig.addActionListener(this);
        btnFin.addActionListener(this);
        btnGuardar.addActionListener(this);

        btnNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnNuevoClick(e);
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnEliminarClick(e);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void loadData() throws Exception {
        idHormiga = 1;
        lzhormigaBL = new LZHormigaBL();
        lzhormiga = lzhormigaBL.getByIdHormiga(idHormiga);
        //idMaxHormiga = lzhormigaBL.getMaxIdHormiga();
    }

    private void showData() {
        boolean lzhormigaNull = (lzhormiga == null);
        txtIdHormiga.setText((lzhormigaNull) ? " " : lzhormiga.getIdHormiga().toString());
        txtNombre.setText((lzhormigaNull) ? " " : lzhormiga.getNombre());
        txtIdHormigaClasificacion.setText((lzhormigaNull) ? " " : lzhormiga.getIdHormigaClasificacion().toString());
        //lblTotalReg.setText(idHormiga.toString() + " de " + idMaxHormiga.toString());
    }

    private void btnNuevoClick(ActionEvent e) {
        lzhormiga = null;
        showData();
    } 

    private void btnEliminarClick(ActionEvent e) throws Exception {
        if (JOptionPane.showConfirmDialog(this, "¿Está seguro que desea Eliminar?", "Eliminar...",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            if (!lzhormigaBL.delete(lzhormiga.getIdHormiga()))
                JOptionPane.showMessageDialog(this, "Error al eliminar...!", "ERROR", JOptionPane.OK_OPTION);

            loadData();
            showData();
            showTable();
        }
    }

    private void btnGuardarClick(ActionEvent e) throws  Exception {
        boolean lzhormigaNull = (lzhormiga == null);
        if (JOptionPane.showConfirmDialog(this, "¿Seguro que desea guardar?", (lzhormigaNull)?"Agregar...": "Actualizar...", 
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            // if (lzhormigaNull)
            //     lzhormiga = new LZHormigaBL(txtNombre.getText().toString(), txtIdHormigaClasificacion.getText().toString());
            // else {
            //     lzhormiga.setNombre(txtNombre.getText().toString());
            //     lzhormiga.setIdHormigaClasificacion(Integer.parseInt(txtIdHormigaClasificacion.getText().toString()));
            //     // Puedes agregar más campos aquí
            // }

            // if (!((lzhormigaNull) ? lzhormigaBL.create(lzhormiga) : lzhormigaBL.update(lzhormiga)))
            //     JOptionPane.showMessageDialog(this, "Error al guardar...!", "ERROR", JOptionPane.OK_OPTION);

            loadData();
            showData();
            showTable();
        }
    } 

    private void showTable() throws Exception {
        String[] header = {"Id", "Nombre", "IdClasificacion", "Estado"};
        Object[][] data = new Object[lzhormigaBL.getAll().size()][4];  
        int index = 0;
        for(LZHormigaDTO p : lzhormigaBL.getAll()) {
            data[index][0] = p.getIdHormiga();
            data[index][1] = p.getNombre();
            data[index][2] = p.getIdHormigaClasificacion();
            data[index][3] = p.getEstado();
            index++;
        }

        JTable table = new JTable(data, header);
        table.setShowHorizontalLines(true);
        table.setGridColor(Color.lightGray);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);

        table.setPreferredScrollableViewportSize(new Dimension(450, 150));
        table.setFillsViewportHeight(true);

        pnlTabla.removeAll();
        pnlTabla.add(new JScrollPane(table));

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){ 
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int col = 0;
                int row = table.getSelectedRow();
                String strIdHormiga = table.getModel().getValueAt(row, col).toString();

                idHormiga = Integer.parseInt(strIdHormiga);
                try {
                    lzhormiga = lzhormigaBL.getByIdHormiga(idHormiga);
                    showData(); 
                } catch (Exception e1) { }  
                System.out.println("Tabla.Selected: " + strIdHormiga);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnIni)
            idHormiga = 1;
        if(e.getSource() == btnAnt  &&  (idHormiga > 1))
            idHormiga--;
        if(e.getSource() == btnSig  &&  (idHormiga < idMaxHormiga))
            idHormiga++;
        if(e.getSource() == btnFin)
            idHormiga = idMaxHormiga;
    }

    private PatLabel  
            lblTitulo  = new PatLabel("Hormiga"          ),
            lblIdHormiga  = new PatLabel("Codigo:      " ),
            lblNombre  = new PatLabel("Nombre: " ),
            lblIdHormigaClasificacion  = new PatLabel("IdClasificacion: " ),
            lblTotalReg= new PatLabel("  0 de 0  "    );
    private PatTextBox  
            txtIdHormiga  = new PatTextBox (),
            txtNombre  = new PatTextBox (),
            txtIdHormigaClasificacion  = new PatTextBox ();
    private PatButton  
            btnIni     = new PatButton(" |< "), 
            btnAnt     = new PatButton(" << "),            
            btnSig     = new PatButton(" >> "),
            btnFin     = new PatButton(" >| ");
    private PatButton
            btnNuevo   = new PatButton("Nuevo"),            
            btnGuardar = new PatButton("Guardar"),
            btnCancelar= new PatButton("Cancelar"),
            btnEliminar= new PatButton("Eliminar");
    private JPanel 
            pnlTabla   = new JPanel(),
            pnlBtnCRUD = new JPanel(new FlowLayout()),
            pnlBtnPage = new JPanel(new FlowLayout());
    private Border  
            line       = new LineBorder(Color.lightGray),
            margin     = new EmptyBorder(5, 5, 5, 5),
            border     = new CompoundBorder(line, margin);

    public void setGridBagLayout(){
        GridBagConstraints gbc= new GridBagConstraints();
        txtIdHormiga.setEnabled(false);

        pnlBtnPage.add(btnIni);       
        pnlBtnPage.add(btnAnt);  
        pnlBtnPage.add(new PatLabel(" Page: [ "));      
        pnlBtnPage.add(lblTotalReg);        
        pnlBtnPage.add(new PatLabel(" ] "));      
        pnlBtnPage.add(btnSig);
        pnlBtnPage.add(btnFin);

        pnlBtnCRUD.add(btnNuevo);
        pnlBtnCRUD.add(btnGuardar);
        pnlBtnCRUD.add(btnCancelar);
        pnlBtnCRUD.add(btnEliminar);
        pnlBtnCRUD.setBorder(border);

        gbc.insets=new Insets(5,5,5,5);    

        gbc.gridy = 0;       gbc.gridx=0;
        gbc.gridwidth=3;
        add(lblTitulo, gbc);               

        gbc.gridy = 1;       gbc.gridx=0;   
        gbc.gridwidth=1;                     
        add(new JLabel("■ Sección de datos: "), gbc);                 

        gbc.gridy = 2;       gbc.gridx=0;
        gbc.gridwidth=3;                   
        gbc.ipady = 150;                   
        gbc.ipadx = 450;                   
        pnlTabla.add(new Label("Loading data..."));
        add(pnlTabla, gbc);

        gbc.ipady = 1;                    
        gbc.ipadx = 1;

        gbc.gridy = 3;       gbc.gridx=0;   
        gbc.gridwidth=3;  
        add(pnlBtnPage, gbc);  

        gbc.gridy = 4;       gbc.gridx=0; 
        gbc.gridwidth=1;    
        add(new JLabel("■ Sección de registro: "), gbc);  

        gbc.gridy = 5;       gbc.gridx=0;     add(lblIdHormiga,  gbc);   
        gbc.gridy = 5;       gbc.gridx=1;     add(txtIdHormiga,  gbc);   

        gbc.gridy = 6;       gbc.gridx=0;     add(lblNombre, gbc);        
        gbc.gridy = 6;       gbc.gridx=1;     add(txtNombre, gbc);

        gbc.gridy = 7;       gbc.gridx=0;     add(lblIdHormigaClasificacion, gbc);        
        gbc.gridy = 7;       gbc.gridx=1;     add(txtIdHormigaClasificacion, gbc);

        gbc.gridy = 8;       gbc.gridx=0;
        gbc.gridwidth=3;
        gbc.insets    = new Insets(30,0,0,0); 
        gbc.fill=GridBagConstraints.HORIZONTAL;
        add(pnlBtnCRUD, gbc);
    }
}
