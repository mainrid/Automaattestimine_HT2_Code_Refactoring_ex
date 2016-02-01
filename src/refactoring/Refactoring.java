package refactoring;

import java.util.*;

public class Refactoring {

	// 1a
	public int nextInvoiceNumber() {
		return ++invoiceNumber;
	}

	// 1b
	public void addFilledOrdersTo(List<Order> orderList) {
		for (Order order : orders) {
			if (order.isFilled()) {
				orderList.add(order);
			}
		}
	}

	// 2a
	public void printStatementFor(Long invoiceId) {
		List<InvoiceRow> invoiceRows = dao.getInvoiceRowsFor(invoiceId);

		printInvoiceRows(invoiceRows);
		printInvoiceTotal(invoiceRows);
	}

	public void printInvoiceTotal(List<InvoiceRow> invoiceRows) {
		double total = 0;
		for (InvoiceRow invoiceRow : invoiceRows) {
			total += invoiceRow.getAmount();
		}

		printValue(total);
	}

	// 2b
	public String getItemsAsHtml() {
		String retval = "";
		retval += "<ul>";

		for (String item : Arrays.asList(item1, item2, item3, item4)) {
			retval += encloseInTag(item);
		}
		retval += "</ul>";
		return retval;
	}

	public String encloseInTag(String item) {
		return "<li>" + item + "</li>";
	}

	// 3
	public boolean isSmallOrder() {
		return (order.getTotal() > 100);
	}

	// 4
	public void printPrice() {
		System.out.println("Hind ilma käibemaksuta: " + getBasePrice());
		System.out.println("Hind käibemaksuga: " + getBasePrice()
				* VALUE_ADDED_TAX);
	}

	// 5
	public void calculatePayFor(Job job) {
		// on holiday at night
		boolean onHolidayAtNight = (job.day == 6 || job.day == 7)
				&& (job.hour > 20 || job.hour < 7);
		if (onHolidayAtNight) {

		}
	}

	// 6
	public boolean canAccessResource(SessionData sessionData) {
		// is admin and has preferred status
		return ifAdminWithPermission(sessionData);
	}

	public boolean ifAdminWithPermission(SessionData sessionData) {
		return (sessionData.getCurrentUserName().equals("Admin") || sessionData
				.getCurrentUserName().equals("Administrator"))
				&& (sessionData.getStatus().equals("preferredStatusX") || sessionData
						.getStatus().equals("preferredStatusY"));
	}

	// 7
	public void drawLines() {
		Space space = new Space();
		space.drawLine(new LineCoordinatesSetter(12, 3, 5, 2, 4, 6));
		space.drawLine(new LineCoordinatesSetter(2, 4, 6, 0, 1, 0));
	}

	// 8

	public int calculateWeeklyPayWithOvertime(int hoursWorked) {
		int straightTime = Math.min(40, hoursWorked);
		int straightPay = straightTime * hourRate;
		
		int overTime = Math.max(0, hoursWorked - straightTime);
		double overtimeRate = 1.5 * hourRate;
		int overtimePay = (int) Math.round(overTime * overtimeRate);
		
		return straightPay + overtimePay;
	}
	
	public Integer calculateWeeklyPayWithoutOvertime(int hoursWorked) {		
		return hoursWorked * hourRate;
	}

	// //////////////////////////////////////////////////////////////////////////

	// Abiväljad ja abimeetodid.
	// Need on siin lihtsalt selleks, et kood kompileeruks

	private static final double VALUE_ADDED_TAX = 1.2;
	private String item1 = "1";
	private String item2 = "2";
	private String item3 = "3";
	private String item4 = "4";
	private int hourRate = 5;
	int invoiceNumber = 0;
	private List<Order> orders = new ArrayList<Order>();
	private Order order = new Order();
	private Dao dao = new SampleDao();
	private double price = 0;

	void justACaller() {
		nextInvoiceNumber();
		addFilledOrdersTo(null);
	}

	private void printValue(double total) {
	}

	private void printInvoiceRows(List<InvoiceRow> invoiceRows) {
	}

	class Space {
		public void drawLine(LineCoordinatesSetter coordinates) {
		}

	}

	interface Dao {
		List<InvoiceRow> getInvoiceRowsFor(Long invoiceId);
	}

	class SampleDao implements Dao {
		@Override
		public List<InvoiceRow> getInvoiceRowsFor(Long invoiceId) {
			return null;
		}
	}

	class Order {
		public boolean isFilled() {
			return false;
		}

		public double getTotal() {
			return 0;
		}
	}

	class InvoiceRow {
		public double getAmount() {
			return 0;
		}
	}

	class Job {
		public int hour;
		public int day;
	}

	private double getBasePrice() {
		return price;
	}

	private class SessionData {

		public String getCurrentUserName() {
			return null;
		}

		public String getStatus() {
			return null;
		}

	}



}
