/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.dao;

import dbconnectionlib.DBConnectionLib;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import prueba.vo.AccountVo;
import prueba.vo.Account_listVo;
import prueba.vo.CustomerVo;

/**
 *
 * @author SERGIO
 */
public class AccountDao {
    
    String custnameOwner = "";
    int listaId;
    
        public void asignAcc(int idCustd, int accNum) {
            ArrayList<CustomerVo> miCustomer = new ArrayList<CustomerVo>();
            ArrayList<Account_listVo> miAccount_List = new ArrayList<Account_listVo>();
            DBConnectionLib conex = new DBConnectionLib();

        try {
            PreparedStatement consulta = conex.getConnection().prepareStatement(""
                     //+ "SELECT * FROM account where cust_ID= ?");            
                     +"SELECT customer.name, list_accounts.list_accounts_ID "
                     + "FROM customer "
                     + "INNER JOIN list_accounts ON customer.id = list_accounts.cust_ID ");
                     //+ "WHERE customer.id= ?");
            //consulta.setInt(1, idCustd);//
            ResultSet res = consulta.executeQuery();

            if (res.next()) {
                CustomerVo customer = new CustomerVo();
                customer.setId(Integer.parseInt(res.getString("id")));
                customer.setName(res.getString("name"));
                customer.setAddress(res.getString("address"));
                customer.setDob(Date.valueOf(res.getString("dob")));

                miCustomer.add(customer);
                
                Account_listVo Acc_list= new Account_listVo();
                Acc_list.setList_accounts_ID(Integer.parseInt(res.getString("list_accounts_ID")));
                Acc_list.setCust_ID(Integer.parseInt(res.getString("cust_ID")));
                         
                miAccount_List.add(Acc_list);
                
                custnameOwner = customer.getName();
                listaId = Acc_list.getList_accounts_ID();
                
                PreparedStatement consulta2 = conex.getConnection().prepareStatement(""
                    + "UPDATE account SET owner = '" + custnameOwner
                                                     + ", cust_ID= '"+ customer.getId() 
                                                     + ", lista_ID= '"+ listaId
                    + "' WHERE acc_No= ?");
                consulta.setInt(1, accNum);
            }
            
            consulta.execute();

            consulta.close();
            conex.desconectar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se pudo modificar el stock\n " + e);
        }
    }
        public ArrayList<AccountVo> consultarAccount(int idAcc) {
        ArrayList<AccountVo> miAccount = new ArrayList<AccountVo>();
        DBConnectionLib conex = new DBConnectionLib();

        try {
            PreparedStatement consulta = conex.getConnection().prepareStatement(""
                    + "SELECT * FROM account where acc_No= ?");
            consulta.setInt(1, idAcc);//
            ResultSet res = consulta.executeQuery();

            if (res.next()) {
                AccountVo account = new AccountVo();
                account.setAcc_No(Integer.parseInt(res.getString("acc_No")));
                account.setType(res.getString("type"));
                account.setOwner(res.getString("owner"));
                account.setCust_ID(Integer.parseInt(res.getString("cust_ID")));
                account.setLista_ID(Integer.parseInt(res.getString("lista_ID")));
                account.setAmount(Integer.parseInt(res.getString("amount")));

                miAccount.add(account);
            }

            res.close();
            consulta.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se pudo consultar la stock blabla\n " + e);
        }
        return miAccount;
    }
}
