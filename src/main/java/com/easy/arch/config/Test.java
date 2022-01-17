package com.easy.arch.config;//package com.easy.arch.config;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//public class Test {
//    public static void main(String[] args) {
//        try {
//            Class cls=Class.forName("com.mysql.jdbc.Driver");
//            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true","root","root");
//            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE user SET password='12345' WHERE username='admin'");
//            preparedStatement.executeUpdate();
////            while (set.next()){
////                System.out.println(set.getString(1));
////                System.out.println(set.getString(2));
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
