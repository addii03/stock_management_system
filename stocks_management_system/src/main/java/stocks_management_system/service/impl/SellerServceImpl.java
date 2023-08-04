package stocks_management_system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import stocks_management_system.entity.Seller;
import stocks_management_system.exception.CustomNewException;
import stocks_management_system.helper.SellerHelper;
import stocks_management_system.repository.SellerRepository;
import stocks_management_system.request.SellerRequest;
import stocks_management_system.responce.SellerResponce;
import stocks_management_system.servicee.SellerService;

@Service
public class SellerServceImpl implements SellerService {

	@Autowired
	SellerHelper sellerHelper;
	@Autowired
	SellerRepository sellerRepository;

	@Override
	public String createSeller(SellerRequest sellerRequest) {

		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String encryptedPwd = bcrypt.encode(sellerRequest.getPassword());
		sellerRequest.setPassword(encryptedPwd);

		Seller seller = sellerHelper.toEntity(sellerRequest);
		Seller save = sellerRepository.save(seller);
		return save.getId() + ": Seller added to database Successfully";

	}

	@Override
	public SellerResponce getById(long id) {
		Seller seller = sellerRepository.findById(id).orElseThrow(() -> new CustomNewException("seller id not found"));
		return sellerHelper.toDto(seller);
	}

	@Override
	public List<SellerResponce> getAllSeller() {
		List<Seller> sellerList = sellerRepository.findAll();
		return sellerList.stream().map(sellerHelper::toDto).collect(Collectors.toList());
	}

	@Override
	public String authenticateSeller(SellerRequest sellerRequest) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		Seller username = sellerRepository.findByUsername(sellerRequest.getUsername());
//
//		if (optSeller.isPresent()) {
//			Seller dbSeller = optSeller.get();

		if (bcrypt.matches(sellerRequest.getPassword(), username.getPassword())) {
			return "Seller Login Successfully";
		} else {
			return "Incorrect Password";
		}
//		}
//		throw new CustomNewException("Seller is not found with this id");

	}

	@Override
	public Seller findByUsername(String username) {

		return sellerRepository.findByUsername(username);
	}

}
