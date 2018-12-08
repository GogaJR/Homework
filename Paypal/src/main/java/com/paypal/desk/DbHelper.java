package com.paypal.desk;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class DbHelper {
    private static Connection connection = getConnection();

    private static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/paypal", "root", "");
            return connection;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    static int createUser(String firstName, String lastName) {
        String query = "insert into users (first_name, last_name) values (?, ?)";
        PreparedStatement preInsertStatement = null;
        Statement maxIdStatement = null;

        try {
            preInsertStatement = connection.prepareStatement(query);
            preInsertStatement.setString(1, firstName);
            preInsertStatement.setString(2, lastName);
            preInsertStatement.executeUpdate();

            query = "select max(id) from users";
            maxIdStatement = connection.createStatement();
            ResultSet resultSet = maxIdStatement.executeQuery(query);

            resultSet.next();
            return resultSet.getInt(1);
        }catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }finally {
            try {
                if(preInsertStatement != null) {
                    preInsertStatement.close();
                }

                if(maxIdStatement != null) {
                    maxIdStatement.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static List<User> listUsers() {
        List<User> users = new ArrayList<>();
        String query = "select * from users";
        Statement selectStatement = null;

        try {
            selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                double balance = resultSet.getDouble("balance");

                users.add(new User(id, firstName, lastName, balance));
            }

            return users;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                if(selectStatement != null) {
                    selectStatement.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static int cashFlow(int id, double amount) {
        String query = "select balance from users where id = ?";
        PreparedStatement preSelectStatement = null;
        PreparedStatement preUpdateStatement = null;

        try {
            preSelectStatement = connection.prepareStatement(query);
            preSelectStatement.setInt(1, id);

            ResultSet resultSet = preSelectStatement.executeQuery();
            double oldBalance = 0;

            if(resultSet.next()) {
                oldBalance = resultSet.getDouble(1);
            } else {
                System.out.println("There is no user which such id.");
                return -1;
            }

            query = "update users set balance = ? where id = ?";
            preUpdateStatement = connection.prepareStatement(query);
            preUpdateStatement.setDouble(1, oldBalance + amount);
            preUpdateStatement.setInt(2, id);
            preUpdateStatement.executeUpdate();

            return 1;
        }catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }finally {
            try {
                if(preSelectStatement != null) {
                    preSelectStatement.close();
                }

                if(preUpdateStatement != null) {
                    preUpdateStatement.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static int transaction(int id, int targetId, double amount) {
        if(cashFlow(id, -amount) == -1) {
            return -1;
        }

        if(cashFlow(targetId, amount) == -1) {
            return -1;
        }

        PreparedStatement preInsertStatement = null;

        try {
            String query = "insert into transactions (user_from, user_to, transaction_amount) values (?, ?, ?)";
            preInsertStatement = connection.prepareStatement(query);
            preInsertStatement.setInt(1, id);
            preInsertStatement.setInt(2, targetId);
            preInsertStatement.setDouble(3, amount);
            preInsertStatement.executeUpdate();

            return 1;
        }catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }finally {
            try {
                if(preInsertStatement != null) {
                    preInsertStatement.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
