package offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {
	
	@Autowired
	OfferManager offerManager;
	
	@RequestMapping("/offers")
	public Offer[] getOffers(){
		return offerManager.getOffers();
	}

	@RequestMapping("/offer")
	public Offer getOffer(@RequestParam(name="productNumber", required=true) long productNumber){
		return offerManager.getOffer(productNumber);
	}
}
