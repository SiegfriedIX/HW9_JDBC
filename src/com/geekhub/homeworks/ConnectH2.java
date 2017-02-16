package com.geekhub.homeworks;
import java.sql.*;

class ConnectH2 {
    static void connect(){
        try{
            Class.forName("org.h2.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test","sa","");
            Statement st = conn.createStatement();
            st.execute("CREATE TABLE EMPLOYEE(id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR (20)) ");
            //st.execute("CREATE TABLE IF NOT EXISTS EMPLOYEE(id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(20))");
            st.execute("CREATE TABLE SALARY(id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,date1 DATE,value1 DOUBLE,emp_id INT)");
            //st1.execute("CREATE TABLE IF NOT EXISTS SALARY(id INT PRIMARY KEY AUTO_INCREMENT,date DATE,value1 FLOAT,emp_id INT)");
            System.out.println("Tables created successful");

            st.execute("INSERT INTO EMPLOYEE(name)VALUES('John'),('James'),('Nik'),('Sofia') ");
            st.addBatch("INSERT INTO SALARY (date1,value1,emp_id)VALUES('2016-12-07',3200.50,120)");
            st.addBatch("INSERT INTO SALARY (date1,value1,emp_id)VALUES('2016-12-10',3300.45,121)");
            st.addBatch("INSERT INTO SALARY (date1,value1,emp_id)VALUES('2016-12-18',3620.45,122)");
            st.addBatch("INSERT INTO SALARY (date1,value1,emp_id)VALUES('2016-12-20',3210.30,123)");
            st.executeBatch();

            ResultSet result;
            result =st.executeQuery("SELECT EMPLOYEE.id,name,date1,value1,emp_id FROM EMPLOYEE,SALARY WHERE EMPLOYEE.id = SALARY.id");
            while (result.next()){
                String name = result.getString("name");
                String date1 = result.getString("date1");
                Double value1 = result.getDouble("value1");
                Integer emp_id = result.getInt("emp_id");
                System.out.println(result.getInt("id"));
                System.out.println(name+" "+date1+" "+value1+" "+emp_id);
            }
            Statement st1 = conn.createStatement();
            ResultSet result1;
            result1 =st1.executeQuery("SELECT SUM(value1) FROM SALARY");
            double value;
            while (result1.next()){
                String sum =result1.getString(1);
                value=Double.parseDouble(sum);
            System.out.println("Sum of all salaries is:"+value);
            }

            result.close();
            result1.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
