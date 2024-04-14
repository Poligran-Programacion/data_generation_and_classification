import com.poligran.repository.RouteRepository;
import com.poligran.service.GenerateInfoFiles;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class contains test cases for the GenerateInfoFiles class.
 * It verifies the functionality of creating files for sellers and products.
 */
public class GenerateInfoFilesTest {

    private static final RouteRepository routeRepository = new RouteRepository();

    /**
     * Test case to verify the creation of files for sellers and products.
     * @throws IOException if an I/O error occurs while performing the test.
     */
    @Test
    public void testCreateFilesForBase() throws IOException {
        // Execute the function that creates files for sellers
        GenerateInfoFiles.initialConfig();

        // Verify if the base files were created
        assertTrue(new File(routeRepository.getBaseRoute() + "products.txt").exists());
        assertTrue(new File(routeRepository.getBaseRoute() + "seller.txt").exists());
        assertTrue(new File(routeRepository.getReportRoute() + "total_sales_by_seller.csv").exists());
    }
}
