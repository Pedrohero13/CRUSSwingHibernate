/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uv.gui;

import com.uv.model.Departamento;
import com.uv.model.Empleado;
import com.uv.model.FactoryDAO;
import com.uv.model.IDAOGeneral;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pedro
 */
public class DepartamentoGUI extends VentanaGUI {

    public Scanner teclado = new Scanner(System.in);

    public DepartamentoGUI() {
        this.setTitle("Departamentos");
        labelTitulo.setText("Departamentos");
        this.txtDepartamento.setVisible(false);
        this.txtDireccion.setVisible(false);
        this.txtTelefono.setVisible(false);
        this.labelDepartamento.setVisible(false);
        this.labelDireccion.setVisible(false);
        this.labelTelefono.setVisible(false);
    }

    @Override
    public void guardar() {
        IDAOGeneral<Departamento> dao = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        
        Departamento departamento = new Departamento();
        departamento.setNombre(txtNombre.getText());
        boolean res = dao.guardar(departamento);
        if (res) {
            JOptionPane.showMessageDialog(this, "Guardado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "NO Guardado ");
        }

    }

    @Override
    public void modificar() {
        IDAOGeneral<Departamento> dao = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        
        Departamento departamento = new Departamento();
        departamento.setClave(Integer.parseInt(txtClave.getText()));
        departamento.setNombre(txtNombre.getText());
        boolean res = dao.modificar(departamento);
        if (res) {
            JOptionPane.showMessageDialog(this, "Modificado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "No Modificado ");
        }
    }

    @Override
    public void consultar() {
        Vector<String> columnas = new Vector<>();
        columnas.add("clave");
        columnas.add("nombre");
        

        Vector datos = new Vector();

        IDAOGeneral<Departamento> dao = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        List<Departamento> lstDepartamentos = dao.consultar();
        for (Departamento departamento : lstDepartamentos) {
            Vector row = new Vector();
            row.add(departamento.getClave());
            row.add(departamento.getNombre());
        

            datos.add(row);
        }

        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        tabla.setModel(model);

    }

    @Override
    public void buscarID() {

        Vector<String> columnas = new Vector<>();
        columnas.add("clave");
        columnas.add("nombre");
        

        Vector datos = new Vector();

        IDAOGeneral<Departamento> dao = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        Departamento departamento =dao.buscarID(Integer.parseInt(txtClave.getText()));
            Vector row = new Vector();
            row.add(departamento.getClave());
            row.add(departamento.getNombre());
            
            datos.add(row);
        

        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        tabla.setModel(model);
    }

    @Override
    public void eliminar() {
        IDAOGeneral<Departamento> dao = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        boolean res = dao.borrar(Integer.parseInt(txtClave.getText()));
        if (res) {
            JOptionPane.showMessageDialog(this, "Borrado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "NO Borrado ");
        }
    }
}
