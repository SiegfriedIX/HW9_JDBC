package com.geekhub.homeworks;

import java.sql.*;

class IfTablesExists {
    static void checkTablesExists(){
        Connection con;
        try { Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

            DatabaseMetaData meta = con.getMetaData();
            ResultSet res = meta.getTables(null, null,"EMPLOYEE",null);
            if (res.next()) {
                System.out.println("Tables EMPLOYEE and SALARY in DataBase test existing");
                Statement st2=con.createStatement();
                st2.execute("DROP TABLE EMPLOYEE");
                st2.execute("DROP TABLE SALARY");
                System.out.println("Tables deleted successful");
                ConnectH2.connect();

            } else {
                System.out.println("Tables EMPLOYEE and SALARY in DataBase test not existing");
                ConnectH2.connect();
            }
            res.close();
            con.close();
        } catch (Exception e) {
            System.err.println("Exception: "+e.getMessage());
        }

    }
}


