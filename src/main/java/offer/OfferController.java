package offer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {
	
	private final static Logger log = LoggerFactory.getLogger(OfferController.class);
	
	@Autowired
	OfferManager offerManager;
	
	@RequestMapping("/offers")
	public Offer[] getOffers(){
		log.info("Requested log: ");
		for(Offer offer : offerManager.getOffers()){
			log.info(offer.getProductName());
		}
		return offerManager.getOffers();
	}

	@RequestMapping("/offer")
	public Offer getOffer(@RequestParam(name="productNumber", required=true) long productNumber){
		return offerManager.getOffer(productNumber);
	}
}
