package integration;

import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolver;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.engine.discovery.DiscoverySelectorResolver.resolver;
import static sun.nio.cs.Surrogate.is;

public class AddressResolverIT {

    AddressResolver resolver = new AddressResolver(new TqsBasicHttpClient());


    @BeforeEach
    public void init(){
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        //todo

        // repeat the same tests conditions from AddressResolverTest, without mocks
        Optional<Address> result = resolver.findAddressForLocation(40.633116,-8.658784);
        Address esperado = new Address("Jacinto Magalhães", "Jacksonville", "FL", "32202", "1");

        //return
        //assertThat(result.toString(), is(esperado.toString()));
        assertEquals(result.toString(), esperado.toString());

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        //todo
        // repeat the same tests conditions from AddressResolverTest, without mocks
        assertThrows(IndexOutOfBoundsException.class, () -> resolver.findAddressForLocation(40.633116,-8.658784));
        
    }

}
