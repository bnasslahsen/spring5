package com.training.sample.hellorest.exposition;

import java.util.List;

import com.training.sample.hellorest.common.NotFoundException;
import com.training.sample.hellorest.domaine.Info;
import com.training.sample.hellorest.repository.InfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/infos")
public class InfoController {

    @Autowired
    private InfoRepository infoRepository;

    @GetMapping
    public List<Info> getAllInfo() {
    	return infoRepository.findAll();
    }

	@GetMapping("/{idInfo}")
	public Info findOneRepo(@PathVariable Long idInfo) {
		return infoRepository.findById(idInfo).orElseThrow(() -> new NotFoundException("id not found: "+ idInfo));
	}

}
