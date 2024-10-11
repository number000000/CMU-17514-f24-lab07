package AndrewWebServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class AndrewWebServicesTest {
    Database database;
    InMemoryDatabase fakeDatabase;
    RecSys recommender;
    PromoService promoService;
    AndrewWebServices andrewWebService;
    RecSys mockRecSys;
    PromoService mockPromoService;

    @Before
    public void setUp() {
        // You need to use some mock objects here
        database = new Database(); // We probably don't want to access our real database...
        fakeDatabase = new InMemoryDatabase();
        recommender = new RecSys();
        promoService = new PromoService();

        mockRecSys = mock(RecSys.class);
        mockPromoService = mock(PromoService.class);
        
        //andrewWebService = new AndrewWebServices(database, recommender, promoService);
        andrewWebService = new AndrewWebServices(fakeDatabase, mockRecSys, mockPromoService);
    }

    @Test
    public void testLogIn() {
        // This is taking way too long to test
        //assertTrue(andrewWebService.logIn("Scotty", 17214));
        assertTrue(andrewWebService.logIn("Scotty", 17214));
    }

    @Test
    public void testGetRecommendation() {
        // This is taking way too long to test
        //assertEquals("Animal House", andrewWebService.getRecommendation("Scotty"));

        when(mockRecSys.getRecommendation("Scotty")).thenReturn("Animal House");
        String result = andrewWebService.getRecommendation("Scotty");

        verify(mockRecSys).getRecommendation("Scotty");
        assertEquals("Animal House", result);
    }

    @Test
    public void testSendEmail() {
        // How should we test sendEmail() when it doesn't have a return value?
        // Hint: is there something from Mockito that seems useful here?
        String fakeEmail = "fake email.";
        andrewWebService.sendPromoEmail(fakeEmail);

        verify(mockPromoService).mailTo(fakeEmail);
    }

    @Test
    public void testNoSendEmail() {
        // How should we test that no email has been sent in certain situations (like right after logging in)?
        // Hint: is there something from Mockito that seems useful here?
        String fakeEmail = "fake email.";
        andrewWebService.logIn("Scotty", 17214);

        verify(mockPromoService, never()).mailTo(fakeEmail);
    }
}
