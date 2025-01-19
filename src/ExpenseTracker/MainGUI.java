import ExpenseTracker.CurrencyConversionAPI;
import javax.swing.*;
import java.awt.*;

public class MainGUI {
    private final CRUD crudOperations = new CRUD();

    public MainGUI() {
        JFrame frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Users", createUserPanel());
        tabbedPane.addTab("Categories", createCategoryPanel());
        tabbedPane.addTab("Expenses", createExpensePanel());

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private JPanel createUserPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton addUserButton = new JButton("Add User");
        JButton viewUsersButton = new JButton("View Users");
        JButton updateUserButton = new JButton("Update User");
        JButton deleteUserButton = new JButton("Delete User");

        addUserButton.addActionListener(e -> {
            String username = JOptionPane.showInputDialog("Enter username:");
            String email = JOptionPane.showInputDialog("Enter email:");
            String password = JOptionPane.showInputDialog("Enter password:");
            if (username != null && email != null && password != null) {
                crudOperations.createUser(username, email, password);
                JOptionPane.showMessageDialog(null, "User added successfully!");
            }
        });

        viewUsersButton.addActionListener(e -> {
            String result = crudOperations.readUsers();
            JOptionPane.showMessageDialog(null, result, "Users", JOptionPane.INFORMATION_MESSAGE);
        });

        updateUserButton.addActionListener(e -> {
            String userIdStr = JOptionPane.showInputDialog("Enter user ID to update:");
            String newEmail = JOptionPane.showInputDialog("Enter new email:");
            if (userIdStr != null && newEmail != null) {
                int userId = Integer.parseInt(userIdStr);
                crudOperations.updateUser(userId, newEmail);
                JOptionPane.showMessageDialog(null, "User updated successfully!");
            }
        });

        deleteUserButton.addActionListener(e -> {
            String userIdStr = JOptionPane.showInputDialog("Enter user ID to delete:");
            if (userIdStr != null) {
                int userId = Integer.parseInt(userIdStr);
                crudOperations.deleteUser(userId);
                JOptionPane.showMessageDialog(null, "User deleted successfully!");
            }
        });

        panel.add(addUserButton);
        panel.add(viewUsersButton);
        panel.add(updateUserButton);
        panel.add(deleteUserButton);

        return panel;
    }

    private JPanel createCategoryPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));

        JButton addCategoryButton = new JButton("Add Category");
        JButton viewCategoriesButton = new JButton("View Categories");

        addCategoryButton.addActionListener(e -> {
            String categoryName = JOptionPane.showInputDialog("Enter category name:");
            if (categoryName != null) {
                crudOperations.addCategory(categoryName);
                JOptionPane.showMessageDialog(null, "Category added successfully!");
            }
        });

        viewCategoriesButton.addActionListener(e -> {
            String result = crudOperations.readCategories();
            JOptionPane.showMessageDialog(null, result, "Categories", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(addCategoryButton);
        panel.add(viewCategoriesButton);

        return panel;
    }

    private JPanel createExpensePanel() {
    JPanel panel = new JPanel(new BorderLayout());

    JButton viewExpensesButton = new JButton("View Expenses");
    viewExpensesButton.addActionListener(e -> {
        String result = crudOperations.readExpenses();
        JOptionPane.showMessageDialog(null, result, "Expenses", JOptionPane.INFORMATION_MESSAGE);
    });

    JButton convertExpenseButton = new JButton("Convert Expense");
    convertExpenseButton.addActionListener(e -> {
        String amountStr = JOptionPane.showInputDialog("Enter expense amount:");
        String fromCurrency = JOptionPane.showInputDialog("Enter source currency (e.g., USD):");
        String toCurrency = JOptionPane.showInputDialog("Enter target currency (e.g., EUR):");

        try {
            double amount = Double.parseDouble(amountStr);
            double convertedAmount = CurrencyConversionAPI.convertCurrency(amount, fromCurrency, toCurrency);

            if (convertedAmount != -1) {
                JOptionPane.showMessageDialog(null, 
                    "Converted Amount: " + convertedAmount + " " + toCurrency, 
                    "Currency Conversion", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Conversion failed. Please check the input or API connection.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid amount entered.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(viewExpensesButton);
    buttonPanel.add(convertExpenseButton);

    panel.add(buttonPanel, BorderLayout.CENTER);
    return panel;
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
