package com.microfinance.hsmicrofinance.Network.models;

public class HSContacts {
    String name;
    String phoneno;
    String accountno;



    public HSContacts(String name, String phoneno, String accountno) {
        this.name = name;
        this.phoneno = phoneno;
        this.accountno = accountno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }
}
