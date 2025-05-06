/*
 * ExpenseLogRequest.java
 * 
 * This class represents a request to log expenses. It contains an ID and a list of expense items.
 */
package edu.fin.dtos.expense;

import java.util.List;

public class ExpenseLogRequest {
    private Long id;
    private List<ExpenseItemRequest> items;

    // constructors
    public ExpenseLogRequest() {}
    public ExpenseLogRequest(Long id, List<ExpenseItemRequest> items) {
        this.id = id;
        this.items = items;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }    

    public List<ExpenseItemRequest> getItems() { return items; }
    public void setItems(List<ExpenseItemRequest> items) { this.items = items; }

    @Override
    public String toString() {
        return "ExpenseLogRequest [id=" + id + ", items=" + items + "]";
    }
}
