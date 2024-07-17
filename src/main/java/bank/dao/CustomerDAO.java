package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import bank.model.Customer;
import bank.model.Transaction;
import servlets.JDBCUtil;

@SuppressWarnings("unused")
public class CustomerDAO {

	public boolean insert(Customer customer) {
	    String sql = "INSERT INTO customer (full_name, address, mobile_no, email_id, account_type, initial_balance, dob, id_proof, account_no, password, status) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, customer.getFullName());
	        stmt.setString(2, customer.getAddress());
	        stmt.setString(3, customer.getMobileNumber());
	        stmt.setString(4, customer.getEmail());
	        stmt.setString(5, customer.getTypeOfAccount());
	        stmt.setDouble(6, customer.getInitialBalance());
	        stmt.setDate(7, java.sql.Date.valueOf(customer.getDateOfBirth()));
	        stmt.setString(8, customer.getIdProof());
	        stmt.setString(9, customer.getAccountNumber());
	        stmt.setString(10, customer.getPassword());
	        stmt.setString(11, customer.getStatus());
	        int rows = stmt.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


	public Customer getCustomer(String accountNumber) {
	    String sql = "SELECT * FROM customer WHERE account_no = ?";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, accountNumber);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                Customer customer = new Customer();
	                customer.setCustomerId(rs.getInt("customer_id"));
	                customer.setFullName(rs.getString("full_name"));
	                customer.setAddress(rs.getString("address"));
	                customer.setMobileNumber(rs.getString("mobile_no"));
	                customer.setEmail(rs.getString("email_id"));
	                customer.setTypeOfAccount(rs.getString("account_type"));
	                customer.setInitialBalance(rs.getDouble("initial_balance"));
	                customer.setDateOfBirth(rs.getDate("dob").toString());
	                customer.setIdProof(rs.getString("id_proof"));
	                customer.setAccountNumber(rs.getString("account_no"));
	                customer.setPassword(rs.getString("password"));
	                customer.setStatus(rs.getString("status"));
	                return customer;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}


	public boolean deposit(String accountNumber, double amount) {
	    String sql = "UPDATE customer SET initial_balance = initial_balance + ? WHERE account_no = ?";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setDouble(1, amount);
	        stmt.setString(2, accountNumber);
	        int rows = stmt.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	public boolean withdraw(String accountNumber, double amount) {
	    String sql = "UPDATE customer SET initial_balance = initial_balance - ? WHERE account_no = ? ";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setDouble(1, amount);
	        stmt.setString(2, accountNumber);
	      // subtract a small amount to allow for full withdrawal
	        int rows = stmt.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	 public boolean closeAccount(String accountNumber) {
		    String sqlCheckBalance = "SELECT initial_balance FROM customer WHERE account_no = ?";
		    String sqlUpdateStatus = "UPDATE customer SET status = 'Closed', initial_balance = 0 WHERE account_no = ?";
		    
		    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");
		         PreparedStatement stmtCheckBalance = conn.prepareStatement(sqlCheckBalance);
		         PreparedStatement stmtUpdateStatus = conn.prepareStatement(sqlUpdateStatus)) {

		        // Check the current balance
		        stmtCheckBalance.setString(1, accountNumber);
		        ResultSet rs = stmtCheckBalance.executeQuery();
		        if (rs.next()) {
		            double balance = rs.getDouble("initial_balance");
		            if (balance == 0) {
		                // Update the account status to 'Closed' and set balance to 0
		                stmtUpdateStatus.setString(1, accountNumber);
		                int rows = stmtUpdateStatus.executeUpdate();
		                return rows > 0;
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return false;
		}
	 public List<Transaction> getTransactionHistory1(String accountNumber) {
	        List<Transaction> transactions = new ArrayList<>();
	        String sql = "SELECT * FROM transactions WHERE account_no = ?";

	        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, accountNumber);

	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    Transaction transaction = new Transaction();
	                    transaction.setTransactionType(rs.getString("transaction_type"));
	                    transaction.setAmount(rs.getDouble("amount"));
	                    transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
	                    transaction.setBalanceAfterTransaction(rs.getDouble("balance_after_transaction"));
	                    transactions.add(transaction);
	                }
	                System.out.println("Transactions size: " + transactions.size());
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Error fetching transactions: " + e.getMessage());
	        }
	        return transactions;
	    }

	 public boolean logTransaction(String accountNumber, String transactionType, double amount, double balanceAfterTransaction) {
		    String sql = "INSERT INTO transactions (account_no, transaction_type, amount, balance_after_transaction) VALUES (?, ?, ?, ?)";
		    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setString(1, accountNumber);
		        stmt.setString(2, transactionType);
		        stmt.setDouble(3, amount);
		        stmt.setDouble(4, balanceAfterTransaction);
		        int rows = stmt.executeUpdate();
		        return rows > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return false;
		}




	public boolean updateCustomer(Customer customer) {
	    String sql = "UPDATE customer SET full_name = ?, address = ?, mobile_no = ?, email_id = ?, account_type = ?, initial_balance = ?, dob = ?, id_proof = ?, password = ?, status = ? WHERE account_no = ?";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, customer.getFullName());
	        stmt.setString(2, customer.getAddress());
	        stmt.setString(3, customer.getMobileNumber());
	        stmt.setString(4, customer.getEmail());
	        stmt.setString(5, customer.getTypeOfAccount());
	        stmt.setDouble(6, customer.getInitialBalance());
	        stmt.setDate(7, java.sql.Date.valueOf(customer.getDateOfBirth()));
	        stmt.setString(8, customer.getIdProof());
	        stmt.setString(9, customer.getPassword());
	        stmt.setString(10, customer.getStatus());
	        stmt.setString(11, customer.getAccountNumber());
	        int rows = stmt.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


	public boolean deleteCustomer(String accountNumber) {
	    String sql = "DELETE FROM customer WHERE account_no = ?";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, accountNumber);
	        int rows = stmt.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


	public Customer getAccountDetails(String accountNumber) {
	    String sql = "SELECT * FROM customer WHERE account_no = ?";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, accountNumber);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                Customer customer = new Customer();
	                customer.setCustomerId(rs.getInt("customer_id"));
	                customer.setFullName(rs.getString("full_name"));
	                customer.setAddress(rs.getString("address"));
	                customer.setMobileNumber(rs.getString("mobile_no"));
	                customer.setEmail(rs.getString("email_id"));
	                customer.setTypeOfAccount(rs.getString("account_type"));
	                customer.setInitialBalance(rs.getDouble("initial_balance"));
	                customer.setDateOfBirth(rs.getDate("dob").toString());
	                customer.setIdProof(rs.getString("id_proof"));
	                customer.setAccountNumber(rs.getString("account_no"));
	                customer.setPassword(rs.getString("password"));
	                customer.setStatus(rs.getString("status"));
	                return customer;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}


	public List<Transaction> getTransactionHistory(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
