import in.fssa.fertagriboomi.model.Price;
import in.fssa.fertagriboomi.model.Product;
import in.fssa.fertagriboomi.model.User;
import in.fssa.fertagriboomi.service.CategoryService;
import in.fssa.fertagriboomi.service.CategoryTypeService;
import in.fssa.fertagriboomi.service.PriceService;
import in.fssa.fertagriboomi.service.ProductService;
import in.fssa.fertagriboomi.service.UserService;

public class App {

	public static void main(String[] args) {
//		UserService userservice = new UserService();
//		System.out.println("Users:");
//		userservice.getAll();
//		User userentity = new User();
//		userentity.toString();
//
//		CategoryTypeService categoryType = new CategoryTypeService();
//		System.out.println("Category types:");
//		categoryType.getAll();
//
//		CategoryService category = new CategoryService();
//		System.out.println("Categories:");
//		category.getAll();

//		Product product = new Product();
//		product.setName("Dhanuka");
//		product.setCategory_id(1);
//		product.setManufacture("Dhanuka Agritech Limited");
//		product.setProduct_weight("500g");
//		product.setDescription(
//				"Targa Super  (Quizalofop Ethyl 5% EC) is selective, systemic herbicide of Aryloxyphenoxy-propionates group. It is used to control narrow leaf weeds in broad leaf crops.");
//		product.setApplication(
//				"Weed leaves turn purplish/red within 5-8 days after Targa Super application and within 10-15 days are completely killed.");
//		product.setBenefits(
//				"It is a very effective weedicide for control of narrow leaf weeds in broad leaf crops.It does not burn the weeds but kills the weeds - so they do not regerminate.");
//		product.setActive(true);
//
//		PriceService priceService = new PriceService();
//		Price price = new Price();
//
//		price.setPrice(1000);
//		product.setPrice(price);
//		

		ProductService productService = new ProductService();
		System.out.println("All Products:");

		productService.getAll();

		CategoryService categoryService = new CategoryService();
		try {
			categoryService.findCategoriesByCategoryId(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
