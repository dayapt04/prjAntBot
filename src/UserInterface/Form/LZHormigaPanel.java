package UserInterface.Form;

import javax.swing.*;
import BusinessLogic.LZHormigaBL;
import DataAccess.DTO.LZHormigaDTO;
import UserInterface.IAStyle;
import UserInterface.CustomerControl.PatButton;
import UserInterface.CustomerControl.PatLabel;
import UserInterface.CustomerControl.PatTextBox;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class LZHormigaPanel extends JPanel implements ActionListener {
    private Integer idHormiga = 0, idMaxHormiga = 0;
    private LZHormigaBL lzhormigaBL = null;
    private LZHormigaDTO lzhormiga = null;

    public LZHormigaPanel() {
        try {
            customizeComponent();
            loadRow();
            showRow();
            showTable();

            btnRowIni.addActionListener(this);
            btnRowAnt.addActionListener(this);
            btnRowSig.addActionListener(this);
            btnRowFin.addActionListener(this);

            btnNuevo.addActionListener(e -> btnNuevoClick());
            btnGuardar.addActionListener(e -> btnGuardarClick());
            btnEliminar.addActionListener(e -> btnEliminarClick());
            btnCancelar.addActionListener(e -> btnCancelarClick());
        } catch (Exception e) {
            IAStyle.showMsg(e.getMessage());
        }
    }

    private void loadRow() throws Exception {
        idHormiga = 1;
        lzhormigaBL = new LZHormigaBL();
        lzhormiga = lzhormigaBL.getByIdHormiga(idHormiga);
        idMaxHormiga = lzhormigaBL.getMaxRow();
    }

    private void showRow() {
        boolean lzhormigaNull = (lzhormiga == null);
        txtIdHormiga.setText((lzhormigaNull) ? " " : lzhormiga.getIdHormiga().toString());
        txtNombre.setText((lzhormigaNull) ? " " : lzhormiga.getNombre());
        txtIdHormigaClasificacion.setText((lzhormigaNull) ? " " : lzhormiga.getIdHormigaClasificacion().toString());
        lblTotalReg.setText(idHormiga.toString() + " de " + idMaxHormiga.toString());
    }

    private void btnNuevoClick() {
        lzhormiga = null;
        showRow();
    }

    private void btnGuardarClick() {
        boolean lzhormigaNull = (lzhormiga == null);
        try {
            if (IAStyle.showConfirmYesNo("¿Seguro que desea " + ((lzhormigaNull) ? "AGREGAR ?" : "ACTUALIZAR ?"))) {
                if (lzhormigaNull)
                    lzhormiga = new LZHormigaDTO(idHormiga, txtNombre.getText());
                else {
                    lzhormiga.setNombre(txtNombre.getText());
                }

                if (!((lzhormigaNull) ? lzhormigaBL.create(lzhormiga) : lzhormigaBL.update(lzhormiga)))
                    IAStyle.showMsgError("Error al guardar...!");

                loadRow();
                showRow();
                showTable();
            }
        } catch (Exception e) {
            IAStyle.showMsgError(e.getMessage());
        }
    }

    private void btnEliminarClick() {
        try {
            if (IAStyle.showConfirmYesNo("Seguro que desea Eliminar?")) {
                if (!lzhormigaBL.delete(lzhormiga.getIdHormiga()))
                    throw new Exception("Error al eliminar...!");

                loadRow();
                showRow();
                showTable();
            }
        } catch (Exception e) {
            IAStyle.showMsgError(e.getMessage());
        }
    }

    private void btnCancelarClick() {
        try {
            if (lzhormiga == null)
                loadRow();
            showRow();
        } catch (Exception e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRowIni)
            idHormiga = 1;
        if (e.getSource() == btnRowAnt && (idHormiga > 1))
            idHormiga--;
        if (e.getSource() == btnRowSig && (idHormiga < idMaxHormiga))
            idHormiga++;
        if (e.getSource() == btnRowFin)
            idHormiga = idMaxHormiga;
        try {
            lzhormiga = lzhormigaBL.getByIdHormiga(idHormiga);
            showRow();
        } catch (Exception ex) {
        }
    }

    private void showTable() throws Exception {
        String[] header = {"Id", "Nombre", /* Agrega los demás campos */};
        List<LZHormigaDTO> lzhormigas = lzhormigaBL.getAll();

        Object[][] data = new Object[lzhormigas.size()][header.length];
        int index = 0;
        for (LZHormigaDTO h : lzhormigas) {
            data[index][0] = h.getIdHormiga();
            data[index][1] = h.getNombre();
            // Agrega los demás campos según la estructura de LZHormigaDTO
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

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    String strIdHormiga = table.getModel().getValueAt(row, 0).toString();
                    idHormiga = Integer.parseInt(strIdHormiga);
                    try {
                        lzhormiga = lzhormigaBL.getByIdHormiga(idHormiga);
                        showRow();
                    } catch (Exception ignored) {
                    }
                    System.out.println("Tabla.Selected: " + strIdHormiga);
                }
            }
        });
    }

    /************************
     * FormDesing : pat_mic
     ************************/
    private PatLabel
            lblTitulo = new PatLabel("LZHormiga"),
            lblIdHormiga = new PatLabel(" Codigo:      "),
            lblNombre = new PatLabel("*Nombre: "),
            lblTotalReg = new PatLabel(" 0 de 0 ");
    private PatTextBox
            txtIdHormiga = new PatTextBox(),
            txtIdHormigaClasificacion =  new PatTextBox(),
            txtNombre = new PatTextBox();
    private PatButton
            btnPageIni = new PatButton(" |< "),
            btnPageAnt = new PatButton(" << "),
            btnPageSig = new PatButton(" >> "),
            btnPageFin = new PatButton(" >| "),

            btnRowIni = new PatButton(" |< "),
            btnRowAnt = new PatButton(" << "),
            btnRowSig = new PatButton(" >> "),
            btnRowFin = new PatButton(" >| "),

            btnNuevo = new PatButton("Nuevo"),
            btnGuardar = new PatButton("Guardar"),
            btnCancelar = new PatButton("Cancelar"),
            btnEliminar = new PatButton("Eliminar");
    private JPanel
            pnlTabla = new JPanel(),
            pnlBtnRow = new JPanel(new FlowLayout()),
            pnlBtnPage = new JPanel(new FlowLayout()),
            pnlBtnCRUD = new JPanel(new FlowLayout());

    public void customizeComponent() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        txtIdHormiga.setEnabled(false);
        txtIdHormiga.setBorderLine();
        txtNombre.setBorderLine();

        pnlBtnPage.add(btnPageIni);
        pnlBtnPage.add(btnPageAnt);
        pnlBtnPage.add(new PatLabel(" Page:( "));
        pnlBtnPage.add(lblTotalReg);
        pnlBtnPage.add(new PatLabel(" ) "));
        pnlBtnPage.add(btnPageSig);
        pnlBtnPage.add(btnPageFin);

        pnlBtnRow.add(btnRowIni);
        pnlBtnRow.add(btnRowAnt);
        pnlBtnRow.add(lblTotalReg);
        pnlBtnRow.add(btnRowSig);
        pnlBtnRow.add(btnRowFin);

        pnlBtnCRUD.add(btnNuevo);
        pnlBtnCRUD.add(btnGuardar);
        pnlBtnCRUD.add(btnCancelar);
        pnlBtnCRUD.add(btnEliminar);
        pnlBtnCRUD.setBorder(IAStyle.createBorderRect());

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(new JLabel("■ Sección de datos: "), gbc);
        gbc.gridy = 1;
        gbc.gridx = 1;
        add(pnlBtnPage, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.ipady = 150;
        gbc.ipadx = 450;
        pnlTabla.add(new Label("Loading data..."));
        add(pnlTabla, gbc);

        gbc.ipady = 1;
        gbc.ipadx = 1;

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(50, 0, 0, 0);  // Ajusta el valor superior a 50
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(Box.createRigidArea(new Dimension(0, 0)), gbc);

        gbc.insets = new Insets(10, 0, 0, 0);

        gbc.gridy = 4;
        gbc.gridx = 0;
        add(new JLabel("■ Sección de registro: "), gbc);
        gbc.gridy = 4;
        gbc.gridx = 1;
        add(pnlBtnRow, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        add(lblIdHormiga, gbc);
        gbc.gridy = 5;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Indica que este componente ocupa toda la fila
        add(txtIdHormiga, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        add(lblNombre, gbc);
        gbc.gridy = 6;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Indica que este componente ocupa toda la fila
        add(txtNombre, gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(pnlBtnCRUD, gbc);
    }
}
