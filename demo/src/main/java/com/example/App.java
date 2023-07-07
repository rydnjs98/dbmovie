package com.example;

import java.sql.*;

public class App 
{
    public static void main( String[] args )
    {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement insertPstm = null;
        Statement selectPstm = null;

        String url = "jdbc:mysql://localhost:3306/movie";
        String username = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // getConnection(url, username, password)
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("DB 연결 성공");
        } catch (SQLException e) {
            System.out.println("DB 연결 실패");
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            System.err.println(e);
        }

        try {
            String sql = "select * from moive";
            selectPstm = conn.createStatement();

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                System.out.println(resultSet.getString(1)+", "+resultSet.getString(2)+", "+resultSet.getString(3));
            }

            System.out.println("쿼리 성공");
        } catch (SQLException e) {
            System.out.println("쿼리 실패");
            System.err.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (insertPstm != null) {
                    insertPstm.close();
                }
                if (selectPstm != null) {
                    selectPstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
