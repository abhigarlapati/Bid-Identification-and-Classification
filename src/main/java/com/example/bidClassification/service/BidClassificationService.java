package com.example.bidClassification.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bidClassification.model.Bid;
import com.example.bidClassification.model.BidClassification;
import com.example.bidClassification.repository.BidClassificationRepository;

@Service
public class BidClassificationService {
	
	@Autowired
    private BidClassificationRepository repository;

    public List<BidClassification> getAll() {
        return repository.findAll();
    }

    public BidClassification addClassification(BidClassification classification) {
 
    	
        return repository.save(classification);
    }

    public void deleteClassification(Long id) {
        repository.deleteById(id);
    }
    
    private final  Map<String, List<String>> categoryKeywords = Map.of(
            "Infrastructure", List.of("highway", "road", "bridge", "construction"),
            "Electrical", List.of("electrical", "wiring", "power", "circuit"),
            "Domestic", List.of("domestic", "household", "residential"),
            "Solar", List.of("solar", "photovoltaic", "panel", "energy"),
            "Water", List.of("water", "pipeline", "irrigation")
        );
    

    public  String classifyText(String text) {
        text = text.toLowerCase();
        Set<String> matchedCategories = new HashSet<>();

        for (Map.Entry<String, List<String>> entry : categoryKeywords.entrySet()) {
            if (entry.getValue().stream().anyMatch(text::contains)) {
                matchedCategories.add(entry.getKey());
            }
        }

        if (matchedCategories.isEmpty()) {
            return "Uncategorized Or Other";
        }

        // For simplicity, return the first matched category
        return matchedCategories.iterator().next(); 
        // OR: return String.join(", ", matchedCategories); if you want all
    }
    
	
	
	
	private boolean containsAny(String text, List<String> keywords) {
        return keywords.stream().anyMatch(text::contains);
    }

}
