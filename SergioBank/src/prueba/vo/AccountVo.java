/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.vo;

/**
 *
 * @author SERGIO
 */
public class AccountVo {
    private Integer acc_No;
    private String type;
    private String owner;
    private Integer cust_ID;
    private Integer lista_ID;
    private Integer amount;

    public Integer getAcc_No() {
        return acc_No;
    }

    public void setAcc_No(Integer acc_No) {
        this.acc_No = acc_No;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getCust_ID() {
        return cust_ID;
    }

    public void setCust_ID(Integer cust_ID) {
        this.cust_ID = cust_ID;
    }

    public Integer getLista_ID() {
        return lista_ID;
    }

    public void setLista_ID(Integer lista_ID) {
        this.lista_ID = lista_ID;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    
}
