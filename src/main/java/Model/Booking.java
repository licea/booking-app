package Model;

import com.google.gson.Gson;

import java.util.List;

public class Booking {
    private String customerName;
    private int tableSize;
    private String dateTime;

    public String getCustomerName() {
        return customerName;
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    // Method to parse JSON string to Booking object
    public static Booking fromJson(String json) {
        // Example using Gson library:
        return new Gson().fromJson(json, Booking.class);
    }

    @Override
    public String toString() {
        return "Booking {" +
                "customerName='" + customerName + '\'' +
                ", tableSize=" + tableSize +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
