package in.fssa.fertagriboomi.update;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.model.Stocks;
import in.fssa.fertagriboomi.service.StockService;

public class TestUpdateStock {
	@Test
	public void testUpdateProductStockWithValideInput() {
		StockService stockService = new StockService();
		
		Stocks stock = new Stocks();
		stock.setProductId(1);
		stock.setStockCount(400);
		
		assertDoesNotThrow(() -> {
			stockService.updateStock(stock);
		});
	}
	
	@Test
	public void testFindQuantiesByProductId() {
		StockService stockService = new StockService();
		
		
		assertDoesNotThrow(() -> {
			System.out.println(stockService.findQuantities(1));
		});
	}
	
	@Test
	public void testGetStockCountByProductId() {
		StockService stockService = new StockService();
		
		
		assertDoesNotThrow(() -> {
			System.out.println(stockService.findStockCountByProductId(3));
		});
	}
	
	
}
