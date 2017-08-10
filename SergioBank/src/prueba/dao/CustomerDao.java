/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba.Dao;

import dbconnectionlib.DBConnectionLib;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
//import prueba.Conection.DbConnection;
import prueba.vo.CustomerVo;

/**
 *
 * @author SERGIO
 */
public class CustomerDao {

    /**
     * This method interacts with the database
     *
     * @param customer
     */
    public void registrarCust(CustomerVo customer) {
        DBConnectionLib conex = new DBConnectionLib();
        try {
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("INSERT INTO customer VALUES ('"
                    + customer.getId() + "', '"
                    + customer.getName() + "', '"
                    + customer.getAddress() + "', '"
                    + customer.getDob() + "')");
            JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente",
                    "Informacion", JOptionPane.INFORMATION_MESSAGE
            );
            estatuto.close();
            conex.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se regstro el customer, puede que ya este en la base de datos");
        }
    }

    public ArrayList<CustomerVo> listaDeCustomers() {
        ArrayList<CustomerVo> miCustomer = new ArrayList<CustomerVo>();
        DBConnectionLib conex = new DBConnectionLib();

        try {
            PreparedStatement consulta = conex.getConnection().prepareStatement(""
                    + "SELECT * FROM customer");
            ResultSet res = consulta.executeQuery();

            while (res.next()) {
                CustomerVo customer = new CustomerVo();
                customer.setId(Integer.parseInt(res.getString("id")));
                customer.setName(res.getString("name"));
                customer.setAddress(res.getString("address"));
                customer.setDob(Date.valueOf(res.getString("dob")));

                miCustomer.add(customer);
            }

            res.close();
            consulta.close();
            conex.desconectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se pudo consultar el stock\n " + e);
        }
        return miCustomer;
    }

    public ArrayList<CustomerVo> consultarCust(int idCust) {
        ArrayList<CustomerVo> miCustomer = new ArrayList<CustomerVo>();
        DBConnectionLib conex = new DBConnectionLib();

        try {
            PreparedStatement consulta = conex.getConnection().prepareStatement(""
                    + "SELECT * FROM customer WHERE id= ?");
            consulta.setInt(1, idCust);//
            ResultSet res = consulta.executeQuery();

            if (res.next()) {
                CustomerVo customer = new CustomerVo();
                customer.setId(Integer.parseInt(res.getString("id")));
                customer.setName(res.getString("name"));
                customer.setAddress(res.getString("address"));
                customer.setDob(Date.valueOf(res.getString("dob")));

                miCustomer.add(customer);
            }

            res.close();
            consulta.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se pudo consultar la stock bleble\n " + e);
        }
        return miCustomer;
    }

    public void eliminarelCust(int idCustd) {
        //ArrayList<StockVo> miCustomer = new ArrayList<StockVo>();
        DBConnectionLib conex = new DBConnectionLib();

        try {
            PreparedStatement consulta = conex.getConnection().prepareStatement(""
                    + "DELETE FROM customers where cust_ID= ?");
            consulta.setInt(1, idCustd);//
            consulta.execute();

            //res.close();
            consulta.close();
            conex.desconectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se pudo consultar la stock\n " + e);
        }

    }

    public void modificarelCustNombre(int idCustd, String nuevoNombre) {
        DBConnectionLib conex = new DBConnectionLib();

        try {
            PreparedStatement consulta = conex.getConnection().prepareStatement(""
                    + "UPDATE customer SET nombre = '" + nuevoNombre + "' WHERE cust_ID= ?");
            consulta.setInt(1, idCustd);//
            consulta.execute();

            //res.close();
            consulta.close();
            conex.desconectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se pudo modificar el stock\n " + e);
        }
    }
}
