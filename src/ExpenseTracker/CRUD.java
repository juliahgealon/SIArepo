import java.sql.*;

public class CRUD {
    // Create a user
    public void createUser(String username, String email, String passwordHash) {
        String sql = "INSERT INTO Users (username, email, password_hash) VALUES (?, ?, ?)";
        Connection conn = null;
        try {
            conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, passwordHash);
            stmt.executeUpdate();
            conn.commit();  // Manually commit the transaction
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();  // Rollback in case of error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();  // Close the connection manually
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Read all users
    public String readUsers() {
        StringBuilder result = new StringBuilder("Users:\n");
        String sql = "SELECT * FROM Users";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.append("ID: ").append(rs.getInt("user_id"))
                      .append(", Username: ").append(rs.getString("username"))
                      .append(", Email: ").append(rs.getString("email"))
                      .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    // Read all categories
    public String readCategories() {
        StringBuilder result = new StringBuilder("Categories:\n");
        String sql = "SELECT * FROM Categories";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.append("ID: ").append(rs.getInt("category_id"))
                      .append(", Name: ").append(rs.getString("category_name"))
                      .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    // Update user
    public void updateUser(int userId, String newEmail) {
        String sql = "UPDATE Users SET email = ? WHERE user_id = ?";
        Connection conn = null;
        try {
            conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newEmail);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
            conn.commit();  // Manually commit the transaction
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();  // Rollback in case of error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();  // Close the connection manually
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Delete user
    public void deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        Connection conn = null;
        try {
            conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.executeUpdate();
            conn.commit();  // Manually commit the transaction
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();  // Rollback in case of error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();  // Close the connection manually
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Add expense
    public void addExpense(int userId, int categoryId, double amount, String date, String description) {
        String sql = "INSERT INTO Expenses (user_id, category_id, amount, expense_date, description) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, categoryId);
            stmt.setDouble(3, amount);
            stmt.setDate(4, Date.valueOf(date));
            stmt.setString(5, description);
            stmt.executeUpdate();
            conn.commit();  // Manually commit the transaction
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();  // Rollback in case of error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();  // Close the connection manually
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Read expenses
    public String readExpenses() {
        StringBuilder result = new StringBuilder("Expenses:\n");
        String sql = "SELECT * FROM Expenses";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.append("ID: ").append(rs.getInt("expense_id"))
                      .append(", User ID: ").append(rs.getInt("user_id"))
                      .append(", Category ID: ").append(rs.getInt("category_id"))
                      .append(", Amount: ").append(rs.getDouble("amount"))
                      .append(", Date: ").append(rs.getDate("expense_date"))
                      .append(", Description: ").append(rs.getString("description"))
                      .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    // Add a new category
    public void addCategory(String categoryName) {
        String sql = "INSERT INTO Categories (category_name) VALUES (?)";
        Connection conn = null;
        try {
            conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoryName);
            stmt.executeUpdate();
            conn.commit();  // Manually commit the transaction
            System.out.println("Category added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();  // Rollback in case of error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();  // Close the connection manually
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
