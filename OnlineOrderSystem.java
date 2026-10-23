
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OnlineOrderSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] products = {
            "Laptop", "Mouse", "Keyboard", "Headphones"
        };

        double[] prices = {
            45000.00, 500.00, 1200.00, 1500.00
        };

        int[] quantities = new int[products.length];

        System.out.println("====================================");
        System.out.println(" ONLINE ORDER PROCESSING SYSTEM");
        System.out.println("====================================");

        System.out.print("Enter Customer Name: ");
        String customer = sc.nextLine();

        System.out.print("Enter Customer Phone: ");
        String phone = sc.nextLine();

        int choice;
        do {
            System.out.println("\nAvailable Products:");
            System.out.println("------------------------------------");

            for (int i = 0; i < products.length; i++) {
                System.out.printf("%d. %-15s Rs. %.2f%n",
                        i + 1, products[i], prices[i]);
            }

            System.out.println("0. Finish Order");
            System.out.print("Select product number: ");
            choice = sc.nextInt();

            if (choice >= 1 && choice <= products.length) {
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();

                if (qty > 0) {
                    quantities[choice - 1] += qty;
                    System.out.println("Product added successfully!");
                } else {
                    System.out.println("Quantity must be greater than zero.");
                }

            } else if (choice != 0) {
                System.out.println("Invalid product choice!");
            }

        } while (choice != 0);

        double subtotal = 0;

        for (int i = 0; i < products.length; i++) {
            subtotal += prices[i] * quantities[i];
        }

        if (subtotal == 0) {
            System.out.println("No products ordered.");
            sc.close();
            return;
        }

        double gst = subtotal * 0.18;
        double total = subtotal + gst;

        String orderId = "ORD" + System.currentTimeMillis();
        String date = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

        System.out.println("\n\n==============================================");
        System.out.println("                 CUSTOMER INVOICE");
        System.out.println("==============================================");
        System.out.println("Order ID   : " + orderId);
        System.out.println("Date       : " + date);
        System.out.println("Customer   : " + customer);
        System.out.println("Phone      : " + phone);
        System.out.println("----------------------------------------------");

        System.out.printf("%-15s %8s %10s %10s%n",
                "Product", "Qty", "Price", "Amount");
        System.out.println("----------------------------------------------");

        for (int i = 0; i < products.length; i++) {
            if (quantities[i] > 0) {
                double amount = prices[i] * quantities[i];

                System.out.printf("%-15s %8d %10.2f %10.2f%n",
                        products[i], quantities[i],
                        prices[i], amount);
            }
        }

        System.out.println("----------------------------------------------");
        System.out.printf("Subtotal       : Rs. %.2f%n", subtotal);
        System.out.printf("GST (18%%)      : Rs. %.2f%n", gst);
        System.out.printf("TOTAL AMOUNT   : Rs. %.2f%n", total);
        System.out.println("----------------------------------------------");
        System.out.println("Order Status   : Confirmed");
        System.out.println("Payment Status : Pending");
        System.out.println("==============================================");
        System.out.println("     Thank you for your purchase!");
        System.out.println("==============================================");

        sc.close();
    }
}
