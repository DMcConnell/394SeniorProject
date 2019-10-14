package com.eat.mysql;

import java.sql.*;

public class DBInteractor {
    
    private Connection con;
    private Statement st;

    public DBInteractor(String url) {
        try {
            con = DriverManager.getConnection(url);
            st = con.createStatement();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public DBInteractor(String url, String username, String password) {
        try {
            con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void executeStatement(String statement) {
        try {
            st.executeUpdate(statement);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            return st.executeQuery(query);
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public DatabaseMetaData getMetaData() {
        try {
            return con.getMetaData();
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void closeEverything() {
        try {
            if(st != null) {
                st.close();
            }
            if(con != null) {
                con.close();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}