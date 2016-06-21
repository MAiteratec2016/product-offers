package offer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OfferManager {

	private Offer[] offerArray;
	private Map<Long, Offer> offerMap;

	public OfferManager() {
	}

	public void updateOffers() {

		String detailsUrl = "http://product-details-service:8080/products";
		ResponseEntity<PagedResources<ProductDetails>> detailsResponseEntity = getRestTemplate().exchange(detailsUrl,
				HttpMethod.GET, null, new ParameterizedTypeReference<PagedResources<ProductDetails>>() {
				});
		PagedResources<ProductDetails> detailsResources = detailsResponseEntity.getBody();

		String pricesUrl = "http://product-prices-service:8080/products";
		ResponseEntity<PagedResources<ProductPrice>> pricesResponseEntity = getRestTemplate().exchange(pricesUrl,
				HttpMethod.GET, null, new ParameterizedTypeReference<PagedResources<ProductPrice>>() {
				});
		PagedResources<ProductPrice> pricesResources = pricesResponseEntity.getBody();

		Map<Long, ProductPrice> pricesMap = new HashMap<>();
		for (ProductPrice pp : pricesResources.getContent()) {
			pricesMap.put(pp.getProductNumber(), pp);
		}

		offerMap = new HashMap<>();

		for (ProductDetails pd : detailsResources.getContent()) {
			ProductPrice price = pricesMap.get(pd.getProductNumber());
			offerMap.put(pd.getProductNumber(), new Offer(pd.getProductNumber(), pd.getName(), pd.getDescription(),
					price.getPrice(), price.getCurrency()));
		}

		offerArray = offerMap.values().toArray(new Offer[offerMap.values().size()]);
	}

	public Offer[] getOffers() {
		updateOffers();
		return offerArray;
	}

	public Offer getOffer(long productNumber) {
		return offerMap.get(productNumber);
	}

	private RestTemplate getRestTemplate() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(new Jackson2HalModule());

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
		converter.setObjectMapper(mapper);
		return new RestTemplate(Arrays.asList(converter));
	}
}
