package in.fssa.fertagriboomi.create;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Stocks;
import in.fssa.fertagriboomi.service.StockService;

public class TestCreateStock {
	@Test
	public void testCreateProductStockWithValideInput() {
		StockService stockService = new StockService();
		
		Stocks stock = new Stocks();
		stock.setProductId(3);
		stock.setStockCount(520);
		
		assertDoesNotThrow(() -> {
			stockService.createStock(stock);

		});
	}
	
	@Test
	public void testCreateProductStockWithInValideInput() {
		StockService stockService = new StockService();

		assertDoesNotThrow(() -> {
			stockService.createStock(null);

		});
	}
	@Test
	public void testCreateProductStockWithInValideProductId() {
		StockService stockService = new StockService();
		
		Stocks stock = new Stocks();
		stock.setProductId(-22);
		stock.setStockCount(520);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			stockService.createStock(stock);
		});

		String exceptedMessage = "Invalid Stock input";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateProductStockWithNonExistenProductId() {
		StockService stockService = new StockService();
		
		Stocks stock = new Stocks();
		stock.setProductId(322);
		stock.setStockCount(520);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			stockService.createStock(stock);
		});

		String exceptedMessage = "The product is not listed among the available products";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
	@Test
	public void testCreateProductStockWithZeroOrNegativeStockInput() {
		StockService stockService = new StockService();
		
		Stocks stock = new Stocks();
		stock.setProductId(322);
		stock.setStockCount(-2);
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			stockService.createStock(stock);
		});

		String exceptedMessage = "Invalid Stock Count";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}
}
